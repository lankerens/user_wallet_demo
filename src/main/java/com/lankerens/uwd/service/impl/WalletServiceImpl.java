package com.lankerens.uwd.service.impl;

import com.lankerens.uwd.bean.PayInfo;
import com.lankerens.uwd.bean.TblWalletDetailsEntity;
import com.lankerens.uwd.bean.TblWalletEntity;
import com.lankerens.uwd.mapper.WalletDetailMapper;
import com.lankerens.uwd.mapper.WalletMapper;
import com.lankerens.uwd.service.WalletService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zhusing
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 8:47 PM
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private WalletDetailMapper detailMapper;

    @Override
    public double getUserWalletBalance(Long uid) {
        Optional<TblWalletEntity> entity = walletMapper.findById(uid);
        return entity.map(tblWalletEntity -> tblWalletEntity.getBalance().doubleValue()).orElse(0.0);
    }


    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void userPayWallet(PayInfo payInfo) {
        BigDecimal price = new BigDecimal(payInfo.getPrice());

        RLock lock = redissonClient.getLock(String.valueOf(payInfo.getUid()));
        try {
            boolean b = lock.tryLock(4000, 2000, TimeUnit.MILLISECONDS);
            if (b) {
                TblWalletEntity wallet = walletMapper.getByUid(payInfo.getUid());
                BigDecimal balance = wallet.getBalance();
                if (balance.compareTo(price) < 0) {
                    throw new RuntimeException("pay error");
                }
                BigDecimal temp = balance.subtract(price);
                // 落库 -
                int rows = walletMapper.updateBalance(temp.doubleValue(), balance.doubleValue(), payInfo.getUid());
                if (rows < 1) {
                    throw new RuntimeException("pay error, pls try again");
                }

                detailMapper.save(new TblWalletDetailsEntity().setBalance(temp).setType(0).setWid(wallet.getId())
                        .setChange(price.negate()).setCreateTime(new Timestamp(new Date().getTime())));

                // 缓存
//                redisTemplate.opsForList().rightPush("walletDetailsFlush",
//                        new TblWalletDetailsEntity().setBalance(temp).setType(0).setWid(wallet.getId())
//                                .setChange(price.negate()).setCreateTime(new Timestamp(new Date().getTime())));
            }

        } catch (InterruptedException e) {
            throw new RuntimeException("pay error " + e.getMessage());
        } finally {
            lock.unlock();
        }

//        TODO: 这里要保证原子操作 ==> 换成 redisson吧
//        Boolean absent = redisTemplate.opsForHash().putIfAbsent(payInfo.getUid(), 1, balance);
//        redisTemplate.expire(payInfo.getUid(), 2, TimeUnit.MINUTES);
//        if(absent) {
//        } else {
//        }

    }


    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void addUserWallet(long uid, Double num) {
        RLock lock = redissonClient.getLock(String.valueOf(uid));
        try {
            boolean b = lock.tryLock(4000, 2000, TimeUnit.MILLISECONDS);
            if(b) {
                TblWalletEntity wallet = walletMapper.getByUid(uid);
                BigDecimal balance = wallet.getBalance();
                BigDecimal temp = balance.add(BigDecimal.valueOf(num));
                int rows = walletMapper.updateBalance(temp.doubleValue(), balance.doubleValue(), uid);

                if(rows < 1) {
                    throw new RuntimeException("pay error, pls try again");
                }
                // 落库.
                detailMapper.save(new TblWalletDetailsEntity().setBalance(temp).setType(0).setWid(wallet.getId())
                        .setChange(BigDecimal.valueOf(num)).setCreateTime(new Timestamp(new Date().getTime())));

//                redis push
//                applicationContext.publishEvent(uid);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public List<TblWalletDetailsEntity> getBalanceDetailList(Long uid, Integer pageIndex, Integer pageSize) {
        return walletMapper.getListByUid(uid, pageIndex, pageSize);
    }


    @EventListener
    public void eventDeal(long event){
        // redis pop
        System.out.println(event);
    }

}

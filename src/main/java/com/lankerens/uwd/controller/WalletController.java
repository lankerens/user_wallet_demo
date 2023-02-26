package com.lankerens.uwd.controller;

import com.lankerens.uwd.bean.PayInfo;
import com.lankerens.uwd.bean.TblWalletDetailsEntity;
import com.lankerens.uwd.service.impl.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lankerens
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 8:17 PM
 */
@RestController
@RequestMapping("api/wallet")
public class WalletController {

    @Autowired
    private WalletServiceImpl walletService;

    /**
     * 查询用户钱包余额
     * @param uid
     * @return
     */
    @GetMapping("/{uid}")
    public double getUserWalletBalance(@PathVariable("uid") Long uid){
        return walletService.getUserWalletBalance(uid);
    }

    @PostMapping("/pay")
    public void userPayWallet(@RequestBody PayInfo info){
        walletService.userPayWallet(info);
    }


    @GetMapping("/{uid}/{num}")
    public void addUserWallet(@PathVariable("uid") Long uid, @PathVariable("num") Double num){
        walletService.addUserWallet(uid, num);
    }


    @GetMapping("/his/list/{uid}/{pageIndex}/{pageSize}")
    public void getBalanceDetailList(@PathVariable("uid") Long uid,
                                     @PathVariable("pageIndex") Integer pageIndex,
                                     @PathVariable("pageSize") Integer pageSize){

        List<TblWalletDetailsEntity> list = walletService.getBalanceDetailList(uid, pageIndex, pageSize);
    }
}

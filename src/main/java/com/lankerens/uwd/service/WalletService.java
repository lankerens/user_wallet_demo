package com.lankerens.uwd.service;

import com.lankerens.uwd.bean.PayInfo;
import com.lankerens.uwd.bean.TblWalletDetailsEntity;

import java.util.List;

/**
 * @author zhusing
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 8:47 PM
 */
public interface WalletService {

    double getUserWalletBalance(Long uid);

    void userPayWallet(PayInfo payInfo);

    void addUserWallet(long uid, Double num);

    List<TblWalletDetailsEntity> getBalanceDetailList(Long uid, Integer pageIndex, Integer pageSize);

}

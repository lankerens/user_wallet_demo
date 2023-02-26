package com.lankerens.uwd.mapper;

import com.lankerens.uwd.bean.TblWalletDetailsEntity;
import com.lankerens.uwd.bean.TblWalletEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhusing
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 8:22 PM
 */
@Repository
public interface WalletMapper extends CrudRepository<TblWalletEntity, Long> {



//    default int updateBalance(double newValue, double oldValue, long uid) {
//
//    }

    @Query(value = "select twe.id, twe.balance, twe.uid from TblWalletEntity as twe " +
            "where twe.state = 1 and twe.uid = ?1")
    TblWalletEntity getByUid(long uid);

    @Query(value = "update TblWalletEntity as twe set twe.balance = ?1 where twe.balance = ?2 and twe.uid = ?3")
    int updateBalance(double newValue, double oldValue, long uid) ;



    @Query(value = "select wde.id, wde.balance, wde.change, wde.orderId, wde.type, wde.createTime " +
            "from TblWalletDetailsEntity as wde left join TblWalletEntity as twe on wde.wid = twe.id " +
            "where twe.uid = ?1 ")
    List<TblWalletDetailsEntity> getListByUid(long uid, int pageSize, int pageIndex);

}


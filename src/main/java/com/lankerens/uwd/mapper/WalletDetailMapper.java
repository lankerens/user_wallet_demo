package com.lankerens.uwd.mapper;

import com.lankerens.uwd.bean.TblWalletDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lankerens
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 10:13 PM
 */
@Repository
public interface WalletDetailMapper extends CrudRepository<TblWalletDetailsEntity, Long> {



}

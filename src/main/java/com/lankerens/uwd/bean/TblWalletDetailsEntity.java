package com.lankerens.uwd.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author lankerens
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 9:16 PM
 */
@Accessors(chain = true)
@Setter
@Getter
@Entity
@Table(name = "tbl_wallet_details", schema = "wallet", catalog = "")
public class TblWalletDetailsEntity implements Serializable {
    @Basic
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "wid")
    private Long wid;
    @Basic
    @Column(name = "type")
    private Integer type;
    @Basic
    @Column(name = "change")
    private BigDecimal change;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "balance")
    private BigDecimal balance;
    @Basic
    @Column(name = "order_id")
    private String orderId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblWalletDetailsEntity that = (TblWalletDetailsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(wid, that.wid) && Objects.equals(type, that.type) && Objects.equals(change, that.change) && Objects.equals(createTime, that.createTime) && Objects.equals(balance, that.balance) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wid, type, change, createTime, balance, orderId);
    }
}

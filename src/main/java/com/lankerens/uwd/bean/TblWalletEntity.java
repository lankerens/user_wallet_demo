package com.lankerens.uwd.bean;

import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author zhusing
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 9:16 PM
 */
@Accessors(chain = true)
@Entity
@Table(name = "tbl_wallet", schema = "wallet", catalog = "")
public class TblWalletEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "uid")
    private long uid;
    @Basic
    @Column(name = "balance")
    private BigDecimal balance;
    @Basic
    @Column(name = "state")
    private Integer state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblWalletEntity that = (TblWalletEntity) o;
        return id == that.id && uid == that.uid && Objects.equals(balance, that.balance) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, balance, state);
    }
}

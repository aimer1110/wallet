package com.longchain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private Long id;
    private String openId;
    private String passphrase;
    private String paymentKeyword;
    private String walletAddress;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Timestamp deleteTime;

    public User() {
    }

    public User(Long id, String openId, String passphrase, String paymentKeyword, String walletAddress, Timestamp createTime, Timestamp modifyTime, Timestamp deleteTime) {
        this.id = id;
        this.openId = openId;
        this.passphrase = passphrase;
        this.paymentKeyword = paymentKeyword;
        this.walletAddress = walletAddress;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.deleteTime = deleteTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getPaymentKeyword() {
        return paymentKeyword;
    }

    public void setPaymentKeyword(String paymentKeyword) {
        this.paymentKeyword = paymentKeyword;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", passphrase='" + passphrase + '\'' +
                ", paymentKeyword='" + paymentKeyword + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}

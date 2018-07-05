package com.longchain.service;

import com.longchain.entity.ResponseModel;

public interface UserService {
    String getAccount(String openId) throws Exception;

    void setPaymentKey(String openId, String paymentKey) throws Exception;

    Boolean hasPaymentKey(String openId);

    Integer sendToken(String openId, String paymentKey, String targetAccount, Long amount) throws Exception;

    ResponseModel sendBag(String openId, String paymentKey, Long totalMoney, Long number, String extraInfo) throws Exception;

    ResponseModel openBag(String openId, String targetAddress, Long bagNumber) throws Exception;

    ResponseModel getReceivedMoney(String openId, String targetAddress, Long bagNumber) throws Exception;
}

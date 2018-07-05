package com.longchain.service;

import java.math.BigInteger;

public interface TokenService {
    String getTokenName() throws Exception;

    Long balanceOf(String address) throws Exception;

    void transfer(String from, String to, Long amount, String passphrase) throws Exception;

    Long getBagNum(String address) throws Exception;

    void sendBag(String address, String passphrase, Long totalMoney, Long num, String extraInfo) throws Exception;

    void openBag(String address, String targetAddress, Long bagNumber, String passphrase) throws Exception;

    Boolean isOpened(String address, String targetAddress, Long bagNumber) throws Exception;

    Long getReceivedMoney(String address, String targetAddress, Long bagNumber) throws Exception;
}

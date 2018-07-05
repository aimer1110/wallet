package com.longchain.service.impl;

import com.longchain.service.TokenService;
import com.longchain.utils.EthUtil;
import com.longchain.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    private static final Long GAS_LIMIT = 9999999L;
    private static final String TOKEN_ADDRESS = "0x5bf3e0363252c5c7bdfde9927ea3c5ce393a6dc2";
    private static final String DEFAULT_ACCOUNT = "0xbcb1a8e2abb7ee6f90d010b3f0ade9572ebb8c12";
    private static final String METHOD_BALANCE_OF = "0x70a08231";
    private static final String METHOD_TRANSFER = "0xa9059cbb";
    private static final String METHOD_SEND_BAG = "0x4d118ac6";
    private static final String METHOD_GET_BAG_NUM = "0x67e0badb";
    private static final String METHOD_OPEN_BAG = "0xbfd7b16c";
    private static final String METHOD_GET_RECEIVED_MONEY = "0x75b32289";
    private Web3j web3j = EthUtil.initWeb3j();

    @Override
    public String getTokenName() throws Exception {
        Transaction tx = new Transaction(DEFAULT_ACCOUNT, EthUtil.getNonce(DEFAULT_ACCOUNT), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, "0x06fdde03");
        return StringUtils.hexStringToString(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public Long balanceOf(String address) throws Exception {
        String data = METHOD_BALANCE_OF + StringUtils.prefixTo32(address.substring(2));
        Transaction tx = new Transaction(DEFAULT_ACCOUNT, EthUtil.getNonce(DEFAULT_ACCOUNT), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public void transfer(String from, String to, Long amount, String passphrase) throws Exception {
        EthUtil.unlockAccount(from, passphrase, BigInteger.valueOf(2));
        String data = METHOD_TRANSFER + StringUtils.prefixTo32(to.substring(2)) + StringUtils.long2Hex(amount);
        Transaction tx = new Transaction(from, EthUtil.getNonce(from), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        EthUtil.sendTransaction(tx, passphrase);
    }

    @Override
    public Long getBagNum(String address) throws Exception {
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, METHOD_GET_BAG_NUM);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public void sendBag(String address, String passphrase, Long totalMoney, Long num, String extraInfo) throws Exception {
        String data = METHOD_SEND_BAG + StringUtils.long2Hex(totalMoney) + StringUtils.long2Hex(num) + StringUtils.long2Hex(96l) + StringUtils.long2Hex((long) extraInfo.length()) + StringUtils.suffixTo32(StringUtils.str2Hex(extraInfo));
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        EthUtil.sendTransaction(tx, passphrase);
    }

    @Override
    public void openBag(String address, String targetAddress, Long bagNumber, String passphrase) throws Exception {
        String data = METHOD_OPEN_BAG + StringUtils.prefixTo32(targetAddress.substring(2)) + StringUtils.long2Hex(bagNumber);
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        EthUtil.sendTransaction(tx, passphrase);
    }

    @Override
    public Boolean isOpened(String address, String targetAddress, Long bagNumber) throws Exception {
        String data = METHOD_OPEN_BAG + StringUtils.prefixTo32(targetAddress.substring(2)) + StringUtils.long2Hex(bagNumber);
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue()) == 10001;
    }

    @Override
    public Long getReceivedMoney(String address, String targetAddress, Long bagNumber) throws Exception {
        String data = METHOD_GET_RECEIVED_MONEY + StringUtils.prefixTo32(targetAddress.substring(2)) + StringUtils.long2Hex(bagNumber);
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(GAS_LIMIT), TOKEN_ADDRESS, BigInteger.ZERO, data);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }
}

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
    private static final String TOKEN_ADDRESS = "0x6bf1e908647a565e6e8c0905af87a1966ce1dbb5";
    private static final String DEFAULT_ACCOUNT = "0xbcb1a8e2abb7ee6f90d010b3f0ade9572ebb8c12";
    private static final String METHOD_BALANCE_OF = "0x70a08231";
    private static final String METHOD_TRANSFER = "0xa9059cbb";
    private static final String METHOD_SEND_BAG = "0x4d118ac6";
    private static final String METHOD_GET_BAG_NUM = "0x67e0badb";
    private static final String METHOD_GET_BAG = "0xbfd7b16c";
    private Web3j web3j = EthUtil.initWeb3j();

    @Override
    public String getTokenName() throws Exception {
        Transaction tx = new Transaction(DEFAULT_ACCOUNT, EthUtil.getNonce(DEFAULT_ACCOUNT), BigInteger.ZERO, BigInteger.valueOf(999999), TOKEN_ADDRESS, BigInteger.ZERO, "0x06fdde03");
        return StringUtils.hexStringToString(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public Long balanceOf(String address) throws Exception {
        String data = METHOD_BALANCE_OF + StringUtils.prefixTo32(address.substring(2));
        Transaction tx = new Transaction(DEFAULT_ACCOUNT, EthUtil.getNonce(DEFAULT_ACCOUNT), BigInteger.ZERO, BigInteger.valueOf(999999), TOKEN_ADDRESS, BigInteger.ZERO, data);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public void transfer(String from, String to, Long amount, String passphrase) throws Exception {
        EthUtil.unlockAccount(from, passphrase, BigInteger.valueOf(2));
        String data = METHOD_TRANSFER + StringUtils.prefixTo32(to.substring(2)) + StringUtils.long2Hex(amount);
        Transaction tx = new Transaction(from, EthUtil.getNonce(from), BigInteger.ZERO, BigInteger.valueOf(999999), TOKEN_ADDRESS, BigInteger.ZERO, data);
        EthUtil.sendTransaction(tx, passphrase);
    }

    @Override
    public Long getBagNum(String address) throws Exception {
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(999999), TOKEN_ADDRESS, BigInteger.ZERO, METHOD_GET_BAG_NUM);
        return StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
    }

    @Override
    public void sendBag(String address, String passphrase, Long totalMoney, Long num, String extraInfo) throws Exception {
        String data = METHOD_SEND_BAG + StringUtils.long2Hex(totalMoney) + StringUtils.long2Hex(num) + StringUtils.long2Hex(96l) + StringUtils.long2Hex((long) extraInfo.length()) + StringUtils.suffixTo32(StringUtils.str2Hex(extraInfo));
        Transaction tx = new Transaction(address, EthUtil.getNonce(address), BigInteger.ZERO, BigInteger.valueOf(9999999), TOKEN_ADDRESS, BigInteger.ZERO, data);
        EthUtil.sendTransaction(tx, passphrase);
    }
}

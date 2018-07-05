package com.longchain.service.impl;

import com.longchain.entity.ResponseModel;
import com.longchain.entity.User;
import com.longchain.mapper.UserMapper;
import com.longchain.service.TokenService;
import com.longchain.service.UserService;
import com.longchain.utils.DateUtils;
import com.longchain.utils.EthUtil;
import com.longchain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Integer SEND_SUCCESS = 0;
    private static final Integer WRONG_KEYWORD = 1;
    private static final Integer INSUFFICIENT_FOUNDS = 2;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public String getAccount(String openId) throws Exception {
        User user = userMapper.selectByOpenId(openId);
        if (null == user) {
            String passphrase = StringUtils.md5Password(openId);
            String walletAddress = EthUtil.newAccount(passphrase);
            userMapper.insert(new User(null, openId, passphrase, null, walletAddress, DateUtils.nowT(), null, null));
            return walletAddress;
        } else {
            return user.getWalletAddress();
        }
    }

    @Override
    public void setPaymentKey(String openId, String paymentKey) throws Exception {
        userMapper.setPaymentKey(openId, paymentKey);
    }

    @Override
    public Boolean hasPaymentKey(String openId) {
        User user = userMapper.selectByOpenId(openId);
        return StringUtils.isNotBlank(user.getPaymentKeyword());
    }

    @Override
    public Integer sendToken(String openId, String paymentKey, String targetAccount, Long amount) throws Exception {
        User user = userMapper.selectByOpenId(openId);
        if (!paymentKey.equals(user.getPaymentKeyword())) {
            return WRONG_KEYWORD;
        }
        if (tokenService.balanceOf(user.getWalletAddress()) < amount) {
            return INSUFFICIENT_FOUNDS;
        }
        tokenService.transfer(user.getWalletAddress(), targetAccount, amount, user.getPassphrase());
        return SEND_SUCCESS;
    }

    @Override
    public ResponseModel sendBag(String openId, String paymentKey, Long totalMoney, Long number, String extraInfo) throws Exception {
        User user = userMapper.selectByOpenId(openId);
        if (!paymentKey.equals(user.getPaymentKeyword())) {
            return new ResponseModel(false, 10001, "支付密码错误");
        }
        if (tokenService.balanceOf(user.getWalletAddress()) < totalMoney) {
            return new ResponseModel(false, 10002, "账户余额不足");
        }
        Long num = tokenService.getBagNum(user.getWalletAddress());
        tokenService.sendBag(user.getWalletAddress(), user.getPassphrase(), totalMoney, number, extraInfo);
        return new ResponseModel(true, 10000, "发送成功", num);
    }
}

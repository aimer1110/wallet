package com.longchain.controller;

import com.longchain.entity.ResponseModel;
import com.longchain.service.TokenService;
import com.longchain.service.UserService;
import com.longchain.utils.EthUtil;
import com.longchain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;

/**
 * Created by liurui on 2018/6/28.
 */
@RestController
@RequestMapping("/html/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取钱包地址
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getAddress(@RequestParam(value = "openId", required = true) String openId) {
        try {
            String address = userService.getAccount(openId);
            return new ResponseModel(true, 10000, "获取成功", address);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误", null);
        }
    }

    /**
     * 设置支付密码
     *
     * @param openId
     * @param paymentKey
     * @return
     */
    @RequestMapping(value = "/setPaymentKey", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel setPaymentKey(@RequestParam(value = "openId", required = true) String openId,
                                       @RequestParam(value = "paymentKey", required = true) String paymentKey) {
        try {
            userService.setPaymentKey(openId, paymentKey);
            return new ResponseModel(true, 10000, "设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误");
        }
    }

    /**
     * 获取支付密码
     * @param openId
     * @return
     */
    @RequestMapping(value = "/hasPaymentKey", method = RequestMethod.GET)
    @ResponseBody
    public Boolean hasPaymentKey(@RequestParam(value = "openId", required = true) String openId) {
        return userService.hasPaymentKey(openId);

    }

    /**
     * 发送交易
     * @param openId
     * @param paymentKey
     * @param targetAccount
     * @param amount
     * @return
     */
    @RequestMapping(value = "/sendToken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel sendToken(@RequestParam(value = "openId", required = true) String openId,
                                   @RequestParam(value = "paymentKey", required = true) String paymentKey,
                                   @RequestParam(value = "targetAccount", required = true) String targetAccount,
                                   @RequestParam(value = "amount", required = true) String amount) {
        try {
            Integer res = userService.sendToken(openId, paymentKey, targetAccount, Long.parseLong(amount));
            switch (res) {
                case 1:
                    return new ResponseModel(false, 10001, "支付密码错误");
                case 2:
                    return new ResponseModel(false, 10002, "账户余额不足");
                default:
                    return new ResponseModel(true, 10000, "转账成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误");
        }
    }

    /**
     * 发送糖包
     * @param openId
     * @param paymentKey
     * @param totalMoney
     * @param number
     * @param extraInfo
     * @return
     */
    @RequestMapping(value = "/sendBag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel sendBag(@RequestParam(value = "openId", required = true) String openId,
                                 @RequestParam(value = "paymentKey", required = true) String paymentKey,
                                 @RequestParam(value = "totalMoney", required = true) String totalMoney,
                                 @RequestParam(value = "number", required = true) String number,
                                 @RequestParam(value = "extraInfo", required = true) String extraInfo) {
        try {
            return userService.sendBag(openId, paymentKey, Long.parseLong(totalMoney), Long.parseLong(number), extraInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误");
        }
    }

//    public ResponseModel getBag()

    /**
     * 获取token名
     *
     * @return
     */
    @RequestMapping(value = "/getTokenName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getTokenName() {
        try {
            String tokenName = tokenService.getTokenName();
            return new ResponseModel(true, 10000, "获取成功", tokenName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误", null);
        }
    }

    /**
     * 获取账户余额
     *
     * @param address
     * @return
     */
    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getBalanceOf(@RequestParam(value = "address", required = true) String address) {
        try {
            Long balance = tokenService.balanceOf(address);
            return new ResponseModel(true, 10000, "获取成功", balance);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, 10003, "系统错误", null);
        }
    }
}

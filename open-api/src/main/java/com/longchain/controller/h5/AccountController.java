package com.longchain.controller.h5;

import com.longchain.domain.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liurui on 2018/6/28.
 */
@RestController
@RequestMapping("/html/account")
public class AccountController {
    public ResponseModel getAddress(@RequestParam(value = "openId", required = true) String openId){
        try {
            // TODO:
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseModel(false,10003,"系统错误",null);
        }
        return null;
    }
}

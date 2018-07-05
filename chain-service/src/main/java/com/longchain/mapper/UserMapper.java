package com.longchain.mapper;

import com.longchain.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void insert(User user);
    User selectByOpenId(String openId);
    void setPaymentKey(@Param("openId") String openId,
                       @Param("paymentKey") String paymentKey);
}

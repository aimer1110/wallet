package com.longchain.entity;

import java.io.Serializable;

public class ResponseModel implements Serializable {
    public ResponseModel() {

    }

    public ResponseModel(String message) {
        this.code = 500;
        this.success = false;
        this.message = message;
    }

    public ResponseModel(String message, Exception e) {
        this.code = 500;
        this.success = false;
        this.message = message;
        System.out.println(message);
        e.printStackTrace();
    }

    public ResponseModel(Boolean success, String message) {
        this.code = 200;
        this.success = success;
        this.message = message;
    }

    public ResponseModel(Boolean success, Object data) {
        this.code = 200;
        this.success = success;
        this.message = "";
        this.data = data;
    }

    public ResponseModel(Boolean success, Integer code) {
        this.code = code;
        this.success = success;
    }

    public ResponseModel(Boolean success, String message, Object data) {
        this.code = 200;
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public ResponseModel(Boolean success, Integer code, Object data) {
        this.code = code;
        this.success = success;
        this.data = data;
    }

    public ResponseModel(Boolean success, Integer code, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(Boolean success, Integer code, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    private Integer code;// code
    private String message;// 返回消息
    private Object data;// 返回字符串
    private Boolean success;//是否成功

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

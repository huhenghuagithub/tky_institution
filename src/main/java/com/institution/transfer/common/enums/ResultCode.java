package com.institution.transfer.common.enums;

/**
 * 统一状态返回码
 * @Date: 2020/4/28 15:56
 */
public enum ResultCode {

    OK("00000", "成功"),

    /** 一级宏观错误码 */
    CLIENT_ERROR("A0001", "用户端错误 ");


    private String status;
    private String message;

    ResultCode(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

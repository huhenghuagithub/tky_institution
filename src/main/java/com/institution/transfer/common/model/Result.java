package com.institution.transfer.common.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.institution.transfer.common.enums.ResultCode;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共返回DTO，交由前端使用
 * @Author: Jason
 * @Date: 2020/04/24 09:28
 */
public class Result<T> {

    public interface commonResult{};

    public interface standardResult extends commonResult{};

    // 响应状态码
    @JsonView(commonResult.class)
    private String status;

    // 响应信息
    @JsonView(commonResult.class)
    private String message;

    // 响应内容
    @JsonView(standardResult.class)
    private T body;

    public Result() {
        this.status = ResultCode.OK.getStatus();
        this.message = ResultCode.OK.getMessage();
    }

    public Result(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, String message) {
        this(resultCode);
        this.message = message;
    }

    public Result(Collection<T> collection) {
        this.status = ResultCode.OK.getStatus();
        this.message = ResultCode.OK.getMessage();
        this.body = (T) collection;
    }

    public Result(ResultCode resultCode, Collection<T> collection) {
        this(collection);
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    public Result(Page<T> page) {
        this.status = ResultCode.OK.getStatus();
        this.message = ResultCode.OK.getMessage();
        Map<String, Object> info = new HashMap<>(4);
        info.put("total", page.getTotalElements());
//        info.put("pageSize", page.getNumber());
//        info.put("pageNum", page.getSize());
//        info.put("totalPage", page.getTotalPages());
        info.put("item", page.getContent());
        this.body = (T) info;
    }

    public Result(ResultCode resultCode, Page<T> page) {
        this(page);
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    public Result(T body) {
        this.status = ResultCode.OK.getStatus();
        this.message = ResultCode.OK.getMessage();
        this.body = body;
    }

    public Result(ResultCode resultCode, T body) {
        this(body);
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}

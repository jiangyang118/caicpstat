/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.caicp.controller.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author liman
 */
public class WebResponse<T> {

    /**
     * 调用成功
     */
    public static final int WEB_STATUS_OK = 0;

    /**
     * 参数错误
     */
    public static final int WEB_STATUS_PARAM_ERROR = 10100;

    /**
     * 鉴权错误
     */
    public static final int WEB_STATUS_SIGN_ERROR = 10101;

    /**
     * 系统错误
     */
    public static final int WEB_STATUS_SYSTEM_ERROR = 10500;
    /**
     * 业务异常
     */
    public static final int WEB_STATUS_BUSSINESS_ERROR = 10505;

    /**
     * 返回状态码
     */
    private int status = WEB_STATUS_OK;

    /**
     * 返回成功
     */
    public static final String SUCCESS_DATA = "success";

    /**
     * 返回错误
     */
    public static final String ERROR_DATA = "error";

    /**
     * 调用成功返回的数据
     */
    private T data;

    /**
     * 调用失败返回的数据，大多数情况下为null
     */
    private String errorMsg;

    public WebResponse() {

    }

    public WebResponse(int status, T data) {
        setStatus(status);
        setData(data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static final WebResponse<String> SUCCESS_WEB_RESPONSE = new WebResponse<String>(WebResponse.WEB_STATUS_OK,
            WebResponse.SUCCESS_DATA);
    public static final WebResponse<String> ERROR_WEB_RESPONSE = new WebResponse<String>(
            WebResponse.WEB_STATUS_SYSTEM_ERROR, WebResponse.ERROR_DATA);

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

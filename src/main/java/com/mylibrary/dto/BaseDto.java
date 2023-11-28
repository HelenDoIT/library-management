package com.mylibrary.dto;

import java.io.Serializable;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -4756921222650951446L;
    // -1 user not exit, -2: error, 0 success
    private int code;
    private String errorMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

package com.yupi.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 9165783891832090776L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}

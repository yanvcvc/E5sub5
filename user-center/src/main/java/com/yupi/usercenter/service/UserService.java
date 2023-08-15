package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author jiang
 * @createDate 2023-08-10 08:57:02
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录态键
     */
    String USER_LOGINI_STATE = "userLoginState";

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 返回用户脱敏后的信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);
}

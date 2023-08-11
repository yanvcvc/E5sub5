package com.yupi.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UserCenterApplicationTests {


    @Test

    public void md5Test() {
        String rawPassword = "123456";
        String salt = "kjfcsddkjfdsajfdiusf8743urf";
        String encodedPassword = DigestUtils.md5DigestAsHex(
                (salt + salt + rawPassword + salt + salt).getBytes());
        System.out.println("原密码：" + rawPassword);
        System.out.println("加密后的密码：" + encodedPassword);
    }


    @Test
    void contextLoads() {
    }

}

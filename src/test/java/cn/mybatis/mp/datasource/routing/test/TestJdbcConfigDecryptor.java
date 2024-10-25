package cn.mybatis.mp.datasource.routing.test;

import cn.mybatis.mp.datasource.routing.JdbcConfigDecryptor;

public class TestJdbcConfigDecryptor implements JdbcConfigDecryptor {
    @Override
    public String jdbcUrlDecrypt(String encryptedJdbcUrl) {
        //此处进行解密
        return encryptedJdbcUrl;
    }

    @Override
    public String usernameDecrypt(String encryptedUsername) {
        //此处进行解密
        return encryptedUsername;
    }

    @Override
    public String passwordDecrypt(String encryptedPassword) {
        //此处进行解密
        return encryptedPassword;
    }
}

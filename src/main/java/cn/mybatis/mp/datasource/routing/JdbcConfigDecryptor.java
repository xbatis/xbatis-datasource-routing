package cn.mybatis.mp.datasource.routing;

/**
 * jdbc 配置解密器
 */
public interface JdbcConfigDecryptor {

    /**
     * 用戶名解密
     *
     * @param encryptedJdbcUrl 加密後的jdbcUrl
     * @return 明文jdbcUrl
     */
    String jdbcUrlDecrypt(String encryptedJdbcUrl);

    /**
     * 用戶名解密
     *
     * @param encryptedUsername 加密後的用戶名
     * @return 明文用户名
     */
    String usernameDecrypt(String encryptedUsername);


    /**
     * 用戶名解密
     *
     * @param encryptedPassword 加密後的密碼
     * @return 明文密码
     */
    String passwordDecrypt(String encryptedPassword);
}

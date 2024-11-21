/*
 *  Copyright (c) 2024-2024, Ai东 (abc-127@live.cn).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License").
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

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

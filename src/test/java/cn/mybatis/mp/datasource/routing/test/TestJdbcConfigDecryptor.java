/*
 *  Copyright (c) 2024-2025, Ai东 (abc-127@live.cn).
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

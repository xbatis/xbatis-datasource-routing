/*
 *  Copyright (c) 2024-2026, Ai东 (abc-127@live.cn) xbatis.
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

package cn.xbatis.datasource.routing;

import org.springframework.aop.ClassFilter;
import org.springframework.util.AntPathMatcher;

public class AntClassFilter implements ClassFilter {

    private static final AntPathMatcher MATCHER = new AntPathMatcher(".");

    private final String pattern;

    private final boolean isPattern;

    public AntClassFilter(String pattern) {
        this.pattern = pattern;
        this.isPattern = MATCHER.isPattern(pattern);
    }

    public static void main(String[] args) {
        System.out.println(MATCHER.match("cn.xbatis.datasource.**.dao", "cn.xbatis.datasource.routing.test.dao"));
    }

    @Override
    public boolean matches(Class<?> clazz) {
        Package p = clazz.getPackage();
        if (p == null) {
            return false;
        }
        String packageName = p.getName();
        if (!isPattern) {
            return packageName.startsWith(pattern);
        }
        return MATCHER.match(pattern, packageName);
    }
}

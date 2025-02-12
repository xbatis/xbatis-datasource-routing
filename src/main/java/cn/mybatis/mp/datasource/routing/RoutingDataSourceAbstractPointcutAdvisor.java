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

package cn.mybatis.mp.datasource.routing;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RoutingDataSourceAbstractPointcutAdvisor extends AbstractPointcutAdvisor {

    private final Pointcut pointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            if (Proxy.isProxyClass(targetClass)) {
                return false;
            }
            if (methodMatch(method)) {
                return true;
            }
            Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);

            if (methodMatch(specificMethod)) {
                return true;
            }
            if (method.getDeclaringClass().isAnnotationPresent(DS.class)) {
                return true;
            }
            return targetClass.isAnnotationPresent(DS.class);
        }
    };
    private final RoutingDataSourceSpringInterceptor interceptor;

    public RoutingDataSourceAbstractPointcutAdvisor(RoutingDataSourceSpringInterceptor routingDataSourceSpringInterceptor) {
        this.interceptor = routingDataSourceSpringInterceptor;
    }

    private boolean methodMatch(Method method) {
        return AnnotatedElementUtils.hasAnnotation(method, DS.class);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.interceptor;
    }
}

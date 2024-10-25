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

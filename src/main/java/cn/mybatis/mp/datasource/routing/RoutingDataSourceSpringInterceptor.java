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


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 基于spring aopalliance的方法拦截器
 */
public class RoutingDataSourceSpringInterceptor implements MethodInterceptor {

    /**
     * 参数名解析器，用于获取参数名
     */
    private static final ParameterNameDiscoverer localVariableTable = new DefaultParameterNameDiscoverer();
    private static final ExpressionParser parser = new SpelExpressionParser();
    private final Logger logger = LoggerFactory.getLogger(RoutingDataSourceSpringInterceptor.class);

    @Autowired(required = false)
    private RoutingDataSourceSwitchContext routingDataSourceSwitchContext;

    public static void main(String[] args) {
        String key = "{'slave-'+(#i % 2)}";
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("i", 1);
        String str = parser.parseExpression(key.substring(1, key.length() - 1)).getValue(context, String.class);
        System.out.println(str);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String dSKey = null;
        String currentDSKey = DataSourceHolder.getCurrent();
        if (method.isAnnotationPresent(DS.class)) {
            DS ds = method.getAnnotation(DS.class);
            dSKey = ds.value();
        } else if (invocation.getThis().getClass().isAnnotationPresent(DS.class)) {
            DS ds = invocation.getThis().getClass().getAnnotation(DS.class);
            dSKey = ds.value();
        } else if (method.getDeclaringClass().isAnnotationPresent(DS.class)) {
            DS ds = method.getDeclaringClass().getAnnotation(DS.class);
            dSKey = ds.value();
        }

        if (dSKey != null) {
            dSKey = parseDSKey(dSKey, method, invocation.getArguments());
        }

        if (dSKey == null || dSKey.equals(currentDSKey)) {
            //和当前一样 没必须切换
            return invocation.proceed();
        }

        logger.debug(">>>>>>>>> {} 数据源切至：{}", method, dSKey);
        DataSourceHolder.add(dSKey);
        try {
            return invocation.proceed();
        } finally {
            DataSourceHolder.remove();
            dSKey = DataSourceHolder.getCurrent();
            if (dSKey != null) {
                logger.debug("<<<<<<<<< {} 数据源切回至：{}", method, dSKey);
            }
        }
    }

    private String parseDSKey(String key, Method method, Object[] args) {
        if (Objects.isNull(key) || !StringUtils.hasText(key)) {
            return null;
        }
        key = key.trim();
        if (key.startsWith("{") && key.endsWith("}")) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            //填充spEL的上下文对象
            if (Objects.nonNull(args) && args.length > 0) {
                String[] parameterNames = localVariableTable.getParameterNames(method);
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
            }

            if (Objects.nonNull(routingDataSourceSwitchContext)) {
                context.setVariable("context", routingDataSourceSwitchContext.getVariables());
            }
            return parser.parseExpression(key).getValue(context, String.class);
        }
        return key;
    }
}

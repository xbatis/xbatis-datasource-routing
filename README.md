# English wiki: [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/xbatis/xbatis-datasource-routing)


# 动态数据源
```
该模块从xbatis独立而出，已经历多次迭代，可放心使用！！！
``` 
## 1. springboot3 maven引入

```
<dependency>
    <groupId>cn.xbatis</groupId>
    <artifactId>xbatis-datasource-routing</artifactId>
    <version>1.0.8</version>
</dependency>
```

## 2. springboot4 maven引入

```
<dependency>
    <groupId>cn.xbatis</groupId>
    <artifactId>xbatis-datasource-routing</artifactId>
    <version>1.0.8-spring-boot4</version>
</dependency>
```

> 记得 加上 @EnableAspectJAutoProxy 启用aop

## 2.配置数据源

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'   
```

> master 可使用自己喜欢的

## 3.多个数据源

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'  
      slave:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'     
```

> master、slave 可使用自己喜欢的，这里表示 一主一从的配置

## 4.数据源分组

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'  
      slave-1:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'   
      slave-2:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql'     
```

> 相当于master、slave；slave下有 slave-1，slave-2；框架自动随机slave

## 5.如何配置数据源其他属性

> 数据源其他属性 和 spring datasource下配置一样的

### 5.1 hikari 数据源

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql' 
        hikari:
          minimum-idle: 1
          maximum-pool-size: 10  
          ... 其他配置
```

> 更多配置 查看 hikari 官方文档

### 5.2 druid 数据源

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql' 
        druid:
          initial-size: 1
          max-active: 10
          ... 其他配置
```

> 更多配置 查看 druid 官方文档

### 5.3 其他数据源

```
spring:
  ds:
    primary: master
    routing:
      master:
        username: root
        password: 123456
        url: jdbc:h2:mem:druidDB;INIT=RUNSCRIPT FROM 'classpath:h2-schema.sql' 
        other:
          initial-size: 1
          max-active: 10
          ... 其他配置
```

> other 配置下是无提示的，具体有那么配置，需要看数据库支持那些配置;
> 可以 spring datasource 配置后，然后 拷贝到 other节点下。例如

```
spring:
  datasource:
    dbcp2:
      connection-init-sqls: select 1
```

拷贝后，变成：

```
spring:
  ds:
    routing:
        other:
          connection-init-sqls: select 1
```

## 6.如何在项目里切换

> 使用注解 @DS(数据源的名称)，例如：@DS("master)
>
> 注解可以用在方法 方法，类中，方法的优先级比类高

## 7.使用注意事项

### 7.1 事务下，无法切换问题

```
class A{
    @Resource
    private B b;

    @DS("master")
    @Transactional
    void a(){
        b.b();
    }
}

class B{
    @DS("slave")
    @Transactional
    void b();
}

```

> 执行 a()方法，b方法 用的还是 master数据源；
>
> 解决方案：b方法事务改成 @Transactional(propagation = Propagation.NOT_SUPPORTED) 或 @Transactional(propagation =
> Propagation.REQUIRES_NEW)

### 7.2 同类下，无法切换问题

```
class A{

    @DS("master")
    @Transactional
    void a(){
        b();
    }
    
    @DS("slave")
    @Transactional
    void b();
}

```

> 由于spring 代理方法时，对于同类下方法内部调用走的是类本身的方法，无法被spring代理，所以：
>
> 1：创建一个新类，然后b方法放到里边
>
> 2：使用 A a = (A)AopContext.currentProxy(); 同时：@EnableAspectJAutoProxy(exposeProxy = true) 如下：

```
class A{

    @DS("master")
    @Transactional
    void a(){
        A a = (A)AopContext.currentProxy();
        a.b();
    }
    
    @DS("slave")
    @Transactional
    void b();
}
```

## 8.关于事务一致性

> 其他动态数据源框架在不接入分布式事务，也一样不支持事务一致；
>
> 无法保证事务一致性，如果需要一致性，可需要接入 其他 例如 seta 分布式事务
>

## 9.推荐使用方式

### 9.1 一主一从 或 一主多从

### 9.2 多个不同数据源事务的情况，不要产生交叉写事务，读可以交叉使用

## 10.开启seata

```
spring:
  ds:
    aop:
      enabled: true
      order: 1
    seata: true
    seata-mode: at  
```

## 11.jdbc配置安全加密

### 11.1 创建加密器：

```java
public class TestJdbcConfigDecryptor implements JdbcConfigDecryptor {
    @Override
    public String jdbcUrlDecrypt(String encryptedJdbcUrl) {
        //此处进行解密
        return encryptedJdbcUrl;
    }

    @Override
    public String usernameDecrypt(String encryptedUsername) {
        //此处进行解密
        return "root";
    }

    @Override
    public String passwordDecrypt(String encryptedPassword) {
        //此处进行解密
        return encryptedPassword;
    }
}
```

```java
@Configuration(proxyBeanMethods = false)
public class Config {

    /**
     * 创建JdbcConfig解密 Bean
     *
     * @return
     */
    @Bean
    JdbcConfigDecryptor jdbcConfigDecryptor() {
        return new TestJdbcConfigDecryptor();
    }
}
```

### 11.2 开启加密：

```
spring:
  ds:
    jdbc-config-decrypt: true
    primary: master
    routing:
      master:
        name: master
        type: com.zaxxer.hikari.HikariDataSource
        url: 加密后的地址
        username: 加密后的username
        passowrd: 加密后的password
        hikari:
          minimum-idle: 1
          maximum-pool-size: 10
```

## 12：SQL分析与打印
### 12.1 yaml 启用p6spy
```yaml
spring:
  ds:
    p6spy: false
```
### 12.2 maven引入p6spy依赖
```xml
<dependency>
    <groupId>p6spy</groupId>
    <artifactId>p6spy</artifactId>
    <version>3.9.1</version>
</dependency>
```
### 12.3 把spy.properties放到resources下
> spy.properties 是 p6spy配置，参考[spy.properties](https://p6spy.readthedocs.io/en/latest/configandusage.html#common-property-file-settings)


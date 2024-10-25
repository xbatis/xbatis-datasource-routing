package cn.mybatis.mp.datasource.routing.dataSourceConfig;

import java.util.List;
import java.util.Properties;


public class DruidConfig {

    private boolean defaultAutoCommit = true;
    private Boolean defaultReadOnly;
    private Integer defaultTransactionIsolation;
    private String defaultCatalog;
    private String name;
    private String username;
    private String password;
    private String jdbcUrl;
    private String driverClass;

    private Properties connectProperties = new Properties();

    private int initialSize = 0;
    private int maxActive = 8;
    private int minIdle = 0;
    private int maxIdle = 8;
    private long maxWait = -1L;
    private int notFullTimeoutRetryCount;
    private String validationQuery;
    private int validationQueryTimeout;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private boolean poolPreparedStatements;
    private boolean sharePreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;

    private boolean initExceptionThrow;


    private int connectTimeout;
    private int socketTimeout;
    private String connectTimeoutStr;
    private String socketTimeoutSr;
    private int queryTimeout;
    private int transactionQueryTimeout;
    private long createTimespan;
    private int maxWaitThreadCount;
    private boolean accessToUnderlyingConnectionAllowed;
    private long timeBetweenEvictionRunsMillis;
    private int numTestsPerEvictionRun;
    private long minEvictableIdleTimeMillis;
    private long maxEvictableIdleTimeMillis;
    private long keepAliveBetweenTimeMillis;
    private long phyTimeoutMillis;
    private long phyMaxUseCount;
    private boolean removeAbandoned;
    private long removeAbandonedTimeoutMillis;
    private boolean logAbandoned;
    private int maxOpenPreparedStatements;
    private List<String> connectionInitSqls;
    private String dbTypeName;
    private long timeBetweenConnectErrorMillis;

    private boolean usePingMethod;


    private int connectionErrorRetryAttempts;
    private boolean breakAfterAcquireFailure;
    private long transactionThresholdMillis;


    private long errorCount;
    private long dupCloseCount;
    private long startTransactionCount;
    private long commitCount;
    private long rollbackCount;
    private long cachedPreparedStatementHitCount;
    private long preparedStatementCount;
    private long closedPreparedStatementCount;
    private long cachedPreparedStatementCount;
    private long cachedPreparedStatementDeleteCount;
    private long cachedPreparedStatementMissCount;

    private boolean dupCloseLogEnable;

    private long executeCount;
    private long executeQueryCount;
    private long executeUpdateCount;
    private long executeBatchCount;


    private long lastErrorTimeMillis;

    private long lastCreateErrorTimeMillis;
    private long lastCreateStartTimeMillis;
    private boolean isOracle;
    private boolean isMySql;
    private boolean useOracleImplicitCache;

    private int createErrorCount;
    private int creatingCount;
    private int directCreateCount;
    private long createCount;
    private long destroyCount;
    private long createStartNanos;
    private Boolean useUnfairLock;
    private boolean useLocalSessionState;
    private boolean keepConnectionUnderlyingTransactionIsolation;
    private long timeBetweenLogStatsMillis;

    private boolean asyncCloseConnectionEnable;
    private int maxCreateTaskCount;
    private boolean failFast;
    private int failContinuous;
    private long failContinuousTimeMillis;

    private boolean netTimeoutError;
    private boolean initVariants;
    private boolean initGlobalVariants;
    private boolean onFatalError;
    private int onFatalErrorMaxActive;
    private int fatalErrorCount;
    private int fatalErrorCountLastShrink;
    private long lastFatalErrorTimeMillis;

    public boolean isDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public Boolean getDefaultReadOnly() {
        return defaultReadOnly;
    }

    public void setDefaultReadOnly(Boolean defaultReadOnly) {
        this.defaultReadOnly = defaultReadOnly;
    }

    public Integer getDefaultTransactionIsolation() {
        return defaultTransactionIsolation;
    }

    public void setDefaultTransactionIsolation(Integer defaultTransactionIsolation) {
        this.defaultTransactionIsolation = defaultTransactionIsolation;
    }

    public String getDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(String defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public Properties getConnectProperties() {
        return connectProperties;
    }

    public void setConnectProperties(Properties connectProperties) {
        this.connectProperties = connectProperties;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public int getNotFullTimeoutRetryCount() {
        return notFullTimeoutRetryCount;
    }

    public void setNotFullTimeoutRetryCount(int notFullTimeoutRetryCount) {
        this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(int validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public boolean isSharePreparedStatements() {
        return sharePreparedStatements;
    }

    public void setSharePreparedStatements(boolean sharePreparedStatements) {
        this.sharePreparedStatements = sharePreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public boolean isInitExceptionThrow() {
        return initExceptionThrow;
    }

    public void setInitExceptionThrow(boolean initExceptionThrow) {
        this.initExceptionThrow = initExceptionThrow;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getConnectTimeoutStr() {
        return connectTimeoutStr;
    }

    public void setConnectTimeoutStr(String connectTimeoutStr) {
        this.connectTimeoutStr = connectTimeoutStr;
    }

    public String getSocketTimeoutSr() {
        return socketTimeoutSr;
    }

    public void setSocketTimeoutSr(String socketTimeoutSr) {
        this.socketTimeoutSr = socketTimeoutSr;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public int getTransactionQueryTimeout() {
        return transactionQueryTimeout;
    }

    public void setTransactionQueryTimeout(int transactionQueryTimeout) {
        this.transactionQueryTimeout = transactionQueryTimeout;
    }

    public long getCreateTimespan() {
        return createTimespan;
    }

    public void setCreateTimespan(long createTimespan) {
        this.createTimespan = createTimespan;
    }

    public int getMaxWaitThreadCount() {
        return maxWaitThreadCount;
    }

    public void setMaxWaitThreadCount(int maxWaitThreadCount) {
        this.maxWaitThreadCount = maxWaitThreadCount;
    }

    public boolean isAccessToUnderlyingConnectionAllowed() {
        return accessToUnderlyingConnectionAllowed;
    }

    public void setAccessToUnderlyingConnectionAllowed(boolean accessToUnderlyingConnectionAllowed) {
        this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(long maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public long getKeepAliveBetweenTimeMillis() {
        return keepAliveBetweenTimeMillis;
    }

    public void setKeepAliveBetweenTimeMillis(long keepAliveBetweenTimeMillis) {
        this.keepAliveBetweenTimeMillis = keepAliveBetweenTimeMillis;
    }

    public long getPhyTimeoutMillis() {
        return phyTimeoutMillis;
    }

    public void setPhyTimeoutMillis(long phyTimeoutMillis) {
        this.phyTimeoutMillis = phyTimeoutMillis;
    }

    public long getPhyMaxUseCount() {
        return phyMaxUseCount;
    }

    public void setPhyMaxUseCount(long phyMaxUseCount) {
        this.phyMaxUseCount = phyMaxUseCount;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public long getRemoveAbandonedTimeoutMillis() {
        return removeAbandonedTimeoutMillis;
    }

    public void setRemoveAbandonedTimeoutMillis(long removeAbandonedTimeoutMillis) {
        this.removeAbandonedTimeoutMillis = removeAbandonedTimeoutMillis;
    }

    public boolean isLogAbandoned() {
        return logAbandoned;
    }

    public void setLogAbandoned(boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public int getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
        this.maxOpenPreparedStatements = maxOpenPreparedStatements;
    }

    public List<String> getConnectionInitSqls() {
        return connectionInitSqls;
    }

    public void setConnectionInitSqls(List<String> connectionInitSqls) {
        this.connectionInitSqls = connectionInitSqls;
    }

    public String getDbTypeName() {
        return dbTypeName;
    }

    public void setDbTypeName(String dbTypeName) {
        this.dbTypeName = dbTypeName;
    }

    public long getTimeBetweenConnectErrorMillis() {
        return timeBetweenConnectErrorMillis;
    }

    public void setTimeBetweenConnectErrorMillis(long timeBetweenConnectErrorMillis) {
        this.timeBetweenConnectErrorMillis = timeBetweenConnectErrorMillis;
    }

    public boolean isUsePingMethod() {
        return usePingMethod;
    }

    public void setUsePingMethod(boolean usePingMethod) {
        this.usePingMethod = usePingMethod;
    }

    public int getConnectionErrorRetryAttempts() {
        return connectionErrorRetryAttempts;
    }

    public void setConnectionErrorRetryAttempts(int connectionErrorRetryAttempts) {
        this.connectionErrorRetryAttempts = connectionErrorRetryAttempts;
    }

    public boolean isBreakAfterAcquireFailure() {
        return breakAfterAcquireFailure;
    }

    public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
        this.breakAfterAcquireFailure = breakAfterAcquireFailure;
    }

    public long getTransactionThresholdMillis() {
        return transactionThresholdMillis;
    }

    public void setTransactionThresholdMillis(long transactionThresholdMillis) {
        this.transactionThresholdMillis = transactionThresholdMillis;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getDupCloseCount() {
        return dupCloseCount;
    }

    public void setDupCloseCount(long dupCloseCount) {
        this.dupCloseCount = dupCloseCount;
    }

    public long getStartTransactionCount() {
        return startTransactionCount;
    }

    public void setStartTransactionCount(long startTransactionCount) {
        this.startTransactionCount = startTransactionCount;
    }

    public long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(long commitCount) {
        this.commitCount = commitCount;
    }

    public long getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(long rollbackCount) {
        this.rollbackCount = rollbackCount;
    }

    public long getCachedPreparedStatementHitCount() {
        return cachedPreparedStatementHitCount;
    }

    public void setCachedPreparedStatementHitCount(long cachedPreparedStatementHitCount) {
        this.cachedPreparedStatementHitCount = cachedPreparedStatementHitCount;
    }

    public long getPreparedStatementCount() {
        return preparedStatementCount;
    }

    public void setPreparedStatementCount(long preparedStatementCount) {
        this.preparedStatementCount = preparedStatementCount;
    }

    public long getClosedPreparedStatementCount() {
        return closedPreparedStatementCount;
    }

    public void setClosedPreparedStatementCount(long closedPreparedStatementCount) {
        this.closedPreparedStatementCount = closedPreparedStatementCount;
    }

    public long getCachedPreparedStatementCount() {
        return cachedPreparedStatementCount;
    }

    public void setCachedPreparedStatementCount(long cachedPreparedStatementCount) {
        this.cachedPreparedStatementCount = cachedPreparedStatementCount;
    }

    public long getCachedPreparedStatementDeleteCount() {
        return cachedPreparedStatementDeleteCount;
    }

    public void setCachedPreparedStatementDeleteCount(long cachedPreparedStatementDeleteCount) {
        this.cachedPreparedStatementDeleteCount = cachedPreparedStatementDeleteCount;
    }

    public long getCachedPreparedStatementMissCount() {
        return cachedPreparedStatementMissCount;
    }

    public void setCachedPreparedStatementMissCount(long cachedPreparedStatementMissCount) {
        this.cachedPreparedStatementMissCount = cachedPreparedStatementMissCount;
    }

    public boolean isDupCloseLogEnable() {
        return dupCloseLogEnable;
    }

    public void setDupCloseLogEnable(boolean dupCloseLogEnable) {
        this.dupCloseLogEnable = dupCloseLogEnable;
    }

    public long getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(long executeCount) {
        this.executeCount = executeCount;
    }

    public long getExecuteQueryCount() {
        return executeQueryCount;
    }

    public void setExecuteQueryCount(long executeQueryCount) {
        this.executeQueryCount = executeQueryCount;
    }

    public long getExecuteUpdateCount() {
        return executeUpdateCount;
    }

    public void setExecuteUpdateCount(long executeUpdateCount) {
        this.executeUpdateCount = executeUpdateCount;
    }

    public long getExecuteBatchCount() {
        return executeBatchCount;
    }

    public void setExecuteBatchCount(long executeBatchCount) {
        this.executeBatchCount = executeBatchCount;
    }

    public long getLastErrorTimeMillis() {
        return lastErrorTimeMillis;
    }

    public void setLastErrorTimeMillis(long lastErrorTimeMillis) {
        this.lastErrorTimeMillis = lastErrorTimeMillis;
    }

    public long getLastCreateErrorTimeMillis() {
        return lastCreateErrorTimeMillis;
    }

    public void setLastCreateErrorTimeMillis(long lastCreateErrorTimeMillis) {
        this.lastCreateErrorTimeMillis = lastCreateErrorTimeMillis;
    }

    public long getLastCreateStartTimeMillis() {
        return lastCreateStartTimeMillis;
    }

    public void setLastCreateStartTimeMillis(long lastCreateStartTimeMillis) {
        this.lastCreateStartTimeMillis = lastCreateStartTimeMillis;
    }

    public boolean isOracle() {
        return isOracle;
    }

    public void setOracle(boolean oracle) {
        isOracle = oracle;
    }

    public boolean isMySql() {
        return isMySql;
    }

    public void setMySql(boolean mySql) {
        isMySql = mySql;
    }

    public boolean isUseOracleImplicitCache() {
        return useOracleImplicitCache;
    }

    public void setUseOracleImplicitCache(boolean useOracleImplicitCache) {
        this.useOracleImplicitCache = useOracleImplicitCache;
    }

    public int getCreateErrorCount() {
        return createErrorCount;
    }

    public void setCreateErrorCount(int createErrorCount) {
        this.createErrorCount = createErrorCount;
    }

    public int getCreatingCount() {
        return creatingCount;
    }

    public void setCreatingCount(int creatingCount) {
        this.creatingCount = creatingCount;
    }

    public int getDirectCreateCount() {
        return directCreateCount;
    }

    public void setDirectCreateCount(int directCreateCount) {
        this.directCreateCount = directCreateCount;
    }

    public long getCreateCount() {
        return createCount;
    }

    public void setCreateCount(long createCount) {
        this.createCount = createCount;
    }

    public long getDestroyCount() {
        return destroyCount;
    }

    public void setDestroyCount(long destroyCount) {
        this.destroyCount = destroyCount;
    }

    public long getCreateStartNanos() {
        return createStartNanos;
    }

    public void setCreateStartNanos(long createStartNanos) {
        this.createStartNanos = createStartNanos;
    }

    public Boolean getUseUnfairLock() {
        return useUnfairLock;
    }

    public void setUseUnfairLock(Boolean useUnfairLock) {
        this.useUnfairLock = useUnfairLock;
    }

    public boolean isUseLocalSessionState() {
        return useLocalSessionState;
    }

    public void setUseLocalSessionState(boolean useLocalSessionState) {
        this.useLocalSessionState = useLocalSessionState;
    }

    public boolean isKeepConnectionUnderlyingTransactionIsolation() {
        return keepConnectionUnderlyingTransactionIsolation;
    }

    public void setKeepConnectionUnderlyingTransactionIsolation(boolean keepConnectionUnderlyingTransactionIsolation) {
        this.keepConnectionUnderlyingTransactionIsolation = keepConnectionUnderlyingTransactionIsolation;
    }

    public long getTimeBetweenLogStatsMillis() {
        return timeBetweenLogStatsMillis;
    }

    public void setTimeBetweenLogStatsMillis(long timeBetweenLogStatsMillis) {
        this.timeBetweenLogStatsMillis = timeBetweenLogStatsMillis;
    }

    public boolean isAsyncCloseConnectionEnable() {
        return asyncCloseConnectionEnable;
    }

    public void setAsyncCloseConnectionEnable(boolean asyncCloseConnectionEnable) {
        this.asyncCloseConnectionEnable = asyncCloseConnectionEnable;
    }

    public int getMaxCreateTaskCount() {
        return maxCreateTaskCount;
    }

    public void setMaxCreateTaskCount(int maxCreateTaskCount) {
        this.maxCreateTaskCount = maxCreateTaskCount;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public int getFailContinuous() {
        return failContinuous;
    }

    public void setFailContinuous(int failContinuous) {
        this.failContinuous = failContinuous;
    }

    public long getFailContinuousTimeMillis() {
        return failContinuousTimeMillis;
    }

    public void setFailContinuousTimeMillis(long failContinuousTimeMillis) {
        this.failContinuousTimeMillis = failContinuousTimeMillis;
    }

    public boolean isNetTimeoutError() {
        return netTimeoutError;
    }

    public void setNetTimeoutError(boolean netTimeoutError) {
        this.netTimeoutError = netTimeoutError;
    }

    public boolean isInitVariants() {
        return initVariants;
    }

    public void setInitVariants(boolean initVariants) {
        this.initVariants = initVariants;
    }

    public boolean isInitGlobalVariants() {
        return initGlobalVariants;
    }

    public void setInitGlobalVariants(boolean initGlobalVariants) {
        this.initGlobalVariants = initGlobalVariants;
    }

    public boolean isOnFatalError() {
        return onFatalError;
    }

    public void setOnFatalError(boolean onFatalError) {
        this.onFatalError = onFatalError;
    }

    public int getOnFatalErrorMaxActive() {
        return onFatalErrorMaxActive;
    }

    public void setOnFatalErrorMaxActive(int onFatalErrorMaxActive) {
        this.onFatalErrorMaxActive = onFatalErrorMaxActive;
    }

    public int getFatalErrorCount() {
        return fatalErrorCount;
    }

    public void setFatalErrorCount(int fatalErrorCount) {
        this.fatalErrorCount = fatalErrorCount;
    }

    public int getFatalErrorCountLastShrink() {
        return fatalErrorCountLastShrink;
    }

    public void setFatalErrorCountLastShrink(int fatalErrorCountLastShrink) {
        this.fatalErrorCountLastShrink = fatalErrorCountLastShrink;
    }

    public long getLastFatalErrorTimeMillis() {
        return lastFatalErrorTimeMillis;
    }

    public void setLastFatalErrorTimeMillis(long lastFatalErrorTimeMillis) {
        this.lastFatalErrorTimeMillis = lastFatalErrorTimeMillis;
    }
}

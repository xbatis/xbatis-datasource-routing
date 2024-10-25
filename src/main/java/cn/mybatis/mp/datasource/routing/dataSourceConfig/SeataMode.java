package cn.mybatis.mp.datasource.routing.dataSourceConfig;

/**
 * Seata 模式
 */
public enum SeataMode {
    /**
     * XA 模式
     */
    XA,
    /**
     * AT 模式
     */
    AT,
    /**
     * TCC 模式
     */
    TCC,
    /**
     * Saga 模式
     */
    SAGA,
}

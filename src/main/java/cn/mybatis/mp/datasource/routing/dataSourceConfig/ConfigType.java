package cn.mybatis.mp.datasource.routing.dataSourceConfig;

public enum ConfigType {
    //17725028565

    HIKARI("hikari"),
    DRUID("druid"),
    OTHER("other"),
    ;

    private final String key;

    ConfigType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

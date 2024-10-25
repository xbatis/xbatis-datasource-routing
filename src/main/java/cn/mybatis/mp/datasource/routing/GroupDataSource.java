package cn.mybatis.mp.datasource.routing;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GroupDataSource implements DataSource {

    private final List<DataSource> delegateList;

    private final Random random = new SecureRandom();

    public GroupDataSource(List<DataSource> delegateList) {
        this.delegateList = delegateList;
    }

    public DataSource loadBalance() {
        return delegateList.get(random.nextInt(delegateList.size()));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return loadBalance().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return loadBalance().getConnection(username, password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return loadBalance().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return loadBalance().isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return loadBalance().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) {
        delegateList.forEach(item -> {
            try {
                item.setLogWriter(out);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return loadBalance().getLoginTimeout();
    }

    @Override
    public void setLoginTimeout(int seconds) {
        delegateList.forEach(item -> {
            try {
                item.setLoginTimeout(seconds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return loadBalance().getParentLogger();
    }

    public List<DataSource> getDelegateList() {
        return delegateList;
    }
}

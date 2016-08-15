package zany.fwk.config.db.oracle.odbc.primary;

import org.springframework.boot.context.properties.ConfigurationProperties;

import zany.fwk.config.db.oracle.odbc.base.DatabaseProperties;

@ConfigurationProperties(prefix = PrimaryDatabaseProperties.PREFIX)
public class PrimaryDatabaseProperties implements DatabaseProperties {

    public static final String PREFIX = "datasource.primary";

    private String             driverClassName;

    private String             url;

    private String             userName;

    private String             password;

    private int                initialSize;

    private int                maxActive;

    private int                maxIdle;

    private int                minIdle;

    private int                maxWait;

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    @Override
    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LocalDatabaseProperties[" + this.driverClassName + "]";
    }
}

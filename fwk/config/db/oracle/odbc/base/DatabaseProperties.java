package zany.fwk.config.db.oracle.odbc.base;

public interface DatabaseProperties {
	public String getDriverClassName();    // ClassName
	public String getUrl();                // URL
	public String getUserName();           // ID
	public String getPassword();           // PASSWORD
	public int getInitialSize();           // init size
	public int getMaxActive();             // max active
	public int getMaxIdle();               // max idle
	public int getMinIdle();               // min idle
	public int getMaxWait();               // max wait
	
}
package com.cn.hwl.modle;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DbDao {
    private Connection conn;

    private String driver;

    private String url;

    private String username;

    private String password;

    public DbDao() {

    }

    public DbDao(String driver, String url, String username, String password) {
        super();
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConn() throws Exception {
        if (conn == null) {
            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
        }
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    /**
     * @Description
     * @author Hero
     * @param sql
     * @param agrs
     * @return
     * @throws Exception
     */
    public ResultSet queryUser(String sql, Object... agrs) throws Exception {
        PreparedStatement pstmt = (PreparedStatement) getConn().prepareStatement(sql);
        for (int i = 0; i < agrs.length; i++) {
            pstmt.setObject(i + 1, agrs[i]);
        }
        return pstmt.executeQuery();

    }

}

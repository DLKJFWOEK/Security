package com.yaloostore.sqlinjection.client.persistence.util;

import java.sql.*;

public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 접속 객체 반환
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/fisasecurity?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "root");

    }

    // select 전담 close 로직
    public static void close(Connection con, Statement stmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
                rs = null;

            }

            if (stmt != null) {
                stmt.close();
                stmt = null;

            }
            if (con != null) {
                con.close();
                con = null;

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // DML(insert, update, delete 즉, ResultSet과 무관한 자원 반환
    public static void close(Connection con, Statement stmt) {
        try {

            if (stmt != null) {
                stmt.close();
                stmt = null;

            }
            if (con != null) {
                con.close();
                con = null;

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
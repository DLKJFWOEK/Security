package com.yaloostore.sqlinjection.client.persistence;


import com.yaloostore.sqlinjection.client.domain.dto.ClientDTO;
import com.yaloostore.sqlinjection.client.persistence.util.DBUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class ClientDAO {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    /*
    * 해당 클래스에서는 sql injection이 가능하도록 진행하고 있습니다.
    * 이 작업은 statement를 이용해서 진행하고 있고 sql injection 방어를 위해서는 prepareStatement 사용,
    * JPA, spring security 등을 사용해 방어를 할수 있습니다.
    * */
    public ClientDTO findClientByLoginId(String loginId, String pwd){

        ClientDTO client = null;

        try {

            con = DBUtil.getConnection();

            String query = "SELECT * FROM client WHERE login_id = '" + loginId + "' AND password = '" + pwd +"'";

            stmt = con.createStatement();


            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()){
                client = new ClientDTO();

                client.setClientId(resultSet.getLong("client_id"));
                client.setLoginId(resultSet.getString("login_id"));
                client.setPwd(resultSet.getString("password"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            DBUtil.close(con, stmt);
        }

        return client;
    }

    /*
    * sql injection 방어를 위한 preparedStatement를 사용한 메서드입니다.
    * */
    public ClientDTO findClientByLoginId_pre(String loginId, String pwd) {

        ClientDTO client = null;

        try {
            con = DBUtil.getConnection();

            // SQL Injection 방어를 위해 PreparedStatement 사용
            String query = "SELECT * FROM client WHERE login_id = ? AND password = ?";
            pstmt = con.prepareStatement(query);

            // ? 위치에 값 매핑
            pstmt.setString(1, loginId);
            pstmt.setString(2, pwd);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // 결과가 존재하는 경우, ClientDTO 객체에 결과를 매핑
                client = new ClientDTO();
                client.setClientId(resultSet.getLong("client_id"));
                client.setLoginId(resultSet.getString("login_id"));
                client.setPwd(resultSet.getString("password"));
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            DBUtil.close(con, stmt);
        }

        return client;
    }



}

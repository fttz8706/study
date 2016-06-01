package com.yjm.study.jdbc;



import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by yangjinming on 16/5/30.
 */
public class JDBC {

    @Test
    public void fun(){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "123456");
            for(int i = 0; i < 1000000; i++) {
                String userName = "user" + i;
                String password = i + "";
                int userId = i;
                statement = connection.prepareStatement
                        ("insert into user(userId,password,userName) values(" + userId +",'" + password+ "','" + userName + "')");
                statement.execute();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){

                }
            }
            if(statement != null){
                try{
                    statement.close();
                }catch (Exception e){

                }
            }
        }
    }
}

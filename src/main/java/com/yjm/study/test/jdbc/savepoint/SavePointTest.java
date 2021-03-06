package com.yjm.study.test.jdbc.savepoint;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

import static com.yjm.study.common.Constant.*;

/**
 * CREATE TABLE `user` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
 *   `age` int(11) DEFAULT NULL COMMENT '年龄',
 *   `gender` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
 *   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
 *
 * CREATE TABLE `log` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `type` int(11) DEFAULT NULL,
 *   `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
 *   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
 *
 * 插入一个保存点后，当发生异常，回滚时，事务会回滚到指定rollback的保存点（这时会把所有设置的保存点清空）
 * 当再次commit时，会把保存点之前的内容commit
 *
 * </p>
 *
 * @author test
 * @date 2019-06-22
 */
public class SavePointTest {

    @Test
    public void testSafePoint() {
        Savepoint savepoint = null;
        Savepoint savepoint2 = null;
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement logStatement = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            connection.setAutoCommit(false);

            String insertUser = "insert into user(name, age, gender) values('zhangsan', 10, 'm');";
            userStatement = connection.prepareStatement(insertUser);
            userStatement.execute();

            // 在此处插入一个保存点"s1"
            savepoint = connection.setSavepoint("s1");

            String insertLog = "insert into log(type, content) values(1, 'insert data');";
            logStatement = connection.prepareStatement(insertLog);
            logStatement.execute();

            // 再次设置一个保存点"s2"
            savepoint2 = connection.setSavepoint("s2");

            // test rollback to savepoint "s1"，lets transaction rollback
            occurException();

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (savepoint == null && savepoint2 == null) {
                    if (connection != null) {
                        connection.rollback();
                    }
                } else {
                    // 回滚到指定的保存点，这里可以指定任意的保存点（savepoint或者savepoint2）去回滚
                    connection.rollback(savepoint);

                    // 上面一步会把所有设置的保存点清空，执行到这里会报错"SAVEPOINT s2 does not exist"
                    // connection.rollback(savepoint2);

                    // 指定保存点之前的内容commit
                    connection.commit();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (userStatement != null) {
                    userStatement.close();
                }
                if (logStatement != null) {
                    logStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void occurException() {
        int i = 1 / 0;
    }
}

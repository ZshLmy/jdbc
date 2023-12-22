package com.atguigu.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @Author 赵伟风
 * Description: 利用jdbc技术,完成用户数据查询工作
 *
 * TODO: 步骤总结 (6步)
 *    1. 注册驱动
 *    2. 获取连接
 *    3. 创建statement
 *    4. 发送SQL语句,并获取结果
 *    5. 结果集解析
 *    6. 关闭资源
 */
public class JdbcStatementQueryPart {

    public static Connection connection;
    static {
        try {
            //1.注册驱动
            /**
             * TODO: 注意
             *   Driver -> com.mysql.cj.jdbc.Driver
             */
            DriverManager.registerDriver(new Driver());
            //2.获取连接
            /**
             * TODO: 注意
             *   面向接口编程
             *   java.sql 接口 = 实现类
             *   connection 使用java.sql.Connection接口接收
             */
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu",
                    "root",
                    "toor!");

            //连接可以复用所以可以写成全局的变量
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        //3.创建小车
        Statement statement = connection.createStatement();

        //4.发送SQL语句
        String sql = "select id,account,password,nickname from t_user ;";
        ResultSet resultSet =  statement.executeQuery(sql);

        //5.结果集解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"::"+account+"::"+password+"::"+nickname);
        }

        //6.关闭资源  【先开后关】
        resultSet.close();
        statement.close();
        connection.close();

    }

}

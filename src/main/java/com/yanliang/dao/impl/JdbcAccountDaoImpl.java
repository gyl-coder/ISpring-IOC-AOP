package com.yanliang.dao.impl;

import com.yanliang.dao.AccountDao;
import com.yanliang.pojo.Account;
import com.yanliang.utils.ConnectionUtils;
import com.yanliang.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class JdbcAccountDaoImpl implements AccountDao {

    private ConnectionUtils connectionUtils;
    private String name;
    private int sex;
    private float money;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setMoney(float money) {
        this.money = money;
    }
    public void init() {
        System.out.println("初始化方法.....");
    }

    public void destory(){
        System.out.println("销毁方法.....");
    }

    public JdbcAccountDaoImpl(ConnectionUtils connectionUtils, String name, int sex, float money) {
        this.connectionUtils = connectionUtils;
        this.name = name;
        this.sex = sex;
        this.money = money;
    }

    private String[] myArray;
    private Map<String,String> myMap;
    private Set<String> mySet;
    private Properties myProperties;

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyProperties(Properties myProperties) {
        this.myProperties = myProperties;
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        //从连接池获取连接
//        Connection con = DruidUtils.getInstance().getConnection();
        // 改造为：从当前线程当中获取绑定的connection连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while(resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();
//        con.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        // 从连接池获取连接
//        Connection con = DruidUtils.getInstance().getConnection();
        // 改造为：从当前线程当中获取绑定的connection连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,account.getMoney());
        preparedStatement.setString(2,account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
//        con.close();
        return i;
    }
}

package com.lifeassistant.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lifeassistant.dao.IUsersDao;
import com.lifeassistant.po.User;
import com.lifeassistant.util.DBUtil;

/**
 * 
 * @author 周生锋 实现userdao接口
 */
public class UserDaoImpl implements IUsersDao {

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		// 拼接 sql 语句
		sql.append("select * from user");
		sql.append(" where userName = ? and userPassword = ?");
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// 接收返回值
		User result = null;
		// 创建连接
		connection = DBUtil.getConnection();

		try {
			// 创建执行对象
			pStatement = connection.prepareStatement(sql.toString());
			// 给参数赋值
			pStatement.setString(1, user.getUserName());
			pStatement.setString(2, user.getUserPassword());
			// 执行 接受结果
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				result = new User();
				result.setUserId(resultSet.getInt("userId"));
				result.setUserName(resultSet.getString("userName"));
				result.setUserPassword(resultSet.getString("userPassword"));
				result.setRealName(resultSet.getString("realName"));
				result.setTel(resultSet.getString("tel"));
				result.setAddress(resultSet.getString("address"));
				result.setLogintime(resultSet.getTimestamp("login_time"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);

		}

		return result;
	}

	@Override
	public Integer register(User user) {
		// TODO Auto-generated method stub
		// 准备工作 sql语句 和 将要使用的各种资源
		StringBuffer sql = new StringBuffer();
		sql.append("insert into user(userName, userPassword, realName, tel, address) ");
		sql.append(" values(?,?,?,?,?) ");
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// 接收返回值
		Integer result = 0;
		// 获取连接
		connection = DBUtil.getConnection();
		try {
			// 添加事物 设置非自动提交
			connection.setAutoCommit(false);
			// 重载的pstatment 获取生成的主键值
			pStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			// 实现循环赋值
			Object[] args = { user.getUserName(), user.getUserPassword(), user.getRealName(), user.getTel(), user.getAddress() };
			for (int i = 0; i < args.length; i++) {
				pStatement.setObject(i + 1, args[i]);
			}
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}

			// 释放资源
			DBUtil.releaseDb(resultSet, pStatement, null);
			// 创建账户
			String sql1 = "insert into account(account_money,userId) values(?,?)";
			pStatement = connection.prepareStatement(sql1);
			pStatement.setDouble(1, 0);
			pStatement.setInt(2, result);

			pStatement.executeUpdate();
			// 提交事务
			connection.commit();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// 重置result
			result = null;
			try {
				// 回滚事物
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			e1.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}

		/*
		 * // 获取连接 connection = DBUtil.getConnection(); try { pStatement = connection.prepareStatement(sql.toString());
		 * // 获取各各参数 pStatement.setString(1, user.getUserName()); pStatement.setString(2, user.getUserPassword());
		 * pStatement.setString(3, user.getRealName()); pStatement.setString(4, user.getTel()); pStatement.setString(5,
		 * user.getAddress());
		 * 
		 * // 执行 result = pStatement.executeUpdate();
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } finally {
		 * DBUtil.releaseDb(null, pStatement, connection); }
		 */

		return result;
	}

	@Override
	public boolean isExists(String userName) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select * from user where userName = ?");
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		// 用于接受查询结果

		boolean result = false;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());

			pStatement.setString(1, userName);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}
		// 返回查询结果
		return result;
	}

	@Override
	public int updateUserInfo(User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("update user set  tel =? , address =? where userId = ? ");
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());
			Object[] args = { user.getTel(), user.getAddress(), user.getUserId() };
			for (int i = 0; i < args.length; i++) {
				pStatement.setObject(i + 1, args[i]);
			}
			result = pStatement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

	@Override
	public int updatePassword(int userId, String password) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("update user set  userPassword = ? where userId = ?  ");
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, userId);
			pStatement.setString(2, password);
			result = pStatement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

	@Override
	public void updateLoginTime(int userId) {
		// TODO Auto-generated method stub
		String sql = "update user set login_time = now() where userId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, userId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}

	}

}

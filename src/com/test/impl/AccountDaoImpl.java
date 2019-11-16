package com.lifeassistant.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lifeassistant.dao.IAccountDao;
import com.lifeassistant.po.Account;
import com.lifeassistant.po.AccountLog;
import com.lifeassistant.po.AccountVo;
import com.lifeassistant.util.DBUtil;

/**
 * 
 * @author 周生锋 实现IaccountDao接口
 * 
 */
public class AccountDaoImpl implements IAccountDao {

	@Override
	public Account viewAccount(int userId) {// 查询余额 返回account对象
		// TODO Auto-generated method stub
		String sql = "select * from account where userId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Account result = null;

		
		try {
			connection = DBUtil.getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, userId);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result = new Account();
				result.setAccountId(resultSet.getInt("accountID"));
				result.setAccount_money(resultSet.getDouble("account_money"));
				
				result.setUserId(resultSet.getInt(userId));
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
	public boolean saveAccount(AccountVo accountVo) {// 存钱 返回true 或 false
		// TODO Auto-generated method stub
		String sql = "update account set account_money = account_money + ? where accountId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		// 定义返回值 初始为false
		boolean result = false;
	
		try {
			// 创键连接
			connection = DBUtil.getConnection();
			// 添加事务 默认不自动执行
			connection.setAutoCommit(false);
			// 预编译
			pStatement = connection.prepareStatement(sql);
			// 填充占位符
			pStatement.setDouble(1, accountVo.getMoney());
			pStatement.setInt(2, accountVo.getAccountId());
			// 执行
			pStatement.executeUpdate();
			// 释放资源
			DBUtil.releaseDb(null, pStatement, null);
			// 存放日志
			String sql2 = " insert into account_log(accountId,transfer_account,type,money) values(?,?,?,?)";
			pStatement = connection.prepareStatement(sql2);

			pStatement.setInt(1, accountVo.getAccountId());
			pStatement.setInt(2, accountVo.getTransferAccountId());
			pStatement.setString(3, accountVo.getType());
			pStatement.setDouble(4, accountVo.getMoney());
			
			pStatement.executeUpdate();

			// 提交事物
			connection.commit();
			// 设置返回结果
			result = true;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

	@Override
	public boolean isExistAccount(int accountId) {// 判断账户是否存在
		// TODO Auto-generated method stub
		String sql = " select * from account where accountId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// 用于接受查询结果
		boolean result = false;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, accountId);
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

		return result;
	}

	@Override
	public boolean transferAccount(AccountVo accountVo) {// 实现转账的功能
		// TODO Auto-generated method stub
		String sql = "update account set account_money = account_money-? where accountId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		boolean result = false;
		connection = DBUtil.getConnection();
		try {// 开启事务
			
			connection.setAutoCommit(false);

			pStatement = connection.prepareStatement(sql);
			pStatement.setDouble(1, accountVo.getMoney());
			pStatement.setInt(2, accountVo.getAccountId());
			pStatement.executeUpdate();
			// 释放资源
			DBUtil.releaseDb(null, pStatement, null);

			// 转入账户 操作 钱增加
			String sql2 = "update account set account_money = account_money + ? where accountId = ?";

			pStatement = connection.prepareStatement(sql2);
			// 填充占位符
			pStatement.setDouble(1, accountVo.getMoney());
			pStatement.setInt(2, accountVo.getTransferAccountId());
			// 执行
			pStatement.executeUpdate();
			DBUtil.releaseDb(null, pStatement, null);
			// 写入日志
			String sql3 = " insert into account_log(accountId,transfer_account,type,money) values(?,?,?,?)";
			pStatement = connection.prepareStatement(sql3);
			pStatement.setInt(1, accountVo.getAccountId());
			pStatement.setInt(2, accountVo.getTransferAccountId());
			pStatement.setString(3, accountVo.getType());
			pStatement.setDouble(4, accountVo.getMoney());
			pStatement.executeUpdate();
			// 提交事务
			connection.commit();
			// 事物通过 后 设置返回值
			result = true;

		} catch (Exception e) {
			try {
				
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
	
		return result;
	}

	@Override
	public double queryIncome(AccountLog accountLog) {// 查询 收入统计 及别人给该账户转入的钱
		// TODO Auto-generated method stub
		String sql = "select sum(money) as in_money from account_log where transfer_account = ? ";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		double in_money = 0;

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, accountLog.getTransfer_account());
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				in_money = resultSet.getDouble("in_money");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}

		// 返回查询到的值
		return in_money;
	}

	@Override
	public double queryExpenditure(AccountLog accountLog) {// 查询 支出统计 转出的前
		// TODO Auto-generated method stub
		String sql = "select sum(money) as out_money from account_log where accountId = ? and type = ? ";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		double out_money = 0;

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, accountLog.getAccount_Id());
			pStatement.setString(2, accountLog.getType());
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				out_money = resultSet.getDouble("out_money");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}

		// 返回查询到的值
		return out_money;

	}
}

package com.lifeassistant.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lifeassistant.dao.IMemorandumDao;
import com.lifeassistant.po.Memorandum;
import com.lifeassistant.util.DBUtil;

public class MemorandumDaoImpl implements IMemorandumDao {

	@Override
	public List getAllMemorandum(int userId) {//获取所有备忘录信息
		// TODO Auto-generated method stub
		List allmem = new ArrayList();
		String sql = "select * from memorandum where userId = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Memorandum result = new Memorandum();

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, userId);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result.setMemorandum_id(resultSet.getInt("memorandum_id"));
				result.setMemorandum_title(resultSet.getString("memorandum_title"));
				result.setDatetime(resultSet.getDate("time"));
				result.setContent(resultSet.getString("content"));
				result.setUserId(resultSet.getInt("userId"));
				allmem.add(result);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}

		return allmem;
	}

	@Override
	public int addMemorandum(Memorandum memorandum) {//添加备忘录
		// TODO Auto-generated method stub
		String sql = "insert into memorandum(memorandum_title, content, userId) values(?,?,?)";
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, memorandum.getMemorandum_title());
			pStatement.setString(2, memorandum.getContent());
			pStatement.setInt(3, memorandum.getUserId());
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

	@Override
	public int updateMemorandum(Memorandum memorandum) {//更新备忘录
		// TODO Auto-generated method stub
		String sql = "update memorandum set memorandum_title = ? , content = ? where memorandum_id = ? ";
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, memorandum.getMemorandum_title());
			pStatement.setString(2, memorandum.getContent());
			pStatement.setInt(3, memorandum.getMemorandum_id());
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

	@Override
	public Memorandum getMemorandumById(int memorandum_id) {//通过备忘录id查询备忘录
		// TODO Auto-generated method stub
		String sql = "select * from memorandum where memorandum_id = ?";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Memorandum result = new Memorandum();

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, memorandum_id);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result.setMemorandum_id(resultSet.getInt(1));
				result.setMemorandum_title(resultSet.getString(2));
				result.setDatetime(resultSet.getDate(3));
				result.setContent(resultSet.getString(4));
				result.setUserId(resultSet.getInt(5));
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
	public int deleteMemorandum(int memorandum_id) {//删除备忘录
		// TODO Auto-generated method stub
		String sql = " delete  from memorandum where memorandum_id = ? ";
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, memorandum_id);
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(null, pStatement, connection);
		}
		return result;
	}

}

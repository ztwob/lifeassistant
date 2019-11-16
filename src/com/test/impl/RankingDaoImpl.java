package com.lifeassistant.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lifeassistant.dao.IRankingDao;
import com.lifeassistant.po.Ranking;
import com.lifeassistant.po.RankingVo;
import com.lifeassistant.util.DBUtil;

public class RankingDaoImpl implements IRankingDao {

	@Override
	public int addRank(Ranking ranking) {// 添加记录
		// TODO Auto-generated method stub
		String sql = "insert into ranking(userId,integral) values(?,?)";
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, ranking.getUserId());
			pStatement.setInt(2, ranking.getIntegral());

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
	public List<RankingVo> getAllRanking() {// 游戏排行
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select rankingId,userName,integral,time  from ranking,user");
		sql.append(" where user.userId = ranking.userId  order by integral desc ");
		List allranking = new ArrayList();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement pStatement = null;

		connection = DBUtil.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				RankingVo rankingVo = new RankingVo();
				rankingVo.setIntegral(resultSet.getInt("integral"));
				rankingVo.setUserName(resultSet.getString("userName"));
				rankingVo.setTime(resultSet.getTime("time"));
				allranking.add(rankingVo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.releaseDb(resultSet, pStatement, connection);
		}

		return allranking;
	}

}

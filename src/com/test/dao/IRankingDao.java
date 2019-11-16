package com.lifeassistant.dao;

import java.util.List;

import com.lifeassistant.po.Ranking;
import com.lifeassistant.po.RankingVo;

/** 
 * 接口描述：游戏排行访问接口
 * 作者： 于红键
 * 创建日期：2017年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IRankingDao {

	/**
	 * 
	 * 方法描述：添加记录
	 * @param ranking
	 * @return
	 */
	int addRank(Ranking ranking);
	/**
	 * 
	 * 方法描述：查询游戏排名
	 * @return
	 */
	List<RankingVo> getAllRanking();
	
}

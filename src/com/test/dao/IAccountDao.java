package com.lifeassistant.dao;

import com.lifeassistant.po.Account;
import com.lifeassistant.po.AccountLog;
import com.lifeassistant.po.AccountVo;

/**
 * 接口描述：账户数据访问接口 作者： 陈向阳 创建日期：2017年11月21日 修改人： 修改日期： 修改内容： 版本号： 1.0.0
 */
public interface IAccountDao {

	/**
	 * 方法描述：查看余额
	 * 
	 * @param userId
	 *            用户ID
	 * @return 个人账户信息
	 */
	public Account viewAccount(int userId);

	/**
	 * 方法描述：存钱
	 * 
	 * @param accountVo
	 *            存钱信息
	 * @return 成功或者失败
	 */
	 public boolean saveAccount(AccountVo accountVo);

	/**
	 * 方法描述：验证是否存在该账户
	 * 
	 * @param accountId
	 *            账户ID
	 * @return 存在或者不存在
	 */
	public boolean isExistAccount(int accountId);

	/**
	 * 方法描述：转账
	 * 
	 * @param accountVo
	 *            转账信息
	 * @return 成功或者失败
	 */
	 public boolean transferAccount(AccountVo accountVo);

	/**
	 * 方法描述：当月收入查询
	 * 
	 * @param accountLog
	 *            查询条件
	 * @return 统计金额
	 */
	public double queryIncome(AccountLog accountLog);

	/**
	 * 方法描述：当月支出查询
	 * 
	 * @param accountLog
	 *            查询条件
	 * @return 统计金额
	 */
	public double queryExpenditure(AccountLog accountLog);

}

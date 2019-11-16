package com.lifeassistant.dao;
import com.lifeassistant.po.User;
/**
 * 接口描述：用户数据库访问接口
 * 作者： 陈向阳
 * 创建日期：2017年11月21日
 * 修改人：陈向阳
 * 修改日期：2017年11月21日
 * 修改内容：添加验证用户名方法
 * 版本号： 1.0.0
 */
public interface IUsersDao {
	
	/**
	 * 方法描述：用户登录
	 * @param users 用户名和密码
	 * @return 用户信息
	 */
	public User login(User user);
	
	/**
	 * 方法描述: 用户注册
	 * @param users 用户信息
	 * @return 返回主键(userId)
	 */
	public Integer register(User user);
	
	/**
	 * 方法描述: 验证用户名是否存在
	 * @param userName 用户名
	 * @return true:存在 false:不存在
	 */
	public boolean isExists(String userName);
	
	/**
	 * 方法描述: 用户信息维护
	 * @param users 用户信息
	 * @return 1:成功  0:失败
	 */
	public int updateUserInfo(User user);
	
	/**
	 * 方法描述: 修改密码
	 * @param userId 用户Id
	 * @param password 密码
	 * @return 1:成功  0:失败
	 */
	public int updatePassword(int userId,String password);
	/**
	 * 方法描述: 修改时间
	 * @param userId 用户Id
	 *  1:成功  0:失败
	 */
	public void updateLoginTime(int userId);
	
}	

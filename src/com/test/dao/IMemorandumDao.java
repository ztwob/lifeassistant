package com.lifeassistant.dao;

import java.util.List;

import com.lifeassistant.po.Memorandum;

/**
 * 
 * 接口描述：备忘录数据访问
 * 作者： 焦轲  
 * 创建日期：2017年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IMemorandumDao {
	
	/**
	 * 方法描述：查询个人所有备忘录
	 * @param userId 用户编号
	 * @return 个人备忘录信息
	 */
    public List<?> getAllMemorandum(int userId);
    
    /**
     * 方法描述：添加个人备忘录
     * @param memorandum 备忘录信息
     * @return 1或0，1代表添加成功，0代表添加失败
     */
    public int addMemorandum(Memorandum memorandum);
    
    /**
     * 方法描述：修改备忘录
     * @param memorandum 备忘录信息
     * @return 1或0，1代表修改成功，0代表修改失败
     */
    public int updateMemorandum(Memorandum memorandum);
    
    /**
     * 方法描述：根据备忘录编号查询备忘录
     * @param memorandum_id 备忘录编号
     * @return 备忘录信息
     */
    public Memorandum getMemorandumById(int memorandum_id);
    
    /**
     * 方法描述：删除备忘录
     * @param memorandum 备忘录信息
     * @return 1或0，1代表删除成功，0代表删除失败
     */
    public int deleteMemorandum(int memorandum_id);
}

package org.tj.dubbo.user.service;

import org.tj.dubbo.common.model.User;
import org.tj.dubbo.common.model.dto.UserSeach;

import com.github.pagehelper.PageInfo;

public interface UserService {

	/**
	 * 获取用户列表
	 * 
	 * @return
	 */
	public PageInfo<User> getUserListPage(UserSeach userSeach);

	/**
	 * 添加新的用户
	 * 
	 * @throws Exception
	 */
	public void addNewUser(User user) throws Exception;

	/**
	 * tcc方式控制分布式事务的添加用户
	 * 
	 * @param user
	 */
	public void addNewUserTcc(User user);

	/**
	 * 用户接口本地事务
	 * 
	 * @param user
	 */
	public void addNewUserLocal(User user);

}

package org.tj.dubbo.user.service.mapper;

import java.util.List;

import org.tj.dubbo.common.model.User;
import org.tj.dubbo.common.model.dto.UserSeach;

public interface UserMapper {

	/**
	 * 获取用户分页数据
	 * 
	 * @param userSeach
	 * @return
	 */
	List<User> getUserList(UserSeach userSeach);

	/**
	 * 添加新用户
	 * 
	 * @param user
	 */
	void addNewUser(User user);

	/**
	 * 根据id查询待插入的用户
	 * 
	 * @param id
	 * @return
	 */
	User findInsertingUserById(String id);

	/**
	 * 确认插入用户
	 * 
	 * @param waiting
	 */
	void updateUserInsertStatus(User waiting);

	/**
	 * 删掉待插入的用户
	 * 
	 * @param waiting
	 */
	void deleteInsertingUser(User waiting);

}

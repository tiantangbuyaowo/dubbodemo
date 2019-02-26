package org.tj.dubbo.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.tj.dubbo.coin.service.CoinService;
import org.tj.dubbo.common.model.Coin;
import org.tj.dubbo.common.model.CoinWait;
import org.tj.dubbo.common.model.Message;
import org.tj.dubbo.common.model.User;
import org.tj.dubbo.common.model.dto.UserSeach;
import org.tj.dubbo.common.quene.MessageQuene;
import org.tj.dubbo.common.util.IDUtil;
import org.tj.dubbo.common.util.JacksonUtil;
import org.tj.dubbo.message.service.MessageService;
import org.tj.dubbo.user.service.UserService;
import org.tj.dubbo.user.service.mapper.UserMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户管理实现类
 * 
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private MessageService messageService;

	@Resource
	private CoinService coinService;

	/**
	 * 分页查询用户数据
	 */
	public PageInfo<User> getUserListPage(UserSeach userSeach) {
		PageHelper.startPage(userSeach.getPage(), userSeach.getRows());
		List<User> users = userMapper.getUserList(userSeach);
		return new PageInfo<User>(users);
	}

	/**
	 * 添加新用户
	 * 
	 * @throws Exception
	 */
	public void addNewUser(User user) throws Exception {

		// 先预插入一条消息
		Message message = new Message();
		message.setId(IDUtil.getId());
		message.setMessageid(IDUtil.getId());
		message.setQuenename(MessageQuene.REGISTER_USER_QUENE.getQuene());
		message.setStatus("0");
		message.setMessagebody(JacksonUtil.toJSon(user));
		messageService.addConfirmMessage(message);
		// 插入用户
		userMapper.addNewUser(user);
		// 发送消息
		messageService.sendConfirmMessage(message);
	}

	/**
	 * 添加新用户
	 */
	public void addNewUserLocal(User user) {

		// 插入用户
		userMapper.addNewUser(user);
		int x = 1 / 0;
	}

	/**
	 * tcc实现添加用户
	 */
	@Compensable(confirmMethod = "confirmAddUser", cancelMethod = "cancelAddUser", asyncConfirm = true)
	public void addNewUserTcc(User user) {
		userMapper.addNewUser(user); // 预插入用户
		CoinWait coinWait = new CoinWait();

		coinWait.setId(IDUtil.getId());
		coinWait.setNum(2);
		coinWait.setType("0");
		coinWait.setUserid(user.getId());
		coinWait.setSubmitstatus("0"); // 未提交
		// 调用金币服务预操作积分
		coinService.addRegisterCoinTcc(coinWait);

	}

	/**
	 * 确认添加用户的操作
	 * 
	 * @param user
	 */
	public void confirmAddUser(User user) {
		User waiting = userMapper.findInsertingUserById(user.getId());
		if (null != waiting) { // 有一个待插入的
			waiting.setStatus("1"); // 修改为插入
			userMapper.updateUserInsertStatus(waiting);

		}
	}

	/**
	 * 取消添加用户的操作
	 * 
	 * @param user
	 */
	public void cancelAddUser(User user) {
		User waiting = userMapper.findInsertingUserById(user.getId());
		if (null != waiting) { // 有一个待插入的
			userMapper.deleteInsertingUser(waiting); // 删掉这个用户

		}
	}

}

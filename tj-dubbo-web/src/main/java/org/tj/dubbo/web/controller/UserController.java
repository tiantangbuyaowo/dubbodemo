package org.tj.dubbo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tj.dubbo.common.model.User;
import org.tj.dubbo.common.model.dto.UserSeach;
import org.tj.dubbo.common.util.IDUtil;
import org.tj.dubbo.web.base.BaseController;

import com.github.pagehelper.PageInfo;

/**
 * 后台接口请求的controller
 * 
 * @ClassName: AppBackController
 * @Description: TODO
 * @author: 唐靖
 * @date: 2017年7月18日 下午6:17:43
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@RequestMapping("toUserListUI")
	public String toUserListUI(UserSeach userSeach, Model model) {
		PageInfo<User> userPage = userService.getUserListPage(userSeach);
		model.addAttribute("userPage", userPage);
		return "user/userList";

	}

	@ResponseBody
	@RequestMapping("testCoin")
	public void testCoin() {
		coinService.addCoinTest();
	}

	@RequestMapping("addNewUser")
	public String toUserListUI(User user, int addType) {
		user.setId(IDUtil.getId());
		switch (addType) {
		case 0:
			try {
				user.setStatus("1"); // mq消息无需try阶段，直接状态为1
				userService.addNewUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 1:
			try {
				user.setStatus("0"); // tcc需要try阶段，状态为0
				userService.addNewUserTcc(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				user.setStatus("0"); // 本地事务设置
				userService.addNewUserLocal(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

		return "redirect:toUserListUI.do";

	}
}

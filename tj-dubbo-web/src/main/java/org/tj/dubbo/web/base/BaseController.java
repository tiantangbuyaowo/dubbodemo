package org.tj.dubbo.web.base;

import javax.annotation.Resource;

import org.tj.dubbo.coin.service.CoinService;
import org.tj.dubbo.user.service.UserService;

/**
 * 基类controller
 * 
 * @ClassName: BaseController
 * @Description: TODO
 * @author: 唐靖
 * @date: 2017年7月21日 下午3:19:31
 */
public class BaseController {

	@Resource
	protected UserService userService;

	@Resource
	protected CoinService coinService;
}

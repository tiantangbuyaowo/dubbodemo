package org.tj.dubbo.coin.service.mapper;

import org.tj.dubbo.common.model.Coin;

/**
 * 金币操作mapper
 * 
 * @author Administrator
 *
 */
public interface CoinMapper {

	/**
	 * 查询用户注册的金币入账记录
	 * 
	 * @param userId
	 * @return
	 */
	int getUserRegisterCount(String userId);

	/**
	 * 添加用户的金币
	 * 
	 * @param coin
	 */
	void registerUserAddCoin(Coin coin);

	/**
	 * 查询用户是否有注册金币
	 * 
	 * @param userid
	 * @return
	 */
	Coin findUserRegisterCoin(String userid);

}

package org.tj.dubbo.coin.service;

import org.mengyun.tcctransaction.api.Compensable;
import org.tj.dubbo.common.model.CoinWait;

/**
 * 用户的积分操作service
 * 
 * @author Administrator
 *
 */
public interface CoinService {
	/**
	 * 注册新用户之后增加用户积分
	 * 
	 * @param UserId
	 */
	public void registerUserAddCoin(String UserId);

	/**
	 * 添加金币的tcc操作
	 * 
	 * @param coinWait
	 */
	@Compensable
	public void addRegisterCoinTcc(CoinWait coinWait);

	/**
	 * 测试金币的本地事务
	 */
	void addCoinTest();

	/**
	 * 添加金币确认操作
	 * 
	 * @param coinWait
	 */
	void confirmAddRegisterCoin(CoinWait coinWait);

	/**
	 * 添加金币取消操作
	 * 
	 * @param coinWait
	 */
	void cancelAddRegisterCoin(CoinWait coinWait);
}

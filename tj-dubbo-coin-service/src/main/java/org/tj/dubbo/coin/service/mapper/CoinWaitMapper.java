package org.tj.dubbo.coin.service.mapper;

import org.tj.dubbo.common.model.CoinWait;

/**
 * 待插入金币操作mapper
 * 
 * @author Administrator
 *
 */
public interface CoinWaitMapper {

	/**
	 * 根据id查询待插入金币
	 * 
	 * @param id
	 * @return
	 */
	CoinWait findCoinWaitById(String id);

	/**
	 * 根据用户id查询该用户的注册金币记录数
	 * 
	 * @param userid
	 * @return
	 */
	CoinWait findUserRegisterWaitCoin(String userid);

	/**
	 * 插入新的注册金币
	 * 
	 * @param coinWait
	 */
	void addNewWaitCoin(CoinWait coinWait);

	/**
	 * 查询正在插入中的注册金币
	 * 
	 * @param id
	 * @return
	 */
	CoinWait findCoinWaitingById(String id);

	/**
	 * 改变插入金币的操作状态
	 * 
	 * @param old
	 */
	void updateCoinWaitSubmitStatus(CoinWait old);

}

package org.tj.dubbo.coin.service.impl;

import javax.annotation.Resource;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tj.dubbo.coin.service.CoinService;
import org.tj.dubbo.coin.service.mapper.CoinMapper;
import org.tj.dubbo.coin.service.mapper.CoinWaitMapper;
import org.tj.dubbo.common.model.Coin;
import org.tj.dubbo.common.model.CoinWait;
import org.tj.dubbo.common.util.IDUtil;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;

/**
 * 金币服务实现类
 * 
 * @author Administrator
 *
 */
@Service("coinService")
public class CoinServiceImpl implements CoinService {

	/**
	 * 金币操作mapper
	 */
	@Resource
	private CoinMapper coinMapper;

	@Resource
	private CoinWaitMapper coinWaitMapper;

	@Override
	public void registerUserAddCoin(String userId) {
		int count = coinMapper.getUserRegisterCount(userId); // 查询用户注册的金币入账记录
		if (count == 0) { // 没有注册过，完成注册,否则说明是重复信息，保持接口冥等性不予操作
			Coin coin = new Coin();
			coin.setId(IDUtil.getId());
			coin.setNum(2);
			coin.setType("0");
			coin.setUserid(userId);
			coinMapper.registerUserAddCoin(coin);
		}

	}

	@Override
	public void addCoinTest() {
		Coin coin = new Coin();
		coin.setId(IDUtil.getId());
		coin.setNum(2);
		coin.setType("0");
		coin.setUserid(IDUtil.getId());
		coinMapper.registerUserAddCoin(coin);
		int x = 1 / 0;

	}

	/**
	 * tcc操作添加积分操作
	 */
	@Compensable(confirmMethod = "confirmAddRegisterCoin", cancelMethod = "cancelAddRegisterCoin", transactionContextEditor = DubboTransactionContextEditor.class, asyncConfirm = true)
	public void addRegisterCoinTcc(CoinWait coinWait) {
		CoinWait old = coinWaitMapper.findCoinWaitById(coinWait.getId());
		if (null == old) { // 保证冥等性，没有才能插入
			// 一个人只能有一条注册奖励，根据用户id和类型找一下是否存在重复数据
			old = coinWaitMapper.findUserRegisterWaitCoin(coinWait.getUserid());
			if (null == old) { // 还没有，就可以插入了
				coinWaitMapper.addNewWaitCoin(coinWait);
			}

		}
	}

	/**
	 * 确认添加积分操作
	 * 
	 * @param coinWait
	 */
	@Override
	public void confirmAddRegisterCoin(CoinWait coinWait) {
		CoinWait old = coinWaitMapper.findCoinWaitingById(coinWait.getId()); // 查询状态是待插入的金币
		if (null != old) { // 有就说明可以操作
			int count = coinMapper.getUserRegisterCount(coinWait.getUserid()); // 确认是否已经加过了

			if (count == 0) {
				Coin coin = new Coin();
				coin.setId(IDUtil.getId());
				coin.setNum(old.getNum());
				coin.setType(old.getType());
				coin.setUserid(old.getUserid());
				coinMapper.registerUserAddCoin(coin);
			}
			// int x = 1 / 0;
			old.setSubmitstatus("1");
			coinWaitMapper.updateCoinWaitSubmitStatus(old); // 改为插入成功
		}
	}

	/**
	 * 取消添加积分操作
	 * 
	 * @param coinWait
	 */
	@Override
	public void cancelAddRegisterCoin(CoinWait coinWait) {
		CoinWait old = coinWaitMapper.findCoinWaitingById(coinWait.getId()); // 查询状态是待插入的金币
		if (null != old) { // 有就把状态位改为失败
			old.setSubmitstatus("3");
			coinWaitMapper.updateCoinWaitSubmitStatus(old); // 改为插入失败
		}
	}

}

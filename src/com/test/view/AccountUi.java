package com.lifeassistant.view;
/**
 * 
 * @author 周生锋
 *UserUi 跳转到AccountUI  实现个人钱包界面
 */

import java.util.Scanner;

import com.lifeassistant.dao.IAccountDao;
import com.lifeassistant.impl.AccountDaoImpl;
import com.lifeassistant.po.Account;
import com.lifeassistant.po.AccountLog;
import com.lifeassistant.po.AccountVo;
import com.lifeassistant.po.User;

public class AccountUi {

	private static Scanner scanner = new Scanner(System.in);
	private static IAccountDao iaccountDao = new AccountDaoImpl();
	private static Account account = null;

	public static void PersonalWallet(User user) {

		
		while (true) {
			account = iaccountDao.viewAccount(user.getUserId());
			System.out.println("--------个人生活助手平台--------");
			System.out.println("************个人钱包***********");
			System.out.println("1.查看余额2.存钱3.转账4.统计查询0.返回主菜单");
			System.out.print("请输入选择： ");
			int commend1 = scanner.nextInt();
			// 防止 跳过下一个sc.nextline()
			@SuppressWarnings("unused")
			String emp1 = scanner.nextLine();

			// TODO Auto-generated method stub
			switch (commend1) {
			case 1:// 实现查看余额
				// 创建account对象 传入对应的 currentuser的 userId
				account = iaccountDao.viewAccount(user.getUserId());
				viewAccount();
				break;
			case 2:// 实现存钱
				saveAccount();
				break;

			case 3:// 实现转账
			
				transferAccount();
				break;

			case 4:// 实现统计查询
				
				getInAndOutMoney();
				break;
			case 0:// 返回上级主菜单
				UserUI.menu();

				break;

			default:
				System.out.println("输入错误, 请重新输入");
				break;
			}

		}
	}

	public static void viewAccount() {// 实现输出当前账户信息
		System.out.println("您的账户" + account.getAccountId() + " 余额为： " + account.getAccount_money());
	}

	public static void saveAccount() {// 存款界面
		System.out.println("----------个人钱包----------");
		System.out.println("************存钱***********");
		System.out.print("请输入存款金额：");
		double money = scanner.nextDouble();
		if (money < 0) {
			System.out.println("输入金额不合法 ， 请重新输入");
			return;
		}

		AccountVo accountVo = new AccountVo();
		// 获取当前账户id
		accountVo.setAccountId(account.getAccountId());
		// 固定类型
		accountVo.setType("转入");
		accountVo.setMoney(money);
		boolean result = iaccountDao.saveAccount(accountVo);
		if (result) {
			System.out.println("您的账户存入： " + money + " 元");
		} else {
			System.out.println("存款失败！");
		}

	}

	public static void transferAccount() {// 转账界面
		System.out.println("----------个人钱包----------");
		System.out.println("************转账***********");
		System.out.print("请输入对方账户：");
		int accountId = scanner.nextInt();
		// 判断账户是否存在
		if (iaccountDao.isExistAccount(accountId) == false  || accountId == account.getAccountId() ) {
			System.out.println("您输入账户不合法！ 请重新输入");
			return;
		}
		// 消除nextint()的 /n
		@SuppressWarnings("unused")
		String emp = scanner.nextLine();
		System.out.print("请输入转账金额：");
		double money = scanner.nextDouble();
		// 判断输入金额是否合法
		if (money < 0 && money > account.getAccount_money()) {
			System.out.println("输入金额不合法 ， 请重新输入");
			return;
		}

		AccountVo accountVo = new AccountVo();
		// 获取当前账户id
		accountVo.setAccountId(account.getAccountId());
		accountVo.setTransferAccountId(accountId);
		// 固定类型
		accountVo.setType("转出");
		accountVo.setMoney(money);

		boolean result = iaccountDao.transferAccount(accountVo);

		if (result) {
			System.out.println("转账成功 ！ 您的账户转出了： " + money + " 元");
		} else {
			System.out.println("转账失败！");
		}

	}

	public static void getInAndOutMoney() {
		double in_money = 0;
		double out_money = 0;
		AccountLog accountLog = new AccountLog();
		// 参数传递 通过 account 获取 accountlog 对象
		accountLog.setTransfer_account(account.getAccountId());
		// 执行获取收如查询 接受返回结果
		in_money = iaccountDao.queryIncome(accountLog);
		accountLog.setAccount_Id(account.getAccountId());
		accountLog.setType("转出");
		// 执行获取支出查询 接受返回结果
		out_money = iaccountDao.queryExpenditure(accountLog);
		// 输出显示信息
		System.out.println("当前收入为： " + in_money);
		System.out.println("当前支出为: " + out_money);

	}

}

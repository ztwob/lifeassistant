package com.lifeassistant.view;

import java.util.Scanner;

import com.lifeassistant.dao.IUsersDao;
import com.lifeassistant.impl.UserDaoImpl;
import com.lifeassistant.po.User;
/**
 * 
 * @author 周生锋   
 * 二级主菜单 用户界面
 *
 */
public class UserUI {

	private static Scanner scanner = new Scanner(System.in);
	private static IUsersDao iUsersDao = new UserDaoImpl();
	// 存放当前登录用户
	private static User currentuser = null;

	public static void login() {// 实现用户登录功能
		int logcount = 1;
		while (true) {
			System.out.println(" --------个人生活助手平台-------- ");
			System.out.println(" ************用户登录*********** ");
			System.out.print("请输入用户名： ");
			String username = scanner.nextLine();
			System.out.print("请输入密   码： ");
			String password = scanner.nextLine();
			User user = new User();
			user.setUserName(username);
			user.setUserPassword(password);

			currentuser = iUsersDao.login(user);
			if (currentuser != null) {
				System.out.println("登录成功");
				// 更新当前用户的登陆时间
				iUsersDao.updateLoginTime(currentuser.getUserId());
				// 进入登录成功的界面
				menu();
			} else {
				if (++logcount > 3) {
					System.out.println("连续三次错误，请确认后重新登录");
					return;
				} else {
					System.out.println("登录失败 请在次尝试");
					continue;
				}
			}
		}
	}

	public static void register() {// 实现用户注册功能

		System.out.println("--------个人生活助手平台--------");
		System.out.println("************用户注册***********");
		// 获取键盘输入的用户信息
		System.out.print("请输入用户名：   ");
		String userName = scanner.nextLine();
		// 判断 用户名是否已存在
		if (iUsersDao.isExists(userName) == false) {
			System.out.print("请输入密    码：   ");
			String userPassword = scanner.nextLine();
			System.out.print("请输入真实名字：");
			String realName = scanner.nextLine();
			System.out.print("请输入电话号码：");
			String tel = scanner.nextLine();
			System.out.print("请输入联系地址：");
			String address = scanner.nextLine();

			// 创建user对象 接受参数
			User user = new User(userName, userPassword, realName, tel, address);
			/*
			 * user.setUserName(userName); user.setUserPassword(userPassword); user.setRealName(realName);
			 * user.setTel(tel); user.setAddress(address);
			 */
			Integer result;
			result = iUsersDao.register(user);

			if (result != null) {
				System.out.println("注册成功！正在跳转登录界面");
				currentuser = user;
				currentuser.setUserId(result);
				menu();

			} else {
				System.out.println("未知错误！");
			}

		} else {
			System.err.println("用户名已存在 ！请重新注册");
			register();
		}

	}

	// 登录成功的菜单界面
	public static void menu() {
		while (true) {
			System.out.println("--------个人生活助手平台--------");
			System.out.println("**[欢迎您 ，用户" + currentuser.getUserName() + "上次登录时间：" + currentuser.getLogintime() + "]**");
			System.out.println("1.信息维护 2.修改密码 3.个人钱包 4.备忘录 5.娱乐天地 0.返回登录主菜单");
			System.out.print("请选择：");
			int commend = scanner.nextInt();
			// 防止 跳过下一个sc.nextline()
			@SuppressWarnings("unused")
			String emp = scanner.nextLine();
			switch (commend) {
			case 1:// 信息维护
				updateUserInfo();
				break;
			case 2:// 修改密码
				updateUserPassword();
				break;
			case 3:// 个人钱包
				AccountUi.PersonalWallet(currentuser);
				break;
			case 4:// 备忘录
				MemorandumUI.MemoranUI(currentuser);
				break;
			case 5:// 娱乐天地
				RankingUI.Ranking(currentuser);
				break;
			case 0:// 返回登录主菜单
				System.out.println("--------个人生活助手平台--------");
				System.out.println("************返回登录主菜单***********");
				//重置当前登录用户信息
				currentuser = null;
				//返回登录主菜单
				MainUI.main(null);
				break;

			default:
				System.out.println("输入错误，请重新输入");
				break;

			}
		}
	}

	// 用户信息维护界面
	public static void updateUserInfo() {
		System.out.println("--------个人生活助手平台--------");
		System.out.println("************信息维护***********");

		// 显示当前用户信息
		System.out.println(currentuser);
		System.out.print("请输入新的手机号: ");

		String ntel = scanner.nextLine();
		System.out.print("请输入 新 的 地 址: ");
		String naddress = scanner.nextLine();

		currentuser.setTel(ntel);
		currentuser.setAddress(naddress);

		int result = iUsersDao.updateUserInfo(currentuser);
		if (result > 0) {
			System.out.println("更新成功！");
			System.out.println(currentuser);
		} else {
			System.out.println("更新失败 ！  请再次尝试");
		}
	}

	// 用户密码修改界面
	public static void updateUserPassword() {
		System.out.println("--------个人生活助手平台--------");
		System.out.println("************修改密码***********");
		System.out.println("请输入原密码");
		String oldpsw = scanner.next();
		// 判断 密码
		while (true) {

			if (!oldpsw.equals(currentuser.getUserPassword())) {
				System.out.println("您输入的原密码不正确");

			} else {
				System.out.print("请输入新密码: ");
				String newpsw1 = scanner.nextLine();
				System.out.println("请输入新密码: ");
				String newpsw2 = scanner.nextLine();
				if (newpsw1 == newpsw2) {
					System.out.println("两次输入密码不一致！");
					break;
				} else {
					int result = 0;
					result = iUsersDao.updatePassword(currentuser.getUserId(), newpsw1);
					if (result > 0) {
						System.out.println("修改成功 ， 请重新登录");
						login();
					} else {
						System.out.println("修改失败");
					}
				}
			}
		}
	}
}

package com.lifeassistant.view;

import com.lifeassistant.po.User;

import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Copies;

import com.lifeassistant.dao.IRankingDao;
import com.lifeassistant.impl.RankingDaoImpl;
import com.lifeassistant.po.Ranking;
import com.lifeassistant.po.RankingVo;;

public class RankingUI {
	private static User currentuser = null;
	private static IRankingDao iRankingDao = new RankingDaoImpl();
	private static Scanner scanner = new Scanner(System.in);
	private static int integral = 0;
	private static boolean b = true;

	public static void Ranking(User user) {

		while (true) {
			currentuser = user;
			System.out.println("----------娱乐天地----------");
			System.out.println("**【欢迎您，用户-" + user.getUserName() + "】**");
			System.out.println("1.猜拳游戏  2.游戏排行榜  0. 返回主菜单");
			int commend = scanner.nextInt();
			switch (commend) {

			case 1:// 进入游戏
				game();
				break;
			case 2:// 显示排行榜
				showRanking();
				break;
			case 0:
				UserUI.menu();
				break;

			default:
				System.out.println("输入错误！请重新输入");
				break;
			}
		}

	}

	private static void showRanking() {// 显示排行榜
		// TODO Auto-generated method stub
		System.out.println("----------娱乐天地----------");
		System.out.println("----------排行榜----------");
		// 输出首栏信息
		System.out.print("排名" + "\t");
		System.out.print("姓名" + "\t");
		System.out.print("积分" + "\t");
		System.out.println("时间" + "\t");
		// 查询当前用户的所有的备忘录
		List<RankingVo> rankings = iRankingDao.getAllRanking();
		// 循环遍历输出备忘录信息
		int count = 0;
		for (RankingVo me : rankings) {
			count += 1;
			System.out.print(count + "\t");
			System.out.print(me.getUserName() + "\t");
			System.out.print(me.getIntegral() + "\t");
			System.out.print(me.getTime() + "\t");
			System.out.println();
		}

	}

	private static void game() {// 游戏
		// TODO Auto-generated method stub
		System.out.println("----------娱乐天地----------");
		System.out.println("----------猜拳游戏----------");

		// 设置退出条件
		
		// 给电脑和用户赋参数名
		int nowUser = 1;
		int computer = 0;
		int result = 0;
		while (b) {
			System.out.println("请出拳： 1。剪刀  2。石头3。 布");
			int select = scanner.nextInt();
			// 判断游戏结果的函数
			// 用户出拳类型
			guessout(nowUser, select);
			// 电脑出拳类型 有随机数决定
			int com_select = (int) ((Math.random() * 3) + 1);
			guessout(computer, com_select);
			result = guessWin(select, com_select);
			
			
			

		}

		System.out.println("游戏结束，你获得 " + integral + " 分");
		Ranking rank = new Ranking();
		rank.setUserId(currentuser.getUserId());
		rank.setIntegral(result);
		iRankingDao.addRank(rank);
	}

	private static void guessout(int user, int select) {
		// TODO Auto-generated method stub
		switch (select) {
		case 1:
			if (user == 1) {
				System.out.println(currentuser.getUserName() + " 出：剪刀 ");
			} else {
				System.out.println("电脑出：剪刀 ");
			}

			break;
		case 2:
			if (user == 1) {
				System.out.println(currentuser.getUserName() + " 出： 石头");
			} else {
				System.out.println("电脑出： 石头");
			}
			break;
		case 3:
			if (user == 1) {
				System.out.println(currentuser.getUserName() + " 出：布 ");
			} else {
				System.out.println("电脑出： 布");
			}
			break;

		default:
			System.out.print("输入错误，请重新输入！   ");
			System.out.println("出拳规则：1。剪刀  2。石头  3。布");
			break;
		}
	}

	public static int guessWin(int select, int com_select) {

		if (select == com_select) {
			System.out.println("和局！");
			b = false;
		} else if (select == 1 && com_select == 3) {
			System.out.println("hoho ！你赢了");
			integral++;

		} else if (select == 3 && com_select == 1) {
			System.out.println("wowo ！你输了");
			b = false;
		} else if (select > com_select) {
			System.out.println("hoho ！你赢了");
			integral++;

		} else if (select < com_select) {
			System.out.println("wowo ！你输了");
			b = false;
		}
		return integral;

	}

}

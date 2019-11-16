package com.lifeassistant.view;

import java.util.List;
import java.util.Scanner;

import com.lifeassistant.dao.IMemorandumDao;
import com.lifeassistant.impl.MemorandumDaoImpl;
import com.lifeassistant.po.Memorandum;
import com.lifeassistant.po.User;

public class MemorandumUI {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentuser = null;
	private static IMemorandumDao memorandumDao = new MemorandumDaoImpl();
	private static Memorandum memorandum = null;

	public static void MemoranUI(User user) {
		while (true) {
			currentuser = user;
			System.out.println("************我的备忘录***********");
			System.out.println("**【欢迎您，用户-" + currentuser.getUserName() + "】**");
			System.out.println("1.查看备忘录   2.添加备忘录   3.修改备忘录   4.删除备忘录   0. 返回主菜单");
			System.out.print("请选择：");
			int commend = scanner.nextInt();
			@SuppressWarnings("unused") // 防止错误
			String emp = scanner.nextLine();
			switch (commend) {
			case 1:// 查看备忘录
				showAllMemorandum();
				break;
			case 2:// 添加备忘录
				addMemroandum();
				break;
			case 3:// 修改备忘录
				updateMemorandum();
				break;
			case 4:// 删除备忘录
				deleteMemorandum();
				break;
			case 0:// 返回主菜单
				UserUI.menu();
				break;
			default:
				System.out.println("输入错误 请重新输入！");
				break;
			}
		}
	}

	private static void deleteMemorandum() {// 删除界面
		// TODO Auto-generated method stub
		System.out.println("----------我的备忘录----------");
		System.out.println("**********删除备忘录**********");
		showAllMemorandum();
		System.out.print("请输入要删除备忘录的编号： ");

		int memid = scanner.nextInt();
		memorandum = new Memorandum();
		memorandum = memorandumDao.getMemorandumById(memid);
		if (memorandum == null && currentuser.getUserId() != memorandum.getUserId()) {
			System.out.println("输入错误！ 请重新输入");
			return;
		}
		System.out.println("是否确认删除  y：确认  n：取消");
		String select = scanner.nextLine();
		switch (select) {
		case "y":
			int result = memorandumDao.deleteMemorandum(memid);
			if (result > 0) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
			break;
		case "n":
			System.out.println("删除取消！");
			break;
		default:
			System.out.println("输入有误！");
			break;
		}
		
	}

	private static void updateMemorandum() {// 更新界面
		// TODO Auto-generated method stub
		System.out.println("----------我的备忘录----------");
		System.out.println("**********修改备忘录**********");
		showAllMemorandum();
		System.out.print("请输入要修改备忘录的编号： ");
		int memid = scanner.nextInt();

		@SuppressWarnings("unused") // 防止nextInt后的错误
		String emp = scanner.nextLine();
		memorandum = new Memorandum();
		memorandum = memorandumDao.getMemorandumById(memid);
		if (memorandum == null && currentuser.getUserId() != memorandum.getUserId()) {
			System.out.println("输入错误！ 请重新输入");
			return;
		}
		memorandum.toString();
		System.out.print("请输入新的标题：");
		String newtit = scanner.nextLine();
		System.out.print("请输入新的内容：");
		String newcont = scanner.nextLine();

		memorandum.setMemorandum_title(newtit);
		memorandum.setContent(newcont);

		int result = memorandumDao.updateMemorandum(memorandum);
		if (result > 0) {
			System.out.println("修改成功！请查看");
			showAllMemorandum();
		}
	}

	private static void addMemroandum() {// 添加界面
		// TODO Auto-generated method stub
		System.out.println("----------我的备忘录----------");
		System.out.println("**********添加备忘录**********");
		System.out.print("请输入标题");
		String title = scanner.nextLine();
		System.out.print("请输入内容");
		String content = scanner.nextLine();
		memorandum = new Memorandum();
		memorandum.setMemorandum_title(title);
		memorandum.setContent(content);
		memorandum.setUserId(currentuser.getUserId());

		int result = memorandumDao.addMemorandum(memorandum);
		if (result > 0) {
			System.out.println("添加成功！ 请注意查看");
			showAllMemorandum();
		}
	}

	private static void showAllMemorandum() {// 显示全部信息界面
		// TODO Auto-generated method stub
		System.out.println("----------我的备忘录----------");
		System.out.println("**********查看备忘录**********");
		List<Memorandum> memorandums = (List<Memorandum>) memorandumDao.getAllMemorandum(currentuser.getUserId());
		for (Memorandum mem : memorandums) {
			System.out.print("1.编号：" + mem.getMemorandum_id() + "\t");
			System.out.print("2.标题：" + mem.getMemorandum_title() + "\t");
			System.out.print("3.时间：" + mem.getDatetime() + "\t");
			System.out.print("4.内容：" + mem.getContent() + "\t");
			System.out.println();
		}

	}
}

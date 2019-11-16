package com.lifeassistant.view;

import java.util.Scanner;

/**
 * 
 * @author 周生锋 生活助手入口主界面设计
 */
public class MainUI {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("----------个人生活助手平台----------");
			System.out.println("***************1.登录**************");
			System.out.println("***************2.注册**************");
			System.out.println("***************0.退出**************");
			System.out.println("-----------------------------------");
			System.out.print("请选择： ");
			int comment = scanner.nextInt();
			switch (comment) {
			case 1:
				UserUI.login();

				break;
			case 2:
				UserUI.register();
				break;

			case 0:
				System.out.println("退出成功  再见！");
				System.exit(0);
				break;

			default:
				System.out.println("输入错误， 请重新输入");
				break;
			}

		}
	}

}

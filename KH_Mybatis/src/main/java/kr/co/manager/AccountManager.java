package kr.co.manager;

import java.util.Scanner;

import kr.co.controller.AccountController;
import kr.co.vo.AccountVO;
public class AccountManager {

	private Scanner sc = new Scanner(System.in);
	private AccountVO user = null;
	
	public void start() {
		while(true) {
			System.out.println("[1] 회원 가입 요청 목록");
			System.out.println("[2] 회원 가입 요청");
			System.out.println("[3] 회원 가입 요청 승인");
			System.out.println("[4] 회원 목록");
			if(this.user == null) {
				System.out.println("[5] 로그인");
			} else {
				System.out.println("[5] 로그아웃");
			}
			System.out.println("[9] 프로그램 종료");
			
			int menu = 0;
			while(true) {
				System.out.print("메뉴 번호 : ");
				if(sc.hasNextInt()) {
					menu = sc.nextInt(); sc.nextLine();
					break;
				}
			}
			
			switch(menu) {
				case 1:
					this.getAccountRequestList();
					break;
				case 2:
					this.setAccountRequest();
					break;
				case 3:
					this.acceptAccountRequest();
					break;
				case 4:
					this.getAccountList();
					break;
				case 5:
					if(this.user == null) {
						this.login();
					} else {
						this.logout();
					}
					break;
				case 9:
					System.exit(0);
			}
		}
	}

	private void logout() {
		AccountController ac = new AccountController();
		this.user = ac.logout(this.user);
	}

	private void login() {
		AccountController ac = new AccountController();
		
		System.out.print("닉네임 입력 : ");
		String nickname = sc.nextLine();
		
		System.out.print("암호 입력 : ");
		String password = sc.nextLine();
		
		this.user = ac.login(nickname, password);
	}

	private void acceptAccountRequest() {
		this.getAccountRequestList();
		
		System.out.println("위 목록에서 가입을 승인할 회원의 ID 를 입력하세요.");
		System.out.println("예)");
		System.out.println("    ID 입력 : 1 3 6 9\n");
		
		System.out.print("ID 입력 : ");
		String[] inputArr = sc.nextLine().split(" ");
		int[] idArr = new int[inputArr.length];
		
		AccountController ac = new AccountController();
		for(int i = 0; i < idArr.length; i++) {
			idArr[i] = Integer.parseInt(inputArr[i]);
		}
		
		ac.acceptAccountRequest(idArr);
	}

	private void getAccountList() {
		AccountController ac = new AccountController();
		ac.getAccountList();
	}

	private void setAccountRequest() {
		System.out.print("닉네임 입력 : ");
		String nickname = sc.nextLine();
		
		System.out.print("이메일 입력 : ");
		String email = sc.nextLine();
		
		AccountController ac = new AccountController();
		ac.setAccountRequest(nickname, email);
	}

	private void getAccountRequestList() {
		AccountController ac = new AccountController();
		ac.getAccountRequestList();
	}

}
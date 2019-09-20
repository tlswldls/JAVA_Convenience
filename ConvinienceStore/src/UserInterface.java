import java.io.*;
import java.util.*;

public class UserInterface {
	public static void main(String []args) throws Exception {
		
		Management manager = new Management();
		try {
			//fIn에 information.txt파일 저장
			FileInputStream fIn = new FileInputStream("information.txt");
			//in에 fIn저장
			DataInputStream in = new DataInputStream(fIn);
			manager = new Management(in);

		}
		catch(FileNotFoundException e) {	//파일이 존재하지 않는 경우
			System.out.println("존재하지 않는 파일입니다.");
		}
		catch(IOException e) {	//읽어올 수 없는 파일인 경우
			System.out.println("파일로 출력할 수 없습니다.");
		}
		catch(Exception e) {	//읽어오는 과정에서 문제가 발생한 경우
			System.out.println("읽어오기에 실패했습니다.");
		}
		
		System.out.println(
				"---------------*편의점 프로그램*---------------"
				);
		while(true) {
			//시작 메뉴 선택지 출력
			StartMenu.startMenu();
			Scanner input = new Scanner(System.in);
			int answer;	//사용자의 응답을 저장할 변수 answer(int형)
			String answerStr;	//사용자의 응답을 저장할 변수 answerStr(String형)
			char answerChar;	//사용자의 응답을 저장할 변수 answerChar(char형)
			Goods[] categoryItems = null;	//같은 카테고리의 물품을 저장하기 위한 객체 배열
			
			try {
				answer = input.nextInt();	//사용자가 입력한 숫자를 answer에 할당
			}
			catch(java.util.InputMismatchException e){	//사용자가 정수가 아닌 다른 값을 입력한 경우
				System.out.print("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
				answer = 0;	//사용자가 입력한 값을 answer에 저장할 수 없으므로 answer을 0으로 초기화
			}
			
			input.nextLine();	//키보드 버퍼를 비우기 위한 nextLine()
			
			if(answer == 1) {//1. 사용자
				while(true) {
					//사용자 메뉴 선택지 출력
					StartMenu.clientMenu();
					try {
						answer = input.nextInt();
						input.nextLine();
					}catch(java.util.InputMismatchException e) {	//숫자가 아닌 값을 입력한 경우
						System.out.print("숫자만 입력하세요.");
					}
					if(answer==1) {	//상품 구매를 선택한 경우
						System.out.print("구매하고자 하는 물품의 이름을 입력하세요.: ");
						answerStr = input.next();
						input.nextLine();
						if(manager.findGoodsIndex(answerStr)==-1) {	//입력받은 상품의 이름과 같은 값을 가진 객체가 없는 경우
							System.out.print("존재하지 않는 상품입니다.");
							continue;	//다음 반복 실행
						}
						while(true) {
							System.out.print(answerStr + "을(를) 몇개 구매하시겠습니까?(1 이상의 숫자만 입력하세요.): ");
							try {
								answer = input.nextInt();
								input.nextLine();
							}catch(java.util.InputMismatchException e) {	//숫자가 아닌 값을 입력한 경우
								System.out.print("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
								answer = 0;	//사용자가 입력한 값을 answer에 저장할 수 없으므로 answer을 0으로 초기화
								continue;	//다음 반복 실행
							}
							
							if(answer<=0) {	//사용자가 입력한 값(구매하고자 하는 물건의 개수)이 0이하이면
								System.out.println("1 이상의 숫자만 입력하세요.");
								continue;	//다음 반복 실행
							}else if(answer>manager.getGoodsList()[manager.findGoodsIndex(answerStr)].getstock()) {	//사용자가 입력한 값(구매하고자 하는 물건의 개수)이 해당 물품의 재고보다 크다면
								System.out.println("재고가 모자랍니다." +manager.getGoodsList()[manager.findGoodsIndex(answerStr)].getstock()+"이하의 숫자를 입력해주세요.");
								continue;	//다음 반복 실행
							}
							break;	//제대로 입력된 경우 반복 종료
						}
						
						while(true){
							try {	//재고와 인덱스 값이 제대로 입력되어 총 금액이 제대로 반환되는 경우
								System.out.print(manager.sellEstimate(manager.findGoodsIndex(answerStr), answer)+"원입니다. 구매하시겠습니까? (y/n)");	//구매할지 물어보기
							}catch(Exception e) {	//제대로 입력되지 않아 익셉션이 발생한 경우
								System.out.print("잘못된 값이 입력되었습니다. 다시 시도해주세요.");
								break;	//반복 종료
							}
							answerChar = input.next().charAt(0);
							if(answerChar=='y') {	//사용자가 구매하겠다고 한 경우
								if(manager.sellEstimate(manager.findGoodsIndex(answerStr), answer)!=-1) {	//총 금액이 제대로 반환되면
									manager.sell(manager.findGoodsIndex(answerStr), answer);	//구매 메소드 실행
									System.out.println("구매가 완료되었습니다.");	//구매가 완료되었습니다 출력
									break;	//반복 종료
								}else {	//제대로 반환되지 않은 경우
									System.out.print("잘못된 값이 입력되었습니다. 다시 시도해주세요.");
									break;
								}
							}else if(answerChar=='n') {	//사용자가 구매하지 않겠다고 한 경우
								break;	//반복 종료
							}else {	//잘못 입력한 경우
								System.out.println("다시 입력해주세요. y / n중 선택해주세요.");	
								continue;	//다음 반복 실행
							}
						}
					}else if(answer==2) {	//종료를 선택한 경우
						break;	//반복 종료
					}
				}
				
			}else if(answer==2) {//2. 관리자
				//관리자 프로그램 메뉴 선택지 출력
				StartMenu.managementMenu();
				try {
					answer = input.nextInt();	//사용자가 입력한 숫자를 answer에 할당
				}
				catch(java.util.InputMismatchException e){	//사용자가 정수가 아닌 다른 값을 입력한 경우
					System.out.print("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
					answer = 0;	//사용자가 입력한 값을 answer에 저장할 수 없으므로 answer을 0으로 초기화
				}
				input. nextLine();
				
				if(answer == 1) {//상품 추가
					//사용자에게 입력받은 goods에 대한 데이터를 저장 할 변수 선언
					String category;
					String name;
					int price;
					int stock;
					
					//사용자가 값을 입력하도록 안내 문구를 출력하고 입력받은 값으로 각각의 변수를 초기화 한다
					System.out.print("상품의 카테고리를 입력하세요.: ");
					category = input.next();
					input. nextLine();

					System.out.print("상품의 이름을 입력하세요.: ");
					name = input.next();
					input. nextLine();
					
					while(true) {
						System.out.print("상품의 가격을 입력하세요.(숫자만 입력하세요.): ");

						try {
							price = input.nextInt();
							input. nextLine();
						}
						catch(java.util.InputMismatchException e){	//사용자가 정수가 아닌 다른 값을 입력한 경우
							System.out.print("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
							input = new Scanner(System.in);	//input변수 다시 선언
							continue;	//while문의 처음으로 돌아가 다시 실행
						}
						break;	//while문 반복 종료
					}
					
					while(true) {
						System.out.print("상품의 재고를 입력하세요.(숫자만 입력하세요.): ");

						try {
							stock = input.nextInt();
							input. nextLine();
						}
						catch(java.util.InputMismatchException e){	//사용자가 정수가 아닌 다른 값을 입력한 경우
							System.out.print("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
							input = new Scanner(System.in);	//input변수 다시 선언
							continue;	//while문의 처음으로 돌아가 다시 실행
						}
						break;	//while문 반복 종료
					}
					//입력받은 값으로 상품 객체 goods를 선언한다.
					Goods goods = new Goods(category, name, price, manager.getcount(), stock);
					
					manager.insertGoods(goods);	//goods를 goodsList에 추가
				}else if(answer == 2) {//2. 상품 목록 조회.
					System.out.println("상품 목록 조회");
					Goods[] goodsList = manager.getGoodsList();
					for(int i=0; i<manager.getNum(); i++) {
						if(manager.getGoodsList()[i]!=null) {	//배열이 비어있지 않으면 해당 물품의 데이터를 출력
							System.out.println(i+1);
							System.out.println("상품명 : " + goodsList[i].getname());
							System.out.println("상품 번호 : " + goodsList[i].getitemNumber());
							System.out.println("카테고리 : " + goodsList[i].getcategory());
							System.out.println("가격 : " + goodsList[i].getprice());
							System.out.println("재고 : " + goodsList[i].getstock());
						}
					}
				}else if(answer == 3) {//3. 상품 삭제. 
					while(true) {
						//상품의 카테고리를 입력받아 그 카테고리에 속하는 물품을 출력함
						System.out.print("삭제하고자 하는 상품의 카테고리를 입력하세요. : ");
						answerStr = input.next();
						input.nextLine();
						try{
							categoryItems = manager.findGoods(answerStr);
						}catch(Exception e){	//입력받은 이름과 일치하는 카테고리를 갖는 상품이 없는 경우
							System.out.println("존재하지 않는 카테고리입니다. 다시 입력하세요.");
							continue;
						}
						for(int i=0; i<=categoryItems.length-1; i++) {
							if(categoryItems[i]!=null) {	//배열이 비어있지 않으면 해당 물품의 데이터 출력 같은 카테고리의 아이템이기 때문에 카테고리 정보는 생략했다.
								System.out.println(i+1+"번");
								System.out.println("상품명 : " + categoryItems[i].getname());
								System.out.println("상품 번호 : " + categoryItems[i].getitemNumber());
								System.out.println("가격 : " + categoryItems[i].getprice());
								System.out.println("재고 : " + categoryItems[i].getstock());
								System.out.println();
							}
						}
						while(true) {
							System.out.print("삭제할 물품의 번호를 입력하세요.(숫자만 입력하세요.): ");
							try {
								answer = input.nextInt();
								input. nextLine();
							}catch(Exception e) {
								System.out.print("숫자만 입력하세요");
							}
							if(answer>categoryItems.length || answer<=0) {	//입력받은 값이 물품 번호의 범위를 벗어나는 경우
								System.out.print("다시 입력하세요.");
								continue;	//다음 반복 실행
							}
							break;	//입력받은 값이 범위를 벗어나지 않는다면 반복 종료
						}
						if(manager.findGoodsIndex(categoryItems[answer-1].getname())==-1) {	//입력받은 이름과 일치하는 이름을 가진 객체가 없다면 다시 입력하도록 안내문 출력
							System.out.println("존재하지 않는 상품입니다. 다시 입력하세요.");
						}else {	//있다면 반복문 종료
							break;
						}
					}
					try {
						manager.deleteGoods(manager.findGoodsIndex(categoryItems[answer-1].getname()));	//입력받은 물품 번호에 해당하는 물품의 이름을 통해 해당 물품 삭제
						System.out.println("삭제되었습니다.");
					} catch (Exception e) {	//입력받은 물품 번호에 해당하는 물품의 이름과 일치하는 이름을 가진 객체가 없다면
						System.out.println("물품이 존재하지 않습니다.");
						break;	//반복문 종료
					}
				}else if(answer==4) {	//저장
					try {
						//fOut에 information.txt파일 저장
						FileOutputStream fOut = new FileOutputStream("information.txt");
						//out에 fOut저장
						DataOutputStream out = new DataOutputStream(fOut);
						
						//out에 객체의 프로그램 실행 내용을 저장
						manager.saveFile(out);
						out.close();
					}
					catch(IOException e) {	//파일로 출력이 제대로 이루어지지 않는 경우
						System.out.println("파일로 출력할 수 없습니다.");
					}
					catch(Exception e) {	//출력을 하는 과정에서 에러가 발생한 경우
						System.out.println("저장에 실패했습니다.");
					}
					
				}else if(answer == 5){
					break;
				}else{// 사용자가 1, 2, 3, 4외의 다른 숫자를 입력하였을 때 다시 입력하도록 안내 문구를 출력한다.
				
					System.out.println("다시 입력하세요.");
				}
			}else if(answer==3){//3. 종료
				break;
			}else {	//사용자가 1, 2, 3외의 다른 숫자를 입력한 경우
				System.out.println("다시 입력하세요.");
			}
		}

	}
}

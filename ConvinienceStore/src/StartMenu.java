
public class StartMenu {
	public static void startMenu() {	//프로그램리 시작될 경우 출력하는 메뉴
		System.out.println(		"\n원하시는 작업을 선택해 주세요.(숫자만 입력하세요.)"+
				"\n1. 사용자"+
				"\n2. 관리자"+
				"\n3. 종료");

	}
	
	public static void  managementMenu() {	//관리자가 선택될 경우 출력하는 메뉴
		System.out.println(		"\n원하시는 작업을 선택해 주세요.(숫자만 입력하세요.)"+
				"\n1. 상품 추가"+
				"\n2. 상품 목록 조회"+
				"\n3. 상품 삭제"+
				"\n4. 저장"+
				"\n5. 종료");

	}
	public static void clientMenu() {	//사용자가 선택된 경우 호출되는 메뉴
		System.out.println("\n원하시는 작업을 선택해 주세요.(숫자만 입력하세요.)"+
	"\n1. 상품 구매"+
	"\n2. 종료");
	}
}

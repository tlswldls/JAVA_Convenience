import java.io.*;
import java.util.*;
public class Management {
	private LinkedList<Goods> goodsList = new LinkedList<Goods>();
	private int count = 0;	//insertGoods함수가 호출된 횟수를 나타내는 데이터필드. 객체의 상품번호를 나타낸다
	private int num = 0;	//GoodsList에 들어있는 객체의 개수
	private int totalSales = 0;
	
	Management(){
		//빈 생성자
	}
	
	Management(DataInputStream in)throws Exception{
		try {
			readFile(in);
			in.close();
		}catch(Exception e) {
			throw new Exception("읽어오기 에러");
		}
	}
	
	LinkedList<Goods> getGoodsList() {//goodsList를 반환하는 메소드
		return goodsList;
	}

	void insertGoods(Goods item) {//goodsList에 새로운 물건을 추가하는 메소드
				goodsList.add(item);//goodList[num]에 item을 할당하고
				count++;	//count에 1을 더한다.
				num++;
	}
	
	int getcount() {	//insertGoods함수가 실행된 횟수를 반환해주는 메소드
		return count;
	}
	
	int getTotalSales() {	//총 매출액을 반환해주는 메소드
		return totalSales;
	}
	
	int findGoodsIndex(String name) {	//상품 이름을 파라메터로 해서 그 객체가 있는 배열의 index를 반환하는 메소드
		for(int i = 0; i<goodsList.size(); i++) {
			if(goodsList.get(i).getname().equals(name)) {	//i를 인덱스로 갖는 객체의 이름과 파라미터로 들어온 이름이 같다면
				return i;	//그 객체의 인덱스 값을 리턴
			}else {	//값과 이름이 다르다면
				continue;	//다음 반복 실행
			}
		}
		return -1;	//입력받은 이름과 일치하는 값이 없을 경우 -1반환
	}
	
	void deleteGoods(int index) throws Exception {	//인덱스를 입력받고 그 인덱스의 값을 삭제하는 메소드
		if(goodsList.size()<=0) {	//goodsList에 들어있는 원소의 개수가 0보다 작거나 같으면
			throw new Exception("물품 없음");	//물품 없음 익셉션 던지기
		}else {
			goodsList.remove(index);
		}
			num--;
	}
	
	Goods[] findGoods(String category) throws Exception {//카테고리 이름을 입력받고 그 카테고리에 해당하는 객체 배열을 반환하는 메소드
		Goods[] categoryList = new Goods[count];	//카테고리에 속하는 객체들을 저장할 배열 categoryList
		int index = 0;//객체를 저장할 인덱스값
		for(int i = 0; i<goodsList.size(); i++) {
			if(goodsList.get(i).getcategory().equals(category)) {//i를 인덱스로 갖는 객체의 카테고리와 입력받은 카테고리의 값이 같다면
				categoryList[index] = goodsList.get(i);	//index를 인덱스로 갖는 위치에 그 객체를 저장하고
				index++;	//index값 1증가
			}
		}
		if(index==0) {
			throw new Exception("존재하지 않는 카테고리");
		}else{
			return categoryList;	//카테고리 리스트 반환
		}
	}

	
	int sellEstimate(int index, int sellCount)throws Exception {	//구매자가 원하는 물품의 값을 알려주는 메소드
		if(sellCount<=0 || goodsList.get(index).getstock()<sellCount) {	//입력받은 sellCount값이 음수거나 해당 물품의 재고보다 값이 큰 경우
			throw new Exception("재고 부족");
		}
		else return (goodsList.get(index).getprice()*sellCount);	//물품의 가격*구매하고자 하는 물품의 개수를 계산한 값(총 금액)을 리턴
	}
	
	int sell(int index, int sellCount) {	//구매자가 구매를 원하면 실제 구매를 실행하는 메소드
		int totalPrice = 0;	//총 금액을 나타내는 변수 totalPrice
		if(sellCount<=0  || goodsList.get(index).getstock()<sellCount) {	//sellCount가 0이하이거나 해당 물품의 재고보다 큰 경우
			return -1;	//-1반환
		}else {
			totalPrice = goodsList.get(index).getprice()*sellCount;	//totlaPrice에 해당 물품의 가격*구매하고자 하는 개수를 계산한 값을 할당
			try {
				goodsList.get(index).removeStock(sellCount);	//해당 물품의 재고의 개수에서 sellCount만큼 빼 재고의 개수를 다시 할당해줌
			} catch (Exception e) {
				System.out.println("재고의 개수가 부족합니다.");
			}
			totalSales += totalPrice;	//총매출에 총 가격을 더한다.
			return totalPrice;	//총 가격을 리턴
		}
	}
	
	void saveFile(DataOutputStream out) throws Exception{	//데이터를 txt파일로 출력하는 메소드
		try{
			out.writeInt(totalSales);	//누적 총 매출값 출력
			out.writeInt(count);	//다음 상품 번호 값을 가지고 있는 count 값 출력
			out.writeInt(goodsList.size());	//배열에 저장된 객체의 개수 값을 가지고 있는 num 출력
		      
			for(int i=0; i<goodsList.size(); i++) {	//객체가 가지고 있는 데이터 출력
				goodsList.get(i).save(out);
			}
		}catch(Exception e) {	//출력이 제대로 이루어지지 않은 경우 익셉션 던지기
			throw new Exception("출력 오류");
		}
	}

	void readFile(DataInputStream in) throws Exception{	//txt파일에서 데이터를 읽어오는 메소드
		try {
			totalSales = in.readInt();	//누적 총 매출값 읽어와 저장
			count = in.readInt();	//다음 상품 번호 값을 가지고 있는 count 읽어와 저장
			num = in.readInt();	//배열에 저장된 객체의 개수 값을 가지고 있는 num 읽어와 저장
			
			for(int i=0; i<num; i++) {	//for문을 돌면서 객체가 가지고 있는 데이터 읽어와 저장	
				Goods goods = new Goods();	//goodsList에 새로운 객체를 만들어 할당
				goods.read(in);
				goodsList.add(goods);
			}
		}catch(Exception e) {	//입력이 제대로 이루어지지 않은 경우 익셉션 던지기
			throw new Exception("입력 오류");
		}
	}
}

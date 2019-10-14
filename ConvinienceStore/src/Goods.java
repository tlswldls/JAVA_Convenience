import java.io.*;

public class Goods implements java.io.Serializable {
	private String category;	//대분류 이름
	private String name;	//제품명
	private int price;	//가격
	private int itemNumber; //상품의 고유번호를 나타내는 itemNumber. 생성자함수가 호출될 때 마다 1씩 증가한다.
	private int stock;	//재고수량

	
	Goods(String category, String name, int price, int itemNumber, int stock){
		this.category = category;
		this.name = name;
		this.price = price;
		this.itemNumber = itemNumber;
		this.stock = stock;
	}
	
	Goods(){
		
	}
	
	String getcategory() {
		return category;
	}
	
	String getname() {
		return name;
	}

	int getprice() {
		return price;
	}

	int getitemNumber() {
		return itemNumber;
	}

	int getstock() {
		return stock;
	}

	void setcategory(String category) {
		this.category = category;
	}

	void setname(String name) {
		this.name = name;
	}

	void setprice(int price) {
		this.price = price;
	}

	void setitemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	void setstock(int stock) {
		this.stock = stock;
	}
	
	void addStuck() {	//재고를 더하는 메소드
		stock++;
	}
	
	void removeStock(int count) throws Exception{	//재고를 빼는 메소드
		if(stock<count) {	//재고의 개수가 0이하이면
			throw new Exception("재고 부족");	//익셉션 던지기
		}else {	//재고의 개수가 0 이상이면 
			stock = stock - count;
		}
	}
/*
	void save(ObjectOutputStream out) throws Exception {
		//객체의 데이터필드의 값을 파일로 출력
		//출력이 제대로 이루어지지 않으면 익셉션
		out.writeUTF(category);
		out.writeUTF(name);
		out.writeInt(price);
		out.writeInt(itemNumber);
		out.writeInt(stock);

	}
	
	void read(ObjectInputStream in) throws Exception {
		//객체의 데이터필드에 값을 저장
		//읽어오는게 제대로 안되면 익셉션
		try {
			category = in.readUTF();
		}catch(Exception e) {
			throw new Exception("카테고리 못읽음");
		}
		try {
			name = in.readUTF();
		}catch(Exception e) {
			throw new Exception("이름 못읽음");
		}
		try {
			price = in.readInt();
			itemNumber = in.readInt();
			stock = in.readInt();
		}catch(Exception e) {
			throw new Exception("숫자 못읽음");
		}
	}
*/
}

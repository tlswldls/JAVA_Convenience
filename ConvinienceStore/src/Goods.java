import java.io.*;

public class Goods implements java.io.Serializable {
	private String category;	//��з� �̸�
	private String name;	//��ǰ��
	private int price;	//����
	private int itemNumber; //��ǰ�� ������ȣ�� ��Ÿ���� itemNumber. �������Լ��� ȣ��� �� ���� 1�� �����Ѵ�.
	private int stock;	//������

	
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
	
	void addStuck() {	//��� ���ϴ� �޼ҵ�
		stock++;
	}
	
	void removeStock(int count) throws Exception{	//��� ���� �޼ҵ�
		if(stock<count) {	//����� ������ 0�����̸�
			throw new Exception("��� ����");	//�ͼ��� ������
		}else {	//����� ������ 0 �̻��̸� 
			stock = stock - count;
		}
	}
/*
	void save(ObjectOutputStream out) throws Exception {
		//��ü�� �������ʵ��� ���� ���Ϸ� ���
		//����� ����� �̷������ ������ �ͼ���
		out.writeUTF(category);
		out.writeUTF(name);
		out.writeInt(price);
		out.writeInt(itemNumber);
		out.writeInt(stock);

	}
	
	void read(ObjectInputStream in) throws Exception {
		//��ü�� �������ʵ忡 ���� ����
		//�о���°� ����� �ȵǸ� �ͼ���
		try {
			category = in.readUTF();
		}catch(Exception e) {
			throw new Exception("ī�װ� ������");
		}
		try {
			name = in.readUTF();
		}catch(Exception e) {
			throw new Exception("�̸� ������");
		}
		try {
			price = in.readInt();
			itemNumber = in.readInt();
			stock = in.readInt();
		}catch(Exception e) {
			throw new Exception("���� ������");
		}
	}
*/
}

import java.io.*;
import java.util.*;
public class Management {
	private LinkedList<Goods> goodsList = new LinkedList<Goods>();
	private int count = 0;	//insertGoods�Լ��� ȣ��� Ƚ���� ��Ÿ���� �������ʵ�. ��ü�� ��ǰ��ȣ�� ��Ÿ����
	private int num = 0;	//GoodsList�� ����ִ� ��ü�� ����
	private int totalSales = 0;
	
	Management(){
		//�� ������
	}
	
	Management(DataInputStream in)throws Exception{
		try {
			readFile(in);
			in.close();
		}catch(Exception e) {
			throw new Exception("�о���� ����");
		}
	}
	
	Goods[] getGoodsList() {//goodsList�� ��ȯ�ϴ� �޼ҵ�
		Goods[] goods = new Goods[goodsList.size()];
		for(int i = 0; i<goodsList.size(); i++) {
			goods[i] = goodsList.get(i);
		}
		return goods;
	}

	void insertGoods(Goods item) {//goodsList�� ���ο� ������ �߰��ϴ� �޼ҵ�
				goodsList.add(item);//goodList[num]�� item�� �Ҵ��ϰ�
				count++;	//count�� 1�� ���Ѵ�.
				num++;
	}
	
	int getcount() {	//insertGoods�Լ��� ����� Ƚ���� ��ȯ���ִ� �޼ҵ�
		return count;
	}
	
	int getNum() {
		return num;
	}
	
	int getTotalSales() {	//�� ������� ��ȯ���ִ� �޼ҵ�
		return totalSales;
	}
	
	int findGoodsIndex(String name) {	//��ǰ �̸��� �Ķ���ͷ� �ؼ� �� ��ü�� �ִ� �迭�� index�� ��ȯ�ϴ� �޼ҵ�
		for(int i = 0; i<goodsList.size(); i++) {
			if(goodsList.get(i).getname().equals(name)) {	//i�� �ε����� ���� ��ü�� �̸��� �Ķ���ͷ� ���� �̸��� ���ٸ�
				return i;	//�� ��ü�� �ε��� ���� ����
			}else {	//���� �̸��� �ٸ��ٸ�
				continue;	//���� �ݺ� ����
			}
		}
		return -1;	//�Է¹��� �̸��� ��ġ�ϴ� ���� ���� ��� -1��ȯ
	}
	
	void deleteGoods(int index) throws Exception {	//�ε����� �Է¹ް� �� �ε����� ���� �����ϴ� �޼ҵ�
		if(goodsList.size()<=0) {	//goodsList�� ����ִ� ������ ������ 0���� �۰ų� ������
			throw new Exception("��ǰ ����");	//��ǰ ���� �ͼ��� ������
		}else {
			goodsList.remove(index);
		}
			num--;
	}
	
	Goods[] findGoods(String category) throws Exception {//ī�װ� �̸��� �Է¹ް� �� ī�װ��� �ش��ϴ� ��ü �迭�� ��ȯ�ϴ� �޼ҵ�
		Goods[] categoryList = new Goods[count];	//ī�װ��� ���ϴ� ��ü���� ������ �迭 categoryList
		int index = 0;//��ü�� ������ �ε�����
		for(int i = 0; i<goodsList.size(); i++) {
			if(goodsList.get(i).getcategory().equals(category)) {//i�� �ε����� ���� ��ü�� ī�װ��� �Է¹��� ī�װ��� ���� ���ٸ�
				categoryList[index] = goodsList.get(i);	//index�� �ε����� ���� ��ġ�� �� ��ü�� �����ϰ�
				index++;	//index�� 1����
			}
		}
		if(index==0) {
			throw new Exception("�������� �ʴ� ī�װ�");
		}else{
			return categoryList;	//ī�װ� ����Ʈ ��ȯ
		}
	}

	
	int sellEstimate(int index, int sellCount)throws Exception {	//�����ڰ� ���ϴ� ��ǰ�� ���� �˷��ִ� �޼ҵ�
		if(sellCount<=0 || goodsList.get(index).getstock()<sellCount) {	//�Է¹��� sellCount���� �����ų� �ش� ��ǰ�� ����� ���� ū ���
			throw new Exception("��� ����");
		}
		else return (goodsList.get(index).getprice()*sellCount);	//��ǰ�� ����*�����ϰ��� �ϴ� ��ǰ�� ������ ����� ��(�� �ݾ�)�� ����
	}
	
	int sell(int index, int sellCount) {	//�����ڰ� ���Ÿ� ���ϸ� ���� ���Ÿ� �����ϴ� �޼ҵ�
		int totalPrice = 0;	//�� �ݾ��� ��Ÿ���� ���� totalPrice
		if(sellCount<=0  || goodsList.get(index).getstock()<sellCount) {	//sellCount�� 0�����̰ų� �ش� ��ǰ�� ����� ū ���
			return -1;	//-1��ȯ
		}else {
			totalPrice = goodsList.get(index).getprice()*sellCount;	//totlaPrice�� �ش� ��ǰ�� ����*�����ϰ��� �ϴ� ������ ����� ���� �Ҵ�
			try {
				goodsList.get(index).removeStock(sellCount);	//�ش� ��ǰ�� ����� �������� sellCount��ŭ �� ����� ������ �ٽ� �Ҵ�����
			} catch (Exception e) {
				System.out.println("����� ������ �����մϴ�.");
			}
			totalSales += totalPrice;	//�Ѹ��⿡ �� ������ ���Ѵ�.
			return totalPrice;	//�� ������ ����
		}
	}
	
	void saveFile(DataOutputStream out) throws Exception{	//�����͸� txt���Ϸ� ����ϴ� �޼ҵ�
		try{
			out.writeInt(totalSales);	//���� �� ���Ⱚ ���
			out.writeInt(count);	//���� ��ǰ ��ȣ ���� ������ �ִ� count �� ���
			out.writeInt(goodsList.size());	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� num ���
		      
			for(int i=0; i<goodsList.size(); i++) {	//��ü�� ������ �ִ� ������ ���
				goodsList.get(i).save(out);
			}
		}catch(Exception e) {	//����� ����� �̷������ ���� ��� �ͼ��� ������
			throw new Exception("��� ����");
		}
	}

	void readFile(DataInputStream in) throws Exception{	//txt���Ͽ��� �����͸� �о���� �޼ҵ�
		try {
			totalSales = in.readInt();	//���� �� ���Ⱚ �о�� ����
			count = in.readInt();	//���� ��ǰ ��ȣ ���� ������ �ִ� count �о�� ����
			num = in.readInt();	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� num �о�� ����
			
			for(int i=0; i<num; i++) {	//for���� ���鼭 ��ü�� ������ �ִ� ������ �о�� ����	
				Goods goods = new Goods();	//goodsList�� ���ο� ��ü�� ����� �Ҵ�
				goods.read(in);
				goodsList.add(goods);
			}
		}catch(Exception e) {	//�Է��� ����� �̷������ ���� ��� �ͼ��� ������
			throw new Exception("�Է� ����");
		}
	}
}

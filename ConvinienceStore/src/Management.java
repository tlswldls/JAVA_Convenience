import java.io.*;
import java.util.*;
public class Management {
	private LinkedList<Goods> goodsList = new LinkedList<Goods>();	//��ǰ ��ü�� �����ϴ� LinkedList goodsList
	private int count = 0;	//insertGoods�Լ��� ȣ��� Ƚ���� ��Ÿ���� �������ʵ�. ��ü�� ��ǰ��ȣ�� ��Ÿ����
	private int num = 0;	//GoodsList�� ����ִ� ��ü�� ����
	private int totalSales = 0;
	
	
	Management(DataInputStream in)throws Exception{
			//����� ������ �ִٸ� �ڵ����� �о���� ������
		try {
			readFile(in);
			in.close();
		}catch(Exception e) {//�ͼ����� �߻��� ���
			throw new Exception("�о���� ����");
		}
	}
	
	Goods[] getGoodsList() {//�迭 ������ goodsList�� ��ȯ�ϴ� �޼ҵ�
		Iterator<Goods> iterator = goodsList.iterator();
		Goods[] goods = new Goods[goodsList.size()];	//goodsList �ȿ� �ִ� ��ü�� ������ �迭 ����
		int i = 0;
		while(iterator.hasNext()){	//for���� ���� ��ü�� �ϳ��� ����
			Goods goods_ = iterator.next();
			goods[i] = goods_;
			i++;
		}
		return goods;	//�迭�� ����
	}

	void insertGoods(Goods item) {//goodsList�� ���ο� ������ �߰��ϴ� �޼ҵ�
				goodsList.add(item);//goodList[num]�� item�� �Ҵ��ϰ�
				count++;	//count�� 1�� ���Ѵ�.
				num++;
	}
	
	int getcount() {	//insertGoods�Լ��� ����� Ƚ���� ��ȯ���ִ� �޼ҵ�
		return count;
	}
	
	int getNum() {	//LinkedList�� ����� ��ü�� ������ ��ȯ���ִ� �޼ҵ�
		return num;
	}
	
	int getTotalSales() {	//�� ������� ��ȯ���ִ� �޼ҵ�
		return totalSales;
	}
	
	int findGoodsIndex(String name) {	//��ǰ �̸��� �Ķ���ͷ� �ؼ� �� ��ü�� �ִ� �迭�� index�� ��ȯ�ϴ� �޼ҵ�
		Iterator<Goods> iterator = goodsList.iterator();
		
		int i=0;
		while(iterator.hasNext()) {
			if(iterator.next().getname().equals(name)) {	//i�� �ε����� ���� ��ü�� �̸��� �Ķ���ͷ� ���� �̸��� ���ٸ�
				return i;	//�� ��ü�� �ε��� ���� ����
			}else {	//���� �̸��� �ٸ��ٸ�
				i++;
				continue;	//���� �ݺ� ����
			}
		}
		return -1;	//�Է¹��� �̸��� ��ġ�ϴ� ���� ���� ��� -1��ȯ
	}
	
	void deleteGoods(int index) throws Exception {	//�ε����� �Է¹ް� �� �ε����� ���� �����ϴ� �޼ҵ�
		Iterator<Goods> iterator = goodsList.iterator();
		
		if(!iterator.hasNext()) {	//goodsList�� ����ִ� ������ ������ 0���� �۰ų� ������
			throw new Exception("��ǰ ����");	//��ǰ ���� �ͼ��� ������
		}else {
			goodsList.remove(index);	//�ε��� ���� �ִ� ��ü�� goodsList���� ����
			num--;	//num���� 1 ����
		}
	}
	
	Goods[] findGoods(String category) throws Exception {//ī�װ� �̸��� �Է¹ް� �� ī�װ��� �ش��ϴ� ��ü �迭�� ��ȯ�ϴ� �޼ҵ�
		Iterator<Goods> iterator = goodsList.iterator();
		Goods[] categoryList = new Goods[goodsList.size()];	//ī�װ��� ���ϴ� ��ü���� ������ �迭 categoryList
		int index = 0;//��ü�� ������ �ε�����
		/*
		for(int i = 0; i<goodsList.size(); i++) {
			if(goodsList.get(i).getcategory().equals(category)) {//i�� �ε����� ���� ��ü�� ī�װ��� �Է¹��� ī�װ��� ���� ���ٸ�
				categoryList[index] = goodsList.get(i);	//index�� �ε����� ���� ��ġ�� �� ��ü�� �����ϰ�
				index++;	//index�� 1����
			}
		}
		*/
		
		while(iterator.hasNext()) {
			Goods goods = iterator.next();
			if(goods.getcategory().equals(category)) {
				categoryList[index] = goods;
				index++;
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
			Iterator<Goods> iterator = goodsList.iterator();
			out.writeInt(totalSales);	//���� �� ���Ⱚ ���
			out.writeInt(count);	//���� ��ǰ ��ȣ ���� ������ �ִ� count �� ���
			out.writeInt(goodsList.size());	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� num ���
		      
			int i=0;
			while(iterator.hasNext()) {	//��ü�� ������ �ִ� ������ ���
				iterator.next().save(out);
				i++;
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

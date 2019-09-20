import java.io.*;
import java.util.*;

public class UserInterface {
	public static void main(String []args) throws Exception {
		
		Management manager = new Management();
		try {
			//fIn�� information.txt���� ����
			FileInputStream fIn = new FileInputStream("information.txt");
			//in�� fIn����
			DataInputStream in = new DataInputStream(fIn);
			manager = new Management(in);

		}
		catch(FileNotFoundException e) {	//������ �������� �ʴ� ���
			System.out.println("�������� �ʴ� �����Դϴ�.");
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
		}
		catch(Exception e) {	//�о���� �������� ������ �߻��� ���
			System.out.println("�о���⿡ �����߽��ϴ�.");
		}
		
		System.out.println(
				"---------------*������ ���α׷�*---------------"
				);
		while(true) {
			//���� �޴� ������ ���
			StartMenu.startMenu();
			Scanner input = new Scanner(System.in);
			int answer;	//������� ������ ������ ���� answer(int��)
			String answerStr;	//������� ������ ������ ���� answerStr(String��)
			char answerChar;	//������� ������ ������ ���� answerChar(char��)
			Goods[] categoryItems = null;	//���� ī�װ��� ��ǰ�� �����ϱ� ���� ��ü �迭
			
			try {
				answer = input.nextInt();	//����ڰ� �Է��� ���ڸ� answer�� �Ҵ�
			}
			catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
				System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
				answer = 0;	//����ڰ� �Է��� ���� answer�� ������ �� �����Ƿ� answer�� 0���� �ʱ�ȭ
			}
			
			input.nextLine();	//Ű���� ���۸� ���� ���� nextLine()
			
			if(answer == 1) {//1. �����
				while(true) {
					//����� �޴� ������ ���
					StartMenu.clientMenu();
					try {
						answer = input.nextInt();
						input.nextLine();
					}catch(java.util.InputMismatchException e) {	//���ڰ� �ƴ� ���� �Է��� ���
						System.out.print("���ڸ� �Է��ϼ���.");
					}
					if(answer==1) {	//��ǰ ���Ÿ� ������ ���
						System.out.print("�����ϰ��� �ϴ� ��ǰ�� �̸��� �Է��ϼ���.: ");
						answerStr = input.next();
						input.nextLine();
						if(manager.findGoodsIndex(answerStr)==-1) {	//�Է¹��� ��ǰ�� �̸��� ���� ���� ���� ��ü�� ���� ���
							System.out.print("�������� �ʴ� ��ǰ�Դϴ�.");
							continue;	//���� �ݺ� ����
						}
						while(true) {
							System.out.print(answerStr + "��(��) � �����Ͻðڽ��ϱ�?(1 �̻��� ���ڸ� �Է��ϼ���.): ");
							try {
								answer = input.nextInt();
								input.nextLine();
							}catch(java.util.InputMismatchException e) {	//���ڰ� �ƴ� ���� �Է��� ���
								System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
								answer = 0;	//����ڰ� �Է��� ���� answer�� ������ �� �����Ƿ� answer�� 0���� �ʱ�ȭ
								continue;	//���� �ݺ� ����
							}
							
							if(answer<=0) {	//����ڰ� �Է��� ��(�����ϰ��� �ϴ� ������ ����)�� 0�����̸�
								System.out.println("1 �̻��� ���ڸ� �Է��ϼ���.");
								continue;	//���� �ݺ� ����
							}else if(answer>manager.getGoodsList()[manager.findGoodsIndex(answerStr)].getstock()) {	//����ڰ� �Է��� ��(�����ϰ��� �ϴ� ������ ����)�� �ش� ��ǰ�� ����� ũ�ٸ�
								System.out.println("��� ���ڶ��ϴ�." +manager.getGoodsList()[manager.findGoodsIndex(answerStr)].getstock()+"������ ���ڸ� �Է����ּ���.");
								continue;	//���� �ݺ� ����
							}
							break;	//����� �Էµ� ��� �ݺ� ����
						}
						
						while(true){
							try {	//���� �ε��� ���� ����� �ԷµǾ� �� �ݾ��� ����� ��ȯ�Ǵ� ���
								System.out.print(manager.sellEstimate(manager.findGoodsIndex(answerStr), answer)+"���Դϴ�. �����Ͻðڽ��ϱ�? (y/n)");	//�������� �����
							}catch(Exception e) {	//����� �Էµ��� �ʾ� �ͼ����� �߻��� ���
								System.out.print("�߸��� ���� �ԷµǾ����ϴ�. �ٽ� �õ����ּ���.");
								break;	//�ݺ� ����
							}
							answerChar = input.next().charAt(0);
							if(answerChar=='y') {	//����ڰ� �����ϰڴٰ� �� ���
								if(manager.sellEstimate(manager.findGoodsIndex(answerStr), answer)!=-1) {	//�� �ݾ��� ����� ��ȯ�Ǹ�
									manager.sell(manager.findGoodsIndex(answerStr), answer);	//���� �޼ҵ� ����
									System.out.println("���Ű� �Ϸ�Ǿ����ϴ�.");	//���Ű� �Ϸ�Ǿ����ϴ� ���
									break;	//�ݺ� ����
								}else {	//����� ��ȯ���� ���� ���
									System.out.print("�߸��� ���� �ԷµǾ����ϴ�. �ٽ� �õ����ּ���.");
									break;
								}
							}else if(answerChar=='n') {	//����ڰ� �������� �ʰڴٰ� �� ���
								break;	//�ݺ� ����
							}else {	//�߸� �Է��� ���
								System.out.println("�ٽ� �Է����ּ���. y / n�� �������ּ���.");	
								continue;	//���� �ݺ� ����
							}
						}
					}else if(answer==2) {	//���Ḧ ������ ���
						break;	//�ݺ� ����
					}
				}
				
			}else if(answer==2) {//2. ������
				//������ ���α׷� �޴� ������ ���
				StartMenu.managementMenu();
				try {
					answer = input.nextInt();	//����ڰ� �Է��� ���ڸ� answer�� �Ҵ�
				}
				catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
					System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
					answer = 0;	//����ڰ� �Է��� ���� answer�� ������ �� �����Ƿ� answer�� 0���� �ʱ�ȭ
				}
				input. nextLine();
				
				if(answer == 1) {//��ǰ �߰�
					//����ڿ��� �Է¹��� goods�� ���� �����͸� ���� �� ���� ����
					String category;
					String name;
					int price;
					int stock;
					
					//����ڰ� ���� �Է��ϵ��� �ȳ� ������ ����ϰ� �Է¹��� ������ ������ ������ �ʱ�ȭ �Ѵ�
					System.out.print("��ǰ�� ī�װ��� �Է��ϼ���.: ");
					category = input.next();
					input. nextLine();

					System.out.print("��ǰ�� �̸��� �Է��ϼ���.: ");
					name = input.next();
					input. nextLine();
					
					while(true) {
						System.out.print("��ǰ�� ������ �Է��ϼ���.(���ڸ� �Է��ϼ���.): ");

						try {
							price = input.nextInt();
							input. nextLine();
						}
						catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
							System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
							input = new Scanner(System.in);	//input���� �ٽ� ����
							continue;	//while���� ó������ ���ư� �ٽ� ����
						}
						break;	//while�� �ݺ� ����
					}
					
					while(true) {
						System.out.print("��ǰ�� ��� �Է��ϼ���.(���ڸ� �Է��ϼ���.): ");

						try {
							stock = input.nextInt();
							input. nextLine();
						}
						catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
							System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
							input = new Scanner(System.in);	//input���� �ٽ� ����
							continue;	//while���� ó������ ���ư� �ٽ� ����
						}
						break;	//while�� �ݺ� ����
					}
					//�Է¹��� ������ ��ǰ ��ü goods�� �����Ѵ�.
					Goods goods = new Goods(category, name, price, manager.getcount(), stock);
					
					manager.insertGoods(goods);	//goods�� goodsList�� �߰�
				}else if(answer == 2) {//2. ��ǰ ��� ��ȸ.
					System.out.println("��ǰ ��� ��ȸ");
					Goods[] goodsList = manager.getGoodsList();
					for(int i=0; i<manager.getNum(); i++) {
						if(manager.getGoodsList()[i]!=null) {	//�迭�� ������� ������ �ش� ��ǰ�� �����͸� ���
							System.out.println(i+1);
							System.out.println("��ǰ�� : " + goodsList[i].getname());
							System.out.println("��ǰ ��ȣ : " + goodsList[i].getitemNumber());
							System.out.println("ī�װ� : " + goodsList[i].getcategory());
							System.out.println("���� : " + goodsList[i].getprice());
							System.out.println("��� : " + goodsList[i].getstock());
						}
					}
				}else if(answer == 3) {//3. ��ǰ ����. 
					while(true) {
						//��ǰ�� ī�װ��� �Է¹޾� �� ī�װ��� ���ϴ� ��ǰ�� �����
						System.out.print("�����ϰ��� �ϴ� ��ǰ�� ī�װ��� �Է��ϼ���. : ");
						answerStr = input.next();
						input.nextLine();
						try{
							categoryItems = manager.findGoods(answerStr);
						}catch(Exception e){	//�Է¹��� �̸��� ��ġ�ϴ� ī�װ��� ���� ��ǰ�� ���� ���
							System.out.println("�������� �ʴ� ī�װ��Դϴ�. �ٽ� �Է��ϼ���.");
							continue;
						}
						for(int i=0; i<=categoryItems.length-1; i++) {
							if(categoryItems[i]!=null) {	//�迭�� ������� ������ �ش� ��ǰ�� ������ ��� ���� ī�װ��� �������̱� ������ ī�װ� ������ �����ߴ�.
								System.out.println(i+1+"��");
								System.out.println("��ǰ�� : " + categoryItems[i].getname());
								System.out.println("��ǰ ��ȣ : " + categoryItems[i].getitemNumber());
								System.out.println("���� : " + categoryItems[i].getprice());
								System.out.println("��� : " + categoryItems[i].getstock());
								System.out.println();
							}
						}
						while(true) {
							System.out.print("������ ��ǰ�� ��ȣ�� �Է��ϼ���.(���ڸ� �Է��ϼ���.): ");
							try {
								answer = input.nextInt();
								input. nextLine();
							}catch(Exception e) {
								System.out.print("���ڸ� �Է��ϼ���");
							}
							if(answer>categoryItems.length || answer<=0) {	//�Է¹��� ���� ��ǰ ��ȣ�� ������ ����� ���
								System.out.print("�ٽ� �Է��ϼ���.");
								continue;	//���� �ݺ� ����
							}
							break;	//�Է¹��� ���� ������ ����� �ʴ´ٸ� �ݺ� ����
						}
						if(manager.findGoodsIndex(categoryItems[answer-1].getname())==-1) {	//�Է¹��� �̸��� ��ġ�ϴ� �̸��� ���� ��ü�� ���ٸ� �ٽ� �Է��ϵ��� �ȳ��� ���
							System.out.println("�������� �ʴ� ��ǰ�Դϴ�. �ٽ� �Է��ϼ���.");
						}else {	//�ִٸ� �ݺ��� ����
							break;
						}
					}
					try {
						manager.deleteGoods(manager.findGoodsIndex(categoryItems[answer-1].getname()));	//�Է¹��� ��ǰ ��ȣ�� �ش��ϴ� ��ǰ�� �̸��� ���� �ش� ��ǰ ����
						System.out.println("�����Ǿ����ϴ�.");
					} catch (Exception e) {	//�Է¹��� ��ǰ ��ȣ�� �ش��ϴ� ��ǰ�� �̸��� ��ġ�ϴ� �̸��� ���� ��ü�� ���ٸ�
						System.out.println("��ǰ�� �������� �ʽ��ϴ�.");
						break;	//�ݺ��� ����
					}
				}else if(answer==4) {	//����
					try {
						//fOut�� information.txt���� ����
						FileOutputStream fOut = new FileOutputStream("information.txt");
						//out�� fOut����
						DataOutputStream out = new DataOutputStream(fOut);
						
						//out�� ��ü�� ���α׷� ���� ������ ����
						manager.saveFile(out);
						out.close();
					}
					catch(IOException e) {	//���Ϸ� ����� ����� �̷������ �ʴ� ���
						System.out.println("���Ϸ� ����� �� �����ϴ�.");
					}
					catch(Exception e) {	//����� �ϴ� �������� ������ �߻��� ���
						System.out.println("���忡 �����߽��ϴ�.");
					}
					
				}else if(answer == 5){
					break;
				}else{// ����ڰ� 1, 2, 3, 4���� �ٸ� ���ڸ� �Է��Ͽ��� �� �ٽ� �Է��ϵ��� �ȳ� ������ ����Ѵ�.
				
					System.out.println("�ٽ� �Է��ϼ���.");
				}
			}else if(answer==3){//3. ����
				break;
			}else {	//����ڰ� 1, 2, 3���� �ٸ� ���ڸ� �Է��� ���
				System.out.println("�ٽ� �Է��ϼ���.");
			}
		}

	}
}

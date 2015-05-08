package bank;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ATM {

	static Scanner scanner;
	static ArrayList<acount> list = new ArrayList<acount>();
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		loop: while (true) {
			
		System.out.println("1.���µ��");
		System.out.println("2.���¸��");
		System.out.println("3.���¼���");
		System.out.println("4.���»���");
		System.out.println("5.�����");
		System.out.println("6.����� �����丮���");
		System.out.println("=>");
		
		int selectNo = scanner.nextInt();
		scanner.nextLine();
		try {
		switch(selectNo) {
			case 1:
				System.out.println("�����̸��� �Է��ϼ���:");
				String accountName = scanner.nextLine();
				
				System.out.print("���¹�ȣ�� �Է��ϼ���:");
				int accountNo = scanner.nextInt();
				loop1: while(accountNo < 0 || accountNo > 999) {
					
					System.out.print("1~999������ ���ڸ� �Է��ϼ���.");
					accountNo = scanner.nextInt();
					continue loop1;
				}
				
				System.out.print("������� �Է��ϼ���");
				scanner.nextLine();
				String bankName = scanner.nextLine();
				
				
				System.out.print("������ȭ��ȣ�� �Է��ϼ���");
				String bankPhone = scanner.nextLine();
					acount account = new acount (
							accountName,
							accountNo,
							bankName,
							bankPhone
							
							);
					list.add(account);
			break;
			case 2:
				
				
				Collections.sort(list, new Comparator<acount>()  {
					public int compare(acount account1, acount account2){
						return account1.getAccountName().compareTo(account2.getAccountName());
					}
				}) ;
				
				
				
				
				
				System.out.println("�����̸�  |   ���¹�ȣ     |    �ܾ�        |   �����      |     ���� ��ȭ��ȣ      |");
				int index = 0;
				for(acount account1 : list)	{
					System.out.printf("%8s %8s %10s %9s %12s\n",
							account1.getAccountName(), numberFormat(account1.getAccountNo()),
							account1.getMoney()+"��", account1.getBankName(), account1.getBankPhone());
					index++;			
					
					
				}
				
				
				System.out.println("2");
				
				
			break;
			case 3:
				doUpdate();
			break;
			case 4:
				doDelete();
			break;
			case 5:
				doIO();
			break;
			case 6:
				System.out.println("6");
			break;
			
			default:
			
				System.out.println("�� ����� �������� �ʽ��ϴ�.");
			
		}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("��ɾ� ó�� �� �����߻�.");
		}
		}
	}
		private static void doIO() {
			loop2 : while (true){
				System.out.println("1.�Ա�");
				
				System.out.println("2.���");
				
				System.out.println("3.������");
	
				System.out.print("�޴��� �Է��� �ּ���");
				
				int selectNo = scanner.nextInt();
				scanner.nextLine();
				
				if (selectNo == 1) {
					System.out.print("�Ա��� ���¸� �Է����ּ���");
				} else if (selectNo == 2) {
					System.out.print("����� ���¸� �Է����ּ���");
				} else if (selectNo == 3) {
					break;
				}
				
				
				else {
					System.out.println("�߸��� ����Դϴ�.");
					
					selectNo = scanner.nextInt();
					continue loop2;
				}
				
				int accountNumber = Integer.parseInt(scanner.nextLine());
				acount account = list.get(searchAccount(accountNumber));
				
			}				
			
		};
		private static void doDelete() {
			System.out.print("������ ���¹�ȣ�� �Է����ּ���");
	        int accountNumber = Integer.parseInt(scanner.nextLine());
	        
	        
	        acount account = list.get(searchAccount(accountNumber));
	        
	        System.out.print(account.getAccountName() +"-"+ account.getAccountNo() +" �� �����Ͻðڽ��ϱ�?(y/n)");
	        if (scanner.nextLine().equalsIgnoreCase("y")) { 
	            
	            //�ܾ� ����
	            if(account.getMoney() == 0){
	                list.remove(searchAccount(accountNumber));
	                System.out.println("�����Ͽ����ϴ�."); 
	            }else{
	                System.out.println("�ܾ��� 0�� �̻��̸� ���¸� ������ �� �����ϴ�.");
	            }
	        } else { 
	            System.out.println("���� ����Ͽ����ϴ�."); 
	        } 
	}

		private static int searchAccount(int accountNo){
	        int i;
	            for(i = 0; i < list.size(); i++){
	                if(list.get(i).getAccountNo() == accountNo){
	                    break;
	                }
	            }
	            return i;
	    };
	    
	    private static void doUpdate()throws CloneNotSupportedException{
	        System.out.print("������ ���¹�ȣ�� �Է����ּ���");
	        int accountNumber = Integer.parseInt(scanner.nextLine());
	        
	        acount account = list.get(searchAccount(accountNumber));
	        acount tempAccount = account.clone();
	    
	         String text = null; 
	         System.out.printf("���¹�ȣ(%s):", numberFormat(account.getAccountNo())); 
	           text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setAccountNo(Integer.parseInt(text)); 
	    
	        System.out.printf("���¸�(%s):", account.getAccountName()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setAccountName(text); 
	    
	        System.out.printf("�����̸�(%s):", account.getBankName()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setBankName(text);  
	    
	        System.out.printf("�����ȣ(%d):", account.getBankPhone()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setBankPhone(text); 
	    
	        System.out.print("���� �����Ͻðڽ��ϱ�?(y/n)"); 
	        if (scanner.nextLine().equalsIgnoreCase("y")) { 
	    
	          list.set(searchAccount(accountNumber), tempAccount); 
	          System.out.println("�����Ͽ����ϴ�."); 
	        } else { 
	          System.out.println("���� ����Ͽ����ϴ�."); 
	        } 
	    }

		
		
	

	private static String numberFormat(int accountNo) {
		NumberFormat numformat = NumberFormat.getIntegerInstance();
		numformat.setMinimumIntegerDigits(3);
	
		return numformat.format(accountNo);
	}

}

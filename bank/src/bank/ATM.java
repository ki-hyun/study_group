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
			
		System.out.println("1.계좌등록");
		System.out.println("2.계좌목록");
		System.out.println("3.계좌수정");
		System.out.println("4.계좌삭제");
		System.out.println("5.입출금");
		System.out.println("6.입출금 히스토리목록");
		System.out.println("=>");
		
		int selectNo = scanner.nextInt();
		scanner.nextLine();
		try {
		switch(selectNo) {
			case 1:
				System.out.println("계좌이름을 입력하세요:");
				String accountName = scanner.nextLine();
				
				System.out.print("계좌번호을 입력하세요:");
				int accountNo = scanner.nextInt();
				loop1: while(accountNo < 0 || accountNo > 999) {
					
					System.out.print("1~999사이의 숫자를 입력하세요.");
					accountNo = scanner.nextInt();
					continue loop1;
				}
				
				System.out.print("은행명을 입력하세요");
				scanner.nextLine();
				String bankName = scanner.nextLine();
				
				
				System.out.print("은행전화번호를 입력하세요");
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
				
				
				
				
				
				System.out.println("계좌이름  |   계좌번호     |    잔액        |   은행명      |     은행 전화번호      |");
				int index = 0;
				for(acount account1 : list)	{
					System.out.printf("%8s %8s %10s %9s %12s\n",
							account1.getAccountName(), numberFormat(account1.getAccountNo()),
							account1.getMoney()+"원", account1.getBankName(), account1.getBankPhone());
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
			
				System.out.println("이 명령을 지원하지 않습니다.");
			
		}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("명령어 처리 중 오류발생.");
		}
		}
	}
		private static void doIO() {
			loop2 : while (true){
				System.out.println("1.입금");
				
				System.out.println("2.출금");
				
				System.out.println("3.나가기");
	
				System.out.print("메뉴를 입력해 주세요");
				
				int selectNo = scanner.nextInt();
				scanner.nextLine();
				
				if (selectNo == 1) {
					System.out.print("입금할 계좌를 입력해주세요");
				} else if (selectNo == 2) {
					System.out.print("출금할 계좌를 입력해주세요");
				} else if (selectNo == 3) {
					break;
				}
				
				
				else {
					System.out.println("잘못된 명령입니다.");
					
					selectNo = scanner.nextInt();
					continue loop2;
				}
				
				int accountNumber = Integer.parseInt(scanner.nextLine());
				acount account = list.get(searchAccount(accountNumber));
				
			}				
			
		};
		private static void doDelete() {
			System.out.print("삭제할 계좌번호를 입력해주세요");
	        int accountNumber = Integer.parseInt(scanner.nextLine());
	        
	        
	        acount account = list.get(searchAccount(accountNumber));
	        
	        System.out.print(account.getAccountName() +"-"+ account.getAccountNo() +" 를 삭제하시겠습니까?(y/n)");
	        if (scanner.nextLine().equalsIgnoreCase("y")) { 
	            
	            //잔액 조건
	            if(account.getMoney() == 0){
	                list.remove(searchAccount(accountNumber));
	                System.out.println("삭제하였습니다."); 
	            }else{
	                System.out.println("잔액이 0원 이상이면 계좌를 삭제할 수 없습니다.");
	            }
	        } else { 
	            System.out.println("삭제 취소하였습니다."); 
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
	        System.out.print("수정할 계좌번호를 입력해주세요");
	        int accountNumber = Integer.parseInt(scanner.nextLine());
	        
	        acount account = list.get(searchAccount(accountNumber));
	        acount tempAccount = account.clone();
	    
	         String text = null; 
	         System.out.printf("계좌번호(%s):", numberFormat(account.getAccountNo())); 
	           text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setAccountNo(Integer.parseInt(text)); 
	    
	        System.out.printf("계좌명(%s):", account.getAccountName()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setAccountName(text); 
	    
	        System.out.printf("은행이름(%s):", account.getBankName()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setBankName(text);  
	    
	        System.out.printf("은행번호(%d):", account.getBankPhone()); 
	        text = scanner.nextLine(); 
	        if (text.length() > 0) 
	          tempAccount.setBankPhone(text); 
	    
	        System.out.print("정말 변경하시겠습니까?(y/n)"); 
	        if (scanner.nextLine().equalsIgnoreCase("y")) { 
	    
	          list.set(searchAccount(accountNumber), tempAccount); 
	          System.out.println("변경하였습니다."); 
	        } else { 
	          System.out.println("변경 취소하였습니다."); 
	        } 
	    }

		
		
	

	private static String numberFormat(int accountNo) {
		NumberFormat numformat = NumberFormat.getIntegerInstance();
		numformat.setMinimumIntegerDigits(3);
	
		return numformat.format(accountNo);
	}

}

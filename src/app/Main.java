package app;
import java.util.Scanner;

import Modules.ChangeByte;
import Modules.DigitalSignature;

public class Main {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Hello Welcome to Digital Signature System.");
		int ch = 0;
		while(ch!=4){
			System.out.println("*******************MENU*******************");
			System.out.println("1. Send File");
			System.out.println("2. Receive File");
			System.out.println("3. Change Byte ");
			System.out.println("4. Exit");
			System.out.println();
			System.out.println("Please enter your choice (menu number): -");
			ch = scanner.nextInt();
			switch(ch){
			case 1:
				System.out.println("Please Enter the file name (Eg. Sample.txt): -");
				String filename = scanner.next();
				DigitalSignature.sendFile(filename);
				break;
			case 2:
				System.out.println("Please Enter the file name (Eg. Sample.txt): -");
				String file = scanner.next();
				DigitalSignature.receiveFile(file);
				break;
			case 3:
				System.out.println("Please Enter the file name (Eg. Sample.txt.signed): -");
				String f = scanner.next();
				ChangeByte.readFile(f);
				System.out.println("Please enter the index of the byte array (Eg. 1,2,3,...): -");
				int index = scanner.nextInt();
				if(ChangeByte.isIndexAppropriate(index)){
					ChangeByte.changeIndex(f,index);
				}
				else{
					System.out.println("Please specify a proper index.");
				}
				break;
			case 4:
				System.exit(1);
				break;
			}
			
		}
	}
}

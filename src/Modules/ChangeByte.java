package Modules;

import java.util.Random;

public class ChangeByte {
	
	// to read the file to tamper
	static MessageSigned ms = null;
	public static void readFile(String filename){ 
		 ms = (MessageSigned)FileOperations.read(filename);
		// file opened as a binary file
		System.out.println("File has been opened as a binary file...");
	}
	public static boolean isIndexAppropriate(int index) {
		// TODO Auto-generated method stub
		String content = ms.getContent();
		if(index<content.length())
			return true;
		else
			return false;
	}
	public static void changeIndex(String filename,int index) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		String content = ms.getContent();
		StringBuilder sBuilder = new StringBuilder(content);
		sBuilder.setCharAt(index, (char)rand.nextInt());
		
		//making objectStream
		MessageSigned tampered = new MessageSigned(ms.getSignature(),sBuilder.toString());
		FileOperations.write(filename, tampered);
		
		System.out.println("Whooaa! You just tampered the file.");
	}
}

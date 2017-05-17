package Modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FileOperations {
	public static void write(String file, Object object){
		
		try{
			FileOutputStream fos = new FileOutputStream("src/Files/"+file);
				
			ObjectOutputStream output = new ObjectOutputStream(fos);
			output.writeObject(object);
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found in writing.");

		}catch(IOException ie){
			System.out.println("IO Exception in writing");
		}
	}
	
	public static Object read(String file){
		Object object = null;
		try{
			FileInputStream fis = new FileInputStream("src/Files/"+file);
			ObjectInputStream input = new ObjectInputStream(fis);
			object = input.readObject();
			input.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found in reading.");
		}catch(IOException ie){
			System.out.println("IO Exception in reading");
			
		}catch(ClassNotFoundException cnfe){
			
		}
		return object;
		
	}

	public static byte[] processMessage(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file);
		String contents = "";
		while(scanner.hasNextLine()){
			contents+=scanner.nextLine();
		}
		scanner.close();
		try {
			MessageDigest digestInstance = MessageDigest.getInstance("MD5");
			byte[] contentsInBytes = contents.getBytes();
			digestInstance.update(contentsInBytes);
			return digestInstance.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("No Such Algorithm Found.");
		}
		return null;
	}
	
	public static String getContents(File file){
		Scanner scanner;
		String contents = "";
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				contents+=scanner.nextLine();
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return contents;
	}
}

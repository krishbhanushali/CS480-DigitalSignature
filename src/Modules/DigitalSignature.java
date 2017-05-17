package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DigitalSignature {
	public static void sendFile(String filename){
		
		
		try {
			//process Message into 16 bytes bytes array ( 128 bit)
			byte[] digest = FileOperations.processMessage(new File("src/Files/"+filename));
			
			// using sign/magnitude constructor for BigInteger, convert digest array into a single BigInteger
			BigInteger digestBI = new BigInteger(1,digest);
			
			//encrypt the BigInteger Digest
			KeyGen key = new KeyGen();
			key.printKeys();
			BigInteger signature = key.encrypt(digestBI);
			
			//write it to file.
			MessageSigned ms = new MessageSigned(signature,FileOperations.getContents(new File("src/Files/"+filename)));
			FileOperations.write(""+filename+".signed", ms);
			
			System.out.println("Message Sent.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static void receiveFile(String filename){
		MessageSigned ms = (MessageSigned)FileOperations.read(""+filename+".signed");
		String input = ms.getContent();
		KeyGen key = new KeyGen();
		
		try {
			MessageDigest m1 = MessageDigest.getInstance("MD5");
			byte[] inputByte = input.getBytes();
			m1.update(inputByte);
			byte[] digest = m1.digest();
			byte[] receiverDigest = key.decrypt(ms.getSignature()).toByteArray();
			receiverDigest = Arrays.copyOfRange(receiverDigest,1,receiverDigest.length);
			
			if(MessageDigest.isEqual(digest, receiverDigest)){
				System.out.println("Message is original");
			}
			else{
				System.out.println("Message is tampered");
			}
				
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
}

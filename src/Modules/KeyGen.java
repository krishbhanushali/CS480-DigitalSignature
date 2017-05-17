package Modules;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGen {
	
	public static KeyPair publicKey;
	public static KeyPair privateKey;
	static ModularArithmetic ma = new ModularArithmetic();
	public int count = 0;
	public KeyGen(){
		
		//generate the Keys
		File privFile = new File("src/Files/Privkey.rsa");
		File pubFile = new File("src/Files/Pubkey.rsa");
		if(privFile.length()==0 && pubFile.length()==0){
			generateKeys(512);
			//writing the Keys to the output file with ObjectOutputStream
			FileOperations.write("Privkey.rsa", privateKey);
			FileOperations.write("Pubkey.rsa", publicKey);
		}
		//reading keys from the file with ObjectInputStream
		privateKey = (KeyPair)FileOperations.read("Privkey.rsa");
		publicKey = (KeyPair)FileOperations.read("Pubkey.rsa");
		
	}
	
	public void printKeys() {
		
		System.out.println("e: -"+publicKey.getValue());
		System.out.println("d: -"+privateKey.getValue());
		System.out.println("n: -"+publicKey.getN());
		System.out.println();
		count = count + 1;
	}

	private void generateKeys(int n) {
		
		//declaration
		BigInteger p,q,N,e,d,totientFunctionOfN;
		Random rd = new SecureRandom();
		p = BigInteger.probablePrime(n, rd);
		q = BigInteger.ZERO;
		
		//finding Q to be a prime number other than P;
		q = findQ(p,n);
		
		// n = p * q
		N = p.multiply(q);
		
		// phi(n) = (p-1) * (q-1)
		totientFunctionOfN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		//  Pick e to be a random prime between 1 and ø(n), such that gcd(e, ø(n)) = . e should be similar in (bit) length to p and q, but does not have to be the same length.
		e = findE(totientFunctionOfN,n,rd);
		
		// d = e-1 mod phi(n)
		d = e.modInverse(totientFunctionOfN);
		
		// allocating key pair to public key
		publicKey = new KeyPair(e,N);
		
		//allocating key pair to private key
		privateKey = new KeyPair(d,N);
	}
	
	public BigInteger encrypt(BigInteger digest){
		return ma.modexp(digest, publicKey.getValue(), publicKey.getN());
	}
	
	public BigInteger decrypt(BigInteger digest){
		return ma.modexp(digest, privateKey.getValue(), privateKey.getN());
	}
	
	static BigInteger findQ(BigInteger p,int n){
		Random rd = new SecureRandom();
		BigInteger q = BigInteger.probablePrime(n, rd);
		if(q.compareTo(p)==0){
			return findQ(p, n);
		}
		else
			return q;
	}
	static BigInteger findE(BigInteger totientFunctionOfN, int n,Random rd){
		BigInteger e = BigInteger.probablePrime(n, rd);
		if(e.gcd(totientFunctionOfN).compareTo(BigInteger.ONE)==0 && e.compareTo(totientFunctionOfN)==-1){
			return e;
		}
		else
			return findE(totientFunctionOfN, n, rd);
	}

}

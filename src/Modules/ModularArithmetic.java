package Modules;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class ModularArithmetic {
	public BigInteger modadd(BigInteger a, BigInteger b,BigInteger N){
		BigInteger c = a.mod(N).add(b.mod(N)).mod(N);
		
		return (BigInteger) c;
	}
	
	public BigInteger modmult(BigInteger a, BigInteger b,BigInteger N) {

		BigInteger c = a.mod(N).multiply(b.mod(N)).mod(N);
		
		return (BigInteger) c;
		
	}
	public BigInteger moddiv(BigInteger a, BigInteger b,BigInteger N) {
		//BigInteger c = a.
		BigInteger c = a.mod(N).divide(b.mod(N)).mod(N);
		
		return (BigInteger) c;
		
	}
	public BigInteger modexp(BigInteger a, BigInteger b,BigInteger N) {

		BigInteger c = a.mod(N).modPow(b.mod(N), N).mod(N);
		
		return (BigInteger) c;
		
	}
	
	public boolean isPrime(BigInteger N, int k){
		Boolean b = false;
		b = N.isProbablePrime(k);
	if(b)	{
		return b; } else { return b; }
		
	}

	public BigInteger genPrime(int n){
		 Random rd = new SecureRandom();
		BigInteger N = BigInteger.probablePrime(n, rd);
		System.out.println(N.bitLength());
		return N;
	}

}

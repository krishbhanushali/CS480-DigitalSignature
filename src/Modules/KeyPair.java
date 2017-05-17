package Modules;
import java.io.Serializable;
import java.math.BigInteger;

public class KeyPair implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//class for KeyPair as in (e,n) and (d,n)
	private BigInteger value;
	private BigInteger n;
	
	public KeyPair(BigInteger value, BigInteger n){
		super();
		this.value = value;
		this.n = n;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}
	
	
}

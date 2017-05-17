package Modules;

import java.io.Serializable;
import java.math.BigInteger;

public class MessageSigned implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger signature;
	private String content;
	public MessageSigned(BigInteger signature, String content) {
		super();
		this.signature = signature;
		this.content = content;
	}
	public BigInteger getSignature() {
		return signature;
	}
	public void setSignature(BigInteger signature) {
		this.signature = signature;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

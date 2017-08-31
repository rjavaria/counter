package au.com.inteliment.counter;

import org.apache.tomcat.util.codec.binary.Base64;

public class Decoder {

	public static void main(String[] args) {
		
		final String[] userDetails = decode("b3B0dXM6Y2FuZGlkYXRlcw==");
		System.out.println("Decoded\t: username [" + userDetails[0] + "], password [" +  userDetails[1] + "]");
	}
	
	private static String[] decode(final String encodedString) {
	  final byte[] decodedBytes = Base64.decodeBase64(encodedString.getBytes());
	  final String pair = new String(decodedBytes);
	  final String[] userDetails = pair.split(":", 2);
	  return userDetails;
	}
}

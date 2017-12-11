package br.com.betha_project.jdbc;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.bind.DatatypeConverter;

public class Base64 {

	public static void main(String[] args) {

		String str = "77+9x6s=";
		System.out.println("original value is \t" + str);
        // encode data using BASE64
        str = DatatypeConverter.printBase64Binary(str.getBytes());
        System.out.println("Encode BASE64 \t" + str);
        // Decode data 
    	try {
    		// encode data using UTF-8
    		str = URLEncoder.encode(str, "UTF-8");
    		System.out.println("Encode UTF-8 \t " + str);
    		// decode data 
    		str = URLDecoder.decode(str, "UTF-8");
    		System.out.println("Decode UTF-8 \t " + str);
    		str = new String(DatatypeConverter.parseBase64Binary(str));
    		System.out.println("Decode BASE64 \t" + str);
		} catch (Exception e) {
			try {
				System.out.println("exce \t" + e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("exce e1 \t" + e1);
				e1.printStackTrace();
			}
		}
    	
    	
	
	}

}

package com.transport.rto.genrator;

import java.util.Random;
/**
 * this class used like a custom generator for our application 
 * to generate a reg. Number we use that class
 * here we use basic of  singleton design pattern
 * 
 * @author Rituraj
 *
 */
public class IdGenrator {

	private  IdGenrator() {}
	/**
	 *  return  vehicle number format string to applciation 
	 * @return String
	 */
	public static  String getRegNumber() {
		String str=	randomString(2)+randomInt()+randomString(3);
		return str;
	}
	/**
	 * use helper method for getRegNumber
	 * @return Integer
	 */
	private static Integer randomInt() {
		Random rd=new Random();
		int nextInt=0;
		while(true) {
			nextInt= rd.nextInt(99);
			if(nextInt>10) 
				break;
		}
		return nextInt;
	}	
	/**
	 * use helper method for getRegNumber
	 * @return String
	 */

	private static String randomString(Integer len) {
		Random rd=new Random();
		int nextString;
		String str1="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String str2="";
		while(true) {
			nextString=rd.nextInt(25);
				char charAt = str1.charAt(nextString);
					str2=str2+charAt;
				if(str2.length()==len)	
					break;
			}
		return str2;
	}
}

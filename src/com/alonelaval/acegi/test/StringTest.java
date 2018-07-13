package com.alonelaval.acegi.test;

import java.util.Date;

import java.util.Arrays;


/**
 * 
 * @author huawei
 * @createDate 2009-9-1 下午02:19:59	
 */
public class StringTest {
	public static void main(String[] args) {
		System.out.println(args[1]);
		Date date =  new Date();
		for (int i = 0; i < 6000*5; i++) {
			String string = new String(""+i);
		//	System.out.println(string);
		}
		System.out.println(System.currentTimeMillis()-date.getTime());
		
	}
}

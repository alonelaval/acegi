package com.alonelaval.acegi.test;


/**
 * 
 * @author huawei
 * @createDate 2009-7-2 下午09:43:21	
 */
public class FinallyTest {
	
	@SuppressWarnings("finally")
	public static String test(Boolean b){
		if(b){
			while(b){
				try{
					return "aaa";
				}finally{
					break;
				}
			}
		}
		return "bbb";
	}
	public static void main(String[] args) {
		
		System.out.println(test(Boolean.TRUE));
	}
}

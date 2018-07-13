package com.alonelaval.acegi.test;
/**
 * 
 * @author huawei
 * @createDate 2009-9-16 下午06:30:49	
 */
public class AppTest {
	public  void test1(String aa){
		System.out.println("aa");
	}
	public void test1(Object o){
		System.out.println("oo");
	}
	public void test1(StringBuffer sb){
		System.out.println("sb");
	}
	public static void main(String[] args) {
		AppTest test = new AppTest();
		test.test1(test);
	}
}

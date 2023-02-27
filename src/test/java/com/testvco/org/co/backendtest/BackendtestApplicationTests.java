package com.testvco.org.co.backendtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendtestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	public static void main(String[] args){
		int a = 25;
		int b = -25;
		int c = higher(a, b);
		System.out.println("the higher of 25 and -25 is " + c);
	}

	public static int higher(int x, int y){
		if(x > y){
			return x;
		} else {
			return y;
		}
	}



}

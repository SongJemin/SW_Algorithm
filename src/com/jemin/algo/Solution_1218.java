package com.jemin.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * SWEA 1218 [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
 *
 */

public class Solution_1218 {
	public static void main(String[] args) throws Exception {
		
		int inputLength;
		String inputString;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack st;
		
		for(int a=1; a<=10; a++) {
			st = new Stack();
			int result = 1;
			inputLength = Integer.parseInt(br.readLine());
			inputString = br.readLine();
			for(int i=0; i<inputLength; i++) {
				if(inputString.charAt(i) == '(' || inputString.charAt(i) == '['
						|| inputString.charAt(i) == '{' || inputString.charAt(i) == '<'  ) {
					st.push(inputString.charAt(i));
				}
				else {
					char value = inputString.charAt(i);
					if(st.isEmpty()) {
						result = 0;	// 유효하지 않음
						break;
					}
					else if(value == ')') {
						if((char)st.peek() == '(') {
							st.pop();
						}else {
							result = 0;	// 유효하지 않음
							break;
						}
					}
					else if(value == ']') {
						if((char)st.peek() == '[') {
							st.pop();
						}else {
							result = 0;	// 유효하지 않음
							break;
						}
					}
					else if(value == '}') {
						if((char)st.peek() == '{') {
							st.pop();
						}else {
							result = 0;	// 유효하지 않음
							break;
						}
					}
					else if(value == '>') {
						if((char)st.peek() == '<') {
							st.pop();
						}else {
							result = 0;	// 유효하지 않음
							break;
						}
					}
				}
			}
			System.out.println("#" + a + " " + result);
		}
	}
}

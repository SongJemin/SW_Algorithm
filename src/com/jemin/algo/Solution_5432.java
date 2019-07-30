package com.jemin.algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * SWEA 5432 쇠막대기 자르기
 *
 */

public class Solution_5432 {
	public static void main(String[] args) throws Exception{
		
		int inputCase;	
		String inputString;
		boolean leftFlag = false;
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack st = new Stack();
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			int sum = 0;
			inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++) {
				if(inputString.charAt(i) == '(') {	// 왼쪽 괄호 만나면
					st.push(i);	// 스택에 시작 위치 저장
					leftFlag = true;
				}
				else if(inputString.charAt(i) == ')') {
					if(leftFlag) { // 바로 직전이 '('이면
						// 레이저
						leftFlag = false;
						st.pop();
						sum += st.size();
					}else {	// 바로 직전이 ')'이면
						// 쇠막대
						leftFlag = false;
						st.pop();
						sum += 1;
					}
				}
			}
			System.out.println("#" + a + " " + sum);
		}
	}
}

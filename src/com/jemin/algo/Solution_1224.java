package com.jemin.algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1224 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String inputString;

		for(int a = 1; a <= 10; a++) {
			int inputLength = Integer.parseInt(br.readLine());
			inputString = br.readLine();			
			char[] operation = new char[inputLength];
			int[] stackNum = new int[inputLength];
			int top = -1;
			
			for (int i = 0; i < inputString.length(); i++) {
			
				char charValue = inputString.charAt(i);
				
				switch(charValue) {
				case '(' :
					operation[++top] = charValue;
					break;
				case '*' :
					while(top >= 0 && operation[top] == '*') {
						sb.append(operation[top--]);
					}
					operation[++top] = charValue;
					break;

				case '+' :
					while(top >=0 && operation[top] != '(') {
						sb.append(operation[top--]);
					}
					operation[++top] = charValue;
					break;
				
				case ')' :	// '(' 나올때까지 append
					while(top > 0 && operation[top] != '(') {
						sb.append(operation[top--]);
					}
					if(operation[top] == '(') {
						top --;
					}
					break;
								
				default :	// 숫자는 바로 append
					sb.append(charValue);
					break;
				}
			}
			while(top > -1) { // 스택에 남은 연산자 모두 append
				sb.append(operation[top--]);
			}
			
			// 계산
			for (int i = 0; i < sb.length(); i++) {
				String charValue = String.valueOf(sb.charAt(i));
				int num1, num2;
				switch(charValue) {
				
				case "+" :
					num2 = stackNum[top--];
					num1 = stackNum[top--];
					stackNum[++top] = num1 + num2;
					break;
					
				case "*" :
					num2 = stackNum[top--];
					num1 = stackNum[top--];
					stackNum[++top] = num1 * num2;
					break;
					
				default :
					stackNum[++top] = Integer.parseInt(charValue);
					break;
				}

			}
			
			// 계산 결과
			System.out.println("#" + a + " " + stackNum[top--]);
		}
		
	}
}

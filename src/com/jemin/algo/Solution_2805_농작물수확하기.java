package com.ssafy.day12.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputCase, inputSize;
		String inputString;
		int[][] arr;

		StringBuilder sb = new StringBuilder();
		
		inputCase = Integer.parseInt(br.readLine());
		for(int a=1; a<=inputCase; a++) {
			int sum = 0;
			inputSize = Integer.parseInt(br.readLine());
			arr = new int[inputSize][inputSize];
			
			// 값 입력
			for(int i=0; i<inputSize; i++) {
				inputString = br.readLine();
				for(int j=0; j<inputSize; j++) {
					arr[i][j] = inputString.charAt(j) - '0';
				}
			}
			
			// 값 찾기 (위쪽)
			for(int i=0; i<=inputSize/2; i++) {
				for(int j = (inputSize/2)-i; j <= (inputSize/2) + i; j++) {
					sum += arr[i][j];
				}
			}
			
			// 값 찾기(아래쪽)
			int count = 0;
			for(int i = (inputSize/2)+1; i < inputSize; i++) {
				for(int j = 1+count; j < (inputSize-1)-count; j++) {
					sum += arr[i][j];
				}
				count++;
			}
			sb.append("#" + a + " " + sum);
			System.out.println(sb);
		}
		
	}
}

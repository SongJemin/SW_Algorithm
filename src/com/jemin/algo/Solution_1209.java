package com.jemin.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1209 {
	
	public static void main(String[] args) throws Exception {
		
		int[][] arr;
		int[] rowArr;
		int[] colArr;
		int[] diagArr;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int a = 1; a <= 10; a++) {
			
			int testNum = Integer.parseInt(br.readLine());
			
			arr = new int[100][100];
			rowArr = new int[100];
			colArr = new int[100];
			diagArr = new int[2];
			int max = Integer.MIN_VALUE;
			
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					rowArr[i] += arr[i][j];
					colArr[j] += arr[i][j];
					if(i==j) {
						diagArr[0] += arr[i][j];
					}
					if((i+j) == 99) {
						diagArr[1] += arr[i][j];
					}
				}
			}
			
			for (int i = 0; i < 100; i++) {
				max = Math.max(max, rowArr[i]);
				max = Math.max(max, colArr[i]);
			}
			max = Math.max(max, diagArr[0]);
			max = Math.max(max, diagArr[1]);
			
			System.out.println("#" + testNum + " " + max);
		}
	}
}

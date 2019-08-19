package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247 {

	static Customer[] customArr;
	static int home_x, home_y, dest_x, dest_y;
	static int customNum;
	static int min;
	
	public static void main(String[] args) throws Exception {
		
		int inputCase;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		inputCase = Integer.parseInt(br.readLine());
		
		for(int a=1; a<=inputCase; a++) {
			
			min = Integer.MAX_VALUE;
			
			customNum = Integer.parseInt(br.readLine());
			customArr = new Customer[customNum];

			st = new StringTokenizer(br.readLine());
			
			home_x = Integer.parseInt(st.nextToken());
			home_y = Integer.parseInt(st.nextToken());
			dest_x = Integer.parseInt(st.nextToken());
			dest_y = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < customNum; i++) {
				customArr[i] = new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			perm(0);
			
			System.out.println("#" + a + " " + min);
		}
		
	}
	

	// 조합
	public static void perm(int n) {

		if(n == customArr.length) {
			int sum = 0;
			
			// 집에서 첫 손님 거리
			sum += Math.abs(home_x - customArr[0].x) + Math.abs(home_y - customArr[0].y);
			
			// 손님들 사이의 거리
			for(int i=0; i<customNum-1; i++) {
				sum += Math.abs(customArr[i].x - customArr[i+1].x) + Math.abs(customArr[i].y - customArr[i+1].y);
			}
			
			// 마지막 손님에서 도착지 거리
			sum += Math.abs(customArr[customNum-1].x - dest_x) + Math.abs(customArr[customNum-1].y - dest_y);
			
			if(min > sum) {
				min = sum;
			}
		}
		else {
			for(int i=n; i<customArr.length; i++) {
				Customer temp = customArr[i];
				customArr[i] = customArr[n];
				customArr[n] = temp;
				
				perm(n+1);
				
				temp = customArr[n];
				customArr[n] = customArr[i];
				customArr[i] =temp;
			}
		}
	}
	
	public static class Customer{
		int x;
		int y;

		public Customer(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}

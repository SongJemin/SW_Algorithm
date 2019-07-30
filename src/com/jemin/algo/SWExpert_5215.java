package com.jemin.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy
 * #5215 햄버거 다이어트 문제
 */

public class SWExpert_5215 {
	
	
	public static void main(String[] args) throws IOException {
		int[][] arr = new int[21][1001];
		int[] rating = new int[21];
		int[] cal = new int[21];
		int inputCase, totalMaterial, totalCal;
		String inputString;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		inputCase = Integer.parseInt(br.readLine());
		for(int i=0; i<inputCase; i++) {
			st = new StringTokenizer(br.readLine());
			totalMaterial = Integer.parseInt(st.nextToken());
			totalCal = Integer.parseInt(st.nextToken());
			
			for(int j=1; j<=totalMaterial; j++) {
				st = new StringTokenizer(br.readLine());
				rating[j] = Integer.parseInt(st.nextToken());
				cal[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int m=1; m<=totalMaterial; m++) {
				for(int n=1; n<=totalCal; n++) {
					System.out.print(n +"踰덉㎏");
					if(n < cal[m]) {
						arr[m][n] = arr[m-1][n];
						System.out.println("�뿬湲�1 = " + arr[m][n]);
					}
					else {
						arr[m][n] = Math.max(arr[m-1][n], arr[m-1][n-cal[m]] + rating[m]);
						System.out.println("�뿬湲�2 = " + arr[m][n]);
					}
				}
			}
			
			for(int m=1; m<=totalMaterial; m++) {
				for(int n=1; n<=totalCal; n++) {
					System.out.print(arr[m][n] + ", ");
				}
				System.out.println();
			}
			System.out.println("#" + (i+1) + " " + arr[totalMaterial][totalCal]);
		}
	}
}

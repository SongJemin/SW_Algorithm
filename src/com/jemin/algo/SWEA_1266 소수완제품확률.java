package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1266 {

	static int[][] C = new int[19][19];
	static double[] PercentA = new double[19];
	static double[] PercentB = new double[19];

	public static void main(String[] args) throws Exception {

		int inputCase;
		double skillA, skillB;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		inputCase = Integer.parseInt(br.readLine());

		// Á¶ÇÕ
		for (int i = 0; i <= 18; i++) {
			C[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				if (i == j) {
					C[i][j] = 1;
				} else {
					C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
				}
			}
		}

		for (int a = 1; a <= inputCase; a++) {
			st = new StringTokenizer(br.readLine());

			skillA = Integer.parseInt(st.nextToken()) * 0.01;
			skillB = Integer.parseInt(st.nextToken()) * 0.01;

			// 2°³ ¸¸µé È®·ü
			PercentA[2] = C[18][2] * Math.pow(skillA, 2) * Math.pow((1 - skillA), 16);
			PercentB[2] = C[18][2] * Math.pow(skillB, 2) * Math.pow((1 - skillB), 16);
			
			// 3°³ ¸¸µé È®·ü
			PercentA[3] = C[18][3] * Math.pow(skillA, 3) * Math.pow((1 - skillA), 15);
			PercentB[3] = C[18][3] * Math.pow(skillB, 3) * Math.pow((1 - skillB), 15);

			// 5°³ ¸¸µé È®·ü
			PercentA[5] = C[18][5] * Math.pow(skillA, 5) * Math.pow((1 - skillA), 13);
			PercentB[5] = C[18][5] * Math.pow(skillB, 5) * Math.pow((1 - skillB), 13);

			// 7°³ ¸¸µé È®·ü
			PercentA[7] = C[18][7] * Math.pow(skillA, 7) * Math.pow((1 - skillA), 11);
			PercentB[7] = C[18][7] * Math.pow(skillB, 7) * Math.pow((1 - skillB), 11);

			// 11°³ ¸¸µé È®·ü
			PercentA[11] = C[18][11] * Math.pow(skillA, 11) * Math.pow((1 - skillA), 7);
			PercentB[11] = C[18][11] * Math.pow(skillB, 11) * Math.pow((1 - skillB), 7);

			// 13°³ ¸¸µé È®·ü
			PercentA[13] = C[18][13] * Math.pow(skillA, 13) * Math.pow((1 - skillA), 5);
			PercentB[13] = C[18][13] * Math.pow(skillB, 13) * Math.pow((1 - skillB), 5);

			// 17°³ ¸¸µé È®·ü
			PercentA[17] = C[18][17] * Math.pow(skillA, 17) * Math.pow((1 - skillA), 1);
			PercentB[17] = C[18][17] * Math.pow(skillB, 17) * Math.pow((1 - skillB), 1);

			double sumA = 0.0;
			double sumB = 0.0;
			for (int i = 0; i <= 18; i++) {
				sumA += PercentA[i];
				sumB += PercentB[i];
			}
			
			double sum = sumA + sumB;
			double result = sum - (sumA * sumB);

			System.out.println("#" + a + " " + String.format("%.6f", result));
		}
	}
}

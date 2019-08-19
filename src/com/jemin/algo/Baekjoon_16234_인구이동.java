package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	<�Ｚ ���� ������>
 * 	Baekjoon_16234 �α��̵�
 * 	writer : Song Jemin
 */

public class Baekjoon_16234 {

	static int[] moveX = {1, 0, -1, 0};		// ��, ��, ��, ��
	static int[] moveY = {0, 1, 0, -1};		// ��, ��, ��, ��
	
	static Queue<Pair> que = new LinkedList<>();
	static int[][] arr;
	static boolean[][] checkArr;
	static boolean openFlag;
	static int sum, region, divResult;
	static int inputSize, minStand, maxStand, count;
	static int result = -1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		inputSize = Integer.parseInt(st.nextToken());
		minStand = Integer.parseInt(st.nextToken());
		maxStand = Integer.parseInt(st.nextToken());
		
		arr = new int[inputSize][inputSize];

		// �� �Է�
		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ���漱 �ȿ��� ������ �ݺ�
		do {
			// ���++
			result++;
			openFlag = false;
			checkArr = new boolean[inputSize][inputSize];
			
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					// �湮���� ���� ���̸�
					if(!checkArr[i][j]) {
						sum = 0;
						region = 0;
						divResult = 0;
						// �湮 O�� ����
						checkArr[i][j] = true;
						// ������ ������ �����Ϸ� dfs ����
						dfs(i, j);
						// dfs�� ���ؼ� ���� ���� / �̵������� ��� ��
						divResult = sum / region;
						// �̵� ������ ��� ���� 2�� �̻��� ���
						if(que.size() > 1) {
							// ť�� ����� �����鿡 �ش��ϴ� �α��� �缳��
							bfs();
							openFlag = true;
						}
						// �̵� ������ ��� 1���� ��� ť ����
						else {
							que.clear();
						}
					}
				}
			}
			
		}while(openFlag);
		
		System.out.println(result);
	}
	
	static public void dfs(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		
		que.add(new Pair(x, y));
		sum += arr[x][y];
		region++;
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX >= 0 && nextX < inputSize && nextY >= 0 && nextY < inputSize) {
				// �湮���� ���� ���̶��
				if(!checkArr[nextX][nextY] ) {
					// �α��� ���̰� �ּڰ��� �ִ� ������ ���
					if(Math.abs(arr[inputX][inputY] - arr[nextX][nextY]) >= minStand
							&& Math.abs(arr[inputX][inputY] - arr[nextX][nextY]) <= maxStand) {
						// �湮O�� ����
						checkArr[nextX][nextY] = true;
						// ���� ������ ������
						dfs(nextX, nextY);
					}
				}
			}
		}
	}
	
	static public void bfs() {

		while(!que.isEmpty()) {
			
			Pair pair = que.remove();
			int x = pair.x;
			int y = pair.y;
			
			// ��հ����� �缳��
			arr[x][y] = divResult;
		}
		
	}
	
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
}

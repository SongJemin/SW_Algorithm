package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	<삼성 기출 문제집>
 * 	Baekjoon_16234 인구이동
 * 	writer : Song Jemin
 */

public class Baekjoon_16234 {

	static int[] moveX = {1, 0, -1, 0};		// 하, 우, 상, 좌
	static int[] moveY = {0, 1, 0, -1};		// 하, 우, 상, 좌
	
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

		// 값 입력
		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 국경선 안열릴 때까지 반복
		do {
			// 결과++
			result++;
			openFlag = false;
			checkArr = new boolean[inputSize][inputSize];
			
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					// 방문하지 않은 곳이면
					if(!checkArr[i][j]) {
						sum = 0;
						region = 0;
						divResult = 0;
						// 방문 O로 변경
						checkArr[i][j] = true;
						// 인접한 국가들 조사하러 dfs 시작
						dfs(i, j);
						// dfs를 통해서 얻은 총합 / 이동가능한 장소 수
						divResult = sum / region;
						// 이동 가능한 장소 수가 2곳 이상일 경우
						if(que.size() > 1) {
							// 큐에 저장된 국가들에 해당하는 인구수 재설정
							bfs();
							openFlag = true;
						}
						// 이동 가능한 장소 1곳일 경우 큐 비우기
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
				// 방문하지 않은 곳이라면
				if(!checkArr[nextX][nextY] ) {
					// 인구수 차이가 최솟값과 최댓값 사이일 경우
					if(Math.abs(arr[inputX][inputY] - arr[nextX][nextY]) >= minStand
							&& Math.abs(arr[inputX][inputY] - arr[nextX][nextY]) <= maxStand) {
						// 방문O로 변경
						checkArr[nextX][nextY] = true;
						// 인접 국가들 재조사
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
			
			// 평균값으로 재설정
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

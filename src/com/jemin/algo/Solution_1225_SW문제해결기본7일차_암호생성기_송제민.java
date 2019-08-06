package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1225 {
	
	public static void main(String[] args) throws Exception{
		int[] que;
		int inputCase;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int a=1; a<=10; a++) {
			StringBuilder sb = new StringBuilder();
			que = new int[9];
			
			// 원형 큐 front, rear 0으로 초기화
			int rear = 0;
			int front = 0;
			
			// 감소할 sub 값 1로 초기화
			int sub = 1;
			inputCase = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			// 입력값 전부 원형 큐에 저장
			while(st.hasMoreTokens()) {
				int inputNum = Integer.parseInt(st.nextToken());
				que[++rear] = inputNum;
			}
			
			// 0보다 이하 나올때까지 무한 루프
			while(true) {
				rear = (rear+1) % que.length;
				front = (front+1) % que.length;
				que[rear] = que[front] - sub;
				
				if(que[rear] <= 0) {
					que[rear] = 0;
					
					while(front != rear) {
						front = (front+1) % que.length;
						sb.append(" " + que[front]);
					}
					break;
				}
				
				sub = sub + 1;
				if(sub > 5) {
					sub = 1;
				}
			}

			System.out.println("#" + a + sb);
		}
	}
}

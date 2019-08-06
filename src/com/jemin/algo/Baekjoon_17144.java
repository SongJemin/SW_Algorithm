package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static Queue<Pair> que = new LinkedList<Pair>();
	static int[] dx = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dy = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int inputRow, inputCol, inputTime;
	static int clearTopRow;	// 청정기의 윗쪽 x좌표
	static int[][] arr;
	// 회전시 들어갈 임시로 저장할 데이터 변수
	static int temp1 = 0;
	static int temp2 = 0;
	static int temp3 = 0;
	static int temp4 = 0;
	
	public static void main(String[] args) throws Exception {
			
		String inputString;
		boolean clearTopSearchFlag = false;	// 청정기 위쪽 위치 만나면 바로 true
		int sum = 0;	// 합  0 초기화
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inputString = br.readLine();
		StringTokenizer st = new StringTokenizer(inputString);
		
		inputRow = Integer.parseInt(st.nextToken());
		inputCol = Integer.parseInt(st.nextToken());
		inputTime = Integer.parseInt(st.nextToken());

		arr = new int[inputRow][inputCol];
		
		// 배열에 값 입력
		for (int i = 0; i < inputRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputCol; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 공기청정기일 경우 && 처음 -1 확인
				if(arr[i][j] == -1 && !clearTopSearchFlag) {
					clearTopRow = i;	// 공기청정기 상단부 x좌표 저장, y는 무조건 0=> 공기청정기 (x, 0)
					clearTopSearchFlag = true;
				}
			}
		}
			
		for(int t=0; t<inputTime; t++) {
			// 데이터 임시로 저장할 변수 0 초기화
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			temp4 = 0;

			for (int i = 0; i < inputRow; i++) {
				for (int j = 0; j < inputCol; j++) {				
					// 미세먼지 위치이면
					if(arr[i][j] != 0 && arr[i][j] != -1) {
						// 큐에 저장
						que.add(new Pair(i, j, arr[i][j]));
					}
				}
			}
			// 미세먼지 확산
			spread();
			// 사이클 회전
			cycle();
			
			for (int i = 0; i < inputRow; i++) {

				for (int j = 0; j < inputCol; j++) {
					if(t == inputTime-1){
						sum += arr[i][j];
					}
				}
			}
		}	
		// 결과값
		System.out.println(sum+2);
		
	}
	
	// 회전 사이클 메서드
	public static void cycle() {
		// 맨 오른쪽 사라지는 데이터 임시 저장(위 & 아래 사이클)
		temp1 = arr[clearTopRow][inputCol-1];
		temp3 = arr[clearTopRow+1][inputCol-1];
		// 오른쪽 맨끝으로 이동(사이클 1)
		for(int s=inputCol-1; s>=2; s--) {
			arr[clearTopRow][s] = arr[clearTopRow][s-1];			
			arr[clearTopRow+1][s] = arr[clearTopRow+1][s-1];
		}

		// 오른쪽 맨 끝 위 데이터 임시 저장(위쪽 사이클) && 오른쪽 맨 끝 아래 데이터 임시 저장(아래쪽 사이클)
		temp2 = arr[0][inputCol-1];
		temp4 = arr[inputRow-1][inputCol-1];		
		// 오른쪽 맨 끝 위로 올라가는부분(위쪽  && 사이클2)
		for(int s = 0; s <= clearTopRow-2; s++) {
			arr[s][inputCol-1] = arr[s+1][inputCol-1];
		}
		arr[clearTopRow-1][inputCol-1] = temp1;
		temp1 = temp2;		
		// 오른쪽 맨 끝 아래로 내려가는부분 (아래쪽 && 사이클2)
		for (int s = inputRow-1; s >= clearTopRow+3; s--) {
			arr[s][inputCol - 1] = arr[s-1][inputCol-1];
		}
		arr[clearTopRow+2][inputCol-1] = temp3;
		temp3 = temp4;
	
		// 왼쪽 맨 위 데이터 임시 저장(위쪽 사이클) && 왼쪽 맨 아래 데이터 임시 저장(아래쪽 사이클)
		temp2 = arr[0][0];
		temp4 = arr[inputRow-1][0];		
		// 왼쪽 맨 끝으로 움직 움직이기(사이클3)
		for(int s = 0; s <= inputCol-3; s++) {
			arr[0][s] = arr[0][s+1];			
			arr[inputRow-1][s] = arr[inputRow-1][s+1];
		}
		arr[0][inputCol-2] = temp1;
		arr[inputRow-1][inputCol-2] = temp3;		
		temp1 = temp2;
		temp3 = temp4;
		

		// 청정기로 들어가기
		// 위쪽 사이클4(왼쪽 아래로)
		for(int s = clearTopRow-1; s >= 2; s--) {
			arr[s][0] = arr[s-1][0];
		}

		// 아래쪽 사이클4(왼쪽 위로)
		for(int s = clearTopRow+2; s <= inputRow-3; s++) {
			arr[s][0] = arr[s+1][0];
		}
		arr[1][0] = temp1;
		arr[inputRow-2][0] = temp3;
		
		// 청정기 바로 옆은 0
		arr[clearTopRow][1] = 0;
		arr[clearTopRow+1][1] = 0;							
	}
	
	// 미세먼지 확산 메서드
	public static void spread() {
		while(!que.isEmpty()) {
			Pair pair = que.remove();
			int x = pair.x;
			int y = pair.y;
			int value = pair.value;
			
			int count = 0;
			for(int k=0; k<4; k++) {
				int nextX = x + dx[k];
				int nextY = y + dy[k]; 
				if(nextX >= 0 && nextX < inputRow && nextY >= 0 && nextY < inputCol && arr[nextX][nextY] != -1) {
					arr[nextX][nextY] += value / 5;	 // 자신의 1/5값 넘어감
					count++;	// 방향 개수++
				}
			}
			arr[x][y] -= (value / 5) * count; // 자신의 값 변경
		}		
	}
	
	static class Pair{
		int x;
		int y;
		int value;
		
		Pair(int x, int y, int value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
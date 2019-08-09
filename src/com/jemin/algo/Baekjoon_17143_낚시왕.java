package com.jem.report;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkKill {
	
	static Shark arr[][];
	static int inputSharkNum, inputRow, inputCol;
	static Queue<Shark> que = new LinkedList<>();
	static int[] moveX = {-1, 1, 0, 0};	// ��, ��, ��, ��
	static int[] moveY = {0, 0, 1, -1};	// ��, ��, ��, ��
	static boolean flag;
	static int sum = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		inputRow = Integer.parseInt(st.nextToken());
		inputCol = Integer.parseInt(st.nextToken());
		inputSharkNum = Integer.parseInt(st.nextToken());
		
		arr = new Shark[inputRow][inputCol];
		
		// ��� ������ ��� �迭�� �Է�
		for(int i=0; i<inputSharkNum; i++){
			st = new StringTokenizer(br.readLine());

			int shark_x = Integer.parseInt(st.nextToken()) - 1;
			int shark_y = Integer.parseInt(st.nextToken()) - 1;
			
			int shark_speed = Integer.parseInt(st.nextToken());
			// 1:��, 2:�Ʒ�, 3:������, 4:���� 
			int shark_direction = Integer.parseInt(st.nextToken());
			int shark_size = Integer.parseInt(st.nextToken());
			
			arr[shark_x][shark_y] = new Shark(shark_x, shark_y, shark_speed, shark_direction, shark_size);
		}
		
		// ���ÿ��� �� ���ʿ��� �� �����ʱ��� ���� �Ÿ���ŭ �ݺ�
		for(int t=0; t<inputCol; t++){
			
			flag = false;
			
			for(int i=0; i<inputRow; i++){
				for(int j=0; j<inputCol; j++){
					// ����~
					if(j == t && !flag && arr[i][j] != null){
						flag = true;	// ���� �õ� flag
						sum += arr[i][j].size;
						arr[i][j] = null;
						continue;
					}
					if(arr[i][j] != null){
						// ť�� �߰�
						que.add(arr[i][j]);
						arr[i][j] = null;
					}
				}
			}
				
			// ť ����
			while(!que.isEmpty()){
				Shark shark = que.remove();
				int x = shark.x;
				int y = shark.y;
				int speed = shark.speed;
				int direction = shark.direction;
				int size = shark.size;

				for(int i=0; i<speed; i++){
					
					// ����
					if(direction == 1){
						x += moveX[0];
						y += moveY[0];
						// �� ������ ���
						if(x == -1){
							x = 1;
							direction = 2;
						}				
					}
					// �Ʒ���
					else if(direction == 2){
						x += moveX[1];
						y += + moveY[1];
						// �� �Ʒ����� ���
						if(x == inputRow){
							x = inputRow-2;
							direction = 1;
						}	
					}
					// ����������
					else if(direction == 3){
						x += moveX[2];
						y += moveY[2];
						// �� �������̾��� ���
						if(y == inputCol){
							y = inputCol-2;
							direction = 4;
						}	
					}
					// ��������
					else {
						x += moveX[3];
						y += moveY[3];
						// �� �����̾��� ���
						if(y == -1){
							y = 1;
							direction = 3;
						}
					}
					
				}
				
				// �̹� ��� ������ ��� ũ�� ��
				if(arr[x][y] != null){
					if(arr[x][y].size < size){
						arr[x][y].speed = speed;
						arr[x][y].direction = direction;
						arr[x][y].size = size;
					}
				}
				// �� �������� �ʴ� �����̶��
				else{
					// ������ �߰�
					arr[x][y] = new Shark(x, y, shark.speed, direction, shark.size);
				}
			}
		}
		System.out.println(sum);
	}

	static public class Shark{
		int x;
		int y;
		int speed;
		int direction;
		int size;
		
		public Shark(int x, int y, int speed, int direction, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
}

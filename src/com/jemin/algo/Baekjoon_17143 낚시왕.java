package hw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fishing {
	
	static Shark arr[][];
	static int inputSharkNum, inputRow, inputCol;
	static Queue<Shark> que = new LinkedList<>();
	static int[] moveX = {-1, 1, 0, 0};	// 상, 하, 우, 좌
	static int[] moveY = {0, 0, 1, -1};	// 상, 하, 우, 좌
	static boolean flag;
	static int sum = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		inputRow = Integer.parseInt(st.nextToken());
		inputCol = Integer.parseInt(st.nextToken());
		inputSharkNum = Integer.parseInt(st.nextToken());
		
		arr = new Shark[inputRow][inputCol];
		
		// 상어 데이터 상어 배열에 입력
		for(int i=0; i<inputSharkNum; i++){
			st = new StringTokenizer(br.readLine());

			int shark_x = Integer.parseInt(st.nextToken()) - 1;
			int shark_y = Integer.parseInt(st.nextToken()) - 1;
			
			int shark_speed = Integer.parseInt(st.nextToken());
			// 1:위, 2:아래, 3:오른쪽, 4:왼쪽 
			int shark_direction = Integer.parseInt(st.nextToken());
			int shark_size = Integer.parseInt(st.nextToken());
			
			arr[shark_x][shark_y] = new Shark(shark_x, shark_y, shark_speed, shark_direction, shark_size);
		}
		
		// 낚시왕이 맨 왼쪽에서 맨 오른쪽까지 가는 거리만큼 반복
		for(int t=0; t<inputCol; t++){
			
			flag = false;
			
			for(int i=0; i<inputRow; i++){
				for(int j=0; j<inputCol; j++){
					// 낚시~
					if(j == t && !flag && arr[i][j] != null){
						flag = true;	// 낚시 시도 flag
						sum += arr[i][j].size;
						arr[i][j] = null;
						continue;
					}
					if(arr[i][j] != null){
						// 큐에 추가
						que.add(arr[i][j]);
						arr[i][j] = null;
					}
				}
			}
				
			// 큐 실행
			while(!que.isEmpty()){
				Shark shark = que.remove();
				int x = shark.x;
				int y = shark.y;
				int speed = shark.speed;
				int direction = shark.direction;
				int size = shark.size;
				
				if(direction == 1 || direction == 2) {
					for(int i=0; i<speed % (inputRow * 2 -2); i++){
						// 위로
						if(direction == 1){
							x += moveX[0];
							y += moveY[0];
							// 맨 위였을 경우
							if(x == -1){
								x = 1;
								direction = 2;
							}				
						}
						// 아래로
						else if(direction == 2){
							x += moveX[1];
							y += + moveY[1];
							// 맨 아래였을 경우
							if(x == inputRow){
								x = inputRow-2;
								direction = 1;
							}	
						}
					}
				}
				else {
					for(int i=0; i<speed % (inputCol * 2 -2); i++){
						// 오른쪽으로
						if(direction == 3){
							x += moveX[2];
							y += moveY[2];
							// 맨 오른쪽이었을 경우
							if(y == inputCol){
								y = inputCol-2;
								direction = 4;
							}	
						}
						// 왼쪽으로
						else {
							x += moveX[3];
							y += moveY[3];
							// 맨 왼쪽이었을 경우
							if(y == -1){
								y = 1;
								direction = 3;
							}
						}
					
					}				
				}	
						
				
				// 이미 상어 존재할 경우 크기 비교
				if(arr[x][y] != null){
					if(arr[x][y].size < size){
						arr[x][y].speed = speed;
						arr[x][y].direction = direction;
						arr[x][y].size = size;
					}
				}
				// 상어가 존재하지 않는 구역이라면
				else{
					// 데이터 추가
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
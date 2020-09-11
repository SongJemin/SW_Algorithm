package kakao_summer_2020;

import java.util.*;

public class Kakao_Summer_4 {
	
	static int[] moveX = {-1, 1, 0, 0};
	static int[] moveY = {0, 0, -1, 1};
	static int[][] map;
	static int[][] cost;
	static int R, C;
	static int min = Integer.MAX_VALUE;
	
	static Queue<Pair> que = new LinkedList<Pair>();
	
	public static void main(String[] args) {
		
		int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
		int result = solution(board);
		
		System.out.println(result);
	}
	
    public static int solution(int[][] board) {
        map = board;
        
        R = board.length;
        C = board[0].length;
        cost = new int[R][C];
        que.add(new Pair(0, 0, 0, 0));
        
        bfs();
        
        return min;
    }
    
    public static void bfs() {
    	int time = 0;
    	
    	while(!que.isEmpty()) {
    		
    		Pair pair = que.poll();
    		int x = pair.x;
    		int y = pair.y;
    		int direction = pair.direction;
    		int score = pair.score;
    		
    		if((x == (R-1)) && (y == (C-1))) {
    			min = Math.min(min, score);
    			break;
    		}
    		
    		for (int i = 0; i < 4; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];
				
				if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C) {
					if(map[nextX][nextY] != 1) {
						// È¸Àü
						if(direction == i || time == 0) {
							score += 100;
						}
						else {
							score += 600;
						}
						
						if(cost[nextX][nextY] == 0) {
							cost[nextX][nextY] = score;
							que.add(new Pair(nextX, nextY, i, score));
						}
						else {
							if(cost[nextX][nextY] > score) {
								cost[nextX][nextY] = score;
								que.add(new Pair(nextX, nextY, i, score));
							}
						}
					}
				}
				score = pair.score;
			}
    		time++;
    	}
    }
    
    public static class Pair {
    	int x, y, direction, score;

		public Pair(int x, int y, int direction, int score) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.score = score;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", direction=" + direction + ", score=" + score + "]";
		}
    }
}

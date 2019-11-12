package asd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution_1953_탈주범검거 {
 
    static int[] moveX = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
    static int[] moveY = { 0, 0, -1, 1 }; // 상, 하, 좌, 우
    static int[][] map;
    static boolean[][] visited;
    static Queue<Hole> que;
    static int R, C, T;
    static int cnt;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int inputCase = Integer.parseInt(br.readLine());
        int holeX, holeY;
        map = new int[50][50];
        visited = new boolean[50][50];
        
        for (int t = 1; t <= inputCase; t++) {
            que = new LinkedList<>();
            st = new StringTokenizer(br.readLine(), " ");
 
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
 
 
            holeX = Integer.parseInt(st.nextToken());
            holeY = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
             
         
            que.add(new Hole(holeX, holeY, 1));

            
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = false;
                }
            }
            // 1초이면 그냥 1 반환
            if(T == 1) {
                sb.append("#" + t + " " + 1 + "\n");
                continue;
            }
 
            visited[holeX][holeY] = true;
            cnt = 0;
            bfs();
             
            sb.append("#" + t + " " + cnt + "\n");
        }
        System.out.println(sb);
    }
 
    public static void bfs() {
         
        while(!que.isEmpty()) {
            Hole hole = que.remove();
             
            int x = hole.x;
            int y = hole.y;
            int time = hole.time;
            int val = map[x][y];
            cnt += 1;
            if(time == T) continue;
             
            int nextX, nextY;
            // 상, 하
            if (val == 2) {
                for (int i = 0; i < 2; i++) {
                    nextX = x + moveX[i];
                    nextY = y + moveY[i];
                    if(checkMove(nextX, nextY, i)) que.add(new Hole(nextX, nextY, time+1));
                }
            }
            // 좌, 우
            else if (val == 3) {
                for (int i = 2; i < 4; i++) {
                    nextX = x + moveX[i];
                    nextY = y + moveY[i];
                    if(checkMove(nextX, nextY, i)) que.add(new Hole(nextX, nextY, time+1));
                }
            }
            // 상, 우
            else if (val == 4) {
                // 상
                nextX = x + moveX[0];
                nextY = y + moveY[0];
                if(checkMove(nextX, nextY, 0)) que.add(new Hole(nextX, nextY, time+1));
                 
                // 우
                nextX = x + moveX[3];
                nextY = y + moveY[3];
                if(checkMove(nextX, nextY, 3)) que.add(new Hole(nextX, nextY, time+1));
            }
            // 하, 우
            else if (val == 5) {
                // 하
                nextX = x + moveX[1];
                nextY = y + moveY[1];
                if(checkMove(nextX, nextY, 1)) que.add(new Hole(nextX, nextY, time+1));
                 
                // 우
                nextX = x + moveX[3];
                nextY = y + moveY[3];
                if(checkMove(nextX, nextY, 3)) que.add(new Hole(nextX, nextY, time+1));
            }
            // 하, 좌
            else if (val == 6) {
                // 하
                nextX = x + moveX[1];
                nextY = y + moveY[1];
                if(checkMove(nextX, nextY, 1)) que.add(new Hole(nextX, nextY, time+1));
 
                 
                // 좌
                nextX = x + moveX[2];
                nextY = y + moveY[2];
                if(checkMove(nextX, nextY, 2)) que.add(new Hole(nextX, nextY, time+1));
                 
            }
            // 상, 좌
            else if (val == 7) {
                // 상
                nextX = x + moveX[0];
                nextY = y + moveY[0];
                if(checkMove(nextX, nextY, 0)) que.add(new Hole(nextX, nextY, time+1));
                 
                // 좌
                nextX = x + moveX[2];
                nextY = y + moveY[2];
                if(checkMove(nextX, nextY, 2)) que.add(new Hole(nextX, nextY, time+1));
            }
            // 상, 하, 좌, 우
            else {
                for (int i = 0; i < 4; i++) {
                    nextX = x + moveX[i];
                    nextY = y + moveY[i];
                    if(checkMove(nextX, nextY, i)) que.add(new Hole(nextX, nextY, time+1));
                }
            }
        }
    }
     
    public static boolean checkMove(int x, int y, int d) {
         
        if(x >= 0 && x < R && y >= 0 && y < C) {
            if(!visited[x][y]) {
                // 상
                if(d == 0) {
                	 int nextVal = map[x][y];
                     if(nextVal == 1 || nextVal == 2 || nextVal == 5 || nextVal == 6) {
                         visited[x][y] = true;
                         return true;
                     }
                }
                // 하
                else if(d == 1) {
                    int nextVal = map[x][y];
                    if(nextVal == 1 || nextVal == 2 || nextVal == 4 || nextVal == 7) {
                        visited[x][y] = true;
                        return true;
                    }
                }
                // 좌
                else if(d == 2) {
                    int nextVal = map[x][y];
                    if(nextVal == 1 || nextVal == 3 || nextVal == 4 || nextVal == 5) {
                        visited[x][y] = true;
                        return true;
                    }
                }
                // 우
                else {
                    int nextVal = map[x][y];
                    if(nextVal == 1 || nextVal == 3 || nextVal == 6 || nextVal == 7) {
                        visited[x][y] = true;
                        return true;
                    }
                }
            }
            return false;
        }
        else return false;
    }
 
    public static class Hole {
        int x;
        int y;
        int time;
 
        public Hole(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
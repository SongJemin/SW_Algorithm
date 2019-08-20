import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Ladder1 {
     
    static int[] dx = {-1, 0, 0};   // 상, 좌, 우
    static int[] dy = {0, -1, 1};   // 상, 좌, 우
    static int[][] arr;
    static int startLoc;
    static int count;
     
    public static void main(String[] args) throws Exception {
//      String name = JOptionPane.showInputDialog("이름을 입력하세요");
         
        // 자바 초기버전부터 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringTokenizer st;
         
        for(int a=1; a<=10; a++) {
            count = a;
            int testNum = Integer.parseInt(br.readLine());
            arr = new int[100][100];
             
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine()," ");    // 구분자를 지정하는 것이 더 빠르다.
                for (int j = 0; j < 100; j++) {
                    // 값 입력
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 2) {
                        // 시작 값 설정
                        startLoc = j;
                    }
                }
            }
            // 사다리타기 시작
            go(99, startLoc, 0);            
        }
         
    }
     
    static void go(int currentX, int currentY, int direction) {
         
        arr[currentX][currentY] = 0;
         
        if(currentX == 0) {
            System.out.println("#" + count + " " + currentY);
        }
        else {
            if(direction == 0) {
                int count = 0;
 
                // 양 옆 체크
                for(int i=1; i<3; i++) {
                    int nextY = currentY + dy[i];
                    if(nextY >= 0 && nextY < 100) {
                        if(arr[currentX][nextY] == 1) {
                            count++;
                            // 꺾기
                            go(currentX, nextY, i);
                        }
                    }               
                }
                if(count == 0) {
                    // 계속 위로 직진
                    go(currentX-1, currentY, 0);
                }           
            }
            // 왼쪽으로 가는 경우
            else if(direction == 1) {
                // 맨 왼쪽 블럭 또는 왼쪽 블럭값이 0
                if(currentY == 0 || arr[currentX][currentY-1] == 0) {
                    // 위로 꺾기
                    go(currentX-1, currentY, 0);
                }
                else {
                    // 계속 왼쪽으로
                    go(currentX, currentY-1, 1);
                }
            }
            // 오른쪽으로 가는 경우
            else if(direction == 2) {
                // 맨 오른쪽 블럭 또는 오른쪽 블럭값이 0
                if(currentY == 99 || arr[currentX][currentY+1] == 0) {
                    // 위로 꺾기
                    go(currentX-1, currentY, 0);
                }
                else {
                    // 계속 오른쪽으로
                    go(currentX, currentY+1, 2);
                }
            }
        }       
    }
}

package com.jemin.algo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 	JUNGOL_1809 탑
 * 	각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 알고리즘 문제 
 * 	스택 문제
 */
public class Jongol_1809 {

    public static void main(String[] args) throws Exception{

        String inputStringTop;
        int count = 0;
        int insertTopHeight;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       
        Top[] stack;

        int inputTopCase = Integer.parseInt(br.readLine());
        
        stack = new Top[inputTopCase];
        int top = -1;
        inputStringTop = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStringTop);
        StringBuilder sb = new StringBuilder();
        
        // 첫 번째 탑
        insertTopHeight = Integer.parseInt(st.nextToken());
        stack[++top] = new Top(++count, insertTopHeight); // 위치 : 1, 높이 : 입력한 값의 탑데이터 push
        sb.append(0 + " "); // 무조건 0

        // 두번째 ~ 마지막 탑 반복문
        for(int i = 1; i < inputTopCase; i++){
            insertTopHeight = Integer.parseInt(st.nextToken());

            // 입력 위치 기준 왼쪽에 있는 모든 탑들(스택에 남아있는)과 높이 비교
            for(int j = top; j >= 0; j--){
            	// 왼쪽 탑 높이 < 현재 기준 탑 높이
                if(stack[j].height < insertTopHeight){
                	top--;	// 왼쪽 탑 위치로
                	// 왼쪽 탑의 위치가 첫 번째탑(스택에 남아있는)일 경우
                	if(j == 0){
                      sb.append(0 + " ");	// 막는 탑이 없으므로 0값 append
                      break;
                    }
                }
                // 왼쪽 탑 높이 > 현재 기준 탑 높이
                else{
                    sb.append(stack[top].position + " ");	// 왼쪽 탑의 위치값 append
                    break;
                }
            }
              
            stack[++top] = new Top(count+1, insertTopHeight);	// 탑 위치, 높이 push
            count++;	// 위치 count++
        }
        System.out.print(sb);	// 결과물 출력
      
    }
}

class Top{
    int position;
    int height;

    public Top(int position, int height) {
        this.position = position;
        this.height = height;
    }
}
package com.jemin.algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1954 {

	static int[] moveY = {1, 0, -1, 0}; // 오른쪽, 아래, 왼쪽, 위
    static int[] moveX = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위
	
    static int[][] arr;
    static boolean[][] flag;
    
    static int inputLine;
    static int count;

    public static void main(String[] args) throws Exception {

        int inputCase;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inputCase = Integer.parseInt(br.readLine());
        for(int a=1; a<=inputCase; a++){
            inputLine = Integer.parseInt(br.readLine());
            arr = new int[inputLine][inputLine];
            flag = new boolean[inputLine][inputLine];
            
            count = 1;

            move(0, 0, 0);
            System.out.println("#" + a);
            
            for(int i=0; i<inputLine; i++){
                for(int j=0; j<inputLine; j++){
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static void move(int inputX, int inputY, int direction){

        flag[inputX][inputY] = true;
        arr[inputX][inputY] = count++;

        if(count <= inputLine * inputLine){
            int x = inputX + moveX[direction];
            int y = inputY + moveY[direction];

            if((x >= 0 && x < inputLine && y >= 0 && y < inputLine)){
                if((!flag[x][y])){
                    move(x, y, direction);
                }
                else{
                    move(inputX + moveX[(direction+1)%4], inputY + moveY[(direction+1)%4], (direction+1)%4);
                }
            }
            else{
                move(inputX + moveX[(direction+1)%4], inputY + moveY[(direction+1)%4], (direction+1)%4);
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException{
         
        int inputCase, inputScore;
        String inputString;
        int[] scoreArr;
        StringTokenizer st;
        String orderValue;
        int max = 0;
        int maxNum = 0;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputCase = Integer.parseInt(br.readLine());
         
        for(int i=0; i<inputCase; i++) {
            scoreArr = new int[101];
            orderValue = br.readLine();
            inputString = br.readLine();
            st = new StringTokenizer(inputString);
             
            for(int j=0; j<1000; j++) {
                inputScore = Integer.parseInt(st.nextToken());
                scoreArr[inputScore] += 1;
                 
            }
            for(int k=0; k<101; k++) {
                if(scoreArr[k] > max) {
                    maxNum = k;
                    max = scoreArr[k];
                }
                else if(scoreArr[k] == max ) {
                    if(k > maxNum)
                        maxNum = k;
                }
                 
            }
            System.out.println("#" + (i+1) + " "+ maxNum);
            maxNum = 0;
            max = 0;
        }
         
    }
}

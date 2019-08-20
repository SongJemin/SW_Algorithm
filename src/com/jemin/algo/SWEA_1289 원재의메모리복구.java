import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        int caseNum;
        String inputString;
        boolean flag = false;
        int count =0;
        boolean startFlag = false;
         
        Scanner sc = new Scanner(System.in);
         
        caseNum = sc.nextInt();
 
        for(int i=0; i<caseNum; i++) {
            inputString = sc.next();
            for(int j=0; j<inputString.length(); j++) {
                 
                 
                // 값 == 1 && 앞의 값 0
                if(inputString.charAt(j) == '1' && !flag) {
                    startFlag = true;
                    count+=1;
                    flag = true;
                }
                // 값 == 1 && 앞의 값도 1
                else if(inputString.charAt(j) == '1' && flag && startFlag) {
                }
                // 값 == 0 && 앞의 값 1
                else if(inputString.charAt(j) == '0' && flag && startFlag) {
                    count+=1;
                    flag = false;
                }
                // 값 == 0 && 앞의 값도 0
                else if(inputString.charAt(j) == '0' && !flag && startFlag) {
 
                }
            }
            System.out.println("#" + (i+1) + " " + count);
            count = 0;
            flag = false;
            startFlag = false;
        }
    } // end of main
}   // end of close

import java.io.*;
import java.util.*;
 
public class ReportCheck {
     
    public static void main(String[] args) throws IOException {
         
        boolean[] reportCheck;
        int inputCase, inputStuNum, submitNum;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        inputCase = Integer.parseInt(br.readLine());
         
        for (int i = 1; i <= inputCase; i++) {
            st = new StringTokenizer(br.readLine());
            inputStuNum = Integer.parseInt(st.nextToken());
            submitNum = Integer.parseInt(st.nextToken());
             
            reportCheck = new boolean[inputStuNum+1];
             
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                reportCheck[Integer.parseInt(st.nextToken())] = true;
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#" + i);
             
            for (int j = 1; j < reportCheck.length; j++) {
                if(!reportCheck[j]) {
                    sb.append(" " + j);
                }
            }
            System.out.println(sb);
        }       
    }
}

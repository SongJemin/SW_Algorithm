import java.util.*;
import java.io.*;
 
public class Flatten {
     
    public static void main(String[] args) throws IOException {
         
        int inputDump;
        int[] boxHeight;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        for(int i=1; i<=10; i++) {
            inputDump = Integer.parseInt(br.readLine());
            boxHeight = new int[100];
             
            st = new StringTokenizer(br.readLine());
             
            for(int j=0; j<100; j++) {
                boxHeight[j] = Integer.parseInt(st.nextToken());
            }
            for(int j=0; j<inputDump; j++) {
                boxHeight[0] += 1;
                boxHeight[99] -= 1;
                Arrays.sort(boxHeight);
            }       
            System.out.println("#" + i + " " + (boxHeight[99]-boxHeight[0]));
        }
    }
}

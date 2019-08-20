import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputCase = Integer.parseInt(br.readLine());
        String inputString = br.readLine();
        ArrayList<Integer> inputArr = new ArrayList<Integer>();
         
        StringTokenizer st = new StringTokenizer(inputString);
         
        for (int i = 0; i < inputCase; i++) {
            inputArr.add(Integer.parseInt(st.nextToken()));
        }
         
        Collections.sort(inputArr);
         
        System.out.println(inputArr.get((inputCase/2)));
    }
}

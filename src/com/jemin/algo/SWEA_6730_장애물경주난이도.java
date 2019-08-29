import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6730_장애물경주난이도 {
	public static void main(String[] args) throws Exception {
		int inputCase, stepNum;
		int[] steps;
		int upMax, downMax;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			stepNum = Integer.parseInt(br.readLine());
			steps = new int[stepNum];
			upMax = 0;
			downMax = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < stepNum; i++) {
				steps[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < stepNum-1; i++) {
				int sub = Math.abs(steps[i+1] - steps[i]);
				if(steps[i] > steps[i+1]){
					if(downMax < sub){
						downMax = sub;
					}
				}
				else{
					if(upMax < sub){
						upMax = sub;
					}
				}
			}
			sb.append("#" + a + " " + upMax + " " + downMax + "\n");
		}
		System.out.println(sb);
	}
}

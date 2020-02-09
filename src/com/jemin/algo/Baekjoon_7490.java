import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baekjoon_7490 {
	
	static ArrayList<StrArr> arr;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int size = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			start(size, 1, 0, "", 1, 1);
			
			Collections.sort(arr);
			
			for (int i = 0; i < arr.size(); i++) {
				sb.append(arr.get(i).str + "\n");
			}
			sb.append("\n");
		}
			System.out.println(sb);
	}
	
	public static void start(int size, int cnt, int result, String sb, int sign, int value) {
		
		int num = sign * value;
		
		// ¸¶Áö¸·
		if(cnt == size) {
			if((result+num == 0)) {
				arr.add(new StrArr(sb+cnt));
			}
		}
		else {
			int sum = result + num;
			start(size, cnt+1, sum, sb + cnt + "+", 1, cnt+1);
			start(size, cnt+1, sum, sb + cnt + "-", -1, cnt+1);
			start(size, cnt+1, result, sb + cnt + " ", sign, (value*10)+(cnt+1));
		}

	}
	
	public static class StrArr implements Comparable<StrArr>{
		
		String str;
		
		public StrArr(String str) {
			this.str = str;
		}

		@Override
		public int compareTo(StrArr r) {
			
			for (int i = 0; i < r.str.length(); i++) {
			
				if(this.str.charAt(i) < r.str.charAt(i)) {
					return -1;
				}
				else if(this.str.charAt(i) > r.str.charAt(i)){
					return 1;
				}
				else {
					continue;
				}
			}
			return 0;
		}
	}
}
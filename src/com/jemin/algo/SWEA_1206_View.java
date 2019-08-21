import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test {
	public static void main(String[] args) throws Exception {
		int buildingNum;
		List<Integer> arr;
		int ans, max, sub;
		int[] moveX = {-2, -1, 1, 2};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int a = 1; a <= 10; a++) {
			buildingNum = Integer.parseInt(br.readLine());
			arr= new ArrayList();
			st = new StringTokenizer(br.readLine(), " ");
			ans = 0;
			
			// 높이 입력
			for (int i = 0; i < buildingNum; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			// 주변 양 옆 두 빌딩 높이와 비교
			for (int i = 2; i < arr.size()-2; i++) {
				max = Integer.MIN_VALUE;
				
				for (int j = 0; j < 4; j++) {
					if(max < arr.get(i + moveX[j])){
						max = arr.get(i+ moveX[j]);
					}
				}
				
				sub = arr.get(i) - max;
				// 더 크다면 그 차이++
				if(sub > 0){
					ans += sub;
				}
			}
			
			System.out.println("#" + a + " " + ans);
		}
		
		
	}
}

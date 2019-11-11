import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Jungol_2577_회전초밥 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, d, k, c;
		int size;
		int max = Integer.MIN_VALUE;
		int[] dish;
		
		Map<Integer, Integer> map;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		dish = new int[N];
		
		for (int i = 0; i < N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		map = new HashMap<>();
		
		// 시작을 처음 위치부터 마지막 위치까지
		for (int i = 0; i < N; i++) {
			// 처음 시작은 그냥 추가만
			if(i == 0) {
				for (int j = 0; j < k; j++) {
					// 이미 map에 있는 값이라면 val+1
					if(map.containsKey(dish[j])) {
						map.put(dish[j], map.get(dish[j]) + 1);
					}
					// 없다면 새로 추가
					else {
						map.put(dish[j], 1);
					}
				}
			}
			
			// i >= 1
			else if(i >= 1) {
				// 맨 앞의 값
				// val > 1 ==> val--
				if(map.get(dish[i-1]) > 1){
					map.put(dish[i-1], map.get(dish[i-1]) - 1);
				}
				// val == 1이면 map에서 제거
				else {
					map.remove(dish[i-1]);
				}
				
				// 마지막 값
				int index = (i + (k-1)) % N;
				// 이미 map에 있는 값이라면 val+1
				if(map.containsKey(dish[index])) {
					map.put(dish[index], map.get(dish[index]) + 1);
				}
				// 없다면 새로 추가
				else {
					map.put(dish[index], 1);
				}
			}
			
			size =  map.size();
			// 쿠폰 조회
			if(!map.containsKey(c)) size++;
			
			if(max < size) max = size;
		}
		System.out.println(max);
	}
}

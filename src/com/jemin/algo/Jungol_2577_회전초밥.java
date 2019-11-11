import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Jungol_2577_회전초밥 {
	
	static Set<Integer> set;
	static int count = 0;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, d, k, c;
		int[] dish;
		int[] f;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		set = new HashSet<>();
		dish = new int[N];
		f = new int[d + 1];

		// 등록
		for (int i = 0; i < k; i++) {
			dish[i] = Integer.parseInt(br.readLine());
			if (f[dish[i]] == 0)
				count++;
			f[dish[i]]++;
			set.add(dish[i]);
		}
		for (int i = k; i < N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		max = checkCoupon(c);

		// i >= 1
		for (int i = 1; i < N; i++) {
			// 맨 앞의 값 제거
			if (f[dish[i - 1]] == 1) {
				f[dish[i - 1]] = 0;
				count--;
				set.remove(dish[i - 1]);
			} else
				f[dish[i - 1]]--;

			// 마지막 값 추가
			int index = (i + (k - 1)) % N;
			// 처음 등록 -> count++
			if (f[dish[index]] == 0) {
				count++;
				set.add(dish[index]);
			}
			// val ==> val++
			f[dish[index]]++;
			max = checkCoupon(c);
		}
		
		System.out.println(max);
	}

	// 쿠폰 조회
	public static int checkCoupon(int c) {
		if (!set.contains(c)) {
			if (max < (count + 1))
				max = (count + 1);
		} else {
			if (max < count)
				max = count;
		}
		return max;
	}
}

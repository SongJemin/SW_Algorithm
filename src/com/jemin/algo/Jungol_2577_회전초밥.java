import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Jungol_2577_ȸ���ʹ� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, d, k, c;
		int count = 0;
		int max = Integer.MIN_VALUE;
		int[] dish;
		int[] f;
		Set<Integer> set;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		set = new HashSet<>();
		dish = new int[N];
		f = new int[d+1];
		
		for (int i = 0; i < N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		// ������ ó�� ��ġ���� ������ ��ġ����
		for (int i = 0; i < N; i++) {
			// ó�� ������ �׳� �߰���
			if(i == 0) {
				for (int j = 0; j < k; j++) {
					// ó�� ��� -> count++
					if(f[dish[j]] == 0)	count++;
					f[dish[j]]++;
					set.add(dish[j]);
				}
			}
			
			// i >= 1
			else if(i >= 1) {
				// �� ���� �� ����
				if(f[dish[i-1]] == 1) {
					f[dish[i-1]] = 0;
					count--;
					set.remove(dish[i-1]);
				}
				else f[dish[i-1]]--;
				
				// ������ �� �߰�
				int index = (i + (k-1)) % N;
				// ó�� ��� -> count++
				if(f[dish[index]] == 0) {
					count++;
					set.add(dish[index]);
				}
				// val ==> val++
				f[dish[index]]++;
			}
			
			// ���� ��ȸ
			if(!set.contains(c)) {
				if(max < (count+1))	max = (count+1);
			}
			else {
				if(max < count) max = count;
			}
		}
		System.out.println(max);
	}
}

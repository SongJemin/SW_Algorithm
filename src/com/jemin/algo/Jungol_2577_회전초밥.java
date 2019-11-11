import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Jungol_2577_ȸ���ʹ� {
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
		
		// ������ ó�� ��ġ���� ������ ��ġ����
		for (int i = 0; i < N; i++) {
			// ó�� ������ �׳� �߰���
			if(i == 0) {
				for (int j = 0; j < k; j++) {
					// �̹� map�� �ִ� ���̶�� val+1
					if(map.containsKey(dish[j])) {
						map.put(dish[j], map.get(dish[j]) + 1);
					}
					// ���ٸ� ���� �߰�
					else {
						map.put(dish[j], 1);
					}
				}
			}
			
			// i >= 1
			else if(i >= 1) {
				// �� ���� ��
				// val > 1 ==> val--
				if(map.get(dish[i-1]) > 1){
					map.put(dish[i-1], map.get(dish[i-1]) - 1);
				}
				// val == 1�̸� map���� ����
				else {
					map.remove(dish[i-1]);
				}
				
				// ������ ��
				int index = (i + (k-1)) % N;
				// �̹� map�� �ִ� ���̶�� val+1
				if(map.containsKey(dish[index])) {
					map.put(dish[index], map.get(dish[index]) + 1);
				}
				// ���ٸ� ���� �߰�
				else {
					map.put(dish[index], 1);
				}
			}
			
			size =  map.size();
			// ���� ��ȸ
			if(!map.containsKey(c)) size++;
			
			if(max < size) max = size;
		}
		System.out.println(max);
	}
}

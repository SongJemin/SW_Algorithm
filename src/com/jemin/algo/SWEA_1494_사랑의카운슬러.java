import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1494_사랑의카운슬러 {
	
	static long min;
	static List<Pair> list;
	static int inputSize;
	static long xSum, selectedXsum, unSelectedXsum;
	static long ySum, selectedYsum, unSelectedYsum;
	static long resultX, resultY;
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		int[] arr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			min = Long.MAX_VALUE;
			inputSize = Integer.parseInt(br.readLine());
			arr = new int[inputSize];
			list = new ArrayList<>();
			xSum = 0;
			ySum = 0;
			
			for (int i = 0; i < inputSize; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				xSum += x;
				ySum += y;
				list.add(new Pair(x, y));
			}
			comb(arr, 0, inputSize, inputSize/2, 0);
			System.out.println("#" + a + " " + min);
		}
		System.out.println(sb);
	}

	// 오름차순 && N개의 숫자중 K개의 숫자를 추출해 조합
	static public void comb(int[] arr, int size, int N, int K, int index) {
		if(K == 0) {
			selectedXsum = 0;
			selectedYsum = 0;
			
			for (int i = 0; i < size; i++) {
				selectedXsum += list.get(arr[i]).x;
				selectedYsum += list.get(arr[i]).y;
			}
			unSelectedXsum = xSum - selectedXsum;
			unSelectedYsum = ySum - selectedYsum;
			
			resultX = selectedXsum - unSelectedXsum;
			resultY = selectedYsum - unSelectedYsum;

			if(min > (resultX) * (resultX) + (resultY) * (resultY)) {
				min = (resultX) * (resultX) + (resultY) * (resultY);
			}
			return;
			
		}
		else if(N == index){
			return;
		}
		else {
			arr[size] = index;
			comb(arr, size+1, N, K-1, index + 1);
			comb(arr, size, N, K, index + 1);
		}
	}
	
	public static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

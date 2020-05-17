import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PermTest {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] arr;

		int n = 5;
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = (i+1);
		}

		boolean[] visited = new boolean[n];
		int[] output = new int[3];

		// ���� ���� �ʿ� X
//		perm(arr, 0, n, 3);
		
		// ���� ���� �ʿ� O
//		orderPerm(arr, output, visited, 0, n, 3);

		System.out.println("=======����============");
		// ����
//		comb(arr, output, 0, n, 3, 0);
		comb2(arr, n);
	}
	
	// ����2
	public static void comb2(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				for (int k = j+1; k < n; k++) {
					System.out.print(arr[i] + ", " + arr[j] + ", " + arr[k]);
					System.out.println();
				}
			}
		}
	}
	
	// ����1
	public static void comb(int[] arr, int[] output, int step, int n, int r, int target) {
		if(r == 0) {
			System.out.println(Arrays.toString(output));
		}
		else if(target == n) return;
		else {
			output[step] = arr[target];
			
			comb(arr, output, step+1, n, r-1, target+1);
			comb(arr, output, step, n, r, target+1);
		}
	}
	
	// ���� �ʿ��� ����
	public static void orderPerm(int[] arr, int[] output, boolean[] visited, int step, int n, int r) {
		if(step == r) {
			System.out.println(Arrays.toString(output));
		}
		else {
			for (int i = 0; i < n; i++) {
				if(!visited[i]) {
					output[step] = arr[i];
					visited[i] = true;
					
					orderPerm(arr, output, visited, step+1, n, r);
					visited[i] = false;
				}
			}
		}
	}
	
	// ���� �ʿ� ���� ����
	public static void perm(int[] arr, int step, int n, int r) {
		if(step == r) {
			for (int i = 0; i < r; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		else {
			for (int i = step; i < n; i++) {
				int temp = arr[i];
				arr[i] = arr[step];
				arr[step] = temp;
				
				perm(arr, step+1, n, r);
				
				temp = arr[i];
				arr[i] = arr[step];
				arr[step] = temp;
			}
		}
	}
}
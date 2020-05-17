package segment;

import java.util.*;
import java.io.*;

public class Baekjoon_2042_������ {
	
	static int N, M, K;
	static int[] arr;
	static long[] node;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String inputStr = br.readLine();
		StringTokenizer st = new StringTokenizer(inputStr, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(baseLog((double)N, 2.0));
		int treeSize = (1 << (height+1));
		
		arr = new int[N+1];
		node = new long[treeSize];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, N, 1);
		
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int oper = Integer.parseInt(st.nextToken());
			int firstParam = Integer.parseInt(st.nextToken());
			int secondParam = Integer.parseInt(st.nextToken());
			
			// change
			if(oper == 1) {
				int diff = secondParam - arr[firstParam];
				arr[firstParam] = secondParam;
				change(1, N, firstParam, diff, 1);
			}
			// sum
			else {
				long sum = sum(firstParam, secondParam, 1, N, 1);
				System.out.println(sum);
			}
		}
	}
	
	// �α� �� ���ϱ�
	public static double baseLog(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	
	// �� ����
	public static void change(int start, int end, int index, int diff, int n) {
		if(index < start || end < index) return;
		
		if(start == index && end == index) {
			node[n] += diff;
			return;
		}
		
		if(start <= index && index <= end) {
			int mid = (start + end) / 2;
			
			change(start, mid, index, diff, n*2);
			change(mid+1, end, index, diff, n*2+1);
			
			node[n] += diff;
			return;
		}
	}
	
	// ���� �� ���ϱ�
	public static long sum(int rLeft, int rRight, int start, int end, int n) {
		if(rLeft > end || rRight < start) {
			return 0;
		}
		if(rLeft <= start && end <= rRight) {
			return node[n];
		}
		
		int mid = (start + end) / 2;
		long val = sum(rLeft, rRight, start, mid, n*2) + sum(rLeft, rRight, mid+1, end, n*2+1);
		return val;
	}
	
	// ���׸�Ʈ Ʈ�� �ʱ� ����
	public static long init(int start, int end, int n) {
		// ��忡 �� ����(leaf)
		if(start == end) {
			node[n] = arr[start];
		}
		// �ڽ� ���κ��� �� ������ ���Ͽ� ��尪 ����(leaf X)
		else {
			int mid = (start + end) / 2;
			node[n] = init(start, mid, n*2) + init(mid+1, end, n*2+1);
		}
		return node[n];
	}
}

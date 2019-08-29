import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon_17140_이차원배열과연산 {

	static int ansX, ansY, ansVal, rowSize, colSize;
	static int[][] arr = new int[3][3];
	static int count[];
	static List<Num> nums;
	static int[][] temp;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		boolean flag = false;

		ansX = Integer.parseInt(st.nextToken());
		ansY = Integer.parseInt(st.nextToken());
		ansVal = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		rowSize = 3;
		colSize = 3;

		
		while (time <= 100) {
			max = Integer.MIN_VALUE;
			temp = new int[100][100];
			rowSize = arr.length;
			colSize = arr[0].length;

			// 답 찾음
			if(rowSize >= ansX && colSize >= ansY){
				
				if(arr[ansX-1][ansY-1] == ansVal){
					flag = true;
					break;
				}
			}
			if (rowSize >= colSize) {
				functionR();
			}
			else{
				functionC();
			}
			arr = new int[rowSize][colSize];

			
			for(int i = 0; i < rowSize; i++){
				for (int j = 0; j < colSize; j++) {
					arr[i][j] = temp[i][j];
				}
			}
			
			time++;
		}
		if (!flag) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}

		
	}
	
	// 행의 개수 >= 열의 개수
	private static void functionR() {
	
		for (int i = 0; i < rowSize; i++) {
			count = new int[101];
			nums = new ArrayList<>();
			for (int j = 0; j < colSize; j++) {
				if(arr[i][j] != 0){
					count[arr[i][j]]++;
				}
			}
			for (int j = 0; j < 101; j++) {
				if(count[j] != 0){
					nums.add(new Num(j, count[j]));
				}
			}
			Collections.sort(nums);
			int index = 0;
			for (int j = 0; j < nums.size(); j++) {
				temp[i][index] = nums.get(j).index;
				temp[i][index+1] = nums.get(j).count;
				index += 2;
			}
			
			if(max < nums.size()*2){
				max = nums.size()*2;
			}
		}
		colSize = max;
	}

	// 행의 개수 < 열의 개수
	private static void functionC() {
		for (int j = 0; j < colSize; j++) {
			count = new int[101];
			nums = new ArrayList<>();
			for (int i = 0; i < rowSize; i++) {
				if(arr[i][j] != 0){
					count[arr[i][j]]++;
				}
			}
			for (int i = 0; i < 101; i++) {
				if(count[i] != 0){
					nums.add(new Num(i, count[i]));
				}
			}
			Collections.sort(nums);

			int index = 0;
			for (int i = 0; i < nums.size(); i++) {
				temp[index][j] = nums.get(i).index;
				temp[index+1][j] = nums.get(i).count;
				index += 2;
			}
			
			if(max < nums.size()*2){
				max = nums.size()*2;
			}

			
		}
		rowSize = max;
	}

	public static class Num implements Comparable<Num> {
		int index;
		int count;

		public Num(int index, int count) {
			this.index = index;
			this.count = count;
		}

		@Override
		public int compareTo(Num o) {
			if(this.count > o.count){
				return 1;
			} else if(this.count == o.count){
				if(this.index > o.index){
					return 1;
				}else{
					return -1;
				}
			} else{
				return -1;
			}
		}
	}
}
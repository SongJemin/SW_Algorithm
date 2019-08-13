import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution17406 {
	
	static int[][] arr;
	static int[][] copyArr;
	static int inputRow, inputCol, circleNum;
	static int[] circleX;
	static int[] circleY;
	static int[] circleSize;
	
	static int[] a;		// 순열 때 사용하는 숫자 배열
	static int rowSum;	// 각 행마다 총 값
	static boolean[] checkCircle;	// 순열 때 사용하는 boolean 배열
	static int min = Integer.MAX_VALUE;
	
	static int topLeft, topRight, bottomRight;	// 모서리 값 임시 저장 변수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		inputRow = Integer.parseInt(st.nextToken());
		inputCol = Integer.parseInt(st.nextToken());
		circleNum = Integer.parseInt(st.nextToken());
		
		// 순열 횟수만큼 생성
		arr = new int[inputRow][inputCol];
		copyArr = new int[inputRow][inputCol];
		circleX = new int[circleNum];
		circleY = new int[circleNum];
		circleSize = new int[circleNum];
		
		a = new int[circleNum];
		checkCircle = new boolean[circleNum];
		
		// 이차원 배열 값 입력
		for (int i = 0; i < inputRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputCol; j++) {
				int inputNum = Integer.parseInt(st.nextToken());
				for(int k = 0; k < circleNum; k++) {
					arr[i][j] = inputNum;
					copyArr[i][j] = inputNum;
				}				
			}
		}
		
		// 왜곡이 일어나는 중심값, 크기 입력
		for (int i = 0; i < circleNum; i++) {
			st = new StringTokenizer(br.readLine());
			circleX[i] = Integer.parseInt(st.nextToken()) - 1;
			circleY[i] = Integer.parseInt(st.nextToken()) - 1;
			circleSize[i] = Integer.parseInt(st.nextToken());
			a[i] = i;
		}
		
		// 모든 순열 계산 후 왜곡 시작
		solve(a, 0, checkCircle);
		System.out.println(min);
	}
	
	// 순열 계산
	static void solve(int[] a, int count, boolean[] check) {
		// 순열이 끝났다면
		if(count == circleNum) {
			// 그 크기만큼 왜곡 시작
			for(int i = 0; i<a.length; i++) {
				move(circleX[a[i]], circleY[a[i]], circleSize[a[i]]);
			}
			// 각 행마다 총 값 계산 반복
			for (int i = 0; i < inputRow; i++) {
				rowSum = 0;
				for (int j = 0; j < inputCol; j++) {
					rowSum += arr[i][j];
				}
				if(rowSum < min) {
					min = rowSum;
				}
			}
			// 다음 이차원 배열로 넘어가기
			for (int i = 0; i < inputRow; i++) {
				for (int j = 0; j < inputCol; j++) {
					arr[i][j] = copyArr[i][j];
				}				
			}
		}
		// 체크
		for(int i=0; i<circleNum; i++) {
			if(!check[i]) {
				check[i] = true;
				a[count] = i;
				
				solve(a, count+1, check);
				
				// 현재 값 false 후 다음 값들로 다시 시작
				check[i] = false;
			}
		}
	}
	
	static void move(int inputX, int inputY, int inputSize) {
		int centerX = inputX;
		int centerY = inputY;
		int size = inputSize;
		
		for(int s = 1; s <= size; s++) {
			topLeft = arr[centerX - s][centerY - s];
			for(int i = centerX - s + 1; i <= centerX + s; i++) {
				arr[i-1][centerY - s] = arr[i][centerY-s];
			}
			
			topRight = arr[centerX - s][centerY + s];
			for(int i = centerY + s - 1; i >= centerY - s; i--) {
				arr[centerX - s][i+1] = arr[centerX - s][i];
			}
			
			bottomRight = arr[centerX + s][centerY + s];
			for(int i = centerX + s -1; i >= centerX - s; i--) {
				arr[i+1][centerY + s] = arr[i][centerY + s];
			}
			
			for(int i = centerY - s + 1; i <= centerY + s; i++) {
				arr[centerX + s][i-1] = arr[centerX + s][i];
			}
			
			arr[centerX - s][centerY - s + 1] = topLeft;
			arr[centerX - s + 1][centerY + s] = topRight;
			arr[centerX + s][centerY + s - 1] = bottomRight;
		}
		
	}
}

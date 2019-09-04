import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_15686_치킨배달 {
 
 static int[] moveX = {-1, 1, 0, 0};  // 상, 하, 좌, 우
 static int[] moveY = {0, 0, -1, 1};  // 상, 하, 좌, 우
 
 public static int inputSize, maxChicNum;
 public static Queue<Pair> que;
 static List<Integer> chicX, chicY, targetX, targetY, homeXs, homeYs;
 static int min, sum;
 
 static int arr[];
 static int result[];
 
 public static void main(String[] args) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(br.readLine(), " ");
  
  min = Integer.MAX_VALUE;
  
  inputSize = Integer.parseInt(st.nextToken());
  maxChicNum = Integer.parseInt(st.nextToken());
  
  homeXs = new ArrayList<>();
  homeYs = new ArrayList<>();
  targetX = new ArrayList<>();
  targetY = new ArrayList<>();
  
  chicX = new ArrayList<>();
  chicY = new ArrayList<>();
  for (int i = 0; i < inputSize; i++) {
   st = new StringTokenizer(br.readLine());
   for (int j = 0; j < inputSize; j++) {
    int num = Integer.parseInt(st.nextToken());
    if(num == 2) {
     chicX.add(i);
     chicY.add(j);
    }
    else if(num == 1) {
     homeXs.add(i);
     homeYs.add(j);
    }
   }
  }
  arr = new int[chicX.size()];
  for (int i = 0; i < arr.length; i++) {
   arr[i] = i;
  }
  
  int[] result;
  for (int i = 1; i <= maxChicNum; i++) {
   result = new int[i];
   sum = 0;
   comb(result, 0, i, 0);
  }
  System.out.println(min);
  
 }
 
 private static void comb(int[] result, int step, int r, int target) {
	 if(r == 0){
		 for (int i = 0; i < result.length; i++) {
			 targetX.add(chicX.get(result[i]));
			 targetY.add(chicY.get(result[i]));
		 }
		 
		 // 모든 집위치 데이터로 반복
		 for (int i = 0; i < homeXs.size(); i++) {
			 int minDistance = Integer.MAX_VALUE;
			 // 받은 치킨 집 위치와 거리 계산
			 for (int j = 0; j < result.length; j++) {
				 int distance = Math.abs(homeXs.get(i) - targetX.get(j)) + Math.abs(homeYs.get(i) - targetY.get(j));
				 if(minDistance > distance){
					 minDistance = distance;
				 }
			 }
			 // 최소 거리값 계산
			 sum += minDistance;
		 }
		 if(min > sum){
			 min = sum;
		 }
		 sum = 0;
		 targetX.clear();
		 targetY.clear();
	 }
	 else if(target == chicX.size()) return;
	 else{
		 result[step] = arr[target];
		 comb(result, step+1, r-1, target+1);
		 comb(result, step, r, target+1);
	 }
  
 }

 public static class Pair{
  int x;
  int y;
  int distance;
  
  public Pair(int x, int y, int distance) {
   this.x = x;
   this.y = y;
   this.distance = distance;
  }
 }
}
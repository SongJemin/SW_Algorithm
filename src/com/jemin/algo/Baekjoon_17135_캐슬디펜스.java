
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 백준 17135 캐플 디펜스 삼성 SW 역량테스트 A형 2번 기출문제 복잡한 시뮬레이션 문제 - 효율성 생각하지 말고 일단 작성
 * 
 * 아이디어 적군을 한줄씩 아래로 이동시켜도 되지만, 반대로 궁수가 있는 성의 위치를 한줄씩 위로 올려도 된다(시간이 빠름)
 */
public class Baekjoon_17135_캐슬디펜스 {
	private static int N;
	private static int M;
	private static int D;
	private static int[][] a;
	private static int[][] aCopy;
	private static int max;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 격자판 행, 3 <= N <= 15
		M = Integer.parseInt(st.nextToken()); // 격자판 열, 3 <= N <= 15
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격거리 제한, 1 <= D <= 10
		a = new int[N][M];
		aCopy = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				a[i][j] = str.charAt(index) - '0';
			}
		}

		max = 0; // 죽인 적군의 최대값을 저장 변수
		int count = 0;
//  M개의 열 중에서 3명의 3명의 궁수자리 배치(조합)
		for (int x = 0; x < M - 2; x++) {
			for (int y = x + 1; y < M - 1; y++) {
				for (int z = y + 1; z < M; z++) { // 궁수의 위치 x, y, z
//					if(x == 0 && y == 2 && z == 4) {
						
//  원본 맵을 보존하고, 사본을 복사해서 진행
						for (int i = 0; i < a.length; i++) {
							aCopy[i] = a[i].clone(); // 깊은 복사
						}
						
						cnt = 0; // 궁수 배치후 시뮬레이션 해서 죽인 적군의 개수 누적
//  궁수가 공격거리 안에 있는 적군을 한 번씩 쏜다
						count += 1;
						for (int i = 0; i < a.length; i++) { // 행의 수만큼 반복
							궁수공격(x, y, z); // 죽으면 cnt++
							적군이동(); // 격자판 이동
						}
//   죽인 적군의 수를 업데이트
						if (max < cnt)
							max = cnt;
					}
				}
			}
//		}
//  최대값 출력
		System.out.println(max);
	} // end of main

	public static HashSet<String> hs = new HashSet<>(); // 죽일 적군의 위치 "행 열" 문자열로 저장

	/** 적군을 죽이는 기준 : 거리(dx+dy:문제에서 정의됨)가 가까운 순, 거리가 동일하다면 왼쪽 순 */
	private static void 궁수공격(int... trr) { // int[] trr로 사용 가능
		hs.clear();
		for (int i = 0; i < trr.length; i++) { // 궁수 차례로 (N행, trr[i])
			next: for (int j = 1; j <= D; j++) { // 공격 거리
				int r = N - 1; // 왼쪽 끝은 격자판 밖이라서 적군이 없음
				int c = trr[i] - j + 1;
				for (; r < N; c++) { // 오르막내리막
					if (0 <= r && r < N && 0 <= c && c < M && aCopy[r][c] == 1) { // 범위 유효성 체크
//      제거할 적군을 기록해 놓는다.
						hs.add(r + " " + c);
						break next;
					}
					r = c < trr[i] ? r - 1 : r + 1; // 높이 조정
				}
			}
		}
		
		cnt += hs.size(); // 제거할 적군 개수를 누적
		for (String e : hs) { // "행 열"
			StringTokenizer st = new StringTokenizer(e, " ");
			aCopy[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 0;
		}
	}

	/** 격자판을 한행씩 아래로 내리기 */
	private static void 적군이동() {
		for (int r = N - 2; r >= 0; r--) { // 마지막 행부터 옮기기
			for (int c = 0; c < M; c++) {
				aCopy[r + 1][c] = aCopy[r][c];
			}
		}
		for (int c = 0; c < M; c++) { // 맨 윗줄은 0으로 초기화
			aCopy[0][c] = 0;
		}
	}
} // end of class

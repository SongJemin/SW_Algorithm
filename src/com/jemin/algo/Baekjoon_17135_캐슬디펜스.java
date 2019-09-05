
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * ���� 17135 ĳ�� ���潺 �Ｚ SW �����׽�Ʈ A�� 2�� ���⹮�� ������ �ùķ��̼� ���� - ȿ���� �������� ���� �ϴ� �ۼ�
 * 
 * ���̵�� ������ ���پ� �Ʒ��� �̵����ѵ� ������, �ݴ�� �ü��� �ִ� ���� ��ġ�� ���پ� ���� �÷��� �ȴ�(�ð��� ����)
 */
public class Baekjoon_17135_ĳ�����潺 {
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
		N = Integer.parseInt(st.nextToken()); // ������ ��, 3 <= N <= 15
		M = Integer.parseInt(st.nextToken()); // ������ ��, 3 <= N <= 15
		D = Integer.parseInt(st.nextToken()); // �ü��� ���ݰŸ� ����, 1 <= D <= 10
		a = new int[N][M];
		aCopy = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				a[i][j] = str.charAt(index) - '0';
			}
		}

		max = 0; // ���� ������ �ִ밪�� ���� ����
		int count = 0;
//  M���� �� �߿��� 3���� 3���� �ü��ڸ� ��ġ(����)
		for (int x = 0; x < M - 2; x++) {
			for (int y = x + 1; y < M - 1; y++) {
				for (int z = y + 1; z < M; z++) { // �ü��� ��ġ x, y, z
//					if(x == 0 && y == 2 && z == 4) {
						
//  ���� ���� �����ϰ�, �纻�� �����ؼ� ����
						for (int i = 0; i < a.length; i++) {
							aCopy[i] = a[i].clone(); // ���� ����
						}
						
						cnt = 0; // �ü� ��ġ�� �ùķ��̼� �ؼ� ���� ������ ���� ����
//  �ü��� ���ݰŸ� �ȿ� �ִ� ������ �� ���� ���
						count += 1;
						for (int i = 0; i < a.length; i++) { // ���� ����ŭ �ݺ�
							�ü�����(x, y, z); // ������ cnt++
							�����̵�(); // ������ �̵�
						}
//   ���� ������ ���� ������Ʈ
						if (max < cnt)
							max = cnt;
					}
				}
			}
//		}
//  �ִ밪 ���
		System.out.println(max);
	} // end of main

	public static HashSet<String> hs = new HashSet<>(); // ���� ������ ��ġ "�� ��" ���ڿ��� ����

	/** ������ ���̴� ���� : �Ÿ�(dx+dy:�������� ���ǵ�)�� ����� ��, �Ÿ��� �����ϴٸ� ���� �� */
	private static void �ü�����(int... trr) { // int[] trr�� ��� ����
		hs.clear();
		for (int i = 0; i < trr.length; i++) { // �ü� ���ʷ� (N��, trr[i])
			next: for (int j = 1; j <= D; j++) { // ���� �Ÿ�
				int r = N - 1; // ���� ���� ������ ���̶� ������ ����
				int c = trr[i] - j + 1;
				for (; r < N; c++) { // ������������
					if (0 <= r && r < N && 0 <= c && c < M && aCopy[r][c] == 1) { // ���� ��ȿ�� üũ
//      ������ ������ ����� ���´�.
						hs.add(r + " " + c);
						break next;
					}
					r = c < trr[i] ? r - 1 : r + 1; // ���� ����
				}
			}
		}
		
		cnt += hs.size(); // ������ ���� ������ ����
		for (String e : hs) { // "�� ��"
			StringTokenizer st = new StringTokenizer(e, " ");
			aCopy[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 0;
		}
	}

	/** �������� ���྿ �Ʒ��� ������ */
	private static void �����̵�() {
		for (int r = N - 2; r >= 0; r--) { // ������ ����� �ű��
			for (int c = 0; c < M; c++) {
				aCopy[r + 1][c] = aCopy[r][c];
			}
		}
		for (int c = 0; c < M; c++) { // �� ������ 0���� �ʱ�ȭ
			aCopy[0][c] = 0;
		}
	}
} // end of class

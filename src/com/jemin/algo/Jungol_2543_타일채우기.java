import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_2543_Ÿ��ä��� {
	
	static int size;
	static int[][] map;
	static int holeX, holeY;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		holeX = Integer.parseInt(st.nextToken());
		holeY = Integer.parseInt(st.nextToken());
		
		go(0, 0, size-1, size-1, holeX, holeY);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void go(int sX, int sY, int eX, int eY, int holeX, int holeY) {
		// ��ġ ����
		int mX = (sX + eX) / 2;
		int mY = (sY + eY) / 2;

		
		if(sX == eX) return;
		if(sY == eY) return;
		
		// ��-���� ������ ����
		if(holeX <= mX && holeY <= mY) {
			// �� ��и� �ֺ����� 1�� Ÿ�� ����
			map[mX+1][mY] = 1;
			map[mX][mY+1] = 1;
			map[mX+1][mY+1] = 1;
			
			// �ٽ� ����
			go(sX, sY, mX, mY, holeX, holeY);
			go(sX, mY+1, mX, eY, mX, mY+1);
			go(mX+1, sY, eX, mY, mX+1, mY);
			go(mX+1, mY+1, eX, eY, mX+1, mY+1);
		}
		// ��-������ ������ ����
		else if(holeX <= mX && holeY > mY) {
			// �� ��и� �ֺ����� 2�� Ÿ�� ����
			map[mX][mY] = 2;
			map[mX+1][mY] = 2;
			map[mX+1][mY+1] = 2;
			
			// �ٽ� ����
			go(sX, sY, mX, mY, mX, mY);
			go(sX, mY+1, mX, eY, holeX, holeY);
			go(mX+1, sY, eX, mY, mX+1, mY);
			go(mX+1, mY+1, eX, eY, mX+1, mY+1);
		}
		// �Ʒ�-���� ������ ����
		else if(holeX > mX && holeY <= mY) {
			// �� ��и� �ֺ����� 3�� Ÿ�� ����
			map[mX][mY] = 3;
			map[mX][mY+1] = 3;
			map[mX+1][mY+1] = 3;
			
			// �ٽ� ����
			go(sX, sY, mX, mY, mX, mY);
			go(sX, mY+1, mX, eY, mX, mY+1);
			go(mX+1, sY, eX, mY, holeX, holeY);
			go(mX+1, mY+1, eX, eY, mX+1, mY+1);
		}
		// �Ʒ�-������ ������ ����
		else {
			// �� ��и� �ֺ����� 4�� Ÿ�� ����
			map[mX][mY] = 4;
			map[mX+1][mY] = 4;
			map[mX][mY+1] = 4;
			
			// �ٽ� ����
			go(sX, sY, mX, mY, mX, mY);
			go(sX, mY+1, mX, eY, mX, mY+1);
			go(mX+1, sY, eX, mY, mX+1, mY);
			go(mX+1, mY+1, eX, eY, holeX, holeY);
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8104_Á¶¸¸µé±â {
	public static void main(String[] args) throws Exception {
		int inputCase;
		int N, K;
		int result;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			result = 0;
			sb.append("#" + a);
			// NÀÌ Â¦¼ö
			if(N % 2 == 0){
				for (int i = 0; i < N; i=i+2) {
					result += ((2 + (4 * (i/2))) * K + 1);
				}

				for (int i = 0; i < K; i++) {
					sb.append(" " + result);
				}
			}
			// NÀÌ È¦¼ö
			else{
				for (int i = 0; i < N-1; i=i+2) {
					result += ((2 + (4 * (i/2))) * K + 1);
				}
				for (int i = K - 1; i >= 0; i--) {
					int ans = result + (N*K)-i;
					sb.append(" " + ans);

				}
			}
			sb.append("\n");
		}
		System.out.println(sb);			
	}
}

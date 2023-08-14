import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 누적합을 이용해 시간복잡도를 줄였다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 참여한 라운드 1~10만
		int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수 1~100만
		int K = Integer.parseInt(st.nextToken()); // 기준 레이팅 -10억~10억
		int X = Integer.parseInt(st.nextToken()); // 초기 레이팅 -1만~1만
		
		st = new StringTokenizer(br.readLine());
		int[] D = new int[N + 1];
		int rating = X;
		for (int i = 1; i <= N; i++) {
			rating += Integer.parseInt(st.nextToken()); // 이전라운드에서 수열A의 i번째값을 더해줌
			if (rating < K) {
				D[i] = D[i - 1] + 1;
			} else {
				D[i] = D[i - 1];
			}
		}
		
		for (int i = 0; i < M; i++) { // M개의 쿼리
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()); // l라운드 참여직후부터
			int r = Integer.parseInt(st.nextToken()); // r라운드 참여직전까지
			sb.append(D[r - 1] - D[l - 1]).append("\n");
		}
		System.out.print(sb);
	}

}
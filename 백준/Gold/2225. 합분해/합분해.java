import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 0부터 N까지 정수 K개를 더해서 합이 N이 되는 경우의 수를 구하는 문제
 * 다이나믹 프로그래밍으로 풀이
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 1 ~ 200
		int K = Integer.parseInt(st.nextToken()); // 1 ~ 200
		
		long[][] dp = new long[N + 1][K + 1];
		
		for (int j = 1; j <= K; j++) {
			dp[0][j] = 1;
		}
		
		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
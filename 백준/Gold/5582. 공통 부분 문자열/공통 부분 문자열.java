import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * DP 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		
		int N = A.length;
		int M = B.length;
		
		int[][] dp = new int[N + 1][M + 1];
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					answer = Math.max(answer, dp[i][j]);
				}
			}
		}
		
		System.out.println(answer);
	}

}
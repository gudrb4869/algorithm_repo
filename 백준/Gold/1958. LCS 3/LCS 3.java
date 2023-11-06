import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * LCS 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		char[] C = br.readLine().toCharArray();
		
		int N = A.length;
		int M = B.length;
		int K = C.length;
		
		int[][][] dp = new int[N + 1][M + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 1; k <= K; k++) {
					if (A[i - 1] == B[j - 1] && B[j - 1] == C[k - 1] && C[k - 1] == A[i - 1]) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(dp[i][j - 1][k - 1], 
								Math.max(dp[i - 1][j][k - 1],
										Math.max(dp[i - 1][j - 1][k],
												Math.max(dp[i][j][k - 1],
														Math.max(dp[i][j - 1][k], dp[i - 1][j][k])))));
					}
				}
			}
		}
		
		System.out.println(dp[N][M][K]);
	}

}
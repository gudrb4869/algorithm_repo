import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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
		StringBuilder sb = new StringBuilder();
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		
		int N = A.length;
		int M = B.length;
		
		int[][] dp = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					if (dp[i - 1][j] > dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = dp[i][j - 1];
					}
				}
			}
		}
		
		Stack<Character> lcs = new Stack<>();
		int x = N, y = M;
		while (x > 0 && y > 0) {
			if (A[x - 1] == B[y - 1]) {
				lcs.push(A[x - 1]);
				x--;
				y--;
			} else {
				if (dp[x - 1][y] > dp[x][y - 1]) {
					x--;
				} else {
					y--;
				}
			}
		}
		
		sb.append(dp[N][M]).append("\n");
		while (!lcs.isEmpty()) {
			sb.append(lcs.pop());
		}
		sb.append("\n");
		System.out.print(sb);
		
	}

}
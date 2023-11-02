import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 다이나믹 프로그래밍
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[31];
		dp[0] = 1;
		dp[1] = 2;
		for (int i = 2; i <= 30; i++) {
			if ((i & 1) == 0) {
				dp[i] = dp[i - 1] + dp[i - 2];
			} else {
				dp[i] = dp[i - 1] * 2 + dp[i - 2];
			}
		}
		
		System.out.println((N & 1) == 1 ? 0 : dp[N]);
	}

}
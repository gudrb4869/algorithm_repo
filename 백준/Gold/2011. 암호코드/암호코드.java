import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 다이나믹 프로그래밍
 * 2023-12-16(토)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int n = s.length();
		
		if (s.charAt(0) == '0') {
			System.out.println(0);
			return;
		}
		
		if (n == 1) {
			System.out.println(1);
			return;
		}
		
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			String suffix = s.substring(i - 2, i);
			int num = Integer.parseInt(suffix);
			if (suffix.charAt(1) == '0' && (num > 26 || num == 0)) {
				dp[n] = 0;
				break;
			}
			else if (suffix.charAt(0) == '0' || num > 26) dp[i] = dp[i - 1];
			else if (suffix.charAt(1) == '0') dp[i] = dp[i - 2];
			else dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
		}
		
		System.out.println(dp[n]);
	}

}
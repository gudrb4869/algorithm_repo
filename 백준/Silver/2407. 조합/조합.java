import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * <pre>
 * nCm을 구해야하는데 m<=n이고 n과m은 5이상100이하이다.
 * 따라서 n-1Cr-1 + n-1Cr = nCr 공식을 이용해서
 * nCm을 구했다.
 * long의 범위를 넘어가는 경우가 있기 때문에 BigInteger를 사용해서 계산했다.
 * </pre>
 * @author 박형규
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		BigInteger[][] dp = new BigInteger[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			dp[i][0] = new BigInteger("1"); // nC0 = 1
			dp[i][i] = new BigInteger("1"); // nC0 = 1
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
			}
		}
		
		System.out.println(dp[n][m].toString()); // nCm
		
	}
}

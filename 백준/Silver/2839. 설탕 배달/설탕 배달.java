import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * n킬로그램을 만들기 위한 봉지개수는 (n-3킬로그램을 만드는데 필요한 봉지개수 + 1)과 (n-5킬로그램을 만드는데 필요한 봉지개수 +1)중
 * 가장 작은값이다. 만약 n-3, n-5킬로그램을 둘다만들수없는경우엔 n킬로그램도 만들수 없으므로 유지시켜줌
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int INF = Integer.MAX_VALUE;
		int[] dp = new int[5001]; // n이 3부터 5000이므로 5001크기 dp테이블 생성
		Arrays.fill(dp, INF); // 무한대로 초기화
		dp[3] = dp[5] = 1; // 3키로와 5키로 1개로 설정
		
		for (int i = 6; i < 5001; i++) {
			if (dp[i - 3] != INF) { // i-3킬로그램 만들수 있는경우
				dp[i] = Math.min(dp[i], dp[i - 3] + 1);
			}
			if (dp[i - 5] != INF) { // i-5킬로그램 만들수 있는 경우
				dp[i] = Math.min(dp[i], dp[i - 5] + 1);
			}
		}
		
		if (dp[n] == INF) { // 만들수 없는경우
			System.out.println(-1); // -1출력
		} else { // 만들수 있는 경우
			System.out.println(dp[n]); // 봉지최소개수 출력
		}
	}

}

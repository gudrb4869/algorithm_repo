import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * dp로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1보다 크거나 같고 10^6보다 작거나 같은 정수
		
		int[] dp = new int[N + 1]; // dp table 초기화
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1; // i를 1로만드는 횟수 = (i-1을 1로만드는 횟수+1)
			if (i % 2 == 0) { // i가 2로 나누어 떨어지는 경우
				dp[i] = Math.min(dp[i], dp[i / 2] + 1); // i/2를 1로만드는 횟수+1과 기존값중 최솟값으로 갱신
			}
			if (i % 3 == 0) { // i가 3으로 나누어 떨어지는 경우
				dp[i] = Math.min(dp[i], dp[i / 3] + 1); // i/3을 1로만드는 횟수+1과 기존값중 최솟값으로 갱신
			}
		}
		System.out.println(dp[N]); // N을 1로만드는 횟수의 최솟값
	}

}
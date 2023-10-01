import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 플로이드 워셜 알고리즘으로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 유저의 수 (2 ~ 100)
		int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수 (1 ~ 5000)
		
		int[][] dp = new int[N + 1][N + 1]; // 1부터 N까지 사용하므로 N+1크기로 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = i == j ? 0 : 987654321; // dp 테이블 초기화
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			dp[A][B] = 1; // A와 B
			dp[B][A] = 1; // B와 A
		}
		
		// 플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
		
		int cur = 987654321, ans = 1; // 현재 케빈 베이컨의 수와 현재 사람
		
		for (int i = 1; i <= N; i++) {
			int sum = 0; // 케빈 베이컨의 수
			for (int j = 1; j <= N; j++) {
				sum += dp[i][j]; // 누적
			}
			if (cur > sum) { // 더 작은 경우
				cur = sum; // 케빈베이컨의 수 최솟값으로 변경
				ans = i; // 현재 사람 변경
			}
		}
		System.out.println(ans); // 결과 출력
		
	}

}
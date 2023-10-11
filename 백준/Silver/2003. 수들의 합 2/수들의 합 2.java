import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 누적합과 투포인터를 이용해 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		int M = Integer.parseInt(st.nextToken()); // 찾을 합
		
		st = new StringTokenizer(br.readLine());

		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			dp[i] += dp[i - 1];
		}
		
		int answer = 0; // 경우의 수
		for (int left = 0; left < N; left++) { // left 포인터의 범위 0 ~ N-1
			int right = left + 1; // right 포인터의 시작점 -> left포인터의 오른쪽 한칸 지점
			
			while (right < N && dp[right] - dp[left] < M) right++; // 구간의합이 M보다 작은경우 right포인터 오른쪽한칸이동
			if (dp[right] - dp[left] == M) answer++; // 구간의합이 M인경우 경우의수 1증가
			else continue; // 구간의합이 M보다 큰경우 패스
		}
		System.out.println(answer);
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다이나믹 프로그래밍
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 수열의 길이
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()); // 둘째 줄
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 1;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] <= arr[i]) {
				dp[i] = dp[i - 1] + 1;
			}
			answer = Math.max(answer, dp[i]);
		}
		Arrays.fill(dp, 1);
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] >= arr[i]) {
				dp[i] = dp[i - 1] + 1;
			}
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}

}
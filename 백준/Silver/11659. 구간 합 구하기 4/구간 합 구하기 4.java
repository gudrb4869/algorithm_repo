import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * n이 최대10만이므로
 * 구간합을 단순히 for문으로 구하면 O(N)이 걸리는데
 * m이 최대 10만이므로 10만 * 10만 100억초
 * 시간초과난다.
 * 1번째부터 i번째 숫자까지의 누적합을 저장하기 위해 dp 배열을 사용함
 * 구간 a와 b의 합을 구하기 위해 dp[b]에서 dp[a-1]을 빼서 O(1)만에 구할 수있게 된다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		int[] num = new int[n]; // n개의 수를 저장할 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken()); // 수 저장
		}
		
		int[] dp = new int[n + 1]; // 누적합 저장할 배열
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + num[i - 1]; // 누적합 저장
		}
		
		
		for (int i = 0; i < m; i++) { // 합을 구해야 하는 횟수만큼 반복
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 구간 시작
			int b = Integer.parseInt(st.nextToken()); // 구간 끝
			
			sb.append((dp[b] - dp[a - 1]) + "\n"); // 결과 저장
		}
		System.out.print(sb); // 출력
	}
	
}

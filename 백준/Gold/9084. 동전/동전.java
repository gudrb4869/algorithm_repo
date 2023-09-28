import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 배낭 문제와 다이나믹 프로그래밍 개념을 활용하여 풀었다.
 * 
 * i번째 동전 w원을 포함시켰을 때 m원을 만들수 있는 가짓수
 * D[0][j] = 0
 * D[i][0] = 1
 * D[i][m] = D[i - 1][m] + D[i][m - w]  (if, m >= w)
 * D[i][m] = D[i - 1][m]  (if, m < w)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수(1~10)
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 동전의 가지 수(1~20)
			st = new StringTokenizer(br.readLine()); // N가지 동전의 각 금액이 오름차순으로 정렬되어 주어짐
			
			int[] coin = new int[N];
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine()); // N가지 동전으로 만들어야 할 금액(1~10000)
			
			int[] dp = new int[M + 1];
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 1; j <= M; j++) {
					if (j >= coin[i]) {
						dp[j] += dp[j - coin[i]];
					}
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.print(sb);
	}

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 물품이 최대 100개이고 각 물건을 선택하는 경우와 선택하지 않는 경우를 모두고려한 경우의수의 최대개수는 2^100이므로 dfs나 백트래킹 알고리즘 사용시 시간초과가 난다.
 * 따라서 다이나믹 프로그래밍을 이용해 문제를 해결했다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int n, k, weight[], value[], dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 물품의 수
		k = Integer.parseInt(st.nextToken()); // 준서가 버틸 수 있는 무게 
		
		weight = new int[n + 1]; // 물품의 무게 저장할 배열 1번부터 쓰기 위해 n + 1 크기로 초기화
		value = new int[n + 1]; // 물품의 가치 저장할 배열 1번부터 쓰기 위해 n + 1 크기로 초기화
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken()); // 무게
			value[i] = Integer.parseInt(st.nextToken()); // 가치
		}
		
		dp = new int[n + 1][k + 1]; // dp 배열 생성
		
		System.out.println(knapsack()); // knapsack 결과 출력
		
	}

	/**
	 * 0-1 knapsack 알고리즘 (DP 이용)
	 * @return 배낭에 넣을 수 있는 물건들의 가치합의 최댓값
	 */
	private static int knapsack() {
		for (int i = 1; i <= n; i++) { // 1번부터 n번물건을 검사
			for (int j = 1; j <= k; j++) { // 가방이 버틸수있는 무게
				if (j >= weight[i]) { // 버틸수 있는 무게가 i번물건의 무게보다 크거나 같으면
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]); // i번물건을 배낭에 넣지 않은 가치합과 i번물건을선택한 가치합중 가장큰값을 저장
				} else { // 버틸수있는 무게가 i번물건의 무게보다 작으면
					dp[i][j] = dp[i - 1][j]; // i번물건을 배낭에 넣지 않음
				}
			}
		}
		return dp[n][k];
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 냅색 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken()); // 형택이가 적어도 늘리고 싶은 고객의 수(1~1000)
		int N = Integer.parseInt(st.nextToken()); // 형택이가 홍보할 수 있는 도시의 개수(1~20)
		
		int[][] dp = new int[N + 1][C + 1]; // 홍보할도시의개수 * 늘린고객의수
		for (int j = 1; j <= C; j++) {
			dp[0][j] = 987654321; // 최솟값으로 갱신해주기 위해 초기값 세팅
		}
		
		for (int i = 1; i <= N; i++) { // i번째 도시 정보
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 각 도시에서 홍보할 때 대는 비용
			int v = Integer.parseInt(st.nextToken()); // 그 비용으로 얻을 수 있는 고객의 수
			for (int j = 1; j <= C; j++) {
				int cnt = (j - 1) / v + 1; // i번째 도시로 j명의 고객을 얻기 위해 필요한 홍보 횟수 계산
				dp[i][j] = Math.min(dp[i - 1][j], w * cnt); // i-1번째 도시까지를 이용하여 j명을 얻었을때 비용과 i번도시를 val번홍보했을때 비용중 최소값을 dp[i][j]에 지정
				if (j >= v) { // 냅색 조건
					dp[i][j] = Math.min(dp[i][j], dp[i][j - v] + w); // i번도시까지를 이용하여 j-v명을 얻었을때 비용에 i번도시를 홍보하는데드는비용w를 더한값이 더작으면 최소값 갱신
				}
			}
		}
		
		System.out.println(dp[N][C]); // N개의 도시를 이용하여 C명을 얻기 위해 형택이가 투자해야하는 돈의 최솟값 출력
		
	}

}
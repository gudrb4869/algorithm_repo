import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다이나믹 프로그래밍, 비트마스킹을 이용하여 풀었다.
 * N개의 도시의 방문 상태를 나타내기 위해 1<<N개의 수를 사용했다.
 * 최소비용을 갖는 경로가 있을 때 N개의 도시 어느곳에서 출발해도 최소비용이 똑같이 나오기때문에
 * 문제를 풀기위해 0번도시에서 출발하는 것으로 고정시켜놓고 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static final int INF = 987654321;
	static int N, W[][], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 첫째 줄, 도시의 수
		
		W = new int[N][N]; // 비용행렬
		dp = new int[N][1 << N]; // 동적 테이블
		
		for (int i = 0; i < N; i++) { // 다음 N개의 줄 입력
			StringTokenizer st = new StringTokenizer(br.readLine()); // 비용 행렬이 주어짐
			
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken()); // 도시 i에서 j로 가기 위한 비용
			}
		}
		
		int answer = TSP(0, 1); // 외판원 순회 알고리즘 수행
		System.out.println(answer); // 외판원의 순회에 필요한 최소 비용 출력
	}

	/**
	 * 외판원 순회 알고리즘
	 * @param now 현재 노드
	 * @param visited 현재까지 방문한 노드 -> 비트마스킹으로 표현
	 * @return
	 */
	private static int TSP(int now, int visited) {
		
		if (visited == (1 << N) - 1) {
			return (W[now][0] == 0) ? INF : W[now][0];
		}
		
		if (dp[now][visited] != 0) {
			return dp[now][visited];
		}
		
		dp[now][visited] = INF;
		
		for (int i = 0; i < N; i++) {
			if (W[now][i] == 0) {
				continue; // 현재노드에서 다음노드로 갈 수 없는 경우 패스
			}
			
			if ((visited & (1 << i)) > 0) { // 이미 방문한 정점인 경우 패스
				continue;
			}
			
			// i번 노드 방문 처리 후 최소 비용 반환
			int temp = TSP(i, visited | (1 << i));
			dp[now][visited] = Math.min(dp[now][visited], W[now][i] + temp);
		}
		
		return dp[now][visited];
	}

}
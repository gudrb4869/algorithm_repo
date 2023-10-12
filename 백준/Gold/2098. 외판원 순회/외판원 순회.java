import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 도시가 5개 있고 최소비용 경로가
 * 4 -> 1 -> 2 -> 0 -> 3 이라고 할때
 * (4,1,2,0,3)은 (1,2,0,3,4), (2,0,3,4,1), (0,3,4,1,2), (3,4,1,2,0)과 모두 같은 결과를 가지게 된다.
 * 따라서 출발 도시를 0번도시로 고정시켜놓고 최소비용을 찾으면 된다.
 * 현재도시가 어디인지, 현재까지 방문한 도시가 어디인지를 체크하는 메서드를 재귀적으로 이용하면 풀수있다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, W[][], dp[][]; // 도시의 수, 비용행렬, 외판원순회에 필요한 최소비용
	static final int INF = 999999999; // 무한대값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 첫째 줄 입력, 도시의 수 (2 ~ 16)
		
		W = new int[N][N]; // 비용 행렬
		dp = new int[N][1 << N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(TSP(0, 1));
	}
	
	/**
	 * TSP 알고리즘
	 * @param now 현재 도시
	 * @param visited 현재까지 방문한 도시 체크(비트마스킹)
	 * @return 외판원 순회에 필요한 최소 비용
	 */
	private static int TSP(int now, int visited) {
		
		if (dp[now][visited] != 0) { // 메모이제이션된 경우 기존 값 갖다 사용
			return dp[now][visited];
		}

		dp[now][visited] = INF; // 무한대 값으로 초기화
		
		if (visited == (1 << N) - 1) { // 모든 도시를 방문완료한 경우
			
			if (W[now][0] > 0) { // 현재도시 now에서 출발도시 0으로 갈 수 있는 경우
				dp[now][visited] = W[now][0];
			}
			
		} else { // 아직 미방문 도시가 있는 경우
			
			for (int i = 0; i < N; i++) {
				if (W[now][i] == 0) {
					continue; // 현재도시 now에서 다음도시i로 갈 수 없는 경우 패스
				}
				
				if ((visited & (1 << i)) > 0) {
					continue; // i도시를 이미 방문한 경우 패스
				}
				
				int temp = TSP(i, visited | (1 << i)); // 다음 도시 i로 이동한 뒤 외판원 순회 재귀 호출했을때 최소 비용 획득
				dp[now][visited] = Math.min(dp[now][visited], W[now][i] + temp); // 현재 저장된값과 현재도시에서 i로갔을때 최소비용중 최솟값으로 갱신
			}
			
		}
		
		return dp[now][visited];
	}

}
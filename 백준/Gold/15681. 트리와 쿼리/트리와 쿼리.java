import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * DFS와 DP를 이용하여 문제 해결
 * 
 * 2023-12-24(일)
 * </pre>
 *
 */
public class Main {

	static List<Integer> tree[];
	static boolean visited[];
	static int N, R, Q, dp[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 트리의 정점의 수 (2 ~ 10^5)
		R = Integer.parseInt(st.nextToken()); // 루트의 번호 (1 ~ N)
		Q = Integer.parseInt(st.nextToken()); // 쿼리의 수 (1 ~ 10^5)
		
		tree = new List[N + 1];
		visited = new boolean[N + 1];
		dp = new int[N + 1];
		Arrays.fill(dp, 1); // 각 정점을 루트로하는 서브트리에 속한 정점의 수 1로 초기화
		
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine()); // 트리에 속한 간선의 정보
			
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			tree[U].add(V);
			tree[V].add(U);
		}
		
		dfs(R);
		
		for (int i = 0; i < Q; i++) {
			int U = Integer.parseInt(br.readLine());
			
			sb.append(dp[U]).append("\n");
		}
		
		System.out.print(sb);
	}

	// DFS
	static int dfs(int cur) {
		visited[cur] = true;
		for (int next : tree[cur]) {
			if (!visited[next]) {
				dp[cur] += dfs(next);
			}
		}
		return dp[cur];
	}
	
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * LCA + DP
 * </pre>
 */
public class Main {

	static int N, H, parent[][], depth[]; // 노드의 개수, 트리의 최대높이, 부모 노드 정보, 노드의 깊이
	static boolean visited[]; // 해당 정점이 계산되었는지 여부
	static List<Integer> graph[]; // 트리 정보
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		
		H = getMaxHeight();
		
		parent = new int[N + 1][H];
		depth = new int[N + 1];
		visited = new boolean[N + 1];
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine()); // 트리 상에서 연결된 두 정점
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			graph[B].add(A);
		}
		
		setParent(); // 전체 노드의 부모 관계 설정
		
		int M = Integer.parseInt(br.readLine()); // 가장 가까운 공통 조상을 알고싶은 쌍의 개수
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 정점 쌍
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.print(sb);
	}

	static int lca(int a, int b) {
		// b가 a보다 더 깊도록 설정
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// a와 b의 깊이를 통일
		for (int i = H - 1; i >= 0; i--) {
			if (depth[b] - depth[a] >= (1 << i)) {
				b = parent[b][i];
			}
		}
		
		// 부모가 같아지도록 설정
		if (a == b) return a;
		
		for (int i = H - 1; i >= 0; i--) {
			// 조상을 향해 거슬러 올라감
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		// 이후의 부모가 찾고자 하는 조상이다.
		return parent[a][0];
	}

	// 트리의 최대 높이???
	static int getMaxHeight() {
		return (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
	}

	static void setParent() {
		dfs(1, 0); // 루트는 1번!
		
		// dp: j번째노드의 2^0, 2^1, ... 2^i 번째 부모 노드 기록
		for (int i = 1; i < H; i++) {
			for (int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	static void dfs(int x, int d) {
		visited[x] = true; // 현재 노드 방문 체크
		depth[x] = d; // 현재 노드의 깊이 기록
		for (int y : graph[x]) { // 현재 노드와 연결된 다음 정점들 탐색 
			if (visited[y]) continue; // 이미 방문한 노드인 경우 skip
			
			parent[y][0] = x; // 다음 정점의 부모 노드는 x라고 기록
			dfs(y, d + 1); // dfs 재귀호출
		}
	}

}
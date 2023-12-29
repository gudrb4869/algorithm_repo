import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * DFS + DP를 이용한 LCA 알고리즘
 * </pre>
 */
public class Main {

	static int N, H, d[], parent[][];
	static boolean visited[];
	static List<Integer> graph[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		H = getMaxHeight();
		
		graph = new List[N + 1];
		d = new int[N + 1];
		parent = new int[N + 1][H];
		visited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) { // N개의 노드로 이루어진 트리의 간선개수 => N-1개
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		setParent(); // 노드의 조상 기록
		
		int M = Integer.parseInt(br.readLine()); // 가장 가까운 공통 조상을 알고싶은 쌍의 개수
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.print(sb);
		
	}

	static int lca(int a, int b) {
		// b의 깊이를 더 깊게
		if (d[a] > d[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		// a와 b의 깊이를 동일하게 맞춤
		for (int i = H - 1; i >= 0; i--) {
			if (d[b] - d[a] >= (1 << i)) {
				b = parent[b][i];
			}
		}
		
		if (a == b) // a와 b가 같은 경우
			return a;
		
		// a의 조상과 b의 조상이 동일할때까지 반복
		for (int i = H - 1; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		// a와 b의 공통 조상 리턴
		return parent[a][0];
	}

	static int getMaxHeight() {
		// 트리의 최대 높이 계산
		return (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
	}

	static void setParent() {
		dfs(1, 0); // 루트는 1번이고, 루트의 깊이는 0이다.
		
		// dp를 이용하여 j번 노드의 2^0번째, 2^1번째, ..., 2^i번째 조상의 노드번호 계산
		for (int i = 1; i < H; i++) {
			for (int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	static void dfs(int x, int depth) {
		// dfs를 이용하여 x번 노드의 깊이 기록, x번노드의 자식노드 y들에 대해 부모노드기록 및 dfs재귀호출
		visited[x] = true;
		d[x] = depth;
		
		for (int y : graph[x]) {
			if (visited[y])
				continue;
			
			parent[y][0] = x;
			dfs(y, depth + 1);
		}
	}

}
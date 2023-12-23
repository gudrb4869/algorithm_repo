import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * LCA(Lowest Common Ancestor) 가장 가까운 공통 조상 문제
 * 
 * 매 쿼리마다 부모 방향으로 올라가기 때문에 최악의 경우 O(N)의 시간복잡도를 가진다.
 * 2023-12-23(토)
 * </pre>
 *
 */
public class Main {

	static int parent[], d[];
	static List<Integer> tree[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 트리를 구성하는 노드의 수
			
			parent = new int[N + 1];
			d = new int[N + 1];
			tree = new List[N + 1];
			
			for (int i = 1; i <= N; i++) {
				tree[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N - 1; i++) { // 정점이 N개인 트리의 간선의 개수 => N-1개
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				tree[A].add(B);
				parent[B] = A;
			}
			
			for (int i = 1; i <= N; i++) {
				if (parent[i] == 0) {
					dfs(i, 0);
					break;
				}
			}
			
			st = new StringTokenizer(br.readLine()); // 가장 가까운 공통 조상을 구할 두 노드
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.print(sb);
	}

	static int lca(int a, int b) {
		while (d[a] != d[b]) {
			if (d[a] > d[b]) a = parent[a];
			else b = parent[b];
		}
		
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	static void dfs(int x, int depth) {
		d[x] = depth;
		for (int y : tree[x]) {
			dfs(y, depth + 1);
		}
	}

}
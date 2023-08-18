import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 무방향 그래프에서 정점 V를 기준으로 DFS 수행결과와 BFS 수행결과를 구해야하는 문제
 * 방문정점이 여러개인 경우에는 정점 번호가 작은 것을 먼저 방문함
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, V;
	static List<Integer>[] graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호
		
		graph = new List[N + 1]; // 각 정점마다 연결된 정점의 번호를 저장할 리스트 배열
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>(); // 1번정점부터 N번정점까지 어레이리스트 초기화
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b); // 양방향그래프이므로 a에 대해서 b추가
			graph[b].add(a); // 양방향그래프이므로 b에 대해서 a추가
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]); // 정점의 번호를 낮은 순부터 찾아가기 위해 정렬
		}
		
		visited = new boolean[N + 1]; // 각 정점의 방문여부 체크할 배열
		dfs(V); // DFS 수행 결과
		sb.append("\n");
		
		visited = new boolean[N + 1]; // 각 정점의 방문여부 체크할 배열
		bfs(V); // BFS 수행 결과
		sb.append("\n");
		
		System.out.print(sb); // 결과 출력
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		
		while (!q.isEmpty()) {
			int current = q.poll();
			sb.append(current).append(" ");
			
			for (int next : graph[current]) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
	}

	private static void dfs(int current) {
		visited[current] = true;
		sb.append(current).append(" ");
		for (int next : graph[current]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
	}

}
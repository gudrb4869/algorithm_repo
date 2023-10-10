import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그래프 탐색 문제. 깊이 우선 탐색과 인접 리스트를 이용하여 문제 해결했다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static boolean[] visited;
	static List<Integer>[] graph;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 창용 마을에 사는 사람의 수
			M = Integer.parseInt(st.nextToken()); // 서로를 알고 있는 사람의 관계 수
			
			visited = new boolean[N + 1]; // 방문 배열
			graph = new List[N + 1]; // 인접 리스트
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				graph[A].add(B);
				graph[B].add(A);
			}
			
			int answer = 0; // 무리의 개수
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					answer++;
					dfs(i);
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		
		System.out.print(sb);
	}

	// 깊이 우선 탐색
	static void dfs(int cur) {
		visited[cur] = true;
		for (int next : graph[cur]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
	}

}
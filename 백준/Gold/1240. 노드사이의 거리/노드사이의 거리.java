import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 노드의 개수
		int M = Integer.parseInt(st.nextToken()); // 거리를 알고 싶은 노드 쌍의 개수
		
		List<int[]>[] graph = new List[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 두 점의 거리
			
			graph[a].add(new int[] {b, d});
			graph[b].add(new int[] {a, d});
		}
		
		boolean[] visited = new boolean[N + 1];
		Queue<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Arrays.fill(visited, false);
			q.clear();
			
			visited[a] = true;
			q.offer(new int[] {a, 0});
			
			while (!q.isEmpty()) {
				
				int[] info = q.poll();
				int cur = info[0], distance = info[1];
				
				if (cur == b) {
					sb.append(distance).append("\n");
					break;
				}
				
				for (int[] data : graph[cur]) {
					int next = data[0], dist = data[1];
					
					if (!visited[next]) {
						q.offer(new int[] {next, distance + dist});
						visited[next] = true;
					}
				}
			}
		}
		
		System.out.print(sb);
	}

}
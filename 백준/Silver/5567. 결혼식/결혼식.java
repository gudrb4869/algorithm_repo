import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 상근이의 동기의 수
		int n = Integer.parseInt(br.readLine());
		// 리스트의 길이
		int m = Integer.parseInt(br.readLine());
		
		Set<Integer>[] graph = new Set[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new HashSet<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 친구 관계 (a와 b가 친구관계, b와 a도 친구관계)
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int[] dist = new int[n + 1];
		Arrays.fill(dist, 987654321);
		dist[1] = 0;
		boolean[] visited = new boolean[n + 1];
		visited[1] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				if (!visited[next]) {
					dist[next] = dist[cur] + 1;
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		
		int answer = 0;
		for (int j = 2; j <= n; j++) {
			if (dist[j] <= 2) answer++;
		}

		System.out.println(answer);
		
	}

}
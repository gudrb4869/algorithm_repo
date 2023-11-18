import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
		
		answer = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i);
		}
		
		int max = Arrays.stream(answer).max().getAsInt();
		for (int i = 1; i <= N; i++) {
			if (answer[i] == max)
				sb.append(i + " ");
		}
		
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if (!visited[next]) {
					visited[next] = true;
					answer[next]++;
					q.offer(next);
				}
			}
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean check;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			dfs(i, 1);
			if (check) break;
		}
		if (check) System.out.println(1);
		else System.out.println(0);
	}
	private static void dfs(int i, int cnt) {
		if (cnt == 5) {
			check = true;
			return;
		}
		visited[i] = true;
		for (int j : graph[i]) {
			if (!visited[j]) {
				dfs(j, cnt + 1);
			}
		}
		visited[i] = false;
	}
}
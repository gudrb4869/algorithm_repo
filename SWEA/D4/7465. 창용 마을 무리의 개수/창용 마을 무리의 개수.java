import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	private static ArrayList<Integer>[] graph;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[n + 1];
			for (int i = 1; i < n + 1; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			visited = new boolean[n + 1];
			
			int answer = 0;
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					answer++;
					dfs(i);
				}
			}
			
			sb.append("#" + test_case + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}
	
	private static void dfs(int x) {
		visited[x] = true;
		
		for (int j : graph[x]) {
			if (!visited[j]) {
				dfs(j);
			}
		}
		
	}

}

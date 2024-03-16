import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, A[];
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스의 개수
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			// 순열의 크기
			int N = Integer.parseInt(br.readLine());
			
			A = new int[N + 1];
			visited = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				int j = Integer.parseInt(st.nextToken());
				A[i] = j;
			}
			
			int count = 0;

			for (int i = 1; i <= N; i++) {
				if (visited[i]) continue;
				
				count++;
				dfs(i);
			}
			
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}

	static void dfs(int cur) {
		visited[cur] = true;
		int next = A[cur];
		
		if (!visited[next]) {
			dfs(next);
		}
	}

}
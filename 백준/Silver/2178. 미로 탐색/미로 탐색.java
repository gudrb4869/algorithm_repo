import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] arr = new char[n][m];
		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}	
		bfs(n, m, arr, visited);
	}
	
	private static void bfs(int n, int m, char[][] arr, boolean[][] visited) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{0, 0, 1});
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int r = p[0], c = p[1], cnt = p[2];
			if (r == n - 1 && c == m - 1) {
				System.out.println(cnt);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
					if (arr[nr][nc] == '1' && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc, cnt + 1});
					}
				}
			}
		}
	}
}
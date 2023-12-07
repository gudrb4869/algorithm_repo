import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 깊이우선탐색
 * 시뮬레이션
 * 2023-12-07(목)
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static int N, M, arr[][], answer;
	static boolean visited[][], check[][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) count++;
				}
			}
			
			if (count == 0) break;
			
			visited = new boolean[N][M]; // false:공기가 아닌곳, true: 공기인곳
			check = new boolean[N][M];
			
			dfs(0, 0);
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (arr[r][c] == 1) {
						int cnt = 0;
						
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
							
							if (visited[nr][nc]) cnt++;
						}
						
						if (cnt >= 2) {
							check[r][c] = true;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j]) arr[i][j] = 0;
				}
			}
			answer++;
		}
		
		System.out.println(answer);
		
	}

	static void dfs(int r, int c) {
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == 1 || visited[nr][nc]) continue;
			
			dfs(nr, nc);
		}
	}

}
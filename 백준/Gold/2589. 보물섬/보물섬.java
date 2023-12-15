import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비우선탐색
 * 브루트포스 알고리즘
 * 2023-12-15(금)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보물 지도의 세로의 크기
		int M = Integer.parseInt(st.nextToken()); // 보물 지도의 가로의 크기
		
		char[][] map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'W') continue;
				
				Queue<int[]> q = new ArrayDeque<>();
				boolean[][] visited = new boolean[N][M];
				q.offer(new int[] {i, j, 0});
				visited[i][j] = true;
				int result = 0;
				
				int[] dr = {-1, 0, 1, 0};
				int[] dc = {0, 1, 0, -1};
				
				while (!q.isEmpty()) {
					int[] info = q.poll();
					int r = info[0], c = info[1], dist = info[2];
					result = Math.max(result, dist);
					
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'W' || visited[nr][nc]) {
							continue;
						}
						
						q.offer(new int[] {nr, nc, dist + 1});
						visited[nr][nc] = true;
					}
				}
				
				answer = Math.max(answer, result);
			}
		}
		
		System.out.println(answer);
	}

}
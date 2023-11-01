import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다익스트라, 너비우선탐색
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 미로의 가로 크기
		int N = Integer.parseInt(st.nextToken()); // 미로의 세로 크기
		
		char[][] maze = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			maze[i] = br.readLine().toCharArray(); // 0: 빈방, 1: 벽
		}
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			}
		});
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = 987654321;
			}
		}
		
		pq.offer(new int[] {0, 0, 0});
		dist[0][0] = 0;
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int r = info[0], c = info[1], cnt = info[2];
			if (r == N - 1 && c == M - 1) {
				System.out.println(cnt);
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				
				if (maze[nr][nc] == '1' && dist[nr][nc] > dist[r][c] + 1) {
					dist[nr][nc] = dist[r][c] + 1;
					pq.offer(new int[] {nr, nc, dist[nr][nc]});
				} else if (maze[nr][nc] == '0' && dist[nr][nc] > dist[r][c]) {
					dist[nr][nc] = dist[r][c];
					pq.offer(new int[] {nr, nc, dist[nr][nc]});
				}
				
			}
		}
	}

}
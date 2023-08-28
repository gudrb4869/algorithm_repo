import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 섬들을 숫자로 구별시켜준 다음
 * bfs로 섬과 섬 사이의 최단거리를 계산한 후 최솟값을 출력함
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 지도의 크기
		
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0=바다 1=육지
			}
		}

		int answer = 200; // 가장 짧은 다리의 길이
		int num = 1; // 섬의 번호
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) { // 섬이고 아직 미방문한 곳이면
					Queue<int[]> q = new ArrayDeque<>();
					visited[i][j] = true; // 방문 체크
					q.offer(new int[] {i, j});
					map[i][j] = num; // 섬의 번호 지정
					 
					// 너비 우선 탐색
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1];
						
						for (int d = 0; d < 4; d++) { // 4방향 탐색
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 0) {
								continue; // 지도 범위 밖이거나 방문한 지점이거나 바다면 skip
							}
							
							visited[nr][nc] = true; // 방문 체크
							map[nr][nc] = num; // 섬의 번호 지정
							q.offer(new int[] {nr, nc});
						}
					}
					
					num++; // 다음 섬의 번호 위해 1증가
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) { // 섬인 경우
					for (int k = 0; k < N; k++) {
						Arrays.fill(visited[k], false); // 방문 배열 false로 초기화
					}
					
					// 너비 우선탐색
					Queue<int[]> q = new ArrayDeque<>();
					visited[i][j] = true;
					q.offer(new int[] {i, j, 0}); // 현재 위치, 다리의 길이
					
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1], t = info[2];
						
						if (map[r][c] > 0 && map[r][c] != map[i][j]) { // 다른 섬에 도착한 경우
							answer = Math.min(answer, t - 1); // 다리의 길이 최솟값으로 갱신
							break;
						}
						
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == map[i][j]) {
								continue;
							}
							
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc, t + 1});
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}

}
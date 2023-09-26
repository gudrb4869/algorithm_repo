import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색, 비트마스킹을 이용해 문제 해결했음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, dist[][][];
	static char[][] maze;
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static Queue<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 미로의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 미로의 가로 크기
		
		maze = new char[N][M]; // 미로의 모양을 저장할 2차원 배열
		dist = new int[N][M][1 << 6]; // 각좌표의 열쇠획득상태에 따른 이동횟수를 저장할 배열,A열쇠~F열쇠의 획득상태를 0~63까지 비트로 표현
		
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray(); // i번째 행 미로의 모양
			for (int j = 0; j < M; j++) {
				Arrays.fill(dist[i][j], 987654321); // 이동횟수를 무한대로 초기화
				maze[i][j] = input[j]; // 미로의 각 위치 정보를 저장
				if (maze[i][j] == '0') { // 민식이가 있는 위치인 경우
					dist[i][j][0] = 0; // 이동횟수 0으로 세팅
					q.offer(new int[] {i, j, 0, 0}); // 큐에 상태 삽입
				}
			}
		}
		
		System.out.println(BFS()); // 출력 
	}

	// 너비 우선 탐색
	private static int BFS() {
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1], s = info[2], cnt = info[3]; // 행, 열, 열쇠상태(비트마스킹), 이동 횟수
			
			if (maze[r][c] == '1') { // 출구에 도착한 경우
				return cnt; // 민식이가 미로를 탈출하는데 드는 이동횟수의 최솟값
			}
			
			for (int d = 0; d < 4; d++) { // 상하좌우 네방향 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 미로바깥으로 벗어났거나 벽인 경우 skip
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || maze[nr][nc] == '#') {
					continue;
				}
				
				if (maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') { // 열쇠가 있는 곳에 도달한 경우
					int ns = s | 1 << (maze[nr][nc] - 'a'); // 비트마스킹 처리하여 해당 열쇠를 획득한 상태로 변경
					if (dist[nr][nc][ns] > cnt + 1) { // 이동 횟수가 저장된 이동횟수보다 더 작은 경우에만 갱신후 큐에 삽입
						dist[nr][nc][ns] = cnt + 1;
						q.offer(new int[] {nr, nc, ns, cnt + 1});
					}
				} else if (maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F' && (s & 1 << (maze[nr][nc] -'A')) > 0 && dist[nr][nc][s] > cnt + 1) {
					// 문에 도달했는데, 열쇠가 있고, 이동횟수가 저장된 이동횟수보다 작은 경우에만 갱신후 큐에 삽입
					dist[nr][nc][s] = cnt + 1;
					q.offer(new int[] {nr, nc, s, cnt + 1});
				} else if ((maze[nr][nc] == '0' || maze[nr][nc] == '.' || maze[nr][nc] == '1') && dist[nr][nc][s] > cnt + 1) {
					// 민식이가 있던 처음위치이거나, 빈 칸이거나, 출구이면서 이동횟수가 저장된 이동횟수보다 작은 경우에만 갱신후 큐에 삽입
					dist[nr][nc][s] = cnt + 1;
					q.offer(new int[] {nr, nc, s, cnt + 1});
				}
			}
		}
		return -1; // 민식이가 미로를 탈출 할 수 없음
	}


}
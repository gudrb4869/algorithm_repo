import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int R, C;
	static final int INF = 987654321; // 무한대값
	static char[][] map = new char[R][C]; // 티떱숲의 지도
	static boolean[][] visited;
	static int[][] spread;
	
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째줄 입력
		
		R = Integer.parseInt(st.nextToken()); // 티떱숲의 세로길이
		C = Integer.parseInt(st.nextToken()); // 티떱숲의 가로길이
		
		map = new char[R][C]; // 티떱숲의 지도
		visited = new boolean[R][C];
		spread = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				spread[i][j] = INF;
			}
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		int startR = -1, startC = -1;
		for (int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];
				if (map[i][j] == '*') {
					spread[i][j] = 0;
					q.offer(new int[] {i, j});
				} else if(map[i][j] == 'S') {
					startR = i;
					startC = j;
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] != '.' || spread[nr][nc] <= spread[r][c] + 1) {
					continue;
				}
				
				spread[nr][nc] = spread[r][c] + 1;
				q.offer(new int[] {nr, nc});
			}
		}
		
		int result = BFS(startR, startC);
		
		System.out.println(result == -1 ? "KAKTUS" : result);
	}

	private static int BFS(int startR, int startC) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[startR][startC] = true;
		q.offer(new int[] {startR, startC, 0});
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1], t = info[2];
			
			if (map[r][c] == 'D') {
				return t;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 티떱숲을 벗어난 경우 
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				// 돌 또는 물이 있는 지역인 경우
				if (map[nr][nc] == 'X' || map[nr][nc] == '*') {
					continue;
				}
				
				// 이미 방문한 지점이거나, 이미 물로 차있어서 못가는 경우
				if (visited[nr][nc] || spread[nr][nc] <= t + 1) {
					continue;
				}
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, t + 1});
			}
		}
		return -1;
	}

}
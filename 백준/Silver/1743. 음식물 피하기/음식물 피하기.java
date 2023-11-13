import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 깊이 우선 탐색
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static int N, M, count;
	static boolean[][] waste, visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력 첫째 줄
		N = Integer.parseInt(st.nextToken()); // 통로의 세로 길이(1 ~ 100)
		M = Integer.parseInt(st.nextToken()); // 통로의 가로 길이(1 ~ 100)
		int K = Integer.parseInt(st.nextToken()); // 음식물 쓰레기의 개수(1 ~ N*M)
		
		waste = new boolean[N][M]; // 통로의 음식물 쓰레기 존재 여부
		visited = new boolean[N][M]; // 깊이 우선 탐색시 방문 확인 여부
		
		for (int k = 0; k < K; k++) { // K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어짐(중복 x)
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			waste[r][c] = true;
		}
		
		int answer = 0; // 음식물 중 가장 큰 음식물의 크기
		// 통로 전체 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (waste[i][j] && !visited[i][j]) { // 쓰레기가 있는 곳이고 미방문 위치인 경우
					count = 0; // 음식물의 크기
					dfs(i, j); // 깊이 우선 탐색
					answer = Math.max(answer, count); // 크기가 큰 걸로 갱신
				}
			}
		}
		
		System.out.println(answer); // 가장 큰 음식물의 크기 출력
		
		
	}

	static void dfs(int r, int c) {
		visited[r][c] = true;
		count++;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || !waste[nr][nc] || visited[nr][nc]) {
				continue; // 통로 범위 벗어났거나, 쓰레기가 없는 곳이거나, 이미 방문한 곳인경우 skip
			}
			
			dfs(nr, nc);
		}
	}

}
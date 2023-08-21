import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <pre>
 * 그래프 탐색에 BFS를 활용하여 문제를 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N; // 그림의 가로세로 길이
	static char[][] grid; // 그림의 정보를 저장할 2차원배열
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		N = Integer.parseInt(br.readLine()); // 그림의 가로세로 길이
		grid = new char[N][]; // 그림의 정보를 저장할 2차원배열
		
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray(); // 그림의 정보 저장
		}
		
		visited = new boolean[N][N]; // 정점의 방문관리 배열
		// 색약이 아닌사람
		int ans1 = 0; // 적록색약이 아닌 사람이 봤을 때 구역의 수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) { // 아직 방문안한곳이면
					ans1++; // 구역개수 1증가
					bfs(i, j); // bfs 그래프 탐색
				}
			}
		}
		
		// 색약인 사람이 봤을때그림
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 'R' || grid[i][j] == 'G') {
					grid[i][j] = 'X'; // 빨강과 초록을 동일한 색으로 변환 
				}
			}
		}
		
		visited = new boolean[N][N]; // 정점의 방문관리 배열
		// 색약인 사람
		int ans2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) { // 아직 방문안한곳이면
					ans2++; // 구역개수 1증가
					bfs(i, j); // bfs 그래프 탐색
				}
			}
		}
		
		sb.append(ans1).append(" ").append(ans2).append("\n");
		System.out.print(sb);
		
	}

	/**
	 * bfs 그래프 탐색
	 * @param i 시작위치 행
	 * @param j 시작위치 열
	 */
	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[i][j] = true; // 시작위치 방문체크
		q.offer(new int[] {i, j}); // 큐에 삽입
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1]; // 현재위치 행열
			
			for (int d = 0; d < 4; d++) { // 4방향 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) { // 그림 밖이면 진행하지 않음
					continue;
				}
				
				if (!visited[nr][nc] && grid[nr][nc] == grid[i][j]) { // 아직방문하지 않은곳이고, 다음위치의 색이 시작위치의 색과 같으면
					visited[nr][nc] = true; // 방문체크하고
					q.offer(new int[] {nr, nc}); // 큐에 삽입
				}
				
			}
		}
	}

}
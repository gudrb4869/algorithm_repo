import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * bfs를 이용해서 풀었다.
 * @author 박형규
 */
public class Main {

	static int N, M, K, x1, y1, x2, y2; // 체육관 세로길이, 가로길이, 1초에 이동할수 있는 칸의 최대개수, 시작좌표, 끝좌표
	static char[][] arr; // 체육관 상태 저장할 2차원 배열
	static int[][] time; // 해당 지점에 방문한 최단 시간 저장
	static boolean[][] visited; // 해당 지점에 방문 여부
	static int[] dr = {-1,1,0,0}; // 상,하,좌,우
	static int[] dc = {0,0,-1,1}; // 상,하,좌,우
	static StringBuilder sb = new StringBuilder(); // 출력스트림
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 기준 입력값 분리
		N = Integer.parseInt(st.nextToken()); // 체육관 세로길이(행)
		M = Integer.parseInt(st.nextToken()); // 체육관 가로길이(열)
		K = Integer.parseInt(st.nextToken()); // 1초에 이동할 수 있는 칸의 최대 개수 K
		
		arr = new char[N][M]; // 체육관의 상태를 저장할 2차원배열
		time = new int[N][M]; // 해당칸에 도달하기 위한 최소시간 저장할 2차원배열
		visited = new boolean[N][M]; // 해당칸의 방문여부
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray(); // 체육관의 상태 저장
		}
		
		st = new StringTokenizer(br.readLine());
		
		x1 = Integer.parseInt(st.nextToken()) - 1; // 시작좌표 행(문제에선1~N까지인데 0부터시작하기위해 -1보정)
		y1 = Integer.parseInt(st.nextToken()) - 1; // 시작좌표 열(문제에선1~M까지인데 0부터시작하기위해 -1보정)
		x2 = Integer.parseInt(st.nextToken()) - 1; // 끝좌표 행
		y2 = Integer.parseInt(st.nextToken()) - 1; // 끝좌표 열
		
		bfs(); // 너비 우선 탐색
		
		System.out.print(sb); // 결과 출력
	}
	
	/**
	 * 너비 우선 탐색
	 */
	private static void bfs() {
		
		Queue<int[]> q = new ArrayDeque<>(); // 큐
		visited[x1][y1] = true; // 시작지점 방문 체크
		q.offer(new int[] {x1, y1}); // 시작 지점행, 시작 지점열
		
		while (!q.isEmpty()) { // 반복 조건
			int[] info = q.poll();
			int r = info[0], c = info[1]; // 현재 위치

			// 도착위치에 도달한 경우 경과 시간 리턴
			if (r == x2 && c == y2) {
				sb.append(time[r][c]).append("\n");
				return;
			}
			
			for (int i = 0; i < 4; i++) { // 4방향 탐색
				for (int j = 1; j <= K; j++) { // 1초동안 같은방향으로 1부터 K칸 이동가능
					int nr = r + dr[i] * j;
					int nc = c + dc[i] * j;

					// 체육관 범위밖으로 나왔거나 벽과 마주친 경우 중단
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == '#') {
						break;
					}
					// 이미 해당지점방문했는데 해당지점 방문에 드는시간이 현재지점에방문에 드는시간보다 작거나 같은경우 중단
					if (visited[nr][nc] && time[nr][nc] <= time[r][c]) {
						break;
					}
					
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						time[nr][nc] = time[r][c] + 1;
						q.offer(new int[] {nr, nc});  // 이동가능한 칸 1로 세팅하고, 경과시간 1증가시켜주고, 방향도 i로 세팅
					}
				}
			}
		}
		
		sb.append(-1).append("\n");
	}

}

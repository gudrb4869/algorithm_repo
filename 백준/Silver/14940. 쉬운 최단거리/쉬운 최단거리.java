import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색을 통해 그래프 탐색을 해 문제를 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫줄
		
		int n = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		int m = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		
		int[][] arr = new int[n][m]; // n * m 크기의 지도
		boolean[][] visited = new boolean[n][m]; // 각 지점의 방문여부
		int[][] result = new int[n][m]; // 출력 결과 저장할 배열
		
		Queue<int[]> q = new ArrayDeque<>(); // 너비 우선탐색에 사용할 큐
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 지점의 정보 저장
				if (arr[i][j] == 2) { // 목표지점인 경우
					q.offer(new int[] {i, j, 0}); // 큐에 삽입
					visited[i][j] = true; // 방문체크
				}
			}
		}
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		while (!q.isEmpty()) { // 너비 우선탐색
			int[] info = q.poll();
			int r = info[0], c = info[1], dist = info[2]; // 행, 열, 거리
			result[r][c] = dist; // 거리 갱신
			
			for (int d = 0; d < 4; d++) { // 4방향 탐색
				int nr = r + dr[d], nc = c + dc[d];
				
				// 지도의 범위를 벗어난 경우, 갈수 없는땅인 경우, 이미 방문한 지점인 경우 skip
				if (nr < 0 || nr >= n || nc < 0 || nc >= m || arr[nr][nc] == 0 || visited[nr][nc])
					continue;
				
				visited[nr][nc] = true; // 방문체크
				q.offer(new int[] {nr, nc, dist + 1}); // 큐에 삽입
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) { // 원래 갈수 있는 땅인데 도달할 수 없는 위치인경우
					sb.append(-1); // -1출력
				} else { // 나머지의 경우 result값출력
					sb.append(result[i][j]);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
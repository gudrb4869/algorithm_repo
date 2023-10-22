import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색 이용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄 입력(도화지의 세로 크기, 가로 크기)
		
		int n = Integer.parseInt(st.nextToken()); // 도화지의 세로 크기
		int m = Integer.parseInt(st.nextToken()); // 도화지의 가로 크기
		
		int[][] arr = new int[n][m]; // 그림의 정보 저장할 n*m 2차원 배열
		boolean[][] visited = new boolean[n][m];
		int count = 0; // 그림의 개수
		int maxArea = 0; // 가장 넓은 그림의 넓이
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					count++; // 그림의 개수 1 증가
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					int area = 0; // 그림의 넓이
					
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1];
						area++; // 그림의 넓이 1 증가
						
						for (int d = 0; d < 4; d++) { // 현재 위치 기준 상,우,하,좌 4방 탐색
							int nr = r + dr[d], nc = c + dc[d];
							
							if (nr < 0 || nr >= n || nc < 0 || nc >= m || arr[nr][nc] == 0 || visited[nr][nc]) {
								continue;
							}
							
							q.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
					
					maxArea = Math.max(maxArea, area);
				}
			}
		}
		
		System.out.println(count); // 첫째줄 출력(그림의 개수)
		System.out.println(maxArea); // 둘째 줄 출력(가장 넓은 그림의 넓이, 그림이 하나도 없는 경우 0 출력)
	}

}
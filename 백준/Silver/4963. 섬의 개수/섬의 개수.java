import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 깊이 우선 탐색으로 푸는 그래프 탐색 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int w, h, arr[][]; // 지도의 너비, 높이, 지도정보
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static boolean visited[][]; // 각 지점 방문 여부 체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		while (true) { // 여러 개의 테스트 케이스 반복
			st = new StringTokenizer(br.readLine()); // 각 테스트 케이스의 첫째 줄
			w = Integer.parseInt(st.nextToken()); // 너비
			if (w == 0) {
				break;
			}
			h = Integer.parseInt(st.nextToken()); // 높이
			
			int cnt = 0; // 섬의 개수
			arr = new int[h + 2][w + 2]; // 바깥에 바다로 둘러싸기 위해 2씩 더길게 만들음
			visited = new boolean[h + 2][w + 2];
			
			for (int i = 1; i <= h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 지도 정보 저장
				}
			}
			
			for (int i = 1; i <= h; i++) {
				for (int j = 1; j <= w; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) { // 현재 지점이 땅이면서, 미방문땅인 경우
						cnt++; // 섬의 개수 1증가
						dfs(i, j); // 깊이 우선 탐색 시작
					}
				}
			}
			
			sb.append(cnt).append("\n"); // 해당 테스트 케이스에서 섬의 개수 스트링빌더에 삽입 
		}
		
		System.out.print(sb); // 출력
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true; // 현재 위치 방문체크
		
		for (int d = 0; d < 8; d++) { // 8방향 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 바다이거나, 이미 방문한 땅인 경우 skip
			if (arr[nr][nc] == 0 || visited[nr][nc]) {
				continue;
			}
			
			dfs(nr, nc); // 깊이 우선 탐색
		}
	}

}
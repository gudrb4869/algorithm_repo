import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 깊이우선탐색을 이용해 문제를 풀었다. 또 이미 방문한 알파벳이면 다시 방문하지 않도록 가지치기도 해주었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[] dr = {-1, 0, 1, 0}; // 상,우,하,좌
	static int[] dc = {0, 1, 0, -1}; // 상,우,하,좌
	static boolean[] visited = new boolean[26]; // A부터 Z까지 이미 지나왔는지 여부
	static char[][] arr; // 알파벳 저장할 2차원배열
	static int R, C, answer = 0; // 세로, 가로, 말이 지날수있는 최대칸수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로
		
		arr = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				arr[i][j] = cs[j]; // 보드에 적혀있는 알파벳정보
			}
		}
		
		dfs(0, 0, 1);
		System.out.println(answer);
	}

	/**
	 * 깊이 우선 탐색 + 백트래킹
	 * @param r // 현재 말이 있는 위치 행
	 * @param c // 현재 말이 있는 위치 열
	 * @param cnt // 현재 말이 지나온 칸의 수
	 */
	private static void dfs(int r, int c, int cnt) {
		visited[arr[r][c] - 'A'] = true; // 현재 위치에 있는 알파벳 방문체크
		answer = Math.max(answer, cnt); // 저장되어있는 최대칸수와 현재 칸수중 가장 큰값으로 갱신
		
		for (int i = 0; i < 4; i++) { // 4방향 탐색
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[arr[nr][nc] - 'A']) {
				continue; // 보드를 벗어났거나, 이미 지나온 알파벳이면 재귀호출하지 않고 넘어감
			}
			
			dfs(nr, nc, cnt + 1); // 다음위치에서 말이 지나온칸수를 1늘려주고 재귀호출
		}
		visited[arr[r][c] - 'A'] = false; // 현재 위치에 있는 알파벳 방문해제
	}

}
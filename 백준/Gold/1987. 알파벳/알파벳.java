import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 백트래킹 알고리즘을 사용
 * 4방으로 이동하기 위해 dr, dc 이용
 * 이미 지나온 알파벳이 있는지 확인하기 위해 visited 배열 선언
 * </pre>
 * @author 박형규
 * 메모리 16464 KB
 * 시간 1272 ms
 */
public class Main {

	private static int R, C, answer;
	private static char[][] arr; // 보드
	private static boolean[] visited = new boolean[26]; //대문자 알파벳 26개 선택여부
	private static int[] dr = {-1,0,1,0}; // 상, 우, 하, 좌
	private static int[] dc = {0,1,0,-1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		
		arr = new char[R][C]; // 보드의 크기
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		dfs(0, 0, 1); // 백트래킹
		System.out.println(answer); // 출력
	}

	/**
	 * 백트래킹
	 * @param r 현재 말이 위치한 행
	 * @param c 현재 말이 위치한 열
	 * @param cnt 현재 말이 지나온 칸 수
	 */
	private static void dfs(int r, int c, int cnt) {
		visited[arr[r][c] - 'A'] = true; // 현재 말이 있는 칸에 있는 알파벳 체크
		answer = Math.max(answer, cnt); // 현재까지 밟은 칸수와 현재값중 최대값으로 갱신
		
		for (int i = 0; i < 4; i++) { // 4방향 체크
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) { // 보드범위내에 있는지
				if (!visited[arr[nr][nc] - 'A']) {// 아직방문안했고 지나온알파벳중에 있는지 점검
					dfs(nr, nc, cnt + 1); // 다음 단계 호출
				}
			}
		}
		visited[arr[r][c] - 'A'] = false; // 현재 칸에 있는 알파벳 체크해제
	}

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * dfs 알고리즘을 썻는데 재귀함수로 짰고, 이미 방문한 위치를 체크하기위해 visited배열을 이용하여 가지치기를 함.
 * </pre>
 * 
 * @author 박형규
 *
 */
public class Solution {

	static int[][] arr = new int[100][100]; // 사다리 정보
	static boolean[][] visited = new boolean[100][100]; // 방문여부 정보
	static int[] dr = { 0, 0, 1 }; // 왼 오 아래
	static int[] dc = { -1, 1, 0 }; // 왼 오 아래
	static int answer; // 각 테스트케이스별로 정답 저장할 변수
	static int start; // 시작 x좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine()); // 테스트케이스번호

			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 사다리정보 저장
				}
			}

			for (int j = 0; j < 100; j++) {
				if (arr[0][j] == 1) { // 출발점이 1인경우 dfs 알고리즘 수행
					start = j;
					dfs(0, j);
				}
			}
			sb.append(String.format("#%d %d\n", T, answer)); // 테스트 케이스 결과 저장
		}
		System.out.print(sb); // 출력
	}

	/**
	 * dfs 알고리즘 재귀함수
	 * @param r 현재 행
	 * @param c 현재 열
	 * @param start 시작 x좌표(시작 열)
	 * @return
	 */
	private static void dfs(int r, int c) {
		if (r == 99) { // 마지막에 도달햇을 경우
			if (arr[r][c] == 2) {
				answer = start; // 값이 2이면 시작 x좌표로 정답 세팅
			}
			return; // 재귀함수 종료
		}
		
		for (int k = 0; k < 3; k++) { // 왼쪽, 오른쪽, 아래순으로 점검
			int nr = r + dr[k]; // 다음 좌표 행
			int nc = c + dc[k]; // 다음 좌표 열
			
			if (nr < 0 || nr > 99 || nc < 0 || nc > 99) { // 2차원배열 범위를 벗어나는지여부체크
				continue;
			}
			
			if (arr[nr][nc] != 0 && !visited[nr][nc]) { // 사다리가 있고, 아직방문하지않은위치라면
				visited[nr][nc] = true; // 방문 체크
				dfs(nr, nc); // 다음재귀함수 호출
				visited[nr][nc] = false; // 방문 해제
				break;
			}
			
		}
	}

}

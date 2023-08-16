import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * dfs와 dp 이용해서 풀음
 * </pre>
 * @author 박형규
 */
public class Main {

	static int n, arr[][], d[][];
	static int[] dr = {-1,0,1,0}; // 상,우,하,좌
	static int[] dc = {0,1,0,-1}; // 상,우,하,좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine()); // 대나무숲크기
		arr = new int[n][n]; // 대나무숲 정보 저장할 2차원배열
		d = new int[n][n]; // 각 지역에서 출발하여 이동할 수 있는 최대 칸의 수를 저장할 2차원배열
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 대나무숲 정보 저장
			}
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer = Math.max(answer, dfs(i, j));
			}
		}

		sb.append(answer).append("\n");
		System.out.print(sb); // 출력
	}
	
	/**
	 * 깊이 우선 탐색 알고리즘
	 * @param r 현재위치 행
	 * @param c 현재위치 열
	 */
	private static int dfs(int r, int c) {
		
		if (d[r][c] > 0) { // 이미 dp테이블에 값이 존재하면 dfs 수행하지 않음
			return d[r][c];
		}
		
		d[r][c] = 1; // 초기 이동할 수 있는 칸수 = 1
		
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k]; // 다음위치 행
			int nc = c + dc[k]; // 다음위치 열
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || arr[r][c] >= arr[nr][nc]) { // 대나무숲을 벗어낫거나 다음 지역의 대나무가 현재 지역보다 적은 경우
				continue; // 넘어감
			}
			
			d[r][c] = Math.max(d[r][c], dfs(nr, nc) + 1); // dp
		}
		
		return d[r][c];
	}

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*N 크기의 등산로 부지. 최대한 긴 등산로를 만듬
 * 1. 등산로는 가장 높은 봉우리에서 시작해야함
 * 2. 높은 지형에서 낮은 지형으로 가로or세로 방향으로 연결되야함(높이가 같은곳혹은낮은지형,대각선방향의연결 불가능)
 * 3. 딱한곳정해서 최대 k깊이만큼 지형깎는공사가능
 * 
 * N이 최대8 K가 최대5인데, dfs 완전탐색으로 문제를 풀면 될거같다.
 * </pre>
 * @author 박형규
 */
public class Solution {

	static int[] dr = {-1,0,1,0}; // 상,우,하,좌
	static int[] dc = {0,1,0,-1}; // 상,우,하,좌
	static int N, K, maxHeight, arr[][], answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int t = 1; t <= T; t++) { // 테스트 케이스마다 수행
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 지도 한변의 길이
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			
			arr = new int[N][N];
			maxHeight = 0;
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 지도의 정보
					maxHeight = Math.max(maxHeight, arr[i][j]); // 최대 높이 봉우리 갱신
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == maxHeight) { // 가장 높은 봉우리이면
						flatten(i, j); // 공사할 곳 탐색 start
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void flatten(int r, int c) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i==r && j==c) { // 최대 봉우리인경우 skip
					continue;
				}
				
				for (int k = 1; k <= K; k++) { // 1부터 K까지 공사 진행
					arr[i][j] -= k; // k깊이만큼 공사
					dfs(r, c, 1); // dfs 탐색
					arr[i][j] += k; // 공사한거 원상 복구
				}
			}
		}
	}

	private static void dfs(int r, int c, int distance) {
		answer = Math.max(answer, distance); // 등산로 길이 최대값으로 갱신
		
		for (int i = 0; i < 4; i++) { // 상,우,하,좌 4방향탐색
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				continue; // 지도를 벗어난 경우
			}
			
			if (arr[r][c] > arr[nr][nc]) { // 현위치의높이가 다음위치의높이보다 높은경우에만 등산로연결가능
				dfs(nr, nc, distance + 1); // 다음위치에서 dfs탐색, 등산로길이1증가시켜줌
			}
		}
	}

}
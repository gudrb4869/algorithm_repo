import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 벽을 새울 곳을 3개 뽑는다 -> 조합
 * 바이러스 확산 -> 너비/깊이 우선 탐색
 * 
 * => 완전탐색, 그래프 탐색으로 문제를 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M;
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		
		int[][] map = new int[N][M]; // 지도의 모양을 저장할 2차원 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] p = new int[N * M]; // Next Combination에 사용할 배열
		for (int i = N * M - 1; i >= N * M - 3; i--) { // 3개 미리 선택
			p[i] = 1;
		}
		
		int answer = 0; // 얻을 수 있는 안전 영역의 최대 크기
		
		do {
			int[][] copyMap = new int[N][M]; // 지도 복사본
			for (int i = 0; i < N; i++) {
				copyMap[i] = map[i].clone(); // 원본 복사하여 저장
			}
			boolean flag = true; // 3개의 새로운 벽을 세울수 있는지 판단할 플래그
			for (int i = 0; i < N * M; i++) {
				if (p[i] == 1) { // 현재 고른 좌표인 경우
					int r = i / M; // 행값
					int c = i % M; // 열값
					
					if (copyMap[r][c] != 0) { // 빈칸이 아닌경우
						flag = false; // 플래그 false로 전환
						break; // 중단
					}
					copyMap[r][c] = 1; // 벽으로 세팅
				}
			}
			
			if (!flag) { // 플래그가 false인 경우(3개의 벽을 세우지 못한 경우)
				continue; // 그래프 탐색하지 않고 다음 경우의 수로 넘어감
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copyMap[i][j] == 2) { // 바이러스가 있는 위치인 경우
						dfs(i, j, copyMap); // 그래프 탐색 시작
					}
				}
			}
			
			int result = 0; // 현재 case에서 안전 영역의 크기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copyMap[i][j] == 0) { // 빈칸인 경우
						result++; // 안전 영역 1 누적
					}
				}
			}
			answer = Math.max(answer, result); // 안전 영역을 최댓값으로 갱신
			
		} while (np(p));
		
		System.out.println(answer);
		
	}

	// 깊이 우선 탐색
	private static void dfs(int r, int c, int[][] copyMap) {
		copyMap[r][c] = 2;
		
		for (int d = 0; d < 4; d++) { // 상,하,좌,우 4방향 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 지도를 벗어났는지, 빈칸이 아닌지 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || copyMap[nr][nc] != 0) {
				continue;
			}
			
			// 깊이우선탐색 재귀수행
			dfs(nr, nc, copyMap);
		}
	}

	// 조합
	private static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;
		
		while (i > 0 && p[i - 1] >= p[i]) i--;
		
		if (i == 0) return false;
		
		int j = N - 1;
		
		while(p[i - 1] >= p[j]) j--;
		
		swap(p, i - 1, j);
		
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	// 스왑
	private static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 사방 탐색 사용, 문제 조건대로 구현하여 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 학생의  수
		
		StringTokenizer st;
		
		boolean[][] favorite = new boolean[N * N + 1][N * N + 1]; // 좋아하는 학생의 번호를 저장할 배열
		int[][] arr = new int[N][N]; // N*N 교실
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 학생의 번호
			
			for (int j = 0; j < 4; j++) {
				favorite[num][Integer.parseInt(st.nextToken())] = true;
			}
			
			int R = 0, C = 0, E = -1, T = -1; // 행, 열, 빈칸수, 좋아하는학생이인접한 칸의수
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (arr[r][c] != 0) continue; // 이미 배정된 칸인 경우 건너뜀
					
					int empty = 0, total = 0; // 빈칸수, 좋아하는학생인접한 칸수
					for (int d = 0; d < 4; d++) { // 상우하좌 사방탐색
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 자리범위내에 있고
							if (arr[nr][nc] == 0) { // 빈칸인 경우
								empty++;
							} else if (favorite[num][arr[nr][nc]]) { // 좋아하는 학생이 인접해있는 경우
								total++;
							}
						}
					}
					if (total > T || (total == T && empty > E)) { // 조건1, 조건2
						R = r;
						C = c;
						E = empty;
						T = total;
					}
				}
			}
			arr[R][C] = num; // 학생 배정
		}
		
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0; // 인접한 칸에 앉은 좋아하는 학생의 수
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 자리범위내에 있고
						if (favorite[arr[r][c]][arr[nr][nc]]) { // 좋아하는 학생이 인접해있는 경우
							cnt++;
						}
					}
				}
				
				if (cnt > 0) { // 학생의 만족도: 0이면 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000
					answer += Math.pow(10, cnt - 1);
				}
			}
		}
		System.out.println(answer); // 결과 출력
	}

}
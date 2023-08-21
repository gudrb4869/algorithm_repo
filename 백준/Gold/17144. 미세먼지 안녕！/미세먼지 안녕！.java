import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * 확산되는 양은 Ar,c/5이고 소수점은 버린다.
 * (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
 * 공기청정기가 작동한다.
 * 공기청정기에서는 바람이 나온다.
 * 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
 * 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * 공기청정기는 위아래로 붙어있다.
 * 공기청정기는 항상 1번열에 설치!
 * </pre>
 * @author 박형규
 */
public class Main {

	static int R, C, T; // 집의 가로길이, 집의 세로길이, 시간
	static int[] cleaner = new int[2]; // 공기청정기의 윗부분 아랫부분 위치 행저장할 배열
	static int[][] home; // 집의 상태를 저장할 2차원 배열
	static int[] dr = {-1, 0, 1, 0}; // 상,우,하,좌
	static int[] dc = {0, 1, 0, -1}; // 상,우,하,좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 집의 가로 길이
		C = Integer.parseInt(st.nextToken()); // 집의 세로 길이
		T = Integer.parseInt(st.nextToken()); // 시간
		
		home = new int[R][C]; // 집의 상태를 저장할 2차원 배열
		int index = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				home[i][j] = Integer.parseInt(st.nextToken()); // 집의 정보(공기청정기=-1, 미세먼지양=0~1000)
				if (home[i][j] == -1) {
					cleaner[index++] = i; // 공기청정기의 위,아래 행 기록
				}
			}
		}
		
		
		for (int t = 0; t < T; t++) {
			int[][] spread = new int[R][C]; // 미세먼지기준으로 미세먼지가 퍼진양을 저장할 배열
			int[][] count = new int[R][C]; // 미세먼지기준으로 확산된 방향의 개수를 저장할 배열
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (home[r][c] > 0) { // 미세먼지 있는곳이면
						for (int d = 0; d < 4; d++) { // 미세먼지 기준 4방향
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nr >= R || nc < 0 || nc >= C || home[nr][nc] == -1) {
								continue; // 집의 범위를 벗어낫거나 공기청정기가 있는곳이면 진행하지 않음
							}
							spread[nr][nc] += home[r][c] / 5; // 미세먼지가 퍼진곳에 값더해줌
							count[r][c]++; // 미세먼지가 퍼진 방향의 개수 1증가
						}
					}
				}
			}
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (home[r][c] != -1) { // 공기청정기 있는곳 제외
						home[r][c] = home[r][c] - (home[r][c] / 5) * count[r][c] + spread[r][c]; // 미세먼지값 갱신
					}
				}
			}
			
			rotateTop(); // 상단 반시계방향회전
			rotateBottom(); // 하단 시계방향회전
			
		}

		int answer = 0; // 미세먼지의 양
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (home[i][j] > 0) {
					answer += home[i][j]; // 미세먼지의 양 더해줌
				}
			}
		}
		System.out.println(answer); // 출력
	}

	// 윗부분 공기순환(반시계방향)
	private static void rotateTop() {
		for (int r = cleaner[0] - 1; r > 0; r--) { // 좌측
			home[r][0] = home[r - 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 상단
			home[0][c] = home[0][c + 1];
		}
		for (int r = 0; r < cleaner[0]; r++) { // 우측
			home[r][C - 1] = home[r + 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) { // 하단
			home[cleaner[0]][c] = home[cleaner[0]][c - 1];
		}
		home[cleaner[0]][1] = 0;
	}
	
	// 아랫부분 공기순환(시계방향)
	private static void rotateBottom() {
		for (int r = cleaner[1] + 1; r < R - 1; r++) { // 좌측
			home[r][0] = home[r + 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 하단
			home[R - 1][c] = home[R - 1][c + 1];
		}
		for (int r = R - 1; r > cleaner[1]; r--) { // 우측
			home[r][C - 1] = home[r - 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) { // 상단
			home[cleaner[1]][c] = home[cleaner[1]][c - 1];
		}
		home[cleaner[1]][1] = 0;
	}

}
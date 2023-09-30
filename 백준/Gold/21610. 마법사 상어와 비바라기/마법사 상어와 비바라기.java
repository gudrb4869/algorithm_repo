import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 구현, 시뮬레이션
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 격자의 가로세로길이
		int M = Integer.parseInt(st.nextToken()); // 명령횟수
		
		int[][]  A = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}; // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
		int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1}; // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
		
		boolean[][] cloud = new boolean[N][N];
		cloud[N - 2][0] = cloud[N - 2][1] = cloud[N - 1][0] = cloud[N - 1][1] = true;
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int di = Integer.parseInt(st.nextToken()) - 1; // 이동 방향
			int si = Integer.parseInt(st.nextToken()); // 이동 칸수
			
			boolean[][] check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cloud[i][j]) {
						int r = (N + i + dr[di] * (si % N)) % N;
						int c = (N + j + dc[di] * (si % N)) % N;
						A[r][c]++;
						check[r][c] = true;
					}
				}
			}
			
			cloud = check;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cloud[i][j]) {
						int cnt = 0;
						for (int d = 1; d < 8; d+=2) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < N && A[nr][nc] > 0) {
								cnt++;
							}
						}
						A[i][j] += cnt;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cloud[i][j]) {
						cloud[i][j] = false;
					} else {
						if (A[i][j] >= 2) {
							A[i][j] -= 2;
							cloud[i][j] = true;
						}
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += A[i][j];
			}
		}
		System.out.println(sum);
	}

}
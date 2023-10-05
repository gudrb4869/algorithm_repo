import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static int N, W, H, arr[][], selected[], answer;
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder();	// 출력스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스의 개수
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			arr = new int[H][W];
			selected = new int[N];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = 99999;
			products(0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	private static void products(int cnt) {
		if (cnt == N) {
			int[][] board = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					board[i][j] = arr[i][j];
				}
			}
			
			for (int n = 0; n < N; n++) {
				int i = 0;
				while (i < H && board[i][selected[n]] == 0) i++;
				
				if (i < H) {
					boolean[][] visited = new boolean[H][W];
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, selected[n]});
					visited[i][selected[n]] = true;
					
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1];
						
						for (int x = 1; x <= board[r][c] - 1; x++) {
							for (int d = 0; d < 4; d++) {
								int nr = r + dr[d] * x;
								int nc = c + dc[d] * x;
								
								if (nr >= 0 && nr < H && nc >= 0 && nc < W && board[nr][nc] > 0 && !visited[nr][nc]) {
									visited[nr][nc] = true;
									q.offer(new int[] {nr, nc});
								}
							}
						}
					}
					
					for (int c = 0; c < W; c++) {
						int idx = H - 1;
						for (int r = H - 1; r >= 0; r--) {
							if (!visited[r][c]) {
								board[idx--][c] = board[r][c];
							}
						}
						for (int r = idx; r >= 0; r--) {
							board[r][c] = 0;
						}
					}
				}
			}
			
			int result = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (board[i][j] > 0) {
						result++;
					}
				}
			}
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			products(cnt + 1);
		}
	}

}
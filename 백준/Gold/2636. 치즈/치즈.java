import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비우선탐색, 시뮬레이션
 * 2023-12-05(화)
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 사각형 모양 판의 세로 길이
		int M = Integer.parseInt(st.nextToken()); // 사각형 모양 판의 가로 길이
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int time = 0;
		int last = 0;
		while (true) {
			
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) {
						count++;
					}
				}
			}
			
			if (count == 0) break;
			last = count;
			
			Queue<int[]> q = new ArrayDeque<>();
			int[][] status = new int[N][M]; // 1: 공기인곳, -1: 공기와접촉해있는치즈
			q.offer(new int[] {0, 0});
			status[0][0] = 1;
			
			while (!q.isEmpty()) {
				int[] info = q.poll();
				int r = info[0], c = info[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d], nc = c + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || status[nr][nc] != 0) continue;
					
					if (arr[nr][nc] == 0) {
						q.offer(new int[] {nr, nc});
						status[nr][nc] = 1;
					} else {
						status[nr][nc] = -1;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (status[i][j] == -1) {
						arr[i][j] = 0;
					}
				}
			}
			
			time++;
		}
		
		System.out.println(time);
		System.out.println(last);
	}

}
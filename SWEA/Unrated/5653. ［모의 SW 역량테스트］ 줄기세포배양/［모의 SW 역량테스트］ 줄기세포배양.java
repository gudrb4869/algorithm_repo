import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][][] arr = new int[K * 2 + N][K * 2 + M][2];
			boolean[][] visited = new boolean[K * 2 + N][K * 2 + M];
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					int startA = a[0] + a[1];
					int startB = b[0] + b[1];
					
					if (startA == startB) {
						return b[1] - a[1];
					}
					
					return startA - startB;
				}
			});
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[K + i][K + j][0] = 0;
					arr[K + i][K + j][1] = Integer.parseInt(st.nextToken());
					if (arr[K + i][K + j][1] != 0) {
						pq.offer(new int[] {arr[K+i][K+j][0], arr[K+i][K+j][1], K+i, K+j});
						visited[K+i][K+j] = true;
					}
				}
			}
			
			int[] dr = {-1,0,1,0}; // 상,우,하,좌
			int[] dc = {0,1,0,-1}; // 상,우,하,좌
			
			for (int currentTime = 1; currentTime < K; currentTime++) {
				while (!pq.isEmpty() && pq.peek()[0] + pq.peek()[1] <= currentTime) {
					int[] info = pq.poll();
					int appearTime = info[0], elapsedTime = info[1], r = info[2], c = info[3];
					
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nr >= K * 2 + N || nc < 0 || nc >= K * 2 + M || visited[nr][nc]) {
							continue;
						}
						
						arr[nr][nc][0] = currentTime + 1;
						arr[nr][nc][1] = elapsedTime;
						visited[nr][nc] = true;
						pq.offer(new int[] {arr[nr][nc][0], arr[nr][nc][1], nr, nc});
					}
				}
			}
			
			int answer = 0;
			for (int i = 0; i < K * 2 + N; i++) {
				for (int j = 0; j < K * 2 + M; j++) {
					if (visited[i][j]) {
						if (arr[i][j][0] + 2 * arr[i][j][1] - 1 >= K) {
							answer++;
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}
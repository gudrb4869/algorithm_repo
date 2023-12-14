import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그래프탐색
 * 너비우선탐색
 * 
 * 2차원행렬을 순차탐색하면서 1인곳은 그냥 지나가고, 0인곳이라면 너비우선탐색을 통해 이동할 수 있는 벽의 개수를 구한다음, 인접한 벽에 이동할수 있는 칸을 더했다.
 * 이때 중복계산방지하기 위해 우선순위큐를 사용했는데, 먼저 0부터처리한다음 나중에 1을 처리했음.
 * 2023-12-14(목)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		int[][] result = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp[j] - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					result[i][j]++;
					continue;
				}
				if (visited[i][j]) {
					continue;
				}
				
				PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
					@Override
					public int compare(int[] a, int[] b) {
						return a[0] - b[0];
					}
				});
				q.offer(new int[] {0, i, j});
				visited[i][j] = true;
				int count = 1;
				while (!q.isEmpty()) {
					int[] info = q.poll();
					int status = info[0], r = info[1], c = info[2];
					
					if (status == 1) {
						result[r][c] += count;
						visited[r][c] = false;
						continue;
					}
					
					
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
							continue;
						}
						
						q.offer(new int[] {arr[nr][nc], nr, nc});
						visited[nr][nc] = true;
						if (arr[nr][nc] == 0) {
							count++;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j] % 10);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
		
	}

}
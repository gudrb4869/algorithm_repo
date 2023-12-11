import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 조합을 통해 바이러스중 활성화시킬 바이러스 M개를 뽑았다.
 * 그다음 뽑은 M개의 바이러스를 너비우선탐색으로 퍼뜨리면서 시간을 계산한다.
 * 최소 시간으로 갱신하고 답을 출력한다.
 * 2023-12-11(월)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, arr[][], selected[], size, answer = 987654321;
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static List<int[]> virus = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 연구소의 크기(4 ~ 50)
		M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수(1 ~ 10)
		
		arr = new int[N][N];
		selected = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				// 0: 빈칸
				// 1: 벽
				// 2: 비활성 바이러스의 위치
				// M <= 2의 개수 <= 10
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					virus.add(new int[] {i, j}); // 바이러스의 위치 저장
					size++; // 바이러스의 개수 1증가
				}
			}
		}
		
		combinations(0, 0);
		
		System.out.println(answer == 987654321 ? -1 : answer);
	}

	static void combinations(int start, int cnt) {
		
		if (cnt == M) {
			int[][] copyArr = new int[N][N];
			int count = 0; // 빈공간의 개수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0) {
						copyArr[i][j] = -1; // 빈칸
						count++;
					}
					else if (arr[i][j] == 1) copyArr[i][j] = -2; // 벽
					else copyArr[i][j] = -3; // 바이러스
				}
			}
			
			boolean[][] visited = new boolean[N][N];
			Queue<int[]> q = new ArrayDeque<>();
			
			for (int i = 0; i < M; i++) {
				int[] info = virus.get(selected[i]);
				int r = info[0], c = info[1];
				copyArr[r][c] = 0;
				visited[r][c] = true;
				q.offer(new int[] {r, c});
			}
			
			while (!q.isEmpty()) {
				if (count == 0) {
					break;
				}
				
				int[] info = q.poll();
				int r = info[0], c = info[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 연구소 벗어났거나, 벽이거나, 이미 방문한 칸인경우 skip
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || copyArr[nr][nc] == -2 || visited[nr][nc]) {
						continue;
					}
					
					if (copyArr[nr][nc] == -1) {
						count--;
					}
					
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					copyArr[nr][nc] = copyArr[r][c] + 1;
				}
			}
			
			if (count == 0) {
				int result = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (copyArr[i][j] < 0) continue;
						
						result = Math.max(result, copyArr[i][j]);
					}
				}
				answer = Math.min(answer, result);
			}
			
			return;
		}
		
		for (int i = start; i < size; i++) {
			selected[cnt] = i;
			combinations(i + 1, cnt + 1);
		}
		
	}

}
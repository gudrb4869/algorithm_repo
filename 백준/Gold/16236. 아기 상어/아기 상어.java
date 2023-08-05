import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 동일한 시간을 이동하여 먹을수있는 물고기가 여러군데일경우 가장 위쪽이면서 가장왼쪽에 있는 곳부터 방문해야하므로
 * 우선순위큐를 사용하여 너비우선탐색으로 문제를 풀었다. 
 * @author 박형규
 *
 */
public class Main {

	private static int n, arr[][], answer, size, eat;
	private static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	private static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	private static int[] cur;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine()); // 공간의 크기
		
		arr = new int[n][n]; // 공간의 상태를 저장할 2차원 배열
		
		size = 2;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 공간의 상태 저장
				if (arr[i][j] == 9) { // 아기 상어 위치이면
					arr[i][j] = 0; // 빈공간 0으로 세팅
					cur = new int[] {0, i, j}; // 이동시간, 현재 아기상어의 좌표 삽입
				}
			}
		}
		
		while (true) {
			int[] nxt = bfs(cur); // 아기상어의 이동결과
			if (nxt[1] == cur[1] && nxt[2] == cur[2]) { // 이전아기상어의 위치와 현재 아기상어의 위치가동일하면
				break; // 더이상 먹을수있는 물고기가 없으므로 반복문 중단
			}
			answer += nxt[0]; // 이동시간 더함
			cur = new int[] {0, nxt[1], nxt[2]}; // 아기상어가 위치한 곳으로 좌표 이동 및 이동시간 0으로 초기화
		}
		
		System.out.println(answer); // 결과 출력
	}
	
	/**
	 * bfs 탐색 메서드
	 * @param cur 이동시간, 아기상어의 위치
	 * @return 먹을수 있는 물고기를 먹으러 가는데 걸린시간, 먹은 물고기가 있는 곳의 좌표
	 */
	private static int[] bfs(int[] cur) {
		// 동일한 시간이 걸리는 곳에 먹을 수 있는 물고기가 여러곳이 있을경우,
		// 가장 위쪽에 있는 물고기부터 먹고, 위쪽에도 먹을 수 있는 물고기가 여러곳이 있다면 가장 왼쪽에 있는 물고기부터 먹기위해 우선순위큐 사용
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) { // 동일한 시간이라면
					if (a[1] == b[1]) { // 동일한 행에 위치해있다면
						return a[2] - b[2]; // 열 오름차순 정렬
					}
					return a[1] - b[1]; // 행 오름차순 정렬
				}
				return a[0] - b[0]; // 이동시간순으로 오름차순 정렬
			}
		});
		boolean[][] visited = new boolean[n][n]; // n *n 방문배열 초기화
		visited[cur[1]][cur[2]] = true; // 현재 위치 방문체크
		pq.offer(cur); // 큐에 현재이동시간, 아기상어의위치 삽입
		
		while (!pq.isEmpty()) { // 우선순위큐가 비어있지 않다면 반복문 수행
			int[] temp = pq.poll(); // 우선순위큐에서 값 추출
			int time = temp[0], r = temp[1], c = temp[2]; // 시간, 행, 열
			
			if (arr[r][c] > 0 && arr[r][c] < size) { // 먹을 수 있는 물고기가 있는 위치라면
				arr[r][c] = 0; // 빈공간으로 세팅
				if (++eat == size) { // 먹은 물고기수 1증가시키고, 먹은 물고기의 수가 아기상어의크기와 동일하다면
					eat = 0; // 먹은물고기수 0으로 세팅하고
					size++; // 아기상어의 크기 1 증가
				}
				return temp; // 이동시간, 행, 열 반환
			}
			
			for (int i = 0; i < 4; i++) { // 4방향으로 탐색
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) { // 공간범위밖이면 다음 for문으로 이동
					continue;
				}
				
				if (!visited[nr][nc] && arr[nr][nc] <= size) { // 아직방문하지않았고, 아기상어가 지나갈수있는 공간이라면
					visited[nr][nc] = true; // 방문체크하고
					pq.offer(new int[] {time + 1, nr, nc}); // 다시 우선순위큐에 삽입
				}
			}
		}
		
		return cur; // 먹은 물고기가 없다면 초기상태 반환
	}

}

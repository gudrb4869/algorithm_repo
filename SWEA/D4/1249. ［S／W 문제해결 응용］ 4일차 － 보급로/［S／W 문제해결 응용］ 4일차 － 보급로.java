import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * 지도 정보가 n * n 2차원 배열 형태로 표시됨
 * 출발지 : 좌상단의 칸 map[0][0]
 * 도착지 : 우하단의 칸 map[n - 1][n - 1]
 * 각 칸의 값들은 복구 작업에 드는 시간임
 * 출발지와 도착지의 값은 0
 * 나머지 칸들의 값은 0 ~ 9
 * 최소 복구 시간이 드는 경로의 총 복구 시간을 구해야함
 * 따라서 최단 경로 알고리즘 중 하나인 다익스트라 알고리즘을 쓸것이고
 * 우선순위 큐를 이용할 것이다.
 * 또한 각 칸마다 복구에 든 시간을 저장하기 위해 distance 2차원 배열 선언
 * 이후 while 문을 통해 다익스트라 알고리즘 수행
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	private static int INF = 987654321; // 무한대값 설정
	private static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	private static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int test_case = 1; test_case <= T; test_case++) { // 테스트케이스 수만큼 수행
			int n = Integer.parseInt(br.readLine()); // 지도의 크기
			int[][] map = new int[n][n]; // n * n 지도
			int[][] distance = new int[n][n]; // 각칸의 복구시간을 저장할 2차원 배열
			
			for (int i = 0; i < n; i++) { // 입력
				Arrays.fill(distance[i], INF); // distance의 각칸 무한대로 초기화
				String[] s = br.readLine().split(""); // 각 줄 한글자단위로 분리
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(s[j]); // 정수형으로 변환후 map에 저장
				}
			}
			
			int result = dijkstra(n, map, distance); // 다익스트라 알고리즘 수행 결과값 저장
			
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(result);
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	/**
	 * 다익스트라 알고리즘
	 * @param n 지도의 크기
	 * @param map 지도 정보
	 * @param distance 복구 시간 정보
	 * @return 도착점의 복구 시간
	 */
	private static int dijkstra(int n, int[][] map, int[][] distance) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) { // 우선순위큐 정렬 조건 (min-heap)
				return o1[0] - o2[0]; // 복구시간 기준으로 오름차순 정렬
			}
		}); // 우선순위큐 선언
		distance[0][0] = 0; // 시작점의 복구시간 0으로 초기화
		pq.offer(new int[]{0, 0, 0}); // 우선순위 큐에 초기 상태값 삽입 (현재 위치의 복구 시간, 현재위치의 행값, 현재위치의 열값)
		
		while (!pq.isEmpty()) {
			int[] t = pq.poll(); // 우선순위큐 pop. 최소 복구 시간의 값을 가진 상태값이 나온다.
			int now = t[0]; // 현재 위치의 복구 시간
			int r = t[1]; // 현재 위치의 행값
			int c = t[2]; // 현재 위치의 열값
			
			if (now > distance[r][c]) {
				continue; // 현재 위치의 복구 시간이 현재 위치에 저장된 distance값보다 크면 확인할 필요없으므로 빠져나옴
			}
			
			for (int k = 0; k < 4; k++) { // 현재 위치를 기준으로 상, 우, 하, 좌 순으로 탐색
				int nr = r + dr[k]; // 다음 위치의 행값
				int nc = c + dc[k]; // 다음 위치의 열값
				if (nr >= 0 && nr < n && nc >= 0 && nc < n) { // 다음 위치가 지도 범위내에 있는지
					int dist = now + map[nr][nc]; // 다음 위치의 복구 시간
					if (dist < distance[nr][nc]) { // dist값이 다음위치에 저장되어있는 값보다 작으면 갱신하고 우선순위큐에 삽입 
						distance[nr][nc] = dist;
						pq.add(new int[]{dist, nr, nc});
					}
				}
			}
		}
		return distance[n - 1][n - 1]; // 도착점의 복구 시간값 리턴
	}
}
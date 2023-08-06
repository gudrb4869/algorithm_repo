import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개(지점)의 정점, M개(도로)의 무방향 간선, W개(웜홀)의 방향 간선
 * 웜홀로 지나갈시 시간이 거꾸로감
 * 한지점에서 출발을 하여서 다시 출발한 위치로 돌아왔을 때
 * 출발을 하였을 때보다 시간이 되돌아가는 경우가 있는지 판별을 해야하기 때문에
 * 그래프내에서 음수 사이클이 있는지를 판단해봐야한다.
 * 따라서 벨만-포드 알고리즘을 사용하여 문제를 해결해보았다.
 * </pre>
 * @author 박형규
 * 
 */
public class Main {

	private static int N, M, W, INF = 987654321;
	private static List<int[]>[] graph;
	private static int[] distance;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < TC; t++) { // 테스트케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지점의 수
			M = Integer.parseInt(st.nextToken()); // 도로의 개수
			W = Integer.parseInt(st.nextToken()); // 웜홀의 개수
			
			distance = new int[N + 1];
			
			graph = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) { // 도로의 정보(양방향 간선)
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken()); // 지점1
				int E = Integer.parseInt(st.nextToken()); // 지점2
				int T = Integer.parseInt(st.nextToken()); // 이동하는데 걸리는 시간
				graph[S].add(new int[] {E, T});
				graph[E].add(new int[] {S, T});
			}

			for (int i = 0; i < W; i++) { // 웜홀의 정보(단방향 간선)
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken()); // 지점1
				int E = Integer.parseInt(st.nextToken()); // 지점2
				int T = Integer.parseInt(st.nextToken()); // 줄어드는 시간(입력이 양수로 주어지므로 갱신할때는 -부호 붙여서 갱신 여부 판단)
				graph[S].add(new int[] {E, -T});
			}
			
			if (isPossible()) { // 시간이 줄어들면서 출발위치로 돌아오는 것이 가능하면 
				sb.append("YES\n"); // YES 출력
			} else { // 불가능하면
				sb.append("NO\n"); // NO 출력
			}
		}
		
		System.out.print(sb);
	}

	/**
	 * 정점 1부터 정점 N까지 검사
	 * @return 시간이 줄어들면서 출발위치로 돌아올 수 있는지 여부
	 */
	private static boolean isPossible() {
		for (int i = 1; i <= N; i++) {
			if (bellmanFord(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 벨만포드 알고리즘
	 * @param start 출발 지점
	 * @return
	 */
	private static boolean bellmanFord(int start) {
		Arrays.fill(distance, INF);
		distance[start] = 0; // 시작점 0으로 초기화
		
		boolean update = false;
		// t - 1번 탐색하고 마지막 한번은 음수 사이클 존재여부 확인 
		for (int t = 0; t < N - 1; t++) {
			update = false;
			// 정점마다 간선 확인
			for (int curNode = 1; curNode <= N; curNode++) {
				if (distance[curNode] == INF) {
					continue;
				}
				// 간선 확인
				for (int[] edge : graph[curNode]) {
					int nextNode = edge[0]; // 다음 노드
					int cost = edge[1]; // 간선 비용
					
					// 현재 간선을 거쳐서 다음 노드로 이동하는 거리가 더 짧은 경우
					if (distance[nextNode] > distance[curNode] + cost) {
						distance[nextNode] = distance[curNode] + cost;
						update = true;
					}
				}
			}
			if (!update) {
				break;
			}
		}
		
		if (update) {
			for (int curNode = 1; curNode <= N; curNode++) {
				if (distance[curNode] == INF) {
					continue;
				}
				// 간선 확인
				for (int[] edge : graph[curNode]) {
					int nextNode = edge[0]; // 다음 노드
					int cost = edge[1]; // 간선 비용
					
					// 현재 간선을 거쳐서 다음 노드로 이동하는 거리가 더 짧은 경우
					if (distance[nextNode] > distance[curNode] + cost) {
						return true;
					}
				}
			}
		}
		
		// 음수 사이클 존재하지 않음
		return false;
	}

}

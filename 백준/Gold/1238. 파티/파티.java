import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다익스트라 알고리즘을 써서 풀었다.
 * X에서 집으로 돌아가는 최단경로는 정방향 간선으로 다익스트라 알고리즘 돌리고
 * 집에서 X로 가는 최단경로는 간선들을 역방향으로 뒤집은상태로 다익스트라 알고리즘 돌린다
 * 다익스트라 알고리즘을 총 2번만 수행하면 정답을 구할수가 있다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, X; // 정점 개수
	static List<int[]>[] graph; // 인접 리스트 정방향
	static List<int[]>[] graphReverse; // 인접 리스트 역방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 마을의 개수
		int M = Integer.parseInt(st.nextToken()); // 단방향 도로의 개수
		X = Integer.parseInt(st.nextToken()); // 파티가 열리는 마을번호
		
		graph = new List[N + 1];
		graphReverse = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			graphReverse[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 도로의 시작점
			int B = Integer.parseInt(st.nextToken()); // 도로의 끝점
			int T = Integer.parseInt(st.nextToken()); // 도로를 지나는데 필요한 소요시간
			graph[A].add(new int[] {B, T});
			graphReverse[B].add(new int[] {A, T});
		}
		
		int[] toX = dijkstraToX(); // 집에서 X로가는 최단경로가 저장된 배열
		int[] fromX = dijkstraFromX(); // X에서 집으로 가는 최단경로가 저장된 배열
		
		int answer = 0; // 가장 오래 걸리는 소요시간
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, toX[i] + fromX[i]); // i번정점 -> X정점 -> i번정점 소요시간이 최대인 것으로 갱신해줌
		}
		
		System.out.println(answer); // 결과 출력
	}
	
	// 다익스트라 알고리즘 (X -> 집)
	private static int[] dijkstraFromX() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 거리 오름차순
			}
		});
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		pq.offer(new int[] {distance[X], X});
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int dist = info[0], cur = info[1];
			
			if (distance[cur] < dist) {
				continue;
			}
			
			for (int[] edge : graph[cur]) { // 정방향
				int next = edge[0], weight = edge[1];
				if (distance[next] > distance[cur] + weight) {
					distance[next] = distance[cur] + weight;
					pq.offer(new int[] {distance[next], next});
				}
			}
		}
		
		return distance;
	}

	// 다익스트라 알고리즘 (집 -> X)
	private static int[] dijkstraToX() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 거리 오름차순
			}
		});
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		pq.offer(new int[] {distance[X], X});
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int dist = info[0], cur = info[1];
			
			if (distance[cur] < dist) {
				continue;
			}
			
			for (int[] edge : graphReverse[cur]) { // 역방향
				int next = edge[0], weight = edge[1];
				if (distance[next] > distance[cur] + weight) {
					distance[next] = distance[cur] + weight;
					pq.offer(new int[] {distance[next], next});
				}
			}
		}
		return distance;
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다익스트라 알고리즘
 * 2023-12-06(수)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 개수(1 ~ 1000)
		int M = Integer.parseInt(br.readLine()); // 버스의 개수(1 ~ 100000)
		
		int[][] W = new int[N + 1][N + 1];
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(W[i], INF);
		}
		
		for (int i = 0; i < M; i++) { // 버스의 정보
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken()); // 버스의  출발 도시의 번호
			int E = Integer.parseInt(st.nextToken()); // 도착지의 도시 번호
			int C = Integer.parseInt(st.nextToken()); // 버스 비용(0 ~ 100000)
			W[S][E] = Math.min(W[S][E], C);
		}
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken()); // 구하고자 하는 구간 출발점의 도시번호
		int B = Integer.parseInt(st.nextToken()); // 도착점의 도시번호
		
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) return a[1] - b[1];
				return a[0] - b[0];
			}
		});
		
		distance[A] = 0;
		pq.offer(new int[] {0, A});
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int weight = info[0], i = info[1]; // 출발점(A)부터 현재정점까지 가는 최소비용,현재정점
			
			if (weight > distance[i]) continue;
			
			for (int j = 1; j <= N; j++) {
				if (W[i][j] != INF && distance[j] > distance[i] + W[i][j]) {
					distance[j] = distance[i] + W[i][j];
					pq.offer(new int[] {distance[j], j});
				}
			}
		}
		
		System.out.println(distance[B]);
	}
}
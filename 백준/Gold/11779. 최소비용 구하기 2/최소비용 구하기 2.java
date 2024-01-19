import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 도시의 개수(1~1000)
		int m = Integer.parseInt(br.readLine()); // 버스의 개수(1~100000)
		
		int[][] matrix = new int[n + 1][n + 1]; // 인접 행렬
		final int INF = 987654321; // 무한대
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				matrix[i][j] = INF; // 무한대로 초기화
			}
		}
		
		for (int i = 1; i <= n; i++) {
			matrix[i][i] = 0; // 자기 자신으로 가는 비용은 0
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()); // 버스의 출발 도시의 번호
			int e = Integer.parseInt(st.nextToken()); // 도착지의 도시 번호
			int w = Integer.parseInt(st.nextToken()); // 버스 비용(0~99999)
			
			matrix[s][e] = Math.min(matrix[s][e], w); // 비용 최소값으로 갱신
		}
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		int B = Integer.parseInt(st.nextToken()); // 도착 도시의 번호
		
		// 다익스트라 알고리즘 start
		int[] distance = new int[n + 1]; // A에서 각 지점으로 가는 최소 비용 저장할 자료구조
		List<Integer>[] path = new List[n + 1]; // 경로를 방문하는 도시 번호 저장할 자료구조
		for (int i = 1; i <= n; i++) {
			path[i] = new ArrayList<>();
		}
		Arrays.fill(distance, INF);
		distance[A] = 0;
		path[A].add(A);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 최소 비용 순으로 값 저장 및 추출
			}
		});
		pq.offer(new int[] {distance[A], A});
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int dist = info[0], cur = info[1];
			
			if (distance[cur] < dist) {
				continue;
			}
			
			for (int next = 1; next <= n; next++) {
				if (matrix[cur][next] != INF && distance[cur] + matrix[cur][next] < distance[next]) {
					distance[next] = distance[cur] + matrix[cur][next];
					pq.offer(new int[] {distance[next], next});
					path[next].clear();
					for (int node : path[cur]) {
						path[next].add(node);
					}
					path[next].add(next);
				}
			}
		}
		// 다익스트라 알고리즘 end
		
		sb.append(distance[B]).append("\n");
		sb.append(path[B].size()).append("\n");
		for (int node : path[B]) {
			sb.append(node).append(" ");
		}
		sb.append("\n");
		
		System.out.print(sb);
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 최소 신장 트리를 구하는 문제이다.
 * 프림 알고리즘을 이용하여 문제를 풀어보았다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static class Star {
		double x, y;
		
		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Data implements Comparable<Data> {
		int node;
		double weight;
		
		public Data(int node, double weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data d) {
			return (int)(weight - d.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 입력 첫째 줄, 별의 개수 (1 ~ 100)
		
		Star[] star = new Star[N];
		
		for (int i = 0; i < N; i++) { // 둘째 줄부터 n개의 줄 입력
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken()); // i번 별의 x좌표 
			double y = Double.parseDouble(st.nextToken()); // i번 별의 y좌표 
			star[i] = new Star(x, y);
		}
		
		double[][] adjMatrix = new double[N][N]; // 인접 행렬
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = getDistance(star[i].x, star[i].y, star[j].x, star[j].y);
			}
		}
		
		PriorityQueue<Data> pq = new PriorityQueue<>(); // 프림알고리즘에 이용할 우선순위 큐
		boolean[] visited = new boolean[N]; // 방문 배열
		double[] distance = new double[N]; // 최단 거리 배열
		Arrays.fill(distance, 987654321); // 시작점으로부터 최단 거리
		distance[0] = 0; // 임의의 0번 별에서부터 시작한다고 가정
		pq.offer(new Data(0, distance[0]));
		
		double total = 0; // 별자리를 만드는 최소 비용
		int cnt = 0; // 현재까지 탐색 노드의 개수
		while (!pq.isEmpty()) {
			Data data = pq.poll();
			int cur = data.node; // 현재 정점
			double weight = data.weight; // 이전 정점에서 현재 정점으로 가는 간선의 가중치
			
			if (visited[cur]) { // 현재 정점을 이미 방문한 경우 패스
				continue;
			}
			
			visited[cur] = true; // 현재 정점을 방문 처리
			total += weight; // 간선의 가중치 누적
			if (++cnt == N) { // 탐색 정점이 N개가 된 경우 중지
				break;
			}
			
			for (int i = 0; i < N; i++) { // 현재 정점기준 탐색 정점들 검사
				if (!visited[i] && distance[i] > adjMatrix[cur][i]) { // 다음정점을 미방문했으면서 최단거리배열에저장된값보다 현재노드->다음노드 간선의 가중치가 작은 경우
					distance[i] = adjMatrix[cur][i];
					pq.offer(new Data(i, distance[i]));
				}
			}
		}
		
		System.out.printf("%.2f", total);
		
	}

	// 두 좌표간 거리 계산해주는 메서드
	private static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

}
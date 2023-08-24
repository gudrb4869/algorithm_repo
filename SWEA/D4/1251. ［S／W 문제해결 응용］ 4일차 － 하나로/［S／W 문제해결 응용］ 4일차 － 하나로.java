import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 최소 신장 트리 문제
 * 프림 알고리즘을 이용하여 해결
 * 각 좌표들의 정보를 저장한다음, i번째 섬을 i번 정점으로 생각하여 인접행렬을 만들었다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {
	
	static class Vertex implements Comparable<Vertex> {
		int no;
		long weight;
		
		public Vertex(int no, long weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st; // 공백 단위 분리
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		int T = Integer.parseInt(br.readLine()); // 전체 테스트 케이스의 수
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine()); // 섬의 개수
			
			long[][] adjMatrix = new long[N][N];
			
			int[] xList = new int[N];
			int[] yList = new int[N];

			st = new StringTokenizer(br.readLine()); // 각 섬들의 x좌표
			for (int i = 0; i < N; i++) {
				xList[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine()); // 각 섬들의 y좌표
			for (int i = 0; i < N; i++) {
				yList[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long d = getDistancePower(xList[i], yList[i], xList[j], yList[j]); // 두 섬을 해저터널로 연결했을때 길이의 제곱
					adjMatrix[i][j] = d;
					adjMatrix[j][i] = d;
				}
			}
			
			double E = Double.parseDouble(br.readLine()); // 해저터널 건설의 환경 부담 세율
			
			// 프림 알고리즘
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			long[] minEdge = new long[N];
			boolean[] visited = new boolean[N];
			Arrays.fill(minEdge, Long.MAX_VALUE);
			minEdge[0] = 0;
			pq.offer(new Vertex(0, minEdge[0]));
			
			int current = 0;
			long weight = 0;
			int cnt = 0;
			double cost = 0;
			
			while (!pq.isEmpty()) {
				Vertex v = pq.poll();
				current = v.no;
				weight = v.weight;
				
				if (visited[current]) {
					continue;
				}
				
				visited[current] = true;
				cost += E * weight;
				if (++cnt == N) break;
				
				for (int i = 0; i < N; i++) {
					if (!visited[i] && adjMatrix[current][i] != 0 && minEdge[i] > adjMatrix[current][i]) {
						minEdge[i] = adjMatrix[current][i];
						pq.offer(new Vertex(i, minEdge[i]));
					}
				}
			}
			
			// 모든 섬들을 잇는 최소 환경 부담금을 소수 첫째 자리에서 반올림하여 정수 형태로 출력
			sb.append("#").append(tc).append(" ").append(Math.round(cost)).append("\n");
		}
		
		System.out.print(sb); // 출력
	}
	
	/**
	 * 두 섬 사이의 거리의 제곱을 반환하는 메서드
	 * @param a
	 * @param b
	 * @return
	 */
	private static long getDistancePower(int x1, int y1, int x2, int y2) {
		long x = x1 - x2;
		long y = y1 - y2;
		return x * x + y * y;
	}

}
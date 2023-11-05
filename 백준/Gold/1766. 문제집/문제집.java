import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1번문제가 가장 쉬운 문제고, N번 문제가 가장 어려운 문제라고 할때
 * N개의 문제를 모두 풀고, 먼저 푸는 것이 좋은 문제를 먼저 풀고, 가능하면 쉬운문제부터 풀어야한다,
 * 위상 정렬과 우선순위 큐를 사용해서 풀어보았다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		int N = Integer.parseInt(st.nextToken()); // 문제의 수(1~32000)
		int M = Integer.parseInt(st.nextToken()); // 먼저 푸는 것이 좋은 문제에 대한 정보의 개수(1~100000)
		
		int[] inDegree = new int[N + 1];
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) { 
			st = new StringTokenizer(br.readLine()); // 두 정수의 순서쌍
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			inDegree[B]++;
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(" ");
			
			for (int next : graph[cur]) {
				if (--inDegree[next] == 0) {
					pq.offer(next);
				}
			}
			
		}
		
		System.out.println(sb);
	}

}
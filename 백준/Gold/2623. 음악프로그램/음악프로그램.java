import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 위상 정렬 알고리즘(BFS-큐) 사용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 가수의 수
		int M = Integer.parseInt(st.nextToken()); // 보조 PD의 수
		
		// 인접 리스트
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 진입 차수
		int[] inDegree = new int[N + 1];
		
		for (int m = 0; m < M; m++) { // 각 보조 PD가 정한 순서들이 한 줄에 하나씩 나옴
			st = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(st.nextToken()); // 보조 PD가 담당한 가수의 수
			
			int[] arr = new int[K];
			for (int i = 0; i < K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 그래프 및 진입 차수 구성
			for (int i = 0; i < K - 1; i++) {
				graph[arr[i]].add(arr[i + 1]);
				inDegree[arr[i + 1]]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			sb.append(cur).append("\n");
			
			for (int next : graph[cur]) {
				if (--inDegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		System.out.println(cnt == N ? sb.toString() : 0);
		
	}

}
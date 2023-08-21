import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 학생 번호 A B가 주어지면 A가 B의 앞에 서야함 => 키순으로 출력해야하므로 A에서 B로 가는 간선으로 표현하면 될거 같다.
 * 답이 여러가지일때, 아무거나 호출 => 굳이 학생 번호 순으로 정렬안해도 될거같다.
 * 위상정렬 이용 (BFS)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M; // 학생의 수(정점의 수), 키를 비교한 회수(간선의 수)
	static List<Integer>[] graph; // 학생의번호가 1부터 N까지이므로 N+1개 생성
	static int[] inDegree; // 정점의 진입차수를 저장할 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생의 수(정점의 수)
		M = Integer.parseInt(st.nextToken()); // 키를 비교한 회수(간선의 수)
		graph = new List[N + 1]; // 학생의번호가 1부터 N까지이므로 N+1개 생성
		inDegree = new int[N + 1]; // 정점의 진입차수를 저장할 배열
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B); // A에서 B로가는 간선
			inDegree[B]++; // B의 진입차수 1증가
		}
		
		topologySort(); // 위상 정렬 알고리즘
		
		System.out.print(sb); // 줄을 세운 결과 출력
	}

	/**
	 * 위상 정렬 알고리즘
	 */
	private static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) { // 진입차수가 0인 노드번호 큐에 삽입
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			int current = q.poll(); // 현재 정점
			sb.append(current).append(" "); // 스트링빌더에 출력결과 삽입
			
			for (int next : graph[current]) { // 현재정점에서 갈수있는 인접 정점들
				inDegree[next]--; // 인접 정점들의 진입차수 1감소
				
				if (inDegree[next] == 0) { // 해당 인접 정점의 진입차수가 0일때
					q.offer(next); // 인접 정점 번호 큐에 삽입
				}
			}
		}
		sb.append("\n");
	}

}
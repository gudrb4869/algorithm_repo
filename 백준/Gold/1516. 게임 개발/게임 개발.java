import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬 알고리즘을 이용하여 문제 해결이 가능하다.
 * @author 박형규
 *
 */
public class Main {

	static int n; // 건물의 종류 수
	static int[] indegree; // 진입차수배열
	static int[] time; // 건물짓는데걸리는시간저장할배열
	static List<Integer>[] graph; // 그래프
	static int[] result; // 건물완성하는데 걸리는 최소시간 저장할 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		indegree = new int[n + 1]; // 건물번호가 1부터 n까지이므로 n+1크기로초기화
		time = new int[n + 1]; // 건물번호가 1부터 n까지이므로 n+1크기로초기화
		result = new int[n + 1]; // 건물번호가 1부터 n까지이므로 n+1크기로초기화
		graph = new List[n + 1]; // 건물번호가 1부터 n까지이므로 n+1크기로초기화
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>(); // 건물번호에 해당하는 그래프정보담기위해 arraylist로 초기화
		}
		
		for (int i = 1; i <= n; i++) { // i번째 건물
			st = new StringTokenizer(br.readLine());
			
			result[i] = time[i] = Integer.parseInt(st.nextToken()); // 각 건물을짓는데 걸리는시간 저장
			
			
			while (true) {
				int j = Integer.parseInt(st.nextToken());
				if (j == -1) { // -1이면 중단
					break;
				}
				indegree[i]++; // i번째 건물을 짓는데 j번째건물이 지어져야하므로 그래프상으로 진입하는 엣지가 생김 따라서 진입차수 1증가시킴
				graph[j].add(i); // j번째 건물에서 i번째로 가는 그래프 간선이 생기는것이므로 j번째 인덱스에 i추가
			}
		}
		
		topologySort(); // 위상정렬 알고리즘 수행
		
		for (int i = 1; i <= n; i++) {
			sb.append(result[i] + "\n"); // 결과 저장
		}
		System.out.print(sb); // 출력
	}

	/**
	 * 위상정렬 알고리즘
	 */
	static void topologySort() {
		Queue<Integer> q = new LinkedList<>(); // 큐 선언
		
		for (int i = 1; i <= n; i++) { // 진입차수 배열 탐색
			if (indegree[i] == 0) { // i번건물의 진입차수가 0이면
				q.add(i); // i번 건물 큐에 저장
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll(); // 큐에서 값 추출
			
			for (int x : graph[now]) { // 그래프 탐색
				
				result[x] = Math.max(result[x], result[now] + time[x]); // x번건물의 최소시간을 now번건물의최소시간과 x번건물짓는데걸리는시간의합과 비교하여 갱신
				
				indegree[x]--; // x번건물 진입차수 1감소
				if (indegree[x] == 0) { // x번건물의 진입차수가 0이면
					q.add(x); // 큐에 x번건물 저장
				}
			}
		}
		
	}

}

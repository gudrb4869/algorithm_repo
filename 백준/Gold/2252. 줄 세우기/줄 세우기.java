import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상 정렬 알고리즘 사용하여 풀이 가능
 * 학생번호가 1부터 시작하므로 n + 1크기의 그래프와 진입차수배열을 만듬
 * m개의 줄에서 학생번호 a와 b를 받는데 그래프a에 b를 넣고 b의 진입차수 1증가시킴
 * 이후 위상정렬 알고리즘 수행
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[n + 1];
		
		List<Integer>[] graph = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			inDegree[b]++;
		}
		
		topologySort(graph, inDegree, n, sb);
		System.out.println(sb);
	}

	/**
	 * 위상 정렬 알고리즘
	 * @param graph 그래프 정보
	 * @param inDegree 진입차수 정보
	 * @param n 학생 수
	 */
	private static void topologySort(List<Integer>[] graph, int[] inDegree, int n, StringBuilder sb) {
		
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			Integer i = q.poll();
			sb.append(i + " ");
			
			for (Integer j : graph[i]) {
				inDegree[j]--;
				
				if (inDegree[j] == 0) {
					q.add(j);
				}
			}
		}
	}

}

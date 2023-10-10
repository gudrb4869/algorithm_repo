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
 * 위상 정렬 알고리즘 이용
 * </pre>
 * 
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 첫째 줄 테스트케이스 개수

		for (int t = 0; t < T; t++) { // 각 테스트케이스
			StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄

			int N = Integer.parseInt(st.nextToken()); // 건물의 개수 N
			int K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙의 총 개수 K

			int[] D = new int[N + 1];

			st = new StringTokenizer(br.readLine()); // 둘째 줄
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken()); // 각 건물당 건설에 걸리는 시간
			}
			
			int[] inDegree = new int[N + 1]; // 진입차수
			int[] time = new int[N + 1]; // 건설완료하는데 드는 최소 시간 저장
			List<Integer>[] graph = new List[N + 1]; // 인접리스트
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				time[i] = D[i];
			}
			
			for (int k = 0; k < K; k++) { // 셋째 줄 ~ K+2줄
				st = new StringTokenizer(br.readLine()); // 건설순서 X Y
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				graph[X].add(Y);
				inDegree[Y]++;
			}
			
			// 위상 정렬 알고리즘 - 너비 우선 탐색
			Queue<Integer> q = new ArrayDeque<>();
			
			for (int i = 1; i <= N; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (int next : graph[cur]) {
					time[next] = Math.max(time[next], time[cur] + D[next]);
					if (--inDegree[next] == 0) {
						q.offer(next);
					}
				}
			}
			
			int W = Integer.parseInt(br.readLine()); // 마지막 줄, 백준이가 승리하기 위해 건설해야 할 건물의 번호
			
			sb.append(time[W]).append("\n"); // 건물 W를 건설하는데 드는 최소 시간
		}

		System.out.print(sb);
	}

}
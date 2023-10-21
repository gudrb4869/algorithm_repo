import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 위상 정렬 이용해서 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int n, student[], count, inDegree[]; // 학생수, 선택된 학생들의번호 저장할배열, 프로젝트 팀에 속하지 못한 학생들의 수, 진입차수 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine()); // 학생의 수
			student = new int[n + 1]; // 선택된 학생들의 번호 저장할 배열
			inDegree = new int[n + 1]; // 진입차수 배열
			st = new StringTokenizer(br.readLine()); // 선택된 학생들의 번호
			
			for (int i = 1; i <= n; i++) {
				student[i] = Integer.parseInt(st.nextToken());
				inDegree[student[i]]++; // 진입차수 1증가
			}
			
			// 위상 정렬
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= n; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				int next = student[cur];
				
				if (--inDegree[next] == 0) {
					q.offer(next);
				}
			}
			
			// 진입 차수가 0인 학생 = 어느 팀에도 속하지 못한 학생
			count = 0;
			for (int i = 1; i <= n; i++) {
				if (inDegree[i] == 0) {
					count++;
				}
			}
			
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
}
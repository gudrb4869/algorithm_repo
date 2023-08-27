import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 서류성적 순위기준 오름차순으로 우선순위큐에 넣고 면접성적 순위가 현재 순위보다 낮을경우 인원수 1증가시키고 현재순위를 갱신해주는 방식으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 지원자의 숫자
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return a[0] - b[0]; // 서류성적 기준 오름차순 정렬
				}
			});
			for (int i = 0; i < N; i++) { // 각각의 지원자의 결과
				st = new StringTokenizer(br.readLine());
				// 서류심사 성적, 면접 성적의 순위 입력받은 값을 우선순위큐에 삽입
				pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			int answer = 1; // 선발할 수 있는 신입사원의 최대 인원 수
			int current = pq.poll()[1]; // 현재 면접 순위
			while (!pq.isEmpty()) {
				int[] info = pq.poll();
				
				if (info[1] < current) { // 지원자의 면접 순위가 현재 순위보다 낮으면
					answer++; // 인원수 1증가시키고
					current = info[1]; // 현재 순위 갱신
				}
				if (info[1] == 1) { // 만약 면접 1위이면 더이상 볼필요없음
					break; // 중단
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}

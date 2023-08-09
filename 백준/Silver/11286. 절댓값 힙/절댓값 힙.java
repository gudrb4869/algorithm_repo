import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * 0이 아닌 수를 힙에 넣었을 떄 절댓값이 가장 작은값을 출력해야 하므로
 * (절댓값, 원래값) 두 쌍으로 힙에 저장하고 min-heap을 통해 값들을 출력하는 방식으로 구현하면 좋을것같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 연산의 개수
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) return a[1] - b[1];
				return a[0] - b[0];
			}
		}); // 우선순위큐 선언 및 정렬조건 정의함. 절댓값이 작은 순으로 추출되는데 절댓값이 같다면 그중에서 가장 작은수부터 추출
		
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine()); // 연산에 대한 정보
			
			if (x != 0) { // 0이 아니면 (절댓값, 실제값)을 우선순위큐에 삽입
				pq.offer(new int[] {Math.abs(x), x});
			} else { // 0이면
				if (pq.isEmpty()) { // 우선순위큐가 비어있는 경우
					sb.append("0\n"); // 0을 출력
				} else { // 우선순위큐가 비어있지 않은경우
					sb.append(pq.poll()[1]).append("\n"); // 우선순위큐에서 값 추출하여 원래값 출력
				}
			}
		}
		System.out.print(sb);
	}

}

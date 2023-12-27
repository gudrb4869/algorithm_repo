import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 우선순위큐에 카드들을 집어넣고, 앞에 있는 가장 작은 두개의 카드를 꺼낸 다음 더한 값을 다시 두번 집어넣는다.
 * 이러면 가장 작은 점수를 획득가능하다.
 * </pre>
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 번째 줄 입력
		
		int n = Integer.parseInt(st.nextToken()); // 카드의 개수 (2 ~ 1000)
		int m = Integer.parseInt(st.nextToken()); // 카드 합체 횟수(0 ~ 15*n)
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			pq.offer(Long.parseLong(st.nextToken())); // 카드에 써있는 수
		}
		
		for (int i = 0; i < m; i++) {
			long result = pq.poll() + pq.poll(); // 두장에 써있는 수를 더한 값
			pq.offer(result);
			pq.offer(result);
		}
		
		long answer = 0;
		for (int i = 0; i < n; i++) {
			answer += pq.poll();
		}
		
		System.out.println(answer); // 가장 작은 점수 출력
	}

}
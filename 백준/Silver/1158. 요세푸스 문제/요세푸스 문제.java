import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 큐를 이용하여 문제 풀시 O(NK)의 시간복잡도를 가지지만 N, K가 5000이하이기 때문에 시간초과 안난다.
 * 만약 N, K의 크기가 더 크다면 다른 알고리즘이나 풀이법을 써봐야할거같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken()); // 원을 이루어 앉아있는 사람의 수
		int k = Integer.parseInt(st.nextToken()); // K번째 사람을 제거
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.offer(i); // 사람의 번호 저장
		}
		
		sb.append("<");
		while (q.size() > 1) { // 마지막 한명 남을때까지 반복
			for (int i = 0; i < k - 1; i++) { // k - 1번째까지 빼고 넣고함
				q.offer(q.poll());
			}
			sb.append(q.poll()).append(", "); // 마지막 번째에서 사람 제거해야하므로 스트링빌더에 저장
		}
		sb.append(q.poll()).append(">\n"); // 마지막 한사람도 저장
		System.out.print(sb); // 출력
	}
}

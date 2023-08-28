import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정수 A를 B로 바꾸려함 (A,B-> 1이상 10억이하의 수)
 * 가능한 연산은 두가지인데 2를 곱하거나, 1을 수의 가장 오른쪽에 추가하는 방법이 있음
 * A를 B로 바꾸는데 필요한 연산의 최솟값을 구하는 문제
 * 
 * 큐에 값들의 상태와 연산의 횟수를 계속 추가해주고, B보다 커진 경우 더이상 볼 필요없으므로 중단시킴
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		bfs(A, B); // 너비 우선 탐색
	}

	private static void bfs(long A, long B) {
		Queue<long[]> q = new ArrayDeque<>();
		q.offer(new long[] {A, 1}); // A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력해야 하므로 그냥 1로 넘겨줌
		while (!q.isEmpty()) {
			long[] info = q.poll();
			long cur = info[0], cnt = info[1]; // 현재값, 연산 횟수
			
			if (cur > B) { // B보다 커진 경우 더이상 볼 필요 X
				continue;
			}
			
			if (cur == B) { // 현재값이 B인 경우
				System.out.println(cnt); // 연산 횟수 출력하고 리턴
				return;
			}
			
			q.offer(new long[] {cur * 2, cnt + 1}); // 1. 2를 곱한다
			q.offer(new long[] {cur * 10 + 1, cnt + 1}); // 2. 1을 수의 가장 오른쪽에 추가한다
		}
		
		System.out.println(-1); // 만들 수 없는 경우
	}

}
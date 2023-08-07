import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 배열을 이용한 풀이
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 원을 이루어 앉아있는 사람의 수
		int k = Integer.parseInt(st.nextToken()); // K번째 사람을 제거
		
		sb.append("<");
		int last = josephus(n, k); // 마지막 남은 사람
		sb.append(last).append(">\n"); // 마지막 한사람도 저장
		System.out.print(sb); // 출력
	}

	/**
	 * <pre>
	 * 배열을 이용한 요세푸스 풀이
	 * </pre>
	 * @param n 사람의 수
	 * @param k
	 * @return 마지막 남은 사람의 번호
	 */
	private static int josephus(int n, int k) {
		boolean[] live = new boolean[n + 1]; // 1번부터 n번까지 쓰기위해 n + 1크기로 초기화
		Arrays.fill(live, true); // 아직 제거되지 않은 상태이므로 true로 초기화
		live[0] = false; // 0번은 안쓰므로 false로 만들어줌
		int cnt = 0; // 제거한 사람의 수
		
		int x = 1; // 시작 위치 1번사람부터 시작
		for (int i = 0; i < n - 1; i++) { // 마지막 한사람 남을 때까지 해야하므로 n - 1번 반복
			int c = 1; // 제거할 수를 고르기 위한 변수. 지금 보는 수 x가 최근에 제거한 수로부터 몇번째 수인지를 의미
			int t = (k - 1) % (n - i) + 1; // 남은 수의 개수가 k보다 작아지면 배열을 여러 차례 돌게 되므로 이를 방지하기 위해 수를 세는 횟수를 k가 아닌 (k - 1)%남은사람의수 + 1로 최적화함
			int prev = cnt; // 한 사람이 제거되는 순간을 포착하기 위해 사용
			while (cnt == prev) { // 한 사람이 제거되어 cnt가 더 커지면 루프 탈출
				if (live[x] == true) { // 아직 남아있고
					if (c == t) { // 제거될 사람이라면
						live[x] = false; // 제거하고
						cnt++; // 제거한사람의 수 1증가시키고
						sb.append(x).append(", "); // 스트링빌더에 삽입
					}
					c++; // live[x]가 true일때만 1더해줌
				}
				x++; // x에 1더함. x가 n+1이 되면 1로 바꿔줌
				if (x == n + 1) {
					x = 1;
				}
			}
			
		}
		for (int i = 1; i <= n; i++) {
			if (live[i]) {
				return i;
			}
		}
		return 0;
	}
}

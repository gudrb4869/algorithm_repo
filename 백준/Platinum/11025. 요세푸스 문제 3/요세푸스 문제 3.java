import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N, K가 10만이하이기 때문에 O(NK)의 시간복잡도로 풀 수 없다.
 * 근데 마지막 남는 사람의 번호만 출력하면 되므로
 * 점화식을 통해 O(n)의 시간복잡도로 문제를 풀 수 있다.
 * 큐를 이요한 풀이를 수행하는 상황에서 생각해 보았을때 n,k가 7,3일때 [1,2,3,4,5,6,7]의 상황에서 첫번째 루프가 수행되면 3이제거되고 [4,5,6,7,1,2]가 될것이다.
 * 이때의 큐를 X라 했을 때 n=6,k=3인 상황에서 계산하는 로직과 완전히 동일하다.
 * 왜냐하면 n=6일때 큐의 상태는[1,2,3,4,5,6]인데 각 요소에 k만큼더하고 n보다 큰 원소는 n을 빼주면 [4,5,6,7,1,2]과 같기 때문이다.
 * 따라서 점화식을 세우면 (f(n, k) = (f(n - 1, k) + k - 1) mod n) +_ 1 이다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 원을 이루어 앉아있는 사람의 수
		int k = Integer.parseInt(st.nextToken()); // K번째 사람을 제거

		int[] dp = new int[n + 1]; // 1번부터 n번 인덱스까지 쓰기 위해 n + 1 크기로 초기화  
		dp[1] = 1; // 사람의 수가 1명일땐 1이다.(기저 조건)
		for (int i = 2; i <= n; i++) {
			dp[i] = ((dp[i - 1] + k - 1) % i) + 1;
		}
		
		System.out.println(dp[n]); // 출력
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * n가지 종류의 동전 중에서 가치의 합을 k원이 되도록 하는 동전의 최소 개수를 구하는 문제이다.
 * 각각의 동전은 몇 개라도 사용이 가능함.
 * 사용한 동전 구성의 순서가 다르더라도 같은 경우로 본다.
 * k원을 만드는게 불가능하다면 -1을 출력
 * 
 * 탑다운 방식으로 구현함
 * 연산의 중복을 방지하기 위해 메모이제이션을 활용했다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static final int INF = 987654321; // 무한대
	static int n, k, dp[]; // 동전의 가짓수, 동전 가치의 합, dp테이블
	static Integer[] coins; // 동전의 가치 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위 입력값 분리
		
		n = Integer.parseInt(st.nextToken()); // 동전의 가짓수 (1 ~ 100)
		k = Integer.parseInt(st.nextToken()); // 요구하는 동전 가치의 합 (1 ~ 10,000)
		
		dp = new int[k + 1]; // dp테이블 초기화
		Arrays.fill(dp, -1); // -1 값으로 초기화
		dp[0] = 0; // 0원을 만들기 위해 필요한 동전갯수는 0
		
		Set<Integer> s = new HashSet<>();
		
		for (int i = 0; i < n; i++) { // 같은 가치의 동전이 여러번 주어질 수도 있으므로 일단 집합에 삽입함.
			int coin = Integer.parseInt(br.readLine());
			if (coin <= k) {
				s.add(coin); // 동전의 가치
			}
		}
		
		coins = s.toArray(new Integer[s.size()]); // 집합->배열로 변환
		Arrays.sort(coins); // 오름차순 정렬
		
		System.out.println(recursion(k) == INF ? -1 : dp[k]); // 무한대면 불가능한경우이므로 -1출력, 그렇지않은경우 동전의 최소갯수 출력
	}

	private static int recursion(int x) {
		if (dp[x] == -1) { // 메모이제이션 안된 경우
			dp[x] = INF;
			for (int i = 0; i < coins.length; i++) { // 코인의 가치 종류의 수만큼 만복
				if (x - coins[i] >= 0) { // 0원 이상인 경우
					dp[x] = Math.min(dp[x], recursion(x - coins[i]) + 1); // 최소 개수로 갱신
				}
			}
		}
		
		return dp[x]; // x원 만드는데 필요한 동전갯수 리턴
	}

}
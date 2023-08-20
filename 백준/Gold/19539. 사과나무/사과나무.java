import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 사과나무를 1~N번까지 심음. 나무의 초기높이는 0
 * 1만큼 성장시키는 물뿌리개, 2만큼 성장시키는 물뿌리개가 있는데
 * 두물뿌리개를 한나무에 사용하여 3만큼 키울수도 있다.
 * 2만큼 성장시키는 물뿌리개는 1만큼 성장시키는 물뿌리개 2번뿌리는것으로도 대체가 가능하다.
 * 따라서 1의개수와 2의개수를 먼저 구한다음,
 * 2의개수가 1의개수보다 많다면 남는 2들을 1로 싹다 바꿔준다.
 * 그러고나서 1의개수와 2의개수가 안같으면 NO, 같으면 YES 출력
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 사과나무의 개수
		int[] arr = new int[N]; // 갊자가 바라는 i번째 나무의 높이를 저장할 1차원 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 각나무의 요구 높이
		}
		
		int odd = 0, even = 0; // 1의 개수, 2의 개수
		for (int i = 0; i < N; i++) {
			odd += arr[i] % 2;
			even += arr[i] / 2;
		}
		
		int temp = Math.max(even - odd, 0) / 3;
		odd += temp * 2;
		even -= temp;
		
		if (odd == even) {
			sb.append("YES\n");
		} else {
			sb.append("NO\n");
		}
		
		System.out.print(sb);
		
	}

}
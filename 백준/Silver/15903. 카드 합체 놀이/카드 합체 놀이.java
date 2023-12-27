import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 카드를 합체할때마다 카드배열을 오름차순 정렬하고, 앞에 있는 가장 작은 두개의 카드를 더해서 덮어씌운다.
 * 이러면 가장 작은 점수를 획득가능하다.
 * </pre>
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 번째 줄 입력
		
		int n = Integer.parseInt(st.nextToken()); // 카드의 개수 (2 ~ 1000)
		int m = Integer.parseInt(st.nextToken()); // 카드 합체 횟수(0 ~ 15*n)
		
		long[] card = new long[n]; // 카드의 상태를 저장할 배열
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			card[i] = Long.parseLong(st.nextToken()); // 카드에 써있는 수
		}
		
		for (int i = 0; i < m; i++) {
			Arrays.sort(card); // 오름차순 정렬
			
			long result = card[0] + card[1]; // 두장에 써있는 수를 더한 값
			card[0] = card[1] = result; // 덮어 씌움
		}
		
		long answer = 0;
		for (int i = 0; i < n; i++) {
			answer += card[i];
		}
		
		System.out.println(answer); // 가장 작은 점수 출력
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그리디 알고리즘
 * i번공장, i+1번공장, i+2번공장에 라면이 남아있을때 i+1번공장라면의개수가 i+2번공장라면의개수보다 작거나 같을경우에만 7원을 들여 라면을 구매할수있다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 첫 번째 줄, 라면 공장의 개수
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()); // 두 번째 줄 N개의 정수
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			while (A[i] > 0) {
				if (i < N - 2 && A[i] > 0 && A[i + 1] > 0 && A[i + 2] > 0 && A[i + 1] <= A[i + 2]) {
					answer += 7;
					A[i]--;
					A[i + 1]--;
					A[i + 2]--;
				} else if (i < N - 1 && A[i] > 0 && A[i + 1] > 0) {
					answer += 5;
					A[i]--;
					A[i + 1]--;
				} else if (A[i] > 0) {
					answer += 3;
					A[i]--;
				}
			}
		}
		
		System.out.println(answer);
	}

}
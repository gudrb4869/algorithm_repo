import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 그리디 알고리즘
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 레벨의 수
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 각 레벨을 클리어하면 얻는 점수
		}
		
		int answer = 0; // 감소 시켜야 되는 최소 횟수
		
		for (int i = N - 2; i >= 0 ; i--) {
			if (arr[i] >= arr[i + 1]) { //현재 레벨의 점수가 다음 레벨의 점수보다 큰 경우
				int cnt = arr[i] - arr[i + 1] + 1; // 감소시킬 횟수
				answer += cnt; // 누적
				arr[i] -= cnt; // 현재 레벨의 점수 감소
			}
		}
		System.out.println(answer);
	}

}
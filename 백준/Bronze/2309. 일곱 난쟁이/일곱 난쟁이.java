import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * 9명중 7명을 뽑는 경우의수를 다 체크하기위해 조합을 이용했다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int[] arr = new int[9]; // 아홉 난쟁이의 키
	private static int[] result = new int[7]; // 조합 결과
	private static int[] answer = new int[7]; // 정답
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		combination(0, 0);
		for (int i = 0; i < 7; i++) {
			System.out.println(answer[i]);
		}
	}
	
	private static void combination(int cnt, int start) {
		if (cnt == 7) { // 7명을 뽑는 경우
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += result[i];
			}
			if (sum == 100) { // 키의 합이 100인경우
				for (int i = 0; i < 7; i++) {
					answer[i] = result[i];
				}
				Arrays.sort(answer); // 키를 오름차순 정렬
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			result[cnt] = arr[i];
			combination(cnt+1, i+1);
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 난이도 의견의 개수
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 사용자들이 제출한 난이도 의견
		}
		
		Arrays.sort(arr);
		int exclude = (int) Math.round((float) n * 3 / 20);
		int answer = 0;
		for (int i = exclude; i < n - exclude; i++) {
			answer += arr[i];
		}
		System.out.println((int) Math.round((float) answer / (n - exclude * 2))); 
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * 최대 10^5개의 숫자로 구성된 N을 입력받아 30의 배수가 되는 수중 가장 큰수를 만듬
 * 
 * 모든 수들의 합이 일단 3의 배수여야하고, 0이 적어도 하나이상 존재해야함
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		char[] c = br.readLine().toCharArray();
		
		int sum = 0; // 모든 수의 합
		boolean isZero = false; // 0의 존재여부
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '0') { // 0이면
				isZero = true; // 0이 존재함
			}
			sum += c[i] - '0'; // 모든 수들을 누적
		}
		
		if (sum % 3 != 0 || !isZero) { // 합이 3의 배수가 아니거나 0이 존재하지 않으면
			System.out.println(-1); // 수가 존재하지 않으므로 -1 출력
			return;
		}
		
		Arrays.sort(c); // 수를 오름차순 정렬
		
		for (int i = c.length - 1; i >= 0; i--) { // 역순으로 출력
			sb.append(c[i]);
		}
		System.out.println(sb);
	}

}
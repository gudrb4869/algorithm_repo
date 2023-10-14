import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 에라토스테네스의 체 활용
 * 2부터 sqrt(max) 이하의 수들 중에서 소수들을 에라토스테네스의 체를 이용하여 찾는다.
 * 2의제곱의 배수, 3의제곱의 배수, 5의제곱의 배수, ... 이처럼 4의제곱수들은 2의제곱수들에 포함되어있고, 6의제곱수들은 2의제곱수, 3의제곱수들에 포함되어있다.
 * 따라서 소수의 제곱수들만 보면 된다. 
 * 그다음 2이상 sqrt(max)이하 내에 존재하는 소수들에 대해 min이상 max이하 내의 수들 중에서 각 소수제곱의 배수인것들을 false로 바꿔준다.
 * 작업을 마치고 true인것들의 개수를 세주기만 하면 된다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long min = Long.parseLong(st.nextToken()); // 1 ~ 1,000,000,000,000
		long max = Long.parseLong(st.nextToken()); // min ~ min + 1,000,000
		
		boolean[] sieve = new boolean[(int)(Math.sqrt(max)) + 1]; // 에라토스테네스의 체
		Arrays.fill(sieve, true);
		sieve[0] = sieve[1] = false;
		for (int i = 2; i <= Math.sqrt(max); i++) {
			if (sieve[i]) {
				for (int j = i + i; j <= Math.sqrt(max); j += i) {
					sieve[j] = false;
				}
			}
		}
		
		// min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수의 개수 출력
		boolean[] numbers = new boolean[(int)(max - min) + 1];
		Arrays.fill(numbers, true);
		for (long i = 2; i <= Math.sqrt(max); i++) {
			if (sieve[(int)i]) {
				for (long j = (long) Math.ceil(1.0 * min / (i * i)); j <= (long) Math.floor(1.0 * max / (i * i)); j++) {
					numbers[(int) (i * i * j - min)] = false;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < max - min + 1; i++) {
			if (numbers[i]) answer++;
		}
		System.out.println(answer);
	}

}
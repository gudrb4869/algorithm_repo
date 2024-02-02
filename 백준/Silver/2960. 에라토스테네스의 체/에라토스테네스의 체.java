import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int count = 0;
		int answer = 0;
		
		boolean[] sieve = new boolean[N + 1];
		Arrays.fill(sieve, true);
		
		for (int i = 2; i <= N; i++) {
			
			if (sieve[i]) {
				for (int j = i; j <= N; j += i) {
					if (sieve[j]) {
						sieve[j] = false;
						count++;
						if (count == K) {
							answer = j;
						}
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
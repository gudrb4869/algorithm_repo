import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int answer = 0;
			
			for (int x = 1; x <= N; x++) {
				 answer += (int)(Math.sqrt(N * N - x * x)) + 1;
			}
			answer *= 4;
			answer += 1;
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
}
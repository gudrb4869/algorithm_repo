import java.util.Scanner;

/**
 * @author 박형규
 *
 */
public class Main {

	private static int n;
	private static StringBuilder sb = new StringBuilder();
	private static int[] lastNum = new int[] {1, 3, 7, 9};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int[] primeNum = new int[] {2, 3, 5, 7};

		for (int i : primeNum) {
			dfs(1, i);
		}
		System.out.print(sb);
	}
	
	private static void dfs(int cnt, int num) {
		
		if (cnt == n) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int candidate = num * 10 + lastNum[i];
			if (isPrime(candidate)) {
				dfs(cnt + 1, candidate);
			}
		}
			
		
	}

	private static boolean isPrime(int num) {
		
		for (int i = 3; i < Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}

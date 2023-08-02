import java.util.Scanner;

/**
 * <pre>
 * 중복없이 m개를 골랐는데 고른 수열이 오름차순이면 조합이다. 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int n, m, numbers[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		numbers = new int[m];
		
		combination(0, 1); // 조합 호출
		System.out.print(sb);
	}
	/**
	 * 조합 함수
	 * @param cnt 현재까지 고른 갯수
	 * @param start 현재 시작 위치
	 */
	private static void combination(int cnt, int start) {
		
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= n; i++) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
		
	}

}

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 박형규
 * n자리의 소수중 신기한 소수를 출력해야한다.
 * 에라토스테네스 체를 만들경우 1억개의 크기만큼 배열을 만들고 1자리부터 N자리까지 모두 체킹을 해야하는데 이건 너무 시간적으로아니면 공간적으로 효율이 별로안좋을거같다.
 * 따라서 백트래킹으로 해결했다.
 *
 */
public class Main {

	private static int n; // 자리수
	private static StringBuilder sb = new StringBuilder();
	private static int[] lastNum = new int[] {1, 3, 7, 9}; // 마지막 자리수에 올수있는 숫자는 1,3,7,9이므로 0~9중 가지치기함
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int[] primeNum = new int[] {2, 3, 5, 7}; // 첫째자리에는 2,3,5,7만 올수있게 미리 가지치기함. 나머지 수는 소수가 아니기때문

		for (int i : primeNum) { // 각각 2, 3, 5, 7로 스타트
			dfs(1, i);
		}
		System.out.print(sb); // 스트링빌더 출력
	}
	
	/**
	 * 백트래킹
	 * @param cnt 현재 자리수
	 * @param num 현재 숫자
	 */
	private static void dfs(int cnt, int num) {
		
		if (cnt == n) { // 자리수가 n이면
			sb.append(num).append("\n"); // 스트링빌더에 저장
			return;
		}
		
		for (int i = 0; i < 4; i++) { // 1,3,7,9
			int candidate = num * 10 + lastNum[i]; // 소수일수도 있는 숫자
			if (isPrime(candidate)) { // 소수이면
				dfs(cnt + 1, candidate); // 다음단계진행
			}
		}
	}

	/**
	 * 소수판별 메서드
	 * @param num 숫자
	 * @return 소수면 true 소수가아니면 false
	 */
	private static boolean isPrime(int num) {
		
		// i를 루트n 까지만 검사
		for (int i = 3; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false; // 소수아님
			}
		}
		return true; // 소수
	}

}

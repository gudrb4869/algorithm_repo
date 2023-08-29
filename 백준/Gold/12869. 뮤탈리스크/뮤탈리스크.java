import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SCV가 최소 1개 최대 3개 있고
 * SCV의 체력은 1이상 60이하의 수이다.
 * 뮤탈리스크는 한번에 세개의 SCV를 공격할 수 있는데
 * 첫번재 SCV는 9의 체력을 잃고, 두번째 SCV는 3의 체력을, 세번째 SCV는 1의 체력을 잃는다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, dp[][][] = new int[61][61][61];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int scv[] = new int[3]; // SCV의 체력을 저장할 1차원 배열
		
		N = Integer.parseInt(br.readLine()); // SCV의 수
		StringTokenizer st = new StringTokenizer(br.readLine()); // SCV의 체력을 공백으로 구분하여 한줄 입력
		for (int i = 0; i < N; i++) { // N마리의 SCV의 체력이 주어짐
			scv[i] = Integer.parseInt(st.nextToken()); // i번째 SCV의 체력을 저장
		}

		// 메모이제이션 3차원 배열 초기화
		for (int i = 0; i <= 60; i++) {
			for (int j = 0; j <= 60; j++) {
				for (int k = 0; k <= 60; k++) {
					dp[i][j][k] = -1; // 아직 memoize 되지 않았다는 뜻에서 -1로 초기화
				}
			}
		}
		dp[0][0][0] = 0; // SCV의 체력이 0,0,0 일때 뮤탈리스크의 공격 횟수는 0 
		
		System.out.println(recursion(scv[0], scv[1], scv[2])); // 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값
	}

	/**
	 * 
	 * @param a 첫번째 SCV의 현재 남은 체력
	 * @param b 두번째 SCV의 현재 남은 체력
	 * @param c 세번째 SCV의 현재 남은 체력
	 * @return 체력 a,b,c를 모두 파괴하기 위한 공격 횟수의 최솟값
	 */
	private static int recursion(int a, int b, int c) {
		
		if (a <= 0 && b <= 0 && c <= 0) { // SCV1과 SCV2와 SCV3의 체력이 모두 0이거나 0보다 작아진경우
			return dp[0][0][0];
		}
		if (a < 0 && b < 0) { // SCV1과 SCV2의 체력만 0보다 작아진 경우
			if (dp[0][0][c] == -1) {
				dp[0][0][c] = recursion(0, 0, c);
			}
			return dp[0][0][c];
		}
		if (a < 0 && c < 0) { // SCV1과 SCV3의 체력만 0보다 작아진 경우
			if (dp[0][b][0] == -1) {
				dp[0][b][0] = recursion(0, b, 0);
			}
			return dp[0][b][0];
		}
		if (b < 0 && c < 0) { // SCV2과 SCV3의 체력만 0보다 작아진 경우
			if (dp[a][0][0] == -1) {
				dp[a][0][0] = recursion(a, 0, 0);
			}
			return dp[a][0][0];
		}
		if (a < 0) { // SCV1의 체력만 0보다 작아진 경우
			if (dp[0][b][c] == -1) {
				dp[0][b][c] = recursion(0, b, c);
			}
			return dp[0][b][c];
		}
		if (b < 0) { // SCV2의 체력만 0보다 작아진 경우
			if (dp[a][0][c] == -1) {
				dp[a][0][c] = recursion(a, 0, c);
			}
			return dp[a][0][c];
		}
		if (c < 0) { // SCV3의 체력만 0보다 작아진 경우
			if (dp[a][b][0] == -1) {
				dp[a][b][0] = recursion(a, b, 0);
			}
			return dp[a][b][0];
		}
		
		if (dp[a][b][c] == -1) { // 아직 결과값이 없는 경우
			if ((a <= 9 && b <= 3 && c <= 1) || (a <= 9 && b <= 1 && c <= 3) || (a <= 3 && b <= 9 && c <= 1) || 
					(a <= 3 && b <= 1 && c <= 9) || (a <= 1 && b <= 9 && c <= 3) || (a <= 1 && b <= 3 && c <= 9)) {
				dp[a][b][c] = 1; // 9, 3, 1 이하인 경우 1번의 공격으로 모든 SCV 파괴 가능
			} else { // 2번 이상의 공격이 필요한 경우
				dp[a][b][c] = Math.min(recursion(a - 9, b - 3, c - 1), Math.min(recursion(a - 9, b - 1, c - 3), 
						Math.min(recursion(a - 3, b - 9, c - 1), Math.min(recursion(a - 3, b - 1, c - 9), 
								Math.min(recursion(a - 1, b - 9, c - 3), recursion(a - 1, b - 3, c - 9)))))) + 1;
			}
			
		}
		
		return dp[a][b][c]; // 공격 횟수의 최솟값 리턴
	}

}
import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * 그리디로도 풀수 있고 dp로도 풀수 있는 문제
 * </pre>
 * @author 박형규
 */
public class Main {

	static int n, dp[], INF = 99999;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // 거스름돈의 액수 (1~100,000)
		
		dp = new int[n + 1]; // dp 테이블
		Arrays.fill(dp, INF); // 무한대로 초기화
		dp[0] = 0; // 0원을 만들기위해 필요한 동전의 개수는 0개
		int result = recursion(n); // n원을 만들기 위해 필요한 동전의 최소 개수
		
		System.out.println(result == INF ? -1 : result); // 거슬러줄수없는경우-1출력, 아니면 거스름돈의최소개수출력
	}
	private static int recursion(int i) {
		
		if (dp[i] == INF) { // 메모이제이션
			if (i - 2 >= 0 && dp[i] > recursion(i - 2) + 1) { // i가 2원보다 크거나같고, i-2원을만들기위해필요한동전수+1이 최소인경우
				dp[i] = recursion(i - 2) + 1; // 갱신
			}
			if (i - 5 >= 0 && dp[i] > recursion(i - 5) + 1) { // i가 5원보다 크거나같고, i-5원을만들기위해필요한동전수+1이 최소인경우
				dp[i] = recursion(i - 5) + 1; // 갱신
			}
		}
		
		return dp[i]; // 결과 리턴
	}

}
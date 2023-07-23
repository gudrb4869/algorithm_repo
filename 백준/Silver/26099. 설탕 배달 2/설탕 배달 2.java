import java.util.Scanner;

/**
 * <pre>
 * n % 5의 값에따라 결과 리턴
 * 나머지가 0인경우 답은 n / 5
 * 나머지가 1인경우 답은 (n - 6) / 5 + 2 => n / 5 + 1
 * 나머지가 2인경우 답은 (n - 12) / 5 + 4인데 n이 7인경우는 예외처리따로해줌 => n / 5 + 2
 * 나머지가 3인경우 답은 나머지가 0인경우에서 3kg짜리 한개 추가한것이므로 n / 5 + 1
 * 나머지가 4인경우 답은 나머지가 1인경우에서 3kg짜리 한개 추가한것이므로 n / 5 + 2
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		System.out.println(solve(n));
	}
	
	private static long solve(long n) {
		if (n == 4 || n == 7) {
			return -1;
		}
		
		if (n % 5 == 0) {
			return n / 5;
		}
		
		if (n % 5 == 1 || n % 5 == 3) {
			return n / 5 + 1;
		}
		
		return n / 5 + 2;
	}

}

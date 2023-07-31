import java.util.Scanner;

/**
 * <pre>
 * 첫번째 장대에서 세번째 장대로 N개의 원판을 옮기기위해선 일단
 * N번원판을 제외한 N - 1개의 원판을 첫번째장대에서 두번째장대로 옮긴후, N번원판을 첫번째장대에서 세번째장대로 옮긴후,
 * N-1개의 원판을 두번째장대에서 세번째 장대로 옮기면 된다.
 * 결국 하노이의탑옮긴횟수 점화식은 A(n) = 2*A(n - 1) + 1, A(1) = 1로 세울수있다.
 * 점화식을 최적화하면 A(n) = 2^n - 1 이다.
 * 이를 재귀함수로 짜면 아래 코드와 같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 원판의 개수
		
		System.out.println((int)Math.pow(2, n) - 1); // 옮긴횟수 출력
		
		hanoi(1, n, 1, 3, 2); // 하노이의탑 재귀함수 호출
		System.out.print(sb);
	}

	/**
	 * 하노이의탑 재귀함수
	 * @param start 원판의 시작번호
	 * @param end 원판의 마지막번호
	 * @param src 원판의 출발지점
	 * @param dst 원판의 도착지점
	 * @param temp 중간지점
	 */
	private static void hanoi(int start, int end, int src, int dst, int temp) {
		if (start > end) // 시작번호가 끝번호보다 큰경우 무시
			return;
		
		if (start == end) { // 시작번호와 끝번호가 같은경우 출발지점 -> 도착지점 출력
			sb.append(src + " " + dst + "\n");
			return;
		}
		
		hanoi(start, end - 1, src, temp, dst); // 마지막번호제외한 원판을 출발지점에서 중간지점으로 이동 
		hanoi(end, end, src, dst, temp); // 마지막번호 원판을 출발지점에서 도착지점으로 이동
		hanoi(start, end - 1, temp, dst, src); // 마지막번호 원판제외한 원판을 중간지점에서 도착지점으로 이동
	}

}

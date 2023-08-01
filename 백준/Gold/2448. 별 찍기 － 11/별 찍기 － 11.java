import java.util.Scanner;

/**
 * <pre>
 * 재귀로 구현함
 * 하나의 삼각형을 세개의 삼각형으로 쪼갤수있다.
 * 기준점이 (r,c)인 x상태의 삼각형은
 * 기준점이(r,c)인 x/2상태의 삼각형
 * 기준점이 (r+x/2,c-x/2)인 x/2상태의 삼각형  
 * 기준점이 (r+x/2,c+x/2)인 x/2상태의 삼각형
 * 세개의 작은 삼각형으로 쪼갤수 있다.
 * 따라서 재귀함수로 구현하였다.  
 * 따라서 기준점
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static char[][] arr; // 문자 저장할 배열
	private static int N, M; // 배열의 행, 열
	private static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt(); // N = 3 * 2^k, 행
		M = 2 * N - 1; // 열
		
		arr = new char[N][M]; // n*m 배열 공백으로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = ' ';
			}
		}
		updateStar(N, 0, M / 2); // 재귀함수호출
		for (int i = 0; i < N; i++) {
			sb.append(String.valueOf(arr[i]) + "\n"); // 한줄씩 결과 저장
		}
		System.out.print(sb); // 출력
	}

	/**
	 * 별찍기
	 * @param x 현재상태
	 * @param r 기준점 행
	 * @param c 기준점 열
	 */
	private static void updateStar(int x, int r, int c) {
		if (x == 3) { // 상태가 3일경우 기준점 기준으로 삼각형 별 찍어주고 리턴 
			arr[r][c] = '*';
			arr[r + 1][c - 1] = '*';
			arr[r + 1][c + 1] = '*';
			arr[r + 2][c - 2] = '*';
			arr[r + 2][c - 1] = '*';
			arr[r + 2][c] = '*';
			arr[r + 2][c + 1] = '*';
			arr[r + 2][c + 2] = '*';
			return;
		}
		updateStar(x / 2, r, c); // 상단부분 재귀호출
		updateStar(x / 2, r + x / 2, c - x / 2); // 좌측하단부분 재귀호출
		updateStar(x / 2, r + x / 2, c + x / 2); // 우측하단부분 재귀호출
	}

}

import java.util.Scanner;

/**
 * <pre>
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 -> nPm (순열)
 * 재귀로 짜고 반복문의 시작값을 현재 단계값으로 하면 순열구현 가능
 * 선택여부 체크하기 위해 isSelected 배열 만듬
 * 순열의 결과값 저장하기 위해 result 배열만들엇음
 * 메모리 62,504 kb
 * 시간 2,184 ms
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	private static int n, m; // 자연수
	private static boolean[] isSelected; // 선택여부 체크 배열
	private static int[] result; // 수열의 결과값 저장할 배열
	private static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		n = sc.nextInt();
		m = sc.nextInt();
		isSelected = new boolean[n + 1]; // 자연수이므로 1부터시작하기 위해 n + 1크기로 초기화
		result = new int[m];
		
		permutation(0);
		System.out.print(sb);
	}

	/**
	 * 순열 재귀함수
	 * @param x 현재 고른 수의 개수
	 */
	private static void permutation(int x) {
		if (x == m) { // m개 다골랏으면 수열 출력하고 리턴
			for (int i : result) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 자연수 차례대로 탐색
		for (int i = 1; i <= n; i++) {
			if (isSelected[i]) {
				continue; // 이미 고른숫자면 건너뜀
			}
			
			isSelected[i] = true; // 선택한 거로 체크하고
			result[x] = i; // x번째 공간에 숫자 넣고
			permutation(x + 1); // 다음 재귀함수 호출
			result[x] = 0; // x번째 공간에 숫자 초기화
			isSelected[i] = false; // 선택 해제
		}
		
	}
}

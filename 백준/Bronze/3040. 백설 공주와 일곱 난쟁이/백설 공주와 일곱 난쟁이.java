import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 9C7조합 써서 문제풀엇다
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int[] arr = new int[9];
	private static int[] result = new int[7];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 아홉 난쟁이의 모자에 쓰여있는 수
		}
		
		combination(0, 0); // 조합 메서드 실행
	}

	private static void combination(int cnt, int start) {
		
		if (cnt == 7) { // 7명 다골랏으면
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += result[i]; // 일곱 난쟁이 모자에 쓰여있는 수들의 합 계산
			}
			if (sum == 100) { // 합이 100이면
				for (int i = 0; i < 7; i++) {
					System.out.println(result[i]); // 모자에 써있는 수 한줄씩 출력
				}
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			result[cnt] = arr[i]; // 모자 선택
			combination(cnt+1, i+1); // 다음 재귀 호출
		}
		
	}

}

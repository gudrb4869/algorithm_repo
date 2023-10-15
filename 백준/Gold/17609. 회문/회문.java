import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 회문 검사를 위해 투 포인터를 사용했다.
 * left 포인터는 한칸씩 증가하고, right 포인터는 한칸씩 감소하게 했다.
 * 두 포인터가 가리키는 곳의 문자가 같으면 left는 1증가, right는 1감소하였고,
 * status가 0일때 두 문자가 다르면 left만 1증가시킨 재귀 결과와, right만 1감소시킨 결과가 회문이면 1을 리턴
 * status가 1일때 두 포인터가 가리키는 문자가 또 다르면 2를 리턴
 * status가 0일때 두 문자가 모두 같다면 0을 리턴
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 문자열의 개수 (1 ~ 30)
		
		for (int t = 0; t < T; t++) {
			char[] str = br.readLine().toCharArray(); // 문자열
			
			int left = 0, right = str.length - 1; // 두 포인터
			
			int result = isPalindrome(str, left, right, 0); // 회문 검사
			
			sb.append(result).append("\n"); // 회문인지, 유사 회문인지, 둘 모두 해당하지 않는지 판단하여 출력 결과 스트링빌더에 삽입
		}
		
		System.out.print(sb); // 모든 결과 출력
	}

	/**
	 * 회문 검사
	 * @param str 검사할 문자열
	 * @param left 포인터
	 * @param right 포인터
	 * @param status 현재 회문의 상태(0=회문, 1=유사 회문, 2=둘 모두 아님)
	 * @return 회문의 상태
	 */
	private static int isPalindrome(char[] str, int left, int right, int status) {
		while (left <= right) {
			if (str[left] == str[right]) { // 두 포인터가 가리키는 문자가 같은 경우
				left++;
				right--;
			} else { // 두 포인터가 가리키는 문자가 다른 경우
				
				// 상태가 아직 회문이고, left만 1증가시킨 재귀 결과가 유사회문이거나 right만 1감소시킨 재귀결과가 유사회문인 경우
				if (status == 0 && (isPalindrome(str, left + 1, right, status + 1) == 1 || isPalindrome(str, left, right - 1, status + 1) == 1)) {
					return 1; // 1리턴
				} else { // 그 외의 경우
					return 2; // 2리턴
				}
			}
		}
		return status; // 회문의 상태 리턴
	}

}
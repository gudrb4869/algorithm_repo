import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 시작점과 끝점을 각각 +1, -1칸씩 땡겨보면서 일치하는지를 검사하면 된다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		while (true) {
			String input = br.readLine();
			if (input.equals("0")) { // 0인 경우 마지막 줄
				break; // 중단
			}
			
			if (isPalindrome(input)) { // 팰린드롬수인 경우
				sb.append("yes\n");
			} else { // 팰린드롬수가 아닌 경우
				sb.append("no\n");
			}
		}
		
		System.out.print(sb);
	}

	private static boolean isPalindrome(String input) {
		for (int i = 0; i < input.length() / 2; i++) {
			if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
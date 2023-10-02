import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * Equilateral :  세 변의 길이가 모두 같은 경우
 * Isosceles : 두 변의 길이만 같은 경우
 * Scalene : 세 변의 길이가 모두 다른 경우
 * 단 주어진 세 변의 길이가 삼각형의 조건을 만족하지 못하는 경우에는 "Invalid" 를 출력
 * 가장 긴 변의 길이보다 나머지 두 변의 길이의 합이 길지 않으면 삼각형의 조건을 만족하지 못한다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == 0 && b == 0 && c == 0) {
				break;
			}
			
			if (a == b && b == c && c == a) {
				sb.append("Equilateral\n");
			} else if ((a == b && b != c && c != a) || (a != b && b == c && c != a) || (a != b && b != c && c == a)) {
				int maxLen = Math.max(a, Math.max(b, c));
				int twoSum = a + b + c - maxLen;
				
				if (maxLen < twoSum) {
					sb.append("Isosceles\n");
				} else {
					sb.append("Invalid\n");
				}
			} else if (a != b && b != c && c != a) {
				int maxLen = Math.max(a, Math.max(b, c));
				int twoSum = a + b + c - maxLen;
				
				if (maxLen < twoSum) {
					sb.append("Scalene\n");
				} else {
					sb.append("Invalid\n");
				}
			}
		}
		
		System.out.print(sb.toString());
	}

}
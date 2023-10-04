import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * KMP 알고리즘의 부분일치 테이블 생성 메서드를 사용하여 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		String str = br.readLine(); // 첫째 줄, 문자열
		
		int answer = 0; // 입력에서 주어진 문자열의 두 번이상 나오는 부분문자열 중에서 가장 긴 길이
		for (int i = 0; i < str.length(); i++) {
			answer = Math.max(answer, createTable(str.substring(i)));
		}
		
		System.out.println(answer); // 출력
	}

	private static int createTable(String pattern) {
		int N = pattern.length(); // 패턴문자열 길이
		int[] pi = new int[N]; // 부분일치 테이블
		int maxLength = 0; // 두 번이상 나오는 부분문자열 중에서 가장 긴 길이
		
		int j = 0;
		for (int i = 1; i < N; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
				maxLength = Math.max(maxLength, pi[i]);
			}
		}
		return maxLength;
	}


}
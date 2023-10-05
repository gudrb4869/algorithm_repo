import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * KMP 알고리즘 수행
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		int N = Integer.parseInt(br.readLine()); //첫째 줄, Pn -> IOIOI...OI (o가 N개)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append("IO");
		}
		sb.append("I");
		String pattern = sb.toString(); // 찾을 패턴 Pn
		int M = Integer.parseInt(br.readLine()); //둘째 줄, 문자열 S의 길이
		String S = br.readLine(); // 셋째 줄, 문자열 S
		
		System.out.println(kmp(S, pattern)); // kmp 알고리즘 수행후 S에 포함된 Pn의 개수 출력
	}

	private static int kmp(String string, String pattern) {
		int answer = 0;
		int stringSize = string.length();
		int patternSize = pattern.length();
		int[] pi = createTable(pattern, patternSize);
		int j = 0;
		for (int i = 0; i < stringSize; i++) {
			while (j > 0 && string.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (string.charAt(i) == pattern.charAt(j)) {
				if (j == patternSize - 1) {
					answer++;
					j = pi[j];
				} else {
					j++;
				}
			}
		}
		return answer;
	}

	private static int[] createTable(String pattern, int patternSize) {
		int[] pi = new int[patternSize];
		
		int j = 0;
		for (int i = 1; i < patternSize; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		return pi;
	}

}
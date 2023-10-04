import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * KMP 알고리즘 사용하여 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		char[] T = br.readLine().toCharArray(); // 첫째 줄, 문자열 T
		char[] P = br.readLine().toCharArray(); // 둘째 줄, 문자열 P
		
		List<Integer> result = new ArrayList<>();
		
		// KMP 알고리즘
		KMP(T, P, result);
		
		sb.append(result.size()).append("\n");
		for (int idx : result) {
			sb.append(idx).append(" ");
		}
		System.out.println(sb);
	}

	// KMP 알고리즘
	private static void KMP(char[] T, char[] P, List<Integer> result) {
		// 부분일치 테이블 배열 만들기
		int[] table = makeTable(P);
		int j = 0;
		for (int i = 0; i < T.length; i++) {
			while (j > 0 && T[i] != P[j]) {
				j = table[j - 1];
			}
			if (T[i] == P[j]) {
				if (j == P.length - 1) {
					result.add(i - P.length + 2);
					j = table[j];
				} else {
					j++;
				}
			}
		}
	}

	// 부분일치 테이블 배열 만들기
	private static int[] makeTable(char[] P) {
		int[] table = new int[P.length];
		
		int j = 0;
		for (int i = 1; i < P.length; i++) {
			while (j > 0 && P[i] != P[j]) {
				j = table[j - 1];
			}
			
			if (P[i] == P[j]) {
				table[i] = ++j;
			}
		}
		return table;
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * kmp 알고리즘
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			String s = br.readLine(); // 문자열 s
			
			if (s.equals(".")) { // 마지막 테스트 케이스는 마침표
				break;
			}
			
			sb.append(createTable(s)).append("\n"); // s=a^n을 만족하는 가장 큰 n을 찾은 뒤 스트링빌더에 저장
		}
		
		System.out.print(sb); // 모든 테스트 케이스 결과 출력
	}
	
	/**
	 * 전처리 작업, 실패함수. 부분일치 테이블 생성
	 * 시간복잡도 O(M)
	 * @param p 문자열
	 * @return s=a^n을 만족하는 가장큰 n
	 */
	static int createTable(String p) {
		int M = p.length();
		int[] pi = new int[M]; // 부분일치 테이블
		
		int j = 0;
		for (int i = 1; i < M; i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		return M % (M - pi[M - 1]) != 0 ? 1 : M / (M - pi[M - 1]);
	}

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 자음 consonant
 * 모음 vowel
 * 최소 한개의 모음, 최소 두개의 자음 => 기저조건에서 검사하면 될듯
 * 사전순으로 가능성있는 암호 => 조합을 이용하면 되보임
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int L, C; // 암호를 구성하는 알파벳 개수, 암호로 사용가능한 문자의 개수
	static char[] alphabet; // 알파벳을 저장할 1차원 배열
	static char[] password; // 가능성있는 암호를 저장할 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 암호를 구성하는 알파벳의 개수
		C = Integer.parseInt(st.nextToken()); // 암호로 사용가능한 문자의 개수
		
		alphabet = new char[C]; // 알파벳을 저장할 1차원 배열
		password = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet); // 사전순으로 결과를 얻기 위해 사전순 정렬
		
		dfs(0, 0); // 재귀 탐색
		
		System.out.print(sb); // 결과 출력
	}

	private static void dfs(int cnt, int start) {
		
		if (cnt == L) { // 기저조건
			
			if (isPossible()) { // 최소한개의 모음과 최소 두개의 자음으로 구성된 경우
				sb.append(String.valueOf(password)).append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			password[cnt] = alphabet[i];
			dfs(cnt + 1, i + 1);
		}
		
	}

	/**
	 * 가능성 있는 암호인지 판단하는 메서드
	 * @return
	 */
	private static boolean isPossible() {
		int consonant = 0, vowel = 0; // 자음, 모음 개수
		
		for (int i = 0; i < L; i++) {
			if (password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o' || password[i] == 'u') {
				vowel++;
			} else {
				consonant++;
			}
		}
		return vowel >= 1 && consonant >= 2; // 최소 한개의 모음과 최소 두개의 자음으로 구성된 경우
	}

}
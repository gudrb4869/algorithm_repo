import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 암호는 L개의 알파벳 소문자로 구성. 최소 한개의 모음(a,e,i,o,u)과 최소 두개의 자음으로 구성되어 있음
 * 또한 암호는 항상 사전순으로 배열되어있다. abc는 가능성 있지만 bac는 그렇지 않음
 * C개의 문자가 주어졌을때 가능성 있는 암호들을 모두 구해야함
 * 사전순으로 배열되어있으므로 L개중에 C개를 순서를 고려하지 않고 뽑는 조합을 이용하면 될거같다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int L, C, numbers[];
	static char alphabet[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 암호를 구성하는 문자의 개수
		C = Integer.parseInt(st.nextToken()); // 보안 시스템에 존재하는 문자의 개수
		
		numbers = new int[L];
		alphabet = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0); // C개의 문자 저장
		}
		
		Arrays.sort(alphabet); // 사전순으로 오름차순 정렬
		combination(0, 0); // cCl 조합 경우의 수 완전탐색
		
		System.out.print(sb); // 사전식 가능성있는 암호를 모두 출력
	}

	private static void combination(int cnt, int start) {

		if (cnt == L) { // L개로 암호제작 끝난경우
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < L; i++) {
				temp.append(alphabet[numbers[i]]); // 가능성 있는 암호 생성
			}
			String password = temp.toString(); // 가능성 있는 암호 후보
			if (isPossible(password)) { // 가능성있는 암호라면
				sb.append(password).append("\n"); // 스트링빌더에 저장
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	private static boolean isPossible(String password) {
		int consonant = 0, vowel = 0; //자음, 모음 개수
		for (int i = 0; i < password.length(); i++) {
			char current = password.charAt(i); // 현재 검사할 문자
			if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u') { // 모음이면
				vowel++; // 모음개수 1증가
			} else { //자음이면
				consonant++; // 자음개수 1증가
			}
		}
		return consonant >= 2 && vowel >= 1; // 최소2개의 자음이 있고 최소 1개의 모음이 있는 경우에만 true 리턴
	}

}

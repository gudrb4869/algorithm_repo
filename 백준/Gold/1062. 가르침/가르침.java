import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * dfs와 백트래킹을 이용해서 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, K, answer; // 단어의 개수, 지민이가 가르칠 수 있는 글자수, 학생들이 읽을 수 있는 단어 개수의 최댓값
	static boolean[] alphabet = new boolean[26]; // N개의 단어들을 통틀어 해당알파벳이 존재하는지 유무 판별
	static boolean[] readable = new boolean[26]; // K개를 가르치는동안 읽을 수 있는 알파벳인지 판별
	static String[] s; // N개의 단어 저장할 문자열 배열
	static int count; // N개의 단어에 존재하는 알파벳의 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 단어의 개수
		K = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 글자수
		
		s = new String[N]; // N개의 단어를 저장할 문자열 배열 생성
		
		for (int i = 0; i < N; i++) {
			s[i] = br.readLine(); // 단어
			for (int j = 0; j < s[i].length(); j++) {
				alphabet[s[i].charAt(j) - 'a'] = true; // 해당알파벳이 존재한다면 true로 세팅
			}
		}
		
		for (int i = 0; i < 26; i++) {
			if (alphabet[i]) {
				count++;
			}
		}
		
		if (K >= 5) { // 모든단어는 anta로 시작하고 tica로 끝나는데 a,c,i,n,t 단어를 가르치려면 적어도 K가 5이상이어야함
			if (count <= K) { // N개의 단어에 있는 모든 알파벳의 개수가 가르쳐야하는 알파벳의 개수보다 작거나 같으면 모든 단어를 읽을 수 있으므로
				answer = N; // dfs와 백트래킹 수행하지 않음
			} else { // N개의 단어에 존재하는 알파벳이 가르쳐야하는 알파벳의 개수보다 많다면 dfs와 백트래킹 수행
				readable['a' - 'a'] = true; // a를 읽을 수 있도록 세팅
				readable['c' - 'a'] = true; // c를 읽을 수 있도록 세팅
				readable['i' - 'a'] = true; // i를 읽을 수 있도록 세팅
				readable['n' - 'a'] = true; // n를 읽을 수 있도록 세팅
				readable['t' - 'a'] = true; // t를 읽을 수 있도록 세팅
				dfs(0, 5); // DFS + 백트래킹 수행
			}
		}
		
		System.out.println(answer); // 출력
	}

	private static void dfs(int start, int cnt) {
		
		if (K - cnt > 26 - start) { // 가지치기(현재 지민이가 가르칠 수 있는 문자의 개수가, 살펴보고자 하는 남은 알파벳의 개수보다 적다면 더이상 진행하지 않음)
			return;
		}
		
		if (cnt == K) { // K개의 알파벳을 다 가르치는데 완료했다면 몇개의 단어를 읽을 수 있는지 점검
			int total = 0; // 현재 가르친 알파벳들로 읽을 수 있는 단어의 개수
			for (int i = 0; i < N; i++) {
				boolean isReadable = true;
				for (int j = 4; j < s[i].length() - 4 && isReadable; j++) {
					isReadable &= readable[s[i].charAt(j) - 'a']; // anta와 tica사이에 있는 문자열들만 살펴봄
				}
				if (isReadable) { // 읽을 수 있다면
					total++; // 개수 1증가
				}
			}
			answer = Math.max(answer, total); // 가장 큰 값으로 갱신
			return;
		}
		
		for (int i = start; i < 26; i++) { // a부터 z까지 살펴봄
			if (!alphabet[i] || readable[i]) continue; // N개의 단어에서 존재하지 않는 알파벳이거나 이미 읽을수 있는 알파벳(a,c,i,n,t)이면 skip
			readable[i] = true;
			dfs(i + 1, cnt + 1); // 재귀 호출
			readable[i] = false;
		}
		
	}

}
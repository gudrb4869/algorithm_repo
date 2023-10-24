import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine(); // 첫째 줄에 S
		String T = br.readLine(); // 둘째 줄에 T
		
		int sLen = S.length(); // S의 길이
		int tLen = T.length(); // T의 길이
		
		Stack<Character> s = new Stack<>();
		
		for (int i = 0; i < tLen; i++) {
			s.push(T.charAt(i));
		}
		
		// S를 T로 바꿀 수 있는지 알아내기
		while (s.size() > sLen) {
			char top = s.pop();
			if (top == 'B') {
				Collections.reverse(s);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.size(); i++) {
			sb.append(s.get(i));
		}
		String result = sb.toString();
		
		// 출력 결과
		System.out.println(result.equals(S) ? 1 : 0);
	}


}
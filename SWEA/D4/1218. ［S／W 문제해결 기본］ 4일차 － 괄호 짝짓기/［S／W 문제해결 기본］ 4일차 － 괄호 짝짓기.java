import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <pre>
 * 스택 4개를 써서 문제해결
 * 문자로 인덱스접근하기 귀찮아서 맵 사용함
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	private static Map<Character, Character> m1 = new HashMap<>(); // 괄호 종류별로 짝짓기위해만든 맵
	private static Map<Character, Integer> m2 = new HashMap<>(); // 각각의 괄호들 인덱스로 치환하기 위한 맵
	
	private static int n; // 테스트케이스의 길이
	private static char[] arr; // 테스트케이스
	private static Stack<Character>[] s; // 스택 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		m1.put(')', '('); // )는 (에 매핑
		m1.put(']', '[');
		m1.put('}', '{');
		m1.put('>', '<');
		
		m2.put('(', 0); // 각 괄호에 맞게 인덱스화함
		m2.put(')', 0);
		m2.put('[', 1);
		m2.put(']', 1);
		m2.put('{', 2);
		m2.put('}', 2);
		m2.put('<', 3);
		m2.put('>', 3);
		
		for (int t = 1; t <= 10; t++) {
			n = Integer.parseInt(br.readLine());
			arr = br.readLine().toCharArray();
			
			s = new Stack[4]; // 스택 4개 생성
			for (int i = 0; i < 4; i++) {
				s[i] = new Stack<>(); // 각각의 스택 초기화
			}
			
			sb.append("#").append(t).append(" ").append(isValid()?1:0).append("\n"); // 결과 저장
		}
		System.out.print(sb); // 출력
	}
	private static boolean isValid() {
		for (int i = 0; i < n; i++) { // 문자열 탐색
			if (m1.keySet().contains(arr[i])) { // ), ], }, > 인 경우
				int idx = m2.get(arr[i]); // 인덱스얻어옴
				if (s[idx].isEmpty()) { // 해당 괄호에 해당하는 스택이 비어있는 경우
					return false; // 유효하지않음
				}
				s[idx].pop(); // 스택이 비어있지 않은 경우 팝
			} else { // (, [, {, < 인 경우
				s[m2.get(arr[i])].push(arr[i]); // 해당 괄호에 해당하는 스택에 값 푸쉬
			}
		}
		
		// 4개의 스택중 비어있지 않은 스택이 한개라도 존재하는 경우 유효하지 않음
		if (!s[0].isEmpty() || !s[1].isEmpty() || !s[2].isEmpty() || !s[3].isEmpty()) {
			return false;
		}

		return true; // 유효함
	}
	
	
	
}

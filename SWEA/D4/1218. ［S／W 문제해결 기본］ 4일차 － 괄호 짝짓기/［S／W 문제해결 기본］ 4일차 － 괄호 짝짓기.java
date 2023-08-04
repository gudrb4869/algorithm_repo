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
 * 메모리 18,640 KB
 * 실행시간 109 ms
 *
 */
public class Solution {

	private static Map<Character, Integer> open = new HashMap<>(); // 여는괄호 인덱스로 바꾸기 위한 맵
	private static Map<Character, Integer> close = new HashMap<>(); // 닫는괄호 인덱스로 바꾸기 위한 맵
	
	private static int n; // 테스트케이스의 길이
	private static char[] arr; // 테스트케이스
	private static Stack<Character>[] s; // 스택 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		open.put('(', 0); // 여는 괄호 인덱스화함
		open.put('[', 1);
		open.put('{', 2);
		open.put('<', 3);

		close.put(')', 0); // 닫는 괄호 인덱스화함
		close.put(']', 1);
		close.put('}', 2);
		close.put('>', 3);
		
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
			if (close.keySet().contains(arr[i])) { // ), ], }, > 인 경우
				int idx = close.get(arr[i]); // 인덱스얻어옴
				if (s[idx].isEmpty()) { // 해당 괄호에 해당하는 스택이 비어있는 경우
					return false; // 유효하지않음
				}
				s[idx].pop(); // 스택이 비어있지 않은 경우 팝
			} else { // (, [, {, < 인 경우
				s[open.get(arr[i])].push(arr[i]); // 해당 괄호에 해당하는 스택에 값 푸쉬
			}
		}
		
		// 4개의 스택중 비어있지 않은 스택이 한개라도 존재하는 경우 유효하지 않음
		if (!s[0].isEmpty() || !s[1].isEmpty() || !s[2].isEmpty() || !s[3].isEmpty()) {
			return false;
		}

		return true; // 유효함
	}
	
}

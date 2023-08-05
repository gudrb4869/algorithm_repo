import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <pre>
 * 스택을 이용하여 문제해결
 * </pre>
 * @author 박형규 
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] c = br.readLine().toCharArray(); // 중위표기식
		
		Stack<Character> operator = new Stack<>(); // 연산자 및 괄호 저장할 스택
		for (int i = 0; i < c.length; i++) { // 중위표기식 탐색
			if (c[i] >= 'A' && c[i] <= 'Z') { // 알파벳 대문자면
				sb.append(c[i]); // 스트링빌더에 삽입
				while (!operator.isEmpty() && !operator.peek().equals('(')
						&& i + 1 < c.length && isFirst(operator.peek(), c[i + 1])) {
					// 연산자 스택에 값이 있고, 스택의 top값이 여는 괄호가 아니고 스택의 top연산자가 최우선순위이면 
					sb.append(operator.pop()); // 연산자 추가
				}
			} else if (c[i] == ')') { // 닫는괄호면서 연산자스택의 top값이 여는괄호면
				operator.pop(); // ( 제거
				while (!operator.isEmpty() && !operator.peek().equals('(')
						&& i + 1 < c.length && isFirst(operator.peek(), c[i + 1])) {
					// 연산자 스택에 값이 있고, 스택의 top값이 여는 괄호가 아니고 스택의 top연산자가 최우선순위이면 
					sb.append(operator.pop()); // 연산자 추가
				}
			} else { // 닫는괄호 제외한 기호일경우
				operator.push(c[i]); // 연산자스택에 삽입
			}
		}
		while (!operator.isEmpty()) { // 연산자 스택에 아직 값이 남아있다면
			sb.append(operator.pop()); // 팝하여 스트링빌더에삽입
		}
		
		System.out.println(sb); // 출력
	}

	/**
	 * 연산자 최우선순위여부 메서드
	 * @param tos 연산자스택 최상단 연산자
	 * @param next 다음 연산자
	 * @return 최우선순위 여부
	 */
	private static boolean isFirst(char tos, char next) {
		if (tos == '*' || tos == '/' || next == ')' || next == '+' || next == '-') { // 스택최상단이 곱셈나눗셈이거나, 다음연산자가 닫는괄호,덧셈,뺄셈이면
			return true; // 스택최상단연산자가 우선순위가짐
		}
		return false; // 아직 우선순위아님
	}

}

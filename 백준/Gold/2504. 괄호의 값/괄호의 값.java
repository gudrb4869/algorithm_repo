import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 괄호열을 나타내는 문자
		char[] str = br.readLine().toCharArray();
		
		// 계산 중간값
		int temp = 1;
		
		// 괄호열의 값
		int answer = 0;
		
		Stack<Character> s = new Stack<>();
		
		for (int i = 0; i < str.length; i++) {
			
			char c = str[i];
			
			if (c == '(') {
				s.push('(');
				temp *= 2;
			} else if (c == '[') {
				s.push('[');
				temp *= 3;
			} else if (c == ')') {
				if (s.isEmpty() || s.peek() != '(') {
					// 올바른 괄호열 아닌경우
					System.out.println(0);
					return;
				} else {
					// 올바른 괄호열인 경우
					// 한쌍의 괄호 () 로만 이루어진 경우 계산 중간값 누적
					if (str[i - 1] == '(') answer += temp;
					s.pop();
					// 계산 중간값 2로 나눔
					temp /= 2;
				}
			} else if (c == ']') {
				if (s.isEmpty() || s.peek() != '[') {
					// 올바른 괄호열 아닌 경우
					System.out.println(0);
					return;
				} else {
					// 올바른 괄호열인 경우
					// 한쌍의 괄호 [] 로만 이루어진 경우 계산 중간값 누적
					if (str[i - 1] == '[') answer += temp;
					s.pop();
					// 계산 중간값 3으로 나눔
					temp /= 3;
				}
			}
		}
		
		// 스택에 괄호 남아있는 경우 -> 올바른 괄호열 아님
		if (!s.isEmpty()) {
			System.out.println(0);
			return;
		}
		
		// 결과 출력
		System.out.println(answer);
	}
}
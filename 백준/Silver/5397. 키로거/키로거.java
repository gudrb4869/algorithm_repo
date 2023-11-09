import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <pre>
 * 백스페이스: -
 * 화살표: <, >
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			char[] str = br.readLine().toCharArray();
			
			Stack<Character> stack = new Stack<>();
			Stack<Character> buffer = new Stack<>();
			
			for (char c : str) {
				if (c == '-') {
					if (!stack.isEmpty()) {
						stack.pop();
					}
				} else if (c == '<') {
					if (!stack.isEmpty()) {
						buffer.push(stack.pop());
					}
				} else if (c == '>') {
					if (!buffer.isEmpty()) {
						stack.push(buffer.pop());
					}
				} else {
					stack.push(c);
				}
			}
			
			while (!buffer.isEmpty()) {
				stack.push(buffer.pop());
			}
			
			for (char c : stack) {
				sb.append(c);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] S = br.readLine().toCharArray();
		int N = S.length;
		
		Stack<Character> stack = new Stack<>();
		
		int i = 0;
		while (i < N) {
			if (S[i] == ' ') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(" ");
			} else if (S[i] == '<') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				while (S[i] != '>') {
					sb.append(S[i]);
					i++;
				}
				sb.append('>');
			} else {
				stack.push(S[i]);
			}
			i++;
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb);
	}

}
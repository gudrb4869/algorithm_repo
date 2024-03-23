import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 피연산자의 개수
		int N = Integer.parseInt(br.readLine());
		
		// 후위 표기식
		char[] op = br.readLine().toCharArray();
		
		// 각 피연산자에 대응하는 값
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Double> stack = new Stack<>();
		
		for (char c : op) {
			if (c >= 'A' && c <= 'Z') {
				stack.push(1.0 * arr[c - 'A']);
				continue;
			}
			
			Double second = stack.pop();
			Double first = stack.pop();
			if (c == '+') {
				stack.push(first + second);
			} else if (c == '-') {
				stack.push(first - second);
			} else if (c == '*') {
				stack.push(first * second);
			} else if (c == '/') {
				stack.push(first / second);
			}
		}
		
		System.out.printf("%.2f", stack.peek());
	}

}
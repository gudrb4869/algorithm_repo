import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <pre>
 * 스택을 써서 문제를 풀었다.
 * </pre>
 */
public class Main {
	
	static class Data {
		char c;
		int idx;

		public Data(char c, int idx) {
			this.c = c;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray(); // 쇠막대기와 레이저의 배치를 나타내는 괄호 표현

		Stack<Data> s = new Stack<>();
		
		int answer = 0; // 잘려진 조각의 총 개수
		int index = 0; // 인덱스
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') { // (인경우
				s.push(new Data(str[i], index++));
			} else { // )인경우
				if (index - s.peek().idx == 1) { // 레이저인 경우
					answer += s.size() - 1; // 쇠막대기의 개수만큼 총개수 누적
				} else { // 막대기끝인 경우
					answer++; // 1조각 누적
				}
				s.pop(); // 스택의 top 제거
			}
		}
		
		System.out.println(answer); // 출력
	}

}
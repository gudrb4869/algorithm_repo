import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <pre>
 * N자리 숫자가 주어졌을 때, 숫자 K개를 지워서 얻을 수 있는 가장 큰 수는?
 * 
 * => 그리디 알고리즘으로 푸는데, N과 K가 최대 50만이어서 자료구조 스택을 이용해 시간복잡도 O(N)으로 풀었다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 기준으로 분리
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		// 1 <= K < N <= 500,000
		int N = Integer.parseInt(st.nextToken()); // 숫자의 자릿수 (1~500,000)
		int K = Integer.parseInt(st.nextToken()); // 지울 숫자의 개수 (1~500,000)
		
		String str = br.readLine(); // N자리 숫자
		int[] number = new int[N]; // 각 자리마다 수를 저장할 N크기의 1차원배열 초기화
		for (int i = 0; i < N; i++) {
			number[i] = str.charAt(i) - '0'; // 각 자리마다 수 저장
		}
		
		int count = 0; // 지운 숫자의 개수
		Stack<Integer> s = new Stack<>(); // 스택
		for (int i = 0; i < N; i++) { // N자리 숫자를 순차 탐색
			// 스택이 비어있지 않고, 스택의 top이 현재 보고 있는 자리의 수보다 작고, 지운숫자의 개수가 아직 K보다 작은 경우 반복문 수행
			while (!s.isEmpty() && s.peek() < number[i] && count < K) {
				count++; // 지운 숫자의 개수 1증가
				s.pop(); // 스택 pop
			} 
			s.push(number[i]); // 스택에 현재 보고 있는 자리의 수 push
		}
		
		while (count < K) { // 아직 지운 숫자의 개수가 K보다 작은경우
			count++; // 지운 숫자의 개수 1증가
			s.pop(); // 스택 pop
		}
		
		for (int i = 0; i < s.size(); i++) { // 스택에 남아있는 수 순차 탐색
			sb.append(s.get(i)); // 스트링빌더에 삽입
		}
		
		System.out.println(sb); // 출력
	}

}
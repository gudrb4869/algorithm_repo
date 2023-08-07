import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <pre>
 * N이 최대 50만이므로 완전탐색으로 풀이시 O(N^2)의 시간복잡도가 나오므로 시간초과가 날 수 있을거같다.
 * 따라서 스택을 이용하여 문제를 풀어보았다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 첫째줄, 탑의 수
		int[] arr = new int[n]; // 탑들의 높이를 저장할 배열 선언 및 초기화
		int[] result = new int[n]; // 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 저장할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 둘째줄, N개의 탑들의 높이가 한칸간격으로 주어짐
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 탑들의 높이 저장
		}
		
		Stack<int[]> s = new Stack<>(); // 스택 선언
		s.push(new int[] {987654321, 0}); // 탑의높이가 최대 1억이므로 무한대의 높이를 가진 0번탑이 있다고 가정하고 스택에 삽입해놓음
		
		for (int i = 0; i < n; i++) { // 1번탑부터 n번탑까지 검사
			while (s.peek()[0] <= arr[i]) { // 스택의 꼭대기에 있는 높이가 i번탑의 높이보다 작거나 같을 때동안 반복
				s.pop(); // i번탑의 높이보다 작거나 같으면 스택에서 꺼내서 버림
			}
			result[i] = s.peek()[1]; // 스택 꼭대기에 있는 탑의 번호를 저장
			s.push(new int[] {arr[i], i + 1}); // 스택에 (탑의높이, 탑의번호) 형태로 푸쉬
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) { // 스트링빌더에 저장
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb); // 결과 출력
	}

}

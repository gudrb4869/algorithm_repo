import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 리프노드인경우 숫자인지체크
 * 리프노드아닌경우 왼쪽자식만있고 오른쪽자식없는경우 false리턴
 * 리프노드아닌경우 왼쪽자식이올바른값이고 오른쪽자식이올바른값이고 자기자신이 연산자인 경우
 * </pre>
 * @author 박형규
 * 메모리 19,104 KB
 * 실행시간 114 ms
 *
 */
public class Solution {

	private static int n;
	private static char[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) { // 10번 반복
			n = Integer.parseInt(br.readLine()); // 각 케이스의 트리가 갖는 정점의 총 수
			tree = new char[n + 1];
			
			for (int i = 0; i < n; i++) { // n개의 정점 정보
				st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken()); // 정점 번호
				char data = st.nextToken().charAt(0); // 값
				tree[node] = data; // 노드에 값저장
			}
			
			sb.append("#").append(t).append(" ");
			if (dfs(1)) { // 유효하면
				sb.append("1\n"); // 1
			} else { // 유효하지 않으면
				sb.append("0\n"); // 0
			}
			
		}
		System.out.print(sb);
	}

	/**
	 * 완전이진트리 탐색 메서드(왼쪽자식은없고 오른쪽자식이 있는 경우는 고려하지 않음)
	 * 리프노드인 경우 숫자인지 체크
	 * 리프노드가 아닌경우
	 * 1) 왼쪽자식만 있는 경우 - 계산 불가하므로 유효하지 않음 false 반환
	 * 2) 왼쪽,오른쪽 자식 둘다 있는 경우 - 왼쪽자식이 유효, 오른쪽 자식이 유효, 자기자신이 연산자인지 체크
	 * @param node
	 * @return
	 */
	private static boolean dfs(int node) {
		int left = node * 2;
		int right = node * 2  + 1;
		
		boolean isNumber = tree[node] >= '0' && tree[node] <= '9';
		if (left > n && right > n) {
			return isNumber;
		} else if (left <= n && right <= n) {
			return !isNumber && dfs(left) && dfs(right);
		}
		return false;
	}

}
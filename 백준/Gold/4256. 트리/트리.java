import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 테스트케이스마다 전위순회, 중위순회가 주어졌을때 후위 순회의 결과를 구하는 문제
 * 
 * 주어진 시작점과 끝점범위내에서 전위순회의 시작점은 항상 해당 서브트리의 리프노드이다!
 * 따라서 전위순회의 리프노드에 해당하는 값을 중위순회 배열에서 찾아낸다음, 그위치를 mid라고 한다면
 * left~mid-1에 해당하는 중위순회 왼쪽구간의 개수만큼 전위순회배열 탐색을 위한 인덱스를 조절해주고,
 * mid+1~right에 해당하는 중위순회 오른쪽구간의 개수만큼 전위순회배열 탐색을 위한 인덱스를 조절해주는 방식으로 문제를 풀었다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int n, preOrder[], inOrder[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			preOrder = new int[n];
			inOrder = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				preOrder[i] = Integer.parseInt(st.nextToken()); // 전위순회
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				inOrder[i] = Integer.parseInt(st.nextToken()); // 중위순회
			}
			
			postOrder(0, n - 1, 0, n - 1); // 후위순회 결과 구하기 메서드
			sb.append("\n");
		}
		System.out.print(sb); // 모든 테스트 케이스 결과 출력
	}

	/**
	 * 후위순위 결과를 구하는 메서드
	 * @param low 전위순회 배열 시작점
	 * @param high 전위순회 배열 끝점
	 * @param left 중위순회 배열 시작점
	 * @param right 중위순회 배열 끝점
	 */
	private static void postOrder(int low, int high, int left, int right) {
		
		if (low > high || left > right) { // 인덱스 범위 벗어난 경우 리턴
			return;
		}
		
		if (low < high && left < right) { // 구간길이가 2이상인경우
			int mid = -1;
			for (int i = left; i <= right; i++) {
				if (inOrder[i] == preOrder[low]) { // 전위순위의 첫값은 리프노드이므로 중위순회에서 해당 리프노드 가진값 탐색
					mid = i; // 중위순회 리프노드의 인덱스
					break;
				}
			}
			
			postOrder(low + 1, low + (mid - left), left, mid - 1); // 왼쪽서브트리
			postOrder(high - (right - mid) + 1, high, mid + 1, right); // 오른쪽서브트리
		}
		
		sb.append(preOrder[low]).append(" "); // 리프노드
	}

}
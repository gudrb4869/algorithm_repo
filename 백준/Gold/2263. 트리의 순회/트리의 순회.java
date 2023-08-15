import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1부터 n까지 노드를 갖는 이진트리의 인오더와 포스트오더가 주어졌을때
 * 프리오더를 구하는 문제
 * 
 * 풀이방법
 * 포스트오더의 가장 맨마지막번호는 항상 루트노드이다.
 * 이것을 이용해서 인오더에서 루트노드를 찾은후 좌측과 우측을 분할하여 계속 재귀적으로 수행하다보면
 * 이진트리의 수행 순서를 유추해볼 수가 있다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int n, inOrder[], postOrder[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine()); // 첫째줄 n 이진트리정점의 개수
		
		inOrder = new int[n]; // 이진 트리의 인오더
		postOrder = new int[n]; // 이진 트리의 포스트오더
		
		st = new StringTokenizer(br.readLine()); // 둘째줄 인오더
		for (int i = 0; i < n; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()); // 셋째줄 포스트오더
		for (int i = 0; i < n; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		preOrder(0, n - 1, 0, n - 1); // 프리오더 결과
		
		System.out.println(sb);
	}

	private static void preOrder(int low, int high, int left, int right) {
		
		if (low > high || left > right) {
			return;
		}
		
		sb.append(postOrder[right]).append(" "); // left~right 범위에서 right에있는 노드는 해당 서브트리에서 루트이다.
		
		int mid = -1;
		for (int i = low; i <= high; i++) {
			if (inOrder[i] == postOrder[right]) { // 인오더에서 루트노드의 인덱스를 찾음
				mid = i;
				break;
			}
		}
		preOrder(low, mid - 1, left, left + (mid - low) - 1); // 인오더에서는 루트노드기준 왼쪽, 왼쪽개수만큼 포스트오더 인덱스조정
		preOrder(mid + 1, high, right - (high - mid), right - 1); // 인오더에서는 루트노드기준 오른쪽, 오른쪽개수만큼 포스트오더 인덱스조정
	}

}
/*
8
4 2 10 5 1 6 3 7
4 10 5 2 6 7 3 1

1 2 4 5 10 3 6 7
 */
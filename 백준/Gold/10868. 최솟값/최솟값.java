import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 세그먼트 트리
 * 2023-12-13(수)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, H, treeSize, tree[], A[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정수의 개수 (1 ~ 100000)
		M = Integer.parseInt(st.nextToken()); // 쌍의 개수 (1 ~ 100000)
		
		H = (int) (Math.ceil(Math.log(N) / Math.log(2))); // 세그먼트 트리의 높이
		treeSize = 1 << (H + 1); // 트리에 필요한 노드의 갯수
		tree = new int[treeSize]; // 세그먼트 트리 배열 초기화
		
		A = new int[N]; // 정수를 저장할 배열
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		build(1, 0, N - 1); // 세그먼트 트리 생성
		
		for (int i = 0; i < M; i++) { // M개의 쌍
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 첫번째부터 시작하므로 1빼서 A의 인덱스와 맞춰줬음
			int b = Integer.parseInt(st.nextToken()) - 1; // 위와 동일한 이유로 1빼줬음
			int min = query(1, 0, N - 1, a, b); // a,b구간 내에서 최솟값 찾음
			sb.append(min).append("\n"); // 출력 결과 저장
		}
		
		System.out.print(sb); // M개의 입력 결과에 대한 출력 한번에  출력
	}

	// 세그먼트 트리에서 구간 내 최솟값을 찾는 메서드
	// 1. 탐색구간이 구간 밖을 벗어난 경우 무한대(1234567890) 리턴
	// 2. 탐색구간이 구간 내에 속한 경우 해당 node번호에 위치한 세그먼트트리값 리턴
	// 3. 그외의 경우 왼쪽서브트리와 오른쪽서브트리의 결과 중 최솟값 리턴
	static int query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) { // 1.
			return 1234567890;
		}
		
		if (left <= start && end <= right) { // 2.
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		int leftChild = query(node * 2, start, mid, left, right);
		int rightChild = query(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.min(leftChild, rightChild); // 3.
	}

	// 세그먼트 트리를 만드는 작업
	// 1. 구간 시작과 끝이 같은 경우(리프노드) 해당 노드번호에 해당하는 곳에 정수 저장
	// 2. 그외의 경우 왼쪽 자식과 오른쪽 자식 중 최솟값 저장
	static void build(int node, int start, int end) {
		if (start == end) { // 1.
			tree[node] = A[start];
			return;
		}
		
		int mid = (start + end) / 2;
		build(node * 2, start, mid);
		build(node * 2 + 1, mid + 1, end);
		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]); // 2.
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 1000000007;
	static int N, M, K, H, treeSize; // 수의 개수, 변경발생횟수, 구간곱계산횟수, 세그먼트트리높이, 세그먼트트리노드개수
	static long A[], tree[]; // 수저장배열, 세그먼트트리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수의 개수 (1 ~ 1000000)
		M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수 (1 ~ 10000)
		K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수 (1 ~ 10000)
		
		H = (int) (Math.ceil(Math.log(N) / Math.log(2))); // 세그먼트트리 높이
		treeSize = 1 << (H + 1); // 세그먼트트리에 필요한 노드갯수
		tree = new long[treeSize];
		
		A = new long[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(br.readLine());
		}
		
		build(1, 0, N - 1);
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()); // 1인 경우 b번째 수 c로 전환, 2인 경우 b부터 c까지 곱 계산
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == 1) {
				update(1, 0, N - 1, b - 1, c);
			} else if (a == 2) {
				sb.append(query(1, 0, N - 1, b - 1, c - 1)).append("\n");
			}
		}
		
		System.out.print(sb);
	}

	// 세그먼트 트리 변경 작업 메서드
	// index번째 배열 A를 val로 바꿈과 동시에 세그먼트트리에서 index가 포함된 구간을 담당하는 노드값도 갱신
	static void update(int node, int start, int end, int index, int val) {
		
		if (start == end) { // 리프노드에 도달한 경우
			tree[node] = val; // 리프노드 값을 val로 변경
			return;
		}
		
		int mid = (start + end) / 2;
		
		if (start <= index && index <= mid) { // index가 왼쪽서브트리내에 포함된 경우
			update(node * 2, start, mid, index, val);
		} else { // index가 오른쪽서브트리내에 포함된 경우
			update(node * 2 + 1, mid + 1, end, index, val);
		}
		
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD; // 해당 node의 세그먼트트리값을 왼쪽자식과 오른쪽자식을 곱한후 1000000007로 나눈 나머지값으로 변경
	}

	// 세그먼트 트리 구간 탐색 메서드
	// 현재구간이(start,end) 탐색구간(left, right)를 벗어난 경우 1 리턴
	// 현재구간이(start,end) 탐색구간(left, right)내에 속한 경우 해당 node에 속하는 세그먼트트리값 리턴
	// 그외의 경우 왼쪽 자식과 오른쪽 자식 결과값을 곱한뒤 1000000007로 나눈 나머지값 리턴
	static long query(int node, int start, int end, int left, int right) {
		
		if (end < left || right < start) {
			return 1;
		}
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		long leftChild = query(node * 2, start, mid, left, right);
		long rightChild = query(node * 2 + 1, mid + 1, end, left, right);
		
		return (leftChild * rightChild) % MOD;
	}

	// 세그먼트 트리 생성 작업 메서드
	static void build(int node, int start, int end) {
		if (start == end) { // 리프노드 도달한 경우
			tree[node] = A[start]; // 리프노드에 해당 노드번호에 해당하는 값(A[start]) 저장
			return;
		}
		
		int mid = (start + end) / 2;
		build(node * 2, start, mid);
		build(node * 2 + 1, mid + 1, end);
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD; // 왼쪽 자식과 오른쪽 자식을 곱하고 나머지 1000000007로 나눈 나머지값 저장
	}

}
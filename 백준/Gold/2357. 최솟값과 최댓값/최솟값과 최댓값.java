import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 세그먼트 트리
 * 최솟값을 저장할 세그먼트트리와 최댓값을 저장할 세그먼트 트리 두개를 만들어서 문제를 해결했다.
 * 2023-12-12(화)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, H, treeSize, treeMin[], treeMax[], A[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정수의 개수(1 ~ 100000)
		M = Integer.parseInt(st.nextToken()); // 쌍의 개수(1 ~ 100000)
		
		H = (int) (Math.ceil(Math.log(N) / Math.log(2))); // h = ceil(log2(N))
		treeSize = 1 << (H + 1); // treeSzie = 2^(h+1)
		treeMin = new int[treeSize]; // 세그먼트트리-최솟값
		treeMax = new int[treeSize]; // 세그먼트트리-최댓값
		
		A = new int[N];
		
		for (int i = 0; i < N; i++) { // N개의 줄
			A[i] = Integer.parseInt(br.readLine()); // N개의 정수를 A배열에 차례대로 저장(1이상 10억이하)
		}
		
		build(1, 0, N - 1);
		
		for (int i = 0; i < M; i++) { // M개의 줄
			st = new StringTokenizer(br.readLine()); // a, b의 쌍
			int a = Integer.parseInt(st.nextToken()) - 1; // 구간 시작 (a번째는 입력순서로 첫번째부터 시작하므로 A인덱스와 맞추기 위해 0을 빼줬음)
			int b = Integer.parseInt(st.nextToken()) - 1; // 구간 끝(위와 동일한이유로 1빼줌)
			int min = queryMin(1, 0, N - 1, a, b); // 구간 내 최솟값
			int max = queryMax(1, 0, N - 1, a, b); // 구간 내 최댓값
			
			sb.append(min).append(" ").append(max).append("\n");// 최솟값, 최댓값 순서로 출력
		}
		
		System.out.print(sb);
	}

	// 세그먼트 트리에서 구간내 최솟값을 가져오는 메서드
	// 트리를 순회
	// 1. 노드가 나타내는 범위가 지정된 범위 밖에 있다면 0
	// 2. 노드가 나타내는 범위가 지정된 범위 내에 있다면 값을 반환
	// 3. 노드가 나타내는 범위가 지정된 범위 일부만 포함한다면 왼쪽 자식과 오른쪽 자식의 최솟값을 반환
	static int queryMin(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 1234567890;
		}
		
		if (left <= start && end <= right) {
			return treeMin[node];
		}
		
		int mid = (start + end) / 2;
		int leftChild = queryMin(node * 2, start, mid, left, right);
		int rightChild = queryMin(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.min(leftChild, rightChild);
	}
	
	// 세그먼트 트리에서 구간내 최댓값을 가져오는 메서드
	// 트리를 순회
	// 1. 노드가 나타내는 범위가 지정된 범위 밖에 있다면 0
	// 2. 노드가 나타내는 범위가 지정된 범위 내에 있다면 값을 반환
	// 3. 노드가 나타내는 범위가 지정된 범위 일부만 포함한다면 왼쪽 자식과 오른쪽 자식의 최댓값을 반환
	static int queryMax(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			return treeMax[node];
		}
		
		int mid = (start + end) / 2;
		int leftChild = queryMax(node * 2, start, mid, left, right);
		int rightChild = queryMax(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.max(leftChild, rightChild);
	}

	// 세그먼트 트리를 만드는 작업
	// start, end: 배열 A에 대한 범위
	static void build(int node, int start, int end) {
		if (start == end) {
			// 리프노드라면 원소를 저장
			treeMin[node] = A[start]; // 최솟값
			treeMax[node] = A[start]; // 최댓값
			return;
		}
		
		int mid = (start + end) / 2;
		build(node * 2, start, mid);
		build(node * 2 + 1, mid + 1, end);
		treeMin[node] = Math.min(treeMin[node * 2], treeMin[node * 2 + 1]); // [start, end] 구간의 최솟값 저장
		treeMax[node] = Math.max(treeMax[node * 2], treeMax[node * 2 + 1]); // [start, end] 구간의 최댓값 저장
	}
}
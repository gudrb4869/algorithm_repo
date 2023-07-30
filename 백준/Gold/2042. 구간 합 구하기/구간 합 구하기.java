import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수의 개수 N이 최대 1,000,000이고, 수의 변경이 일어나는 횟수 M이 최대 10,000이고, 구간의 합을 구하는 횟수 K가 최대 10,000이므로
 * 구간합 배열을 이용해 풀게 될경우 시간초가가 나게 된다.
 * 따라서 문제를 풀기 위해 세그먼트 트리를 이용해 로그시간복잡도로 문제를 통과할 수 있다. 
 * @author 박형규
 *
 */
public class Main {

	private static long[] nums;
	private static long[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		
		// 트리의 높이 h = Math.ceil(log2(n))
		// 세그먼트 트리 초기화, 세그먼트 트리를 만드는데 필요한 배열의 크기 2^(h + 1) -1
		// n이 최대 1,000,000이므로 h의 최대값은 약20
		// 필요한 배열의 크기도 약 2^21정도 이므로 약 2,000,000 좀 넘을 것이다.
		// 따라서 배열의 크기를 여유공간 있게 2,500,000으로 설정
		tree = new long[2500000];
		
		nums = new long[n];  // 입력으로 주어지는 모든 수는 64비트이므로 Long으로 받음
		for (int i = 0; i < n; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		initTree(1, 0, n - 1); // 세그먼트 트리 초기화
		
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()); // 1인경우 b번째수를 c로 바꿈, 2인경우 b번째수부터 c번째 수까지의 합을 구하여 출력
			int b = Integer.parseInt(st.nextToken()); // 1이상 N이하의 범위
			long c = Long.parseLong(st.nextToken()); // a가 1인경우엔 바꿀 수, a가 2인경우엔 b이상 N이하
			
			if (a == 1) {
				b -= 1;
				long diff = c - nums[b];
				nums[b] = c;
				updateTree(1, 0, n - 1, b, diff);
			}
			else if (a == 2) {
				sb.append(subSum(1, 0, n - 1, b - 1, (int)c - 1) + "\n");
			}
		}
		System.out.print(sb);
	}

	/**
	 * 구간합 구하는 메서드
	 * @param node 현재노드번호
	 * @param start node가 담당하는 시작구간
	 * @param end node가 담당하는 끝구간
	 * @param left 합을 구해야하는 구간 시작
	 * @param right 합을 구해야하는 구간 끝
	 * @return
	 */
	private static long subSum(int node, int start, int end, int left, int right) {
		
		// 겹치는 구간이 없기 때문에 탐색 중단
		if (left > end || right < start) {
			return 0;
		}
		
		// 구해야하는 합의 범위는 [left, right]이지만, [start, end]는 그 범위에 모두 포함되고
		// 그 node의 자식도 모두 포함되기 때문에 더 이상 호출을 하는 것은 비효율적
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		// 왼쪽 자식과 오른쪽 자식을 루트로하는 트리에서 다시 탐색을 시작함
		// node의 왼쪽 자식은 node*2, 오른쪽 자식은 node*2 + 1이 됨
		// node가 담당하는 구간이 [start, end]라면 왼쪽 자식은 [start, (start + end) / 2], 오른쪽 자식은 [(start + end) / 2 + 1, end]를 담당함
		return subSum(node * 2, start, (start + end) / 2, left, right) + subSum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	/**
	 * 세그먼트 트리 변경 메서드
	 * @param node 현재 노드번호
	 * @param start 서브트리 시작노드번호
	 * @param end 서브트리 끝노드번호
	 * @param index 값 바꿀 노드번호
	 * @param diff 인덱스 노드번호에 해당하는 값의 차이
	 */
	private static void updateTree(int node, int start, int end, int index, long diff) {
		
		if (index >= start && index <= end) {
			tree[node] += diff;
			
			// 리프노드가 아닌 경우에는 자식도 변경해줘야 하기 때문에 검사함
			if (start != end) {
				updateTree(node * 2, start, (start + end) / 2, index, diff);
				updateTree(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
			}
		}
	}

	/**
	 * 세그먼드 트리 초기화 메서드
	 * @param node 현재 노드번호
	 * @param start 현재서브트리의 시작범위
	 * @param end 현재서브트리의 끝범위
	 * @return 현재노드의 값
	 */
	private static long initTree(int node, int start, int end) {

		if (start == end) {
			// node가 리프노드인경우 배열의 원소값을 반환
			// node가 리프노드인경우 리프노드는 배열의 그 원소를 가져야 하기때문에 tree[node] = nums[start]로 설정
			tree[node] = nums[start];
		} else {
			// 재귀함수를 이용해 왼쪽자식과 오른쪽자식 트리를 만들고 합을 저장
			tree[node] = initTree(node * 2, start, (start + end) / 2) + initTree(node * 2 + 1, (start + end) / 2 + 1, end);
		}
		return tree[node];
	}

}

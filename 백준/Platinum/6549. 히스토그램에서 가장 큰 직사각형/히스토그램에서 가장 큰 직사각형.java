import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 일단 이 문제는 스택이나 세그먼트트리로 풀수있는데 세그먼트 트리로 한번 풀어보려고 한다.
 * 트리의 노드번호와 히스토그램의 직사각형 번호를 일치시키기위해 인덱스는 1부터 시작하려고 한다.
 * 세그먼트트리에서 리프노드에는 노드번호 저장
 * 부모노드는 왼쪽자식노드 값에 위치한 직사각형의 높이와 오른쪽자식노드 값에 위치한 직사각형의 높이를 비교하여
 * 최소 높이를 가진 자식노드의 값을 저장
 * 그러고 넓이를 구하는 메서드에서는 세그먼트트리에서 최소 직사각형 높이에 해당하는 위치를 기준으로
 * 왼쪽구역과 오른쪽구역으로 나누고 그중에서 최대 직사각형넓이인 것을 선택하여 문제를 해결한다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int n; // 직사각형의 수
	static int[] histogram; // 히스토그램에 있는 직사각형의 높이를 저장할 배열
	static int[] tree = new int[300000]; // 세그먼트 트리. 루트노드번호는 1부터 시작이고 n이 최대10만이므로 여유공간있게 30만개로 크기 초기화
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder(); 
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 직사각형의 수
			
			if (n == 0) { // 0이 입력되면 종료
				break;
			}
			
			histogram = new int[n + 1]; // 세그먼트트리도 인덱스1부터시작하므로 1부터 n까지 인덱스쓰기위해 n+1크기로 초기화
			
			for (int i = 1; i <= n; i++) {
				histogram[i] = Integer.parseInt(st.nextToken()); // 히스토그램 직사각형의 높이 저장
			}
			
			initTree(1, n, 1); // 세그먼트 트리 초기화
			long answer = getMaxSquareArea(1, n); // 현재범위내에 있는 히스토그램에서 직사각형의최대넓이 구함
			sb.append(answer).append("\n"); // 스트링빌더에 저장
		}
		System.out.print(sb); // 출력
		
	}

	/**
	 * 현재구간의 히스토그램에서 직사각형의 최대넓이를 구하는 메서드
	 * @param left 히스토그램 시작구간
	 * @param right 히스토그램 끝구간
	 * @return 현재구간내의 히스토그램에서 직사각형의 최대넓이
	 */
	private static long getMaxSquareArea(int left, int right) {
		long area = 0;
		
		int minIndex = findMinIndex(left, right, 1, 1, n); // 현재히스토그램구간에서 가장작은높이를 가지고있는 인덱스를 구함
		area = (long)(right - left + 1) * histogram[minIndex]; // 너비 * 히스토그램내에서 직사각형최소높이
		
		if (left <= minIndex - 1) { // minIndex 기준으로 왼쪽구간에 히스토그램이 존재?
			area = Math.max(area, getMaxSquareArea(left, minIndex - 1)); // 최대넓이로 갱신
		}
		if (minIndex + 1 <= right) { // minIndex 기준으로 오른쪽구간에 히스토그램이 존재?
			area = Math.max(area, getMaxSquareArea(minIndex + 1, right)); // 최대넓이로 갱신
		}
		return area; // 최대넓이 반환
	}

	/**
	 * 현재 탐색하고자하는 구간내에서 최소높이가 위치한 인덱스를 반환하는 메서드
	 * @param left 현재탐색하고자하는 시작구간
	 * @param right 현재탐색하고자하는 끝구간
	 * @param node 노드번호
	 * @param nodeLeft 히스토그램의 시작구간
	 * @param nodeRight 히스토그램의 끝구간
	 * @return 현구간의 히스토그램에서 최소높이가 위치한 인덱스
	 */
	private static int findMinIndex(int left, int right, int node, int nodeLeft, int nodeRight) {
		
		if (nodeRight < left || right < nodeLeft) { // 탐색구간이 히스토그램을 벗어난경우 -1 반환
			return -1;
		}
		if (left <= nodeLeft && nodeRight <= right) { // 탐색구간이 히스토그램 구간내에 있는 경우
			return tree[node]; // 해당노드번호에 해당하는 값 반환
		}
		int mid = (nodeLeft + nodeRight) >>> 1; // 히스토그램 시작구간과 끝구간의 중간지점
		int leftMinIndex = findMinIndex(left, right, node << 1, nodeLeft, mid); // 중간지점기준으로 왼쪽구간의 최소인덱스
		int rightMinIndex = findMinIndex(left, right, (node << 1) + 1, mid + 1, nodeRight); // 중간지점기준 오른쪽구간의 최소인덱스
		if (leftMinIndex == -1) { // 왼쪽구간의 최소인덱스가 -1이면 왼쪽구간이 없다는것이므로
			return rightMinIndex; // 오른쪽구간의 최소인덱스 반환
		}
		if (rightMinIndex == -1) { // 오른쪽구간의 최소인덱스가 -1이면 오른쪽구간이 없다는것이므로
			return leftMinIndex; // 왼쪽구간의 최소인덱스 반환
		}
		// 왼쪽구간의 최소인덱스에 해당하는 직사각형의 높이와 오른쪽구간의 최소인덱스에 해당하는 직사각형의 높이를 비교하여
		// 최소높이인 곳의 인덱스를 반환
		return histogram[leftMinIndex] < histogram[rightMinIndex] ? leftMinIndex : rightMinIndex;
	}

	/**
	 * 세그먼트 트리 초기화 메서드
	 * @param left 히스토그램 시작구간
	 * @param right 히스토그램 끝구간
	 * @param node 현재 노드번호
	 */
	private static int initTree(int left, int right, int node) {
		if (left == right) { // 리프노드인 경우
			return tree[node] = left; // 리프노드에 현재 히스토그램의 인덱스 저장 
		}
		// 리프노드가 아닌경우 왼쪽서브트리와 오른쪽 서브트리를 비교하여
		// 최소높이인 인덱스로 저장
		int mid = (left + right) >> 1;
		int leftMinIndex = initTree(left, mid, node << 1);
		int rightMinIndex = initTree(mid + 1, right, (node << 1) + 1);
		return tree[node] = histogram[leftMinIndex] < histogram[rightMinIndex] ? leftMinIndex : rightMinIndex;
	}

}

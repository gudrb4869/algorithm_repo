import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이진트리와 dfs를 사용하여 문제를 풀엇다
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int[] edge; // 간선의 가중치를 저장할 배열
	private static int[] tree; // 포화이진트리
	private static int k; // 포화이진트리의 높이
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine()); // 포화이진트리의 높이
		
		edge = new int[1 << (k + 1)]; // 간선의 가중치 저장할 배열
		tree = new int[1 << (k + 1)]; // 높이가 k인 포화이진트리의 노드의 개수 2^{k+1} - 1인데 노드의 번호를 1부터시작할것이므로 2^{k+1}크기로 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 2; i < 1 << (k + 1); i++) { // i/2번 노드(i의부모노드)에서 i번노드로 가는 간선의 가중치를 edge[i]에 저장함
			edge[i] = Integer.parseInt(st.nextToken());
		}
		
		initTree(1); // 트리 초기화
		updateEdge(1); // 루트에서 리프까지의 거리중 최대값으로 모든 리프들의 거리를 같게 만들기 위한 메서드 호출
		
		int sum = 0; // 간선의 가중치의 합을 저장할 변수
		for (int i = 2; i < 1 << (k + 1); i++) {
			sum += edge[i]; // 간선의 가중치 더함
		}
		System.out.println(sum); // 결과 출력
		
	}

	private static void updateEdge(int node) {
		if (node > 1) { // 루트노드가 아닐 경우에만 호출
			int val = tree[node >> 1] - edge[node >> 1] - tree[node];
			edge[node] += val;
			tree[node] += val;
		}
		
		if (node < 1 << k) { // 리프노드가 아닐 경우에만 호출
			updateEdge(node << 1);
			updateEdge((node << 1) + 1);
		}
	}

	/**
	 * 트리의 상태 초기화. 수행시 루트노드의 값은 루트에서 리프까지의 거리중 최대값이 된다.
	 * @param node
	 * @return
	 */
	private static int initTree(int node) {
		if (node >= (1 << k)) {
			return tree[node] = edge[node];
		}
		return tree[node] = edge[node] + Math.max(initTree(node << 1), initTree((node << 1) + 1));
	}

}

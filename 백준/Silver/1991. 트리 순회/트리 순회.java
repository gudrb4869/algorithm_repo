import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * 맵으로 이진트리 형태를 표현하였고, 재귀함수로 전위순회, 중위순회, 후위순회를 구현하여 문제를 해결함
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	private static Map<String, String[]> tree = new HashMap<>(); // 트리를 순회하기 위해 맵에 저장 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine()); // 이진 트리의 노드의 개수
		
		for (int i = 0; i < n; i++) { // n개의 노드에 대해 입력을 받음
			st = new StringTokenizer(br.readLine());
			String node = st.nextToken(); // 노드의 이름
			String left = st.nextToken(); // 왼쪽 자식 노드
			String right = st.nextToken(); // 오른쪽 자식노드
			tree.put(node, new String[] {left, right}); // 노드의 이름을 키값으로 하고 (왼쪽자식,오른쪽자식) 두쌍의 값을 저장하는 맵에 저장
		}
		
		preOrder("A"); // 전위 순회
		System.out.println();
		inOrder("A"); // 중위 순회
		System.out.println();
		postOrder("A"); // 후위 순회
		System.out.println();
	}

	private static void preOrder(String node) {
		if (node.equals(".")) {
			return;
		}
		System.out.print(node); // 자기자신
		preOrder(tree.get(node)[0]); // 왼쪽 서브트리 순회
		preOrder(tree.get(node)[1]); // 오른쪽 서브트리 순회
	}
	
	private static void inOrder(String node) {
		if (node.equals(".")) {
			return;
		}
		inOrder(tree.get(node)[0]); // 왼쪽 서브트리 순회
		System.out.print(node); // 자기자신
		inOrder(tree.get(node)[1]); // 오른쪽 서브트리 순회
	}
	
	private static void postOrder(String node) {
		if (node.equals(".")) {
			return;
		}
		postOrder(tree.get(node)[0]); // 왼쪽 서브트리 순회
		postOrder(tree.get(node)[1]); // 오른쪽 서브트리 순회
		System.out.print(node); // 자기자신
	}

}

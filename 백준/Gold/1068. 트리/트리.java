import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int answer;
	static List<Integer>[] node;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 트리의 노드의 개수
		
		node = new List[N]; // 노드의 자식노드번호를 저장할 리스트
		for (int i = 0; i < N; i++) {
			node[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 둘째 줄
		int root = 0;
		
		parents = new int[N];
		for (int i = 0; i < N; i++) { // 0번노드부터 N-1번노드까지 각 노드의 부모가 주어짐
			parents[i] = Integer.parseInt(st.nextToken());
			if (parents[i] == -1) {
				root = i;
				continue; // 부모가 없다면 -1(루트노드)
			}
			
			node[parents[i]].add(i); // 각 노드의 부모인덱스에 해당하는 리스트에 현재노드번호 삽입
		}
		
		int num = Integer.parseInt(br.readLine()); // 셋째 줄, 지울 노드의 번호
		
		if (parents[num] == -1) {
			System.out.println(0);
			return;
		}
		
		node[parents[num]].remove(node[parents[num]].indexOf(num));
		
		traverse(root);
		
		System.out.println(answer);
	}

	private static void traverse(int cur) {
		if (node[cur].size() == 0) {
			answer++;
		}
		for (int next : node[cur]) {
			traverse(next);
		}
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int K, N, traverse[];
	static List<Integer>[] level;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		K = Integer.parseInt(br.readLine()); // 완전 이진 트리 깊이
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = (1 << K) - 1; // 완전 이진 트리 노드 개수
		
		traverse = new int[N]; // 트리 순회 정보 저장할 배열
		level = new List[K]; // 레벨별 노드번호를 저장할 리스트
		
		for (int i = 0; i < K; i++) {
			level[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			traverse[i] = Integer.parseInt(st.nextToken());
		}
		
		inOrder(0, 0, N - 1); // 중위 순회
		
		for (int i = 0; i < K; i++) {
			for (int node : level[i]) {
				sb.append(node).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	static void inOrder(int depth, int start, int end) {
		
		if (start == end) { // 리프 노드(기저 조건)
			level[depth].add(traverse[start]);
			return;
		}
		
		int mid = (start + end) / 2; // 현재 서브트리의 루트노드
		inOrder(depth + 1, start, mid - 1); // 왼쪽 서브트리 중위 순회
		level[depth].add(traverse[mid]); // 현재 빌딩 번호 해당 레벨에 저장
		inOrder(depth + 1, mid + 1, end); // 오른쪽 서브트리 중위 순회
	}

}
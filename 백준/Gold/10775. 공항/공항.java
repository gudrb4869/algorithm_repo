import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 공항에 G개의 게이트가 있는데 게이트 각각은 1부터 G까지의 번호를 가지고 있다.
 * 공항에 P개의 비행기가 순서대로 도착할 예정인데, i번째 비행기를 1번부터 gi(1<=gi<=G)번째 게이트중 하나에 영구적으로 도킹하려함
 * 비행기가 어느 게이트에도 도킹이 불가능한 경우 공항이 폐쇄되고 어떤 비행기도 도착할 수 없음
 * 최대 몇대의 비행기가 도킹 가능한지 구하는 문제 
 * 
 * 서로소집합과 유니온파인드를 이용하여 문제를 풀 수 있을 것 같다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int parent[]; // 서로소 집합, 유니온 파인드 이용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int G = Integer.parseInt(br.readLine()); // 게이트의 수
		
		parent = new int[G + 1]; // 서로소 집합의 루트 노드를 저장할 1차원 배열 초기화
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		
		int P = Integer.parseInt(br.readLine()); // 비행기의 수
		int[] plane = new int[P]; // P개의 gi를 저장할 1차원 배열 선언
		int answer = 0; // 도킹시킬 수 있는 최대의 비행기 수
		for (int i = 0; i < P; i++) { // P개의 줄에 gi가 주어짐
			plane[i] = Integer.parseInt(br.readLine()); // plane배열의 i번째 인덱스에 gi 저장
			
		}
		
		for (int i = 0; i < P; i++) {
			int target = find(plane[i]); // target: gi의 루트노드
			if (target == 0) break; // target이 0 => i번째 비행기는 1부터 gi까지 어느 게이트에도 도킹이 불가함, 공항 폐쇄
			answer++; // 도킹 가능한 비행기수 1증가
			union(target - 1, target); // target의 루트노드를 target-1로 세팅
		}

		System.out.println(answer); // 결과 출력
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		
		parent[rootB] = rootA;
		return true;
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
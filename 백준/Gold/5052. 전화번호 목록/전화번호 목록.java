import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 트라이 자료구조 이용하여 풀이 가능
 * 전화번호 길이가 짧은 것부터 트라이에 삽입해야 일관성 여부를 검사가능하다. 따라서 전화번호 입력다받은다음 전화번호길이 오름차순으로 정렬해줌
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static class TrieNode {
		boolean word;
		Map<Character, TrieNode> children;
		
		public TrieNode() {
			word = false;
			children = new HashMap<>();
		}
	}
	
	static class Trie {
		TrieNode root;
		
		public Trie() {
			root = new TrieNode();
		}
		
		public boolean insert(char[] word) {
			TrieNode node = root;
			for (char w : word) {
				if (node.children.get(w) == null) {
					node.children.put(w, new TrieNode());
				}
				node = node.children.get(w);
				if (node.word) return false;
			}
			return node.word = true;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) { // 각 테스트 케이스마다 수행
			int N = Integer.parseInt(br.readLine()); // 첫째 줄, 전화번호의 수
			
			Trie trie = new Trie();
			boolean isPossible = true;
			char[][] word = new char[N][];
			for (int i = 0; i < N; i++) {
				word[i] = br.readLine().toCharArray(); // 전화번호
			}
			
			Arrays.sort(word, new Comparator<char[]>() {
				@Override
				public int compare(char[] a, char[] b) {
					return a.length - b.length;
				}
			});
			for (int i = 0; i < N; i++) {
				isPossible &= trie.insert(word[i]);
			}
			
			sb.append(isPossible ? "YES\n" : "NO\n");
		}
		
		System.out.print(sb);
	}

}
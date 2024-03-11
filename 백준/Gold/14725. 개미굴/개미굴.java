import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    // 트라이 노드
    static class Node {
        TreeMap<String, Node> children;

        public Node() {
            children = new TreeMap<>();
        }
    }

    // 트라이
    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }
    }

    static StringBuilder sb = new StringBuilder();

    // 트라이 생성
    static Trie trie = new Trie();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 먹이의 정보 개수 (1 <= N <= 1000)
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 로봇 개미 한마리가 보내준 먹이 정보 개수 (1 <= K <= 15)
            int K = Integer.parseInt(st.nextToken());

            // 가리키고 있는 노드. 트라이의 루트 노드
            Node cur = trie.root;

            for (int j = 0; j < K; j++) {
                String word = st.nextToken();
                // 해당 단어 없으면 노드 새로 생성
                cur.children.computeIfAbsent(word, k -> new Node());
                // 가리킬 노드 이동
                cur = cur.children.get(word);
            }
        }

        // 개미굴의 시각화된 구조 출력
        print(0, trie.root);
        System.out.print(sb);
    }

    static void print(int depth, Node cur) {

        for (String word : cur.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(word).append("\n");
            print(depth + 1, cur.children.get(word));
        }
    }
}
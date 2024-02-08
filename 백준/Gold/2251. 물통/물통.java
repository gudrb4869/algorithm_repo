import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B, C;
    static Set<Integer> resultSet = new HashSet<>();
    static Set<String> stateSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dfs(0, 0, C);

        Integer[] list = resultSet.toArray(new Integer[0]);

        Arrays.sort(list);

        for (int num : list) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);

    }

    static void dfs(int a, int b, int c) {

        String s = a + " " + b + " " + c;

        if (stateSet.contains(s)) {
            return;
        }

        stateSet.add(s);

        if (a == 0) {
            resultSet.add(c);
        }

        if (a > 0) {
            if (b < B) {
                if (a + b <= B) {
                    dfs(0, a + b, c);
                } else {
                    dfs(a - (B - b), B, c);
                }
            }
            if (c < C) {
                if (a + c <= C) {
                    dfs(0, b, a + c);
                } else {
                    dfs(a - (C - c), b, C);
                }
            }
        }

        if (b > 0) {
            if (a < A) {
                if (a + b <= A) {
                    dfs(a + b, 0, c);
                } else {
                    dfs(A, b - (A - a), c);
                }
            }
            if (c < C) {
                if (b + c <= C) {
                    dfs(a, 0, b + c);
                } else {
                    dfs(a, B - (C - c), C);
                }
            }
        }

        if (c > 0) {
            if (a < A) {
                if (a + c <= A) {
                    dfs(a + c, b, 0);
                } else {
                    dfs(A, b, c - (A - a));
                }
            }
            if (b < B) {
                if (b + c <= B) {
                    dfs(a, b + c, 0);
                } else {
                    dfs(a, B, c - (B - b));
                }
            }
        }

    }
}
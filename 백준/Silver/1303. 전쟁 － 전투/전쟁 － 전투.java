import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, count, white, blue; // 아군 위력의 합, 적군 위력의 합
    static char A[][], color;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 전쟁터 가로크기(열)
        M = Integer.parseInt(st.nextToken()); // 전쟁터 세로크기(행)

        A = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            A[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                count = 0;
                color = A[i][j];
                dfs(i, j);

                if (A[i][j] == 'W') white += count * count;
                else blue += count * count;
            }
        }

        System.out.println(white + " " + blue);
    }

    static void dfs(int r, int c) {
        count++;
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || A[nr][nc] != color) {
                continue;
            }

            dfs(nr, nc);

        }
    }
}
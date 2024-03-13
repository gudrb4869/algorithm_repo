import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 체스판의 세로 길이 (20억 이하 자연수)
        int N = Integer.parseInt(st.nextToken());
        // 체스판의 가로 길이 (20억 이하 자연수)
        int M = Integer.parseInt(st.nextToken());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        // 이동 횟수 4번보다 적은 경우
        if (M <= 4) {
            if (N >= 3) {
                System.out.println(M);
            } else {
                System.out.println(((M - 1) / 2) + 1);
            }
        } else if (M <= 6) {
            if (N >= 3) {
                System.out.println(4);
            } else {
                System.out.println(3);
            }
        } else {
            if (N >= 3) {
                System.out.println(M - 2);
            } else {
                System.out.println(4);
            }
        }
    }
}
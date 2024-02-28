import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[10];
        Arrays.fill(dp, 1);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 10; j++) {
                dp[j] += dp[j - 1];
                dp[j] %= 10007;
            }
        }

        System.out.println(dp[9]);
    }
}
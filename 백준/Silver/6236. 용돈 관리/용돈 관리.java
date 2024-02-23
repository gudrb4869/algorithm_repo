import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        int low = 1, high = 1000000000;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            low = Math.max(low, A[i]);
        }

        int answer = high;
        while (low <= high) {
            int mid = (low + high) / 2;

            int count = 1;
            int sum = A[0];
            for (int i = 1; i < N; i++) {
                if (sum + A[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum += A[i];
            }

            if (count > M) {
                low = mid + 1;
            } else {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
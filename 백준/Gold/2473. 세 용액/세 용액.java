import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);

        long[] answer = new long[3];
        long min = 3000000001L;

        for (int i = 0; i < N - 2; i++) {
            int left = i;
            int mid = i + 1;
            int right = N - 1;

            while (mid < right) {
                long sum = A[left] + A[mid] + A[right];
                long abs = Math.abs(sum);

                if (min > abs) {
                    min = abs;
                    answer[0] = A[left];
                    answer[1] = A[mid];
                    answer[2] = A[right];
                }
                if (sum == 0) {
                    break;
                } else if (sum > 0) {
                    right--;
                } else {
                    mid++;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
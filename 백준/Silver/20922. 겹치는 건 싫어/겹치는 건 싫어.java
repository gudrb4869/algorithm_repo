import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = 1;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < N) {
            if (map.getOrDefault(A[right], 0) + 1 <= K) {
                map.put(A[right], map.getOrDefault(A[right], 0) + 1);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                map.put(A[left], map.get(A[left]) - 1);
                left++;
            }
        }
        System.out.println(maxLength);
    }
}
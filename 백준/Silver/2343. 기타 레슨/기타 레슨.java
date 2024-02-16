import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 강의의 수
        int M = Integer.parseInt(st.nextToken()); // 블루레이 개수

        st = new StringTokenizer(br.readLine());

        int[] lesson = new int[N]; // 강토의 기타 길이가 담긴 배열
        int left = 0, right = 0, answer = 0;

        for (int i = 0; i < N; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, lesson[i]); // 이진탐색 왼쪽범위
            right += lesson[i]; // 이진탐색 오른쪽범위
            answer += lesson[i]; // 블루레이 최소 크기 저장할 변수
        }

        // 이진 탐색
        while (left <= right) {
            int mid = (left + right) / 2; // 현재 블루레이 크기

            int count = 1;
            int sum = lesson[0];

            for (int i = 1; i < N; i++) {
                if (sum + lesson[i] > mid) {
                    count++;
                    sum = lesson[i];
                } else {
                    sum += lesson[i];
                }
            }

            if (count > M) { // 블루레이 개수가 필요한블루레이개수보다 큰경우
                left = mid + 1; // 이진탐색 왼쪽범위 크게조정
            } else { // 블루레이 개수가 필요한블루레이개수이거나 작은경우
                answer = Math.min(answer, mid); // 블루레이 최소크기 갱신
                right = mid - 1; // 이진탐색 오른쪽범위 작게조정
            }
        }

        System.out.println(answer); // 답출력
    }
}
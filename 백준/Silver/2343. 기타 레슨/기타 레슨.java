import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 강의의 수
        int M = Integer.parseInt(st.nextToken()); // 블루레이 개수

        st = new StringTokenizer(br.readLine());

        int[] lesson = new int[N]; // 강토의 기타 길이가 담긴 배열
        int right = 0, answer = 0;

        for (int i = 0; i < N; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
            right += lesson[i]; // 이진탐색 오른쪽범위
            answer += lesson[i]; // 블루레이 최소 크기 저장할 변수
        }

        int left = lesson[0]; // 이진탐색 왼쪽범위

        // 이진 탐색
        while (left <= right) {
            int mid = (left + right) / 2; // 현재 블루레이 크기

            boolean flag = true; // 현재 블루레이 크기로 모든 강의를 담을수있는지 체크할 변수
            Stack<Integer> s = new Stack<>();
            s.push(0);

            for (int i = 0; i < N; i++) {
                if (lesson[i] > mid) { // 강의길이가 블루레이크기보다 큰 경우
                    flag = false;
                    break;
                }

                if (s.peek() + lesson[i] > mid) { // 현재강의를 새로운 블루레이에 배정해야하는경우
                    s.push(lesson[i]);
                } else { // 현재강의를 기존 블루레이에 배정가능한 경우
                    s.push(s.pop() + lesson[i]);
                }
            }

            if (!flag) { // 블루레이 이진탐색 왼쪽범위를 크게 조정
                left = mid + 1;
                continue;
            }

            int count = s.size(); // 블루레이 개수

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
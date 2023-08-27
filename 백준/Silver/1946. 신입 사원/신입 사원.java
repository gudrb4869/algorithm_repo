import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 서류성적 순위기준 오름차순 정렬하고 면접성적 순위가 현재 순위보다 낮을경우 인원수 1증가시키고 현재순위를 갱신해주는 방식으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 지원자의 숫자
			
			int[][] arr = new int[N][2];
			for (int i = 0; i < N; i++) { // 각각의 지원자의 결과
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken()); // 서류심사 성적
				arr[i][1] = Integer.parseInt(st.nextToken()); // 면접 성적
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return a[0] - b[0]; // 서류 성적 오름차순으로 정렬
				}
			});
			
			int answer = 1; // 선발할 수 있는 신입사원의 최대 인원 수
			int current = arr[0][1];
			for (int i = 0; i < N; i++) {
				if (arr[i][1] < current) {
					answer++;
					current = arr[i][1];
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * n개의 스위치가 있음. 스위치는 0과 1 두가지의 상태를 가짐
 * 남학생은 자신이 받은 카드의 수의 배수에 위치한 스위치의 상태를 반전시킨다.
 * 여학생은 자신이 받은 번호의 스위치를 중심으로 좌우대칭이면서 가장 큰 구간의 스위치를 모두 바꾼다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 스위치 개수
		boolean[] arr = new boolean[n + 1]; // 스위치 상태 저장할 배열. 1번부터 n번까지 쓰기위해 n + 1크기로 설정
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) {
				arr[i] = true; // 입력값이 1이면 true로 세팅
			}
		}
		
		int m = Integer.parseInt(br.readLine()); // 학생수
		for (int k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 학생의 성별
			int b = Integer.parseInt(st.nextToken()); // 학생의 받은수
			
			if (a == 1) { // 남학생이면
				for (int i = b; i <= n; i += b) {
					arr[i] = !arr[i]; // 받은수의 배수에 있는 스위치의 상태를 반전시킴
				}
			} else if (a == 2) { // 여학생이면
				arr[b] = !arr[b]; // 받은수에 있는 스위치를 반전시키고
				
				for (int i = 1; i <= Math.min(n - b, b - 1); i++) { // 받은수에 잇는 스위치를 중심으로 좌우가 대칭이면서 상태가 동일할경우 값반전
					if (arr[b - i] != arr[b + i]) { // 좌우대칭인곳의 상태가 동일하지 않은경우 중지
						break;
					}
					arr[b - i] = !arr[b - i];
					arr[b + i] = !arr[b + i];
				}
			}
		}
		
		// 출력은 한줄에 최대 스위치 20개씩 출력
		for (int i = 1; i <= n; i++) {
			sb.append((arr[i] ? 1 : 0) + " "); // true면 1 false면 0으로 받고 한칸띄움
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb); // 출력
	}

}

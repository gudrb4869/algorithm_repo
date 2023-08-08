import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 반시계로 r번 회전시킨 결과물 출력
 * </pre>
 * @author 박형규
 * 메모리 32,428 KB
 * 시간 632 ms
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken()); // 배열의 행
		int m = Integer.parseInt(st.nextToken()); // 배열의 열
		int r = Integer.parseInt(st.nextToken()); // 회전의 수
		
		int[][] arr = new int[n][m]; // n * m 크기의 배열 생성
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 원소의 값 저장
			}
		}
		
		for (int k = 0; k < r; k++) { // r번 회전
			int minR = 0, maxR = n - 1; // 초기값 세팅
			int minC = 0, maxC = m - 1; // 초기값 세팅
			while (minR < maxR && minC < maxC) { // 반복 조건
				int temp = arr[minR][minC]; // 반시계로 돌려야하는데 이럴경우 한개의 값이 덮어씌워지므로 임시변수에 값 저장해놓음
				for (int j = minC; j < maxC; j++) { // 상단
					arr[minR][j] = arr[minR][j + 1];
				}
				for (int i = minR; i < maxR; i++) { // 우측
					arr[i][maxC] = arr[i + 1][maxC];
				}
				for (int j = maxC; j > minC; j--) { // 하단
					arr[maxR][j] = arr[maxR][j - 1];
				}
				for (int i = maxR; i > minR; i--) { // 좌측
					arr[i][minC] = arr[i - 1][minC];
				}
				arr[minR + 1][minC] = temp;
				
				// 다음 둘레를 회전시키기 위해 시작/끝값 조정
				minR++;
				maxR--;
				minC++;
				maxC--;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}

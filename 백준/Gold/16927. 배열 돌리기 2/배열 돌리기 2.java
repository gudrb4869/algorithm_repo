import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 반시계로 r번 회전시킨 결과물 출력해야 하는데
 * r이 최대 10억이므로 BJ_16926_배열돌리기1 문제마냥 한칸씩 반시계로 땡기는걸 10억번 반복하면 시간초과가 날수 있다.
 * 따라서 각 단계별 둘레길이로 회전 횟수를 나눈 나머지만큼만 회전시키면 연산 횟수를 크게 단축 시킬 수 있을것 같다.
 * 각 단계별 값들을 왼쪽 위부터 시작하여 시계방향으로 큐에 삽입하고, 회전횟수%둘레 크기만큼 꺼내 뒤에붙인다음
 * 단순 구현 풀던거마냥 큐에서 값꺼내가지고 값 변경하면 될거같다.
 * </pre>
 * @author 박형규
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
		
		int minR = 0, maxR = n - 1; // 초기값 세팅
		int minC = 0, maxC = m - 1; // 초기값 세팅
		while (minR < maxR && minC < maxC) { // 반복 조건
			int length = 2 * ((maxR - minR) + (maxC - minC)); // 단계별 둘레길이
			int rotation = r % length;
			
			Queue<Integer> q = new ArrayDeque<>();
			for (int j = minC; j < maxC; j++) { // 상단
				q.offer(arr[minR][j]);
			}
			for (int i = minR; i < maxR; i++) { // 우측
				q.offer(arr[i][maxC]);
			}
			for (int j = maxC; j > minC; j--) { // 하단
				q.offer(arr[maxR][j]);
			}
			for (int i = maxR; i > minR; i--) { // 좌측
				q.offer(arr[i][minC]);
			}
			
			for (int i = 0; i < rotation; i++) {
				q.offer(q.poll());
			}
			
			for (int j = minC; j < maxC; j++) { // 상단
				arr[minR][j] = q.poll();
			}
			for (int i = minR; i < maxR; i++) { // 우측
				arr[i][maxC] = q.poll();
			}
			for (int j = maxC; j > minC; j--) { // 하단
				arr[maxR][j] = q.poll();
			}
			for (int i = maxR; i > minR; i--) { // 좌측
				arr[i][minC] = q.poll();
			}
			
			// 다음 둘레를 회전시키기 위해 시작/끝값 조정
			minR++;
			maxR--;
			minC++;
			maxC--;
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

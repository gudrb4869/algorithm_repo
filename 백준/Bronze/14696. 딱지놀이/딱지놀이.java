import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 만약 두 딱지의 별의 개수가 다르다면, 별(4)이 많은 쪽의 딱지가 이긴다.
 * 별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미(3)가 많은 쪽의 딱지가 이긴다.
 * 별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모(2)가 많은 쪽의 딱지가 이긴다.
 * 별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모(1)가 많은 쪽의 딱지가 이긴다.
 * 별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.
 * 
 * 별, 동그라미, 네모, 세모를 각각 숫자 4, 3, 2, 1로 표현
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 라운드 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int aCnt = Integer.parseInt(st.nextToken()); // A가 낸 카드의 개수
			int[] A = new int[5];
			for (int j = 0; j < aCnt; j++) {
				A[Integer.parseInt(st.nextToken())]++;
			}
			
			st = new StringTokenizer(br.readLine());
			int bCnt = Integer.parseInt(st.nextToken()); // B가 낸 카드의 개수
			int[] B = new int[5];
			for (int j = 0; j < bCnt; j++) {
				B[Integer.parseInt(st.nextToken())]++;
			}
			
			if (A[4] > B[4]) {
				sb.append("A\n");
			} else if (A[4] < B[4]) {
				sb.append("B\n");
			} else {
				if (A[3] > B[3]) {
					sb.append("A\n");
				} else if (A[3] < B[3]) {
					sb.append("B\n");
				} else {
					if (A[2] > B[2]) {
						sb.append("A\n");
					} else if (A[2] < B[2]) {
						sb.append("B\n");
					} else {
						if (A[1] > B[1]) {
							sb.append("A\n");
						} else if (A[1] < B[1]) {
							sb.append("B\n");
						} else {
							sb.append("D\n");
						}
					}
				}
			}
		}
		System.out.print(sb);
	}

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 스네이크버드는 자기자신보다 작거나 같은 높이에 있는 과일을 먹을수 있으므로
 * 과일의 높이를 오름차순 정렬하여 낮은거부터 하나씩 차례차례 먹으면 스네이크버드의 최대 길이가 될거같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 과일의 개수
		int L = Integer.parseInt(st.nextToken()); // 스네이크버드의 초기 길이
		
		st = new StringTokenizer(br.readLine());
		int[] fruit = new int[N]; // 과일의 개수만큼 배열 생성
		
		for (int i = 0; i < N; i++) {
			fruit[i] = Integer.parseInt(st.nextToken()); // 과일의 높이
		}
		Arrays.sort(fruit); // 과일높이 오름차순 정렬
		
		for (int i = 0; i < N; i++) {
			if (fruit[i] <= L) { // 과일의 높이가 스네이크버드의 길이보다 작거나 같으면
				L++; // 스네이크버드 길이 1증가
			}
		}
		System.out.println(L); // 결과 출력
	}

}

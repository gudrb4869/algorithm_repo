import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬 및 그리디 알고리즘 사용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		int N = Integer.parseInt(st.nextToken()); // 유치원에 있는 원생의 수 (1 ~ 300000)
		int K = Integer.parseInt(st.nextToken()); // 조의 개수 (1 ~ N)
		
		st = new StringTokenizer(br.readLine()); // 둘째 줄, 원생들의 키를 나타내는 N개의 자연수(키순으로 주어짐)
		
		int[] height = new int[N]; // 원생들의 키를 저장할 배열
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken()); // 원생들의 키 저장
		}
		
		int[] diff = new int[N - 1]; // i번째 원생과 i+1번째 원생의 키차이를 저장할 배열
		for (int i = 0; i < N - 1; i++) {
			diff[i] = height[i + 1] - height[i]; // 키 차이 저장
		}
		
		Arrays.sort(diff); // 키차이 오름차순 정렬
		int answer = 0;
		for (int i = 0; i < N - K; i++) { // 원생수-조의개수 => 더해야할 키차이의 횟수
			answer += diff[i];
		}
		System.out.println(answer); // 결과 출력
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그리디 알고리즘으로 풀음
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 저울추의 개수 (1~1000)

		int[] arr = new int[N]; // 저울추의 무게를 저장할 1차원 배열 생성
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 저울추의 무게 저장
		}
		Arrays.sort(arr); // 저울추의 무게 오름차순 정렬
		int weight = 1; // 현재 측정하려고 하는 무게
		for (int i = 0; i < N; i++) { // 저울추를 순서대로 하나씩 검사
			if (arr[i] > weight) { // 저울추의 무게가 측정하려고하는 무게보다 큰경우 
				break; // 중단
			}
			weight += arr[i]; // 측정하려고 하는 무게에 저울추 누적
		}
		System.out.println(weight);
	}

}
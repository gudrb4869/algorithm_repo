import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬 및 그리디 알고리즘 사용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 센서의 개수 (1 ~ 10000)
		int K = Integer.parseInt(br.readLine()); // 둘째 줄, 집중국의 개수 (1 ~ 1000)
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 셋째 줄, N개의 센서의 좌표
		
		Set<Integer> s = new HashSet<>(); // 센서의 좌표를 저장할 집합
		for (int i = 0; i < N; i++) {
			s.add(Integer.parseInt(st.nextToken())); // 좌표를 집합에 저장
		}
		
		Integer[] sensor = s.toArray(new Integer[s.size()]); // 집합 -> 배열로 전환
		Arrays.sort(sensor); // 좌표 오름차순 정렬
		
		int[] diff = new int[sensor.length - 1]; // 중복제거한 센서의 개수 - 1 크기의 배열 선언
		
		for (int i = 0; i < diff.length; i++) {
			diff[i] = sensor[i + 1] - sensor[i]; // 두 인접 센서의 거리 저장
		}
		
		Arrays.sort(diff); // 거리차이 오름차순 정렬
		
		int answer = 0; // K개의 집중국의 수신 가능 영역의 길이의 합의 최솟값
		for (int i = 0; i < sensor.length - K; i++) {
			answer += diff[i];
		}
		System.out.println(answer); // 결과 출력
	}

}
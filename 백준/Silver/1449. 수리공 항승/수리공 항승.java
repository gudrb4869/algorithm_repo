import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 물이 새는 곳은 신기하게도 가장 왼쪽에서 정수만큼 떨어진 거리만 물이 샘
 * 길이가 L인 테이프를 무한개 갖고있음
 * 물을 막을 때, 적어도 그 위치의 좌우 0.5만큼 간격을 줘야함
 * 테이프를 자를 수 없고, 테이프를 겹쳐서 붙이는 것도 가능하다
 * 
 * 위치 오름차순 정렬하고, 그리디 알고리즘으로 풀음
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위 분리
		
		int N = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수
		int L = Integer.parseInt(st.nextToken()); // 테이프의 길이
		
		int[] arr = new int[N]; // 물이 새는 곳의 위치를 저장할 1차원 배열 생성
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 물이 새는 곳의 위치 오름차순 정렬
		
		int answer = 1; // 항승이가 필요한 테이프의 개수
		int start = arr[0]; // 항승이가 테이프를 붙이기 시작한 위치
		for (int i = 1; i < N; i++) {
			if (arr[i] - start >= L) { // 테이프의 길이가 모자란 경우
				start = arr[i]; // 시작위치 갱신
				answer++; // 사용한 테이프 개수 1증가
			}
		}
		System.out.println(answer); // 결과 출력
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*M크기의 집터 내의 땅의 높이를 일정하게 바꿔야함
 * 
 * 두종류의 작업이 가능하다.
 * 1번작업: 좌표(i,j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다. => 2초 소요
 * 2번작업: 인벤토리에서 블록 하나를 꺼내어 좌표(i,j)의 가장 위에 있는 블록 위에 놓는다. => 1초 소요
 * 
 * 작업을 시작할때 인벤토리에는 B개의 블록이 들어있다.
 * 
 * 땅 고르기 작업에 걸리는 시간과 그 경우 땅의 높이를 구하는 문제
 * 닾이 여러개 있다면 그 중에서 땅의 높이가 가장 높은 것을 출력
 * 
 * ### 아이디어 ###
 * 일단 최소높이로 땅을 고르게 한다음
 * 인벤토리에 있는 블록의 개수가 N*M보다 크거나 같을동안 블록을 모든칸에 1개씩 쌓아보면서 소요시간이 작거나 같은경우 소요시간과 높이를 갱신해주는 방식으로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 집터의 세로길이
		int M = Integer.parseInt(st.nextToken()); // 집터의 가로길이
		int B = Integer.parseInt(st.nextToken()); // 인벤토리에 들어있는 블록의 개수
		
		int[] arr = new int[N * M]; // 집터
		int[] status = new int[N * M];
		int current = 0; // 블록의 최소 높이로 모든 블록을 맞출경우 걸리는 시간
		int minHeight = 256; // 블록들 중 최소 높이를 저장할 변수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[M * i + j] = Integer.parseInt(st.nextToken()); // 각 위치의 땅의 높이
				minHeight = Math.min(minHeight, arr[M * i + j]); // 블록의 최소높이로 갱신
			}
		}
		
		Arrays.sort(arr); // 높이 오름차순 정렬
		
		for (int i = 0; i < N * M; i++) {
			status[i] = minHeight - arr[i]; // 음수-> 수만큼 블록제거해야함, 0->유지
			current += 2 * status[i] * -1; // 블록제거는 2초걸리므로 -2곱해서 누적
			B += status[i] * -1; // 인벤토리에 블록제거한 개수만큼 누적
		}
		
		int answer = current; // 땅을 고르는 데 걸리는 시간
		while (B >= N * M) { // 인벤토리에 있는 블록의 개수가 N *M보다 크거나 같은 경우 1개씩 쌓아본다.
			for (int i = 0; i < N * M; i++) {
				if (status[i] < 0) { // 블록 제거된 칸인 경우
					current -= 2; // 다시 원상복구 시키므로 소요시간 2감소
				} else { // 블록을 위에 추가하는 칸인 경우
					current += 1; // 가장 위에 있는 블록 위에 놓으므로 1초 소요
				}
				status[i]++; // 각 칸의 상태 변경 
			}
			B -= N * M; // 인벤토리에 있는 블록의 개수 감소시켜줌
			if (answer >= current) { // 현재 소요시간이 저장된 소요시간보다 작거나 같은 경우
				answer = current; // 소요시간 갱신
				minHeight++; // 높이 1증가
			} else { // 현재소요시간이 더 큰경우
				break; // 더이상 볼 필요가 없으므로 중단
			}
		}
		System.out.println(answer + " " + minHeight); // 출력
	}

}
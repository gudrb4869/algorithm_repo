import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <pre>
 * 분단위로 업무가 추가됨. 업무마감기한은 1분기가끝날때까지
 * 업무는 가장 최근에 주어진 순서대로 함. 업무를 받으면 바로 시작 => LIFO
 * 업무를 하던 도중 새로운 업무가 추가된다면 업무를 중단하고 새로운 업무를 진행
 * 새로운 업무가 끝났다면, 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다.
 * 김삼성이 마감한 업무는 업무평가에서 무조건 만점
 * 
 * -> 스택을 활용
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력스트림
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 이번분기가 몇분인지 나타내는 정수
		
		int answer = 0; // 김삼성이 받을 이번 분기 업무 평가 점수를 저장
		Stack<int[]> s = new Stack<>(); // 업무의 만점과 해결하는데 남은 시간을 저장할 스택

		for (int i = 1; i <= N; i++) { // N줄동안 업무의 정보가 주어짐
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken()); // 0인지 1인지 판별할 변수
			
			
			if (cmd == 1) { // 1이면 업무가 주어짐
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				s.push(new int[] {score, time - 1}); // 업무를 받자마자 해당업무를 수행하므로 1초감소시키고 스택에 푸시
			} else if (cmd == 0 && !s.isEmpty()) { // 0이면 업무가 주어지지않는데 만약 끝나지 않은 업무가 스택에 남아있다면
				int[] info = s.pop(); 
				info[1]--; // 해당업무를 수행
				s.push(info); // 다시 스택에 삽입
			}
			
			if (!s.isEmpty() && s.peek()[1] == 0) { // 만약 하고있는 업무의 남은시간이 0초라면
				answer += s.pop()[0]; // 해당업무의 점수를 더해줌
			}
		}
		
		System.out.println(answer); // 출력
	}

}
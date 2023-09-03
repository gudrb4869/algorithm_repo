import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 두가지종류의 폴리오미노 AAAA와 BB가 있고,
 * .과 X로 이루어진 보드판이 있을때 X를 모두 폴리오미노로 덮는다. 이때 폴리오미노로 모두덮은 보드판을 출력
 * 덮을수 없는경우 -1출력
 * 대신 사전순으로 가장 앞서는 답을 출력해야하므로 BB,BB 보다 AAAA가 먼저 오게 처리해야함
 * 
 * =>
 * 연속된 X만큼 1씩카운트하다가 .을만나거나 보드판을 다읽은 경우에 연속된X의길이가 홀수이면 -1출력하도록 하고
 * 아니면 4로나눈몫만큼 AAAA 반복하고, 4로나눈나머지에다가 2로나눈몫만큼 BB를 반복하면됨 
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		char[] board = br.readLine().toCharArray(); // 보드판
		
		int cnt = 0; // 연속된 X의길이를 저장할 변수
		boolean flag = true; // 플래그(폴리오미노로 보드판을 덮을 수 있는지 체크)
		for (int i = 0; i < board.length; i++) { // 보드판의 길이만큼 반복
			if (board[i] == 'X') { // X인경우
				cnt++; // X의길이 1증가
			} else { // .인경우
				if (cnt % 2 == 1) { // 연속된X의길이가 홀수인경우
					flag = false; // 덮을수없음
					break; // 중단
				} else { // 연속된 X의 길이가 짝수인경우
					for (int j = 0; j < cnt / 4; j++) { // X의길이에서 4로나눈몫만큼 AAAA로 채움
						sb.append("AAAA");
					}
					cnt %= 4; // 4로 나눈나머지
					for (int j = 0; j < cnt / 2; j++) { // 4로나눈나머지에서 2로나눈몫만큼 BB로 채움
						sb.append("BB");
					}
					cnt = 0; // X의 길이 0으로 초기화
					sb.append("."); // .은 그대로 .으로 붙임
				}
			}
		}
		
		if (cnt % 2 == 1) { // .을안만나고 보드판 탐색이 끝난경우에도 따로 처리해주어야함
			flag = false;
		} else {
			for (int j = 0; j < cnt / 4; j++) {
				sb.append("AAAA");
			}
			cnt %= 4;
			for (int j = 0; j < cnt / 2; j++) {
				sb.append("BB");
			}
		}
		
		if (flag) { // 덮을수있는 경우 사전순으로 가장 앞서는 답 출력
			System.out.println(sb);
		} else { // 덮을수 없는 경우 -1출력
			System.out.println(-1);
		}
	}

}
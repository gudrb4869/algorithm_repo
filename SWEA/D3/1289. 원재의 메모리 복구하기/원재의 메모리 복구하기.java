import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 메모리 000...0에서 메모리원래값으로 복구하는데 필요한 최소 횟수
 * 만약 i번째값을 바꾼다면 끝까지 모든값을 i번째값으로 덮어씌운다.
 * 따라서 이전메모리값 prev를 0으로 세팅하고 메모리값을 차례대로 순회하면서 현재 메모리값이 prev와 다르면 prev를 현재메모리값으로 세팅하고
 * 수정횟수 1증가시킴 
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) {
			char[] c = br.readLine().toCharArray(); // 메모리 원래 값
			
			int cnt = 0; // 수정 횟수
			char prev = '0'; // 이전메모리값 00..000에서 시작하므로 그냥 0으로 초기화함
			
			for (int i = 0; i < c.length; i++) {
				if (c[i] != prev) { // 현재메모리값과 이전메모리값이 다른경우
					prev = c[i]; // 이전메모리값을 현재메모리값으로 세팅
					cnt++; // 수정횟수 1증가
				}
			}
			
			System.out.printf("#%d %d\n", t, cnt);
		}
	}

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 리스트의 중간에 원소들을 삽입해야하기 때문에 삽입 시간복잡도가 O(1)인 링크드리스트를 이용하여 풀었다.
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) { // 10개의 테스트 케이스동안 반복
			List<Integer> list = new LinkedList<>(); // 링크드리스트 선언
			int n = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list.add(Integer.parseInt(st.nextToken())); // 원본 암호문 저장
			}
			
			int k = Integer.parseInt(br.readLine()); // 명령어의 개수
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; i++) {
				st.nextToken(); // 명령어 I skip
				int x = Integer.parseInt(st.nextToken()); // 앞에서부터 x의 위치
				int y = Integer.parseInt(st.nextToken()); // y개의 숫자
				int[] arr = new int[y]; // y개의 숫자 잠시 저장할 공간
				for (int j = 0; j < y; j++) {
					arr[j] = Integer.parseInt(st.nextToken()); // y개의 숫자 저장
				}
				for (int j = y - 1; j >= 0; j--) { // 뒤에 숫자부터 x의 위치에 삽입
					list.add(x, arr[j]);
				}
			}
			
			sb.append("#").append(t).append(" "); // 테스트 케이스 번호
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" "); // 처음 10개의 숫자 저장
			}
			sb.append("\n"); // 개행
		}
		System.out.print(sb); // 결과 출력
	}

}

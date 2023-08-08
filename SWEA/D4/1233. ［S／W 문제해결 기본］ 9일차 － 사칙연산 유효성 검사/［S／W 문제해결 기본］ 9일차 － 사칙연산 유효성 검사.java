import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 리프노드의 경우 노드번호와 데이터 두개의 값만 읽고
 * 리프노드가 아닌경우 노드번호, 데이터, 왼쪽자식노드번호,오른쪽자식노드번호 4개의 데이털르 받아오기때문에
 * 입력을 받을때 토큰의 길이가 4개면 연산자인지 검사하고 토큰의 길이가 2개면 숫자인지 검사하여 푼다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) { // 10번 반복
			int n = Integer.parseInt(br.readLine()); // 각 케이스의 트리가 갖는 정점의 총 수
			boolean flag = true; // 계산 가능한지 여부
			
			for (int i = 0; i < n; i++) { // n개의 정점 정보
				st = new StringTokenizer(br.readLine());
				st.nextToken(); // 정점 번호
				char data = st.nextToken().charAt(0); // 값

				// 리프노드가 아니면서 정점의 값이 숫자이거나, 리프노드인데 정점의 값이 숫자가 아니면
				// 계산 불가능
				if (st.hasMoreTokens() ^ data >= '0' ^ data <= '9') {
					flag = false;
				}
			}
			
			sb.append("#").append(t).append(" ");
			if (flag) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
			
		}
		System.out.print(sb); // 결과 출력
	}

}
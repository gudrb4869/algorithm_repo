import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1~n까지 n명의 사람이 있음
 * i번사람은 0~(i-1)까지 번호를 뽑아 줄을 선다.
 * 0을 뽑으면 맨뒤에 스고, 1~i-1을 뽑으면 그 수만큼 중간에 줄을 슬수 있다.
 * 스택을 이용하면 문제를 쉽게 해결할 수 있을 것 같다. 
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Integer> s = new Stack<>(); // 줄을 선 결과 저장할 스택
		Stack<Integer> temp = new Stack<>(); // 중간에 끼워넣기 위해 임시로 이용할 스택
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken()); // i+1번 사람이 뽑은 숫자
			
			while (num > 0) { // 0보다클동안
				temp.push(s.pop()); // 뒷사람을 임시 스택에 푸시
				num--;
			}
			
			s.push(i + 1); // i+1번 사람 스택에 푸시
			
			while (!temp.isEmpty()) { // 임시 스택 크기가 0보다 클동안
				s.push(temp.pop()); // 다시 줄을 세움
			}
		}
		
		for (int i = 0; i < s.size(); i++) {
			sb.append(s.get(i)).append(" "); // 출력 결과 스트링빌더에 저장
		}
		sb.append("\n");
		 
		System.out.print(sb); // 결과 출력
	}

}

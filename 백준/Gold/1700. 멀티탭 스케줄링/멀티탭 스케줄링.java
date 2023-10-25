import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 한개의 멀티탭을 이용하는 준규가 전기용품의 사용순서를 기반으로 플러그를 빼는 최소 횟수를 구하는 문제
 * 
 * N(멀티탭구멍의개수)=2고 K(전기용품의총사용횟수)=7일때
 * 전기용품의 이름이 K이하의 자연수로 주어지는데
 * 2 3 2 3 1 2 7 로 주어지면
 * => 1꽂을때 3을 뽑고
 * => 7꽂을땐 2,3 둘중 아무거나 뽑으면 된다
 * => 최소횟수 2회
 * 
 * 아이디어
 * 각 전기용품의 사용 번째 수를 각 인덱스에 해당하는 큐에 저장함
 * 현재 전기용품이 Set에 없고 Set의 size가 N인 경우
 * Set에 저장되어있는 전기용품들 중에서 더이상 등장하지 않는 경우 해당 전기용품 없애고 교체
 * 더이상 등장하지 않는 전기용품이 존재하지 않으면, Set에 있는 전기용품들 중에서 사용번째수가 가장 늦은 전기용품을 없애고 교체
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위로 문자열 분리
		
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수
		int K = Integer.parseInt(st.nextToken()); // 전기 용품의 총 사용횟수

		st = new StringTokenizer(br.readLine());
		
		Queue<Integer>[] appliance = new Queue[K + 1];
		for (int i = 1; i <= K; i++) {
			appliance[i] = new ArrayDeque<>();
		}
		
		int[] input = new int[K];
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(st.nextToken());
			input[i] = num;
			appliance[num].offer(i);
		}
		
		int answer = 0;
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < K; i++) {
			int cur = input[i];
			
			if (!s.contains(cur)) {
				if (s.size() == N) {
					int target = -1, order = -1;
					boolean flag = true;
					for (int num : s) {
						if (appliance[num].isEmpty()) {
							s.remove(num);
							answer++;
							flag = false;
							break;
						}
						int ord = appliance[num].peek();
						if (ord > order) {
							target = num;
							order = ord;
						}
					}
					if (flag) {
						s.remove(target);
						answer++;
					}
				}
				s.add(cur);
			}
			
			appliance[cur].poll();
		}
		
		System.out.println(answer);
	}

}
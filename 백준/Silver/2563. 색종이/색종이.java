import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 각 x좌표마다 y좌표를 저장해서 검은영역의 넓이를 구했다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 색종이의 수
		
		List<Integer>[] arr = new List[100]; // 도화지에서 각 x좌표의 시작y좌표를 저장할 리스트 
		for (int i = 0; i < 100; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int k = 0; k < n; k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 색종이 왼쪽변과 도화지 왼쪽변 사이의 거리
			int y = Integer.parseInt(st.nextToken()); // 색종이 아래쪽변과 도화지 아래쪽변 사이의 거리
			
			for (int i = x; i < x + 10; i++) {
				arr[i].add(y);
			}
		}
		
		for (int i = 0; i < 100; i++) { // y좌표 오름차순 정렬
			Collections.sort(arr[i]);
		}
		
		
		int area = 0; // 검은영역의 넓이
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < arr[i].size() - 1; j++) {
				if (arr[i].get(j) + 10 <= arr[i].get(j + 1)) {
					area += 10;
				} else {
					area += arr[i].get(j + 1) - arr[i].get(j);
				}
			}
			if (arr[i].size() > 0) {
				area += 10;
			}
		}
		
		sb.append(area).append("\n");
		System.out.print(area); // 결과 출력
	}

}

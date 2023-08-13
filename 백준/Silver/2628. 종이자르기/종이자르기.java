import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 가로, 세로 리스트에 점선 번호 넣고 각 너비 높이마다 넓이구해서 가장 큰 종이조각 넓이 구함
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken()); // 종이의 가로길이
		int r = Integer.parseInt(st.nextToken()); // 종이의 세로길이
		
		List<Integer> row = new ArrayList<>();
		List<Integer> column = new ArrayList<>();
		row.add(0);
		column.add(0);
		
		int n = Integer.parseInt(br.readLine()); // 점선의 개수
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향. 가로=0, 세로=1
			int num = Integer.parseInt(st.nextToken()); // 점선 번호
			if (d == 0) {
				row.add(num);
			} else {
				column.add(num);
			}
		}
		row.add(r);
		column.add(c);
		Collections.sort(row);
		Collections.sort(column);
		
		int answer = 0;
		for (int i = 0; i < row.size() - 1; i++) {
			for (int j = 0; j < column.size() - 1; j++) {
				int width = row.get(i + 1) - row.get(i);
				int height = column.get(j + 1) - column.get(j);
				answer = Math.max(answer, width * height); // 종이조각 넓이 최대값으로 갱신
			}
		}
		
		System.out.println(answer);
	}
	
}
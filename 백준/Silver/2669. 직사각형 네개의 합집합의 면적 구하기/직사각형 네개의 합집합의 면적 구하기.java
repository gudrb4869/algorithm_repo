import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 100*100 방문체크배열 만들고
 * 좌표주어질때마다 2중for문 돌면서 방문안한상태면 방문체크해주고 면적1증가시켜줌
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		boolean[][] visited = new boolean[100][100];
		int area = 0;
		
		for (int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					if (!visited[x][y]) {
						visited[x][y] = true;
						area++;
					}
				}
			}
		}
		System.out.println(area);
	}

}
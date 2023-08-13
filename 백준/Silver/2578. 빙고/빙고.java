import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * 5*5 방문체크 배열만들고
 * 사회자가 말하는 번호에 해당하는 숫자가 위치한 인덱스를 방문체크해줌
 * 그순간 빙고 3줄이 완성되었는지 확인하는 메서드를 호출해서 선이 3개이상 그어진 경우 몇번째수부르고 끝났는지 출력
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		boolean[][] arr = new boolean[5][5];
		Map<Integer, int[]> map = new HashMap<>();
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				map.put(num, new int[] {i, j}); // 번호에 해당하는 행,열좌표 해시맵에 저장
			}
		}
		
		int[] cmd = new int[25]; // 사회자가 순서대로 부를 번호
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				cmd[i * 5 + j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 25; i++) {
			int[] info = map.get(cmd[i]); // 사회자가 부른 번호를 꺼내옴
			int r = info[0], c = info[1];
			arr[r][c] = true; // 방문 체크
			if (isFinished(arr)) { // 빙고 3줄완성되었으면
				System.out.println(i+1); // 몇번째에 완성되었는지 출력
				break;
			}
		}
	}

	private static boolean isFinished(boolean[][] arr) {
		int line = 0;
		
		boolean daegak1 = true; // 대각선1 완성여부
		boolean daegak2 = true; // 대각선2 완성여부

		for (int i = 0; i < 5; i++) {
			boolean garo = true; // i번째 행 가로 완성여부
			boolean sero = true; // i번째 열 세로 완성여부
			for (int j = 0; j < 5; j++) {
				garo &= arr[i][j];
				sero &= arr[j][i];
			}
			if (garo) {
				line++;
			}
			if (sero) {
				line++;
			}
			
			daegak1 &= arr[i][i];
			daegak2 &= arr[i][4-i];
		}
		if (daegak1) {
			line++;
		}
		if (daegak2) {
			line++;
		}
		return line >= 3;
	}

}

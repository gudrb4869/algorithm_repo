import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[][] table = new int[6][3];
	static int[][] arr = new int[6][3];
	static int[] result = new int[4];
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());
			arr = new int[6][3];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					if (b[0] == a[0]) {
						return b[1] - a[1];
					} 
					return b[0] - a[0];
				}
			});
			flag = false;
			dfs(0);
			if (flag) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int cnt) {
		
		if (cnt >= 36) {
			boolean isExist = true;
			
			for (int r = 0; r < 6; r++) {
				for (int c = 0; c < 3; c++) {
					isExist &= table[r][c] == arr[r][c];
				}
			}
			if (isExist) {
				flag = true;
			}
			return;
		}
		
		if (!flag) {
			int i = cnt / 6;
			int j = cnt % 6;
			if (i >= j) {
				dfs(cnt + 1);
				return;
			}
			
			// i 승
			if (table[i][0] + 1 <= arr[i][0] && table[j][2] + 1 <= arr[j][2]) {
				table[i][0]++;
				table[j][2]++;
				dfs(cnt + 1);
				table[i][0]--;
				table[j][2]--;
			}
			
			if (table[i][1] + 1 <= arr[i][1] && table[j][1] + 1 <= arr[j][1]) {
				// 무
				table[i][1]++;
				table[j][1]++;
				dfs(cnt + 1);
				table[i][1]--;
				table[j][1]--;
			}
			
			if (table[i][2] + 1 <= arr[i][2] && table[j][0] + 1 <= arr[j][0]) {
				// i 패
				table[i][2]++;
				table[j][0]++;
				dfs(cnt + 1);
				table[i][2]--;
				table[j][0]--;
			}
		}
			
	}

}
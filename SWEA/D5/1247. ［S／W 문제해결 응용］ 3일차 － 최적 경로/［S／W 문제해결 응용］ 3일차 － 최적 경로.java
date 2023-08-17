import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * N명의 고객 존재
 * 회사,집, 고객(2~10명)의 위치는 이차원좌표(x,y)로 주어짐(0~100)
 * 회사에서 출발하여 모든 고객을 방문하고 집으로 돌아오는 경로중 가장 짧은 경로의 이동거리를 찾는 문제
 * 두 지점간의 거리 = |x1-x2| + |y1-y2|
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();	
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] coordinate = new int[N + 2][2];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N + 2; i++) {
				coordinate[i][0] = Integer.parseInt(st.nextToken());
				coordinate[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] arr = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					arr[i][j] = Math.abs(coordinate[i][0] - coordinate[j][0]) + Math.abs(coordinate[i][1] - coordinate[j][1]);
				}
			}
			
			int[] p = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i + 2;
			}
			
			int answer = 987654321;
			do {
				int distance = arr[0][p[0]];
				for (int i = 0; i < N - 1; i++) {
					distance += arr[p[i]][p[i + 1]];
				}
				distance += arr[p[N - 1]][1];
				answer = Math.min(answer, distance);
			} while (np(p));
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	private static boolean np(int[] p) {
		
		int N = p.length;
		int i = N - 1;
		
		while (i>0 && p[i-1] >= p[i]) {
			i--;
		}
		
		if (i == 0) {
			return false;
		}
		
		int j = N - 1;
		while (p[i-1] >= p[j]) {
			j--;
		}
		
		swap(p, i-1, j);
		
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}
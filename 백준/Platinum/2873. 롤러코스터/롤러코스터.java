import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 행열의 크기가 모두 짝수인 경우에만 조건에 따라 움직여주고
 * 나머지경우는 ㄹ자나 N자로 움직여주면 된다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위 분리
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		int R = Integer.parseInt(st.nextToken()); // 부지의 세로크기
		int C = Integer.parseInt(st.nextToken()); // 부지의 가로크기
		
		int[][] arr = new int[R][C]; // R*C의 각칸에 얻을수있는기쁨을 저장할 2차원배열 생성
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 값 저장
			}
		}
		
		if (R % 2 == 1) { // 행이 홀수크기인 경우 역ㄷ자로 반복하면서 움직이면 됨
			for (int k = 0; k < R / 2; k++) {
				for (int i = 0; i < C - 1; i++) {
					sb.append("R");
				}
				sb.append("D");
				for (int i = 0; i < C - 1; i++) {
					sb.append("L");
				}
				sb.append("D");
			}
			for (int i = 0; i < C - 1; i++) {
				sb.append("R");
			}
		} else {
			if (C % 2 == 1) { // 행이 짝수크기인데 열이 홀수크기인경우 N자로 반복하면서 움직이면됨
				for (int k = 0; k < C / 2; k++) {
					for (int i = 0; i < R - 1; i++) {
						sb.append("D");
					}
					sb.append("R");
					for (int i = 0; i < R - 1; i++) {
						sb.append("U");
					}
					sb.append("R");
				}
				for (int i = 0; i < R - 1; i++) {
					sb.append("D");
				}
				
			} else { // 행과 열 모두 짝수크기인 경우에는 (r+c)%2==1인 곳들중 가장작은칸을 제외하고 움직이면됨
				int minR = 0, minC = 1;
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if ((i + j) % 2 == 1) {
							if (arr[minR][minC] > arr[i][j]) {
								minR = i;
								minC = j;
							}
						}
					}
				}
				
				// 최소지점의 행이 있는곳 (minR / 2)전까지 ㄹ자로 이동
				for (int k = 0; k < minR / 2; k++) {
					for (int i = 0; i < C - 1; i++) {
						sb.append("R");
					}
					sb.append("D");
					for (int i = 0; i < C - 1; i++) {
						sb.append("L");
					}
					sb.append("D");
				}
				// 지나가지않은 지점이 있는 구역에선 조건에따라 지그재그로 돈다.
				for (int k = 0; k < minC / 2; k++) {
					sb.append("DRUR");
				}
				if (minR % 2 == 0) {
					sb.append("DR");
				} else {
					sb.append("RD");
				}
				for (int k = 0; k < C / 2 - minC / 2 - 1; k++) {
					sb.append("RURD");
				}
				
				// 이후 도착지점까지 ㄷ자로 반복하며 이동
				for (int k = 0; k < R / 2 - minR / 2 - 1; k++) {
					sb.append("D");
					for (int i = 0; i < C - 1; i++) {
						sb.append("L");
					}
					sb.append("D");
					for (int i = 0; i < C - 1; i++) {
						sb.append("R");
					}
				}
			}
		}
		
		System.out.println(sb); // 움직임 결과 출력
	}

}

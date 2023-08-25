import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 구현, 시뮬레이션
 * 상어들의 이동결과를 저장하기 위해 temp 3차원 임시배열을 선언했고, 이동결과를 원본에 덮어씌우는 방식으로 해결함
 * 상어가 이동하려는 칸이 경계를 넘어갈 경우 방향을 반대로 틀어주었다.
 * 또한 상어가 이동한 위치에 크기가 작은 상어가 있다면 덮어씌웠다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken()); // 격자판 행
		int C = Integer.parseInt(st.nextToken()); // 격자판 열
		int M = Integer.parseInt(st.nextToken()); // 상어의 수
		
		int answer = 0;
		int[][][] arr = new int[R][C][3];
		
		int[] dr = {-1, 1, 0, 0}; // 위, 아래, 오른쪽, 왼쪽
		int[] dc = {0, 0, 1, -1}; // 위, 아래, 오른쪽, 왼쪽
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 상어의 위치
			int c = Integer.parseInt(st.nextToken()) - 1; // 상어의 위치
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동 방향 (1=위, 2=아래, 3=오른쪽, 4=왼쪽)
			int z = Integer.parseInt(st.nextToken()); // 크기
			arr[r][c][0] = s; // 속력
			arr[r][c][1] = d; // 이동방향
			arr[r][c][2] = z; // 크기
		}
		
		// 각 초마다 0열부터 C - 1열까지 낚시왕이 이동하면서 상어를 잡으려고 시도
		for (int time = 0; time < C; time++) {
			
			for (int i = 0; i < R; i++) { // 땅에서 가장 가까운 상어가 있는지 탐색
				if (arr[i][time][2] > 0) { // 크기가 0보다 크다 => 상어가 존재함.
					answer += arr[i][time][2]; // 상어의 크기 누적
					arr[i][time][0] = arr[i][time][1] = arr[i][time][2] = 0; // 해당 영역의 상어가 사라지도록 세팅
					break; // 상어를 잡았으므로 탐색 중단
				}
			}
			
			int[][][] temp = new int[R][C][3]; // 상어의 이동 결과를 저장할 3차원배열
			
			// 상어의 이동
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j][2] > 0) { // 상어의 크기가 0보다 크다 => 상어가 존재하는 위치!
						
						int count = arr[i][j][0], d = arr[i][j][1] - 1; // count: 상어가 이동할 남은 칸수, d: 상어의 이동방향(0=위,1=아래,2=오른쪽,3=왼쪽)
						if (d == 0 || d == 1) { // 위나 아래방향일때 (R - 1) * 2번 움직이면 다시 원래자리와 원래방향으로 돌아온다. 따라서 연산횟수 줄이기 위해 나머지만 취해줌
							count %= (R - 1) * 2;
						} else if (d == 2 || d == 3) { // 오른쪽이나 왼쪽방향일때 (C - 1) * 2번 움직이면 다시 원래자리와 원래방향으로 돌아온다. 따라서 연산횟수 줄이기 위해 나머지만 취해줌
							count %= (C - 1) * 2;
						}
						int r = i, c = j; // 상어의 현재 위치
						while (count > 0) { // 상어가 속도만큼의 칸을 이동하기 전까지 반복
							if ((d == 0 && r == 0) || (d == 1 && r == R - 1) || (d == 2 && c == C - 1) || (d == 3 && c == 0)) {
								// 위방향인데 0행인경우, 아래방향인데 R-1행인경우, 오른쪽방향인데 C-1열인경우, 왼쪽방향인데 0열인경우
								// 방향 반대방향으로 바꿔줌
								d ^= 1; // 1비트 자리를 xor 연산해주면 위<->아래, 오른쪽<->왼쪽 방향전환을 쉽게 할수있다.
							}
							r += dr[d]; // 상어의 위치 이동
							c += dc[d]; // 상어의 위치 이동
							count--; // 상어의 이동할수 있는 남은 칸수 1감소
						}
						
						if (arr[i][j][2] > temp[r][c][2]) { // 상어의 이동결과, 해당위치에 있는 상어의 크기보다 현재이동시킨 상어의크기가 더 클경우 
							temp[r][c][0] = arr[i][j][0]; // 상어의 속력
							temp[r][c][1] = d + 1; // 상어의 이동방향(-1보정시켜줬던거 다시 1더해서 복구)
							temp[r][c][2] = arr[i][j][2]; // 상어의 크기
						}
					}
				}
			}
			
			arr = temp; // 이동결과의 상태로 덮어씌움

		}
		
		System.out.println(answer);
	}

}
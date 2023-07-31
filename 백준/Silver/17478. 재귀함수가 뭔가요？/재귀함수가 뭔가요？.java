import java.util.Scanner;

/**
 * 재귀함수로 풀이함
 * @author 박형규
 *
 */
public class Main {

	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		recursion(0, n);
		System.out.print(sb);
	}
	
	private static void recursion(int i, int n) {
		print("\"재귀함수가 뭔가요?\"", i);
		if (i == n) {
			print("\"재귀함수는 자기 자신을 호출하는 함수라네\"", i);
		} else {
			print("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.", i);
			print("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", i);
			print("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"", i);
			recursion(i + 1, n);
		}
		print("라고 답변하였지.", i);
	}
	
	private static void print(String msg, int repeat) {
		for (int i = 0; i < repeat; i++) {
			sb.append("____");
		}
		sb.append(msg + "\n");
	}
}

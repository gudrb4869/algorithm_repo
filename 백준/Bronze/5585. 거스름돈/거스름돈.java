import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rem = 1000 - sc.nextInt();
		int answer = 0;
		int[] coins = {500, 100, 50, 10, 5, 1};
		
		for (int coin : coins) {
			answer += rem / coin;
			rem %= coin;
		}
		System.out.println(answer);
	}

}
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

	private static int answer;
	private static int n;
	private static int first, second;
	private static boolean finished;
	private static int[] count;
	private static boolean isPossible;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] s1 = sc.next().split("");
			n = sc.nextInt();
			String[] s2 = s1.clone();
			Arrays.sort(s2, Collections.reverseOrder());
			first = strToInt(s2);
			swap(s2, s2.length - 2, s2.length - 1);
			second = strToInt(s2);
			answer = 0;
			finished = isPossible = false;
			count = new int[10];
			
			for (String s : s1) {
				count[Integer.parseInt(s)]++;
			}
			for (int i : count) {
				if (i > 1) {
					isPossible = true;
					break;
				}
			}
			
			dfs(0, n, s1);
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static void dfs(int step, int maxStep, String[] str) {
		if (finished)
			return;
		
		int cur = strToInt(str);
		if (first == cur) {
			finished = true;
			if (isPossible || (maxStep - step) % 2 == 0) {
				answer = first;
			} else {
				answer = second;
			}
			return;
		}
		
		if (step >= maxStep) {
			if (first == cur)
				finished = true;
			answer = Integer.max(answer, cur);
			return;
		}
		
		
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str.length; j++) {
				if (i != j && !finished) {
					swap(str, i, j);
					dfs(step + 1, maxStep, str);
					swap(str, i, j);
				}
			}
		}
		
		
	}

	private static int strToInt(String[] str) {
		return Integer.parseInt(String.join("", str));
	}

	private static void swap(String[] str, int i, int j) {
		String temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}

}
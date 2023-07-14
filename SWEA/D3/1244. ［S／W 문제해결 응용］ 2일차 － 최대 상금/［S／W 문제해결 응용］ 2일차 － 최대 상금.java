import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

	private static int answer;
	private static int n, m;
	private static int first, second;
	private static boolean promising, isPossible;
	private static int[] count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String[] str = sc.next().split("");
			n = str.length;
			m = sc.nextInt();
			
			String[] s2 = str.clone();
			Arrays.sort(s2, Collections.reverseOrder());
			first = strToInt(s2);
			swap(s2, s2.length - 2, s2.length - 1);
			second = strToInt(s2);
			answer = 0;
			promising = isPossible = false;
			count = new int[10];
			
			for (String s : str) {
				count[Integer.parseInt(s)]++;
			}
			for (int i : count) {
				if (i > 1) {
					isPossible = true;
					break;
				}
			}
			
			dfs(0, m, str); // 백트래킹 알고리즘 수행
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static void dfs(int step, int m, String[] str) {
		if (promising)
			return;
		
		int cur = strToInt(str);
		
		if (step >= m) {
			if (first == cur)
				promising = true;
			answer = Integer.max(answer, cur);
			return;
		}
		
		if (first == cur) {
			promising = true;
			if (isPossible || (m - step) % 2 == 0) {
				answer = first;
			} else {
				answer = second;
			}
			return;
		}
		
		for (int i = step; i < str.length; i++) {
			for (int j = i + 1; j < str.length; j++) {
				swap(str, i, j);
				dfs(step + 1, m, str);
				swap(str, i, j);
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

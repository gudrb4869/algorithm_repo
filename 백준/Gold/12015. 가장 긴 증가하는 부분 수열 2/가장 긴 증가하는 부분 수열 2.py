from bisect import bisect_left
import sys
input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))
dp = [a[0]]

for i in a:
    if dp[-1] < i:
        dp.append(i)
    else:
        dp[bisect_left(dp, i)] = i
print(len(dp))
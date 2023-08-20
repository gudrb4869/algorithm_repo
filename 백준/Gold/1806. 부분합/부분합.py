import sys
input = sys.stdin.readline

n, s = map(int, input().split())
a = list(map(int, input().split()))
dp = [0] * (n + 1)
for i in range(1, n + 1):
    dp[i] = dp[i - 1] + a[i - 1]

ans = []
l, r = 0, 1
while l <= n and r <= n and l < r:
    cur = dp[r] - dp[l]
    if cur >= s:
        ans.append(r - l)
        l += 1
    else:
        r += 1

print(min(ans) if ans else 0)
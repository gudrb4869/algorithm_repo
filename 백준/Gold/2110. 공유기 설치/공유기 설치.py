import sys
input = sys.stdin.readline

n, c = map(int, input().split())
arr = sorted([int(input()) for _ in range(n)])
low, high = 1, arr[-1] - arr[0]
answer = 0

while low <= high:
    mid = (low + high) // 2
    cnt = 1
    cur = arr[0]
    for a in arr[1:]:
        if a - cur >= mid:
            cnt += 1
            cur = a
    if cnt < c:
        high = mid - 1
    else:
        low = mid + 1
        answer = mid

print(answer)
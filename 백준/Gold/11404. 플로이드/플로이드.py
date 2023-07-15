import sys
input = sys.stdin.readline
INF = int(1e9)
n = int(input())
m = int(input())
arr = [[INF] * n for _ in range(n)]
for i in range(n):
    arr[i][i] = 0
for _ in range(m):
    a, b, c = map(int, input().split())
    arr[a - 1][b - 1] = min(arr[a - 1][b - 1], c)

for k in range(n):
    for i in range(n):
        for j in range(n):
            if arr[i][j] > arr[i][k] + arr[k][j]:
                arr[i][j] = arr[i][k] + arr[k][j]

for i in range(n):
    for j in range(n):
        print(0 if arr[i][j] == INF else arr[i][j], end=' ')
    print()
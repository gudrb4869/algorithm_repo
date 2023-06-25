n = int(input())
d = []
for _ in range(n):
    d.append(list(map(int, input().split())))

for i in range(1, n):
    d[i][0] += d[i - 1][0]
    d[i][i] += d[i - 1][i - 1]
    for j in range(1, i):
        d[i][j] += max(d[i - 1][j - 1], d[i - 1][j])

print(max(d[-1]))
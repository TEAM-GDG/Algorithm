let ESM = readLine()!.split(separator: " ").compactMap{ Int($0)! }
let E = ESM[0], S = ESM[1], M = ESM[2]

var year = 1

while true {
    if (year-E) % 15 == 0 && (year-S) % 28 == 0 && (year-M) % 19 == 0 {
        print(year)
        break
    }
    year += 1
}

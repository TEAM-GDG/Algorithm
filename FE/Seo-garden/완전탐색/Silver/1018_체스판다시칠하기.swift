import Foundation

let blackBoard = [["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"]]

let whiteBoard = [["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"],
                  ["W","B","W","B","W","B","W","B"],
                  ["B","W","B","W","B","W","B","W"]]

let NM = readLine()!.split(separator: " ").compactMap { Int($0)! }
let N = NM[0], M = NM[1]
var board = [[String]]()
var count = 64

for _ in 0..<N {
    board.append(readLine()!.map { String($0) })
}

for i in 0..<N - 7 {
    for j in 0..<M - 7 {
        var Bcount = 0
        var Wcount = 0
        
        for k in 0..<8 {
            for l in 0..<8 {
                if board[i+k][j+l] != blackBoard[k][l] { Bcount += 1 }
                if board[i+k][j+l] != whiteBoard[k][l] { Wcount += 1 }
            }
        }
        let min = min(Bcount, Wcount)
        if count > min { count = min }
    }
}
print(count)

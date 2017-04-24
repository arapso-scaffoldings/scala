package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) {
        return 1
      }
      return pascal(c-1, r -1) + pascal(c, r - 1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      val left = '('
      val right = ')'

      def isBalance(chars: List[Char], toClose: Int): Int = {
        if (chars.isEmpty) {
          toClose
        } else {
          if (chars.head == left) {
            isBalance(chars.tail, toClose + 1)
          } else {
            if(toClose > 0) isBalance(chars.tail, toClose - 1) else -1
          }
        }
      }
      val normalized = chars.filter(x => left == x || right == x)
      isBalance(normalized, 0) == 0
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0 || coins.isEmpty) {
        0
      } else {
        if(coins.size == 1) {
          if(money % coins.head == 0) 1 else 0
        } else {
          val onlyHeadCoin = countChange(money, List(coins.head))

          var moneyLeft = money
          var temp2 = 0
          while(moneyLeft - coins.head > 0) {
            moneyLeft = moneyLeft - coins.head
            temp2 += countChange(moneyLeft, coins.tail)
          }
          val temp3 = countChange(money, coins.tail)
          onlyHeadCoin + temp2 + temp3
        }
      }
    }
  }

package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    pascal(5, 5)
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println("done!")
    }

    val ss: List[Char] = "(if (zero? x) max (/ 1 x))".toList
    println("List1--> " + ss)
    println("List--> " + balance(ss))

    //    val s2: List[Char] = "())(".toList
    val s2: List[Char] = "".toList
    println("List1--> " + s2)
    println("List--> " + balance(s2))

    val s3: List[Char] = ")(".toList
    println("List1--> " + s3)
    println("List--> " + balance(s3))

    println("countChange--> " + countChange(4, List(1, 2)))

  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == r || c == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], done: Int): Boolean =
      chars match {
        case Nil      => done == 0
        case '(' :: t => loop(t, done + 1)
        case ')' :: t => done > 0 && loop(t, done - 1)
        case _ :: t   => loop(t, done)
      }
    loop(chars, 0)

  }

  /*
   * Exercise 3 : Count Change
   * 
   */


  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(money: Int, coins: List[Int]): Int = {
      if (coins.isEmpty) 0
      else if (money - coins.head == 0) 1
      else if (money - coins.head < 0) 0
      else
        loop(money - coins.head, coins) + loop(money, coins.tail)
    }
    loop(money, coins.sorted)
  }

}

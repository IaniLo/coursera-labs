package patmat

import common._

//import Huffman._

object worksheet1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(335); 
  
/*
  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree
  */
  
  val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5);System.out.println("""t1  : <error> = """ + $show(t1 ));$skip(219); 
  //val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  val t2 = Fork(
    Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5),
    Leaf('d', 4), List('a', 'b', 'd'), 9);System.out.println("""t2  : <error> = """ + $show(t2 ));$skip(849); val res$0 = 
/*
  def weight(tree: CodeTree): Int = tree match {
    case Leaf(_, x)       => x
    case Fork(l, r, _, w) => weight(l) + weight(r)
  }

  def chars(tree: CodeTree): List[Char] = tree match {
    case Leaf(c, _)           => List(c)
    case Fork(_, _, clist, _) => clist

  }

 
  def times(chars: List[Char]): List[(Char, Int)] =
  	chars.groupBy(char => char)
    .map { case (char, list) => char -> list.length}
    .toList
*/
/*
  def times(chars: List[Char]): List[(Char, Int)] = {
    def incr(acc:Map[Char, Int], c:Char) = {
      val count = (acc get c).getOrElse(0) + 1
      acc + ((c, count))
    }

    (Map[Char,Int](): chars)(incr).iterator.toList
  }
  */
  
  // List(('a', 2), ('b', 1))

  //val vv = List(('a', 2), ('b', 1))

  //def times(chars: List[Char]): List[(Char, Int)] = for(e <- chars.) yield List(e, i)

  weight(t1);System.out.println("""res0: <error> = """ + $show(res$0));$skip(12); val res$1 = 
  chars(t2);System.out.println("""res1: <error> = """ + $show(res$1));$skip(74); val res$2 = 
  times(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'));System.out.println("""res2: <error> = """ + $show(res$2))}


  // List.concat(v1,v2)
}

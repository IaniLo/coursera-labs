package patmat

import common._


object worksheet {
  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(394); 

  // Part 1: Basics
  def weight(tree: CodeTree): Int = tree match {
    case Leaf(_, x)       => x
    case Fork(l, r, _, w) => weight(l) + weight(r)
  };System.out.println("""weight: (tree: patmat.worksheet.CodeTree)Int""");$skip(141); 

  def chars(tree: CodeTree): List[Char] = tree match {
    case Leaf(c, _)           => List(c)
    case Fork(_, _, clist, _) => clist

  };System.out.println("""chars: (tree: patmat.worksheet.CodeTree)List[Char]""");$skip(137); 

  def makeCodeTree(left: CodeTree, right: CodeTree) =
    Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right));System.out.println("""makeCodeTree: (left: patmat.worksheet.CodeTree, right: patmat.worksheet.CodeTree)patmat.worksheet.Fork""");$skip(97); 

  // Part 2: Generating Huffman trees

  def string2Chars(str: String): List[Char] = str.toList;System.out.println("""string2Chars: (str: String)List[Char]""");$skip(154); 
  def times(chars: List[Char]): List[(Char, Int)] =
    chars.groupBy(char => char)
      .map { case (char, list) => char -> list.length }
      .toList;System.out.println("""times: (chars: List[Char])List[(Char, Int)]""");$skip(147); 
  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
    freqs.sortWith((f1, f2) => f1._2 < f2._2).map((f) => Leaf(f._1, f._2))
  };System.out.println("""makeOrderedLeafList: (freqs: List[(Char, Int)])List[patmat.worksheet.Leaf]""");$skip(66); 
  def singleton(trees: List[CodeTree]): Boolean = trees.size == 1;System.out.println("""singleton: (trees: List[patmat.worksheet.CodeTree])Boolean""");$skip(212); 
  def combine(trees: List[CodeTree]): List[CodeTree] = trees match {
    case left :: right :: cs => (makeCodeTree(left, right) :: cs)
      .sortWith((t1, t2) => weight(t1) < weight(t2))
    case _ => trees
  };System.out.println("""combine: (trees: List[patmat.worksheet.CodeTree])List[patmat.worksheet.CodeTree]""");$skip(224); 

  def until(singleton: List[CodeTree] => Boolean, combine: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): List[CodeTree] = {
    if (singleton(trees)) trees
    else until(singleton, combine)(combine(trees))
  };System.out.println("""until: (singleton: List[patmat.worksheet.CodeTree] => Boolean, combine: List[patmat.worksheet.CodeTree] => List[patmat.worksheet.CodeTree])(trees: List[patmat.worksheet.CodeTree])List[patmat.worksheet.CodeTree]""");$skip(122); 
  def createCodeTree(chars: List[Char]): CodeTree =
    until(singleton, combine)(makeOrderedLeafList(times(chars))).head

  // Part 3: Decoding

  type Bit = Int;System.out.println("""createCodeTree: (chars: List[Char])patmat.worksheet.CodeTree""");$skip(529); 
  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
      def loop(remaining: CodeTree, bits: List[Bit]): List[Char] = remaining match {
        case Leaf(c, _) if bits.isEmpty                => List(c)
        case Leaf(c, _)                                => c :: loop(tree, bits)
        case Fork(left, right, _, _) if bits.head == 0 => loop(left, bits.tail)
        case Fork(left, right, _, _)                   => loop(right, bits.tail)
      }

    loop(tree, bits)
  };System.out.println("""decode: (tree: patmat.worksheet.CodeTree, bits: List[patmat.worksheet.Bit])List[Char]""");$skip(2003); 
 // val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279), Leaf('f', 16351), List('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), List('k', 'w'), 2492), List('z', 'k', 'w'), 4585), Leaf('y', 4725), List('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), List('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), List('z', 'k', 'w', 'y', 'h', 'q'), 41497), List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856), Leaf('u', 96785), List('m', 'p', 'u'), 188641), List('o', 'l', 'm', 'p', 'u'), 355071), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110), List('v', 'g', 'b'), 52085), List('c', 'v', 'g', 'b'), 102088), List('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915), List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575), List('e', 'i', 'a'), 458522), List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)
//  val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)
  def decodedSecret: List[Char] = decode(frenchCode, secret);System.out.println("""decodedSecret: => List[Char]""");$skip(372); 

  // Part 4a: Encoding using Huffman tree

  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
      def loop(tree: CodeTree)(c: Char): List[Bit] = tree match {
        case Leaf(_, _)              => List()
        case Fork(left, right, _, _) => if (chars(left).contains(c)) 0 :: loop(left)(c) else 1 :: loop(right)(c)
      }
    text flatMap loop(tree)
  }

  // Part 4b: Encoding using code table
  type Code = (Char, List[Bit])
  type CodeTable = List[Code];System.out.println("""encode: (tree: patmat.worksheet.CodeTree)(text: List[Char])List[patmat.worksheet.Bit]""");$skip(222); 
  
  def codeBits(table: CodeTable)(char: Char): List[Bit] = {
    table.filter((code) => code._1 == char).head._2
  };System.out.println("""codeBits: (table: patmat.worksheet.CodeTable)(char: Char)List[patmat.worksheet.Bit]""");$skip(200); 
  def convert(tree: CodeTree): CodeTable = tree match {
    case Leaf(char, leaf)        => List((char, List()))
    case Fork(left, right, _, _) => mergeCodeTables(convert(left), convert(right))
  };System.out.println("""convert: (tree: patmat.worksheet.CodeTree)patmat.worksheet.CodeTable""");$skip(186); 


  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = {
      def prepend(b: Bit)(code: Code): Code = (code._1, b :: code._2)

    a.map(prepend(0)) ::: b.map(prepend(1))
  };System.out.println("""mergeCodeTables: (a: patmat.worksheet.CodeTable, b: patmat.worksheet.CodeTable)patmat.worksheet.CodeTable""");$skip(281); 

  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = {
      def loop(table: CodeTable, text: List[Char]): List[Bit] = text match {
        case List()  => List()
        case x :: xs => codeBits(table)(x) ++ loop(table, xs);
      }
    loop(convert(tree), text)
  };System.out.println("""quickEncode: (tree: patmat.worksheet.CodeTree)(text: List[Char])List[patmat.worksheet.Bit]""");$skip(39); val res$0 = 
  
  
  
  
  List(('a', 2), ('b', 1));System.out.println("""res0: List[(Char, Int)] = """ + $show(res$0));$skip(37); 

  val vv = List(('a', 2), ('b', 1));System.out.println("""vv  : List[(Char, Int)] = """ + $show(vv ));$skip(64); 

  val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5);System.out.println("""t1  : patmat.worksheet.Fork = """ + $show(t1 ))}
/*
  val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5),Leaf('d', 4), List('a', 'b', 'd'), 9)

  weight(t1)
    
    
  chars(t2)
  times(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))

*/
  // List.concat(v1,v2)
}

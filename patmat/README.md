
# Week 4

### Assignment: Huffman Coding

>Huffman coding is a compression technique used to reduce the number of bits needed to send or store a message.   
It's based on the idea that frequently-appearing letters should have shorter bit representations and less common letters should have longer representations. See  [Huffman Coding](https://en.wikipedia.org/wiki/Huffman_coding)

### Implementation

Again, we are going to work with binary trees.
Huffman tree can be represemis in Scala as follows:

```scala
abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree
  ```
So we are going to represent Huffman code as a binary tree whose leaves represent the
characters that should be encoded. The code tree below can represent the characters A to H.
  
### Solution
  

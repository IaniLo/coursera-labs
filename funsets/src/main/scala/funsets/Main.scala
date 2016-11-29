package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  type Predicate = Int => Boolean
  type intToInt = Int => Int


  
  val mySet1:Set  = x => Array(0, 1, 2, 3, 5, 8) contains x
  val mySet2: Set = x => Array(5, 8, 9, 10, 11) contains x

 
  
  printSet("mySet1: ", mySet1)
  printSet("mySet2: ", mySet2)

  printSet("union: ", union(mySet1, mySet2))
  printSet("intersection: ", intersect(mySet1, mySet2))
  printSet("diff: ", diff(mySet1, mySet2))
  val p1: Predicate = x => (x>3) && (x<7)  
  printSet("filter with x => (x>3) && (x<7)",filter(mySet1,p1))

  println("forAll with x=>x < 9): ",forall(mySet1,x=>x<9))
  println("exist with  x=>(x>3) && (x<7): ",exists(mySet1,p1))
  printSet("Map with  x => x * 2",map(mySet1, x => x * 2))
  

}

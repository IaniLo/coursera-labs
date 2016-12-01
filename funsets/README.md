# Week 2

## About the characteristic function of a set

In mathematics, we call a function which takes an integer as argument and which returns a boolean indicating 
whether the given integer belongs to a set, _the characteristic function of the set_. 
This is an _abstract mathematical concept_ and you should not pay too much attention to understand it more than it requires...
The goal here is to grasp and practice with **higher-order functions**.

###**_Abstract:_**
**Definition**: Let’s consider A ⊂ E ≠ ∅ (a universal set), then  
![](./images/f001.png?raw=true "Optional Title") where the function   
![](./images/f002.png?raw=true "Optional Title")  
is called the _characteristic function_ of the set **A** .

 
```
Let’s take an example to kind of motivate our representation:  
Think that you bought a lottery ticket. After the lottery draw is done a set A containing 
the winning number is established. This set comes attached with a function “f” 
called characteristic function which will enable you to pass, as an argument, one by one, 
each of you ticket numbers and get back 1 if the number is a winner, 0 otherwise. 
```

We are going to represent a set by its characteristic function and also define a _type alias_ for its representation:
```scala
type Set = Int => Boolean
```

In this session you also introduced to the new types like "Set = Int => Boolean" This just say that a function can return a new function with this specific signature.

### :point_right: Solution:

```scala
  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): Set = (x: Int) => x == elem
  

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)
  
  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)
  
  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)
  
  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set = (x: Int) => s(x) && p(x)
  

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      val myS = filter(s,p)
      if (a > bound) true
      else if ( (s(a)) && !(p(a))) false
      else iter(a+1)
    }
    iter(-bound)
  }
  
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x=> !p(x))
  
  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: Set, f: Int => Int): Set = (y: Int) => exists(s, x => f(x) == y)

```

### Displaying the content of a set

It's nice to have a function to display the content of a such set especially during the developing and testing fazes.

```scala
  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(msg:String, s: Set) {
    println(msg+" : "+toString(s))
  }
```

**Note:** You can study similar types, like **Multiset** 
```scala
type MultiSet = (Int => Int) 
```
For a given argument x: Int the function returns: 
* 0 if x not in Multiset
* n if x is n times in Multiset 




## Assignment: Object-Oriented Sets

In this assignment you will work with an object-oriented representations based on binary trees.
And among the task we have to implement 
* _filtering_, 
* _union_ and 
* _sorting_ on `Tweet Sets` 


```scala
class Tweet(val user: String, val text: String, val retweets: Int)
abstract class TweetSet 
  -- This represents a set of objects of type `Tweet` in the form of a binary search tree.
  -- Every branch in the tree has two children (two `TweetSet`s)
class Empty extends TweetSet
class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet
trait TweetList
object Nil extends TweetList 
class Cons(val head: Tweet, val tail: TweetList) extends TweetList
object GoogleVsApple
```


### Solution
```scala
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
        "User: " + user + "\n" +
          "Text: " + text + " [" + retweets + "]"
}

abstract class TweetSet {
  //We need to know if a TweetSet is empty
  def empty: Boolean

  /**
   * This method takes a predicate and returns a subset of all the elements
   * in the original set for which the predicate is true.
   *
   * Question: Can we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)

  /**
   * This is a helper method for `filter` that propagetes the accumulated tweets.
   */
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  /**
   * Returns a new `TweetSet` that is the union of `TweetSet`s `this` and `that`.
   *
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def union(that: TweetSet): TweetSet

  /**
   * Returns the tweet from this set which has the greatest retweet count.
   *
   * Calling `mostRetweeted` on an empty set should throw an exception of
   * type `java.util.NoSuchElementException`.
   *
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def mostRetweeted: Tweet

  /**
   * Returns a list containing all tweets of this set, sorted by retweet count
   * in descending order. In other words, the head of the resulting list should
   * have the highest retweet count.
   *
   * Hint: the method `remove` on TweetSet will be very useful.
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def descendingByRetweet: TweetList

  /**
   * The following methods are already implemented
   */

  /**
   * Returns a new `TweetSet` which contains all elements of this set, and the
   * the new element `tweet` in case it does not already exist in this set.
   *
   * If `this.contains(tweet)`, the current set is returned.
   */
  def incl(tweet: Tweet): TweetSet

  /**
   * Returns a new `TweetSet` which excludes `tweet`.
   */
  def remove(tweet: Tweet): TweetSet

  /**
   * Tests if `tweet` exists in this `TweetSet`.
   */
  def contains(tweet: Tweet): Boolean

  /**
   * This method takes a function and applies it to every element in the set.
   */
  def foreach(f: Tweet => Unit): Unit
}

class Empty extends TweetSet {

  def empty = true
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  def union(that: TweetSet): TweetSet = that
  def mostRetweeted: Tweet = throw new java.util.NoSuchElementException
  def descendingByRetweet: TweetList = Nil

  /**
   * The following methods are already implemented
   */

  def contains(tweet: Tweet): Boolean = false
  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)
  def remove(tweet: Tweet): TweetSet = this
  def foreach(f: Tweet => Unit): Unit = ()
  override def toString = "."
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def empty = false

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    right.filterAcc(p, left.filterAcc(p, if (p(this.elem)) acc.incl(elem) else acc))
  }

  def union(that: TweetSet): TweetSet = {
    (left union (right union that)).incl(elem)
  }

  def descendingByRetweet: TweetList = {
    new Cons(mostRetweeted, remove(mostRetweeted).descendingByRetweet)
  }

  def mostRetweeted: Tweet = {
    lazy val leftBranch = left.mostRetweeted
    lazy val rightBranch = right.mostRetweeted

    if (!left.empty && leftBranch.retweets > elem.retweets)
      if (!right.empty && rightBranch.retweets > leftBranch.retweets)
        rightBranch
      else
        leftBranch
    else if (!right.empty && rightBranch.retweets > elem.retweets)
      rightBranch
    else
      elem
  }

  /**
   * The following methods are already implemented
   */

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
  override def toString: String = "{" + left + elem + right + "}"
}

trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}
```

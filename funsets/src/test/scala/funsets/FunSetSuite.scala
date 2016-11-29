package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  test("This Set should not contain -100") {
    assert(contains({ x: Int => x < 0 }, -100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)

    val u1 = union(union(s1, s2), s4) // {1,2,4}
    val u2 = union(s4, s5) //{4,5}
    val u3 = intersect(u1, u2) //{4}
    val evNr = union(s2, s4)
    
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
      
      assert(contains(u1, 4), "Union 4")
      

    }
  }
  //====================================

  test("intersections") {
    new TestSets {

      val intersection1 = intersect(s1, s2)
      assert(!contains(intersection1, 1), "Intersect 1 between singletonSet(1) and singletonSet(2)")
      val intersection2 = intersect(s1, s4)
      assert(!contains(intersection2, 1), "Intersect 1 between singletonSet(1) and singletonSet(4)")

      assert(contains(u3, 4), "->Intersect 4 between u1 and u2")

    }
  }

  test("difference") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
      assert(!contains(s, 3), "Diff 3")

      assert(contains(diff(u2, u1), 5), "-->Diff 4")
      assert(contains(diff(u1, u2), 1) || contains(diff(u1, u2), 1), "-->Diff 5")
    }
  }

  test("filtering") {
    new TestSets {
      val s = filter(s1, s2)
      assert(!contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(!contains(s, 3), "Filter 3")

      val filter4 = filter(s1, { x: Int => x < 2 })
      assert(contains(filter4, 1), "Filters s1 for the predicate f(x) = x<2")

      val filter5 = filter(u3, { elem: Int => elem != 5 })
      assert(contains(filter5, 4), "Filters s1 for the predicate f(x) = x==4")

    }
  }

  test("forall predicate") {
    new TestSets {
      assert(forall(s1, s1))
      assert(!forall(s1, s2))

      assert(forall(union(s1, s2), { x: Int => x > 0 }), "Check to see if the forall aplied to union(s1,s2) satisfy the predicate f(x) = x>0")

    }
  }

  test("exists predicate") {
    new TestSets {
      assert(exists(s1, s1))
      assert(!exists(s1, s2))

      assert(exists(union(s1, s2), { x: Int => x > 0 }), "Check to see if the exist aplied to union(s1,s2) satisfy the predicate f(x) = x>0")

    }
  }

  test("mapping") {
    new TestSets {

      val s = map(s1, x => x * 2)
      assert(forall(s, s2))
    }
  }

}

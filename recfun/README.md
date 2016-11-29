# Assignment 1: Recursion

## Exercise 1: Pascal’s Triangle

In mathematics, Pascal's triangle is a triangular array of the binomial coefficients.
In the Western world, it is named after French mathematician Blaise Pascal
The following pattern of numbers is called Pascal’s triangle.


<img src="./images/pascals-triangle.gif"/>


The numbers at the edge of the triangle are all `1`, and each number inside the triangle is the sum of the two numbers above it.
Write a function that computes the elements of Pascal’s triangle by means of a recursive process.


```scala
def pascal(c: Int, r: Int): Int
```

## Exercise 2: Parentheses Balancing

The problem was to take a string and determine if the parenthesis in the string matched using a recursive method.
Write a recursive function which verifies the balancing of parentheses in a string, which we represent as a `List[Char]` not a `String`.

Do this exercise by implementing the balance function in Main.scala.
Its signature is as follows:

```scala
def balance(chars: List[Char]): Boolean
```


## Exercise 3: Counting Change

The problem:
Given some numerical amount and some set of coin denominations we need to count the number of ways that we can represent the amount given the set of denominations.
Do that by writing a recursive function!
For example, there are 3 ways to give change for 4 if you have coins with denomiation 1 and 2: 1+1+1+1, 1+1+2, 2+2.

Do this exercise by implementing the countChange function in Main.scala. This function takes an amount to change, and a list of unique denominations for the coins.
Its signature is as follows:

```scala
def countChange(money: Int, coins: List[Int]): Int
```

Once again, you can make use of functions `isEmpty`, `head` and `tail` on the list of integers `coins`.

**Hint:** Think of the degenerate cases. How many ways can you give change for 0 CHF? How many ways can you give change for >0 CHF, if you have no coins?




Functional Programming Principles in Scala
===========================================



#ABOUT THE CHARACTERISTIC FUNCTION OF A SET

###**_Abstract:_**
**Definition**: Let’s consider A ⊂ E ≠ ∅ (a universal set), then  
![](./images/f001.png?raw=true "Optional Title") where the function   
![](./images/f002.png?raw=true "Optional Title")  
is called the characteristic function of the set A .

So, in mathematics, we call a function which takes an integer as argument and which returns a boolean indicating 
whether the given integer belongs to a set, _the characteristic function of the set_. 
This is an _abstract mathematical concept_ and you should not pay too much attention to understand it more than it requires...
The goal here is to grasp and practice with **higher-order functions**.
 
 
```
Let’s take an example to kind of motivate our representation:  
Think that you bought a lottery ticket. After the lottery draw is done a set A of winning number is established. 
This set comes attached with a function “f” called characteristic function which will enable you to pass, as an argument, one by one, each of you ticket numbers and get back 1 if the number is a winner, 0 otherwise. 
```

In this session you also introduced to the new types like "Set = Int => Boolean" This just say that a function can return a new function with this specific signature.

Note: For avid developers thin about a similar type: type Multiset = (Int => Int) For a given argument x: Int the function returns: 0 if x not in Multiset
n if x is n times in Multiset [(positive number of times it appears otherwise).

f(x) = 0 if x not in Multiset
f(x) = n if n is n times in Multiset [(positive number of times it appears otherwise).



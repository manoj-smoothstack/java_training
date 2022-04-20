# Assignment

## 1. Testing Lambda

Create an example of how you would test a lambda function. Implement a lambda function that calculates the nth term
of a Fibonacci series. And then write a JUnit test for this function.

## 2. Testing Stream

Consider the following example:

```
public void sumWithNoInitialValue() {
   List<Integer> numbers = Arrays.asList(1, 2, 3);
   Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
   System.out.println(sum);
   // Optional[6]
}
```

How would you modify this stream, so that it is testable? Fix the above code so that you can write a JUnit test for it.

Once you are done fixing the code, you can write a JUnit test.

## 3. Maven and testing

Install mvn at command line on your computer and run

```
mvn test
```

with the first two examples to make sure you can run the tests at command line.


1. Compare checked vs. unchecked exceptions in Java.

- checked exceptions 
  - checked at compile time
  - derived from class Exception
  - caught using "try catch" instruction blocks and thrown in the method signature using "throws"
  - examples include IOException and user-defined exceptions

- unchecked exceptions
  - not checked at compile time
  - derived from classes RuntimException and Error
  - are not caught, meaning recovery from unchecked exceptions being thrown cannot be done
  - examples include NullPointerException, IndexOutOfBoundsException, ArithmeticException (division by 0)
  
2. Given the following collection
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,14,15);
   Using Java functional style (Java streams), please write a program that does the following operations in the following order:
        a) keep only the numbers which are multiple of 3 or of 2;
        b) transform each remaining number into a string, that consists of a prefix "A", the successor of the number and the suffix "B"
        c) concatenate all the strings
        
                String result = numbers.stream()
                               .filter(p -> {return (p%2==0 || p%3==0);})
                               .map(p -> "A" + (p=p+1).toString() + "B")
                               .collect(Collectors.joining(","));
                               
                or, alternatively
                
                List<String> result = numbers.stream()
                                     .filter(p -> {return (p%2==0 || p%3==0);})
                                     .map(p -> "A" + (p=p+1).toString() + "B")
                                     .collect(Collectors.toList());
                                     
 3. What is a Cyclic Barrier in Java?
    A Cyclic Barrier is a synchronization mechanism used for thread synchronization. Threads wait for one another to reach a common execution point, called a „barrier”, before resuming execution. Threads wait for eachother by calling the await() method. Once a given number N of threads reach the common execution point, all threads resume execution.

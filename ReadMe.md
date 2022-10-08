# Basic Lexical Analysis for Java

###### This program produces output for the user by performing the Lexical Analysis process on the source codes of the Java language at a basic level.

## What is lexical analysis

In computer science, lexical analysis, lexing or tokenization is the process of converting a sequence of characters (such as in a computer program or web page) into a sequence of lexical tokens (strings with an assigned and thus identified meaning). A program that performs lexical analysis may be termed a lexer, tokenizer,[1] or scanner, although scanner is also a term for the first stage of a lexer. A lexer is generally combined with a parser, which together analyze the syntax of programming languages, web pages, and so forth. [1]

Example:
Program.java:

```
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {

        // Creates a reader instance which takes
        // input from standard input - keyboard
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");

        // nextInt() reads the next integer from the keyboard
        int number = reader.nextInt();

        // println() prints the following line to the output screen
        System.out.println("You entered: " + number);
    }
}

```

Output
```
Class NameHelloWorld
Sub-elements:0
Functions Count:4
-------------
Name: main
Return Type:    public static void
Count of Parameter:1
Parameter:String[] args
-------------
Name: Scanner
Return Type:        Scanner reader = new
Count of Parameter:1
Parameter:System.in
-------------
Name: System.out.print
Return Type:       
Count of Parameter:1
Parameter:"Enter a number: "
-------------
Name: reader.nextInt
Return Type:        int number =
Count of Parameter:0
Parameter:None
-------------
Name: System.out.println
Return Type:       
Count of Parameter:1
Parameter:"You entered: " + number
```


[1] https://en.wikipedia.org/wiki/Lexical_analysis

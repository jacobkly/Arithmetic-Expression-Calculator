# Arithmetic Expression Calculator (V2)
This console-based calculator (V2) evaluates arithmetic expressions using two parsing methods that convert the expression from infix notation to post-fix/Reverse Polish Notation (RPN) or to a Tree similar to an expression tree. Therefore, it contains two different implementations of the Shunting Yard algorithm by Edsger Dijkstra as the expression parser producing a List in RPN or a Tree.

## Calculator (V2) Rules
The following user input rules apply:
  - Only integer and decimal numbers are acceptable for numbers/operands.
  - Only the following five operators are acceptable: '+', '-', '*', '/', '^'.
  - Only the following eight mathematical functions are acceptable: "sin", "cos", "tan", "sec", "csc", "cot", "log", "ln".
    - For trigonometric functions, the argument value is assumed to be in radians.
    - For logarithmic functions, custom bases should follow an underscore character after the function. For example, "log_2" or "log_10".
    - Euler's number and pi can be inputted as "e" and "pi".
  - Between each distinct segment of the input expression, a space should be present.
    - For example, the expression "(((3 + 2) - 1) ^ 2)" should be "( ( ( 3 + 2 ) - 1 ) ^ 2 )" and "sin(pi/2) - log_2(2)" should be "sin ( pi / 2 ) - log_2 ( 2 )".

## How does the calculator (V2) work?
  1. The calculator takes the user's input expression as a string and separates it into a List, keeping the infix notation characteristic of the expression.
  2. The List is fed into two different Shunting Yard algorithm implementations producing another List representing the input expression in RPN and a Tree.
  3. Once converted, the calculator evaluates the List representing the expression in RPN using a stack and evaluates the Tree recursively.
  4. Finally, the calculator outputs both resulting values from the List and Tree evaluation.

## Future Goals
With this Arithmetic Expression Calculator V2 functioning as desired, I believe I am about 50% there toward my final goal of programming a symbolic differentiation calculator. My next goal, as of July 20th, 2024, is to create this calculator.

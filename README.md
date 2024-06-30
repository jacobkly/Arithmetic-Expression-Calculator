# Arithmetic Expression Calculator
This calculator follows the PEMDAS order of operations and evaluates arithmetic expressions ONLY. It contains my implementation of the Shunting Yard algorithm by Edsger Dijkstra as the expression parser.

## Calculator Rules
The following user input rules apply:
  - The program only accepts single-digit integers as operands
  - The program only accepts the following five operators: '+', '-', '*', '/', and '^'
  - The program does not allow any space characters in the expression
  - Any letters in the input expression are ignored

## How does the calculator work?
  1. The calculator takes the user's input expression in infix notation and converts it to postfix notation (also known as RPN) using the Shunting Yard algorithm.
  2. Once converted to postfix notation, the calculator evalutes the expression using a stack data structure.
     - When operands are met, they are pushed onto the stack.
     - When operators are met, two operands are popped from the stack and the operator is applied to those operands.
     - The last value in the stack is the final evaluated value of the postfix notation expression.
  3. Finally, the calculator outputs the final value as a double.

## Future Goals
This calculator is hopefully the beginning foundation of other, larger and more difficult functioning, calculators created by me.

My next goal, as of June 29, 2024, is to create an updated calculator that allows any number of digits, allows spacing, and allows basic functions, such as min/max, trigonometric, and logarithmic functions.

The ultimate goal of mine, is to create a fully functional **derivates calculator**.

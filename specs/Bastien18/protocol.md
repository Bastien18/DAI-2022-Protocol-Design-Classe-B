# Protocol spec
## Objectives:
This protocol needs to let a client ask for specific operation to the server. The server will then compute the result and give it to the client.
## Overall behavior:

- TCP to be sure to have an answer for each calculus request.
- By using the server address and the port 445.
- the client initiate and close the connection.

## Message:

- inverted polish syntax
- ask for solving a calculus
  gives the answer
  gives an error
- 

## Specific element:
- plus, minus, multiplication, division
- divide by 0
  lack of operand
- modulo, roots, power

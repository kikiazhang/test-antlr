grammar Calculator;

/*
 * Tokens (terminal)
 */
POW: '^';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
NUMBER: [0-9]+;
WHITESPACE: [ \r\n\t]+ -> skip;

/*
 * Production Rules (non-terminal)
 */
start : expression;

/*
 * First is to set up the order of the expressions
 * to determine by which possible matching rules to
 * parse the statement.
 * The order is starting at numbers, parentheses,
 * power, multiplication, division, addition,
 * subtraction.
 *
 * Labels (e.g. "# Number") are added to each expression
 * to help to generate the next context.
 * This is can be used in a Listener or Visitor class
 * to allow separated control of the actions.
 *
 * Likewise, inner labels (e.g. "left=expression")
 * can be added to child nodes of the rule.
 * This makes them identifiable in a
 * Listener's or Visitor's parsing of the rule,
 * allowing for even more fine-grained control.
 */
expression
   : NUMBER                                         # Number
   | '(' inner=expression ')'                       # Parentheses
   | left=expression operator=POW right=expression  # Power
   | left=expression operator=MUL right=expression  # Multiplication
   | left=expression operator=DIV right=expression  # Division
   | left=expression operator=ADD right=expression  # Addition
   | left=expression operator=SUB right=expression  # Subtraction
   ;

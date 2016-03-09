
- currency (3 letter code) is transformed to uppercase
- transactions with wrong numbers are ignored (used amount 0 -> balance is not changed)
- if exchange rate is not set, amount in USD is not displayed (only original currency)

Program accepts filename as parameter (file in specified format).
When file not exist, can be currency and amount entered from console (finished by "quit").

There are 2 example commands:
runme.cmd - execution without parameters
runme2.cmd - execution with optional (filename) parameter
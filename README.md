# Game_busters
a repository for programs that solve various games including word games, puzzle games, etc.

#1: WordCruncher: code to solve the popular game pigeon word games like Anagrams and Word Hunt:

- (check src folder in the WordCruncher folder to find this) Word Hunt is a 4X4 grid of letters A-Z that must be combiined to make words minimum length of three, these letter can be combined by starting at any one cell and connecting subsequent letters that are adjacent or diagnol from the current letter:

In the case below for example, we can create the word "HUNT"

| H | _ | _ | _ |

| _ | U | N | _ |

| _ | _ | T | _ |

| _ | _ | _ | _ |


the code should take a string of 16 characters that represent each letter in the matrix and output an array of strings that represents words that can be legally made by the rules of the game from the given matrix.

"ABCDEFGHIJKLMNOP" would create:

| A | B | C | D |

| E | F | G | H |

| I | J | K | L |

| M | N | O | P |


possible words that can be made here include: "INK" or "KNIFE"

- Anagrams is another word game that instead gives the player 6 letters A-Z and the goal is to produce as many combinations of words as possible from just these six letters.

The program will take a string of 6 characters and return an array containing all possible words
ex: "ABCDEF"
possible strings in the retruning array could be : "ACE" or "BEAD"

#2: SudokuSolver: code to solve sudoku:

the code will be given a 2D array of characters that are either numbers from 1-9 or blank space characters: '_', given this array the algorithm will recursivley find the correct numbers for the blank tiles and print a grid of numbers representing the solved sudoku board.

#3: TwentyFourGameSolver:

- 24 is a number game in which four integers are given to the players, and the four basic operations (+, -, *, /) can be used to make those four numbers into the number 24 by the end of the operations.
- for example, if given the numbers: {4, 7, 8, 8}, the solution would be:
    - (7 + 4 = 11)
    - (11 - 8 = 3)
    - (8 * 3 = 24) -> ended with 24
- this solver asks for user input for 4 integers and calculates the steps to get the solution
- the method utilizes recursion to explore all options and return the correct answer if it exists

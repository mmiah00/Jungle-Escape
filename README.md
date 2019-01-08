# JungleEscape

1/4/2019
Vivien: We spent most of class talking in general about how to code for different parts of the games. I added a createbackground() to TerminalDemo to form the grid for 2048. 

1/6/2019
Vivien: I restarted from scratch, realizing that I didn't need to include any of the terminal aspects in the NumberPuzzle class. Partway in, after I had already made a few commits, I realized I named the NumberPuzzle class incorrectly, so I removed the old numberPuzzle and created a new file, copying the code from the other one. I finished writing constructor, toString(), all the move methods and isComplete(). Next, I need to start working on JungleEscape to incorporate the terminal and keystrokes. 

1/7/2019
Vivien: After I did additional testing on my NumberPuzzle, I realized there were a lot of problems, so I spent today fixing those issues. I had to redesign the grid, changing it to a String 2d-array to fit four digit numbers. Then, I added an inputNewNum() that adds a new num once the player has made a move. My next step is to incorporate the terminal and keystrokes. 

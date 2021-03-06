/* RULES OF 3072Game
------------------------
4x4 tile grid with each tile being able to contain one number
the numbers will be three times the number before in the sequence starting from 3 (i.e. 3, 9, 27, ..., 3072)
you start with some number of '3' tiles at random locations and combine them to make higher number tiles (3 and 3 tile makes a 9 tile)
to move tiles together, use the arrow keys and all blocks on grid will move in that corresponding direction until they hit a block or a wall
blocks that do not have the same number cannot be combined (e.g. 3 and 9 tiles will not mix)
with each move of the arrow key, a new tile (or tiles) get generated of usually a low number (like 3 or 9) at empty locations
the player wins when he/she is able to get a 3072 tile
the player loses if the grid is filled with tiles that cannot be combined further in their current orientation
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game
{
    private JFrame frame;
    private JLabel instructions;
    private JLabel [][] labels;
    private JPanel panelHolder;
    private JButton startButton; //might not have startButton
    private int availableSquares;
    private int[][] allSquares;
    
    public Game()
    {
        availableSquares = 16;
        allSquares = new int[4][4];
        initializePanels();
        initializeKeys();
        
        generateNumber();
        
        frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("3072 Game");
        frame.add(panelHolder);
        //frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void initializeKeys()
    {
        class KeyBoardListener implements KeyListener 
        {
            public void keyPressed( KeyEvent event )
            {
                if(event.getKeyCode() == KeyEvent.VK_LEFT) 
		{
                    if(isValidMove("Left"))
                    {
                        moveNumbers(0);
                        checkCombineNumbers(0);
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    if(isValidMove("Right"))
                    {
                        moveNumbers(1);
                        checkCombineNumbers(1);
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(isValidMove("Up"))
                    {
                        moveNumbers(2);
                        checkCombineNumbers(2);
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(isValidMove("Down"))
                    {
                        moveNumbers(3);
                        checkCombineNumbers(3);
                    }
                }
                else
                    return;
                
                if(!isValidMove("Left") && !isValidMove("Right") && !isValidMove("Up") && !isValidMove("Down"))
                    gameOver();
                else
                    generateNumber();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        }
        KeyListener listener = new KeyBoardListener();
        panelHolder.addKeyListener(listener);
        panelHolder.setFocusable(true);
    }
    
    public void initializePanels()
    {
        labels = new JLabel[4][4];
        panelHolder = new JPanel();
        panelHolder.setLayout(new GridLayout(4,4));
        panelHolder.setBorder(BorderFactory.createLineBorder(Color.black));
        for(int i = 0; i<4;  i++)
        {
            for(int k = 0; k<4; k++)
            {
                labels[i][k] = new JLabel();
                labels[i][k].setBorder(BorderFactory.createLineBorder(Color.black));
                labels[i][k].setText("");
                allSquares[i][k] = 0;
                panelHolder.add(labels[i][k]);
            }
        }
    }
    
    //checks if moving is possible (either if combining blocks is possible or there is an empty space)
    public boolean isValidMove(String direction)
    {
        if(direction == "Left")
        {
            for(int row = 0; row<4; ++row)
            {
                for(int col = 0; col<3; ++col)
                { 
                    if(allSquares[row][col] == allSquares[row][col+1] || (allSquares[row][col]==0 && allSquares[row][col+1]>0))
                    {
                        return true;
                    }
                }
            }
        }
        else if(direction == "Right")
        {
            for(int row = 0; row<4; ++row)
            {
                for(int col = 3; col>0; --col)
                { 
                    if(allSquares[row][col] == allSquares[row][col-1] || (allSquares[row][col]==0 && allSquares[row][col-1]>0))
                    {
                        return true;
                    }
                }
            }
        }
        else if(direction == "Up")
        {
            for(int col = 0; col<4; ++col)
            {
                for(int row = 0; row<3; ++row)
                { 
                    if(allSquares[row][col] == allSquares[row+1][col] || (allSquares[row][col]==0 && allSquares[row+1][col]>0))
                    {
                        return true;
                    }
                }
            }
        }
        else //direction == "Down"
        {
            for(int col = 0; col<4; ++col)
            {
                for(int row = 3; row>0; --row)
                { 
                    if(allSquares[row][col] == allSquares[row-1][col] || (allSquares[row][col]==0 && allSquares[row-1][col-1]>0))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void generateNumber()
    {
        int whichBox = (int)(Math.random()*availableSquares);
        int index = -1; //make equal to -1 ???
        for(int i = 0; i<4; ++i)
        {
            for(int j = 0; j<4; ++j)
            {
                if(allSquares[i][j] == 0)
                {
                    ++index;
                    if(index==whichBox)
                    {
                        allSquares[i][j] = 3;
                        //or have some 9's too
                        break;
                    }
                }
            }
        }  
        --availableSquares;
        System.out.print(index/4); System.out.println(index%4);
        labels[index/4][index%4].setText("\t\t\t\t3"); //tried to use setHorizontalAlignment(SwingConstants.CENTER), but wasn't working
        labels[index/4][index%4].repaint();
        panelHolder.repaint();
    }
    
    //direction: 0 = left; 1 = right; 2 = up; 3  = down
    //this is called after the blocks are moved in the direction given (but not "combined" yet)
    public void checkCombineNumbers(int direction)
    {
        if(direction == 0)
        {
            for(int row = 0; row<4; ++row)
            {
                for(int col = 0; col<3; ++col)
                { 
                    if(allSquares[row][col] == allSquares[row][col+1])
                    {
                        combineNumbers(row, col+1, row, col);
                        --col;
                    }
                }
            }
        }
        else if(direction ==1)
        {
            for(int row = 0; row<4; ++row)
            {
                for(int col = 3; col>0; --col)
                { 
                    if(allSquares[row][col] == allSquares[row][col-1])
                    {
                        combineNumbers(row,col-1,row,col);
                        --col;
                    }
                }
            }
        }
        else if(direction ==2)
        {
            for(int col = 0; col<4; ++col)
            {
                for(int row = 0; row<3; ++row)
                { 
                    if(allSquares[row][col] == allSquares[row+1][col])
                    {
                        combineNumbers(row+1,col,row,col);
                        --row;
                    }
                }
            }
        }
        else
        {
            for(int col = 0; col<4; ++col)
            {
                for(int row = 3; row>0; --row)
                { 
                    if(allSquares[row][col] == allSquares[row-1][col])
                    {
                        combineNumbers(row,col,row-1,col);
                        --row;
                    }
                }
            }
        }
    }
    
    //direction: 0 = left; 1 = right; 2 = up; 3  = down
    //move all blocks as far as possible without combining (yet)
    public void moveNumbers(int direction)
    {
        if(direction == 0)
        {
            for(int row = 0; row<4; ++row)
            {
                for(int col = 1; col<4; ++col)
                { 
                    if(allSquares[row][col-1] == 0 && allSquares[row][col]>0)
                    {
                        allSquares[row][col-1] = allSquares[row][col];
                        allSquares[row][col] = 0;
                        //UPDATE JLABEL
                    }
                }
            }
        }
        else if(direction ==1)
        {
            //same as above
        }
        else if(direction ==2)
        {
            
        }
        else
        {
            
        }
    }
    
    //combines 1 into 2 (direction)
    public void combineNumbers(int row1, int col1, int row2, int col2)
    {
         allSquares[row1][col1] = 0;
         //put like if it's equal to zero, then the text is empty for JLabel
         //MOVE ALL THE SQUARES DOWN TOO
        ++availableSquares;
    }
    
    public void gameOver()
    {
        //gameOver stuff
    }
}

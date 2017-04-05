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
                
                if(availableSquares==0)
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
        for(int i = 0; i<4;  i++)
        {
            for(int k = 0; k<4; k++)
            {
                labels[i][k] = new JLabel();
                labels[i][k].setText("");
                allSquares[i][k] = 0;
                panelHolder.add(labels[i][k]);
            }
        }
    }
    
    public boolean isValidMove(String direction)
    {
        if(direction == "Left")
        {
            
        }
        else if(direction == "Right")
        {
            
        }
        else if(direction == "Up")
        {
            
        }
        
        else //direction == "Down"
        {
            
        }
        
        return false;
    }
    
    public void generateNumber()
    {
        int whichBox = (int)(Math.random()*availableSquares)+1;
        int index = 0; //make equal to -1 ???
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
        labels[index/4][index%4].setText("3");
        panelHolder.repaint();
    }
    
    //direction: 0 = left; 1 = right; 2 = up; 3  = down
    //this is called after the blocks are moved in the direction given (but not "combined" yet)
    public void checkCombineNumbers(int direction)
    {
        if(direction == 0)
        {
            for(int col = 0; col<4; ++col)
            {
                for(int row = 0; row<3; ++row)
                { 
                    if(allSquares[col][row] == allSquares[col][row+1])
                    {
                        combineNumbers(col, row+1, col, row);
                        --row;
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
    
    //direction: 0 = left; 1 = right; 2 = up; 3  = down
    //move all blocks as far as possible without combining (yet)
    public void moveNumbers(int direction)
    {
        if(direction == 0)
        {
            for(int col = 1; col<4; ++col)
            {
                for(int row = 0; row<4; ++row)
                { 
                    if(allSquares[col-1][row] == 0 && allSquares[col][row]>0)
                    {
                        allSquares[col-1][row] = allSquares[col][row];
                        allSquares[col][row] = 0;
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
    public void combineNumbers(int col1, int row1, int col2, int row2)
    {
         allSquares[col1][row1] = 0;
         //put like if it's equal to zero, then the text is empty for JLabel
         //MOVE ALL THE SQUARES DOWN TOO
        ++availableSquares;
    }
    
    public void gameOver()
    {
        //gameOver stuff
    }
}

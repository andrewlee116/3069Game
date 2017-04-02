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
    private JLabel score, instructions;
    private int highScore;
    private JPanel[][] panels;
    private JPanel panelHolder;
    private JButton startButton; //might not have startButton
    
    public Game()
    {
        initializePanels();
        initializeKeys();
        
        
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
                        
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    if(isValidMove("Right"))
                    {
                        
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(isValidMove("Down"))
                    {
                        
                    }
                }
                else if(event.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(isValidMove("Up"))
                    {
                        
                    }
                }
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
        panels = new JPanel[4][4];
        panelHolder = new JPanel();
        panelHolder.setLayout(new GridLayout(4,4));
        for(int i = 0; i<4;  i++)
        {
            for(int k = 0; k<4; k++)
            {
                panels[i][k] = new JPanel();
                panelHolder.add(panels[i][k]);
            }
        }
    }
    
    public boolean isValidMove(String direction)
    {
        if(direction == "Left")
        {
            
        }
        else if(direction == "Up")
        {
            
        }
        else if(direction == "Right")
        {
            
        }
        else //Direction == "Down"
        {
            
        }
        
        return false;
    }
    
}
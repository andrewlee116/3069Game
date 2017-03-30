import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


//THE FOLLOWING has been copied from the JPanelMod class in Tritris as a model to be implemented later for 3072Game

public class JPanelMod extends JPanel
{
    private JPanel copy;
    private int dim, type;
    
    public JPanelMod(int dimensions, int type)
    {
        super(true);
        dim = dimensions;
        this.type = type;
        makeCopy();
    }
    
    public void makeCopy()
    {
        copy = new JPanel();
        if(dim==5)
        {
            copy.setLayout(new GridLayout(5,5));
        }
        else
        {
            copy.setLayout(new GridLayout(4,4));
        }  
        shade();
    }
    
    public void shade()
    {
        if(type==0)
        {
            JPanel[][] future0 = new JPanel[5][5];
            for(int i = 0; i<5; i++)
            {
                for(int j = 0; j<5; j++)
                {
                    future0[i][j] = new JPanel();
                    future0[i][j].setBackground(Color.LIGHT_GRAY);
                    copy.add(future0[i][j]);
                }
            }
            future0[2][1].setBackground(Color.cyan);
            future0[2][2].setBackground(Color.cyan);
            future0[2][3].setBackground(Color.cyan);
            
            future0[2][1].setBorder(BorderFactory.createLineBorder(Color.black));
            future0[2][2].setBorder(BorderFactory.createLineBorder(Color.black));
            future0[2][3].setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }
    
    public JPanel getCopy()
    {  
        makeCopy();
        return copy;
    }
}
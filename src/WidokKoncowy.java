import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.util.Vector;
import model.ListaDrzew;
import model.WezelDrzewa;

public class WidokKoncowy extends JPanel
{
	private Vector<WezelDrzewa> drzewa;
	private JButton przyciskPowrotu;
	private JTree drzewo;
	
	DefaultMutableTreeNode node;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("klazule");
	
	public WidokKoncowy()
	{
		this.setSize(400,400);
		przyciskPowrotu = new JButton("Powrot");
		drzewo = new JTree(rootNode);
		drzewo.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);		
		
		
		// TODO tutaj zrobic 
		this.drzewa = new Vector<WezelDrzewa>();
		
		// TODO przerobic elementy tak by odpowiednie byly
		//______________________________________________________
				
		/*node = addANode("A",rootNode);
		addANode("B",rootNode);
		addANode("C",rootNode);
		
		rootNode = node;
		
		node = addANode("D",rootNode);
		addANode("E",rootNode);
		node = addANode("F",rootNode);
				
		rootNode = (DefaultMutableTreeNode)rootNode.getParent();
		node = addANode("G", rootNode);
		node = addANode("H", rootNode);
		node = addANode("I", rootNode);*/
		
		this.add(przyciskPowrotu);
		this.add(drzewo);		
	}
	
	public void przepiszDrzewa()
	{
            for(int i =0;i<drzewa.size();i++)
            {
                
                node = addANode(String.valueOf(i),rootNode);
                
                
                przepiszWezel(drzewa.get(i),node);
            }
		
	}
        
        private void przepiszWezel(WezelDrzewa wezel ,DefaultMutableTreeNode n )
        {
            String znak = new String();
            DefaultMutableTreeNode kozen;
            
            if((wezel.isZnak()))
                {
                    znak = "-";
                }
                if(wezel.getSpojnik() == 1)
                {
                    znak = znak + "&";
                    
                    
                }
                else if(wezel.getSpojnik() == 2)
                {
                    znak = znak + "|";
                    
                }
                else if(wezel.getSpojnik() == 3)
                {
                    znak = znak + "=>";
                    
                }
                else if(wezel.getSpojnik() == 4)
                {
                    znak = znak + "<=>";
                    
                }
                else if(wezel.getSpojnik() == -1)
                {
                    znak = znak + "()";
                    kozen = addANode(znak, n);
                    przepiszWezel(wezel.getPrawy(),kozen);
                    return;
                    
                }
                else if(wezel.getSpojnik() == 0||wezel.getSpojnik() == 5)
                {
                    znak = znak + wezel.getZdanie();
                    kozen = addANode(znak, n);
                    return;
                }
                kozen = addANode(znak, n);
                
                przepiszWezel(wezel.getLewy(),kozen);
                przepiszWezel(wezel.getPrawy(),kozen);
                
                
            
            
        }
	
	public void ustawListeDrzew(Vector<WezelDrzewa> listyDrzew)
	{
		drzewa = new Vector<WezelDrzewa> (listyDrzew);
	}
	
	public void DodajListenerPowrotu(ActionListener listener)
	{
		this.przyciskPowrotu.addActionListener(listener);
	}
	
	private DefaultMutableTreeNode addANode(String nazwa, DefaultMutableTreeNode folder)
	{
		DefaultMutableTreeNode nowy = new DefaultMutableTreeNode(nazwa);
		folder.add(nowy);
		
		return nowy;
	}
}

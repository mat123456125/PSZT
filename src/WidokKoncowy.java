import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.util.Vector;
import model.listaDrzew;
import model.wezeldrzewa;
// UDALO SIE ????
public class WidokKoncowy extends JPanel
{
	private Vector<wezeldrzewa> drzewa;
	private JButton przyciskPowrotu;
	private JTree drzewo;
	
	DefaultMutableTreeNode node;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("NODES");
	
	public WidokKoncowy()
	{
		this.setSize(400,400);
		przyciskPowrotu = new JButton("Powrot");
		drzewo = new JTree(rootNode);
		drzewo.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);		
		
		
		// TODO tutaj zrobic 
		this.drzewa = new Vector<wezeldrzewa>();
		
		// TODO przerobic elementy tak by odpowiednie byly
		//______________________________________________________
				
		node = addANode("A",rootNode);
		addANode("B",rootNode);
		addANode("C",rootNode);
		
		rootNode = node;
		
		node = addANode("D",rootNode);
		addANode("E",rootNode);
		node = addANode("F",rootNode);
				
		rootNode = (DefaultMutableTreeNode)rootNode.getParent();
		node = addANode("G", rootNode);
		node = addANode("H", rootNode);
		node = addANode("I", rootNode);
		
		this.add(przyciskPowrotu);
		this.add(drzewo);		
	}
	
	public void ustawListyDrzew(Vector<wezelDrzewa> listyDrzew)
	{
		drzewa = new Vector<wezelDrzewa> ()
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

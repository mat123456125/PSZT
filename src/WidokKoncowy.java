import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.util.ArrayList;
import java.util.Vector;
import model.ListaDrzew;
import model.WezelDrzewa;
import model.Literal;

public class WidokKoncowy extends JPanel
{
	private Vector<WezelDrzewa> drzewa;
	private JButton przyciskPowrotu;
	private JTree drzewo;
	
	private ArrayList<ArrayList<Integer>> identyfikatory_przodkow;
	private Vector<Vector<Literal>> klauzule;
	
	private DefaultMutableTreeNode node;
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("klazule");
	private JScrollPane scrollDrzewo;
	
	public WidokKoncowy()
	{
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);

		przyciskPowrotu = new JButton("Powrot");
		drzewo = new JTree(rootNode);
		drzewo.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		
		// TODO tutaj zrobic 
		this.drzewa = new Vector<WezelDrzewa>();
		
		
		scrollDrzewo = new JScrollPane (drzewo);
		
	    scrollDrzewo.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollDrzewo.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    
	    
	    przyciskPowrotu.setBounds(50,600, 100, 50);
	    scrollDrzewo.setBounds(300,50 ,600 ,600);
	    
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
		this.add(scrollDrzewo);		
	}
	
	public void przepiszDrzewa(Vector<Vector<Literal>> klauzule, ArrayList<ArrayList<Integer>> identyfikatory_przodkow)
	{
		this.identyfikatory_przodkow = identyfikatory_przodkow;
		this.klauzule = klauzule;
		
        DefaultMutableTreeNode kozen;
		
        String string = "";
        
		for (int i = 0; i<klauzule.size(); i++)
		{
			for (int y = 0; y < klauzule.get(i).size(); y++)
			{
				if (klauzule.get(i).get(y).isZnak())
				{
					string += "-";
				}
				string += klauzule.get(i).get(y).getZdanie();
				
				if (y == klauzule.get(i).size() - 1)
				{
					break;
				}
				else
				{
					string += " v ";
				}
			}
			
			kozen = addANode(string , rootNode);
			
			dopiszPoddrzewa(kozen, i);
			
			
			//addANode(identyfikatory_przodkow.get(i).get(0));
			
			string = "";

		}

            /*for(int i =0;i<drzewa.size();i++)
            {
                
                node = addANode(String.valueOf(i),rootNode);
                
                
                przepiszWezel(drzewa.get(i),node);
            }*/
		
	}
	
	private void dopiszPoddrzewa(DefaultMutableTreeNode kozen, int i)
	{
		String string = "";
		int pomocniczy;
		
		if (identyfikatory_przodkow.get(i).get(0) != -1)
		{
			pomocniczy = identyfikatory_przodkow.get(i).get(0);
			
			for (int y = 0; y < klauzule.get(pomocniczy).size(); y++)
			{
				if (klauzule.get(pomocniczy).get(y).isZnak())
				{
					string += "-";
				}
				string += klauzule.get(pomocniczy).get(y).getZdanie();
				
				if (y == klauzule.get(pomocniczy).size() - 1)
				{
					break;
				}
				else
				{
					string += " v ";
				}
			}
			
			kozen = addANode(string, kozen);
			
			dopiszPoddrzewa(kozen, pomocniczy);
			
			kozen = (DefaultMutableTreeNode) kozen.getParent();
			
			// dopisujemy i uruchamiamy dla nowego i i kozenia
		}
		
		string = "";
		
		if (identyfikatory_przodkow.get(i).get(1) != -1)
		{
			pomocniczy = identyfikatory_przodkow.get(i).get(1);
			
			for (int y = 0; y < klauzule.get(pomocniczy).size(); y++)
			{
				if (klauzule.get(pomocniczy).get(y).isZnak())
				{
					string += "-";
				}
				string += klauzule.get(pomocniczy).get(y).getZdanie();
				
				if (y == klauzule.get(pomocniczy).size() - 1)
				{
					break;
				}
				else
				{
					string += " v ";
				}
			}
			
			kozen = addANode(string, kozen);
			dopiszPoddrzewa(kozen, pomocniczy);
			kozen = (DefaultMutableTreeNode) kozen.getParent();
		}

		return;
	}
	
	
        
        private void przepiszWezel(WezelDrzewa wezel ,DefaultMutableTreeNode n )
        {
           /* String znak = new String();
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
                
                
            
            */
        }
	
	public void ustawListeDrzew(Vector<WezelDrzewa> listyDrzew)
	{
		drzewa = new Vector<WezelDrzewa> (listyDrzew);
	}
	
	public void DodajListenerPowrotu(PowrotListener powrot)
	{
		this.przyciskPowrotu.addActionListener(powrot);
	}
	
	private DefaultMutableTreeNode addANode(String nazwa, DefaultMutableTreeNode folder)
	{
		DefaultMutableTreeNode nowy = new DefaultMutableTreeNode(nazwa);
		folder.add(nowy);
		
		return nowy;
	}
	
	public void czysc()
	{
        System.out.println("Czyszcze !!!");
		
		remove(scrollDrzewo);
        drzewo = null;
		rootNode = new DefaultMutableTreeNode("klazule");
		drzewo = new JTree(rootNode);

		scrollDrzewo = new JScrollPane (drzewo);
		
	    scrollDrzewo.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollDrzewo.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollDrzewo.setBounds(300,50 ,600 ,600);
	    
        add(scrollDrzewo);	
		//drzewo = null;
		
		return;
	}
}

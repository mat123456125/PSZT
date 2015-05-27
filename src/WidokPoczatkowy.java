import model.ListaDrzew;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class WidokPoczatkowy extends JPanel
{
	private JLabel tresc1;
	private JLabel tresc2;
	private JLabel tresc3;
	private JButton przyciskWczytajPlik;
	private JButton obliczaj;
	private JButton zapiszDoPliku;
	private JTextArea znaczenia;
	private JTextArea predykaty;
	private JTextArea teza;
	


	private ListaDrzew lista;
	
	WidokPoczatkowy ()
	{
		lista = new ListaDrzew();
		
		tresc1 = new JLabel("Symbole zdan");
		tresc2 = new JLabel("Predykaty");
		tresc3 = new JLabel("Teza");
		przyciskWczytajPlik = new JButton("Wczytaj plik");
		obliczaj = new JButton("Oblicz");
		zapiszDoPliku = new JButton("Zapisz do pliku");
		znaczenia =  new JTextArea();
		predykaty = new JTextArea();
		teza = new JTextArea();
		
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		tresc1.setForeground(Color.orange);
		tresc2.setForeground(Color.orange);
		tresc3.setForeground(Color.orange);
		
		tresc1.setBounds(450, 30, 100, 50);
		tresc2.setBounds(470, 260, 100, 50);
		tresc3.setBounds(480, 490, 100, 50);
		przyciskWczytajPlik.setBounds(1050, 200, 180, 50);
		obliczaj.setBounds(1120, 620, 110, 50);
		zapiszDoPliku.setBounds(1050, 280, 180, 50);
		teza.setBounds(100, 540, 800, 50);
		
		JScrollPane scrollZnaczenia = new JScrollPane ( znaczenia );
		JScrollPane scrollPredykaty = new JScrollPane ( predykaty );

	    scrollZnaczenia.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollPredykaty.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollZnaczenia.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPredykaty.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    scrollZnaczenia.setBounds(100, 80, 800, 180);
	    scrollPredykaty.setBounds(100, 310, 800, 180);
	    
	    
	   // scrollZnaczeniaHor.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
	   // scrollPredykatyHor.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
	    
	    //Add Textarea in to middle panel
	    this.add ( scrollZnaczenia );
	    this.add ( scrollPredykaty );
	  //  this.add ( scrollZnaczeniaHor );
	  //  this.add ( scrollPredykatyHor );
		
		this.add(tresc1);
		this.add(tresc2);
		this.add(tresc3);
		this.add(przyciskWczytajPlik);
		this.add(obliczaj);
		this.add(zapiszDoPliku);
		this.add(teza);
		
	}
	public void addActionListener(ActionListener listenerOdczyt, ActionListener listenerZapis, ActionListener listenerOblicz)
	{
		przyciskWczytajPlik.addActionListener(listenerOdczyt);
		zapiszDoPliku.addActionListener(listenerZapis);
		obliczaj.addActionListener(listenerOblicz);
	}
	
	public String getZnaczenia()
	{
		return znaczenia.getText();
	}
	
	public String getPredykaty()
	{
		return predykaty.getText();
	}
	
	public String getTeza()
	{
		return teza.getText();
	}
	
	public void setZnaczenia(String text)
	{
		znaczenia.setText(text);
	}
	
	public void setPredykaty(String text)
	{
		predykaty.setText(text);
	}
	
	public void setTeza(String text)
	{
		teza.setText(text);
	}
	
	public ListaDrzew getLista() {
		return lista;
	}
	
	public void setLista(ListaDrzew lista) {
		this.lista = lista;
	}
	
}

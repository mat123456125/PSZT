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
	private JButton przyciskWczytajPlik;
	private JButton obliczaj;
	private JButton zapiszDoPliku;
	private JTextArea predykaty;
	private JTextArea teza;
	


	private ListaDrzew lista;
	
	WidokPoczatkowy ()
	{
		lista = new ListaDrzew();
		
		tresc1 = new JLabel("Predykaty");
		tresc2 = new JLabel("Teza");
		przyciskWczytajPlik = new JButton("Wczytaj plik");
		obliczaj = new JButton("Oblicz");
		zapiszDoPliku = new JButton("Zapisz do pliku");
		predykaty = new JTextArea();
		teza = new JTextArea();
		
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		tresc1.setForeground(new Color(245,233,188));
		tresc2.setForeground(new Color(245,233,188));
		
		tresc1.setBounds(470, 30, 100, 50);
		tresc2.setBounds(480, 470, 100, 50);
		przyciskWczytajPlik.setBounds(1050, 200, 180, 50);
		obliczaj.setBounds(1120, 620, 110, 50);
		zapiszDoPliku.setBounds(1050, 280, 180, 50);
		
		JScrollPane scrollPredykaty = new JScrollPane ( predykaty );
		JScrollPane scrollTeza = new JScrollPane ( teza );

	    scrollPredykaty.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollPredykaty.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    scrollTeza.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollTeza.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    scrollPredykaty.setBounds(100, 90, 800, 350);
	    scrollTeza.setBounds(100, 530, 800, 130);
	    
	    
	    this.add ( scrollPredykaty );
		
		this.add(tresc1);
		this.add(tresc2);
		this.add(przyciskWczytajPlik);
		this.add(obliczaj);
		this.add(zapiszDoPliku);
		this.add(scrollTeza);
		
	}
	public void addActionListener(ActionListener listenerOdczyt, ActionListener listenerZapis, ActionListener listenerOblicz)
	{
		przyciskWczytajPlik.addActionListener(listenerOdczyt);
		zapiszDoPliku.addActionListener(listenerZapis);
		obliczaj.addActionListener(listenerOblicz);
	}
	
	public String getPredykaty()
	{
		return predykaty.getText();
	}
	
	public String getTeza()
	{
		return teza.getText();
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

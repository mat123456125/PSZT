import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

public class OknoProgramu extends JFrame
{

	private WidokPoczatkowy poczatek;
	
	
	OknoProgramu()
	{
		this.setResizable(false);				// tutaj powoduje ze nie mozna recznie zmienic wielkosci ramki
		
		this.setTitle("Wnioskowanie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(1280,720);
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		
		this.poczatek = new WidokPoczatkowy();
	}
	
	public void ustawWidokPoczatkowy ()
	{
		
		add(poczatek);
		validate();
		repaint();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OknoProgramu naszeOkno = new OknoProgramu();
		naszeOkno.setVisible(true);
	
	
		naszeOkno.ustawWidokPoczatkowy();
		
		OdczytListener odczyt = new OdczytListener(naszeOkno.poczatek);
		ZapisListener zapis = new ZapisListener(naszeOkno.poczatek);
                ObliczListener oblicz = new ObliczListener(naszeOkno.poczatek);
		
		naszeOkno.poczatek.addActionListener(odczyt, zapis, oblicz);
		
	}

}

import javax.swing.JFrame;

import model.ListaDrzew;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Vector;
import model.ListaDrzew;
import model.OperacjeNaDrzewach;
import model.WezelDrzewa;

public class OknoProgramu extends JFrame
{

	private WidokPoczatkowy poczatek;
	private WidokKoncowy koniec;
	
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
		
		this.koniec = new WidokKoncowy();
	}
	
	public void czysc()
	{
		koniec.czysc();
	}
	
	public void ustawDrzewa(Vector<WezelDrzewa>lista)
	{
		koniec.ustawListeDrzew(lista);
	}
	
	public void ustawWidokPoczatkowy ()
	{
		remove(koniec);
		add(poczatek);
		validate();
		repaint();
	}
	
	public void ustawKoniec(OperacjeNaDrzewach operacje)
	{
		remove(poczatek);
		
		koniec.przepiszDrzewa(operacje.getKlauzule(), operacje.getIdentyfikatory_przodkow());
		
		add(koniec);
		validate();
		repaint();
	}
	
	public String getPredykaty()
	{
		return poczatek.getPredykaty();
	}
	
	public String getTeza()
	{
		return poczatek.getTeza();
	}
	
	public ListaDrzew getLista() {
		return poczatek.getLista();
	}
	
	public static void main(String[] args) {

		OknoProgramu naszeOkno = new OknoProgramu();
		naszeOkno.setVisible(true);
	
		naszeOkno.ustawWidokPoczatkowy();
		
		OdczytListener odczyt = new OdczytListener(naszeOkno.poczatek);
		ZapisListener zapis = new ZapisListener(naszeOkno.poczatek);
        ObliczListener oblicz = new ObliczListener(naszeOkno);
		
		naszeOkno.poczatek.addActionListener(odczyt, zapis, oblicz);
		
		PowrotListener powrot = new PowrotListener(naszeOkno);
		naszeOkno.koniec.DodajListenerPowrotu(powrot);
	}

}

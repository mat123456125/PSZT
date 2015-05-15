package model;

import java.util.Vector;

public class OperacjeNaDrzewach
{
	private ListaDrzew predykaty = new ListaDrzew();
	private ListaDrzew teza = new ListaDrzew();
	private Vector<Vector<Literal>> klauzule = new Vector<Vector<Literal>>();
	
	public OperacjeNaDrzewach()
	{
		
	}
	
	public void przypiszZawartosciDoKlauzul()
	{
		for (int x = 0; x < teza.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
		}
		for (int x = 0; x < predykaty.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
		}
	}
	
	private Vector<Literal> usunPowtarzanieIOdwrotnosci (Vector<Literal> oryginal)
	{
		Vector<Literal> zastepczy = new Vector<Literal>(oryginal);
		
		for (int x = 0; x < zastepczy.size() - 1; x++)
		{
			for (int y = x + 1; y < zastepczy.size(); y++)
			{
				
				Literal lit1 = zastepczy.get(x);
				Literal lit2 = zastepczy.get(y);
				
				if (lit1.getZdanie() == lit2.getZdanie() && lit1.isZnak() == lit2.isZnak())
				{
					zastepczy.remove(y);
				}
			}
		}
		
		for (int x = 0; x < zastepczy.size() - 1; x++)
		{
			for (int y = zastepczy.size() - 1 ; y > x ; y--)
			{
				
				Literal lit1 = zastepczy.get(x);
				Literal lit2 = zastepczy.get(y);
				
				if (lit1.getZdanie() == lit2.getZdanie() && lit1.isZnak() != lit2.isZnak())
				{
					zastepczy.remove(x);
					zastepczy.remove(y);
				}
			}
		}
		
		return zastepczy;
	}
	
	private Vector<Literal> tworzNowaKlauzule (Vector<Literal> pierwszy, Vector<Literal> drugi)
	{
		
		Vector<Literal> nowy = new Vector<Literal>();
		
		Vector<Literal> zastepczyPierwszy = usunPowtarzanieIOdwrotnosci(pierwszy);
		Vector<Literal> zastepczyDrugi = usunPowtarzanieIOdwrotnosci(drugi);
		
		for (int x = 0; x < zastepczyPierwszy.size(); x++)
		{
			nowy.add(zastepczyPierwszy.elementAt(x));
		}
		for (int x = 0; x < zastepczyDrugi.size(); x++)
		{
			nowy.add(zastepczyDrugi.elementAt(x));
		}
		
		nowy = usunPowtarzanieIOdwrotnosci(nowy);
				
		return nowy;
	}
	
	public void  wytwarzajNoweKlauzuleNaPodstawieObecnych()
	{
		for (int x = 0; x + 1 < klauzule.size(); x++)
		{
			for (int y = x + 1 ; y < klauzule.size(); y++)
			{
				
				
					Vector<Literal> nowy = tworzNowaKlauzule(klauzule.get(x), klauzule.get(y));
				
					// if (sprawdzCzyDodacKlauzule (nowy) ) // sprawdz czy juz takiej klauzuli nie ma w bazie
					// {
					//		dodajemy nowa klauzule do bazy
					// }
			}
		}
	}
	
	public void Oblicz() throws IllegalAccessException
	{
                predykaty.wypelnijListe();
                teza.wypelnijListe();
	}

    public ListaDrzew getPredykaty()
    {
        return predykaty;
    }

    public void setPredykaty(ListaDrzew predykaty)
    {
        this.predykaty = predykaty;
    }

    public ListaDrzew getTeza()
    {
        return teza;
    }

    public void setTeza(ListaDrzew teza)
    {
        this.teza = teza;
    }	
}

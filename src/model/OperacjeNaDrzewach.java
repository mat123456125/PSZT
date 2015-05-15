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
	
	private void wypisywanie(Vector<Literal> nowy)
	{
		System.out.print ("\nZawartosc nowej klauzuli to: ");
		
		for (int z = 0; z < nowy.size(); z++)
		{
			if (nowy.elementAt(z).isZnak())
			{
				System.out.print(" -");
			}
			else System.out.print (" ");
			
			System.out.print(nowy.elementAt(z).getZdanie());
		}
	}
	
	private void przypiszZawartosciDoKlauzul()
	{
		for (int x = 0; x < teza.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
		}
		for (int x = 0; x < predykaty.getKlauzule().size(); x++)
		{
			klauzule.add(predykaty.getKlauzule().elementAt(x));
		}
	}
        
        private void usunPowtarzanieAll()
        {
            Vector<Vector<Literal>> temp = new Vector<Vector<Literal>>();
            
            for (int x = 0; x < klauzule.size(); x++)
            {
                
                temp.add(usunPowtarzanieIOdwrotnosci(klauzule.get(x)));
                

            }
            klauzule = temp;

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
				
				if (lit1.getZdanie().equals(lit2.getZdanie()) && lit1.isZnak() == lit2.isZnak())
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
				
				if (lit1.getZdanie().equals(lit2.getZdanie()) && lit1.isZnak() != lit2.isZnak())
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
		
		for (int x = 0; x < pierwszy.size(); x++)
		{
			nowy.add(pierwszy.elementAt(x));
		}
		for (int x = 0; x < drugi.size(); x++)
		{
			nowy.add(drugi.elementAt(x));
		}
		
		nowy = usunPowtarzanieIOdwrotnosci(nowy);
				
		return nowy;
	}
	
	private void  wytwarzajNoweKlauzuleNaPodstawieObecnych()
	{
		for (int x = 0; x + 1 < klauzule.size(); x++)
		{
			for (int y = x + 1 ; y < klauzule.size(); y++)
			{
				
					Vector<Literal> nowy = tworzNowaKlauzule(klauzule.get(x), klauzule.get(y));
				
					wypisywanie(nowy);

					// if (sprawdzCzyDodacKlauzule (nowy) ) // sprawdz czy juz takiej klauzuli nie ma w bazie
					// {
					//		dodajemy nowa klauzule do bazy
					// }
			}
		}
	}
	
	public void Oblicz() throws IllegalAccessException
	{
                teza.setTeza(true);
                predykaty.wypelnijListe();
                teza.wypelnijListe();
                
                przypiszZawartosciDoKlauzul();
                usunPowtarzanieAll();
                for (int x = 0; x  < klauzule.size(); x++)
                {
                    wypisywanie(klauzule.get(x));
		}
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

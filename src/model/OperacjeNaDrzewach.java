package model;

import java.util.Vector;

public class OperacjeNaDrzewach
{
	private ListaDrzew predykaty = new ListaDrzew();
	private ListaDrzew teza = new ListaDrzew();
	private Vector<WezelDrzewa> klauzule = new Vector<WezelDrzewa>();
	
	public OperacjeNaDrzewach()
	{
		
	}
	
	public void przypiszZawartosciDoKlauzul()
	{
		for (int x = 0; x < teza.getPredykaty().size(); x++)
		{
			klauzule.add(teza.getPredykaty().elementAt(x));
		}
		for (int x = 0; x < predykaty.getPredykaty().size(); x++)
		{
			klauzule.add(teza.getPredykaty().elementAt(x));
		}
	}
	
	public WezelDrzewa tworzNowaKlauzule (WezelDrzewa pierwszy, WezelDrzewa drugi)
	{
		WezelDrzewa rodzic1 = pierwszy;
		WezelDrzewa rodzic2 = drugi;
		
		WezelDrzewa pomocniczy1 = pierwszy.getLewy();
		WezelDrzewa pomocniczy2 = drugi.getLewy();
		
		while (pomocniczy1 != null)
		{
			while (pomocniczy2 != null)
			{
				// if (inne znaki i ta sama zawartosc)
				//{
					// tworzenie nowej klauzuli
				
					
				
				
				
				// WezelDrzewa nowy = new WezelDrzewa();
				
					// return nowy;
				//}
				
				if (rodzic2.getPrawy().getSpojnik() == 1) // oznacza ze tam jest v
				{
					pomocniczy1 = rodzic2.getPrawy().getLewy();
					rodzic2 = rodzic2.getPrawy();
				}
				else pomocniczy2 = rodzic2.getPrawy();
			}
			
			if (rodzic1.getPrawy().getSpojnik() == 1) // oznacza ze tam jest v
			{
				pomocniczy1 = rodzic1.getPrawy().getLewy();
				rodzic1 = rodzic1.getPrawy();
			}
			else pomocniczy1 = rodzic1.getPrawy();
			
			rodzic2 = drugi;
			pomocniczy2 = drugi.getLewy();
		}
		
		// oznacza ze przeszlismy wszystko i nie tworzy sie nic wiec:
				
		return null;
	}
	
	public void  wytwarzajNoweKlauzuleNaPodstawieObecnych()
	{
		for (int x = 0; x + 1 < klauzule.size(); x++)
		{
			for (int y = x + 1 ; y < klauzule.size(); y++)
			{
					WezelDrzewa nowy = tworzNowaKlauzule(klauzule.get(x), klauzule.get(y));
				
					// if (sprawdzCzyDodacKlauzule (nowy) ) // sprawdz czy juz takiej klauzuli nie ma w bazie
					// {
					//		dodajemy nowa klauzule do bazy
					// }
			}
		}
	}
	
	public void Oblicz()
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

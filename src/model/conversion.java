package model;

import javax.swing.tree.TreeNode;

public class conversion
{

    public static WezelDrzewa copyTree(WezelDrzewa m)
    {
        if (m == null)
            return m;
        WezelDrzewa nowy = new WezelDrzewa(m);
        nowy.setLewy(copyTree(m.getLewy()));
        nowy.setPrawy(copyTree(m.getPrawy()));
        return nowy;
    }
    
    public static void sprawdzNegacje(WezelDrzewa m)
    {
        if (m == null)
            return;
        if (m.isZnak())
        {
            if (m.getSpojnik() == 1)
            {
                m.setSpojnik(2);
            } else if (m.getSpojnik() == 2)
            {
                m.setSpojnik(1);
            }
            else if(m.getSpojnik() == 0)
            {
                return;
            }
            m.setZnak(false);
            zaneguj(m.getLewy());
            zaneguj(m.getPrawy());

        } 
        else
        {
            sprawdzNegacje(m.getLewy());
            sprawdzNegacje(m.getLewy());

        }
    }

    
    public static void zaneguj(WezelDrzewa wezel)
    {
// alternatywa - 2
// koniunkcja - 1

        if (wezel == null)
        {
            return;
        }

        if (wezel.getSpojnik() == 0)
        {
            wezel.setZnak(!wezel.isZnak());
            return;
        }

        if (wezel.isZnak() == true) // true oznacza ze jest -
        {
            wezel.setZnak(false);
            sprawdzNegacje(wezel.getLewy());
            sprawdzNegacje(wezel.getPrawy());
            return;
        } else if (wezel.isZnak() == false)
        {
            wezel.setSpojnik((wezel.getSpojnik() % 2) + 1);
        }

        if (wezel.getLewy() != null)
        {
            zaneguj(wezel.getLewy());
        }


        if (wezel.getPrawy() != null)
        {
            zaneguj(wezel.getPrawy());
        }

        return;
    }

 

    public static void IMPEQ(WezelDrzewa m)
    {
        if (m != null)
        {
            if (m.getSpojnik() == 3)
            {									//kopia do ListaDrzew
                Negation(m.getLewy());			//funkcja g�owna wywolujace wszystko
                m.setSpojnik(2);
            } else if (m.getSpojnik() == 4)
            {
                WezelDrzewa mpl = new WezelDrzewa();
                WezelDrzewa mpr = new WezelDrzewa();
                mpl.setSpojnik(2);
                mpr.setSpojnik(2);
                mpl.setLewy(copyTree(m.getLewy()));
                mpl.setPrawy(copyTree(m.getPrawy()));
                Negation(mpl.getPrawy());

                mpr.setLewy(copyTree(m.getLewy()));
                mpr.setPrawy(copyTree(m.getPrawy()));
                Negation(mpr.getLewy());
                m.setSpojnik(1);
                m.setLewy(mpl);
                m.setPrawy(mpr);
            } 

             else if (m.getSpojnik() == 0)
            {
                return;
            }
            if (m.getLewy() != null)
                IMPEQ(m.getLewy());
            if (m.getPrawy() != null)
                IMPEQ(m.getPrawy());
        }
    }
	
	public static void Negation(WezelDrzewa m)
	{
            if(m == null)return;
            m.setZnak(!m.isZnak());
	
	}

}

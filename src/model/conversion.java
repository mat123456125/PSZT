package model;

import javax.swing.tree.TreeNode;

public class conversion {
	
	public static void Negation(WezelDrzewa m)
	{//Zdanie to Literal
		if (m.getSpojnik()==0)
		{
			m.setZnak(true);
		}
		else if(m.getSpojnik()==2)
		{
			m.setSpojnik(1);
			Negation(m.getLewy());
			Negation(m.getPrawy());
		}
		else if(m.getSpojnik()==1)
		{ // Klauzula to wektor literalow
			m.setSpojnik(2);
			Negation(m.getLewy()); 
			Negation(m.getPrawy());
		}
		else if(m.getSpojnik()==3)
		{
			m.setSpojnik(1);
			Negation(m.getPrawy());
		}
		else if(m.getSpojnik()==4)
		{
			WezelDrzewa ml = new WezelDrzewa();
			WezelDrzewa mp = new WezelDrzewa();
			ml.setLewy(m.getLewy());
			ml.setPrawy(m.getPrawy());
			mp.setLewy(m.getLewy());
			mp.setPrawy(m.getPrawy());
			ml.setSpojnik(2);
			mp.setSpojnik(2);
			m.setZnak(true);;
			Negation(mp.getLewy());
			Negation(mp.getPrawy());
		}
	}
	
	
	public static int RedKON(WezelDrzewa m,WezelDrzewa mp,int str)
	{//mp to rodzic m - do poprawy str czy lewo czy prawo
		if ((m.getSpojnik()==2)&&(str == 2))
		{
			if (mp!=null)
			{
				if (mp.getSpojnik()==2)
				{
					WezelDrzewa mpr = new WezelDrzewa();
				
					//mpr.setParent(mp);
					mpr.setSpojnik(2);
					mpr.setPrawy(mp.getPrawy());
					mpr.setLewy(m.getPrawy());
					m.setPrawy(mp.getPrawy());
					m.setSpojnik(2);
					mp.setPrawy(mpr);
				}
					
				mp.setSpojnik(1);
				return 1;
				//SepKON();
			}
		}
			
		else if ((m.getSpojnik()==2)&&(str == 1))
		{
			if (mp!=null)
			{
				if (mp.getSpojnik()==2)
				{
					WezelDrzewa mpr = new WezelDrzewa();
				
					//mpr.setParent(mp);
					mpr.setSpojnik(2);
					mpr.setPrawy(m.getLewy());
					mpr.setLewy(mp.getLewy());
					m.setLewy(mp.getLewy());
					m.setSpojnik(2);
					mp.setLewy(mpr);
				}
				
				mp.setSpojnik(1);
				return 2;
				//SepKON();
				
			}
			
		}
		return 0;
	}
	
	public static int MassiveREDKON(WezelDrzewa m)
	{
		int st = 0;
		if(m.getLewy()!=null)
		{
			st = MassiveREDKON(m.getLewy()); 
		}
		else if(m.getPrawy()!=null)
		{
			st = MassiveREDKON(m.getPrawy());	
		}
		if(st == 1)  st = RedKON(m.getLewy(),m,1);
		else if(st ==2) st = RedKON(m.getPrawy(),m,2);
		return st;
	}
	
	
	public static void IMPEQ(WezelDrzewa m)
	{
		if(m != null)
		{
			if (m.getSpojnik()==3)
			{//kopia do ListaDrzew
				Negation(m.getLewy());//funkcja g�owna wywolujace wszystko 
				m.setSpojnik(2);
			}
	
		else if(m.getSpojnik()==4)
		{
		 	WezelDrzewa mpl = new WezelDrzewa();
			WezelDrzewa mpr = new WezelDrzewa();
			mpl.setSpojnik(2);
			mpr.setSpojnik(2);
			mpl.setLewy(m.getLewy());
			mpl.setPrawy(m.getPrawy());
			Negation(mpl.getPrawy());
			
			mpr.setLewy(m.getLewy());
			mpr.setPrawy(m.getPrawy());
			Negation(mpl.getLewy());
			m.setSpojnik(1);
			
		}
		
		else if(m.getSpojnik()==0)
		{
			return;
		}
		if (m.getLewy() != null) IMPEQ(m.getLewy());
		if (m.getPrawy() != null) IMPEQ(m.getPrawy());
	}
}
	
	
	public void CheckTAUT(WezelDrzewa m)
	{
		
	}
	
	public void CheckFalseSTAT(WezelDrzewa m)
	{
		
	}
        
        public WezelDrzewa copyTree(WezelDrzewa m)
        {
            if(m == null) return m;
            WezelDrzewa nowy = new WezelDrzewa(m);
            nowy.setLewy(copyTree(m.getLewy()));
            nowy.setLewy(copyTree(m.getPrawy()));
            return nowy;
        }
	
}

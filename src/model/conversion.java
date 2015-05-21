package model;

import javax.swing.tree.TreeNode;

public class conversion {
	public void Negation(WezelDrzewa m){//Zdanie to Literal
		if (m.getSpojnik()==0){
			m.setZnak(true);
		}
		else if(m.getSpojnik()==1){
			m.setSpojnik(2);
			this.Negation(m.getLewy());
			this.Negation(m.getPrawy());
		}
		else if(m.getSpojnik()==2){ // Klauzula to wektor literalow
			m.setSpojnik(1);
			this.Negation(m.getLewy()); 
			this.Negation(m.getPrawy());
		}
		else if(m.getSpojnik()==3){
			m.setSpojnik(2);
			this.Negation(m.getPrawy());
		}
		else {
			WezelDrzewa ml = new WezelDrzewa();
			WezelDrzewa mp = new WezelDrzewa();
			ml.setLewy(m.getLewy());
			ml.setPrawy(m.getPrawy());
			mp.setLewy(m.getLewy());
			mp.setPrawy(m.getPrawy());
			ml.setSpojnik(1);
			mp.setSpojnik(1);
			m.setZnak(true);;
			this.Negation(mp.getLewy());
			this.Negation(mp.getPrawy());
		}
	}
	public int RedKON(WezelDrzewa m,WezelDrzewa mp,int str){//mp to rodzic m - do poprawy str czy lewo czy prawo
		if ((m.getSpojnik()==2)&&(str == 2)) {
			if (mp!=null){
				if (mp.getSpojnik()==1){
				WezelDrzewa mpr = new WezelDrzewa();
				
				//mpr.setParent(mp);
				mpr.setSpojnik(1);
				mpr.setPrawy(mp.getPrawy());
				mpr.setLewy(m.getPrawy());
				m.setPrawy(mp.getPrawy());
				m.setSpojnik(1);
				mp.setPrawy(mpr);
				}
				mp.setSpojnik(2);
				return 2;
			//SepKON();
			}
		}
			
		else if ((m.getSpojnik()==2)&&(str == 1)) {
			if (mp!=null){
				if (mp.getSpojnik()==1){
				WezelDrzewa mpr = new WezelDrzewa();
				
				//mpr.setParent(mp);
				mpr.setSpojnik(1);
				mpr.setPrawy(m.getLewy());
				mpr.setLewy(mp.getLewy());
				m.setLewy(mp.getLewy());
				m.setSpojnik(1);
				mp.setLewy(mpr);
				}
				mp.setSpojnik(2);
				return 1;
			//SepKON();
			}
			
		}
		
	}
	
	public int MassiveREDKON(WezelDrzewa m){
		int st = 0;
		if(m.getLewy()!=null){
			st = MassiveREDKON(m.getLewy()); 
		}
		else if(m.getPrawy()!=null){
			st = MassiveREDKON(m.getPrawy());
			
		}
		 if(st == 1)  st = RedKON(m.getLewy(),m,1);
		 else if(st ==2) st = RedKON(m.getPrawy(),m,2);
		return st;
	}
	
	
	public void IMPEQ(WezelDrzewa m){
		if(m != null){
		if (m.getSpojnik()==3){//kopia do ListaDrzew
			Negation(m.getLewy());//funkcja g�owna wywolujace wszystko 
			m.setSpojnik(1);
			
		}else if(m.getSpojnik()==4){
		 	WezelDrzewa mpl = new WezelDrzewa();
			WezelDrzewa mpr = new WezelDrzewa();
			mpl.setSpojnik(1);
			mpr.setSpojnik(1);
			mpl.setLewy(m.getLewy());
			Negation(mpl.getPrawy());
			mpl.setPrawy(m.getPrawy());
			mpr.setLewy(m.getLewy());
			mpr.setPrawy(m.getPrawy());
			Negation(mpl.getPrawy());
			m.setSpojnik(2);
			
		}else if(m.getSpojnik()==0){
			return;
		}
		if (m.getLewy() != null) this.IMPEQ(m.getLewy());
		if (m.getPrawy() != null) this.IMPEQ(m.getPrawy());
		
	}
}
	
	public void SepKON(WezelDrzewa m){
			
	}
	
	public void CheckTAUT(TreeNode m){
		if 
		
	}
	
	public void CheckFalseSTAT(WezelDrzewa m){
		
	}
	
}

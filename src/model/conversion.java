package conversion;

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
	public void RedKON(TreeNode m){
		if (m.getSpojnik()==2) {
			if (m.getParent()){
				if (m.getParent().getspojnik==1){
				WezelDrzewa mpr = new WezelDrzewa();
				mpr.setParent(m.getParent());
				mpr.setSpojnik(1);
				mpr.setChildAt(0,m.getChildAt(1));
				mpr.setChildAt(1,m.getParent().getChildAt(1));
				m.setSpojnik(1);
				}
				m.getParent().setspojnik(2);
				
			
			SepKON();
			}
		}
			
		else {
			
		}
		
	}
	
	public void IMPEQ(WezelDrzewa m){
		if(m){
		if (m.getSpojnik()==3){//kopia do ListaDrzew
			Negation(m.getLewy());//funkcja g³owna wywolujace wszystko 
			m.setSpojnik(1);
			
		}else if(m.getspojnik()==4){
		 	WezelDrzewa mpl = new WezelDrzewa();
			WezelDrzewa mpr = new WezelDrzewa();
			mpl.setSpojnik(1);
			mpr.setSpojnik(1);
			mpl.setLewy(m.) = m.getChildAt(0);
			Negation(mpl.GetChildAt(0));
			mpl.setChildAt(1) = m.getChildAt(1);
			mpr.setChildAt(0) = m.getChildAt(0);
			mpr.setChildAt(1) = m.getChildAt(1);
			Negation(mpl.GetChildAt(1));
			m.setspojnik(2);
			
		}else if(m.getspojnik()==0){
			return;
		}
		if (m.getChildAt(0)) IMPEQ(m.getChildAt(0));
		if(m.getChildAt(1)) IMPEQ(m.getChildAt(1));
		
	}
}
	
	public SepKON(TreeNode m){
		
	}
	
	public CheckTAUT(TreeNode m){
		
	}
	
	public CheckFalseSTAT(TreeNode m){
		
	}
	
}

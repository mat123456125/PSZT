package conversion;

import javax.swing.tree.TreeNode;

public class conversion {
	public void Negation(TreeNode m){
		if (m.getspojnik()==0){
			m.setznak(1);
		}
		else if(m.getspojnik()==1){
			m.setznak(2);
			this.Negation(m.getChildAt(0));
			this.Negation(m.getChildAt(1));
		}
		else if(m.getspojnik()==2){
			m.setznak(1);
			this.Negation(m.getChildAt(0));
			this.Negation(m.getChildAt(1));
		}
		else if(m.getspojnik()==3){
			m.setznak(2);
			this.Negation(m.getChildAt(1));
		}
		else {
			ml = new TreeNode();
			mp = new TreeNode();
			ml.setChildAt(0) = m.getChildAt(0);
			ml.setChildAt(1) = m.getChildAt(1);
			mp.setChildAt(0) = m.getChildAt(0);
			mp.setChildAt(1) = m.getChildAt(1);
			ml.setznak(1);
			mp.setznak(1);
			m.setznak(2);
			this.Negation(mp.getChildAt(0));
			this.Negation(mp.getChildAt(1));
		}
	}
	public void RedKON(TreeNode m){
		if (m.getspojnik()==2) {
			if (m.getParent()){
				if (m.getParent().getspojnik==1){
				mpr = new TreeNode();
				mpr.setParent(m.getParent());
				mpr.setspojnik(1);
				mpr.setChildAt(0,m.getChildAt(1));
				mpr.setChildAt(1,m.getParent().getChildAt(1));
				m.setspojnik(1);
				}
				m.getParent().setspojnik(2);
				
			
			SepKON();
			}
		}
			
		else {
			
		}
		
	}
	
	public void IMPEQ(TreeNode m){
		if(m){
		if (m.getspojnik()==3){
			Negation(m.getChildAt(0));
			m.setspojnik(1);
			
		}else if(m.getspojnik()==4){
			mpl = new TreeNode();
			mpr = new TreeNode();
			mpl.setspojnik(1);
			mpr.setspojnik(1);
			mpl.setChildAt(0) = m.getChildAt(0);
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

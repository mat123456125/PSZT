package model;

public class WezelDrzewa {

	private WezelDrzewa lewy;
	private WezelDrzewa prawy;
	private int spojnik; // 0 brak spojnika | 1 oznacza alternatywe | 2 oznacza koniunkcja | 3 implikacja | 4 rownowaznosc | -1 nawias zamkniety | 5 nawias otwarty
	private boolean znak; // false oznacza negacje | true oznacza twierdzenie
        private String zdanie;
	
	public WezelDrzewa()
	{
		
	}
        public WezelDrzewa(WezelDrzewa wz)
	{
            this.spojnik = wz.spojnik;
            this.zdanie = wz.zdanie;
            this.znak = wz.znak;
		
	}
        
        
        public WezelDrzewa(String zd)
	{
		zdanie = zd;
                spojnik = 0;
                znak = false;
	}
        public WezelDrzewa(int sp)
	{
		
                spojnik = sp;
                znak = false;
	}
	
	

	public WezelDrzewa getLewy() {
		return lewy;
	}

	public void setLewy(WezelDrzewa lewy) {
		this.lewy = lewy;
	}

	public WezelDrzewa getPrawy() {
		return prawy;
	}

	public void setPrawy(WezelDrzewa prawy) {
		this.prawy = prawy;
	}

	public int getSpojnik() {
		return spojnik;
	}

	public void setSpojnik(int spojnik) {
		this.spojnik = spojnik;
	}

	public boolean isZnak() {
		return znak;
	}

	public void setZnak(boolean znak) {
		this.znak = znak;
	}

	public String getZdanie() {
		return zdanie;
	}

	public void setZdanie(String zdanie) {
		this.zdanie = zdanie;
	}
	
	
	
	
}

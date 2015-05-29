/*

 */

package model;

/**
 *
 * 
 */
public class Literal {
    
    
    private boolean znak; // true oznacza negacje | false oznacza twierdzenie
    private String zdanie;

    public boolean isZnak()
    {
        return znak;
    }

    public void setZnak(boolean znak)
    {
        this.znak = znak;
    }

    public String getZdanie()
    {
        return zdanie;
    }

    public void setZdanie(String zdanie)
    {
        this.zdanie = zdanie;
    }

    public Literal(boolean znak, String zdanie)
    {
        this.znak = znak;
        this.zdanie = zdanie;
    }
    public String wypisz()
    {
        String temp = new String();
        if(znak) temp = "-";
        temp = temp + zdanie;
        return temp;
        
    }
}

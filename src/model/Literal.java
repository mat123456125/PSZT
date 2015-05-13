/*

 */

package model;

/**
 *
 * 
 */
class Literal {
    
    
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
        
     
}

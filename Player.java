/**
 * This class represents a player.
 * This class contains the player’s name (String), number of hits (int), and number of errors (int).
*/

public class Player{
    private String name;
    private int numHits;
    private int numErrors;

    /**
     * This is the constructor of the class
     * @param name
     *  a String
     * @param numHits
     *  an int
     * @param numErrors
     *  an int
     */
    public Player(String name, int numHits, int numErrors){
        this.name = name;
        this.numHits = numHits;
        this.numErrors = numErrors;
    }

    /**
     * This is the default constructor of the class
     */
    public Player(){}

    /**
     * This method returns the name of the player.
     * @return
     *  a String
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns the number of hits
     * @return
     *  an int
     */
    public int getNumHits(){
        return numHits;
    }

    /**
     * This method returns the number of errors
     * @return
     *  an int
     */
    public int getNumErrors(){
        return numErrors;
    }

    /**
     * This method sets the name of the player
     * @param name
     *  a String
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * This method sets the number of hits
     * @param numHits
     *  an int
     * @throws IllegalArgumentException
     *  if number of hits is less than 0
     */
    public void setNumHits(int numHits) throws IllegalArgumentException {
        if(numHits < 0){
            throw new IllegalArgumentException("Number of hits cannot be less than 0.");
        }
        this.numHits = numHits;
    }

    /**
     * This method sets the number of errors
     * @param numHits
     *  an int
     * @throws IllegalArgumentException
     *  if number of errors is less than 0
     */
    public void setNumErrors(int numErrors) throws IllegalArgumentException {
        if(numErrors < 0){
            throw new IllegalArgumentException("Number of errors cannot be less than 0.");
        }
        this.numErrors = numErrors;
    }

    /**
     * This method returns a clone of the player.
     */
    public Object clone(){
        Player clonedPlayer = new Player(getName(), getNumHits(), getNumErrors());
        return clonedPlayer;
    }

    /**
     * This method returns a printable representation of the player and his statistics (hits and errors).
     */
    public String toString(){
        String playerStats = String.format("%-19s%-11d%-8d", name, numHits, numErrors);
        return playerStats;
    }
}

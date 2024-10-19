/**
 * This Team class stores all Player objects that belong to a particular team.
*/

public class Team {
    public static final int MAX_PLAYERS = 40;
    public Player[] player = new Player[MAX_PLAYERS];
    public int size = 0;

    /**
     * This is the default constuctor of the class.
     * Brief: Construct an instance of the Team class with no Player objects in it.
     * Postconditions: This Team has been initialized to an empty list of Players.
     */
    public Team(){}

    /**
     * Generate a clone of this Team.
     * @return
     *  The return value is a clone of this Team.
     */
    public Object clone(){
        Team clonedTeam = new Team();
        for(int i = 0 ; i < size(); i++){
            Player currentPlayer = (Player) this.getPlayer(i+1).clone();
            try {
                clonedTeam.addPlayer(currentPlayer, i+1);
            } catch (FullTeamException e) {
                System.out.println("Team overflowed, cannot clone.");
            }
        }
        return clonedTeam;
    }

    /**
     * Brief: Compare this Team to another object for equality.
     * Parameters: obj - an object to which this Team is compared
     * @param obj
     *  an Object
     * 
     * Returns: A return value of true indicates that obj refers to a Team object with the same Players in the same order as this Team. 
     * Otherwise, the return value is false. If obj is null or it is not a Team object, then the return value is false.
     */
    public boolean equals(Object obj){
        if(!(obj instanceof Team) || obj == null){
            return false;
        }
        Team team = (Team) obj;
        if(team.size() != this.size()){
            return false;
        }
        boolean flag = true;
        for(int i = 0; i < size(); i++){
            Player TeamPlayer = team.getPlayer(i+1);
            Player thisPlayer = this.getPlayer(i+1);
            if(!TeamPlayer.getName().equals(thisPlayer.getName())){
                flag = false;
            }
            if(TeamPlayer.getNumHits() != thisPlayer.getNumHits()){
                flag = false;
            }
            if(TeamPlayer.getNumErrors() != thisPlayer.getNumErrors()){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Brief: Determines the number of Players currently in this Team.
     * Preconditions: This Team object has been instantiated.
     * @return
     *  The number of Players in this Team.
     */
    public int size(){
        return size;
    }

    /**
     * Brief: Adds a Player to the team at the indicated position in the lineup.
     * Preconditions: This Team object has been instantiated 1 ≤ position ≤ players_currently_in_team + 1.
     * The number of Player objects in this Team is less than MAX_PLAYERS.
     * Postconditions: The new Player is now stored at the desired position in the Team. 
     * All Players that were originally in positions greater than or equal to position are moved back one position.
     * @param p
     *  the new Player object to add to this Team.
     * @param position
     *  the position in the lineup where the Player will be inserted.
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range.
     * @throws FullTeamException
     *  Indicates that there is no more room inside of the Team to store the new Player object.
     */
    public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException{
        if(size() >= MAX_PLAYERS){
            throw new FullTeamException("There is no more room inside of the Team to store the new Player object.");
        }
        if(position < 1 || position > size() + 1){
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
        if(size() != 0){
            for(int i = size(); i > position-1; i--){
                player[i]=player[i-1];
            }
        }
        player[position-1] = p;
        size++;
    }

    /**
     * Removes a Player from the team at the indicated position in the lineup.
     * Preconditions: This Team object has been instantiated 1 ≤ position ≤ players_currently_in_team .
     * Postconditions: The Player at the desired position in the Team has been removed. 
     * All Players that were originally in positions greater than or equal to position are moved forward one position.
     * @param position
     *  the position in the lineup from which the Player is to be removed.
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range.
     */
    public void removePlayer(int position) throws IllegalArgumentException{
        if(position < 1 || position > size() + 1){
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
        for(int i = position-1; i < size()-1; i++){
            player[i] = player[i+1];
        }
        player[size()-1] = null;
        size--;
    }

    /**
     * Returns a reference to a player in the lineup at the indicated position.
     * @param position
     *  The position in the lineup from which the Player is to be retrieved.
     * @return
     *  The Player from the given index.
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range.
     */
    public Player getPlayer(int position) throws IllegalArgumentException{
        if(position < 1 || position > size() + 1){
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
        return player[position-1];
    }

    /**
     * Return the Player with the best value in the given statistic ("hits" or "errors").
     * 
     * @param stat
     *  either "hits" or "errors".
     * @return
     *  The Player with the best stat.
     * @throws IllegalArgumentException
     *  Indicates that indicated stat was neither "hits" nor "errors".
     */
    public Player getLeader(String stat) throws IllegalArgumentException{
        if(!stat.equalsIgnoreCase("hits") && !stat.equalsIgnoreCase("errors")){
            throw new IllegalArgumentException("stat was neither \"hits\" nor \"errors\".");
        }
        Player bestPlayer = player[0];
        if(stat.equalsIgnoreCase("hits")){
            for(int i = 1; i < size(); i++){
                if(player[i].getNumHits() > bestPlayer.getNumHits()){
                    bestPlayer = player[i];
                }
            }
        }
        if(stat.equalsIgnoreCase("errors")){
            for(int i = 1; i < size(); i++){
                if(player[i].getNumErrors() < bestPlayer.getNumErrors()){
                    bestPlayer = player[i];
                }
            }
        }
        return bestPlayer;
    }

    /**
     * Prints a neatly formatted table of each Player in the Team on its own line with its position number as shown in the sample output.
     */
    public void printAllPlayers(){
        System.out.println(toString());
    }


    /**
     * Gets the String representation of this Team object, 
     * which is a neatly formatted table of each Player in the Team on its own line with its position number as shown in the sample output.
     * @return
     *  The String representation of this Team object.
     */
    public String toString(){
        String table = "Player#   Name               Hits       Errors\n";
        table += "------------------------------------------------\n";
        for(int i = 0; i < size(); i++){
            int position = i+1;
            table += String.format("%-10d", position);
            table += player[i] + "\n";
        }
        table = table.substring(0, table.length()-2);
        return table;
    }

}

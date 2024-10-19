/**
 * This class tests the methods of the Team class and allows the user to manipulate 5 Team objects by performing operations on it.
*/

import java.util.Scanner;
public class TeamManager {
    public static final int MAX_TEAMS = 5;

    /**
     * This is the main method
     * @param args
     *  argument
     */
    public static void main(String[] args){
        Team[] arrOfTeams = new Team[5];
        for(int i = 0; i < arrOfTeams.length; i++){
            arrOfTeams[i] = new Team(); 
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to TeamManager!\n");
        int selectedTeam = 0; // This is team 1
        System.out.println("Team 1 is currently selected.");
        printMenu();
        String userInput = scan.nextLine();
        userInput = userInput.trim();
        System.out.println();

        while(!userInput.equalsIgnoreCase("Q")){
            if(userInput.equalsIgnoreCase("A")){
                System.out.print("Enter the player's name: ");
                String playerName = scan.nextLine();
                System.out.print("Enter the number of hits: ");
                int playerHits = scan.nextInt();
                System.out.print("Enter the number of errors: ");
                int playerErrors = scan.nextInt();
                System.out.print("Enter the position: ");
                int playerPosition = scan.nextInt();
                scan.nextLine();
                Player newPlayer = new Player(playerName, playerHits, playerErrors);
                try {
                    arrOfTeams[selectedTeam].addPlayer(newPlayer, playerPosition);
                    System.out.println("Player added: " + playerName + " - " + playerHits + " hits, " + playerErrors + " errors");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid position for adding the new player.");
                } catch (FullTeamException e ) {
                    System.out.println("Invalid position for adding the new player: Team is full. A team cannot have more than 40 players.");
                }
            }
            else if(userInput.equalsIgnoreCase("G")){
                System.out.print("Enter the position: ");
                int position = scan.nextInt();
                scan.nextLine();
                System.out.println();
                Team currentTeam = arrOfTeams[selectedTeam];
                try {
                    Player currentPlayer = currentTeam.getPlayer(position);
                System.out.println(currentPlayer.getName() + " - " + currentPlayer.getNumHits() + " hits, " + currentPlayer.getNumErrors() + " errors");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid position for getting player stat.");
                }
            }
            else if(userInput.equalsIgnoreCase("L")){
                System.out.print("Enter the stat: ");
                String stat = scan.nextLine();
                try {
                    Player leader = arrOfTeams[selectedTeam].getLeader(stat);
                    System.out.println();
                    System.out.println("Leader in " + stat + ": " + leader.getName() + " - " + leader.getNumHits() + " hits, " + leader.getNumErrors() + " errors");
                } catch (IllegalArgumentException e) {
                    System.out.println("No such statistic.");
                }
            }
            else if(userInput.equalsIgnoreCase("R")){
                System.out.print("Enter the position: ");
                int position = scan.nextInt();
                scan.nextLine();
                try {
                    Player removedPlayer = arrOfTeams[selectedTeam].getPlayer(position);
                    arrOfTeams[selectedTeam].removePlayer(position);
                    System.out.println("Player Removed at position " + position);
                    System.out.println();
                    System.out.println(removedPlayer.getName() + " has been removed from the team.");
                } catch (IllegalArgumentException e) {
                    System.out.println("No player at position " + position + " to remove.");
                }
            }
            else if(userInput.equalsIgnoreCase("P")){
                System.out.print("Select team index: ");
                int index = scan.nextInt() - 1;
                scan.nextLine();
                System.out.println();
                arrOfTeams[index].printAllPlayers();
            }
            else if(userInput.equalsIgnoreCase("S")){
                System.out.println("There are " + arrOfTeams[selectedTeam].size() + " players(s) in the current Team. ");
            }
            else if(userInput.equalsIgnoreCase("T")){
                System.out.print("Enter team index to select: ");
                int index = scan.nextInt(); 
                scan.nextLine();
                System.out.println();
                if(index > MAX_TEAMS || index < 1){
                    System.out.println("Invalid index for team.");
                }
                else{
                    selectedTeam = index - 1;
                    System.out.println("Team " + index + " has been selected.");
                }
            }
            else if(userInput.equalsIgnoreCase("C")){
                System.out.print("Select team to clone from: ");
                int teamToCloneFrom = scan.nextInt();
                Team clonedTeam = (Team) arrOfTeams[teamToCloneFrom - 1].clone();
                System.out.print("Select team to clone to: ");
                int teamToCloneTo = scan.nextInt();
                scan.nextLine();
                System.out.println();
                arrOfTeams[teamToCloneTo - 1] = clonedTeam;
                System.out.println("Team " + teamToCloneFrom + " has been copied to team " + teamToCloneTo + ".");
            }
            else if(userInput.equalsIgnoreCase("E")){
                System.out.print("Select first team index: ");
                int firstTeamIndex = scan.nextInt();
                System.out.print("Select second team index: ");
                int secondTeamIndex = scan.nextInt();
                scan.nextLine();
                System.out.println();
                if(arrOfTeams[firstTeamIndex - 1].equals(arrOfTeams[secondTeamIndex - 1])){
                    System.out.println("These teams are equal.");
                }
                else{
                    System.out.println("These teams are not equal.");
                }
            }
            else if(userInput.equalsIgnoreCase("U")){
                System.out.print("Enter the player to update: ");
                String playerName = scan.nextLine();
                System.out.print("Enter stat to update: ");
                String stat = scan.nextLine();
                System.out.print("Enter the new number of " + stat + ": ");
                int newNum = scan.nextInt();
                scan.nextLine();
                int positionOfPlayer = 0;
                try {
                    for(int i = 0; i < arrOfTeams[selectedTeam].size(); i++){
                        Player thisPlayer = arrOfTeams[selectedTeam].getPlayer(i+1);
                        if(thisPlayer.getName().equalsIgnoreCase(playerName)){
                            positionOfPlayer = i + 1;
                        }
                    }
                    System.out.println();
                    if(positionOfPlayer == 0){
                        System.out.println("Player not found.");
                    }
                    else{
                        Player playerToUpdate = arrOfTeams[selectedTeam].getPlayer(positionOfPlayer);
                        if(stat.equalsIgnoreCase("hits")){
                            playerToUpdate.setNumHits(newNum);
                        }
                        else if(stat.equalsIgnoreCase("errors")){
                            playerToUpdate.setNumErrors(newNum);
                        }
                        System.out.println("Updated " + playerToUpdate.getName() + " " + stat);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid update error.");
                }
                
            }
            else if(!("AaGgLlRrPpSsTtCcEeUu".contains(userInput))){
                System.out.println("Invalid menu option");
                System.out.println("The string you entered does not match the ones on the menu. Try again...");
            }
            printMenu();
            userInput = scan.nextLine();
            userInput = userInput.trim();
            System.out.println();
        }
        System.out.println("Program terminating normally...");
    }

    /**
     * This method prints the menu.
     */
    public static void printMenu(){
        System.out.println();
        System.out.println("A) Add Player.");
        System.out.println("G) Get Player stats.");
        System.out.println("L) Get leader in a stat.");
        System.out.println("R) Remove a player.");
        System.out.println("P) Print all players.");
        System.out.println("S) Size of team.");
        System.out.println("T) Select team.");
        System.out.println("C) Clone team.");
        System.out.println("E) Team equals");
        System.out.println("U) Update stat.");
        System.out.println("Q) Quit.");
        System.out.print("\nSelect a menu option: ");
    }
}

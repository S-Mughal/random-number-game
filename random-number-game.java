[cc lang="java"]
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGame {

public static ArrayList players = new ArrayList<>();
public static int playerIndex=-1;

public RandomNumberGame(String name) {
this.name = name;
}

public RandomNumberGame() {
}

//average guesses
int averageGuess;
public int getAverageGuess() {
return averageGuess;
}
public void setAverageGuess(int averageGuess) {
this.averageGuess = averageGuess;
}

//totalGamesPlayed by the player
int totalGamesPlayed;
public int getTotalGamesPlayed() {
return totalGamesPlayed;
}
public void setTotalGamesPlayed(int totalGamesPlayed) {
this.totalGamesPlayed = totalGamesPlayed;
}

//Player Name
String name;
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}

//array with Guesses
int[] n;
public int[] getN() {
return n;
}
public void setN(int[] n) {
this.n = n;
}

//make sure the totalGamesPlayed is already updated
public int[] updateN(int[] a, int newGuess)
{
int[] x = new int[totalGamesPlayed];

for (int i=0; i<x.length; i++) { for (int j=0; j<a.length; j++) { x[j]=a[j]; } x[totalGamesPlayed-1] = newGuess; } return x; } int totalUsers; //stores total users int minN; //stores min range int maxN; //stores max range int interval; //stores interval int nGames=1; //stores number of games played boolean numberError = false; String userString; ArrayList usersRandNum = new ArrayList<>();
ArrayList win = new ArrayList<>();
ArrayList tries = new ArrayList<>();
ArrayList pName = new ArrayList<>();

Scanner myKeyBoard = new Scanner(System.in);
Random randNum = new Random();

public void updateGameData()
{
for (int i=0; i<pName.size(); i++) { int existingIndex = findExistingPlayer(pName.get(i)); if (existingIndex == -1) { //means it's a new player RandomNumberGame obj2 = new RandomNumberGame(); int []a= new int[1]; obj2.setName(pName.get(i)); players.add(obj2); playerIndex = findExistingPlayer(pName.get(i)); players.get(playerIndex).setName(pName.get(i)); players.get(playerIndex).setTotalGamesPlayed(1); players.get(playerIndex).setN(a); players.get(playerIndex).setN(players.get(playerIndex).updateN(a,tries.get(i))); } else { int[] prevRecords = players.get(existingIndex).getN(); players.get(existingIndex).totalGamesPlayed++; players.get(existingIndex).setN(players.get(existingIndex).updateN(prevRecords,tries.get(i))); } } } public static void main(String[] args) { RandomNumberGame driver = new RandomNumberGame(); System.out.println("Hello... Welcome to the Random Number Game"); driver.mainMenu(); } public void readFile() { try{ File file = new File("gameData.txt"); Scanner scanner = new Scanner(file); System.out.println("Previous Saved Game Progress Found!" + "\nLoading saved data...\n"); int temp; String s; while (scanner.hasNext()) { if (scanner.hasNextInt()) { temp = scanner.nextInt(); players.get(playerIndex).setTotalGamesPlayed((players.get(playerIndex).getTotalGamesPlayed()+1)); players.get(playerIndex).setN(players.get(playerIndex).updateN(players.get(playerIndex).getN(),temp)); }else { s = scanner.next(); RandomNumberGame obj = new RandomNumberGame(); players.add(obj); playerIndex++; players.get(playerIndex).setName(s); players.get(playerIndex).setTotalGamesPlayed(0); int[] a= new int[1]; players.get(playerIndex).setN(a); } } }catch (FileNotFoundException e) { System.err.println("No Previous Saved Data Found!" + "\nCreating new file..."); } } public void writeFile() { try { File fout = new File("gameData.txt"); FileOutputStream fos = new FileOutputStream(fout); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); for (int i=0; i<players.size(); i++) { bw.write(players.get(i).getName()+" "); int []a; for (int j=0; j<players.get(i).getN().length;j++) { a = players.get(i).getN(); bw.write(Integer.toString(a[j])+" "); } bw.newLine(); } bw.close(); }catch (IOException e) { System.err.println("Error! Could not save new game data."); } } public void testRead() { try{ File file = new File("gameData3.txt"); Scanner scanner = new Scanner(file); System.out.println("Previous Saved Game Progress Found!" + "\nLoading saved data...\n"); int temp; String s; while (scanner.hasNext()) { if (scanner.hasNextInt()) { temp = scanner.nextInt(); players.get(playerIndex).setTotalGamesPlayed((players.get(playerIndex).getTotalGamesPlayed()+1)); System.out.print(temp+"\t"); players.get(playerIndex).setN(players.get(playerIndex).updateN(players.get(playerIndex).getN(),temp)); }else { s = scanner.next(); RandomNumberGame obj = new RandomNumberGame(); players.add(obj); playerIndex++; players.get(playerIndex).setName(s); players.get(playerIndex).setTotalGamesPlayed(0); int[] a= new int[1]; players.get(playerIndex).setN(a); System.out.print("\n"+s+"\t"); } } }catch (FileNotFoundException e) { System.err.println("No Previous Saved Data Found!" + "\nCreating new file..."); } } public void mainMenu() { readFile(); System.out.println(); basicInfo(); getAllPlrNames(); setRandomNumbers(); playGame(); finalStanding(); } public void finalStanding() { System.out.println("Final Standing"); System.out.println("Name\t\tAverage Guess"); for (int i=0; i<players.size();i++) { System.out.print(players.get(i).getName()+"\t\t\t"+players.get(i).getAverageGuess()); System.out.println(); } } public void basicInfo() { System.out.println(); System.out.println("[Collecting data: BASIC GAME DATA]"); //getting total users do { try { System.out.print("How many users will be playing?: "); userString = myKeyBoard.nextLine(); totalUsers = Integer.parseInt(userString); numberError = true; }catch (Exception e) { System.err.print("ERROR: INVALID INPUT. Please enter a valid number.\n"); numberError = false; } }while (numberError != true); //getting minimum do { try { System.out.print("Enter a minimum possible value for random number: "); userString = myKeyBoard.nextLine(); minN = Integer.parseInt(userString); numberError = true; }catch (Exception e) { System.err.print("ERROR: INVALID INPUT. Please enter a valid number.\n"); numberError = false; } }while (numberError != true); //getting maximum do { try { System.out.print("Enter a maximum possible value for random number: "); userString = myKeyBoard.nextLine(); maxN = Integer.parseInt(userString); numberError = true; }catch (Exception e) { System.err.print("ERROR: INVALID INPUT. Please enter a valid number.\n"); numberError = false; } }while (numberError != true); interval = (maxN-minN)+1; } public int findExistingPlayer(String name) { int r=-1; //returns if no player found for (int i=0; i<players.size(); i++) { if (players.get(i).getName().equals(name)) r=i; } return r; } public void getAllPlrNames() { System.out.println(); System.out.println("[Collecting data: PLAYER NAMES]"); System.out.println("=> Please choose a unique name. " +
"\nExisting players: Please enter your previously used name.");
for (int i=0; i<totalUsers; i++) { String name; do { try { System.out.print("Enter the name of the player#"+(i+1)+": "); name = myKeyBoard.nextLine(); pName.add(name); numberError = true; }catch (Exception e) { System.err.print("ERROR: INVALID INPUT. Please enter a valid name."); numberError = false; } }while (numberError != true); } } public void setRandomNumbers() { for (int i=0; i<totalUsers; i++) { int randomNum = randNum.nextInt(interval)+minN; usersRandNum.add(randomNum); tries.add(0); win.add(false); } } public boolean checkAllCorrect() { boolean result = true; for (int i=0; i<totalUsers; i++) { if (win.get(i)==false) result=false; } return result; } public void scoreBoard() { System.out.println("\t\tScoreboard"); System.out.println(" #\tPlayer\t\tTotal Tries"); for (int i=0; i<totalUsers; i++) { System.out.print("["+(i+1)+"]\t"+pName.get(i)+"\t\t\t"+tries.get(i)); System.out.println(); } } public int findAverage(int[] a, int x ) { int average; int r=0; for (int i=0; i<a.length; i++) r+=a[i]; average = (r/x); return average; } public void updateAllAverage() { System.out.println("Ranking"); for (int i = 0; i < players.size(); i++) players.get(i).setAverageGuess(findAverage(players.get(i).getN(),players.get(i).getTotalGamesPlayed())); } public void playGame() { System.out.println("\n\n==== GAME#"+nGames+"===="); boolean quit = false; do { int previousTries; do { for (int i=0; i<totalUsers; i++) { int guess; if (win.get(i)==false) { do { try { int randTemp = usersRandNum.get(i); System.out.println(); System.out.print("["+pName.get(i)+"] enter a valid guess: "); userString = myKeyBoard.nextLine(); guess = Integer.parseInt(userString); if (guess == randTemp) { System.out.println("That's right!"); previousTries = tries.get(i); win.set(i,true); tries.set(i,(previousTries+1)); } else if (guess<randTemp) { System.out.println("Your guess is too low! Try again in next round"); previousTries = tries.get(i); win.set(i,false); tries.set(i,(previousTries+1)); } else if (guess>randTemp)
{
System.out.println("Your guess is too high! Try again in next round");

previousTries = tries.get(i);
win.set(i,false);
tries.set(i,(previousTries+1));
}

numberError = true;
}catch (Exception e)
{
System.err.print("ERROR: INVALID INPUT. Please enter a valid number.\n");
numberError = false;
}
}while (numberError != true);
}

}
}while (checkAllCorrect()!=true);

System.out.println();
scoreBoard();
System.out.println();

String input="";
do {
try {
System.out.print("Do you want to play again? [Y/N]: ");
input = myKeyBoard.nextLine();

numberError = true;
}catch (Exception e)
{
System.err.print("ERROR: INVALID INPUT. Please enter either \"y\" or \"n\" ");
numberError = false;
}
}while (numberError != true);

//updating game data in the multidimensional array
int allTries [][]= new int [totalUsers][nGames];
for (int i=0; i<totalUsers; i++) { allTries[i][nGames-1]=tries.get(i); } updateGameData(); writeFile(); updateAllAverage(); if (input.toUpperCase().equals("N")) { quit = true; } else if (input.toUpperCase().equals("Y")) { resetAll(); mainMenu(); } }while(quit !=true); } public void resetAll() { pName.clear(); usersRandNum.clear(); win.clear(); tries.clear(); } } [/cc]

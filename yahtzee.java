import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class yahtzee {

	static int counter = 1; //counter that controls the operation of the game for 10 rounds.
	static int score1 = 0;
	static int score2 = 0;
	static SingleLinkedList sll1 = new SingleLinkedList();
	static SingleLinkedList sll2 = new SingleLinkedList();
	static SingleLinkedList sll3 = new SingleLinkedList();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		
		 
		while (counter <= 10) { // gameloop

			int dice1 = rnd.nextInt(1,7); // randomly generated number for dice
			int dice2 = rnd.nextInt(1,7); // randomly generated number for dice
			int dice3 = rnd.nextInt(1,7); // randomly generated number for dice
			int dice4 = rnd.nextInt(1,7); // randomly generated number for dice
			int dice5 = rnd.nextInt(1,7); // randomly generated number for dice
			int dice6 = rnd.nextInt(1,7); // randomly generated number for dice
			System.out.println("TURN:" + counter);
			sll1.add(dice1); //adding the randomly generated number to sll.
			sll1.add(dice2);
			sll1.add(dice3);
			System.out.print("Player1: ");
			sll1.display(); //display operation for sll1
			yathzeeControl(sll1); // yathzee control function for sll1
			largeStraightControl(sll1); // large straight control operation for sll1
			System.out.println("                          Score1: " + score1);
			
			sll2.add(dice4); //adding the randomly generated number to sll.
			sll2.add(dice5);
			sll2.add(dice6);
			System.out.print("Player2: ");
			sll2.display(); //display operation for sll1
			yathzeeControl(sll2); // yathzee control function for sll1
			largeStraightControl(sll2); // large straight control operation for sll1
			System.out.println("                          Score2: " + score2);
			System.out.println(" ");
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println(" ");
			counter++;
		}
		String line;
		Scanner scanner = new Scanner(new File("HighScoreTable.txt"));
		while (scanner.hasNext()) {
			line = scanner.nextLine(); //Reading highscore.txt and adding each line to sll3.
			sll3.add(line);
		}
		scanner.close();
		sll3.sortList(); //eğer txt dosyasında boşluk varsa veya imleç son kişinin de bir alt satırına geçmişse yani boş satır oluşturulmuşsa hata veriyor. imleç son kişinin puanının yanında olmalı. 
		
		Scanner scanner2 = new Scanner(System.in); 
		System.out.println("GAME OVER!");
		
		if (score1 > score2 && score1 > sll3.find10thElement()) { //If score1 is higher than the score2 and score of the 10th element of sll3
			System.out.println("Winner is player 1");
			System.out.println("Please enter your name: ");
			String name = scanner2.next();  //Get the name of player1
			sll3.add(name + " " + score1);  //Adding name and score to sll3
			System.out.println("");
			BufferedWriter writer = new BufferedWriter(new FileWriter("HighScoreTable.txt",true));
			writer.newLine(); 
			writer.write(name + " " + score1); //adding player and score to txt
			writer.close();
		}
		if (score2 > score1 && score2 > sll3.find10thElement()) { //If score2 is higher than the score1 and score of the 10th element of sll3
			System.out.println("Winner is player 2");
			System.out.println("Please enter your name: ");
			String name = scanner2.next(); //Get the name of player2
			sll3.add(name + " " + score2); // adding the name and score to sll3
			System.out.println("");
			BufferedWriter writer = new BufferedWriter(new FileWriter("HighScoreTable.txt",true));
			writer.newLine();
			writer.write(name + " " + score2); // adding player and score to txt.
			writer.close();
		}
		if (score1 == score2 && score1 > sll3.find10thElement()) { //If scores are equal and that scores are higher than the score of the 10th element of sll3
			System.out.println("Draw");
			System.out.println("Please enter the names of both players: ");
			String name = scanner2.next();
			String name2 = scanner2.next();
			sll3.add(name + " " + score1);
			sll3.add(name2 + " " + score2);
			System.out.println("");
			BufferedWriter writer = new BufferedWriter(new FileWriter("HighScoreTable.txt",true));
			writer.newLine();
			writer.write(name + " " + score1);
			writer.newLine();
			writer.write(name2 + " " + score2);
			writer.close();
		}
		
		sll3.sortList(); //Sorting the sll3 holding the highscore table.
		sll3.printListhighscore(); //Printing the sll3.

	}
	
	public static void yathzeeControl(SingleLinkedList sll) {
		if (counter > 1) 
		{
			for (int j = 0; j < 4; j++) //Checking the yahtzee status 4 times in case it happens more than 1 time.
			{
				for (int i = 1; i < 7; i++) //Control of numbers from 1 to 6.
				{
					if (sll.count(i) >= 4) //Checking the repetition numbers of the digits from 1 to 6 are greater than 4.
					{
						sll.deleteNode(i); //If the number repeats at least 4 times, the process of deleting that number 4 times.
						sll.deleteNode(i);
						sll.deleteNode(i);
						sll.deleteNode(i);
						if (sll == sll1) {
							score1 = score1 + 10; //score calculation for player1
						}
						else if (sll == sll2) {
							score2 = score2 + 10; // score calculation for player2
						}
					}
				}
			}
		}
	}
	public static void largeStraightControl(SingleLinkedList sll) {
		if (counter > 1) 
		{
			for (int i = 0; i < 5; i++)
			{
				if (sll.search(1) == true && sll.search(2) == true && sll.search(3) == true && sll.search(4) == true && sll.search(5) == true && sll.search(6) == true)
				{//The situation where all the digits from 1 to 6 are present at the same time.
					
					sll.deleteNode(1); 
					sll.deleteNode(2);
					sll.deleteNode(3);
					sll.deleteNode(4);
					sll.deleteNode(5);
					sll.deleteNode(6);
					if (sll == sll1) {
						score1 = score1 + 30;
					}
					else if (sll == sll2) {
						score2 = score2 + 30;
					}
				}
			}
		}
	}
}

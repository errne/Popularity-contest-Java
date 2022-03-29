package contest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Contest {

	public static final String[] NAMES = { "Dog", "Cat", "Beetle", "Mouse", "Rabbit", "Fox", "Crow", "Pig", "Horse",
			"Penguin", "Beaver", "Dragon", "Tiger", "Bear", "Lion", "Fish", "Shark", "Octopus", "Bull", "Yak", "Sheep",
			"Ant" };
	private static final String[] PRIZE = { "1000 dollars", "Chicken dinner", "Dream Island", "10 delicious pizzas",
			"Diamond claws", "Whale bone", "Golden Toilet"};
	private static final String[] ROUNDPRIZE = { "Win", "Revenge", "Immunity", "Golden" };
	
	ArrayList<Contestants> contestants;
	Random voteGenerator = new Random();

	public Contest() {
		contestants = new ArrayList<Contestants>();
		addContestants();
	}

	public void addContestants() {
		for (String name : NAMES) {
			contestants.add(new Contestants(name));
		}
	}
	
	public void castVotes() {
		for (Contestants contestant : contestants) {
			contestant.setVotes(voteGenerator.nextInt(1000) + 1);
		}
	}
	
	public void eliminated(Contestants contestant) {
		this.contestants.remove(contestant);
	}
	
	public Contestants mostVotes() {
		Contestants mostVoted = contestants.get(0);
		for (Contestants contestant : contestants) {
			if (mostVoted.getVotes() < contestant.getVotes()) {
				mostVoted = contestant;
			}
		}
		return mostVoted;
	}
	
	public Contestants doomed(Contestants hated){
		ArrayList<Contestants> doomeds =  new ArrayList<Contestants>(contestants);
		doomeds.remove(hated);
		int rt = voteGenerator.nextInt(doomeds.size());
		Contestants doomed = doomeds.get(rt);
		return doomed;
	}

	public Contestants leastVotes() {
		Contestants leastVoted = contestants.get(0);
		for (Contestants contestant : contestants) {
			if (leastVoted.getVotes() > contestant.getVotes()) {
				leastVoted = contestant;
			}
		}
		return leastVoted;
	}
	public void voteResults() {
		for (Contestants contestant : contestants) {
			String result = contestant.getName() + " gets " + contestant.getVotes() + " votes. ";
			System.out.println(result);
		}
	}

	public int totalVotes() {
		int tots = 0;
		for (Contestants contestant : contestants) {
			tots += contestant.getVotes();
		}
		return tots;
	}

	public int countC() {
		return contestants.size();
	}
	
	public String toString() {
		String left = "";
		for (Contestants contestant : contestants) {
			left += " " + contestant.getName();
		}
		return left;
	}
	
	public void startContest() throws InterruptedException {
		System.out.println("Welcome to Object Voting!");
		System.out.println("Give a round of applause to our host Oliver!\n");
		System.out.println("We have " + countC()+ " contestants battling for a mystery prize");		
		System.out.println("I will reveal the prize after the final round.");

		System.out.println("\nFirst round");
		castVotes();

		System.out.println("\nTotal votes: " + totalVotes());
		voteResults();
		
		System.out.println("\n" + mostVotes() + " is eliminated with " + mostVotes().getVotes() +  " votes\n");
		eliminated(mostVotes());
		nextRound();
	}

	public void finalRound() throws InterruptedException {
		System.out.println("Final round\n");
		TimeUnit.SECONDS.sleep(5);
		castVotes();
		System.out.println("Total votes: " + totalVotes());
		voteResults();

		eliminated(mostVotes());
		System.out.println("\nThe winner is: " + toString());
		int prize = voteGenerator.nextInt(6);
		System.out.println("Winner gets " + PRIZE[prize] + "!");
		try {
			FileWriter writer = new FileWriter("Winners.txt", true);
			writer.write( toString() + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nextRound() throws InterruptedException {
		int i = 1;
		while (contestants.size() > 2) {
			TimeUnit.SECONDS.sleep(5);
			i++;
			System.out.println(i + " round");
			castVotes();
			System.out.println("\nTotal votes: " + totalVotes());
			voteResults();
			if (mostVotes().winToken() == true) {
				System.out.println("\n" + mostVotes() + " uses the win token!");
				mostVotes().useWToken();
				System.out.println("\n" + mostVotes() + " has " + mostVotes().getWToken() + " win tokens\n");
				mostVotes().setVotes(mostVotes().getVotes() / 2);				
			}
			
			if (mostVotes().immToken() == true) {
				System.out.println("\n" + mostVotes() + " uses the immunity token!");
				mostVotes().useIToken();
				System.out.println("\n" + mostVotes() + " has " + mostVotes().getIToken() + " immunity tokens\n");
				mostVotes().setVotes(0);				
			}
			
			if (mostVotes().revToken() == true) {				

				Contestants hated = doomed(mostVotes());
				System.out.println("\n" + mostVotes() + " uses the revenge token on " + hated);
				mostVotes().useRToken();
				mostVotes().setVotes(mostVotes().getVotes() / 2);
				hated.setVotes(hated.getVotes() * 2);
			}
			
			if (mostVotes().goldToken() == true) {				

				Contestants doomed = doomed(mostVotes());						
				System.out.println("\n" + mostVotes() + " uses the golden token on " + doomed);
				mostVotes().useGToken();
				doomed.setVotes(990);
			}

			/*if (mostVotes().getVotes() > record) {
				record = mostVotes().getVotes();
				System.out.println(
						"\n" + mostVotes() + " is eliminated with a record of " + mostVotes().getVotes() + " votes\n");
			} else {
				System.out.println("\n" + mostVotes() + " is eliminated with " + mostVotes().getVotes() + " votes\n");
			}*/
			
			System.out.println("\n" + mostVotes() + " is eliminated\n");
			eliminated(mostVotes());
			int token = voteGenerator.nextInt(ROUNDPRIZE.length);
			System.out.println(leastVotes() + " gets a " + ROUNDPRIZE[token] + " token");
			if (token == 0) {
				leastVotes().giveWToken();
				System.out.println(leastVotes() + " has " + leastVotes().getWToken() + " win tokens\n");
			}
			if (token == 1) {
				leastVotes().giveRToken();
				System.out.println(leastVotes() + " has " + leastVotes().getRToken() + " revenge tokens\n");
			}
			
			if (token == 2) {
				leastVotes().giveIToken();
				System.out.println(leastVotes() + " has " + leastVotes().getIToken() + " immunity tokens\n");
			}
			
			if (token == 3) {
				leastVotes().giveGToken();
				System.out.println(leastVotes() + " has " + leastVotes().getGToken() + " golden tokens\n");
			}
		}
		finalRound();
	}
}

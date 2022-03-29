package contest;

public class Contestants {
	private String name;
	private int votes;
	private int winToken;
	private int revToken;
	private int immToken;
	private int goldToken;

	public Contestants(String name) {
		this.name = name;
		this.votes = 0;
	}

	public int setVotes(int vote) {
		this.votes = vote;
		return votes;
	}

	public int getVotes() {
		return this.votes;
	}
	public String getName() {
		return this.name;
	}


	public String toString() {
		return this.name;
	}
	public boolean winToken(){
		return winToken > 0;
	}
	public boolean immToken(){
		return immToken > 0;
	}
	public boolean revToken(){
		return revToken > 0;
	}
	public boolean goldToken(){
		return goldToken > 0;
	}
	public void giveWToken(){
		this.winToken++;
	}
	public void giveRToken(){
		this.revToken++;
	}
	public void giveIToken(){
		this.immToken++;
	}
	public void giveGToken(){
		this.goldToken++;
	}
	public void useWToken(){
		this.winToken--;
	}
	public void useRToken(){
		this.revToken--;
	}
	public void useIToken(){
		this.immToken--;
	}
	public void useGToken(){
		this.goldToken--;
	}
	
	public int getWToken(){
		return this.winToken;
	}
	public int getRToken(){
		return this.revToken;
	}
	public int getIToken(){
		return this.immToken;
	}
	public int getGToken(){
		return this.goldToken;
	}

}


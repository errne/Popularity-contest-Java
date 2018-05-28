package contest;

public class Contestants {
	private String name;
	private int votes;
	private int winToken = 0;
	private int revToken = 0;
	private int immToken = 0;
	private int goldToken = 0;

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
		if(winToken > 0){
		return true;
		}
		return false;
	}
	public boolean immToken(){
		if(immToken>0){
		return true;
		}
		return false;
	}
	public boolean revToken(){
		if(revToken>0){
		return true;
		}
		return false;
	}
	public boolean goldToken(){
		if(goldToken>0){
		return true;
		}
		return false;
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


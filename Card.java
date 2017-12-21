	
public class Card {
	enum Suit{Club,Diamond,Heart,Spade}//列舉Suit=Club,Diamond,Heart,Spade
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	private String[] rankname={"Ace","2","3","4","5","6","7","8","9","10","11","12","13"};//把數字寫進rankname的陣列

	/**constructor為Card (s , r)
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s,int r){
		suit=s;
		rank=r;
	}	
	//printCard印出卡片的花色與數字
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		if(suit==Suit.Club){
			System.out.println("Club"+","+rankname[rank-1]);
		}
		if(suit==Suit.Diamond){
			System.out.println("Diamond"+","+rankname[rank-1]);
		}
		if(suit==Suit.Heart){
			System.out.println("Heart"+","+rankname[rank-1]);
		}
		if(suit==Suit.Spade){
			System.out.println("Spade"+","+rankname[rank-1]);
		}
	}
	//getSuit回傳suit
	public Suit getSuit(){
		return suit;
	}
	// getRank回傳rank
	public int getRank(){
		return rank;
	}
}

import java.util.ArrayList;


public class Player extends Person {//Player繼承Person	
	private String name;//新增一個private String name
	private int chips;//新增一個private int chips 
	private int bet; //新增一個private int bet
	
	/**
	 * constructor為Player(name,chips)
	 * @param name
	 * @param chips
	 */
	public Player(String name, int chips){
		this.name=name;
		this.chips=chips;
	}
	/**方法getName()
	回傳name
	*/
	public String getName(){
		return name;
	}
	/**方法makeBet()
	 * 假設一次下注籌碼為5元
	 * 當下注籌碼為0元(沒錢了)就回傳0
	 * 否則就回傳bet
	 */
	public int makeBet(){
		bet=5;
		while(bet==0){
			return 0;
		}
		return bet;
	}

	/**方法hitMe(),參數為tb1
	 * 如果點數總和小於等於16就回傳true
	 * 否則回傳false
	 */
	public boolean hit_me(Table tb1){
		
		if(getTotalValue()<=16){
			return true;
		}
		else
			return false;
	}
	
	
	/**方法getCurrentChips()
	 * 回傳chips
	 */
	public int getCurrentChips(){
		return chips;
	}
	/**方法increaseChips
	 *籌碼變動
	 */
	public void increaseChips (int diff){
		chips+=diff;
	}
	/**方法sayHello()
	 * 玩家sayHello
	 */
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}

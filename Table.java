import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER = 4;//新增一個public static final int MAXPLAYER=4,一張牌桌最多坐4個人
	private Deck AllCard;//新增一個private Deck AllCard,存放所有的牌
	private Player[] players;//新增一個private Player[] players,存放所有玩家
	private Dealer dealer = new Dealer();//新增一個private Dealer dealer並實體化,存放一個莊家
	private int[] pos_betArray = new int[MAXPLAYER];//新增一個private int[] pos_betArray並實體化,存放每個玩家在一局下的注
	private int nDecks;//新增一個private int nDecks
	/**
	 * constructor為Table(int nDeck)
	 * @param nDeck
	 * 實體化Deck型別的AllCard
	 * 實體化Player型別的player[],長度為MAXPLAYER 
	 */
	public Table(int nDeck) {
		nDecks = nDeck;
		AllCard = new Deck(nDeck);
		players = new Player[MAXPLAYER];
	}
	/**
	 * 方法set_player()
	 * 將Player放到牌桌上 ，pos為牌桌位置，最多MAXPLAYER人，p則為Player instance
	 */
	public void set_player(int pos, Player p) {
		players[pos] = p;
	}
	/**
	 * 方法Player[] get_player()
	 * @return players
	 */
	public Player[] get_player() {
		return players;
	}
	/**
	 * 方法set_dealer()
	 * @param d
	 */
	public void set_dealer(Dealer d) {
		dealer = d;
	}
	/**
	 * 方法Card get_face_up_card_of_dealer()
	 * ArrayList<Card> oneRoundCard等於莊家的牌
	 * 回傳莊家開著的牌(第二張牌)
	 */
	public Card get_face_up_card_of_dealer() {
		ArrayList<Card> oneRoundCard= dealer.getOneRoundCard();
		return oneRoundCard.get(1);
	}
	/**
	 * 方法ask_each_player_about_bets()
	 * for迴圈從玩家1到玩家4,分別打招呼
	 * 每個玩家下的注存在pos_betArray
	 */
	private void ask_each_player_about_bets() {
		for (int i = 0; i < MAXPLAYER; i++) {
			players[i].sayHello();
			pos_betArray[i] = players[i].makeBet();
		}
	}
	/**
	 * 方法distribute_cards_to_dealer_and_players()
	 * for迴圈從玩家1到玩家4,新增一個ArrayList<Card> pcard,pcard增加2張開著的牌,放入玩家所得到的牌
	 * 新增一個ArrayList<Card> dcard,dcard增加一章蓋著一張開著的牌,放入莊家所得到的牌
	 * 印出莊家打開的牌
	 */
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < MAXPLAYER; i++) {
			ArrayList<Card> pcard = new ArrayList<Card>();
			pcard.add(AllCard.getOneCard(true));
			pcard.add(AllCard.getOneCard(true));
			players[i].setOneRoundCard(pcard);
		}
		ArrayList<Card> dcard = new ArrayList<Card>();
		dcard.add(AllCard.getOneCard(false));
		dcard.add(AllCard.getOneCard(true));
		dealer.setOneRoundCard(dcard);
		System.out.print("Dealer's face up card is ");
		Card a = get_face_up_card_of_dealer();
		a.printCard();
	}
	/**
	 * 方法ask_each_player_about_hits()
	 * 新增一個ArrayList<Card> pcard
	 * for迴圈從玩家1到玩家4,設hit=false,先印出玩家名字和目前的牌
	 * do詢問每個玩家要不要牌,hit=玩家呼叫hit_me判斷,如果玩家要牌(hit=true),玩家就會增加一張牌,並印出Hit!及目前的牌,否則印出Pass hit!、hit is over!和整副牌
	 * while hit=true,繼續做do迴圈
	 */
	private void ask_each_player_about_hits() {
		ArrayList<Card> pcard = new ArrayList<Card>();
		for (int i = 0; i < MAXPLAYER; i++) {
			boolean hit = false;
			System.out.print(players[i].getName() + "'s Cards now:");
			players[i].printAllCard();
			do {
				hit = players[i].hit_me(this);
				if (hit) {
					pcard = players[i].getOneRoundCard();
					pcard.add(AllCard.getOneCard(true));
					players[i].setOneRoundCard(pcard);
					System.out.print("Hit!" + players[i].getName()+ "'s Cards now:");
					for (Card a : pcard) {
						a.printCard();
					}
				} else {
					System.out.println("Pass hit!");
					System.out.println(players[i].getName() + "'s hit is over!");
					
				}
			} while (hit);
		}
	}
	/**
	 * 方法ask_dealer_about_hits()
	 * 新增一個ArrayList<Card> dcard 
	 * 設hit=false
	 * do詢問莊家要不要牌,hit=莊家呼叫hit_me判斷,如果莊家要牌(hit=true),莊家就會增加一張牌,並印出Hit!及目前的牌,否則印出hit is over!和整副牌
	 * while hit=true,繼續做do迴圈
	 */
	private void ask_dealer_about_hits() {
		ArrayList<Card> dcard = new ArrayList<Card>();
		boolean hit = false;
		do {
			hit = dealer.hit_me(this);
			if (hit) {
				dcard = dealer.getOneRoundCard();
				dcard.add(AllCard.getOneCard(true));
				dealer.setOneRoundCard(dcard);
				System.out.println("Hit! Dealer's Cards now:");
				for (Card a : dcard) {
					a.printCard();
				}
			} else {
				System.out.println("Dealer's hit is over!!");
				System.out.println("Dealer's Cards now:");
				for (Card a : dcard) {
					a.printCard();
				}
			}
		} while (hit);
	}
	/**
	 * 方法calculate_chips()
	 * 設Chip等於莊家總點數
	 * 印出莊家總點數及全部的牌
	 * for迴圈從玩家1到玩家4,先印出玩家的牌及總點數,接著跟莊家比較:莊家贏,把玩家籌碼沒收;莊家輸，則賠玩家與下注籌碼相符之籌碼;沒輸沒贏就沒事
	 * 印出輸贏+下注籌碼數+玩家最新籌碼數
	 */
	private void calculate_chips() {
		int Chip = dealer.getTotalValue();
		System.out.println("Dealer's card vaue is " + Chip + ",Cards: ");
		dealer.printAllCard();
		for (int i = 0; i < MAXPLAYER; i++) {
			System.out.println(players[i].getName() + "'s Card: ");
			players[i].printAllCard();
			players[i].getTotalValue();
			System.out.print(players[i].getName() + "'s card value is "	+ players[i].getTotalValue());
			if (dealer.getTotalValue() <= 21 && players[i].getTotalValue() > 21) {
				players[i].increaseChips(-pos_betArray[i]);
				System.out.println(",Loss " + pos_betArray[i]+ " Chips, the Chips now is: "+ players[i].getCurrentChips());
			} else if (dealer.getTotalValue() <= 21&& dealer.getTotalValue() > players[i].getTotalValue()) {
				players[i].increaseChips(-pos_betArray[i]);
				System.out.println(",Loss " + pos_betArray[i]+ " Chips, the Chips now is: "+ players[i].getCurrentChips());
			} else if (players[i].getTotalValue() <= 21	&& dealer.getTotalValue() > 21) {
				players[i].increaseChips(pos_betArray[i]);
				System.out.println(",Get " + pos_betArray[i]+ " Chips, the Chips now is: "+ players[i].getCurrentChips());
			} else if (players[i].getTotalValue() <= 21&& players[i].getTotalValue() > dealer.getTotalValue()) {
				players[i].increaseChips(-pos_betArray[i]);
				System.out.println(",Loss " + pos_betArray[i]+ " Chips, the Chips now is: "+ players[i].getCurrentChips());
			} else {
				System.out.println(",chips have no change! The Chips now is:"+ players[i].getCurrentChips());
			}
		}
	}
	/**
	 * 方法get_palyers_bet()
	 * 回傳pos_betArray
	 */
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	/**
	 * 方法play()
	 * 呼叫ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	 */
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}

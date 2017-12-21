import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;// 新增一個private filed:ArrayList<Card> cards
	public ArrayList<Card> usedCard;// 新增一個public filed:ArrayList<Card> usedCard
	private ArrayList<Card> openCard;// 新增一個private filed:ArrayList openCard
	public int nUsed; // 新增一個filed:nUsed

	/**
	 * constructor為Deck (nDeck) 
	 * cards新增為一個ArrayList 
	 * usedCard新增為一個ArrayList
	 * openCard新增為一個ArrayList
	 * 跑2個for迴圈，第一個:從n(幾副牌)等於nDeck到n>0，每次減一，第二個:從rank(數字)=1到13，每次加一，因為4個花色，所以跑4次
	 * 迴圈裡跑出一張card(花色，數字)，就會放進cards裡面，直到迴圈結束
	 */
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		for (int n = nDeck; n > 0; n--) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(Card.Suit.Club, rank);
				cards.add(card);
			}
		}
		for (int n = nDeck; n > 0; n--) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(Card.Suit.Diamond, rank);
				cards.add(card);
			}
		}
		for (int n = nDeck; n > 0; n--) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(Card.Suit.Heart, rank);
				cards.add(card);
			}
		}
		for (int n = nDeck; n > 0; n--) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(Card.Suit.Spade, rank);
				cards.add(card);
			}
		}
		shuffle();
	}

	// printDeck將放在a裡面的卡片都印出來
	public void printDeck() {

		for (Card a : cards) {
			a.printCard();
		}
	}

	// getAllCards回傳cards
	public ArrayList<Card> getAllCards() {
		return cards;
	}
	/**
	 * 方法getOneCard() 
	 * 如果getAllCard()是空的，就要呼叫shuffle來洗牌 
	 * 設一個暫存的temp來放cards的第一張牌
	 * 把cards的第一張牌刪掉 把剛剛放在temp的牌加入usedCard 
	 * nUsed加一 如果卡片是開著的，openCard就加入temp
	 * 回傳temp
	 */
	public Card getOneCard(boolean isOpened) {

		if (cards.size() == 0) {
			shuffle();
		}
		Card temp = cards.get(0);
		cards.remove(0);
		usedCard.add(temp);
		nUsed++;
		if (isOpened) {
			openCard.add(temp);
		}
		return temp;

	}

	// getOpenedCard回傳openCard
	public ArrayList getOpenedCard() {
		return openCard;
	}

	/**
	 * 方法shuffle 
	 * 先將在usedCard裡面的牌全部收回到cards 
	 * for迴圈表示洗牌洗1000次
	 * 亂數隨機選一個數字放在a裡面，範圍是cards的大小 從cards選出第a張排放入temp 把cards第a張牌與第一張牌位子交換，做洗牌的動作
	 * nUsed歸零
	 * usedCard清空 
	 * openCard清空
	 */
	public void shuffle() {
		for (int i = 0; i < nUsed; i++) {
			cards.add(usedCard.get(i));
		}

		for (int j = 0; j < 1000; j++) {
			Random r = new Random();
			int a = r.nextInt(cards.size());
			Card temp = cards.get(a);
			cards.set(a, cards.get(0));
			cards.set(0, temp);
		}
		nUsed = 0;
		usedCard.clear();
		openCard.clear();
	}

}

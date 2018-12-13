import java.util.ArrayList;
import java.util.Random;

public class Deck {

	
	private ArrayList<Card> cards; //定義所有的牌
	public ArrayList<Card> usedCard;//定義發過的牌
	private ArrayList<Card> openCard; //定義記錄此副牌中所有打開的牌
	public int nUsed;
	Random rnd=new Random();//隨機值
	
	public Deck(int nDeck){//新增撲克牌
		cards=new ArrayList<Card>();
		usedCard=new ArrayList<Card>();   //實體化
		openCard=new ArrayList<Card>();
		for(int d=1;d<=nDeck;d++) { //總共有多少副牌
			 for (Card.Suit s : Card.Suit.values()) {  //4種花色依序餵進s參數
			for(int n=1;n<=13;n++) {  //Ace to K 13種牌
		Card cardcard=new Card(s,n);
		cards.add(cardcard);
		}
		}
		}
		Shuffle();//新增完所有的牌後洗牌
	}	
	
	         
	public void printDeck(){//顯示這副牌中所有的牌		
		
		for(int c=0;c < cards.size();c++) { //cards ArrayList 的第0位到最後1位
		
			Card cardcard= cards.get(c);
			cardcard.printCard(); //use printCard() print (suit,rank)
		}		

	}
	public ArrayList<Card> getAllCards(){//取得所有的牌
		return cards;
	}
	
	
	public void Shuffle() {//洗牌
		
		for(int i=0;i<cards.size();i++) {
		int j = rnd.nextInt(cards.size());//隨機位置的值依序與第i位調換
		Card temp=cards.get(j);
		cards.set(j,cards.get(i));
		cards.set(i,temp);
		}		
		cards.addAll(usedCard);//收回發出去的牌
		usedCard.clear();//重設用過的牌		
		openCard.clear();//重設此副牌中打開過的牌		
		nUsed=0;	
		
	}
	
	public Card getOneCard(boolean isOpened){//發牌，決定是否把牌打開
		if(cards.size()==0) {//如果所有的牌發完的話，洗牌
			Shuffle();}
				
		Card cardcard = cards.get(0) ;//先拿第一張牌
		cards.remove(0) ;
		
		if(isOpened==true) { //如果決定要開牌，則加進openCard裡面
			
	    openCard.add(cardcard);}
		
		usedCard.add(cardcard) ;//用過的牌記錄在usedCard
		nUsed++ ;
		return cardcard ;
						
		
		}
		
	public ArrayList getOpenedCard() {//回傳此副牌中所有打開的牌
		return openCard;
	}
	
}

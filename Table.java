import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER = 4;//一張桌子最多4人
	private Deck allDeck; //放所有的牌
	private Player[] allPlayer; //放所有的玩家
	private Dealer dealer; //莊家
	private int[] pos_betArray; //每個玩家每局下的注
	private int ndeck; //存放幾副牌
	
	public Table(int nDeck){
		ndeck = nDeck;
		allDeck = new Deck(nDeck);
		allPlayer = new Player[MAXPLAYER]; //最多4人的玩家array
		
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	public void set_player(int pos,Player p) {//pos:牌桌位置,p:玩家
		if(pos>0 && pos<=allPlayer.length) { //如果牌桌有空位且沒有超過最大值
		allPlayer[pos]=p; //把玩家安置在牌桌上
		}
	}
	public Player[] get_player() { //get在牌桌上的玩家
		return allPlayer;
	}
	public void set_dealer(Dealer d) {
		dealer = d; //設置莊家
	}
	public Card get_face_up_card_of_dealer() {

		Card getdealerFUC = dealer.getOneRoundCard().get(0);
		return getdealerFUC;//回傳dealer打開的那張牌
	}
	private void ask_each_player_about_bets() {
		pos_betArray = new int[allPlayer.length]; //實體化
	    for (int i = 0; i < allPlayer.length; i++) {	
	    	if(allPlayer[i]!=null) {
	    	allPlayer[i].sayHello();//每個玩家打招呼
	        int bet = allPlayer[i].makeBet();//每個玩家下注
	        pos_betArray[i]=bet;//記錄每個玩家下的注
	    	}
	    }
	 }
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < allPlayer.length; i++) {
		      if ((allPlayer[i] != null) && (pos_betArray[i] != 0))//如果有玩家且有下注
		      {
		        ArrayList<Card> playersCard = new ArrayList();//存發過給玩家的牌
		        playersCard.add(allDeck.getOneCard(true));//發給兩張打開的牌
		        playersCard.add(allDeck.getOneCard(true));
		        allPlayer[i].setOneRoundCard(playersCard);//紀錄玩家此局得到的牌
		      }
		    }
		    if (this.dealer != null)//如有莊家
		    {
		      ArrayList<Card> dealersCard = new ArrayList();//存發給莊家的牌
		      dealersCard.add(allDeck.getOneCard(false));//發給莊家一張打開一張蓋起來的牌
		      dealersCard.add(allDeck.getOneCard(true));
		      dealer.setOneRoundCard(dealersCard);//紀錄莊家此局得到的牌
		      System.out.print("Dealer's face up card is ");
		      Card dealersFUC = get_face_up_card_of_dealer();
		      dealersFUC.printCard();//把打開的牌存起來並print
		   
		    }

    }
	private void ask_each_player_about_hits() {
		for(int i =0;i<allPlayer.length;i++) {
			if(allPlayer[i]!=null && pos_betArray[i]!=0) {
		ArrayList<Card> pc = allPlayer[i].getOneRoundCard();//玩家這局手上的牌
	    boolean playerhit;
	    do {
	    	playerhit = allPlayer[i].hit_me(this);
	    	if(playerhit) {//如果要牌
	    		
	    		pc.add(allDeck.getOneCard(true));
	    		allPlayer[i].setOneRoundCard(pc);
	    		System.out.println("Hit!!!"+allPlayer[i].getName()+"'s cards now: ");
	    		for(Card c : pc) {
	    			c.printCard();
	    		}
	    		
	    	}
	    	if(allPlayer[i].getTotalValue()>21) {//玩家手上點數超過21，不要牌
	    		playerhit = false;
	    	}
	    }while(playerhit);
	    System.out.println("Pass hit!!");
			}
		}
	}
	private void ask_dealer_about_hits() {
		ArrayList<Card> dc = dealer.getOneRoundCard();
	    boolean dealerhit;
	    do
	    {
	    	dealerhit = dealer.hit_me(this);
	      if (dealerhit)
	      {
	        dc.add(allDeck.getOneCard(true));
	        dealer.setOneRoundCard(dc);
	      }
	      if (dealer.getTotalValue() > 21) {//莊家手上點數超21，莊家over
	    	  dealerhit = false;
	      }
	    } while (dealerhit);
	    System.out.println("Dealer's hit is over!");
	  }
	
	private void calculate_chips() {
		int dealersCardValue = dealer.getTotalValue();//莊家手上牌點數和
	    System.out.print("Dealer's card value is " + dealersCardValue + " , Cards:");
	    dealer.printAllCard();//印出莊家的牌
	    for (int i = 0; i < allPlayer.length; i++) {//依序印出玩家的名字跟手上點數跟莊家比較
	    	if (allPlayer[i] != null && pos_betArray[i] != 0) {
	        System.out.print(allPlayer[i].getName() + " card value is: "+ allPlayer[i].getTotalValue());
	        allPlayer[i].printAllCard();//印出玩家的牌
	        if(allPlayer[i].getTotalValue()>21) {//玩家爆，莊家沒爆=玩家輸
	        	if(dealer.getTotalValue()<21) {
	        		System.out.println(",Loss "+ pos_betArray[i]+" Chips, the Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}else {//玩家莊家都爆=平手
	        		System.out.println(",chips have no change! The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}
	        }
	        else if(allPlayer[i].getTotalValue()<21) {
	        	if(dealer.getTotalValue()<21) {//玩家莊家都沒有爆=平手
	        		System.out.println(",chips have no change! The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}else {//莊家爆，玩家沒爆=玩家贏
	        		System.out.println(",Get "+pos_betArray[i]+"The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}
	        }


	        
	       
	    	}
	    }
		
	}
	public int[] get_players_bet() {//玩家們的下注
		return pos_betArray;
	}
}

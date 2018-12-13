import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER = 4;//�@�i��l�̦h4�H
	private Deck allDeck; //��Ҧ����P
	private Player[] allPlayer; //��Ҧ������a
	private Dealer dealer; //���a
	private int[] pos_betArray; //�C�Ӫ��a�C���U���`
	private int ndeck; //�s��X�ƵP
	
	public Table(int nDeck){
		ndeck = nDeck;
		allDeck = new Deck(nDeck);
		allPlayer = new Player[MAXPLAYER]; //�̦h4�H�����aarray
		
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	public void set_player(int pos,Player p) {//pos:�P���m,p:���a
		if(pos>0 && pos<=allPlayer.length) { //�p�G�P�঳�Ŧ�B�S���W�L�̤j��
		allPlayer[pos]=p; //�⪱�a�w�m�b�P��W
		}
	}
	public Player[] get_player() { //get�b�P��W�����a
		return allPlayer;
	}
	public void set_dealer(Dealer d) {
		dealer = d; //�]�m���a
	}
	public Card get_face_up_card_of_dealer() {

		Card getdealerFUC = dealer.getOneRoundCard().get(0);
		return getdealerFUC;//�^��dealer���}�����i�P
	}
	private void ask_each_player_about_bets() {
		pos_betArray = new int[allPlayer.length]; //�����
	    for (int i = 0; i < allPlayer.length; i++) {	
	    	if(allPlayer[i]!=null) {
	    	allPlayer[i].sayHello();//�C�Ӫ��a���۩I
	        int bet = allPlayer[i].makeBet();//�C�Ӫ��a�U�`
	        pos_betArray[i]=bet;//�O���C�Ӫ��a�U���`
	    	}
	    }
	 }
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < allPlayer.length; i++) {
		      if ((allPlayer[i] != null) && (pos_betArray[i] != 0))//�p�G�����a�B���U�`
		      {
		        ArrayList<Card> playersCard = new ArrayList();//�s�o�L�����a���P
		        playersCard.add(allDeck.getOneCard(true));//�o����i���}���P
		        playersCard.add(allDeck.getOneCard(true));
		        allPlayer[i].setOneRoundCard(playersCard);//�������a�����o�쪺�P
		      }
		    }
		    if (this.dealer != null)//�p�����a
		    {
		      ArrayList<Card> dealersCard = new ArrayList();//�s�o�����a���P
		      dealersCard.add(allDeck.getOneCard(false));//�o�����a�@�i���}�@�i�\�_�Ӫ��P
		      dealersCard.add(allDeck.getOneCard(true));
		      dealer.setOneRoundCard(dealersCard);//�������a�����o�쪺�P
		      System.out.print("Dealer's face up card is ");
		      Card dealersFUC = get_face_up_card_of_dealer();
		      dealersFUC.printCard();//�⥴�}���P�s�_�Ө�print
		   
		    }

    }
	private void ask_each_player_about_hits() {
		for(int i =0;i<allPlayer.length;i++) {
			if(allPlayer[i]!=null && pos_betArray[i]!=0) {
		ArrayList<Card> pc = allPlayer[i].getOneRoundCard();//���a�o����W���P
	    boolean playerhit;
	    do {
	    	playerhit = allPlayer[i].hit_me(this);
	    	if(playerhit) {//�p�G�n�P
	    		
	    		pc.add(allDeck.getOneCard(true));
	    		allPlayer[i].setOneRoundCard(pc);
	    		System.out.println("Hit!!!"+allPlayer[i].getName()+"'s cards now: ");
	    		for(Card c : pc) {
	    			c.printCard();
	    		}
	    		
	    	}
	    	if(allPlayer[i].getTotalValue()>21) {//���a��W�I�ƶW�L21�A���n�P
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
	      if (dealer.getTotalValue() > 21) {//���a��W�I�ƶW21�A���aover
	    	  dealerhit = false;
	      }
	    } while (dealerhit);
	    System.out.println("Dealer's hit is over!");
	  }
	
	private void calculate_chips() {
		int dealersCardValue = dealer.getTotalValue();//���a��W�P�I�ƩM
	    System.out.print("Dealer's card value is " + dealersCardValue + " , Cards:");
	    dealer.printAllCard();//�L�X���a���P
	    for (int i = 0; i < allPlayer.length; i++) {//�̧ǦL�X���a���W�r���W�I�Ƹ���a���
	    	if (allPlayer[i] != null && pos_betArray[i] != 0) {
	        System.out.print(allPlayer[i].getName() + " card value is: "+ allPlayer[i].getTotalValue());
	        allPlayer[i].printAllCard();//�L�X���a���P
	        if(allPlayer[i].getTotalValue()>21) {//���a�z�A���a�S�z=���a��
	        	if(dealer.getTotalValue()<21) {
	        		System.out.println(",Loss "+ pos_betArray[i]+" Chips, the Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}else {//���a���a���z=����
	        		System.out.println(",chips have no change! The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}
	        }
	        else if(allPlayer[i].getTotalValue()<21) {
	        	if(dealer.getTotalValue()<21) {//���a���a���S���z=����
	        		System.out.println(",chips have no change! The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}else {//���a�z�A���a�S�z=���aĹ
	        		System.out.println(",Get "+pos_betArray[i]+"The Chips now is: "+ allPlayer[i].getCurrentChips());
	        	}
	        }


	        
	       
	    	}
	    }
		
	}
	public int[] get_players_bet() {//���a�̪��U�`
		return pos_betArray;
	}
}

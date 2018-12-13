import java.util.ArrayList;
import java.util.Random;

public class Deck {

	
	private ArrayList<Card> cards; //�w�q�Ҧ����P
	public ArrayList<Card> usedCard;//�w�q�o�L���P
	private ArrayList<Card> openCard; //�w�q�O�����ƵP���Ҧ����}���P
	public int nUsed;
	Random rnd=new Random();//�H����
	
	public Deck(int nDeck){//�s�W���J�P
		cards=new ArrayList<Card>();
		usedCard=new ArrayList<Card>();   //�����
		openCard=new ArrayList<Card>();
		for(int d=1;d<=nDeck;d++) { //�`�@���h�ְƵP
			 for (Card.Suit s : Card.Suit.values()) {  //4�ت��̧����is�Ѽ�
			for(int n=1;n<=13;n++) {  //Ace to K 13�صP
		Card cardcard=new Card(s,n);
		cards.add(cardcard);
		}
		}
		}
		Shuffle();//�s�W���Ҧ����P��~�P
	}	
	
	         
	public void printDeck(){//��ܳo�ƵP���Ҧ����P		
		
		for(int c=0;c < cards.size();c++) { //cards ArrayList ����0���̫�1��
		
			Card cardcard= cards.get(c);
			cardcard.printCard(); //use printCard() print (suit,rank)
		}		

	}
	public ArrayList<Card> getAllCards(){//���o�Ҧ����P
		return cards;
	}
	
	
	public void Shuffle() {//�~�P
		
		for(int i=0;i<cards.size();i++) {
		int j = rnd.nextInt(cards.size());//�H����m���Ȩ̧ǻP��i��մ�
		Card temp=cards.get(j);
		cards.set(j,cards.get(i));
		cards.set(i,temp);
		}		
		cards.addAll(usedCard);//���^�o�X�h���P
		usedCard.clear();//���]�ιL���P		
		openCard.clear();//���]���ƵP�����}�L���P		
		nUsed=0;	
		
	}
	
	public Card getOneCard(boolean isOpened){//�o�P�A�M�w�O�_��P���}
		if(cards.size()==0) {//�p�G�Ҧ����P�o�����ܡA�~�P
			Shuffle();}
				
		Card cardcard = cards.get(0) ;//�����Ĥ@�i�P
		cards.remove(0) ;
		
		if(isOpened==true) { //�p�G�M�w�n�}�P�A�h�[�iopenCard�̭�
			
	    openCard.add(cardcard);}
		
		usedCard.add(cardcard) ;//�ιL���P�O���busedCard
		nUsed++ ;
		return cardcard ;
						
		
		}
		
	public ArrayList getOpenedCard() {//�^�Ǧ��ƵP���Ҧ����}���P
		return openCard;
	}
	
}



public class Card<Suit, rank> {

	enum Suit{Clubs,Diamond,Heart,Spade};//�s�W��⪺�C�|
		private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
		private int rank; //1~13
		/**
		 * @param s suit
		 * @param r rank
		 */
		public Card(Suit s,int r){
			suit=s;
			rank=r;
		}	
		
		public void printCard(){
			//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
			System.out.println(getSuit()+","+getRank()); //getSuit(), getRank() �Ө��osuit rank����
			
		}
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}



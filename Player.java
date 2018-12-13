import java.util.ArrayList;

public class Player extends Person{

	private String name; //���a�m�W
	private int chips; //���a�w�X
	private int bet=0; //���a�����w�U�`���w�X
	private ArrayList oneRoundCard; //�������d
	
	public Player(String name, int chips) { //�s�W���a�m�W�H���w�X		
		this.name = name;
		this.chips = chips;
		
	}
	
	public String getName() { //���a�m�W��getter
		return name;
	}
	
	public int makeBet() {//�U�`�A�æ^�ǹw�p�n�U�`���w�X
		bet=1; //�򥻤U�`		
		chips = chips-bet; //��W�w�X-�w�U�`���w�X
		if(chips<=0) {
			//�Y��W�S���w�X�A�����U�`
			bet = 0;
		} 
		return bet;
			}
	
	public int getCurrentChips() {//�^�Ǫ��a��W�{���w�X
		return chips;
	}
	public void increaseChips (int diff) {//���a�w�X�ܰ�
		chips += diff;
	}
	public void sayHello() { //���a���۩I
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

	@Override
	public boolean hit_me(Table table) {//�n�P���n�P������
		
		boolean hit= false;
		int sum = getTotalValue();	
		int dealercard = table.get_face_up_card_of_dealer().getRank();
		int Maxpoint = 21;
		
		if (getCurrentChips() <= 0) {
			hit = false;
		} else {
			if (sum >= Maxpoint) {
				hit = false;
			} else {
				if (!(hasAce())) {
					if (sum<= 11) {
						hit = true;
					} else if (sum == 12) {
						if (dealercard == 4 || dealercard == 5 || dealercard == 6) {
							hit = false;
						} else {
							hit = true;
						}
					} else if (sum >= 13 && sum <= 16) {
						if (dealercard >= 2 && dealercard <= 6) {
							hit = false;
						} else {
							hit = true;
						}
					} else {
						hit = false;
					}
				} else {
					if (sum <= 17) {
						hit = true;
					} else if (sum == 18) {
						if (dealercard == 2 || dealercard == 7 || dealercard == 8) {
							hit = false;
						} else {
							hit = true;
						}
					} else {
						hit = false;
					}
				}
			}
		}
		return hit;
	}
}

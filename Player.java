import java.util.ArrayList;

public class Player extends Person{

	private String name; //玩家姓名
	private int chips; //玩家籌碼
	private int bet=0; //玩家此局已下注的籌碼
	private ArrayList oneRoundCard; //此局的卡
	
	public Player(String name, int chips) { //新增玩家姓名以及籌碼		
		this.name = name;
		this.chips = chips;
		
	}
	
	public String getName() { //玩家姓名的getter
		return name;
	}
	
	public int makeBet() {//下注，並回傳預計要下注的籌碼
		bet=1; //基本下注		
		chips = chips-bet; //手上籌碼-已下注的籌碼
		if(chips<=0) {
			//若手上沒有籌碼，結束下注
			bet = 0;
		} 
		return bet;
			}
	
	public int getCurrentChips() {//回傳玩家手上現有籌碼
		return chips;
	}
	public void increaseChips (int diff) {//玩家籌碼變動
		chips += diff;
	}
	public void sayHello() { //玩家打招呼
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

	@Override
	public boolean hit_me(Table table) {//要牌不要牌之策略
		
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

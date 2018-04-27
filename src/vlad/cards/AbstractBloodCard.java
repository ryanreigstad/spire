package vlad.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import vlad.actions.BleedAction;
import vlad.patches.AbstractCardEnum;

public abstract class AbstractBloodCard extends CustomCard {

	protected AbstractBloodCard(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardRarity rarity, CardTarget target) {
		this(id, name, img, cost, rawDescription, type, rarity, target, 0);
	}
	
	protected AbstractBloodCard(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardRarity rarity, CardTarget target, int bloodCost) {
		super(id, name, img, cost, rawDescription, type, AbstractCardEnum.Blood, rarity, target, 1);
		
		this.bloodCost = bloodCost;
		this.originalDescription = rawDescription;
	}
	
	protected int bloodCost;
	protected String originalDescription;
	
	@Override
	public void initializeDescription() {
		if (this.bloodCost > 0)
			this.rawDescription = this.originalDescription + " NL Lose " + this.bloodCost + " HP";

		super.initializeDescription();
	}
	
	@Override
	public boolean canPlay(AbstractCard card) {
		if (card instanceof AbstractBloodCard)
			if (((AbstractBloodCard) card).bloodCost < AbstractDungeon.player.currentHealth)
				return true;
		return super.canPlay(card);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.bloodCost > 0)
			AbstractDungeon.actionManager.addToTop(new BleedAction(p, p, this.bloodCost));
	}
	
}

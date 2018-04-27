package vlad.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;

public class BloodDefend extends AbstractBloodCard {
	
	public static final String Id = "BloodDefend";
	public static final String Icon = "cards/defend_purple.png";
    public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 1;
	public static final int BloodCost = 0;
	public static final int Amount = 5;
	public static final int Upgrade = 3;
	
	public static final CardType Type = CardType.SKILL;
	public static final CardRarity Rarity = CardRarity.BASIC;
	public static final CardTarget Target = CardTarget.SELF;

	public BloodDefend() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);
		
		this.baseBlock = this.block = Amount;
		
		this.initializeDescription();
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new BloodDefend();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded)
		{
			this.upgradeName();
			this.upgradeBlock(Upgrade);
			
			this.initializeDescription();
		}
	}

}

package vlad.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;

public class BloodStrike extends AbstractBloodCard {
	
	public static final String Id = "BloodStrike";
	public static final String Icon = "cards/strike_purple.png";
    public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 1;
	public static final int BloodCost = 0;
	public static final int Amount = 6;
	public static final int Upgrade = 3;
	
	public static final CardType Type = CardType.ATTACK;
	public static final CardRarity Rarity = CardRarity.BASIC;
	public static final CardTarget Target = CardTarget.ENEMY;

	public BloodStrike() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);
		
		this.baseDamage = this.damage = Amount;
		
		this.initializeDescription();
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.SLASH_DIAGONAL));
		
		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new BloodStrike();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded)
		{
			this.upgradeName();
			this.upgradeDamage(Upgrade);
			
			this.initializeDescription();
		}
	}

}

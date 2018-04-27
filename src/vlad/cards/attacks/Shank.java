package vlad.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import vlad.powers.BleedPower;

public class Shank extends AbstractBloodCard {
	
	public static final String Id = "Shank";
	public static final String Icon = "cards/comet.png";
    public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 1;
	public static final int BloodCost = 0;
	public static final int Damage = 2;
	public static final int UpgradeDmg = 2;
	public static final int Amount = 5;
	public static final int UpgradeAmt = 2;
	
	public static final CardType Type = CardType.ATTACK;
	public static final CardRarity Rarity = CardRarity.COMMON;
	public static final CardTarget Target = CardTarget.ENEMY;

	public Shank() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);

		this.baseDamage = this.damage = Damage;
		this.baseMagicNumber = this.magicNumber = Amount;
		
		this.initializeDescription();
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BleedPower(m, p, this.magicNumber), this.magicNumber));

		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Shank();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded)
		{
			this.upgradeName();
			this.upgradeDamage(UpgradeDmg);
			this.upgradeMagicNumber(UpgradeAmt);
			
			this.initializeDescription();
		}
	}

}

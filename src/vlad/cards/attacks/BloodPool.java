package vlad.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;
import vlad.powers.BleedPower;

public class BloodPool extends AbstractBloodCard {

	public static final String Id = "BloodPool";
	public static final String Icon = "cards/eclipse.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 0;
	public static final int BloodCost = 5;
	public static final int UpgradedBloodCost = 7;
	public static final int Amount = 6;
	public static final int BleedAmount = 2;
	public static final int Upgrade = 3;
	public static final int BleedUpgrade = 1;

	public static final CardType Type = CardType.ATTACK;
	public static final CardRarity Rarity = CardRarity.UNCOMMON;
	public static final CardTarget Target = CardTarget.ALL_ENEMY;

	public BloodPool() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);

		this.baseDamage = this.damage = Amount;
		this.baseMagicNumber = this.magicNumber = BleedAmount;
		
		this.isMultiDamage = true;

		this.initializeDescription();
	}
	
	protected int bleed;

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AttackEffect.NONE));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new BleedPower(mo, p, this.magicNumber), this.magicNumber));
		}
		
		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new BloodPool();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(Upgrade);
			this.upgradeMagicNumber(BleedUpgrade);
			this.bloodCost = UpgradedBloodCost;

			this.initializeDescription();
		}
	}

}

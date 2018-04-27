package vlad.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;
import vlad.powers.BleedPower;

public class HarnessPain extends AbstractBloodCard {

	public static final String Id = "HarnessPain";
	public static final String Icon = "cards/genesis.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 1;
	public static final int BloodCost = 0;
	public static final int BleedAmount = 2;
	public static final int BleedUpgrade = 1;
	public static final int IntangibleAmount = 1;
	public static final int IntangibleUpgrade = 1;

	public static final CardType Type = CardType.POWER;
	public static final CardRarity Rarity = CardRarity.COMMON;
	public static final CardTarget Target = CardTarget.SELF;

	public HarnessPain() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);

		this.bleed = BleedAmount;
		this.intangible = IntangibleAmount;

		this.initializeDescription();
	}

	protected int bleed;
	protected int intangible;

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BleedPower(p, p, this.bleed), this.bleed));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.intangible), this.intangible));
		
		super.use(p, m);
	}

	@Override
	public void initializeDescription() {
		this.rawDescription = this.originalDescription
				= S.EXTENDED_DESCRIPTION[0] + this.bleed
				+ S.EXTENDED_DESCRIPTION[1] + this.intangible
				+ S.EXTENDED_DESCRIPTION[2];

		super.initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() {
		return new HarnessPain();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();

			this.bleed += BleedUpgrade;
			this.intangible += IntangibleUpgrade;

			this.initializeDescription();
		}
	}

}

package vlad.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;
import vlad.powers.CoagulatePower;

public class Coagulate extends AbstractBloodCard {

	public static final String Id = "Coagulate";
	public static final String Icon = "cards/plasma_wave.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 2;
	public static final int UpgradedCost = 1;
	public static final int BloodCost = 0;
	public static final int Amount = 1;

	public static final CardType Type = CardType.POWER;
	public static final CardRarity Rarity = CardRarity.UNCOMMON;
	public static final CardTarget Target = CardTarget.SELF;

	public Coagulate() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);

		this.baseMagicNumber = this.magicNumber = Amount;

		this.initializeDescription();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CoagulatePower(p, p, this.magicNumber), 1));
		
		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Coagulate();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(UpgradedCost);
			this.initializeDescription();
		}
	}

}

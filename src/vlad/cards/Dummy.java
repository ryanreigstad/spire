package vlad.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;

public class Dummy extends AbstractBloodCard {

	public static final String Id = "Dummy";
	public static final String Icon = "cards/void_ray.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 0;
	public static final int BloodCost = 0;
	public static final int Amount = 1;
	public static final int Upgrade = 1;

	public static final CardTarget Target = CardTarget.NONE;

	public Dummy(CardType type, CardRarity rarity) {
		super(Id + type.toString() + rarity.toString(), S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, type, rarity,
				Target, BloodCost);

		this.baseMagicNumber = this.magicNumber = Amount;
		this.purgeOnUse = true;
		
		this.initializeDescription();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
		
		super.use(p, m);
	}

	@Override
	public void initializeDescription() {
		this.rawDescription = S.DESCRIPTION + this.magicNumber
				+ (this.upgraded ? S.EXTENDED_DESCRIPTION[1] : S.EXTENDED_DESCRIPTION[0])
				+ S.EXTENDED_DESCRIPTION[2];
		
		super.initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() {
		return new Dummy(this.type, this.rarity);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(Upgrade);
			
			initializeDescription();
		}
	}

}

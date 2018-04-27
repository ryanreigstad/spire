package vlad.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;

public class Drain extends AbstractBloodCard {

	public static final String Id = "Drain";
	public static final String Icon = "cards/channel.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);

	public static final int Cost = 1;
	public static final int BloodCost = 0;
	public static final int Amount = 5;
	public static final int HealAmount = 2;
	public static final int Upgrade = 2;

	public static final CardType Type = CardType.ATTACK;
	public static final CardRarity Rarity = CardRarity.COMMON;
	public static final CardTarget Target = CardTarget.ENEMY;

	public Drain() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);

		this.baseDamage = this.damage = Amount;
		this.baseMagicNumber = this.magicNumber = HealAmount;

		this.initializeDescription();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.SLASH_VERTICAL));
		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.damage));

		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Drain();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(Upgrade);
			this.upgradeMagicNumber(Upgrade);

			this.initializeDescription();
		}
	}

}

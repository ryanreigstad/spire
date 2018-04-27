package vlad.cards.skills;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import vlad.Mod;
import vlad.cards.AbstractBloodCard;
import vlad.powers.BleedPower;

public class Consume extends AbstractBloodCard {

	public static final String Id = "Consume";
	public static final String Icon = "cards/power_overwhelming.png";
	public static final CardStrings S = CardCrawlGame.languagePack.getCardStrings(Id);
	public static final String NoBleedMessage = "That enemy didn't have any bleed stacks...";

	public static final int Cost = 0;
	public static final int BloodCost = 5;
	public static final int Amount = 2;
	public static final int Upgrade = 3;
	public static final int BleedStackCost = 2;

	public static final CardType Type = CardType.SKILL;
	public static final CardRarity Rarity = CardRarity.RARE;
	public static final CardTarget Target = CardTarget.ENEMY;

	public Consume() {
		super(Id, S.NAME, Mod.p(Icon), Cost, S.DESCRIPTION, Type, Rarity, Target, BloodCost);
		
		this.baseMagicNumber = this.magicNumber = Amount;
		
		this.initializeDescription();
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean can = super.canUse(p, m);
		
		if (can && m != null && m.hasPower(BleedPower.Id)) {
			int stacks = m.getPower(BleedPower.Id).amount;
			if (stacks < BleedStackCost)
				can = false;
			
			if (!can)
			    AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, NoBleedMessage, true));
		}
		
		return can;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m.hasPower(BleedPower.Id))
		{
			int stacks = m.getPower(BleedPower.Id).amount;
			
			Mod.l.debug("using consume: stacks = " + stacks + ", cost = " + BleedStackCost);
			
			if (stacks > BleedStackCost)
				AbstractDungeon.actionManager.addToTop(new ReducePowerAction(m, p, BleedPower.Id, BleedStackCost));
			else
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(m, p, BleedPower.Id));
	        AbstractDungeon.player.increaseMaxHp(this.magicNumber, true);
		}
		
		super.use(p, m);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Consume();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(Upgrade);
			
			this.initializeDescription();
		}
	}

}

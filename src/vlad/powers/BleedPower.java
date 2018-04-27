package vlad.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import vlad.Mod;
import vlad.actions.BleedAction;

public class BleedPower extends AbstractPower {

	public static final String Id = "BleedPower";
	public static final String Icon = "powers/bleed.png";
	public static final PowerStrings S = CardCrawlGame.languagePack.getPowerStrings(Id);

	public BleedPower(AbstractCreature owner, AbstractCreature source, int stacks) {
		this.ID = Id;
		this.name = S.NAME;
		this.img = Mod.t(Icon);

		this.owner = owner;
		this.amount = stacks;
		this.source = source;

		this.isTurnBased = false;
		this.type = PowerType.DEBUFF;

		this.updateDescription();
	}

	protected AbstractCreature source;

	@Override
	public void updateDescription() {
		this.description = S.DESCRIPTIONS[0] + this.amount + S.DESCRIPTIONS[1];
	}

	@Override
	public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
		if (info.type != DamageType.NORMAL)
			return;
		
		this.flash();
		AbstractDungeon.actionManager.addToTop(new BleedAction(this.owner, this.source, this.amount));
	}
}

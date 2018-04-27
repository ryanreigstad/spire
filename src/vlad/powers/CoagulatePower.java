package vlad.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import vlad.Mod;

public class CoagulatePower extends AbstractPower {

	public static final String Id = "CoagulatePower";
	public static final String Icon = "powers/coagulate.png";
	public static final PowerStrings S = CardCrawlGame.languagePack.getPowerStrings(Id);

	public CoagulatePower(AbstractCreature owner, AbstractCreature source, int amount) {
		this.ID = Id;
		this.name = S.NAME;
		this.img = Mod.t(Icon);

		this.owner = owner;
		this.source = source;
		this.amount = amount;

		this.isTurnBased = false;
		this.type = PowerType.BUFF;

		this.updateDescription();
	}

	protected AbstractCreature source;

	@Override
	public void updateDescription() {
		int amt = 0;
		if (this.owner.hasPower(BleedPower.Id)) {
			amt = this.owner.getPower(BleedPower.Id).amount * this.amount;
		}

		this.description = S.DESCRIPTIONS[0] + amt + S.DESCRIPTIONS[1];
	}

//	@Override
//	public void atStartOfTurn() {
//		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, Id, 1));
//
//		super.atStartOfTurn();
//	}

	@Override
	public void onSpecificTrigger() {
		int amt = 0;
		if (this.owner.hasPower(BleedPower.Id)) {
			amt = this.owner.getPower(BleedPower.Id).amount * this.amount;
		}
		if (amt > 0) {
			this.flash();
			AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, this.source, amt));
		}
	}
}

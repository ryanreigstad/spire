package vlad.actions;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import vlad.powers.CoagulatePower;

public class BleedAction extends LoseHPAction {

	public BleedAction(AbstractCreature target, AbstractCreature source, int amount) {
		super(target, source, amount, AttackEffect.FIRE);
	}

	@Override
	public void update() {
		super.update();

		if (this.isDone && !this.shouldCancelAction()) {
			if (this.target.hasPower(CoagulatePower.Id))
				this.target.getPower(CoagulatePower.Id).onSpecificTrigger();
		}
	}

}

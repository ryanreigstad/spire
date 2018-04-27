package vlad.actions;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class BleedAction extends LoseHPAction {

	public BleedAction(AbstractCreature target, AbstractCreature source, int amount) {
		super(target, source, amount, AttackEffect.FIRE);
	}

	@Override
	public void update() {
		super.update();

		if (this.isDone && !this.shouldCancelAction()) {
			if (this.target.hasPower("todo"))
				this.target.getPower("todo").onSpecificTrigger();
		}
	}

}

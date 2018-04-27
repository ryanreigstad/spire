package vlad;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import basemod.abstracts.CustomPlayer;
import vlad.cards.attacks.BloodStrike;
import vlad.cards.attacks.Shank;
import vlad.cards.skills.BloodDefend;
import vlad.patches.CharacterEnum;

public class Vlad extends CustomPlayer {

	public static final int STARTING_HP = 50;
	public static final int MAX_HP = 50;
	public static final int STARTING_GOLD = 199;
	public static final int HAND_SIZE = 5;
	public static final int ENERGY_PER_TURN = 3;

	public static final String[] orbTextures = { Mod.p("orb/layer1.png"), Mod.p("orb/layer2.png"),
			Mod.p("orb/layer3.png"), Mod.p("orb/layer4.png"), Mod.p("orb/layer5.png"), Mod.p("orb/layer6.png"),
			Mod.p("orb/layer1d.png"), Mod.p("orb/layer2d.png"), Mod.p("orb/layer3d.png"), Mod.p("orb/layer4d.png"),
			Mod.p("orb/layer5d.png"), };

	public static final String MY_CHARACTER_SHOULDER_2 = Mod.p("anim/shoulder2.png");
	public static final String MY_CHARACTER_SHOULDER_1 = Mod.p("anim/shoulder.png");
	public static final String MY_CHARACTER_CORPSE = Mod.p("anim/corpse.png");
	public static final String MY_CHARACTER_SKELETON_ATLAS = Mod.p("anim/idle/skeleton.atlas");
	public static final String MY_CHARACTER_SKELETON_JSON = Mod.p("anim/idle/skeleton.json");

	public Vlad(String name, PlayerClass chosenClass) {
		super(name, chosenClass, orbTextures, Mod.p("orb/vfx.png"), null, MY_CHARACTER_SKELETON_JSON);

		this.dialogX = (this.drawX + 0.0F * Settings.scale);
		this.dialogY = (this.drawY + 220.0F * Settings.scale);

		initializeClass(null, MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1, MY_CHARACTER_CORPSE, getLoadout(),
				20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

		loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("Vlad", "A Blood Mage that uses hp as a resource heavily!", STARTING_HP, MAX_HP,
				STARTING_GOLD, HAND_SIZE, CharacterEnum.Vlad, getStartingRelics(), getStartingDeck(), false);
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();

		retVal.add(BloodStrike.Id);
		retVal.add(BloodStrike.Id);
		retVal.add(BloodStrike.Id);
		retVal.add(BloodStrike.Id);
		retVal.add(Shank.Id);

		retVal.add(BloodDefend.Id);
		retVal.add(BloodDefend.Id);
		retVal.add(BloodDefend.Id);
		retVal.add(BloodDefend.Id);
		retVal.add(BloodDefend.Id);

		return retVal;
	}

	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();

		retVal.add("Black Blood");
		retVal.add("Vajra");
		retVal.add("Ice Cream");

		return retVal;
	}
}

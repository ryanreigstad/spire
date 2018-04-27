package vlad;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;
import vlad.cards.Dummy;
import vlad.cards.attacks.BloodPool;
import vlad.cards.attacks.BloodStrike;
import vlad.cards.attacks.Shank;
import vlad.cards.attacks.Transfusion;
import vlad.cards.powers.HarnessPain;
import vlad.cards.skills.BloodDefend;
import vlad.cards.skills.Consume;
import vlad.patches.AbstractCardEnum;
import vlad.patches.CharacterEnum;

@SpireInitializer
public class Mod implements PostInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber,
		EditCharactersSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, SetUnlocksSubscriber {

	public static final Logger l = LogManager.getLogger(Vlad.class.getName());

	private static final Color BLOOD = CardHelper.getColor(200.0f, 10.0f, 10.0f);

	private static final String ATTACK_BLOOD = "cards/backgrounds/attack_512.png";
	private static final String SKILL_BLOOD = "cards/backgrounds/skill_512.png";
	private static final String POWER_BLOOD = "cards/backgrounds/power_512.png";
	private static final String ENERGY_ORB_BLOOD = "cards/backgrounds/orb_512.png";

	private static final String ATTACK_BLOOD_PORTRAIT = "cards/backgrounds/attack_1024.png";
	private static final String SKILL_BLOOD_PORTRAIT = "cards/backgrounds/skill_1024.png";
	private static final String POWER_BLOOD_PORTRAIT = "cards/backgrounds/power_1024.png";
	private static final String ENERGY_ORB_BLOOD_PORTRAIT = "cards/backgrounds/orb_1024.png";

	private static final String BUTTON = "logo.png";
	private static final String POTRAIT = "splash.jpg";

	public static String p(String path) {
		return "res/" + path;
	}

	public static Texture t(String path) {
		return new Texture(p(path));
	}

	public static void initialize() {
		@SuppressWarnings("unused")
		Mod m = new Mod();
	}

	public Mod() {
		BaseMod.subscribe(this);

		BaseMod.addColor(AbstractCardEnum.Blood.toString(), BLOOD, BLOOD, BLOOD, BLOOD, BLOOD, BLOOD, BLOOD,
				p(ATTACK_BLOOD), p(SKILL_BLOOD), p(POWER_BLOOD), p(ENERGY_ORB_BLOOD), p(ATTACK_BLOOD_PORTRAIT),
				p(SKILL_BLOOD_PORTRAIT), p(POWER_BLOOD_PORTRAIT), p(ENERGY_ORB_BLOOD_PORTRAIT));

		l.debug("added color: " + AbstractCardEnum.Blood);
	}

	@Override
	public void receiveSetUnlocks() {
		l.debug("receiveSetUnlocks");
		l.debug("/receiveSetUnlocks");
	}

	@Override
	public void receiveEditKeywords() {
		l.debug("receiveEditKeywords");

        BaseMod.addKeyword(new String[] {"bleed", "Bleed", "bleeding"}, "Bleed inflicts damage upon attack.");
		
		l.debug("/receiveEditKeywords");
	}

	@Override
	public void receiveEditStrings() {
		l.debug("receiveEditStrings (check your commas)");
		
		l.debug("powers");
		BaseMod.loadCustomStrings(PowerStrings.class,
				Gdx.files.internal("eng/bloodpowers.json").readString(String.valueOf(StandardCharsets.UTF_8)));

		l.debug("relics");
		BaseMod.loadCustomStrings(RelicStrings.class,
				Gdx.files.internal("eng/bloodrelics.json").readString(String.valueOf(StandardCharsets.UTF_8)));

		l.debug("cards");
		BaseMod.loadCustomStrings(CardStrings.class,
				Gdx.files.internal("eng/bloodcards.json").readString(String.valueOf(StandardCharsets.UTF_8)));

		l.debug("/receiveEditStrings");
	}

	@Override
	public void receiveEditCharacters() {
		l.debug("receiveEditCharacters");
		
		BaseMod.addCharacter(Vlad.class, "Vlad", "A Blood Mage that uses hp as a resource heavily!",
				AbstractCardEnum.Blood.toString(), "Vlad", p(BUTTON), p(POTRAIT), CharacterEnum.Vlad.toString());

		l.debug("/receiveEditCharacters");
	}

	@Override
	public void receiveEditRelics() {
		l.debug("receiveEditRelics");
		
//        RelicLibrary.add(new WingedNecklace());
//        BaseMod.addRelicToCustomPool(new OrbOfLight(), AbstractCardEnum.Blood.name());
		
		l.debug("/receiveEditRelics");
	}

	@Override
	public void receiveEditCards() {
		l.debug("receiveEditCards");
		
		BaseMod.addCard(new BloodStrike());
		BaseMod.addCard(new BloodDefend());

		// attacks
		BaseMod.addCard(new Shank());
		BaseMod.addCard(new Transfusion());
		BaseMod.addCard(new BloodPool());
		
		// skills
		BaseMod.addCard(new Consume());
		
		// powers
		BaseMod.addCard(new HarnessPain());

		// dummy cards that need to be here so it doesn't break
//		BaseMod.addCard(new Dummy(CardType.ATTACK, CardRarity.COMMON));
//		BaseMod.addCard(new Dummy(CardType.ATTACK, CardRarity.UNCOMMON));
		BaseMod.addCard(new Dummy(CardType.ATTACK, CardRarity.RARE));

		BaseMod.addCard(new Dummy(CardType.SKILL, CardRarity.COMMON));
		BaseMod.addCard(new Dummy(CardType.SKILL, CardRarity.UNCOMMON));
//		BaseMod.addCard(new Dummy(CardType.SKILL, CardRarity.RARE));

//		BaseMod.addCard(new Dummy(CardType.POWER, CardRarity.COMMON));
		BaseMod.addCard(new Dummy(CardType.POWER, CardRarity.UNCOMMON));
		BaseMod.addCard(new Dummy(CardType.POWER, CardRarity.RARE));

		l.debug("/receiveEditCards");
	}

	@Override
	public void receivePostInitialize() {
		l.debug("receivePostInitialize");
		
		Texture badgeTexture = new Texture(Gdx.files.internal(p("BaseModBadge.png")));
		BaseMod.registerModBadge(badgeTexture, "ioman", "ioman", "A Blood Mage that uses hp as a resource heavily!",
				new ModPanel());

		Settings.isDailyRun = false;
		Settings.isTrial = false;
		Settings.isDemo = false;

		l.debug("/receivePostInitialize");
	}

}

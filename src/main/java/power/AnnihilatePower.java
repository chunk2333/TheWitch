package power;
//湮灭卡牌能力
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AnnihilatePower extends AbstractPower {

    public static final String POWER_ID = "AnnihilatePower";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("AnnihilatePower");

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static TextureAtlas atlas;

    public static TextureAtlas atlas_self;

    public AnnihilatePower(AbstractCreature owner, int amt) {
        super();
        atlas_self = new TextureAtlas(Gdx.files.internal("powers/Selfpowers.atlas"));
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;
        updateDescription();
        this.region48 = atlas_self.findRegion("48/EyesHeartPower");
        this.region128 = atlas_self.findRegion("128/EyesHeartPower");
        this.type = AbstractPower.PowerType.DEBUFF;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        switch (power.ID){
            case "GeoPower":
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
                break;
            case "PyroPower":
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
                break;
            case "HydroPower":
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
                break;
            default:
                break;
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}

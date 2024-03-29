package relics.Witch;
//滋水枪
import TheWitch.TheWitch;
import Tools.YiBaHelper;
import actions.AnemoAction;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import power.GeoPower;
import power.HydroPower;
import power.PyroPower;

import java.util.ArrayList;

public class WaterGun extends CustomRelic {

    public static final String ID = TheWitch.makeID("WaterGun");

    private static final String IMG = "img/relics/WaterGun.png";

    private static final String IMG_OTL = "img/relics/outline/WaterGun.png";

    public WaterGun() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(!targetCard.hasTag(TheWitch.ELEMENT) & useCardAction.target != null){
            int random;
            ArrayList<AbstractMonster> list = new ArrayList();
            random = AbstractDungeon.relicRng.random(1,4); //随机数
            AbstractMonster m = (AbstractMonster) useCardAction.target;
            if(m == null){
                for(AbstractMonster mo :AbstractDungeon.getCurrRoom().monsters.monsters){
                    if(!mo.isDead){
                        list.add(mo);
                    }
                }
            }

            if(list.size() == 0 & m == null){
                return;
            }

            if(list.size() != 0){
                m = list.get(AbstractDungeon.cardRandomRng.random(0,list.size()));
            }

            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            switch (random){
                case 1:
                    addToBot(new AnemoAction(m));
                    break;
                case 2:
                    addToBot(new ApplyPowerAction(m, m, new PyroPower(m, YiBaHelper.getPlayerMystery()), 1));
                    break;
                case 3:
                    addToBot(new ApplyPowerAction(m, m, new HydroPower(m, YiBaHelper.getPlayerMystery()), 1));
                    break;
                case 4:
                    addToBot(new ApplyPowerAction(m, m, new GeoPower(m, YiBaHelper.getPlayerMystery()), 1));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WaterGun();
    }


}

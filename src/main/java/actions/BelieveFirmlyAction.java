package actions;

import TheWitch.TheWitch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BelieveFirmlyAction extends AbstractGameAction {
    private int blockGain;

    public BelieveFirmlyAction(int blockGain) {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.blockGain = blockGain;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.hasTag(TheWitch.ELEMENT)){
                AbstractDungeon.player.gainEnergy(this.blockGain);
                break;
            }
        }
        this.isDone = true;
    }
}

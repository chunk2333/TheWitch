package cards.element;
//潜能解放
import TheWitch.TheWitch;
import Tools.YiBaHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patchs.AbstractCardEnum;
import power.MysteryPower;

public class PotentialLiberation extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(TheWitch.makeID("PotentialLiberation"));

    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "img/cards/witch/PotentialLiberation.png";

    private static final int COST = 2;

    public static final String ID = TheWitch.makeID("PotentialLiberation");

    public PotentialLiberation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Witch_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 200;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        int num;
        num = YiBaHelper.getPlayerMystery();
        if(this.upgraded){
            num = num * 2;
        }else {
            num = (int) (num * 1.5);
        }
        addToBot(new ApplyPowerAction(p, p, new MysteryPower(p, num), num));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new PotentialLiberation();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(50);
        }
    }
}

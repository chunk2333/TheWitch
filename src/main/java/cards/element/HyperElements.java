package cards.element;
//高浓度元素
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
import power.GeoPower;
import power.PyroPower;

public class HyperElements extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(TheWitch.makeID("HyperElements"));

    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "img/cards/witch/HyperElements.png";

    private static final int COST = 1;

    public static final String ID = TheWitch.makeID("HyperElements");

    public HyperElements() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Witch_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = true;
        this.tags.add(TheWitch.ELEMENT);
        this.tags.add(TheWitch.GEO);
        this.tags.add(TheWitch.PYRO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        //给予火
        addToBot(new ApplyPowerAction(m, m, new PyroPower(m, YiBaHelper.getPlayerMystery()), 1));
        //给予岩
        addToBot(new ApplyPowerAction(m, m, new GeoPower(m, YiBaHelper.getPlayerMystery()), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new HyperElements();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

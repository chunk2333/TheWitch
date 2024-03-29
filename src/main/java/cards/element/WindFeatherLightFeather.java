package cards.element;
//风翎光羽
import TheWitch.TheWitch;
import actions.AnemoAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patchs.AbstractCardEnum;

public class WindFeatherLightFeather extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(TheWitch.makeID("WindFeatherLightFeather"));

    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "img/cards/witch/WindFeatherLightFeather.png";

    private static final int COST = 1;

    public static final String ID = TheWitch.makeID("WindFeatherLightFeather");

    public WindFeatherLightFeather() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Witch_COLOR, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 1;
        this.damage = this.baseDamage;
        this.tags.add(TheWitch.ELEMENT);
        this.tags.add(TheWitch.ANEMO);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        int drawCardNum;
        boolean canAnemo = m.hasPower("PyroPower") || m.hasPower("HydroPower");
        //判断是否能扩散成功
        if(canAnemo){
            drawCardNum = this.magicNumber + this.damage;
        }else {
            drawCardNum = this.magicNumber;
        }
        //扩散
        addToBot(new AnemoAction(m));
        //抽卡
        addToBot(new DrawCardAction(AbstractDungeon.player, drawCardNum));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new WindFeatherLightFeather();
    }

    @Override
    public void applyPowers() {}

    @Override
    public void calculateCardDamage(AbstractMonster mo) {}

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            //upgradeDamage(1);
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}

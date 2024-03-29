package cards.element;
//弄巧成拙
import TheWitch.TheWitch;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patchs.AbstractCardEnum;
import power.MysteryPower;

public class Clumsy_My extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(TheWitch.makeID("Clumsy_My"));

    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "img/cards/witch/Clumsy_My.png";

    private static final int COST = 1;

    public static final String ID = TheWitch.makeID("Clumsy_My");

    public Clumsy_My() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Witch_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //抽牌
        addToBot(new DrawCardAction(p, this.magicNumber));
        //失去元素精通
        addToBot(new ApplyPowerAction(p, p, new MysteryPower(p, -1), -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Clumsy_My();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}

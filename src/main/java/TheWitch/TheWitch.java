package TheWitch;

import Tools.YiBaHelper;
import basemod.BaseMod;
import basemod.interfaces.*;
import cards.colorless.*;
import characters.Witch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patchs.AbstractCardEnum;
import cards.element.*;
import patchs.ThmodClassEnum;
import relics.Witch.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpireInitializer
public class TheWitch implements EditCharactersSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, PostEnergyRechargeSubscriber, AddAudioSubscriber{
    public static String modID = "TheWitch";

    private static final String MOD_BADGE = "img/ui/badge.png";
    //攻击、技能、能力牌的背景图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_SELES_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_SELES_s.png";
    private static final String POWER_CC = "img/512/bg_power_SELES_s.png";
    private static final String ENERGY_ORB_CC = "img/512/SELESOrb.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SELES.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SELES.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SELES.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/SELESOrb.png";
    public static final String CARD_ENERGY_ORB = "img/ui/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/SelesButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/SelesPortrait.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private final ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();
    public static final Logger logger = LogManager.getLogger(TheWitch.class.getName());

    @SpireEnum public static AbstractCard.CardTags ELEMENT;

    @SpireEnum public static AbstractCard.CardTags ANEMO;

    @SpireEnum public static AbstractCard.CardTags GEO;

    @SpireEnum public static AbstractCard.CardTags HYDRO;

    @SpireEnum public static AbstractCard.CardTags PYRO;
    public static String makeID(String id){
        return modID + ":" + id;
    }

    public TheWitch(){
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.Witch_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT, POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter(new Witch("魔女"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ThmodClassEnum.Witch_CLASS);
    }

    public static void initialize() {
        new TheWitch();
    }

    private void loadCardsToAdd() {
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Strike_Seles());//基础打击
        this.cardsToAdd.add(new Defend_Seles());//基础防御
        this.cardsToAdd.add(new FireBall());//火球术
        this.cardsToAdd.add(new WaterWaveTechnique());//水波术
        this.cardsToAdd.add(new MeleeAttack());//近身攻击
        this.cardsToAdd.add(new Explosion());//爆裂魔法
        this.cardsToAdd.add(new Clumsy_My());//弄巧成拙
        this.cardsToAdd.add(new WaterMirror());//水镜
        this.cardsToAdd.add(new NairaDescends());//奈落降临
        this.cardsToAdd.add(new LookingGlass());//镜花水月
        this.cardsToAdd.add(new DeathComes());//死神降临
        this.cardsToAdd.add(new InflammationOfTheWheel());//转轮之炎
        this.cardsToAdd.add(new Relearn());//重修
        this.cardsToAdd.add(new WaterProficiency());//水精通
        this.cardsToAdd.add(new FlamingCharge());//烈焰冲锋
        this.cardsToAdd.add(new FastFire());//快速火焰
        this.cardsToAdd.add(new FastWater());//快速水纹
        this.cardsToAdd.add(new PotentialLiberation());//潜能解放
        this.cardsToAdd.add(new LandJurisdiction());//大地管辖
        this.cardsToAdd.add(new MagicShield());//魔法护盾
        this.cardsToAdd.add(new FlamingStorm());//烈焰风暴
        this.cardsToAdd.add(new FlamingHoard());//烈焰囤积
        this.cardsToAdd.add(new MagicLoop());//魔力循环
        this.cardsToAdd.add(new SpiritualDomination());//精神支配
        //this.cardsToAdd.add(new AffinityMorphology());//亲和形态
        this.cardsToAdd.add(new MagicPress());//魔力压榨
        this.cardsToAdd.add(new TransformingFeatherIntoSpirit());//化羽为灵
        this.cardsToAdd.add(new Chant());//吟唱
        this.cardsToAdd.add(new GaleSpell());//疾风术
        this.cardsToAdd.add(new BladeOfWind());//风刃剑术
        this.cardsToAdd.add(new MagicalProgress());//魔力精进
        this.cardsToAdd.add(new BouncingWaterPolo());//弹跳水球
        this.cardsToAdd.add(new WindFeatherLightFeather());//风翎光羽
        this.cardsToAdd.add(new PenetrationWaterGun());//贯穿水枪
        this.cardsToAdd.add(new MagicFeedback());//魔力反馈
        this.cardsToAdd.add(new AngrySea());//怒海狂涛
        this.cardsToAdd.add(new ExercisePatience());//忍耐
        this.cardsToAdd.add(new ElementPunch());//元素拳
        this.cardsToAdd.add(new DancingFireAndFlowingWind());//舞火流风
        this.cardsToAdd.add(new TheSage());//贤者
        this.cardsToAdd.add(new SimpleMagic());//简易魔法
        this.cardsToAdd.add(new LiquidFire());//液态火
        this.cardsToAdd.add(new PleaseKeepBack());//生人勿近
        this.cardsToAdd.add(new SoftWaterShield());//柔水盾
        this.cardsToAdd.add(new OrangeRoad());//古灵精怪
        this.cardsToAdd.add(new StealthFogColor());//隐身雾色
        this.cardsToAdd.add(new TheRoarOfAHurricane());//飓风呼啸
        this.cardsToAdd.add(new ShakeTheWorld());//掀天揭地
        this.cardsToAdd.add(new ThousandCragMyriadRavine());//千岩万壑
        this.cardsToAdd.add(new Penetrate());//识破
        this.cardsToAdd.add(new FranticAndChaotic());//手忙脚乱
        this.cardsToAdd.add(new InstantKill());//瞬杀
        this.cardsToAdd.add(new PositiveFeedback());//正反馈 -> 亲和形态
        this.cardsToAdd.add(new Annihilate());//湮灭
        this.cardsToAdd.add(new HyperElements());//高浓度元素
        this.cardsToAdd.add(new MagicOverflow());//魔力溢出
        this.cardsToAdd.add(new CrazyHurricaneCut());//狂飓切裂
        this.cardsToAdd.add(new BelieveFirmly());//深信不疑
        this.cardsToAdd.add(new Earthshaking());//石破天惊
        this.cardsToAdd.add(new ElementalRefining());//元素精炼
        this.cardsToAdd.add(new Learn());//现学
        this.cardsToAdd.add(new WandStrike());//魔杖敲击
        this.cardsToAdd.add(new SuddenKick());//突然一jio
        this.cardsToAdd.add(new InstantCutting());//瞬身切割
        this.cardsToAdd.add(new BeAlmostWipedOut());//殆尽
        this.cardsToAdd.add(new OmnipotentAndOmniscient());//全能全知
        this.cardsToAdd.add(new RegardMoneyAsFate());//视金如命
        this.cardsToAdd.add(new ObserverBehavior());//观察者行为
        this.cardsToAdd.add(new FallToHeaven());//堕天
        this.cardsToAdd.add(new MagicRedStone());//红色魔法石
        this.cardsToAdd.add(new MagicGreenStone());//绿色魔法石
        this.cardsToAdd.add(new MagicYellowStone());//黄色魔法石
        this.cardsToAdd.add(new MagicWhiteStone());//白色魔法石
        this.cardsToAdd.add(new MagicStoneMining());//魔法石开采
        this.cardsToAdd.add(new AdvancedTrainingTechniques());//高等练成术
        this.cardsToAdd.add(new SlashAndBurn());//火耕水耨
        this.cardsToAdd.add(new ReconstructEverything());//重构万物
        this.cardsToAdd.add(new Tide());//潮水啊，我已归来

    }

    @Override
    public void receiveEditCards() {
        //将卡牌批量添加
        loadCardsToAdd();
        for (AbstractCard card : this.cardsToAdd) {
            BaseMod.addCard(card);
        }
    }

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void receiveEditKeywords() {
        //加载关键词
        String key_WorldFilePatch = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            //zhs
            key_WorldFilePatch = "localization/keywords-zh.json";
        }
        Gson gson = new Gson();
        String json = Gdx.files.internal(key_WorldFilePatch).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null)
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.NAMES, keyword.DESCRIPTION);
            }
    }

    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本
        String relic = "", card = "", power = "", potion = "", event = "", ui = "", monster = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/cards-zh.json";
            relic = "localization/relics-zh.json";
            power = "localization/powers-zh.json";
            potion = "localization/potions-zh.json";
            //event = "localization/events-zh.json";
            ui = "localization/uis-zh.json";
            //monster = "localization/monsters-zh.json";
        }  //其他语言配置的JSON

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//        String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        String uiStrings = Gdx.files.internal(ui).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
//        String monsterStrings = Gdx.files.internal(monster).readString(String.valueOf(StandardCharsets.UTF_8));
//        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);

    }

    @Override
    public void receiveEditRelics() {
        //---------------魔女遗物-----------------------
        //BaseMod.addRelicToCustomPool(new TestTriggerElement(), AbstractCardEnum.Witch_COLOR);//元素反应测试遗物
        BaseMod.addRelicToCustomPool(new cLanguageProgramBegin(), AbstractCardEnum.Witch_COLOR);
        BaseMod.addRelicToCustomPool(new HighLevelMagicBook(), AbstractCardEnum.Witch_COLOR); //很高级的魔导书----魔女专属Boss遗物
        BaseMod.addRelicToCustomPool(new NaturalQuenchedStaff(), AbstractCardEnum.Witch_COLOR); //自然淬炼之杖----魔女专属稀有遗物
        BaseMod.addRelicToCustomPool(new TheLastCoin(), AbstractCardEnum.Witch_COLOR); //最后的一枚硬币
        BaseMod.addRelicToCustomPool(new BlackCat(), AbstractCardEnum.Witch_COLOR); //黑猫
        BaseMod.addRelicToCustomPool(new Fructose(), AbstractCardEnum.Witch_COLOR); //果儿糖
        BaseMod.addRelicToCustomPool(new AlternateDimensionalPocket(), AbstractCardEnum.Witch_COLOR); //异次元口袋
        BaseMod.addRelicToCustomPool(new WaterGun(), AbstractCardEnum.Witch_COLOR); //滋水枪
        //---------------魔女遗物-----------------------
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(YiBaHelper.MakeSoundPath("DaMie"),"sound/AatroxR.ogg");
    }

    @Override
    public void receivePostEnergyRecharge() {

        for (AbstractCard c : recyclecards) {
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }
}


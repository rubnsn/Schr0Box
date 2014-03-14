package ruby.bamboo;

import java.io.File;
import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.Loader;

public class Config {
    public static boolean windPushPlayer;
    public static boolean timeAccel;
    public static int maxExplosionLv;
    public static boolean exDrop;
    public static int deludeTexMaxReference;
    public static int deludeMaxReference;
    public static boolean addMagatama;
    public static boolean useMagatama;
    public static boolean isStopUpdataSnow;
    public static boolean isStopUpdataIce;
    public static Configuration configuration;
    public static final String CATEGORY_BAMBOO = "bamboosettings";
    public static HashMap<String, Integer> maxValue;
    static {
        maxValue = new HashMap<String, Integer>();
        windPushPlayer = true;
        timeAccel = true;
        maxExplosionLv = 3;
        exDrop = false;
        deludeTexMaxReference = 2;
        deludeMaxReference = 2;
        useMagatama = false;
        isStopUpdataSnow = false;
        isStopUpdataIce = false;
        serverInit();
    }

    private static void serverInit() {
        File file = new File(Loader.instance().getConfigDir(), "BambooConfig.cfg");
        configuration = new Configuration(file);
        configuration.load();
        Property prop;
        // 風えんてて
        prop = configuration.get(CATEGORY_BAMBOO, "WindPushPlayer", true);
        prop.comment = "EntityWind knock back for EntityPlayer";
        windPushPlayer = prop.getBoolean(true);
        // ふとんかそく
        prop = configuration.get(CATEGORY_BAMBOO, "TimeAccel", true);
        prop.comment = "Acceleration time by huton";
        timeAccel = prop.getBoolean(true);
        // ばくちくれべる
        maxValue.put("MaxExplosionLv", 3);
        prop = configuration.get(CATEGORY_BAMBOO, "MaxExplosionLv", 3);
        prop.comment = "Possible level firecracker explosion 0~3";
        maxExplosionLv = prop.getInt();
        // 後入れ救済
        prop = configuration.get(CATEGORY_BAMBOO, "ReliefItem", false);
        prop.comment = "Weed to drop a Sakura and Takenoko(Disabled requires a reboot)";
        exDrop = prop.getBoolean(false);
        // 雪を溶かさない
        prop = configuration.get(CATEGORY_BAMBOO, "UpdateStopSnow", false);
        prop.comment = "Not melt snow with light";
        isStopUpdataSnow = !prop.getBoolean(false);
        // 氷溶かさない
        prop = configuration.get(CATEGORY_BAMBOO, "UpdateStopIce", false);
        prop.comment = "Not melt ice with light";
        isStopUpdataIce = !prop.getBoolean(false);
        // テクスチャ最大参照
        maxValue.put("DeludeMaxTexReference", 2);
        prop = configuration.get(CATEGORY_BAMBOO, "DeludeMaxTexReference", 2);
        prop.comment = "Delude texture max reference";
        deludeTexMaxReference = prop.getInt();
        // 右クリック最大参照
        maxValue.put("DeludeMaxRightClickReference", 2);
        prop = configuration.get(CATEGORY_BAMBOO, "DeludeMaxRightClickReference", 2);
        prop.comment = "Delude right click max reference";
        deludeMaxReference = prop.getInt();
        // 勾玉をダンジョンチェストへ生成するか
        prop = configuration.get(CATEGORY_BAMBOO, "AddMagatama", true);
        prop.comment = "Add magatama to DunsionChests";
        addMagatama = prop.getBoolean(true);
        // 勾玉を使用できるか
        prop = configuration.get(CATEGORY_BAMBOO, "UseMagatama", false);
        prop.comment = "!!!This item is to erode the terrain greatly!!!";
        useMagatama = prop.getBoolean(false);
        configuration.save();
        reloadConfig();
    }

    public static void reloadConfig() {
        if (exDrop) {
            MinecraftForge.addGrassSeed(new ItemStack(BambooInit.takenoko), 10);
            MinecraftForge.addGrassSeed(new ItemStack(BambooInit.sakura), 10);
        }

        Blocks.snow.setTickRandomly(isStopUpdataSnow);
        Blocks.ice.setTickRandomly(isStopUpdataIce);
    }

}

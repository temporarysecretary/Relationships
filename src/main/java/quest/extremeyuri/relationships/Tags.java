package quest.extremeyuri.relationships;

import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.RecipeChoice;

public class Tags {
    // Tag for CROWN MATERIALS. Please only use RecipeChoice.MaterialChoice WEDDING_CROWNS!!!
    private static final Material[] CROWN_MATERIALS = {Material.DIAMOND, Material.EMERALD, Material.AMETHYST_SHARD,
            Material.END_CRYSTAL,};
    private static final Tag<Material> CROWN_TAG = new MaterialSetTag(new NamespacedKey(Relationships.getInstance(), "crown_materials"), CROWN_MATERIALS);
    public static final RecipeChoice.MaterialChoice WEDDING_CROWNS = new RecipeChoice.MaterialChoice(CROWN_TAG);

    // Tag for BAND MATERIALS. Please only use RecipeChoice.MaterialChoice WEDDING_BANDS!!!
    private static final Material[] BAND_MATERIALS = {Material.GOLD_NUGGET, Material.IRON_NUGGET};
    private static final Tag<Material> BAND_TAG = new MaterialSetTag(new NamespacedKey(Relationships.getInstance(), "band_materials"), BAND_MATERIALS);
    public static final RecipeChoice.MaterialChoice WEDDING_BANDS = new RecipeChoice.MaterialChoice(BAND_TAG);

    // Material list for every flower except for Wither Rose. Used for love letters.
    private static final Material[] NICE_FLOWERS = {Material.ALLIUM, Material.AZURE_BLUET, Material.BLUE_ORCHID,
    Material.CORNFLOWER, Material.DANDELION, Material.LILAC, Material.LILY_OF_THE_VALLEY, Material.ORANGE_TULIP,
    Material.OXEYE_DAISY, Material.PEONY, Material.PINK_TULIP, Material.POPPY, Material.RED_TULIP, Material.SUNFLOWER,
    Material.TORCHFLOWER, Material.ROSE_BUSH, Material.WHITE_TULIP};
    private static final Tag<Material> NICE_FLOWER_TAG = new MaterialSetTag(new NamespacedKey(Relationships.getInstance(), "nice_flowers"), NICE_FLOWERS);
    public static final RecipeChoice.MaterialChoice LOVE_FLOWERS = new RecipeChoice.MaterialChoice(NICE_FLOWER_TAG);
}

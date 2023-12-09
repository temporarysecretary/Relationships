package quest.extremeyuri.relationships;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import quest.extremeyuri.relationships.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Recipes {
    static Server server = Bukkit.getServer();
    static Logger logger = Bukkit.getLogger();

    // Wedding ring Components to be used as item name and description...
    private static final Component weddingRingDispName = Component.text("Wedding Ring").color(TextColor.color(0x19CDFF))
            .decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    private static final List<Component> weddingRingDesc = new ArrayList<Component>();

    // Declaring relevant pieces of: weddingRing
    private static ShapedRecipe weddingRingRecipe;
    public static final NamespacedKey weddingRingKey = getKey("wedding_ring");
    public static ItemStack weddingRing = new ItemStack(Material.TRIPWIRE_HOOK,1);
    public static ItemMeta weddingRingMeta = weddingRing.getItemMeta();

    private static void makeWeddingRing(){
        // Wedding ring item stack.
        // Lore...
        weddingRingDesc.add(Component.text("Give this wedding ring to"));
        weddingRingDesc.add(Component.text("someone you really love to"));
        weddingRingDesc.add(Component.text("commemorate your eternal bond!"));

        // Defining ItemMeta for the weddingRing.
        weddingRingMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        weddingRingMeta.addEnchant(Enchantment.ARROW_DAMAGE,1,false);
        weddingRingMeta.displayName(weddingRingDispName);
        weddingRingMeta.lore(weddingRingDesc);
        weddingRingMeta.getPersistentDataContainer().set(weddingRingKey, PersistentDataType.STRING,"Wedding ring");
        weddingRing.setItemMeta(weddingRingMeta);

        // Wedding ring recipe.
        weddingRingRecipe = new ShapedRecipe((getKey("wedding_ring_recipe")), weddingRing);
        weddingRingRecipe.shape("XDX","GXG","XGX").setIngredient('D',Tags.WEDDING_CROWNS);
        weddingRingRecipe.shape("XDX","GXG","XGX").setIngredient('G',Tags.WEDDING_BANDS);
        weddingRingRecipe.setIngredient('X',Material.AIR);
        weddingRingRecipe.setCategory(CraftingBookCategory.MISC);
    }

    // Love letter.
    // Components for title and lore...
    private static final Component loveLetterDispName = Component.text("Love Letter").color(TextColor.color(0xdb2e0b))
            .decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    private static final List<Component> loveLetterDesc = new ArrayList<Component>();

    // Declaring relevant pieces of: divorce papers
    private static ShapelessRecipe loveLetterRecipe;
    public static final NamespacedKey loveKey = getKey("love_letter");
    public static ItemStack loveLetter = new ItemStack(Material.PAPER,1);
    public static ItemMeta loveLetterMeta = loveLetter.getItemMeta();

    private static void makeLoveLetters(){
        // Lore...\
        loveLetterDesc.add(Component.text("Hand this to someone you like"));
        loveLetterDesc.add(Component.text("and let them know how you feel~!"));

        // Item meta...
        loveLetterMeta.displayName(loveLetterDispName);
        loveLetterMeta.lore(loveLetterDesc);
        loveLetterMeta.getPersistentDataContainer().set(loveKey, PersistentDataType.STRING,"Love letter");
        loveLetter.setItemMeta(loveLetterMeta);

        // Tha Recipe
        loveLetterRecipe = new ShapelessRecipe(getKey("love_letter_recipe"),loveLetter);
        loveLetterRecipe.addIngredient(Material.PAPER);
        loveLetterRecipe.addIngredient(Tags.LOVE_FLOWERS);
        loveLetterRecipe.addIngredient(Material.RED_DYE);
        loveLetterRecipe.setCategory(CraftingBookCategory.MISC);
    }


    // Divorce papers.
    // Components for title and lore...
    private static final Component divorcePapersDispName = Component.text("Divorce Papers").color(TextColor.color(0xdb2e0b))
            .decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    private static final List<Component> divorcePapersDesc = new ArrayList<Component>();

    // Declaring relevant pieces of: divorce papers
    private static ShapelessRecipe divorceRecipe;
    public static final NamespacedKey divorceKey = getKey("divorce_papers");
    public static ItemStack divorcePapers = new ItemStack(Material.PAPER,1);
    public static ItemMeta divorcePapersMeta = divorcePapers.getItemMeta();

    private static void makeDivorcePapers(){
        // Lore...
        divorcePapersDesc.add(Component.text("Break their heart and ").append(Component.text("end").color(TextColor.color(0xdb2e0b)).decorate(TextDecoration.BOLD)));
        divorcePapersDesc.add(Component.text("your relationship..."));
        divorcePapersDesc.add(Component.text("(even if it never started)"));
        divorcePapersDesc.add(Component.text("Warning! Heartbreak hurts!").color(TextColor.fromHexString("db2e0b")).decorate(TextDecoration.BOLD));

        divorcePapersMeta.displayName(divorcePapersDispName);
        divorcePapersMeta.lore(divorcePapersDesc);
        divorcePapersMeta.getPersistentDataContainer().set(divorceKey, PersistentDataType.STRING,"Divorce papers");
        divorcePapers.setItemMeta(divorcePapersMeta);

        divorceRecipe = new ShapelessRecipe(getKey("divorce_paper_recipe"),divorcePapers);
        divorceRecipe.addIngredient(Material.PAPER);
        divorceRecipe.addIngredient(Material.WITHER_ROSE);
        divorceRecipe.addIngredient(Material.BLACK_DYE);
        divorceRecipe.setCategory(CraftingBookCategory.MISC);
    }

    public static void addRecipes(){
        makeWeddingRing();
        if(server.addRecipe(weddingRingRecipe)){
            logger.info("Recipe added: wedding ring.");
        }
        else{
            logger.info("ERR: WR Recipe not added.");
        }
        makeDivorcePapers();
        if(server.addRecipe(divorceRecipe)){
            logger.info("Recipe added: divorce papers.");
        }
        else{
            logger.info("ERR: DP Recipe not added.");
        }
        makeLoveLetters();
        if(server.addRecipe(loveLetterRecipe)){
            logger.info("Recipe added: love letters.");
        }
        else{
            logger.info("ERR: LL Recipe not added.");
        }
    }

    // Function for returning NamespacedKey for any item, given item name as specified.
    // Probably useful for instantiating new ItemStacks and Recipes?
    private static @NotNull NamespacedKey getKey(String entryName){
        NamespacedKey returnKey;
        returnKey = new NamespacedKey(Relationships.getInstance(), entryName);
        return returnKey;
    }
}


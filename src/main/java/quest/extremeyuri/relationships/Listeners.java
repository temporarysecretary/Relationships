package quest.extremeyuri.relationships;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

import static quest.extremeyuri.relationships.Recipes.*;

public class Listeners implements Listener {

    static Logger logger = Bukkit.getLogger();

    public Listeners() {
        logger.info("Now listening for shitty block placement!");
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockCanBuildEvent e) {
        if (e.isBuildable()) {
            Player player = e.getPlayer();
            PlayerInventory playerInventory = player.getInventory();
            ItemStack playerHand = playerInventory.getItem(e.getHand());
            try {
                ItemMeta activeItem = playerHand.getItemMeta();
                boolean isWeddingRing = activeItem.getPersistentDataContainer().has(weddingRingKey);
                if (isWeddingRing) {
                    logger.info("Player " + player.getName() + " just tried to place a wedding ring!");
                    e.setBuildable(false);
                    playerInventory.setItemInMainHand(playerHand);
                }
            } catch (NullPointerException nullPointerException) {
                logger.info("No ItemMeta!");
            }
        } else {
            e.setBuildable(false);
        }
    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        ItemStack playerHand = playerInventory.getItem(e.getHand());

        if(playerHand.hasItemMeta()) {
            // Test to see if player has Relationship item in their hand
            ItemMeta activeItem = playerHand.getItemMeta();
            boolean isWeddingRing = activeItem.getPersistentDataContainer().has(weddingRingKey);
            boolean isLoveLetter = activeItem.getPersistentDataContainer().has(loveKey);
            boolean isDivorcePaper = activeItem.getPersistentDataContainer().has(divorceKey);
            boolean isRelationshipItem = isWeddingRing || isLoveLetter || isDivorcePaper;

            // If they do have a Relationship item in their hand, let's do the following:
            if (isRelationshipItem) {
                // First, let's check to see if what they're trying to interact with is a player
                if (!(e.getRightClicked() instanceof Player)) {
                    e.setCancelled(true); // Cancel the event if not
                    player.sendMessage(Component.text("Hey, you might not wanna do that...").color(TextColor.color(0xf55154)));
                }
                // Otherwise, let's proceed
                else {
                    // Series of stuff that'll happen for wedding rings:
                    if (isWeddingRing) {

                    }
                    // Series of stuff that'll happen for love letters:
                    else if (isDivorcePaper) {

                    }
                    // Series of stuff that'll happen for divorce papers:
                    else if (isLoveLetter) {

                    }
                }
            }
        }
    }
}

package quest.extremeyuri.relationships;

import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

import static java.lang.Math.*;
import static quest.extremeyuri.relationships.ComponentStyles.*;
import static quest.extremeyuri.relationships.Recipes.*;
import quest.extremeyuri.relationships.UtilityFunctions;

public class Listeners implements Listener {

    static Logger logger = Bukkit.getLogger();

    public Listeners() {
        logger.info("[Relationships] Now listening for wedding mistake placement!");
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
                    logger.info("[Relationships] Player " + player.getName() + " just tried to place a wedding ring!");
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
                // First, let's check to see if what they're trying to interact with is NOT a player
                if (!(e.getRightClicked() instanceof Player)) {
                    // If it's not a player, is it an Entity that represents a living thing?
                    if(e.getRightClicked() instanceof LivingEntity){
                        // DEBUG! E
                        Location entityLocation = e.getRightClicked().getLocation();
                        Location playerLocation = player.getLocation();
                        player.setVelocity(UtilityFunctions.getRepulsionVector(playerLocation, entityLocation));
                        e.getRightClicked().setVelocity(UtilityFunctions.getRepulsionVector(entityLocation, playerLocation));
                        // e.setCancelled(true); // Cancel the event if so
                        // player.sendMessage(Component.text("Hey, you might not wanna do that...").color(TextColor.color(0xf55154)));
                    }
                }

                // Otherwise, let's proceed
                else {
                    // Series of stuff that'll happen for wedding rings:
                    if (isWeddingRing) {
                        Actions.weddingRingIntActions(e.getPlayer(),(Player) e.getRightClicked());
                    }
                    // Series of stuff that'll happen for love letters:
                    else if (isLoveLetter) {
                        Actions.loveLetterIntActions(e.getPlayer(),(Player) e.getRightClicked());
                    }
                    // Series of stuff that'll happen for divorce papers:
                    else {
                        Actions.divPaperIntActions(e.getPlayer(),(Player) e.getRightClicked());
                    }
                }
            }
        }
    }

}

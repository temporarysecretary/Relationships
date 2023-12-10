package quest.extremeyuri.relationships;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import static java.lang.Math.*;

public class UtilityFunctions {

    public static void consumeItem(Player player){
        PlayerInventory playerInventory  = player.getInventory();
        ItemStack playerHand = playerInventory.getItem(EquipmentSlot.HAND);
        playerHand.setAmount(playerHand.getAmount() - 1);
        playerInventory.setItemInMainHand(playerHand);
    }

    private void giveItem(Player sendingPlayer, Player receivingPlayer){
        PlayerInventory sendInventory = sendingPlayer.getInventory();
        PlayerInventory receiveInventory = receivingPlayer.getInventory();
        ItemStack sentItem = sendInventory.getItem(EquipmentSlot.HAND);
        receiveInventory.addItem(sentItem);
    }

    public static double calcFalloff(double effectiveRadius, double distanceFromTarget){
        double falloff = pow(((effectiveRadius - distanceFromTarget)/effectiveRadius),2);
        if(falloff < 0){
            return 0;
        }
        else {
            return falloff;
        }
    }

    public static Vector getRepulsionVector(Location player, Location source){
        // I wanted the repulsion resulting from a divorce to be more dramatic,
        // so here we are. Might also be useful for other things.

        // Let's assume a basic component strength and imagine we're applying a force \\
        Vector repulsionVector = new Vector();
        double vectorStrength = 1;
        int radius = 4;

        // Calculating the direction of the vector
        double xDistance = player.getX() - source.getX();
        double yDistance = player.getY() - source.getY() + 1;
        double zDistance = player.getZ() - source.getZ();
        double xzDistance = sqrt(pow(xDistance, 2) + pow(zDistance, 2));

        double xzAngle = atan2(zDistance,xDistance);
        double finalAngle = atan2(yDistance,xzDistance);
        double yComponent = vectorStrength * sin(finalAngle) * calcFalloff(radius,xzDistance);
        double xzComponent = vectorStrength * cos(finalAngle);
        double xComponent = xzComponent * cos(xzAngle) * calcFalloff(radius,xzDistance);
        double zComponent = xzComponent * sin(xzAngle) * calcFalloff(radius,xzDistance);

        repulsionVector.setX(xComponent).setY(yComponent).setZ(zComponent);

        return repulsionVector;
    }

}

package quest.extremeyuri.relationships;

import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import static quest.extremeyuri.relationships.ComponentStyles.*;
import static quest.extremeyuri.relationships.Recipes.server;

public class Actions {

    public static void weddingRingIntActions(Player sendingPlayer, Player receivingPlayer){
        // Let sendingPlayer know they've given their receivingPlayer the ring...
        sendingPlayer.sendMessage(Component.text("You've given " + receivingPlayer.getName() + " your ")
                .append(Component.text("wedding ring!").decorate(TextDecoration.BOLD).style(weddingRingStyle)));
        // Let receivingPlayer know they've been given a wedding ring...
        receivingPlayer.sendMessage(
                Component.text(sendingPlayer.getName() + " ").style(weddingRingStyle).decorate(TextDecoration.BOLD)
                        .append(Component.text("just "))
                        .append(Component.text("proposed ").style(loveLetterStyle).decorate(TextDecoration.BOLD))
                        .append(Component.text("to you! Will you marry them?")));
        // Take ring from sendingPlayer's inventory, if they're not in creative mode.
        if(sendingPlayer.getGameMode() != GameMode.CREATIVE){
            UtilityFunctions.consumeItem(sendingPlayer);
        }
        // TODO: Actually add a marriage table to the MySQL database...
    }
    public static void loveLetterIntActions(Player sendingPlayer, Player receivingPlayer){
        // Let sendingPlayer know they've given their receivingPlayer the love letter...
        sendingPlayer.sendMessage(Component.text("You've given " + receivingPlayer.getName() + " your ")
                .append(Component.text("love letter!").decorate(TextDecoration.BOLD).style(loveLetterStyle)));
        // Let receivingPlayer know they've been given a love letter...
        receivingPlayer.sendMessage(
                Component.text(sendingPlayer.getName() + " ").style(loveLetterStyle).decorate(TextDecoration.BOLD)
                        .append(Component.text("just gave you their "))
                        .append(Component.text("love letter!").style(loveLetterStyle).decorate(TextDecoration.BOLD)));
        // Take letter from sendingPlayer's inventory, if they're not in creative mode.
        if(sendingPlayer.getGameMode() != GameMode.CREATIVE){
            UtilityFunctions.consumeItem(sendingPlayer);
        }
        // TODO: actually do something lmfoao
    }
    public static void divPaperIntActions(Player sendingPlayer, Player receivingPlayer){
        // Loudly announce the divorce to the whole server!
        server.sendMessage(Component.text(sendingPlayer.getName() + " and " + receivingPlayer.getName() + " just got ")
                .append(Component.text("divorced!").style(divorceStyle).decorate(TextDecoration.BOLD))
                .append(Component.text(" What a shame...")));
        // Blow both players up! (does knockback, shows visual, but no damage)
        ParticleBuilder explosion = new ParticleBuilder(Particle.EXPLOSION_HUGE).location(receivingPlayer.getLocation()).count(8).receivers(100)
                .source(receivingPlayer).spawn();
        // Knock them back!
        sendingPlayer.setVelocity(
                UtilityFunctions.getRepulsionVector(sendingPlayer.getLocation(), receivingPlayer.getLocation()));
        receivingPlayer.setVelocity(
                UtilityFunctions.getRepulsionVector(sendingPlayer.getLocation(), sendingPlayer.getLocation()));
        // ACTUALLY take some of their health!
        sendingPlayer.damage(4, receivingPlayer);
        receivingPlayer.damage(2, sendingPlayer);
        // Take divorce paper from sendingPlayer's inventory, if they're not in creative mode.
        if(sendingPlayer.getGameMode() != GameMode.CREATIVE){
            UtilityFunctions.consumeItem(sendingPlayer);
        }
        // TODO: Actually add a divorce table to the MySQL database...
    }
}

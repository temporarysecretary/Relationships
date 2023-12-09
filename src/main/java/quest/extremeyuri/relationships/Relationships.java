package quest.extremeyuri.relationships;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Relationships extends JavaPlugin {
    private static Plugin instance;
    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        Recipes.addRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package su.spyme.minetoearn;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
   
   private static Economy econ;

   public void onEnable() {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MineToEarn v1.0 by SPY_me enabled!");
      Bukkit.getServer().getPluginManager().registerEvents(this, this);
      this.setupEconomy();
      this.saveDefaultConfig();
   }

   public void onDisable() {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MineToEarn v1.0 by SPY_me disabled!");
   }

   private Boolean isCreative(Player player){
      return player.getGameMode() == GameMode.CREATIVE && this.getConfig().getBoolean("main.blockcreative");
   }

   private void setupEconomy() {
      RegisteredServiceProvider economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
      if(economyProvider != null) {
         econ = (Economy)economyProvider.getProvider();
      }

   }

   private static void addMoney(Player p, double count) {
      EconomyResponse r = econ.depositPlayer(p, count);
      r.transactionSuccess();
   }

   @EventHandler
   public void onInteract(BlockBreakEvent event){
      Player player = event.getPlayer();
      if(player.hasPermission(this.getConfig().getString("perm.mine"))){
         if(isCreative(player)){
            return;
         }
         switch(event.getBlock().getType()){
            case QUARTZ_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.QUARTZ_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.QUARTZ_ORE").replaceAll("&", "§"));
               return;
            case GOLD_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.GOLD_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.GOLD_ORE").replaceAll("&", "§"));
               return;
            case IRON_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.IRON_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.IRON_ORE").replaceAll("&", "§"));
               return;
            case COAL_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.COAL_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.COAL_ORE").replaceAll("&", "§"));
               return;
            case LAPIS_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.LAPIS_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.LAPIS_ORE").replaceAll("&", "§"));
               return;
            case DIAMOND_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.DIAMOND_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.DIAMOND_ORE").replaceAll("&", "§"));
               return;
            case REDSTONE_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.REDSTONE_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.REDSTONE_ORE").replaceAll("&", "§"));
               player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 2.0F, 1.0F);
               return;
            case EMERALD_ORE:
               Main.addMoney(player, this.getConfig().getDouble("pay.EMERALD_ORE"));
               player.sendActionBar(this.getConfig().getString("msg.EMERALD_ORE").replaceAll("&", "§"));
         }
      }
   }
}

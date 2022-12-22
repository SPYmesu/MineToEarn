package su.spyme.minetoearn;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
   
   private static Economy econ;

   public void onEnable() {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MineToEarn v1.1 by SPY_me enabled!");
      Bukkit.getServer().getPluginManager().registerEvents(this, this);
      this.setupEconomy();
      this.saveDefaultConfig();
   }

   public void onDisable() {
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MineToEarn v1.1 by SPY_me disabled!");
   }

   private Boolean isCreative(Player player){
      return player.getGameMode() == GameMode.CREATIVE && this.getConfig().getBoolean("main.blockcreative");
   }

   private void setupEconomy() {
      RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
      if(economyProvider != null) {
         econ = economyProvider.getProvider();
      }

   }

   private static void addMoney(Player p, double count) {
      EconomyResponse r = econ.depositPlayer(p, count);
      r.transactionSuccess();
   }

   @EventHandler
   public void onInteract(BlockBreakEvent event){
      Player player = event.getPlayer();
      if(player.hasPermission(getConfig().getString("perm.mine", "mte.miningmoney"))){
         if(isCreative(player)){
            return;
         }
         switch(event.getBlock().getType()){
            case EMERALD_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.EMERALD_ORE", 150));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.EMERALD_ORE", "&2MineToEarn &8» &e+150 coins &f(Emerald ore broken)").replaceAll("&", "§")));
            }
            case DIAMOND_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.DIAMOND_ORE", 120));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.DIAMOND_ORE", "&2MineToEarn &8» &e+120 coins &f(Diamond ore broken)").replaceAll("&", "§")));
            }
            case REDSTONE_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.REDSTONE_ORE", 100));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.REDSTONE_ORE", "&2MineToEarn &8» &e+100 coins &f(Redstone ore broken)").replaceAll("&", "§")));
            }
            case GOLD_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.GOLD_ORE", 80));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.GOLD_ORE", "&2MineToEarn &8» &e+80 coins &f(Gold ore broken)").replaceAll("&", "§")));
            }
            case IRON_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.IRON_ORE", 50));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.IRON_ORE", "&2MineToEarn &8» &e+50 coins &f(Iron ore broken)").replaceAll("&", "§")));
            }
            case COAL_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.COAL_ORE", 30));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.COAL_ORE", "&2MineToEarn &8» &e+30 coins &f(Coal ore broken)").replaceAll("&", "§")));
            }
            case LAPIS_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.LAPIS_ORE", 10));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.LAPIS_ORE", "&2MineToEarn &8» &e+10 coins &f(Lapis ore broken)").replaceAll("&", "§")));
            }
            case NETHER_QUARTZ_ORE -> {
               Main.addMoney(player, getConfig().getDouble("pay.QUARTZ_ORE", 10));
               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getConfig().getString("msg.QUARTZ_ORE", "&2MineToEarn &8» &e+200 coins &f(Quartz ore broken)").replaceAll("&", "§")));
            }
         }
      }
   }
}

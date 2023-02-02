package net.lemonserver.equalsiplimitplayer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class eventcatch implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        List<String> i_list=new ArrayList<String>();
        for(byte b:e.getPlayer().getAddress().getAddress().getAddress()){
            i_list.add(Integer.valueOf(Byte.toUnsignedInt(b)).toString());
        }
        String ip_under=String.join("_",i_list);
        for (String key : EqualsIpLimitPlayer.config.getConfigurationSection("ignore-ip").getKeys(false)) {
            //getLogger().info(key + config.getString("MapKeys." + key));
            if(ip_under.equals(key)){
                return;
            }
        }
        int limit_ip=EqualsIpLimitPlayer.config.getInt("limit-ip");
        int count=0;
        for(String a:EqualsIpLimitPlayer.player_ip){
            if(a.equals(ip_under)){
                count++;
            }
        }
        if(limit_ip<count){
            EqualsIpLimitPlayer.player_ip.add(ip_under);
        }else{
            e.getPlayer().kickPlayer("同じipから"+limit_ip+"個を超えるアクセスが確認されました");
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        List<String> i_list=new ArrayList<String>();
        for(byte b:e.getPlayer().getAddress().getAddress().getAddress()){
            i_list.add(Integer.valueOf(Byte.toUnsignedInt(b)).toString());
        }
        String ip_under=String.join("_",i_list);
        for(int i=0;i<EqualsIpLimitPlayer.player_ip.size();i++){
            if(EqualsIpLimitPlayer.player_ip.get(i).equals(ip_under)){
                EqualsIpLimitPlayer.player_ip.remove(i);
            }
        }
    }
}

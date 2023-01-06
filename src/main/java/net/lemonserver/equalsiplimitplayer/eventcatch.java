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
        String j=String.join("_",i_list);
        if(EqualsIpLimitPlayer.config.contains("ignore-ip."+j)){
            return;
        }
        if(EqualsIpLimitPlayer.config.contains("ip."+j)){
            if(EqualsIpLimitPlayer.config.getInt("ip."+j)==EqualsIpLimitPlayer.config.getInt("limit-ip")){
                e.getPlayer().kickPlayer("同一ipで"+(EqualsIpLimitPlayer.config.getInt("limit-ip")+1)+"以上の同時接続は許可されていません。");
            }else{
                EqualsIpLimitPlayer.config.set("ip."+j,EqualsIpLimitPlayer.config.getInt("ip."+j)+1);
            }
        }else{
            EqualsIpLimitPlayer.config.set("ip."+j,1);
        }
        EqualsIpLimitPlayer.plugin.saveConfig();
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        List<String> i_list=new ArrayList<String>();
        for(byte b:e.getPlayer().getAddress().getAddress().getAddress()){
            i_list.add(Integer.valueOf(Byte.toUnsignedInt(b)).toString());
        }
        String j=String.join("_",i_list);
        EqualsIpLimitPlayer.config.set("ip."+j,EqualsIpLimitPlayer.config.getInt("ip."+j)-1);
        EqualsIpLimitPlayer.plugin.saveConfig();
    }
}

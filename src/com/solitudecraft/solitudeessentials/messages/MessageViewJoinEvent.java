package com.solitudecraft.solitudeessentials.messages;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by nolan on 6/22/2017.
 */
public class MessageViewJoinEvent implements Listener{
    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event) {
        //if(event.getPlayer().hasPermission()) {
          MessageViewCommand.viewingMessage.add(event.getPlayer());
       // }
    }
}

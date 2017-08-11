package fr.xephi.authme.command.executable.email;

import fr.xephi.authme.command.PlayerCommand;
import fr.xephi.authme.data.auth.PlayerAuth;
import fr.xephi.authme.data.auth.PlayerCache;
import fr.xephi.authme.message.MessageKey;
import fr.xephi.authme.service.CommonService;
import fr.xephi.authme.util.Utils;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.List;

/**
 * Show email command.
 */
public class ShowEmailCommand extends PlayerCommand {

    @Inject
    private CommonService commonService;

    @Inject
    private PlayerCache playerCache;

    @Override
    public void runCommand(Player player, List<String> arguments) {
        PlayerAuth auth = playerCache.getAuth(player.getName());
        if (auth != null && !Utils.isEmailEmpty(auth.getEmail())) {
            commonService.send(player, MessageKey.EMAIL_SHOW, emailMask(auth.getEmail()));
        } else {
            commonService.send(player, MessageKey.SHOW_NO_EMAIL);
        }
    }

    private String emailMask(String email){
        String[] frag = email.split("@");   //Split id and domain
        int sid = frag[0].length() / 3 + 1;     //Define the id view (must be a value >= 1)
        String[] dom = frag[1].split(".");  //Domain substrings
        int sdomain = dom[0].length() / 3;   //Define the domain view (must be a value >= 0)
        String id = frag[0].substring(0, sid) + "*****";  //Build the id
        String domain = "***" + dom[0].substring(sdomain) + "." + dom[1];  //Build the domain
        return id + "@" + domain;
    }
}

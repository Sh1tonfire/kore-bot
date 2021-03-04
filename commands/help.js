import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";
import {
    admins,
    commands
} from "./../config.js";

let command = {
    name: "help",
    admin: false,
};

export default {
    name: "help",
    admin: false,
    run(args, message){
        let cmds = [];
        for(let c in commands){
            if(!commands[c].admin){
                cmds.push(c);
            }else if(admins.includes(message.author.id)){
                cmds.push(c);
            }
        }
        sendMessage(message.channelID, cmds.toString());
    }
};

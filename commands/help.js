import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";
import {admins} from "./../config.js";

let commands = {};
for(let c of Deno.readDirSync("./commands")){
    import("./../commands/" + c.name).then(e => {
        commands[e.default.name] = {
            admin: e.default.admin,
            run: e.default.run
        };
    });
}

export default {
    name: "help",
    admin: false,
    run(args, message){
        let cmds = [];
        for(let c in commands){
            if(!commands[c].admin){
                cmds.push(c);
            }else if(admins.includes(m.author.id)){
                cmds.push(c);
            }
        }
        sendMessage(message.channelID, cmds.toString());
    }
};

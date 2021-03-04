import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";
import {repos} from "./../config.js";

export default {
    name: "whereis",
    admin: false,
    async run(args, message){
        let result = "";
        for(let f in await Deno.readDir("./files/gen")){
            console.log(f);
        }
        for(let r in repos){
            repos[r].api.tree.forEach(e => {
                if(e.type === "tree") return;
                let filename = e.path.substring(e.path.lastIndexOf("/") + 1, e.path.lastIndexOf("."));
                if(args[0] === filename){
                    result += "<" + repos[r].link + e.path + ">\n";
                }
            });
        }
        if(result === "") result = "i cant find that";
        sendMessage(message.channelID, result);
    }
}

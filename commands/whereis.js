import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";
import {repos} from "./../config.js";
import {file} from "./../util/messages.js";

export default {
    name: "whereis",
    admin: false,
    async run(args, message){
        let result = "";
        let mfile = null;
        for await(let f of Deno.readDir("./files/gen")){
            if(f.name.substring(0, f.name.lastIndexOf(".")) === args[0]){
                mfile = file("./files/gen" + f.name, "text/plain");
            }
        }
        for(let r in repos){
            repos[r].api.tree.forEach(e => {
                if(e.type === "tree") return;
                let filename = e.path.substring(e.path.lastIndexOf("/") + 1, e.path.lastIndexOf("."));
                if(args[0] === filename){
                    if(e.path.endsWith(".png")){
                        result += repos[r].link + e.path + "\n";
                    }else{
                        result += "<" + repos[r].link + e.path + ">\n";
                    }
                }
            });
        }
        if(result === "" && mfile === null) result = "i cant find that";
        sendMessage(message.channelID, {
            content: result,
            file: mfile
        });
    }
}

import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";
import {repos} from "./../config.js";

export default {
    name: "whereis",
    admin: false,
    async run(args, message){
        for(let r in repos){
            /*
            repos[r].api.tree.forEach(e => {
                
            });
            */
        }
    }
}

import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";

export default {
    name: "restart",
    admin: true,
    async run(args, message){
        await sendMessage(message.channelID, "restarting...");
        Deno.exit();
    }
}

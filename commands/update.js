import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";

export default {
    name: "update",
    admin: true,
    async run(args, message){
        await sendMessage(message.channelID, "updating...");
        Deno.exit(124);
    }
}

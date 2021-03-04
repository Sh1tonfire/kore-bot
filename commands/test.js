import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";

export default {
    name: "test",
    admin: false,
    run(args, message){
        sendMessage(message.channelID, "/ping");
    }
};

import {
    startBot,
    sendMessage
} from "https://deno.land/x/discordeno/mod.ts";

import {token} from "./token.js";
import parseMessage from "./core/parser.js";

startBot({
    token: token,
    intents: [
        "GUILDS", "GUILD_MESSAGES"
    ],
    eventHandlers: {
        async ready(){
            console.log("bot up");
            try{
                await sendMessage(await Deno.readTextFile("./restart.txt"), "done");
                Deno.writeTextFile("./restart.txt", "731033393072046091");
            }catch{}
        },
        messageCreate: parseMessage
    }
});

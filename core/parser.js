import {sendMessage} from "https://deno.land/x/discordeno/mod.ts";

let admins = [
    "382853907031916544"
];

let commands = {};
for(let c of Deno.readDirSync("./commands")){
    import("./../commands/" + c.name).then(e => {
        commands[e.default.name] = {
            admin: e.default.admin,
            run: e.default.run
        };
    });
}

function parse(m){
    if(!m.content.startsWith("/")) return; // the prefix
    const args = m.content.substring(1).split(" ");
    if(args[0] === "") return;
    
    for(let c in commands){
        if(args[0] === c){
            if(commands[c].admin === true && !admins.includes(m.author.id)){
                sendMessage(m.channelID, "only admins can use this");
                return;
            }
            commands[c].run(args.slice(1), m);
            break;
        }
    }
}

export default parse;

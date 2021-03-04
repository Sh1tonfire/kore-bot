import {admins} from "./config.js";

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
            if(commands[c].admin && admins.includes(message.author.id)){
                cmds.push(c);
            }else{
                cmds.push(c);
            }
        }
    }
};

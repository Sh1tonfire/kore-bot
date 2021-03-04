let admins = [
    "382853907031916544"
];

let commands = {};
for(let c of Deno.readDirSync("./commands")){
    try{
        import("./commands/" + c.name).then(e => {
            commands[e.default.name] = {
                admin: e.default.admin,
                run: e.default.run
            };
        });
    }catch(err){
        console.log(err);
    }
}

export {
    admins,
    commands
};

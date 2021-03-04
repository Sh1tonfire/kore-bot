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

let repos = {
    mindustry: {
        link: "https://github.com/Anuken/Mindustry/blob/master/",
        api: await fetch("https://api.github.com/repos/Anuken/Mindustry/git/trees/master?recursive=true").then(f => f.json())
    },
    arc: {
        link: "https://github.com/Anuken/Arc/blob/master/",
        api: await fetch("https://api.github.com/repos/Anuken/Arc/git/trees/master?recursive=true").then(f => f.json())
    }
};

export {
    admins,
    commands,
    repos
};

let admins = [
    "382853907031916544"
];

let commands = {};
for(let c of Deno.readDirSync("./commands")){
    import("./commands/" + c.name).then(e => {
        let {name, admin, run} = e.default;
        commands[name] = {admin, run};
    }).catch(console.log);
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

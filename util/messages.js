function file(path, type){
    let file = {
        name: path.substring(path.lastIndexOf("/") + 1),
        blob: new Blob([Deno.readFileSync(path)], {type: type})
    };
    return file;
}


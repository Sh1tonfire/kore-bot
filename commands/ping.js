export default {
    name: "ping",
    admin: false,
    run(args, message){
        message.reply("pong");
        console.log(Object.keys(message));
    }
};

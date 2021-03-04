export default {
    name: "ping",
    admin: false,
    run(args, message){
        message.reply("pong\n" + (Date.now() - message.timestamp) + " ms");
    }
};

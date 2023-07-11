db.actors.find()

db.actors.insert(
{
    name: 'Fred Flintstone',
    shows: 'The Flintstones'
}
)

db.actors.deleteOne(
{
    name: 'Fred Flintstone'
}
)

db.actors.update(
{
    name: { $regex:"George", $options: "i"}
},
{
    $set: { name: "Fred"},
//    $set: { hobbies: ["jogging", "music"]}
}
)

db.actors.update(
{
    name: { $regex:"Fred", $options: "i"}
},
{
    $push: { hobbies: "walking"}
}
)

db.tvshows.find(
{
    name: /the/i
}
)

//error
db.tvshows.createIndex(
    {summary: "text"},
    {default_language: "english"},
    {language_override: "language"}
    //language override?
)

use games

db.game.find()

db.game.find(
{
    name: {$regex: "catan", $options: "i"}
}
)

db.game.find(
{name: /catan/i}
)

db.comment.find(
{gid: 13}
)
.sort({rating: -1})
.limit(
)

db.comment.insert(
    {
        user: 'username',
        rating: 9.5,
        c_text: 'comments',
        gid: 13
    }
)


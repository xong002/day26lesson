db.tvshows.find(
    {
        name : {
            $regex: "the", $options: "i"
        }
    }
)

db.tvshows.find(
{
    _id : ObjectId('64a4d4b569ab963e2130c056')
}
)

db.tvshows.find(
{$and :[
    {runtime: { $lte: 60}},
    {"rating.average": {$gte: 8.0}}
]}
)

db.tvshows.find({
    genres: {
        $in: ["Anime", "Horror"]
    }
}).projection({ name: 1, _id: 0})

db.tvshows.find({
    awards: {$exists: false}
})

db.tvshows.find(
{ type: {$regex: 'reality', $options: 'i'}}
)
.limit(5)
.sort({name: 1})
.projection({ id: 1, name: 1, summary: 1, 'image.original': 1, _id: 0})







use shows
show collections

db.tv_shows.find()

db.tv_shows.find({
    type: 'Scripted'
})
//just filtering
db.tv_shows.aggregate([
    {
        $match: {
            type: 'Scripted'
        }
    }
])

db.tv_shows.aggregate([
    {
        $match: {
            type: 'Scripted'
        }        
    },
    {
        $project: { id: 1, _id: 0, name: 1, genres: 1, 'image.original': 1} //to select fields to display
    }
])

db.tv_shows.aggregate([
    {
        $match: {
            language: {$regex: 'japanese', $options: 'i'}
        }
    },
    {
        $group: {
            _id: "$type",
            titles: {
                $push: {
                    title: '$name',
                    genres: '$genres',
                    runtime: '$runtime'
                }
            }
        }
    }
])

//concat 2 fields to 1 field
db.tv_shows.aggregate([
{
    $project: {
        _id: 1, id: 1,
        title: {
            $concat: [ '$name', '-', {$toString: '$runtime'}]
        }
    }
}
])

db.tv_shows.aggregate([
//{
//    $match: { name: 'Bitten'}
//},
{
    $unwind: '$genres'
},
{
    $group: {
        _id: '$genres',
        count: {$sum: 1},
        averageRuntime: {$avg: '$runtime'},
        titles: {$push: '$name'}
    }
}
])

db.tv_shows.aggregate([
{
    $bucket: {
        groupBy: '$rating.average',
        boundaries: [7,8,9],
        default: '>9.0'
    }
    
}])


use games

db.game.find({gid: 6})

db.game.aggregate([
{ $sort: {name :1} },
{ $project: { name: 1, gid: 1} },
{ $skip: 10 },
{ $limit: 5 },
//{ $match: {gid: 6} },
{ $lookup: {
        from: 'comment',
        foreignField: "gid",
        localField: "gid",
        as: "reviews"
    }
}
])

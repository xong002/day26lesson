use pokemons

db.pokemon.find(
)

db.pokemon.aggregate([
{ $sort: {name: 1}},
{ $unwind: '$type' },
{
    $group: {
        _id: '$type',
        pokemon: {$push: {
            name: '$name', 
            img: '$img' }}
    }
},
{ $sort: {_id : 1}}
])

db.pokemon.aggregate([
{ $unwind: '$type' },
{     $group: {
        _id: '$type'}},
{ $sort: {_id : 1}}
])

db.pokemon.aggregate([
//{ $in : ["Grass", "$type"]}
{ $unwind: '$type' },
{ $match: { type: 'Grass'}},
{ $project: { name: 1}}
])

db.pokemon.find(
{ type: { $in : [{$regex:"grass", $options:'i'}]}},
{ name: 1, img: 1})

db.pokemon.aggregate([
        { $sort: {name: 1}},
        { $unwind: '$type' },
        {
            $group: {
                _id: '$type',
                pokemon: {$push: {
                    id: '$id',
                    name: '$name', 
                    img: '$img' }}
            }
        },
        { $sort: {'pokemon.id' : -1}}
        ])


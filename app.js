var databaseUrl = "log210"; // "username:password@example.com/mydb"
var collections = ["user", "restaurant"]
var db = require("mongojs").connect(databaseUrl, collections);


db.user.save({email: "srirangan@gmail.com", password: "iLoveMongo", firstName: "Valentine", lastName: "Berge", phoneNumber:"4158965230", userName: "igloo", type: "mangeuse de pizzas"}, function(err, saved) {
  if( err || !saved ) console.log("User not saved");
  else console.log("User saved");
});

db.user.find({firstName: "Valentine"}, function(err, user){
	if( err || !user) console.log("No Valentine found");
	else user.forEach( function(firstName){
		console.log(firstName);
	});
});

db.user.update({firstName: "Valentine"}, {$set: {password: "iReallyLoveMongo"}}, function(err, updated) {
  if( err || !updated ) console.log("User not updated");
  else console.log("User updated");
});


db.restaurant.save({admin: "Valentine", description: "meilleur restaurant du monde", name: "Pizza en folie"}, function(err, saved) {
  if( err || !saved ) console.log("Restaurant not saved");
  else console.log("Restaurant saved");
});

db.restaurant.find({admin: "Valentine"}, function(err, user){
	if( err || !user) console.log("No restaurant found");
	else user.forEach( function(firstName){
		console.log(name);
	});
});
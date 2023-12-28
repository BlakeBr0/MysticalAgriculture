mods.mysticalagriculture.AwakeningCrafting.addRecipe("netherite_from_dirt_2", <item:minecraft:netherite_ingot>, [
    <item:minecraft:diamond>, <item:minecraft:dirt>.reuse(), <item:minecraft:dirt>.reuse(), <item:minecraft:dirt>, <item:minecraft:dirt>.reuse()
], [
    <item:mysticalagriculture:air_essence> * 10, <item:mysticalagriculture:earth_essence> * 20, <item:mysticalagriculture:water_essence> * 30, <item:mysticalagriculture:fire_essence> * 40
]);

var recipes = <recipetype:mysticalagriculture:awakening>.getAllRecipes();

println("There are " + recipes.length + " awakening recipes");
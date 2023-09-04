mods.mysticalagriculture.EnchanterCrafting.addRecipe("fancy_sharpness", "minecraft:protection", [
    <item:minecraft:carrot> * 24, <item:minecraft:cobblestone> * 128
]);

//mods.mysticalagriculture.EnchanterCrafting.remove("minecraft:sharpness");

var recipes = <recipetype:mysticalagriculture:enchanter>.getAllRecipes();

println("There are " + recipes.length + " enchanter recipes");
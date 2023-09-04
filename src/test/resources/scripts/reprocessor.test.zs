mods.mysticalagriculture.ReprocessorCrafting.addRecipe("test_reprocessor", <item:minecraft:apple>, <tag:items:forge:ingots/iron>);

var recipes = <recipetype:mysticalagriculture:reprocessor>.getAllRecipes();

println("There are " + recipes.length + " reprocessor recipes");
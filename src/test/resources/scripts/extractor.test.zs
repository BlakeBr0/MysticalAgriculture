mods.mysticalagriculture.SoulExtractorCrafting.addRecipe("testing", <item:minecraft:apple>, "mysticalagriculture:zombie", 0.5);

//mods.mysticalagriculture.SoulExtractorCrafting.remove("mysticalagriculture:pig");
//mods.mysticalagriculture.SoulExtractorCrafting.remove(<item:minecraft:coal>);

var recipes = <recipetype:mysticalagriculture:soul_extraction>.getAllRecipes();

println("There are " + recipes.length + " soul extractor recipes");
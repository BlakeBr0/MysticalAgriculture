mods.mysticalagriculture.InfusionCrafting.addRecipe("netherite_from_dirt", <item:minecraft:netherite_ingot>, [
    <item:minecraft:diamond>, <item:minecraft:air>, <item:minecraft:dirt>.reuse(), <item:minecraft:air>, <item:minecraft:dirt>, <item:minecraft:air>, <item:minecraft:dirt>, <item:minecraft:air>, <item:minecraft:dirt>
]);

//mods.mysticalagriculture.InfusionCrafting.addRecipe("chicken_from_dirt", <item:minecraft:chicken>, [
//    <item:minecraft:diamond>, <item:minecraft:air>, <item:minecraft:dirt>, <item:minecraft:air>, <item:minecraft:dirt>, <item:minecraft:air>, <item:minecraft:dirt>
//]);

var recipes = <recipetype:mysticalagriculture:infusion>.getAllRecipes();

println("There are " + recipes.length + " infusion recipes");
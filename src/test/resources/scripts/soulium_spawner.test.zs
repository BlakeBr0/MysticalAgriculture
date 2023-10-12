mods.mysticalagriculture.SouliumSpawnerCrafting.addRecipe("test_spawner", <item:minecraft:apple>, 20, ["minecraft:zombie"]);
mods.mysticalagriculture.SouliumSpawnerCrafting.addRecipe("test_spawner_weights", <item:minecraft:carrot>, 16, ["minecraft:skeleton@5", "minecraft:wither_skeleton@1"]);

var recipes = <recipetype:mysticalagriculture:soulium_spawner>.getAllRecipes();

println("There are " + recipes.length + " soulium spawner recipes");
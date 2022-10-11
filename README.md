# Mystical Agriculture  [![](http://cf.way2muchnoise.eu/full_246640_downloads.svg)](https://minecraft.curseforge.com/projects/mystical-agriculture) 

Adds Resource Crops, Armor, Tools, and other cool things!

[Documentation](https://blakesmods.com/docs/mysticalagriculture)

## Download

The official release builds can be downloaded from the following websites.

- [Blake's Mods](https://blakesmods.com/mystical-agriculture/download)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mystical-agriculture)

## Development

To use this mod in a development environment, you will need to add the following to your `build.gradle`.

```groovy
repositories {
    maven {
        url 'https://maven.blakesmods.com'
    }
}

dependencies {
    implementation fg.deobf('com.blakebr0.cucumber:Cucumber:<minecraft_version>-<mod_version>')
    implementation fg.deobf('com.blakebr0.mysticalagriculture:MysticalAgriculture:<minecraft_version>-<mod_version>')
}
```

## License

[MIT License](./LICENSE)

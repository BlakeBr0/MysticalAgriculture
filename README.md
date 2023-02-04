# Mystical Agriculture

<p align="left">
    <a href="https://blakesmods.com/mystical-agriculture" alt="Downloads">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/mysticalagriculture/downloads&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/mystical-agriculture" alt="Latest Version">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/mysticalagriculture/version&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/mystical-agriculture" alt="Minecraft Version">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/mysticalagriculture/mc_version&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/docs/mysticalagriculture" alt="Docs">
        <img src="https://img.shields.io/static/v1?label=docs&message=view&color=brightgreen&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/wiki/mysticalagriculture" alt="Wiki">
        <img src="https://img.shields.io/static/v1?label=wiki&message=view&color=brightgreen&style=for-the-badge" />
    </a>
</p>

Adds Resource Crops, Armor, Tools, and other cool things!

## Download

The official release builds can be downloaded from the following websites.

- [Blake's Mods](https://blakesmods.com/mystical-agriculture/download)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mystical-agriculture)
- [Modrinth](https://modrinth.com/mod/mystical-agriculture)

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

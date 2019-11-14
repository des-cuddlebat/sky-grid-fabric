## Skygrid for Fabric

A mod that works either on client (for singleplayer) or server that adds "skygrid" level generator type.

## Usage

* On client: On the new world screen, change world type from default to generator.skygrid (I need a localized name for this).

* On server: In server.properties, change level-type from default to skygrid.

Please disable structures, skygrid generator shouldn't create any either way but I never tested that.

## Configuration

Hopefully soon, for now the api exists

## Api

Api is implemented through class net/cuddlebat/skygrid/api/SkygridApi. The api allows mods to add their blocks, loottables and mob spawners to existing dimensions. Mods can also add their own dimension to the skygrid generator, however this also requires the dimension to provide ModGeneratorTypes#SKYGRID_CHUNK for level generator type ModGeneratorTypes#SKYGRID_LEVEL.

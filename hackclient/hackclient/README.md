# HackClient (Fabric, Minecraft Java 26.3)

Kostra klienta: system modulov + settings + keybindy. Zatial moduly Sprint a Fly.

## Pozadavky
- JDK 25
- Gradle 9.x (alebo skopiruj `gradle/` + `gradlew` z oficialneho fabric-example-mod / fabricmc.net/develop)

## Build
    gradle wrapper      # ak chyba gradlew
    ./gradlew runClient # spusti Minecraft s modom
    ./gradlew build     # jar je v build/libs/

## Poznamky
- 26.x je bez obfuskacie: plugin `net.fabricmc.fabric-loom`, ziadne mappings, `implementation` namiesto `modImplementation`.
- Verzie v gradle.properties over na https://fabricmc.net/develop.
- Keybindy nastavis v Options > Controls > Key Binds > HackClient.

## Build bez lokalneho setupu (GitHub Actions)
1. Nahraj tento priecinok do noveho GitHub repozitara.
2. Otvor kartu Actions > build > Run workflow (alebo staci push).
3. Po dobehnuti stiahni artefakt `hackclient-jar` a vyber z neho `hackclient-0.1.0.jar` (nie `-sources`).

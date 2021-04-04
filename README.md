# SpectClient
SpectClient is a client that runs of Minecraft Forge 1.12.2, it is intended for use on servers which allow client-side modification. postman takes inpiration from many previous clients like osiris, Impact, salhack, and others. This client uses a custom event system, a custom clickGui library, and Forge's 1.12.2 mod api. I intend for this project to be community driven and as open as possible, open access, open development, and open sourced.

This client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2854, and is coded in 100% java.

check out the website - https://saberaspect.github.io/SpectClient/.



# Instructions
 download

1. SpectClient uses forge in order to run so, if you havent already, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2.
2. At the top of this readme, click on the downloads button and select the latest version, or your preferred version.
3. Click assets at the bottom of the release, then download the .jar file.
4. If you havent ran forge yet, do so, if you have already ran forge at least once, type %appdata% in ur windows search, enter, and navigate to .minecraft -> mods (if there is no mods folder than you can add it yourself) -> 1.12.2, "1.12.2" may not exist either, so you can create it yourself if needed. this process may vary depending on your device, so you may need to look it up.
5. Drag the SpectClient.jar file that you downloaded earlier into the 1.12.2 folder.

use

* the inital keybind for the clickgui is Left Alt.
* once the clickgui is opened you will see a bunch of categorys, right click to open them up.
* each category has different modules you may enable by left clicking, or right clicking to open up their settings.
* the settings consist of booleans (enable or disable), numbers (sliders to change the value of something), and modes (switches modes).
* you can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
* esc to close the clickgui.

building

gradlew setupDecompWorkspace
gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij)
(./gradlew for linux and mac users)

# Credits

contributions

Stack overflow, litteraly everything.

mod api - Forge http://files.minecraftforge.net.

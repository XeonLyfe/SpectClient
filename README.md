GitHub all releases Lines of code Discord GitHub


SpectClient is a client that runs of Minecraft Forge 1.12.2, it is intended for use on servers which allow client-side modification. SpectClient takes inspiration from many previous clients like Impact, kami, salHack, and others. this client uses a custom event system, custom clickGui library, and Forge's 1.12.2 mod api. The main intent for this project to be community driven and as open as possible, open access, open development, and open sourced.

This client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2854, and is coded in 100% java.

check out the website - https://https://saberaspect.github.io/SpectClient/.
postman archive - this basically just holds all the old versions of postman, https://github.com/SaberAspect/SpectClient-archive.

instructions
download

SpectClient uses forge in order to run so, if you havent already, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2.
Click assets at the bottom of the release, then download the .jar file.
If you havent ran forge yet, do so, if you have already ran forge at least once, type %appdata% in ur windows search, enter, and navigate to .minecraft -> mods (if there is no mods folder than you can add it yourself) -> 1.12.2, "1.12.2" may not exist either, so you can create it yourself if needed. this process may vary depending on your Computer and Operating system, so you may need to look it up.
Drag the SpectClient.jar file that you downloaded earlier into the 1.12.2 folder.

use

The inital keybind for the clickgui is Left Alt.
Once your clickgui is opened you will see a bunch of categorys, right click to open them up.
Each category has different modules you may enable by left clicking, or right clicking to open up their settings.
The settings consist of booleans (enable or disable), numbers (sliders to change the value of something), and modes (switches modes).
You can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
esc to close the clickgui 0_0.

building

gradlew setupDecompWorkspace
gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij)
(./gradlew for linux and mac users)
credits
contributions

mod api - Forge http://files.minecraftforge.net.


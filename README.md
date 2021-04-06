![SpectClient-gui-for-github](https://user-images.githubusercontent.com/74943836/113787548-3e42d800-9709-11eb-806d-62c5bff2cf89.png)
# SpectClient
SpectClient is a client that runs of Minecraft Forge 1.12.2, it is intended for use on servers which allow client-side modification. SpectClient takes inpiration from many previous clients like osiris, Impact, salhack, and others. This client uses a custom event system, a custom clickGui library, and Forge's 1.12.2 mod api. I intend for this project to be community driven and as open as possible, open access, open development, and open sourced.

This client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2854, and is coded in 100% java.

check out the website - https://saberaspect.github.io/Website-for-SpectClient/



# Instructions
 download

1. SpectClient uses forge in order to run. if don't already have forge, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2.
2. At the top of this readme, click on the downloads button and select the latest version, or your preferred version.
3. Click assets at the bottom of the release, then download the .jar file.
4. Type %appdata% in your windows bar and enter. Navigate to .minecraft -> mods (if there is no mods folder than you can add it yourself).
5. Drag the SpectClient.jar file that you downloaded earlier into the mods folder.

use

* The inital keybind for the clickgui is Left Alt.
* Once the clickgui is opened you will see a bunch of categorys, right click to open them up.
* Each category has different modules you may enable by left clicking, or right clicking to open up their settings.
* The settings consist of true or falses, (enable or disable), numbers (sliders to change the value of something), and modes (switches modes).
* You can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
* To remove a keybing, you press like you are going to assign a key then press the del key, not the backspace key.
* Esc to close the clickgui.

building the client(for programers)

gradlew setupDecompWorkspace
gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij)
(./gradlew for linux and mac users)

# Credits

contributions

Stack overflow, litteraly everything.

srgantmoomoo - gave me alot of ideas.

mod api - Forge http://files.minecraftforge.net.

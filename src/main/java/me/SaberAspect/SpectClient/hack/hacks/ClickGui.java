package me.SaberAspect.SpectClient.hack.hacks;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.Main;
import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.visual.ColorUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.IntegerValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClickGui extends Hack{

	public ModeValue theme;
	public static BooleanValue rainbow;
	public static BooleanValue shadow;
	public static BooleanValue tooltip;
	
	public static IntegerValue red;
	public static IntegerValue green;
	public static IntegerValue blue;
	public static IntegerValue alpha;
	
	private static int color;
	public static boolean isLight = false;
	
	public ClickGui() {
		super("ClickGui", HackCategory.VISUAL);
		this.setKey(Keyboard.KEY_RSHIFT);
		this.setShow(false);
		
		this.theme = new ModeValue("Theme", new Mode("Dark", true), new Mode("Light", false));
		
		ClickGui.tooltip = new BooleanValue("Tooltip", true);
		ClickGui.shadow = new BooleanValue("Shadow", true);
		ClickGui.rainbow = new BooleanValue("Rainbow", true);
		ClickGui.red = new IntegerValue("Red", 255, 0, 255);
		ClickGui.green = new IntegerValue("Green", 255, 0, 255);
		ClickGui.blue = new IntegerValue("Blue", 255, 0, 255);
		ClickGui.alpha = new IntegerValue("Alpha", 255, 0, 255);
		
		this.addValue(theme, tooltip, shadow, rainbow, red, green, blue, alpha);
		ClickGui.setColor();
	}
	
	@Override
	public String getDescription() {
		return "Graphical user interface.";
	}
	
	 public static int getColor() {
		 return rainbow.getValue() ? ColorUtils.rainbow().getRGB() : color;
	 }
	
	 public static void setColor() {
		color = ColorUtils.color(red.getValue(),
				green.getValue(),
				blue.getValue(),
				alpha.getValue());
	}
	
	@Override
	public void onEnable() {
		if(GhostMode.enabled) 
			return;
		Wrapper.INSTANCE.mc().displayGuiScreen(Main.hackManager.getGui());
		super.onEnable();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		ClickGui.setColor();
		ClickGui.isLight = theme.getMode("Light").isToggled();
		super.onClientTick(event);
	}
	
	@Override
	public void onRenderGameOverlay(Text event) {
		if(shadow.getValue()) {
			ScaledResolution sr = new ScaledResolution(Wrapper.INSTANCE.mc());
			RenderUtils.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), ColorUtils.color(0.0F, 0.0F, 0.0F, 0.5F));
		}
		super.onRenderGameOverlay(event);
	}

	public static void renderItem(ItemStack itemStack, Point point) {
		
	}

}

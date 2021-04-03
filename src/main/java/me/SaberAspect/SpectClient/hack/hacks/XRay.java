package me.SaberAspect.SpectClient.hack.hacks;

import java.util.LinkedList;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.XRayManager;
import me.SaberAspect.SpectClient.utils.BlockUtils;
import me.SaberAspect.SpectClient.utils.TimerUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.value.types.IntegerValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import me.SaberAspect.SpectClient.xray.XRayBlock;
import me.SaberAspect.SpectClient.xray.XRayData;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class XRay extends Hack{

    public IntegerValue distance;
	public IntegerValue delay;

	public TimerUtils timer;
	
	LinkedList<XRayBlock> blocks = new LinkedList<XRayBlock>();
	
	public XRay() {
		super("XRay", HackCategory.VISUAL);
		distance = new IntegerValue("Distance", 50, 4, 100);
		delay = new IntegerValue("UpdateDelay", 100, 0, 300);
		timer = new TimerUtils();
		this.addValue(distance, delay);
	}

	@Override
	public String getDescription() {
		return "Allows you to see blocks through walls.";
	}
	
	@Override
	public void onEnable() {
		blocks.clear();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		int distance = this.distance.getValue();
		if(!timer.isDelay((long) (delay.getValue() * 10))) {
			return;
		}
		blocks.clear();
		for(XRayData data : XRayManager.xrayList) {
			for (BlockPos blockPos : BlockUtils.findBlocksNearEntity(Wrapper.INSTANCE.player(), data.getId(), data.getMeta(), distance)) {
				XRayBlock xRayBlock = new XRayBlock(blockPos, data);
				blocks.add(xRayBlock);
			}
		}
		timer.setLastMS();
		super.onClientTick(event);
	}

	@Override
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		RenderUtils.drawXRayBlocks(blocks, event.getPartialTicks());
		super.onRenderWorldLast(event);
	}
}

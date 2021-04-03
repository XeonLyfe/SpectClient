package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.BlockUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class BlockOverlay extends Hack{

	public BlockOverlay() {
		super("BlockOverlay", HackCategory.VISUAL);
	}
	
	@Override
    public String getDescription() {
        return "Show of selected block.";
    }
	
	@Override
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		if(Wrapper.INSTANCE.mc().objectMouseOver == null) {
			return;
		}
		if (Wrapper.INSTANCE.mc().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            Block block = BlockUtils.getBlock(Wrapper.INSTANCE.mc().objectMouseOver.getBlockPos());
            BlockPos blockPos = Wrapper.INSTANCE.mc().objectMouseOver.getBlockPos();

            if (Block.getIdFromBlock(block) == 0) {
                return;
            }
            RenderUtils.drawBlockESP(blockPos, 1F, 1F, 1F);
        }
		
		super.onRenderWorldLast(event);
	}

}

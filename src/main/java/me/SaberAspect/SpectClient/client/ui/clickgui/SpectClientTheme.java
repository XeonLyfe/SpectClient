package me.SaberAspect.SpectClient.client.ui.clickgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererBase;
import com.lukflug.panelstudio.theme.Theme;

import me.SaberAspect.SpectClient.api.util.render.JColor;
/**
 * @author SrgantMooMoo
 */
public class SpectClientTheme implements Theme {
	protected ColorScheme scheme;
	protected Renderer componentRenderer,containerRenderer,panelRenderer;
	protected DescriptionRenderer descriptionRenderer;
	
	public SpectClientTheme (ColorScheme scheme, int height, int border) {
		this.scheme=scheme;
		panelRenderer=new ComponentRenderer(0,height,border);
		containerRenderer=new ComponentRenderer(1,height,border);
		componentRenderer=new ComponentRenderer(2,height,border);
	}
	
	@Override
	public Renderer getPanelRenderer() {
		return panelRenderer;
	}

	@Override
	public Renderer getContainerRenderer() {
		return containerRenderer;
	}

	@Override
	public Renderer getComponentRenderer() {
		return componentRenderer;
	}
	
	public DescriptionRenderer getDescription() {
		return descriptionRenderer;
	}

	
	protected class ComponentRenderer extends RendererBase {
		protected final int level,border;
		
		public ComponentRenderer (int level, int height, int border) {
			super(height+1,0,0,0,0);
			this.level=level;
			this.border=border;
		}

		@Override
		public void renderRect (Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
			Color color=getMainColor(focus,active);
			Color color2=getBackgroundColor(focus);
			if (level==1 && !active)context.getInterface().fillRect(context.getRect(),color2,color2,color2,color2);
			else context.getInterface().fillRect(rectangle,color,color,color,color);
			if (overlay) {
				Color overlayColor;
				if (context.isHovered()) {
					overlayColor=new Color(255,255,255,64);
				} else {
					overlayColor=new Color(255,255,255,0);
				}
				context.getInterface().fillRect(context.getRect(),overlayColor,overlayColor,overlayColor,overlayColor);
			}
			Point stringPos=new Point(rectangle.getLocation());
			stringPos.translate(1,border);
			context.getInterface().drawString(stringPos,text,new JColor (255, 255, 255, 255));
		}


		/*@Override
		public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
			ResourceLocation watermark = new ResourceLocation(Reference.MOD_ID, "textures/watermark.png");
			Rectangle rectangle;
			
			super.renderTitle(context,text,focus,active,open);
				Color color=getFontColor(active);
				Point point=new Point(context.getPos().x+context.getSize().width-2,	context.getPos().y+context.getSize().height/4);
				if (open) {
					//drawSetting();
				}else {
				}
		}
		
		public int drawSetting() {
			try {
                BufferedImage image = ImageIO.read(Main.class.getResource("/assets/spc/textures/postmail.png"));
                int texture = TextureUtil.glGenTextures();
                TextureUtil.uploadTextureImage(texture, image);
                return texture;
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
		}*/
		
		@Override
		public void renderBackground (Context context, boolean focus) {
				//Color color=getBackgroundColor(focus);
				//context.getInterface().fillRect(context.getRect(),color,color,color,color);
			}

		@Override
		public void renderBorder (Context context, boolean focus, boolean active, boolean open) {
			Color color;
			color=getDefaultColorScheme().getOutlineColor();
			if (level==1 && open) {
				context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x+context.getSize().width - 100,14 + context.getPos().y),new Dimension(1,context.getSize().height - 15)),color,color,color,color);
				context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x+context.getSize().width - 1,14 + context.getPos().y),new Dimension(1,context.getSize().height - 15)),color,color,color,color);
			}
		}
	
		@Override
		public Color getMainColor (boolean focus, boolean active) {
			Color color;
			// active modules
			if (active && level>0) color=getColorScheme().getActiveColor();
			// background
			else color=getColorScheme().getBackgroundColor();
			// inactive modules
			if (!active && level<2) color=getColorScheme().getBackgroundColor();
			// category
			if (active && level<1) color=getColorScheme().getFontColor();
			color=new Color(color.getRed(),color.getGreen(),color.getBlue(),getColorScheme().getOpacity());
			return color;
		}

		@Override
		public Color getBackgroundColor (boolean focus) {
			Color color;
			color=getColorScheme().getInactiveColor();
			color=new Color(color.getRed(),color.getGreen(),color.getBlue(),200);
			return color;
		}

		@Override
		public ColorScheme getDefaultColorScheme() {
			return SpectClientTheme.this.scheme;
		}
	}
}
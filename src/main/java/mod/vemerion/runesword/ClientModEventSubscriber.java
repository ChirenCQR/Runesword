package mod.vemerion.runesword;

import mod.vemerion.runesword.item.RuneItem;
import mod.vemerion.runesword.renderer.RuneforgeTileEntityRenderer;
import mod.vemerion.runesword.screen.RuneforgeScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
	
	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(Main.RUNEFORGE_BLOCK, RenderType.getCutout());
		
		ClientRegistry.bindTileEntityRenderer(Main.RUNEFORGE_TILE_ENTITY, RuneforgeTileEntityRenderer::new);
		
		ScreenManager.registerFactory(Main.RUNEFORGE_CONTAINER, RuneforgeScreen::new);
	}      
	
	@SubscribeEvent
	public static void onRegisterColor(ColorHandlerEvent.Item event) {
	      for(RuneItem rune : RuneItem.getRunes()) {
	          event.getItemColors().register((stack, layer) -> {
	             return layer == 0 ? -1 : rune.getColor();
	          }, rune);
	       }
	}
}

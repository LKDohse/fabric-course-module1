package net.kaupenjoe.mccourse.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.kaupenjoe.mccourse.item.custom.HammerItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before{
   private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = playerEntity.getMainHandStack();
        if(mainHandItem.getItem() instanceof HammerItem hammer && playerEntity instanceof ServerPlayerEntity serverPlayer){
            if(HARVESTED_BLOCKS.contains(blockPos)){
                return true;
            }

            for(BlockPos position : HammerItem.getBlocksToBeDestroyed(1, blockPos, serverPlayer)){
                if(blockPos == position || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(position))){
                    continue;
                }

                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVESTED_BLOCKS.remove(position);
                /// we add and remove the blocks from this list because the event will fire again when we try to break the block
                /// and cause an infinite loop. If we add it to this list, when the event fires again, it will be caught by the
                /// if statement on line 24 above to prevent the loop.
            }
        }
        return false;
    }
}

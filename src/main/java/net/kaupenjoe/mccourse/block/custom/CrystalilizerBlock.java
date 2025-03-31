package net.kaupenjoe.mccourse.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrystalilizerBlock extends HorizontalFacingBlock {
    public static final MapCodec<CrystalilizerBlock> CODEC = createCodec(CrystalilizerBlock::new);

    public CrystalilizerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        //CLIENT ONLY
    double xPos = pos.getX() + 0.5f;
    double yPos = pos.getY() + 1.25f;
    double zPos = pos.getZ() + 0.5f;
    double offset = random.nextDouble() * 0.6 - 0.3;

    world.addParticle(ParticleTypes.SMOKE, xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0);
    world.addParticle(ParticleTypes.GLOW, xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0);

    /*
    other examples
     using items as particles
     world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, Items.COOKED_BEEF.getDefaultStack()),
     xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0);
    using blocks as particles
            world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK,
                    Blocks.DIAMOND_BLOCK.getDefaultState()), xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0 );
    */


    }
}

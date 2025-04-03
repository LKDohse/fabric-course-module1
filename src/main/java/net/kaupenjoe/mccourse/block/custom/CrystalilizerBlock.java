package net.kaupenjoe.mccourse.block.custom;

import com.mojang.serialization.MapCodec;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.custom.CrystallizerBlockEntity;
import net.kaupenjoe.mccourse.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrystalilizerBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final MapCodec<CrystalilizerBlock> CODEC = createCodec(CrystalilizerBlock::new);

    public CrystalilizerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {

        return CODEC;
    }

    // HORIZONTAL FACING PIECE


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);

    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }

        double xPos = pos.getX() + 0.5f;
        double yPos = pos.getY() + 1.25f;
        double zPos = pos.getZ() + 0.5f;
        if (random.nextDouble()> 0.15){
            world.playSound(xPos, yPos, zPos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1f, 1f, false);
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();



        double offset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : offset;
        double yOffset = random.nextDouble() * 0.6 / 0.8;
        double zOffset = random.nextDouble() * 0.6 - 0.3;

        world.addParticle(ParticleTypes.SMOKE, xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0);

        if(world.getBlockEntity(pos) instanceof  CrystallizerBlockEntity crystallizerBlockEntity && !crystallizerBlockEntity.getStack(1).isEmpty()){
            world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, crystallizerBlockEntity.getStack(1)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
    /*
    other examples
     using items as particles
     world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, Items.COOKED_BEEF.getDefaultStack()),
     xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0);
    using blocks as particles
            world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK,
                    Blocks.DIAMOND_BLOCK.getDefaultState()), xPos + offset, yPos, zPos + offset, 0.0, 0.0, 0.0 );
    */


// BLOCK ENTITY PIECE
@Nullable
@Override
public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new CrystallizerBlockEntity(pos, state);
}

@Override
protected BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
}

@Override //this is pretty boilerplate, makes sure stored items are scattered in the world if block destroyed
protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (state.getBlock() != newState.getBlock()) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PedestalBlockEntity) {
            ItemScatterer.spawn(world, pos, (PedestalBlockEntity) blockEntity);
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}

@Override //boilerplate; pulls up a screen when block used with an item
protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!world.isClient) {
        NamedScreenHandlerFactory screenHandlerFactory = (CrystallizerBlockEntity) world.getBlockEntity(pos);

        if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory);
        }
    }

    return ItemActionResult.SUCCESS;
}

@Nullable
@Override
public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    //this forces the class to use our manually defined tick method, also pretty boilerplate
    return validateTicker(type, ModBlockEntities.CRYSTALLIZER_BE, ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
}
}


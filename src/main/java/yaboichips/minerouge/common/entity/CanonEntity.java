package yaboichips.minerouge.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CanonEntity extends Mob implements PlayerRideable, GeoEntity {
    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    private static int tickCounter = 0;
    private static ServerPlayer targetPlayer = null;
    private static boolean launchInitiated = false;

    public CanonEntity(EntityType<? extends Mob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10000);
    }



    @Override
    public InteractionResult mobInteract(Player player, InteractionHand p_19979_) {
        player.startRiding(this);
        if (player instanceof ServerPlayer splayer) {
            initiateLaunchSequence(splayer);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean canRide(Entity p_20339_) {
        return true;
    }



    @Override
    public void aiStep() {
        if (!launchInitiated) return;

        tickCounter++;

        if (tickCounter < 60) {
            float pitch = 1.0F + (tickCounter / 20.0F) * 0.1F; // Increases pitch slightly each second
            targetPlayer.level().playSound(null, this.getFirstPassenger().blockPosition(), SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 1.0F, pitch);
        }
        else if (tickCounter == 60) {
            targetPlayer.stopRiding();
            targetPlayer.level().playSound(null, targetPlayer.blockPosition(), SoundEvents.GENERIC_EXPLODE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        else if (tickCounter == 65) {
            Vec3 launchVelocity = new Vec3(0, 10, 0); // Upward velocity
            targetPlayer.connection.send(new ClientboundSetEntityMotionPacket(targetPlayer.getId(), launchVelocity)); // Send packet to sync with client
            targetPlayer.setDeltaMovement(launchVelocity); // Launch player upwards
        }
        else if (tickCounter == 160) {
            teleportToEnd(targetPlayer);
            reset();
        }
        super.aiStep();
    }

    public static void initiateLaunchSequence(ServerPlayer player) {
        targetPlayer = player;
        tickCounter = 0;
        launchInitiated = true;
    }

    private static void teleportToEnd(ServerPlayer player) {
        ServerLevel endWorld = player.getServer().getLevel(Level.END);
        if (endWorld != null) {
            player.teleportTo(endWorld, 0.5, 300, 0.5, player.getYRot(), player.getXRot());
        }
    }

    private static void reset() {
        tickCounter = 0;
        targetPlayer = null;
        launchInitiated = false;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }
}

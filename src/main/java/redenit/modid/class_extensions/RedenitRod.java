package redenit.modid.class_extensions;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import javax.swing.*;

public class RedenitRod extends FishingRodItem {

    private final float TimeReductionBoost = 40;
    private final int LuckyBonusBoost = 4;

    public RedenitRod(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.fishHook != null) {

            if (!world.isClient) {
                int i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, LivingEntity.getSlotForHand(hand));
            }

            world.playSound((Entity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);

        } else {
            world.playSound((Entity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)world;

                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, itemStack, user) * TimeReductionBoost);
                int k = EnchantmentHelper.getFishingLuckBonus(serverWorld, itemStack, user) * LuckyBonusBoost;

                ProjectileEntity.spawn(new FishingBobberEntity(user, world, k, j), serverWorld, itemStack);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return ActionResult.SUCCESS;
    }
}

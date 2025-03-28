package walksy.customhitboxes.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Accessor("serverX")
    double getServerX();

    @Accessor("serverY")
    double getServerY();

    @Accessor("serverZ")
    double getServerZ();
}

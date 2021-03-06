package mod.vemerion.runesword.lootmodifier.lootcondition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;

import net.minecraft.entity.EntityType;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class IsEntityType implements ILootCondition {

	private EntityType<?> entityType;

	public IsEntityType(EntityType<?> entityType) {
		this.entityType = entityType;
	}

	@Override
	public boolean test(LootContext t) {
		return t.has(LootParameters.THIS_ENTITY) && t.get(LootParameters.THIS_ENTITY).getType() == entityType;
	}

	@Override
	public LootConditionType func_230419_b_() {
		return LootConditions.IS_ENTITY_TYPE;
	}

	public static class Serializer implements ILootSerializer<IsEntityType> {
		public void serialize(JsonObject json, IsEntityType instance, JsonSerializationContext p_230424_3_) {
			json.addProperty("type", ForgeRegistries.ENTITIES.getKey(instance.entityType).toString());
		}

		public IsEntityType deserialize(JsonObject json, JsonDeserializationContext p_230423_2_) {
			ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(json, "type"));
			EntityType<?> type = ForgeRegistries.ENTITIES.getValue(resourcelocation);
			if (type == null) {
				throw new JsonSyntaxException("Unknown type '" + resourcelocation + "'");
			}

			return new IsEntityType(type);
		}
	}

}

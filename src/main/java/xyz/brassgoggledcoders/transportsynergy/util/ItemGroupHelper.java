package xyz.brassgoggledcoders.transportsynergy.util;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemGroup;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemGroupHelper {
    public static List<ItemGroup> findByModID(String id) {
        List<ItemGroup> itemGroups = Lists.newArrayList();
        for (ItemGroup itemGroup : itemGroups) {
            if (itemGroup.getPath().contains(id)) {
                itemGroups.add(itemGroup);
            }
        }
        return itemGroups;
    }

    @Nonnull
    public static ItemGroup getFirstByModID(String id) {
        List<ItemGroup> itemGroups = findByModID(id);
        if (itemGroups.isEmpty()) {
            return ItemGroup.SEARCH;
        } else {
            return itemGroups.get(0);
        }
    }
}

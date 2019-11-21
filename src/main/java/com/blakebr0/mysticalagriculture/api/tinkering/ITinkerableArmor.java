package com.blakebr0.mysticalagriculture.api.tinkering;

public interface ITinkerableArmor extends ITinkerable {
    /**
     * The numerical tier of this tinkerable, used for set bonuses, higher tiers count for lower tier set bonuses
     * @return the tier of this tinkerable
     */
    int getSetTier();
}

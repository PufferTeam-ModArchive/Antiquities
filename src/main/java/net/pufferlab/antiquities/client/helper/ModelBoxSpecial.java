package net.pufferlab.antiquities.client.helper;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelBoxSpecial extends ModelBox {

    public final int iconIndex;

    public ModelBoxSpecial(ModelRenderer renderer, int u, int v, float cx, float cy, float cz, int x, int y, int z,
        float o, int iconIndex) {
        super(renderer, u, v, cx, cy, cz, x, y, z, o);
        this.iconIndex = iconIndex;
    }
}

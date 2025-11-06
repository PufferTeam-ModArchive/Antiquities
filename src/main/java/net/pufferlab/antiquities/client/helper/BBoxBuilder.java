package net.pufferlab.antiquities.client.helper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.pufferlab.antiquities.client.utils.ModelBox;
import net.pufferlab.antiquities.client.utils.ModelRenderer;

public class BBoxBuilder {

    List<AxisAlignedBB> list;

    public List<AxisAlignedBB> buildBB(ModelRenderer renderer, float scale) {
        if (!renderer.isHidden && renderer.showModel) {

            // Render children first
            if (renderer.childModels != null) {
                for (int i = 0; i < renderer.childModels.size(); ++i) {
                    ModelRenderer child = (ModelRenderer) renderer.childModels.get(i);

                    // Backup child state
                    float oldRotateX = child.rotateAngleX;
                    float oldRotateY = child.rotateAngleY;
                    float oldRotateZ = child.rotateAngleZ;
                    float oldPivotX = child.rotationPointX;
                    float oldPivotY = child.rotationPointY;
                    float oldPivotZ = child.rotationPointZ;

                    // Apply parent rotation/pivot to child
                    child.rotateAngleX += renderer.rotateAngleX;
                    child.rotateAngleY += renderer.rotateAngleY;
                    child.rotateAngleZ += renderer.rotateAngleZ;

                    child.rotationPointX += renderer.rotationPointX;
                    child.rotationPointY += renderer.rotationPointY;
                    child.rotationPointZ += renderer.rotationPointZ;

                    buildBB(child, scale);

                    // Restore child state
                    child.rotateAngleX = oldRotateX;
                    child.rotateAngleY = oldRotateY;
                    child.rotateAngleZ = oldRotateZ;
                    child.rotationPointX = oldPivotX;
                    child.rotationPointY = oldPivotY;
                    child.rotationPointZ = oldPivotZ;
                }
            }

            if (renderer.cubeList != null) {
                list = new ArrayList<>();
                for (int i = 0; i < renderer.cubeList.size(); ++i) {
                    ModelBox box = renderer.cubeList.get(i);

                    double[] bounds = MatrixHelper.getBounds(renderer, box, scale);

                    double minX = bounds[0];
                    double minY = bounds[1];
                    double minZ = bounds[2];
                    double maxX = bounds[3];
                    double maxY = bounds[4];
                    double maxZ = bounds[5];

                    list.add(AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ));
                }
            }

        }
        return list;
    }
}

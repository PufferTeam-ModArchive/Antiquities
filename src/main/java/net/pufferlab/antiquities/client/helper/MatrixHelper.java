package net.pufferlab.antiquities.client.helper;

import net.pufferlab.antiquities.client.utils.ModelBox;
import net.pufferlab.antiquities.client.utils.ModelRenderer;

public class MatrixHelper {

    public static double[][] getRotationMatrix(ModelRenderer renderer) {
        double cosX = Math.cos(renderer.rotateAngleX), sinX = Math.sin(renderer.rotateAngleX);
        double cosY = Math.cos(renderer.rotateAngleY), sinY = Math.sin(renderer.rotateAngleY);
        double cosZ = Math.cos(renderer.rotateAngleZ), sinZ = Math.sin(renderer.rotateAngleZ);

        // Combined rotation matrix Rz * Ry * Rx
        return new double[][] { { cosY * cosZ, cosZ * sinX * sinY - cosX * sinZ, sinX * sinZ + cosX * cosZ * sinY },
            { cosY * sinZ, cosX * cosZ + sinX * sinY * sinZ, cosX * sinY * sinZ - cosZ * sinX },
            { -sinY, cosY * sinX, cosX * cosY } };
    }

    public static double[] rotate(double[][] R, double px, double py, double pz, double x, double y, double z) {
        // Translate relative to pivot
        double dx = x - px, dy = y - py, dz = z - pz;

        // Matrix multiply
        double nx = R[0][0] * dx + R[0][1] * dy + R[0][2] * dz;
        double ny = R[1][0] * dx + R[1][1] * dy + R[1][2] * dz;
        double nz = R[2][0] * dx + R[2][1] * dy + R[2][2] * dz;

        // Translate back + 0.5 (your offset)
        return new double[] { nx + px + 0.5, ny + py + 0.5, nz + pz + 0.5 };
    }

    public static double[] getBounds(ModelRenderer renderer, ModelBox box, float scale) {
        // --- Rotation angles (cos/sin) ---
        double x1 = box.posX1 * scale;
        double x2 = box.posX2 * scale;
        double y1 = box.posY1 * scale;
        double y2 = box.posY2 * scale;
        double z1 = box.posZ1 * scale;
        double z2 = box.posZ2 * scale;

        double px = renderer.rotationPointX;
        double py = renderer.rotationPointY;
        double pz = renderer.rotationPointZ;

        // Build rotation matrix once
        double[][] R = MatrixHelper.getRotationMatrix(renderer);

        // 8 corners of the box
        double[][] corners = { { x1, y1, z1 }, { x2, y1, z1 }, { x1, y2, z1 }, { x2, y2, z1 }, { x1, y1, z2 },
            { x2, y1, z2 }, { x1, y2, z2 }, { x2, y2, z2 } };

        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY, minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, maxZ = Double.NEGATIVE_INFINITY;

        for (double[] c : corners) {
            double[] r = MatrixHelper.rotate(R, px, py, pz, c[0], c[1], c[2]);

            if (r[0] < minX) minX = r[0];
            if (r[0] > maxX) maxX = r[0];
            if (r[1] < minY) minY = r[1];
            if (r[1] > maxY) maxY = r[1];
            if (r[2] < minZ) minZ = r[2];
            if (r[2] > maxZ) maxZ = r[2];
        }

        return new double[] { minX, minY, minZ, maxX, maxY, maxZ };
    }
}

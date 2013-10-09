// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package ruby.bamboo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves,
//            BlockGrass

public class WorldGenSakura extends WorldGenerator {
    int type;

    public WorldGenSakura(int dmg) {
        type = dmg;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        int l;
        boolean flag;
        label0: {
            l = random.nextInt(3) + 4;
            flag = true;

            if (j >= 1) {
                world.getClass();

                if (j + l + 1 <= 128) {
                    break label0;
                }
            }

            return false;
        }
        label1: {
            for (int i1 = j; i1 <= j + 1 + l; i1++) {
                byte byte0 = 1;

                if (i1 == j) {
                    byte0 = 0;
                }

                if (i1 >= (j + 1 + l) - 2) {
                    byte0 = 2;
                }

                for (int i2 = i - byte0; i2 <= i + byte0 && flag; i2++) {
                    for (int l2 = k - byte0; l2 <= k + byte0 && flag; l2++) {
                        if (i1 >= 0) {
                            world.getClass();

                            if (i1 < 128) {
                                int j3 = world.getBlockId(i2, i1, l2);

                                if (j3 != 0 && j3 != BambooInit.sakuraleavsBID) {
                                    flag = false;
                                }

                                continue;
                            }
                        }

                        flag = false;
                    }
                }
            }

            if (!flag) {
                return false;
            }

            int j1 = world.getBlockId(i, j - 1, k);

            if (j1 == Block.grass.blockID || j1 == Block.dirt.blockID) {
                world.getClass();

                if (j < 128 - l - 1) {
                    break label1;
                }
            }

            return false;
        }
        world.setBlock(i, j - 1, k, Block.dirt.blockID, 0, 3);

        for (int k1 = (j - 3) + l; k1 <= j + l; k1++) {
            int j2 = k1 - (j + l);
            int i3 = 1 - j2 / 2;

            for (int k3 = i - i3; k3 <= i + i3; k3++) {
                int l3 = k3 - i;

                for (int i4 = k - i3; i4 <= k + i3; i4++) {
                    int j4 = i4 - k;

                    if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0) && !Block.opaqueCubeLookup[world.getBlockId(k3, k1, i4)]) {
                        world.setBlock(k3, k1, i4, BambooInit.sakuraleavsBID, type, 3);
                    }
                }
            }
        }

        for (int l1 = 0; l1 < l; l1++) {
            int k2 = world.getBlockId(i, j + l1, k);

            if (k2 == 0 || k2 == BambooInit.sakuraleavsBID) {
                world.setBlock(i, j + l1, k, BambooInit.sakuraLogBID, 0, 3);
            }
        }

        return true;
    }
}

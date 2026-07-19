package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.entity.EntityAnacondaPart;
import com.github.alexthe666.alexsmobs.entity.util.AnacondaPartIndex;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ModelAnaconda<T extends LivingEntity> extends AdvancedEntityModel<T> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox part;
    private AdvancedModelBox jaw;
    private AnacondaPartIndex partIndex;

    public ModelAnaconda(AnacondaPartIndex index) {
        texWidth = 128;
        texHeight = 128;
        this.partIndex = index;
        part = new AdvancedModelBox(this, "part");
        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 21.0F, 0);
        switch (index) {
            case HEAD -> {
                part.setRotationPoint(0.0F, 0, 0);
                part.setTextureOffset(62, 32).addBox(-3.5F, -3.0F, -9.0F, 7.0F, 3.0F, 10.0F, 0.0F, false);
                part.setTextureOffset(67, 0).addBox(-3.5F, -1.0F, -9.0F, 7.0F, 0.0F, 10.0F, 0.0F, false);
                jaw = new AdvancedModelBox(this, "        jaw");
                jaw.setRotationPoint(0, 0, 0);
                jaw.setTextureOffset(52, 55).addBox(-3.5F, -1.0F, -9, 7.0F, 4.0F, 10.0F, 0.0F, false);
                jaw.setTextureOffset(66, 11).addBox(-3.5F, 0.0F, -9, 7.0F, 0.0F, 10.0F, 0.0F, false);
                part.addChild(jaw);
            }
            case NECK -> {
                part.setRotationPoint(0.0F, 0, 0.0F);
                part.setTextureOffset(33, 32).addBox(-3.0F, -3.0F, -8, 6.0F, 6.0F, 16.0F, 0.0F, false);
            }
            case BODY -> {
                part.setRotationPoint(0.0F, 0, -8.0F);
                part.setTextureOffset(33, 8).addBox(-4.0F, -4.0F, 0, 8.0F, 7.0F, 16.0F, 0.0F, false);
            }
            case TAIL -> {
                part.setRotationPoint(0.0F, 0, -7.0F);
                part.setTextureOffset(29, 55).addBox(-1.5F, -2.0F, 0, 3.0F, 4.0F, 16.0F, 0.0F, false);
            }
        }
        root.addChild(part);

        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // MANDATORY: Always reset to default pose first (AAA Animation Rule)
        this.resetToDefaultPose();

        float partialTick = ageInTicks - entity.tickCount;
        float strangle = 0F;

        if (jaw != null && entity instanceof EntityAnaconda anaconda) { // HEAD
            strangle = anaconda.getStrangleProgress(partialTick);

            // Advanced AAA Strangle Mechanics with anticipation and recoil
            progressPositionPrev(part, strangle, 0, 4.2F, -1.0F, 5F);
            progressPositionPrev(jaw, strangle, 0, 0, 1.2F, 5F);
            progressRotationPrev(part, strangle, Maths.rad(12), 0, 0, 5F);
            progressRotationPrev(jaw, strangle, Maths.rad(165), 0, 0, 5F);

            // Head tracking with smooth interpolation and natural head stabilization
            this.part.rotateAngleY += netHeadYaw * 0.45F * Mth.DEG_TO_RAD;
            this.part.rotateAngleX += Math.min(0, headPitch * 0.45F * Mth.DEG_TO_RAD);

            // Serpentine lateral oscillation during movement (Slithering biomechanics)
            if (limbSwingAmount > 0.01F) {
                float slitherSpeed = 0.85F;
                float slitherAmplitude = 0.35F * limbSwingAmount;
                this.part.rotateAngleY += Maths.sin(limbSwing * slitherSpeed) * slitherAmplitude;
                this.part.rotationPointX += Maths.cos(limbSwing * slitherSpeed) * 2.5F * limbSwingAmount;
            }

            // Procedural Idle breathing and micro jaw flex
            float breath = Maths.cos(ageInTicks * 0.09F);
            if (strangle <= 0.01F) {
                this.part.rotationPointY += breath * 0.2F;
                if (jaw != null) {
                    jaw.rotateAngleX += breath * 0.04F;
                }
            }

            // Secondary motion during strangulation
            this.walk(part, 0.75F, 0.25F, false, 1F, 0.05F, ageInTicks, strangle * 0.2F);
            this.walk(jaw, 0.75F, 0.45F, true, 1F, -0.05F, ageInTicks, strangle * 0.2F);

        } else if (entity instanceof EntityAnacondaPart partEntity) { // NECK, BODY, TAIL
            float f = 1.01F;
            if (partEntity.getBodyIndex() % 2 == 1) {
                f = 1.0F;
            }
            float swell = partEntity.getSwellLerp(partialTick) * 0.18F;
            
            // Apply scale with muscular contraction simulation
            part.setScale(f + swell, f + swell, f + (swell * 0.5F));

            // Serpentine traveling wave propagation across body segments based on index and motion
            if (limbSwingAmount > 0.01F) {
                float waveSpeed = 0.9F;
                int indexOffset = partEntity.getBodyIndex();
                float wave = Maths.sin(limbSwing * waveSpeed - indexOffset * 0.4F) * limbSwingAmount * 0.4F;
                this.part.rotateAngleY += wave;
            } else {
                // Gentle resting idle undulation
                float idleWave = Maths.sin(ageInTicks * 0.07F + partEntity.getBodyIndex() * 0.3F) * 0.08F;
                this.part.rotateAngleY += idleWave;
            }
        }
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return jaw == null ? ImmutableList.of(root, part) : ImmutableList.of(root, part, jaw);
    }
}

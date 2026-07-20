package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelCatfishSmall extends AdvancedEntityModel<EntityCatfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_barbel;
    private final AdvancedModelBox right_barbel;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;

    public ModelCatfishSmall() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -3.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 6.0F, 14.0F, 0.0F, false);

        left_barbel = new AdvancedModelBox(this, "left_barbel");
        left_barbel.setRotationPoint(4.0F, 1.0F, -6.0F);
        body.addChild(left_barbel);
        setRotationAngle(left_barbel, 0.0F, -0.6109F, 0.0F);
        left_barbel.setTextureOffset(0, 10).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

        right_barbel = new AdvancedModelBox(this, "right_barbel");
        right_barbel.setRotationPoint(-4.0F, 1.0F, -6.0F);
        body.addChild(right_barbel);
        setRotationAngle(right_barbel, 0.0F, 0.6109F, 0.0F);
        right_barbel.setTextureOffset(0, 10).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, true);

        dorsal_fin = new AdvancedModelBox(this, "dorsal_fin");
        dorsal_fin.setRotationPoint(0.0F, -3.0F, -2.0F);
        body.addChild(dorsal_fin);
        setRotationAngle(dorsal_fin, -0.1309F, 0.0F, 0.0F);
        dorsal_fin.setTextureOffset(0, 21).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F, false);

        left_fin = new AdvancedModelBox(this, "left_fin");
        left_fin.setRotationPoint(4.0F, 2.0F, -3.0F);
        body.addChild(left_fin);
        setRotationAngle(left_fin, 0.0F, 0.0F, -0.9163F);
        left_fin.setTextureOffset(19, 21).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        right_fin = new AdvancedModelBox(this, "right_fin");
        right_fin.setRotationPoint(-4.0F, 2.0F, -3.0F);
        body.addChild(right_fin);
        setRotationAngle(right_fin, 0.0F, 0.0F, 0.9163F);
        right_fin.setTextureOffset(19, 21).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 4.0F, 0.0F, true);

        tail = new AdvancedModelBox(this, "tail");
        tail.setRotationPoint(0.0F, 0.0F, 7.0F);
        body.addChild(tail);
        tail.setTextureOffset(0, 21).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 5.0F, 10.0F, 0.0F, false);
        tail.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 2.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        tail.setTextureOffset(0, 0).addBox(0.0F, 2.0F, -1.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);

        tail_fin = new AdvancedModelBox(this, "tail_fin");
        tail_fin.setRotationPoint(0.0F, 2.0F, 9.0F);
        tail.addChild(tail_fin);
        tail_fin.setTextureOffset(19, 27).addBox(0.0F, -7.0F, -2.0F, 0.0F, 9.0F, 10.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public void setupAnim(EntityCatfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐟 CATFISH (SMALL) — WHISKERED BOTTOM-FEEDER ══════
        // IDENTITY: Small variant. Quicker barbel drag. Subcarangiform.

        // ── Core State ──────────────────────────────────────────────
        boolean inWater = entity.isInWater();
        float partialTick = ageInTicks - entity.tickCount;
        float spitProgress = entity.getSpitTime() > 0 ? (entity.getSpitTime() - partialTick) / 10.0F : 0;

        // ── Animation Parameters ────────────────────────────────────
        float swimSpeed = 0.6F;
        float swimDegree = 0.65F;

        // ── AAA BREATHING: Operculum Pumping ────────────────────────
        float breathCycle = Mth.cos(ageInTicks * 0.16F);
        body.setScale(1.0F + breathCycle * 0.012F,
                      1.0F + breathCycle * 0.01F,
                      1.0F);
        body.rotateAngleX += breathCycle * 0.01F;

        // ── AAA SWIMMING: Subcarangiform Body Wave ──────────────────
        // Small fish: faster, more agile undulation
        if (inWater) {
            this.swing(body, swimSpeed, swimDegree * 0.1F, false, 1.0F, 0F, limbSwing, limbSwingAmount);
            this.swing(tail, swimSpeed, swimDegree * 0.55F, false, 0.3F, 0F, limbSwing, limbSwingAmount);
            this.swing(tail_fin, swimSpeed, swimDegree * 1.1F, false, -1.0F, 0F, limbSwing, limbSwingAmount);
            // Body front counter-sway (no separate head bone — body front is head area)
            this.bob(body, swimSpeed * 0.3F, swimDegree * 0.04F, true, limbSwing, limbSwingAmount);
            // Vertical undulation
            float bodyBob = Mth.sin(limbSwing * swimSpeed * 0.8F + 1.5F) * swimDegree * 0.2F * limbSwingAmount;
            body.rotationPointY += bodyBob;
            tail.rotationPointY -= bodyBob * 0.4F;
            tail_fin.rotationPointY -= bodyBob * 0.6F;
        }

        // ── AAA PECTORAL FIN FIGURE-8 ROWING ────────────────────────
        if (inWater) {
            this.flap(left_fin, swimSpeed, swimDegree * 0.4F, false, 3.0F, -0.3F, limbSwing, limbSwingAmount);
            this.flap(right_fin, swimSpeed, swimDegree * 0.4F, true, 3.0F, -0.3F, limbSwing, limbSwingAmount);
            this.walk(left_fin, swimSpeed, swimDegree * 0.25F, false, 2.0F, 0.12F, limbSwing, limbSwingAmount);
            this.walk(right_fin, swimSpeed, swimDegree * 0.25F, true, 2.0F, 0.12F, limbSwing, limbSwingAmount);
        }

        // ── AAA DORSAL FIN ADJUSTMENT ───────────────────────────────
        if (inWater) {
            float dorsalAdjust = Mth.abs(Mth.sin(limbSwing * swimSpeed)) * limbSwingAmount * 0.05F;
            dorsal_fin.rotateAngleX += dorsalAdjust;
        }

        // ── AAA IDLE BEHAVIOR ───────────────────────────────────────
        float idleSpeed = 0.22F;
        float idleDegree = 0.2F;
        float idleAmount = 1.0F - (inWater ? limbSwingAmount * 0.5F : 0F);

        // Buoyancy drift — small fish bob more noticeably
        float hoverDrift = Mth.sin(ageInTicks * 0.09F + 1.0F) * 0.3F;
        float hoverDrift2 = Mth.cos(ageInTicks * 0.11F + 0.6F) * 0.18F;
        body.rotationPointY += hoverDrift * idleAmount;
        body.rotationPointZ += hoverDrift2 * idleAmount * 0.2F;

        // Gentle idle body sway — continuous fish motion even when "still"
        this.swing(body, idleSpeed * 0.6F, idleDegree * 0.2F, false, 0F, 0F, ageInTicks, idleAmount);
        this.swing(tail, idleSpeed * 0.6F, idleDegree * 0.4F, false, -1.0F, 0F, ageInTicks, idleAmount);
        this.swing(tail_fin, idleSpeed * 0.6F, idleDegree * 0.6F, false, -2.0F, 0F, ageInTicks, idleAmount);

        // Idle pectoral fin rippling
        this.flap(left_fin, idleSpeed, idleDegree * 0.3F, false, 3.5F, -0.06F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed, idleDegree * 0.3F, true, 3.5F, -0.06F, ageInTicks, idleAmount);
        this.flap(left_fin, idleSpeed * 1.3F, idleDegree * 0.1F, false, 5.0F, 0F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed * 1.3F, idleDegree * 0.1F, true, 5.5F, 0F, ageInTicks, idleAmount);

        // Idle dorsal fin wave
        this.walk(dorsal_fin, idleSpeed, idleDegree * 0.15F, true, 2.0F, 0.08F, ageInTicks, idleAmount);

        // ── AAA BARBEL PHYSICS ──────────────────────────────────────
        float swimDrag = inWater ? limbSwingAmount * 0.5F : 0F;
        left_barbel.rotateAngleY -= swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.25F;
        right_barbel.rotateAngleY += swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.25F;
        left_barbel.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.06F;
        right_barbel.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.06F;

        // Idle probing — nimble, fast-moving barbels on small fish
        this.swing(left_barbel, idleSpeed * 1.2F, idleDegree * 0.75F, false, 2.0F, 0.1F, ageInTicks, idleAmount);
        this.swing(right_barbel, idleSpeed * 1.2F, idleDegree * 0.75F, true, 2.2F, 0.1F, ageInTicks, idleAmount);
        this.flap(left_barbel, idleSpeed * 0.9F, idleDegree * 0.4F, false, 3.0F, 0.06F, ageInTicks, idleAmount);
        this.flap(right_barbel, idleSpeed * 0.9F, idleDegree * 0.4F, true, 2.8F, 0.06F, ageInTicks, idleAmount);

        // ── AAA FEEDING / SPITTING ANIMATION ───────────────────────
        if (spitProgress > 0.01F) {
            float spitCurve = Mth.sin(spitProgress * Mth.PI);
            // Since small catfish has no separate head bone, simulate mouth opening
            // via body front rotation and scale pulse
            body.rotateAngleX += spitCurve * 0.2F;
            body.setScale(body.getScaleX() + spitCurve * 0.04F,
                          body.getScaleY() + spitCurve * 0.03F,
                          body.getScaleZ() + spitCurve * 0.02F);
            // Fins flare
            left_fin.rotateAngleZ += spitCurve * 0.15F;
            right_fin.rotateAngleZ -= spitCurve * 0.15F;
            // Barbels sweep back from the blast
            left_barbel.rotateAngleY -= spitCurve * 0.3F;
            right_barbel.rotateAngleY += spitCurve * 0.3F;
        }

        // ── AAA LAND FLOPPING ───────────────────────────────────────
        if (!inWater) {
            float flopIntensity = 1.1F;
            float flopSpeed = 1.2F;
            this.swing(body, flopSpeed, flopIntensity, false, 0F, 0F, ageInTicks, 1.0F);
            this.swing(tail, flopSpeed, flopIntensity * 1.1F, false, -0.8F, 0F, ageInTicks, 1.0F);
            this.swing(tail_fin, flopSpeed, flopIntensity * 1.3F, false, -1.5F, 0F, ageInTicks, 1.0F);
            this.flap(body, flopSpeed * 0.8F, 0.18F, false, 1.0F, 0F, ageInTicks, 1.0F);
            this.flap(tail, flopSpeed * 0.8F, 0.25F, false, 0.5F, 0F, ageInTicks, 1.0F);
            left_fin.rotateAngleZ += 0.25F;
            right_fin.rotateAngleZ -= 0.25F;
            left_fin.rotateAngleY += 0.12F;
            right_fin.rotateAngleY -= 0.12F;
            left_barbel.rotateAngleX += 0.18F;
            right_barbel.rotateAngleX += 0.18F;
            dorsal_fin.rotateAngleX -= 0.12F;
        }
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, dorsal_fin, tail, left_fin, right_fin, left_barbel, right_barbel, tail_fin);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }

}

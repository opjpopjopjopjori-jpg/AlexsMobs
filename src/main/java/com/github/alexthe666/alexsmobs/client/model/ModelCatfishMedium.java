package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelCatfishMedium extends AdvancedEntityModel<EntityCatfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_BigWhisker;
    private final AdvancedModelBox right_BigWhisker;
    private final AdvancedModelBox left_SmallWhisker;
    private final AdvancedModelBox right_SmallWhisker;

    public ModelCatfishMedium() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -5.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-7.0F, -6.0F, -10.0F, 14.0F, 11.0F, 18.0F, 0.0F, false);

        dorsal_fin = new AdvancedModelBox(this, "dorsal_fin");
        dorsal_fin.setRotationPoint(0.0F, -6.0F, 0.0F);
        body.addChild(dorsal_fin);
        dorsal_fin.setTextureOffset(0, 0).addBox(0.0F, -4.0F, -1.0F, 0.0F, 4.0F, 7.0F, 0.0F, false);

        left_fin = new AdvancedModelBox(this, "left_fin");
        left_fin.setRotationPoint(7.0F, 3.0F, -2.0F);
        body.addChild(left_fin);
        setRotationAngle(left_fin, 0.0F, 0.0F, -0.7854F);
        left_fin.setTextureOffset(0, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);

        right_fin = new AdvancedModelBox(this, "right_fin");
        right_fin.setRotationPoint(-7.0F, 3.0F, -2.0F);
        body.addChild(right_fin);
        setRotationAngle(right_fin, 0.0F, 0.0F, 0.7854F);
        right_fin.setTextureOffset(0, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 5.0F, 0.0F, true);

        tail = new AdvancedModelBox(this, "tail");
        tail.setRotationPoint(0.0F, -2.0F, 9.0F);
        body.addChild(tail);
        tail.setTextureOffset(0, 30).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 8.0F, 19.0F, 0.0F, false);
        tail.setTextureOffset(53, 18).addBox(0.0F, 4.0F, 1.0F, 0.0F, 4.0F, 12.0F, 0.0F, false);
        tail.setTextureOffset(8, 0).addBox(0.0F, -6.0F, 4.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        tail_fin = new AdvancedModelBox(this, "tail_fin");
        tail_fin.setRotationPoint(0.0F, -4.0F, 17.0F);
        tail.addChild(tail_fin);
        tail_fin.setTextureOffset(38, 37).addBox(0.0F, -3.0F, -6.0F, 0.0F, 13.0F, 21.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setRotationPoint(0.0F, -1.0F, -11.0F);
        body.addChild(head);
        head.setTextureOffset(47, 0).addBox(-6.0F, -5.0F, -5.0F, 12.0F, 9.0F, 6.0F, 0.0F, false);

        left_BigWhisker = new AdvancedModelBox(this, "left_BigWhisker");
        left_BigWhisker.setRotationPoint(6.0F, 1.0F, -2.0F);
        head.addChild(left_BigWhisker);
        setRotationAngle(left_BigWhisker, 0.0F, -0.5236F, 0.0F);
        left_BigWhisker.setTextureOffset(0, 12).addBox(0.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, 0.0F, false);

        right_BigWhisker = new AdvancedModelBox(this, "right_BigWhisker");
        right_BigWhisker.setRotationPoint(-6.0F, 1.0F, -2.0F);
        head.addChild(right_BigWhisker);
        setRotationAngle(right_BigWhisker, 0.0F, 0.5236F, 0.0F);
        right_BigWhisker.setTextureOffset(0, 12).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, 0.0F, true);

        left_SmallWhisker = new AdvancedModelBox(this, "left_SmallWhisker");
        left_SmallWhisker.setRotationPoint(6.0F, 3.0F, -2.0F);
        head.addChild(left_SmallWhisker);
        setRotationAngle(left_SmallWhisker, 0.0F, 0.0F, 0.4363F);
        left_SmallWhisker.setTextureOffset(6, 30).addBox(0.0F, 0.0F, 0.0F, 5.0F, 3.0F, 0.0F, 0.0F, false);

        right_SmallWhisker = new AdvancedModelBox(this, "right_SmallWhisker");
        right_SmallWhisker.setRotationPoint(-6.0F, 3.0F, -2.0F);
        head.addChild(right_SmallWhisker);
        setRotationAngle(right_SmallWhisker, 0.0F, 0.0F, -0.4363F);
        right_SmallWhisker.setTextureOffset(6, 30).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 3.0F, 0.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public void setupAnim(EntityCatfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐟 CATFISH (MEDIUM) — WHISKERED BOTTOM-FEEDER ══════
        // IDENTITY: Medium variant. Barbels drag and sense substrate.
        // Subcarangiform swimming. Dorsal fin ripple.

        // ── Core State ──────────────────────────────────────────────
        boolean inWater = entity.isInWater();
        float partialTick = ageInTicks - entity.tickCount;
        float spitProgress = entity.getSpitTime() > 0 ? (entity.getSpitTime() - partialTick) / 10.0F : 0;

        // ── Animation Parameters ────────────────────────────────────
        float swimSpeed = 0.58F;
        float swimDegree = 0.7F;

        // ── AAA BREATHING: Operculum Pumping ────────────────────────
        float breathCycle = Mth.cos(ageInTicks * 0.14F);
        body.setScale(1.0F + breathCycle * 0.015F,
                      1.0F + breathCycle * 0.012F,
                      1.0F);
        head.rotationPointY += breathCycle * 0.08F;
        head.rotateAngleX += breathCycle * 0.012F;

        // ── AAA SWIMMING: Subcarangiform Body Wave ──────────────────
        if (inWater) {
            this.swing(body, swimSpeed, swimDegree * 0.12F, false, 1.0F, 0F, limbSwing, limbSwingAmount);
            this.swing(tail, swimSpeed, swimDegree * 0.6F, false, 0.3F, 0F, limbSwing, limbSwingAmount);
            this.swing(tail_fin, swimSpeed, swimDegree * 1.15F, false, -1.0F, 0F, limbSwing, limbSwingAmount);
            this.swing(head, swimSpeed, swimDegree * 0.07F, true, 2.0F, 0F, limbSwing, limbSwingAmount);
            // Vertical undulation
            float bodyBob = Mth.sin(limbSwing * swimSpeed * 0.8F + 1.5F) * swimDegree * 0.22F * limbSwingAmount;
            body.rotationPointY += bodyBob;
            head.rotationPointY -= bodyBob * 0.25F;
            tail.rotationPointY -= bodyBob * 0.45F;
            tail_fin.rotationPointY -= bodyBob * 0.65F;
        }

        // ── AAA PECTORAL FIN FIGURE-8 ROWING ────────────────────────
        if (inWater) {
            this.flap(left_fin, swimSpeed, swimDegree * 0.45F, false, 3.0F, -0.35F, limbSwing, limbSwingAmount);
            this.flap(right_fin, swimSpeed, swimDegree * 0.45F, true, 3.0F, -0.35F, limbSwing, limbSwingAmount);
            this.walk(left_fin, swimSpeed, swimDegree * 0.28F, false, 2.0F, 0.15F, limbSwing, limbSwingAmount);
            this.walk(right_fin, swimSpeed, swimDegree * 0.28F, true, 2.0F, 0.15F, limbSwing, limbSwingAmount);
        }

        // ── AAA DORSAL FIN ADJUSTMENT ───────────────────────────────
        if (inWater) {
            float dorsalAdjust = Mth.abs(Mth.sin(limbSwing * swimSpeed)) * limbSwingAmount * 0.06F;
            dorsal_fin.rotateAngleX += dorsalAdjust;
        }

        // ── AAA IDLE BEHAVIOR ───────────────────────────────────────
        float idleSpeed = 0.2F;
        float idleDegree = 0.22F;
        float idleAmount = 1.0F - (inWater ? limbSwingAmount * 0.5F : 0F);

        // Buoyancy drift
        float hoverDrift = Mth.sin(ageInTicks * 0.08F + 1.1F) * 0.35F;
        float hoverDrift2 = Mth.cos(ageInTicks * 0.1F + 0.7F) * 0.2F;
        body.rotationPointY += hoverDrift * idleAmount;
        body.rotationPointZ += hoverDrift2 * idleAmount * 0.25F;

        // Gentle idle body sway
        this.swing(body, idleSpeed * 0.6F, idleDegree * 0.25F, false, 0F, 0F, ageInTicks, idleAmount);
        this.swing(tail, idleSpeed * 0.6F, idleDegree * 0.45F, false, -1.0F, 0F, ageInTicks, idleAmount);
        this.swing(tail_fin, idleSpeed * 0.6F, idleDegree * 0.65F, false, -2.0F, 0F, ageInTicks, idleAmount);
        this.swing(head, idleSpeed * 0.6F, idleDegree * 0.12F, true, 1.0F, 0F, ageInTicks, idleAmount);

        // Idle pectoral fin rippling
        this.flap(left_fin, idleSpeed, idleDegree * 0.35F, false, 3.5F, -0.08F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed, idleDegree * 0.35F, true, 3.5F, -0.08F, ageInTicks, idleAmount);
        this.flap(left_fin, idleSpeed * 1.3F, idleDegree * 0.12F, false, 5.0F, 0F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed * 1.3F, idleDegree * 0.12F, true, 5.5F, 0F, ageInTicks, idleAmount);

        // ── AAA BARBEL PHYSICS ──────────────────────────────────────
        float swimDrag = inWater ? limbSwingAmount * 0.55F : 0F;
        left_BigWhisker.rotateAngleY -= swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.3F;
        right_BigWhisker.rotateAngleY += swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.3F;
        left_BigWhisker.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.08F;
        right_BigWhisker.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.08F;

        this.swing(left_BigWhisker, idleSpeed, idleDegree * 0.85F, false, 2.0F, 0.12F, ageInTicks, idleAmount);
        this.swing(right_BigWhisker, idleSpeed, idleDegree * 0.85F, true, 2.2F, 0.12F, ageInTicks, idleAmount);
        this.flap(left_BigWhisker, idleSpeed * 0.7F, idleDegree * 0.45F, false, 3.0F, 0.08F, ageInTicks, idleAmount);
        this.flap(right_BigWhisker, idleSpeed * 0.7F, idleDegree * 0.45F, true, 2.8F, 0.08F, ageInTicks, idleAmount);

        this.swing(left_SmallWhisker, idleSpeed * 1.4F, idleDegree * 0.65F, false, 3.0F, 0.08F, ageInTicks, idleAmount);
        this.swing(right_SmallWhisker, idleSpeed * 1.4F, idleDegree * 0.65F, true, 3.3F, 0.08F, ageInTicks, idleAmount);
        this.flap(left_SmallWhisker, idleSpeed * 1.1F, idleDegree * 0.35F, false, 4.0F, 0.04F, ageInTicks, idleAmount);
        this.flap(right_SmallWhisker, idleSpeed * 1.1F, idleDegree * 0.35F, true, 4.2F, 0.04F, ageInTicks, idleAmount);
        left_SmallWhisker.rotateAngleY -= swimDrag * Mth.sin(limbSwing * swimSpeed + 0.7F) * 0.2F;
        right_SmallWhisker.rotateAngleY += swimDrag * Mth.sin(limbSwing * swimSpeed + 0.7F) * 0.2F;

        // ── AAA FEEDING / SPITTING ANIMATION ───────────────────────
        if (spitProgress > 0.01F) {
            float spitCurve = Mth.sin(spitProgress * Mth.PI);
            head.rotateAngleX += spitCurve * 0.4F;
            head.rotationPointZ += spitCurve * 1.5F;
            body.setScale(body.getScaleX() + spitCurve * 0.06F,
                          body.getScaleY() + spitCurve * 0.04F,
                          body.getScaleZ());
            left_fin.rotateAngleZ += spitCurve * 0.2F;
            right_fin.rotateAngleZ -= spitCurve * 0.2F;
            tail_fin.rotateAngleY += Mth.sin(spitCurve * Mth.PI * 2) * 0.08F;
        }

        // ── AAA LAND FLOPPING ───────────────────────────────────────
        if (!inWater) {
            float flopIntensity = 1.2F;
            float flopSpeed = 1.15F;
            this.swing(body, flopSpeed, flopIntensity, false, 0F, 0F, ageInTicks, 1.0F);
            this.swing(tail, flopSpeed, flopIntensity * 1.15F, false, -0.8F, 0F, ageInTicks, 1.0F);
            this.swing(tail_fin, flopSpeed, flopIntensity * 1.4F, false, -1.5F, 0F, ageInTicks, 1.0F);
            this.flap(body, flopSpeed * 0.8F, 0.2F, false, 1.0F, 0F, ageInTicks, 1.0F);
            this.flap(tail, flopSpeed * 0.8F, 0.3F, false, 0.5F, 0F, ageInTicks, 1.0F);
            left_fin.rotateAngleZ += 0.3F;
            right_fin.rotateAngleZ -= 0.3F;
            left_fin.rotateAngleY += 0.15F;
            right_fin.rotateAngleY -= 0.15F;
            left_BigWhisker.rotateAngleX += 0.2F;
            right_BigWhisker.rotateAngleX += 0.2F;
            left_SmallWhisker.rotateAngleX += 0.25F;
            right_SmallWhisker.rotateAngleX += 0.25F;
            dorsal_fin.rotateAngleX -= 0.15F;
        }

        // ── HEAD TRACKING ──────────────────────────────────────────
        this.faceTarget(netHeadYaw, headPitch, 1.0F, head);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, head, body, dorsal_fin, tail, left_fin, right_fin,
                left_BigWhisker, right_BigWhisker, left_SmallWhisker, right_SmallWhisker, tail_fin);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }

}

package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelFlyingFish extends AdvancedEntityModel<EntityFlyingFish> {
    private final AdvancedModelBox root, body, tail, tail_fin, left_pectoralFin, right_pectoralFin, left_pelvicFin, right_pelvicFin;

    public ModelFlyingFish() {
        texWidth = 64;
        texHeight = 32;
        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);
        body = new AdvancedModelBox(this, "body");
        body.setPos(0.0F, -1.5F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -5.0F, 3.0F, 3.0F, 9.0F, 0.0F, false);
        tail = new AdvancedModelBox(this, "tail");
        tail.setPos(0.0F, -0.5F, 4.0F);
        body.addChild(tail);
        tail.setTextureOffset(16, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        tail_fin = new AdvancedModelBox(this, "tail_fin");
        tail_fin.setPos(0.0F, 0.0F, 4.0F);
        tail.addChild(tail_fin);
        tail_fin.setTextureOffset(0, 13).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
        left_pectoralFin = new AdvancedModelBox(this, "left_pectoralFin");
        left_pectoralFin.setPos(1.5F, 0.0F, -3.0F);
        body.addChild(left_pectoralFin);
        left_pectoralFin.setTextureOffset(0, 20).addBox(0.0F, 0.0F, -1.0F, 8.0F, 0.0F, 3.0F, 0.0F, false);
        right_pectoralFin = new AdvancedModelBox(this, "right_pectoralFin");
        right_pectoralFin.setPos(-1.5F, 0.0F, -3.0F);
        body.addChild(right_pectoralFin);
        right_pectoralFin.setTextureOffset(0, 20).addBox(-8.0F, 0.0F, -1.0F, 8.0F, 0.0F, 3.0F, 0.0F, true);
        left_pelvicFin = new AdvancedModelBox(this, "left_pelvicFin");
        left_pelvicFin.setPos(1.0F, 0.0F, 2.0F);
        body.addChild(left_pelvicFin);
        left_pelvicFin.setTextureOffset(25, 0).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);
        right_pelvicFin = new AdvancedModelBox(this, "right_pelvicFin");
        right_pelvicFin.setPos(-1.0F, 0.0F, 2.0F);
        body.addChild(right_pelvicFin);
        right_pelvicFin.setTextureOffset(25, 0).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() { return ImmutableList.of(root); }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, tail, tail_fin, left_pectoralFin, right_pectoralFin, left_pelvicFin, right_pelvicFin);
    }

    @Override
    public void setupAnim(EntityFlyingFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐟 FLYING FISH — SUBCARANGIFORM SURFACE-TO-AIR GLIDER ══════
        // IDENTITY: 4-stage takeoff: swim→surface break→tail scull→glide.
        // Pectoral fins flutter at high frequency during glide for stability.
        // Subcarangiform body undulation during swim (body→tail→tailFin wave).
        // UNIQUE vs all other fish: transitions from water to air.

        float swimSpeed = 0.5F, swimDegree = 0.3F;
        float partialTick = Minecraft.getInstance().getFrameTime();
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;

        // ── AAA VISIBLE BREATHING ──────────────────────────────────
        float breath = Mth.cos(ageInTicks * 0.12F);
        body.setScale(1.0F + breath * 0.015F, 1.0F + breath * 0.02F, 1.0F + breath * 0.015F);
        body.rotationPointY += breath * 0.04F;

        // ── AAA SUBCARANGIFORM SWIMMING ────────────────────────────
        AdvancedModelBox[] swimChain = {body, tail, tail_fin};
        this.chainSwing(swimChain, swimSpeed, swimDegree * 0.8F, -2, limbSwing, limbSwingAmount);
        this.bob(body, swimSpeed, swimDegree * 3F, false, limbSwing, limbSwingAmount);

        // ── AAA TAKEOFF: staged surface-to-air transition ──────────
        // Stage 1 (flyProgress 0-2): fins tuck against body for speed
        // Stage 2 (flyProgress 2-3): body angles up to break surface
        // Stage 3 (flyProgress 3-4): tail sculls water for extra thrust
        // Stage 4 (flyProgress 4-5): fins spread for glide
        progressRotationPrev(left_pectoralFin, flyProgress, 0, Maths.rad(85), Maths.rad(180), 5F);
        progressRotationPrev(right_pectoralFin, flyProgress, 0, Maths.rad(-85), Maths.rad(-180), 5F);
        progressRotationPrev(left_pelvicFin, flyProgress, 0, Maths.rad(70), Maths.rad(160), 5F);
        progressRotationPrev(right_pelvicFin, flyProgress, 0, Maths.rad(-70), Maths.rad(-160), 5F);
        progressPositionPrev(body, flyProgress, 0, 3, 0, 5F);

        if (flyProgress > 0) {
            // ── GLIDING: fins spread, body banks, fin flutter ──────
            body.rotateAngleZ += Mth.sin(ageInTicks * 0.3F) * 0.1F * flyProgress;
            this.bob(body, 0.3F, 0.5F * flyProgress, false, ageInTicks, 1);
            // AAA FIN FLUTTER during glide — rapid micro-flap for stability
            float flutterAmp = 0.025F * flyProgress * 0.2F;
            this.flap(left_pectoralFin, 2.8F, flutterAmp, true, 0, 0, ageInTicks, 1);
            this.flap(right_pectoralFin, 2.8F, flutterAmp, false, 0, 0, ageInTicks, 1);
            this.flap(left_pelvicFin, 2.5F, flutterAmp * 0.7F, true, 0.3F, 0, ageInTicks, 1);
            this.flap(right_pelvicFin, 2.5F, flutterAmp * 0.7F, false, 0.3F, 0, ageInTicks, 1);
            // AAA TAIL SCULL during early takeoff — rapid tail wag
            if (flyProgress < 4F) {
                tail_fin.rotateAngleY += Mth.sin(ageInTicks * 3.5F) * 0.1F * (4F - flyProgress) * 0.25F;
                tail.rotateAngleY += Mth.sin(ageInTicks * 3.5F + 0.5F) * 0.06F * (4F - flyProgress) * 0.25F;
            }
        } else {
            // ── SWIMMING: fins tuck, body undulates ────────────────
            this.flap(left_pectoralFin, 0.3F, 0.15F, false, 1F, 0, ageInTicks, 1);
            this.flap(right_pectoralFin, 0.3F, 0.15F, true, 1F, 0, ageInTicks, 1);
        }

        // ── TAIL FIN: always active ────────────────────────────────
        this.swing(tail_fin, swimSpeed, swimDegree * 1.5F, false, 0.5F, 0, limbSwing, limbSwingAmount);

        this.faceTarget(netHeadYaw, headPitch, 1, body);
    }
}

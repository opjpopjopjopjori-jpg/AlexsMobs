package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelSeal extends AdvancedEntityModel<EntitySeal> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox leftLeg;
    public final AdvancedModelBox rightLeg;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox head;
    public final AdvancedModelBox leftWhisker;
    public final AdvancedModelBox rightWhisker;

    public ModelSeal() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);

        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -3.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-6.5F, -6.0F, -9.0F, 13.0F, 9.0F, 18.0F, 0.0F, false);

        tail = new AdvancedModelBox(this, "tail");
        tail.setRotationPoint(0.0F, 1.0F, 9.0F);
        body.addChild(tail);
        tail.setTextureOffset(0, 28).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 6.0F, 14.0F, 0.0F, false);

        leftLeg = new AdvancedModelBox(this, "leftLeg");
        leftLeg.setRotationPoint(2.0F, -0.2F, 13.4F);
        tail.addChild(leftLeg);
        setRotationAngle(leftLeg, 0.0F, 0.3491F, 0.0F);
        leftLeg.setTextureOffset(45, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 8.0F, 0.0F, false);

        rightLeg = new AdvancedModelBox(this, "rightLeg");
        rightLeg.setRotationPoint(-2.0F, -0.2F, 13.4F);
        tail.addChild(rightLeg);
        setRotationAngle(rightLeg, 0.0F, -0.3491F, 0.0F);
        rightLeg.setTextureOffset(45, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 8.0F, 0.0F, true);

        leftArm = new AdvancedModelBox(this, "leftArm");
        leftArm.setRotationPoint(7.5F, 2.5F, -4.0F);
        body.addChild(leftArm);
        leftArm.setTextureOffset(31, 28).addBox(-1.0F, -0.5F, -2.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
        leftArm.setTextureOffset(0, 7).addBox(7.0F, 0.5F, -2.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);

        rightArm = new AdvancedModelBox(this, "rightArm");
        rightArm.setRotationPoint(-7.5F, 2.5F, -4.0F);
        body.addChild(rightArm);
        rightArm.setTextureOffset(31, 28).addBox(-7.0F, -0.5F, -2.0F, 8.0F, 1.0F, 5.0F, 0.0F, true);
        rightArm.setTextureOffset(0, 7).addBox(-7.0F, 0.5F, -2.0F, 0.0F, 2.0F, 5.0F, 0.0F, true);

        head = new AdvancedModelBox(this, "head");
        head.setRotationPoint(0.0F, -1.0F, -5.0F);
        body.addChild(head);
        head.setTextureOffset(35, 39).addBox(-3.5F, -3.0F, -9.0F, 7.0F, 6.0F, 10.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-2.5F, 0.0F, -12.0F, 5.0F, 3.0F, 3.0F, 0.0F, false);

        leftWhisker = new AdvancedModelBox(this, "leftWhisker");
        leftWhisker.setRotationPoint(2.5F, 2.0F, -11.0F);
        head.addChild(leftWhisker);
        setRotationAngle(leftWhisker, 0.0F, -0.2182F, 0.0F);
        leftWhisker.setTextureOffset(0, 7).addBox(0.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

        rightWhisker = new AdvancedModelBox(this, "rightWhisker");
        rightWhisker.setRotationPoint(-2.5F, 2.0F, -11.0F);
        head.addChild(rightWhisker);
        setRotationAngle(rightWhisker, 0.0F, 0.2182F, 0.0F);
        rightWhisker.setTextureOffset(0, 7).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, tail, leftLeg, rightLeg, head, leftArm, rightArm, leftWhisker, rightWhisker);
    }

    @Override
    public void setupAnim(EntitySeal entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🦭 SEAL — VERTICAL-UNDULATION MARINE MAMMAL ══════
        // IDENTITY: Phocid seal. Swims via VERTICAL pelvic undulation
        // (dolphin-like up/down, NOT fish-like side-to-side). Rear flippers
        // provide thrust. Front flippers are independent STEERING surfaces.
        // On land: belly-bounces like giant caterpillar. Whiskers sense fish.
        // BIOMECHANICS: chainWave (lateral) has been REPLACED with explicit
        // vertical pitch on body/tail/legs at staggered phases. This is
        // correct for phocid seals. Speed modulates undulation amplitude.

        float partialTick = Minecraft.getInstance().getFrameTime();
        float baskProgress = entity.prevBaskProgress + (entity.baskProgress - entity.prevBaskProgress) * partialTick;
        float swimAngle = entity.prevSwimAngle + (entity.getSwimAngle() - entity.prevSwimAngle) * partialTick;
        float diggingProgress = entity.prevDigProgress + (entity.digProgress - entity.prevDigProgress) * partialTick;
        float bobbingProgress = entity.prevBobbingProgress + (entity.bobbingProgress - entity.prevBobbingProgress) * partialTick;
        int baskType = entity.isTearsEasterEgg() ? -1 : entity.getId() % 5;
        boolean inWater = entity.isInWater();

        //══════ SPEED-DEPENDENT SWIM AMPLITUDE ══════
        float speed = (float) entity.getDeltaMovement().length();
        float speedFactor = 0.5F + speed * 1.5F;
        float swimSpeed = 0.45F;
        float swimDegree = 0.55F * speedFactor;

        //══════ BREATHING: visible chest expansion ══════
        float breath = Mth.cos(ageInTicks * 0.06F);
        body.setScale(1.0F + breath * 0.015F, 1.0F + breath * 0.02F, 1.0F + breath * 0.01F);
        body.rotationPointY += breath * 0.08F;

        //══════ WHISKERS: independent fish-detecting sensors ══════
        this.flap(leftWhisker, 0.25F, 0.05F, false, 1F, 0, ageInTicks, 1);
        this.flap(rightWhisker, 0.2F, 0.04F, true, 1.5F, 0, ageInTicks, 1);
        leftWhisker.rotateAngleY += Mth.sin(ageInTicks * 0.35F) * 0.06F;
        rightWhisker.rotateAngleY -= Mth.sin(ageInTicks * 0.35F + 0.7F) * 0.06F;

        //══════ HEAD: slow scanning + surface-breaching bob ══════
        this.head.rotationPointZ += (float) (Math.sin(ageInTicks * 0.7F) * (double) 0.5F * bobbingProgress);
        // Idle head scan when in water
        if (inWater && baskProgress < 0.1F && diggingProgress < 0.1F) {
            head.rotateAngleY += Mth.sin(ageInTicks * 0.12F) * 0.15F;
            head.rotateAngleZ += Mth.sin(ageInTicks * 0.08F + 1F) * 0.06F;
        }

        //══════ DIGGING STATE (preserved) ══════
        progressRotationPrev(body, diggingProgress, Maths.rad(70), 0, 0, 5F);
        progressRotationPrev(head, diggingProgress, Maths.rad(10), 0, 0, 5F);
        progressRotationPrev(tail, diggingProgress, Maths.rad(-10), 0, 0, 5F);
        progressRotationPrev(leftArm, diggingProgress, 0, Maths.rad(30), 0, 5F);
        progressRotationPrev(rightArm, diggingProgress, 0, Maths.rad(-30), 0, 5F);
        progressPositionPrev(body, diggingProgress, 0, -12F, 2, 5F);
        progressPositionPrev(leftArm, diggingProgress, -1, 0, -2, 5F);
        progressPositionPrev(rightArm, diggingProgress, 1, 0, -2, 5F);
        if (diggingProgress > 0) {
            float amount = diggingProgress * 0.2F;
            this.swing(rightArm, 0.6F, 0.85F, true, 1F, -0.1F, ageInTicks, amount);
            this.swing(leftArm, 0.6F, 0.85F, false, 1F, -0.1F, ageInTicks, amount);
            this.walk(tail, 0.6F, 0.1F, false, 3F, -0.1F, ageInTicks, amount);
            this.bob(body, 0.3F, 3F, true, ageInTicks, amount);
        }

        //══════ BASKING STATE (5 poses — preserved) ══════
        if (baskProgress > 0 && !entity.isTearsEasterEgg()) {
            this.walk(head, 0.05F, 0.2F, true, 1F, -0.1F, ageInTicks, 1);
            if (baskType == 0) {
                progressRotationPrev(body, baskProgress, 0, 0, Maths.rad(70), 5F);
                progressRotationPrev(head, baskProgress, 0, Maths.rad(-20), Maths.rad(20), 5F);
                progressRotationPrev(leftArm, baskProgress, 0, 0, Maths.rad(110), 5F);
                progressRotationPrev(rightArm, baskProgress, 0, 0, Maths.rad(-120), 5F);
                progressRotationPrev(tail, baskProgress, 0, Maths.rad(15), Maths.rad(-20), 5F);
                progressRotationPrev(leftLeg, baskProgress, 0, Maths.rad(-15), 0, 5F);
                progressRotationPrev(rightLeg, baskProgress, 0, Maths.rad(35), Maths.rad(30), 5F);
                progressPositionPrev(leftArm, baskProgress, -2, 0, 0, 5F);
                progressPositionPrev(rightArm, baskProgress, 1, 0, 0, 5F);
                progressPositionPrev(head, baskProgress, 0, 0, 1, 5F);
                progressPositionPrev(body, baskProgress, 0, -4, 1, 5F);
            } else if (baskType == 1) {
                progressRotationPrev(body, baskProgress, 0, 0, Maths.rad(-70), 5F);
                progressRotationPrev(head, baskProgress, 0, Maths.rad(20), Maths.rad(-20), 5F);
                progressRotationPrev(rightArm, baskProgress, 0, 0, Maths.rad(-110), 5F);
                progressRotationPrev(leftArm, baskProgress, 0, 0, Maths.rad(120), 5F);
                progressRotationPrev(tail, baskProgress, 0, Maths.rad(-15), Maths.rad(20), 5F);
                progressRotationPrev(rightLeg, baskProgress, 0, Maths.rad(15), 0, 5F);
                progressRotationPrev(leftLeg, baskProgress, 0, Maths.rad(-35), Maths.rad(-30), 5F);
                progressPositionPrev(rightArm, baskProgress, 2, 0, 0, 5F);
                progressPositionPrev(leftArm, baskProgress, -1, 0, 0, 5F);
                progressPositionPrev(head, baskProgress, 0, 0, 1, 5F);
                progressPositionPrev(body, baskProgress, 0, -4, 0, 5F);
            } else if (baskType == 2) {
                progressRotationPrev(rightArm, baskProgress, 0, 0, Maths.rad(30), 5F);
                progressRotationPrev(leftArm, baskProgress, 0, 0, Maths.rad(-40), 5F);
                progressRotationPrev(body, baskProgress, 0, 0, Maths.rad(160), 5F);
                progressRotationPrev(tail, baskProgress, Maths.rad(15), 0, 0, 5F);
                progressRotationPrev(head, baskProgress, Maths.rad(-10), Maths.rad(20), Maths.rad(30), 5F);
                progressPositionPrev(body, baskProgress, 0, -4, 0, 5F);
                progressPositionPrev(rightArm, baskProgress, 1, 0, 0, 5F);
                progressPositionPrev(leftArm, baskProgress, -1, 0, 0, 5F);
            } else if (baskType == 3) {
                progressRotationPrev(body, baskProgress, 0, Maths.rad(20), 0, 5F);
                progressRotationPrev(tail, baskProgress, 0, Maths.rad(25), 0, 5F);
                progressRotationPrev(head, baskProgress, 0, Maths.rad(-20), Maths.rad(25), 5F);
                progressRotationPrev(rightArm, baskProgress, 0, Maths.rad(-20), 0, 5F);
                progressRotationPrev(leftArm, baskProgress, 0, Maths.rad(30), 0, 5F);
                progressRotationPrev(leftLeg, baskProgress, 0, Maths.rad(30), 0, 5F);
                progressRotationPrev(rightLeg, baskProgress, 0, Maths.rad(30), 0, 5F);
                progressPositionPrev(head, baskProgress, 0, -1, 0, 5F);
            } else if (baskType == 4) {
                progressRotationPrev(body, baskProgress, 0, Maths.rad(-20), 0, 5F);
                progressRotationPrev(tail, baskProgress, 0, Maths.rad(-25), 0, 5F);
                progressRotationPrev(head, baskProgress, 0, Maths.rad(20), Maths.rad(-25), 5F);
                progressRotationPrev(rightArm, baskProgress, 0, Maths.rad(30), 0, 5F);
                progressRotationPrev(leftArm, baskProgress, 0, Maths.rad(-20), 0, 5F);
                progressPositionPrev(head, baskProgress, 0, -1, 0, 5F);
                progressRotationPrev(leftLeg, baskProgress, 0, Maths.rad(-30), 0, 5F);
                progressRotationPrev(rightLeg, baskProgress, 0, Maths.rad(-30), 0, 5F);
            }
            this.flap(rightArm, 0.05F, 0.2F, true, 3F, -0.1F, ageInTicks, 1);
            this.flap(leftArm, 0.05F, 0.2F, true, 3F, -0.1F, ageInTicks, 1);
        }

        if (!inWater) {
            //══════ LAND: belly-bounce caterpillar walk ══════
            // Real seal on land: arches body UP → lunges forward → belly slides.
            // This is NOT a smooth walk — it's a discrete bounce cycle.
            float walkSpeed = young ? 0.25F : 0.5F;
            float walkDegree = 1F;
            float f = walkSpeed;
            float f1 = walkDegree * 0.3F;

            // Phase: arch-up (positive) vs slide (negative)
            float bouncePhase = Mth.sin(limbSwing * f);
            // Only arch UP (not down — seals don't dig into ground)
            float archUp = bouncePhase > 0 ? bouncePhase : 0;
            float slideForward = bouncePhase;

            // Body arches upward, lunges forward
            body.rotateAngleX += archUp * 0.5F * limbSwingAmount;
            body.rotationPointZ += slideForward * 3F * limbSwingAmount;
            body.rotationPointY -= slideForward * 1.5F * limbSwingAmount; // dip during slide

            // Head follows body with neck articulation
            head.rotateAngleX -= archUp * 0.3F * limbSwingAmount;
            head.rotationPointZ += Mth.sin(limbSwing * f - 0.5F) * 1.5F * limbSwingAmount;

            // Tail counter-balances
            tail.rotateAngleX -= archUp * 0.2F * limbSwingAmount;
            tail.rotationPointZ += Mth.sin(limbSwing * f - 1.5F) * 1.2F * limbSwingAmount;

            // Front flippers pull body forward during arch-up
            leftArm.rotateAngleZ += archUp * 0.8F * limbSwingAmount;
            rightArm.rotateAngleZ -= archUp * 0.8F * limbSwingAmount;
            // Slight forward row
            leftArm.rotationPointZ -= archUp * 0.5F * limbSwingAmount;
            rightArm.rotationPointZ -= archUp * 0.5F * limbSwingAmount;
        } else {
            //══════ SWIM: vertical pelvic undulation ══════
            // Replaces chainWave (WRONG: lateral fish-like) with explicit
            // vertical pitch (CORRECT: dolphin-like marine mammal).
            // Body→tail→legs form a traveling wave of pitch.

            body.rotateAngleX += headPitch * Mth.DEG_TO_RAD;

            // PRIMARY WAVE: body pitch (anterior)
            float bodyPitch = Mth.sin(limbSwing * swimSpeed) * swimDegree * limbSwingAmount;
            body.rotateAngleX += bodyPitch;

            // PHASE-DELAYED: tail follows body with ~0.4 radian lag
            float tailPitch = Mth.sin(limbSwing * swimSpeed - 0.4F) * swimDegree * 1.1F * limbSwingAmount;
            tail.rotateAngleX += tailPitch;
            // Tail also has slight lateral sway (secondary)
            tail.rotateAngleY += Mth.sin(limbSwing * swimSpeed - 0.2F) * swimDegree * 0.15F * limbSwingAmount;

            // REAR FLIPPERS: thrust generators — move together
            // Both legs pitch together for propulsion (seal kick)
            float legThrust = Mth.sin(limbSwing * swimSpeed - 0.7F) * swimDegree * 1.4F * limbSwingAmount;
            leftLeg.rotateAngleX += legThrust;
            rightLeg.rotateAngleX += legThrust;
            // Slight out-of-phase flutter for steering
            leftLeg.rotateAngleZ += Mth.sin(limbSwing * swimSpeed * 2F - 0.9F) * swimDegree * 0.3F * limbSwingAmount;
            rightLeg.rotateAngleZ -= Mth.sin(limbSwing * swimSpeed * 2F - 0.9F) * swimDegree * 0.3F * limbSwingAmount;

            // FRONT FLIPPERS: independent steering (NOT propulsion)
            // Left and right have different phases for organic feel
            this.flap(leftArm, swimSpeed * 0.5F, swimDegree * 0.8F, true, 2F, 0, limbSwing, limbSwingAmount);
            this.flap(rightArm, swimSpeed * 0.5F, swimDegree * 0.8F, false, 2.5F, 0, limbSwing, limbSwingAmount);
            // Subtle independent trim adjustments
            leftArm.rotateAngleZ += Mth.sin(ageInTicks * 0.15F) * 0.08F;
            rightArm.rotateAngleZ -= Mth.sin(ageInTicks * 0.15F + 0.8F) * 0.08F;

            // BODY BUOYANCY: subtle vertical bob
            body.rotationPointY += Mth.sin(limbSwing * swimSpeed * 0.5F) * swimDegree * 0.6F * limbSwingAmount;
        }

        //══════ TEARS EASTER EGG (preserved) ══════
        if (entity.isTearsEasterEgg() && !inWater) {
            this.swing(head, 0.1F, 0.6F, true, 3F, 0.0F, ageInTicks, 1);
            this.walk(head, 0.1F, 0.1F, true, 2F, 0.3F, ageInTicks, 1);
        }

        this.faceTarget(netHeadYaw, headPitch, 1, head);

        //══════ TURN BANKING (preserved) ══════
        float yawAmount = swimAngle / 57.295776F * 0.5F;
        body.rotateAngleZ += yawAmount;
    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.65F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.5D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            head.setScale(1, 1, 1);
        } else {
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }

    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}

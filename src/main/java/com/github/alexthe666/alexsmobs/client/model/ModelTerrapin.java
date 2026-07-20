package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelTerrapin extends AdvancedEntityModel<EntityTerrapin> {

    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox shell;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox left_hand;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox right_hand;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;

    public ModelTerrapin() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 14).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 2.0F, 8.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setPos(0.0F, -1.3F, -5.0F);
        body.addChild(head);
        head.setTextureOffset(0, 25).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

        tail = new AdvancedModelBox(this, "tail");
        tail.setPos(0.0F, 0.5F, 4.0F);
        body.addChild(tail);
        tail.setTextureOffset(28, 26).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        shell = new AdvancedModelBox(this, "shell");
        shell.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(shell);
        shell.setTextureOffset(0, 0).addBox(-4.5F, -3.0F, -5.0F, 9.0F, 3.0F, 10.0F, 0.0F, false);

        left_arm = new AdvancedModelBox(this, "left_arm");
        left_arm.setPos(4.0F, 0.0F, -3.6F);
        body.addChild(left_arm);
        left_arm.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        left_hand = new AdvancedModelBox(this, "left_hand");
        left_hand.setPos(0.0F, 2.0F, -1.0F);
        left_arm.addChild(left_hand);
        left_hand.setTextureOffset(28, 22).addBox(-1.0F, -0.01F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, false);

        right_arm = new AdvancedModelBox(this, "right_arm");
        right_arm.setPos(-4.0F, 0.0F, -3.6F);
        body.addChild(right_arm);
        right_arm.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);

        right_hand = new AdvancedModelBox(this, "right_hand");
        right_hand.setPos(0.0F, 2.0F, -1.0F);
        right_arm.addChild(right_hand);
        right_hand.setTextureOffset(28, 22).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, true);

        left_leg = new AdvancedModelBox(this, "left_leg");
        left_leg.setPos(4.0F, 0.0F, 4.4F);
        body.addChild(left_leg);
        left_leg.setTextureOffset(17, 25).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        left_foot = new AdvancedModelBox(this, "left_foot");
        left_foot.setPos(0.0F, 2.0F, 0.0F);
        left_leg.addChild(left_foot);
        left_foot.setTextureOffset(23, 14).addBox(-1.0F, -0.01F, -5.0F, 3.0F, 0.0F, 5.0F, 0.0F, false);

        right_leg = new AdvancedModelBox(this, "right_leg");
        right_leg.setPos(-4.0F, 0.0F, 4.4F);
        body.addChild(right_leg);
        right_leg.setTextureOffset(17, 25).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 3.0F, 0.0F, true);

        right_foot = new AdvancedModelBox(this, "right_foot");
        right_foot.setPos(0.0F, 2.0F, 0.0F);
        right_leg.addChild(right_foot);
        right_foot.setTextureOffset(23, 14).addBox(-2.0F, 0.0F, -4.0F, 3.0F, 0.0F, 5.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, shell, head, tail, left_arm, left_foot, left_leg, left_hand, right_arm, right_foot, right_leg, right_hand);
    }

    @Override
    public void setupAnim(EntityTerrapin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐢 TERRAPIN — DIAGONAL-GAIT SHELL-ROCKER ══════
        // IDENTITY: Diagonal walk with visible shell rocking. Front limbs
        // are PRIMARY paddles (70% thrust), rear limbs are rudders (30%).
        // Head extends to graze. Shell breathes visibly. Retreats when scared.
        // UNIQUE vs SnappingTurtle: Snapper is ambush — stationary, snap.
        // Terrapin is grazer — continuous motion, gentle shell-rock.
        // UNIQUE vs all quadrupeds: Only creature with independent shell bone
        // that rocks counter to body sway during walking.

        float partialTick = ageInTicks - entity.tickCount;
        float walkSpeed = 0.3F;
        float walkDegree = 0.7F;
        float swimSpeed = 0.5F;
        float swimDegree = 0.8F;
        float idleSpeed = 0.15F;
        float idleDegree = 0.7F;
        float spinProgress = entity.prevSpinProgress + (entity.spinProgress - entity.prevSpinProgress) * partialTick;
        float retreatProgress = Math.max(spinProgress, entity.prevRetreatProgress + (entity.retreatProgress - entity.prevRetreatProgress) * partialTick);
        float swimProgress = (entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick) * (5F - retreatProgress) * 0.2F;
        float standUnderwaterProgress = Math.max(0, (1F - Math.min(limbSwingAmount * 3F, 1F)) * swimProgress - retreatProgress);
        float spinDegree = 0.6F;

        // ── Swim limb repositioning (preserved) ─────────────────────
        progressRotationPrev(left_arm, swimProgress, Maths.rad(-20), 0, Maths.rad(-70), 5F);
        progressRotationPrev(left_hand, swimProgress, 0, 0, Maths.rad(70), 5F);
        progressRotationPrev(right_arm, swimProgress, Maths.rad(-20), 0, Maths.rad(70), 5F);
        progressRotationPrev(right_hand, swimProgress, 0, 0, Maths.rad(-70), 5F);
        progressRotationPrev(left_leg, swimProgress, Maths.rad(30), 0, Maths.rad(-70), 5F);
        progressRotationPrev(left_foot, swimProgress, 0, 0, Maths.rad(70), 5F);
        progressRotationPrev(right_leg, swimProgress, Maths.rad(30), 0, Maths.rad(70), 5F);
        progressRotationPrev(right_foot, swimProgress, 0, 0, Maths.rad(-70), 5F);
        progressRotationPrev(body, standUnderwaterProgress, Maths.rad(-30), 0, 0, 5F);
        progressRotationPrev(head, standUnderwaterProgress, Maths.rad(30), 0, 0, 5F);
        progressRotationPrev(tail, standUnderwaterProgress, Maths.rad(30), 0, 0, 5F);
        progressPositionPrev(body, standUnderwaterProgress, 0, -1F, 0, 5F);
        progressPositionPrev(tail, swimProgress, 0, -0.5F, 1, 5F);

        // ── Retreat state (preserved) ──────────────────────────────
        progressRotationPrev(left_arm, retreatProgress, 0, 0, Maths.rad(-90), 5F);
        progressRotationPrev(right_arm, retreatProgress, 0, 0, Maths.rad(90), 5F);
        progressRotationPrev(left_leg, retreatProgress, 0, 0, Maths.rad(-90), 5F);
        progressRotationPrev(right_leg, retreatProgress, 0, 0, Maths.rad(90), 5F);
        progressPositionPrev(body, retreatProgress, 0, 1, 0, 5F);
        progressPositionPrev(head, retreatProgress, 0, 1, 3, 5F);
        progressPositionPrev(tail, retreatProgress, 0, -0.1F, -4, 5F);
        progressPositionPrev(left_arm, retreatProgress, -3, -0.1F, 3, 5F);
        progressPositionPrev(right_arm, retreatProgress, 3, -0.1F, 3, 5F);
        progressPositionPrev(left_leg, retreatProgress, -3, -0.1F, -2, 5F);
        progressPositionPrev(right_leg, retreatProgress, 3, -0.1F, -2, 5F);

        // ── AAA VISIBLE BREATHING: Shell + Body expansion ──────────
        float breath = Mth.cos(ageInTicks * 0.07F);
        body.rotationPointY += breath * 0.04F;
        shell.rotationPointY += breath * 0.02F;
        body.setScale(1.0F + breath * 0.012F, 1.0F, 1.0F + breath * 0.012F);
        shell.setScale(1.0F + breath * 0.01F, 1.0F + breath * 0.01F, 1.0F + breath * 0.01F);
        head.rotationPointY += breath * 0.015F;
        tail.rotationPointY += breath * 0.01F;

        // ── AAA GRAZING IDLE: Head extends to graze on plants ──────
        float grazeCycle = Mth.sin(ageInTicks * 0.06F);
        float grazeExtension = Mth.clamp(grazeCycle, 0.0F, 1.0F);
        if (swimProgress <= 0F && retreatProgress <= 0F && limbSwingAmount < 0.05F) {
            head.rotationPointZ -= grazeExtension * 2.5F;
            head.rotateAngleX += grazeExtension * 0.3F;
        }

        // ── Tail idle sway (preserved) ──────────────────────────────
        this.swing(tail, idleSpeed, idleDegree, true, -2F, 0, ageInTicks, 1);

        // ── Head tracking (preserved) ───────────────────────────────
        if (!entity.hasRetreated() && !entity.isSpinning()) {
            this.faceTarget(netHeadYaw, headPitch, 1.3F, head);
        }

        // ── Spin state (preserved) ──────────────────────────────────
        if (entity.isSpinning()) {
            float timeSpinning = entity.spinCounter + partialTick;
            body.rotateAngleY = timeSpinning * 0.2F * spinProgress * spinDegree;
            entity.clientSpin = body.rotateAngleY;
            this.bob(body, spinDegree, 1, true, timeSpinning, 0.2F * spinProgress);
        } else {
            float rollDeg = (float) Mth.wrapDegrees(Math.toDegrees(entity.clientSpin));
            body.rotateAngleY = spinProgress * 0.2F * Maths.rad(rollDeg);
            if (swimProgress <= 0F) {
                // ══════ AAA DIAGONAL WALK GAIT ══════
                // Diagonal pair A: left_arm + right_leg (same phase, invert=false)
                // Diagonal pair B: right_arm + left_leg (opposite phase, invert=true)
                // Front arms: shorter stride (0.8x). Rear legs: longer stride (1.2x)
                this.flap(body, walkSpeed, walkDegree * 0.5F, false, 0F, 0F, limbSwing, limbSwingAmount);
                this.swing(body, walkSpeed, walkDegree * 0.5F, false, 1F, 0F, limbSwing, limbSwingAmount);
                this.flap(head, walkSpeed, walkDegree * -0.5F, false, 0F, 0F, limbSwing, limbSwingAmount);
                this.swing(head, walkSpeed, walkDegree * -0.5F, false, 1F, 0F, limbSwing, limbSwingAmount);
                this.flap(tail, walkSpeed, walkDegree * -0.5F, false, 0F, 0F, limbSwing, limbSwingAmount);
                this.swing(tail, walkSpeed, walkDegree * 0.5F, false, 2F, 0F, limbSwing, limbSwingAmount);

                // Diagonal pair A: left_arm + right_leg same phase (invert=false)
                this.walk(left_arm, walkSpeed, walkDegree * 0.8F, false, -2.5F, -0.08F, limbSwing, limbSwingAmount);
                this.walk(right_leg, walkSpeed, walkDegree * 1.2F, false, -2.5F, 0.08F, limbSwing, limbSwingAmount);
                // Diagonal pair B: right_arm + left_leg opposite phase (invert=true)
                this.walk(right_arm, walkSpeed, walkDegree * 0.8F, true, -2.5F, 0.08F, limbSwing, limbSwingAmount);
                this.walk(left_leg, walkSpeed, walkDegree * 1.2F, true, -2.5F, -0.08F, limbSwing, limbSwingAmount);

                // Head bob follows walk rhythm
                this.bob(head, walkSpeed, walkDegree * 0.6F, false, limbSwing, limbSwingAmount);

                // Foot/hand stabilization (preserved)
                this.left_hand.rotateAngleX -= (left_arm.rotateAngleX + body.rotateAngleX);
                this.left_hand.rotateAngleZ -= body.rotateAngleZ;
                this.right_hand.rotateAngleX -= (right_arm.rotateAngleX + body.rotateAngleX);
                this.right_hand.rotateAngleZ -= body.rotateAngleZ;
                this.left_foot.rotateAngleX -= (left_leg.rotateAngleX + body.rotateAngleX);
                this.left_foot.rotateAngleZ -= body.rotateAngleZ;
                this.right_foot.rotateAngleX -= (right_leg.rotateAngleX + body.rotateAngleX);
                this.right_foot.rotateAngleZ -= body.rotateAngleZ;

                // Ground-contact limb lift (preserved)
                this.left_arm.rotationPointY += 1.5F * (float) (Math.sin((double) (limbSwing * walkSpeed) - 2.5F) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));
                this.right_arm.rotationPointY += 1.5F * (float) (Math.sin(-(double) (limbSwing * walkSpeed) + 2.5F) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));
                this.left_leg.rotationPointY += 1.5F * (float) (Math.sin((double) (limbSwing * walkSpeed) - 2.5F) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));
                this.right_leg.rotationPointY += 1.5F * (float) (Math.sin(-(double) (limbSwing * walkSpeed) + 2.5F) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));

                // ══════ AAA SHELL ROCK ══════
                // Shell counter-rotates from body lateral sway during walk
                shell.rotateAngleZ += Mth.sin(limbSwing * walkSpeed + 0.5F) * walkDegree * 0.12F * limbSwingAmount;
                shell.rotateAngleX += Mth.sin(limbSwing * walkSpeed * 2.0F) * walkDegree * 0.05F * limbSwingAmount;

            } else {
                // ══════ AAA ASYMMETRIC SWIM ══════
                this.flap(body, swimSpeed, swimDegree * 0.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
                this.flap(head, swimSpeed, swimDegree * 0.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
                this.flap(tail, swimSpeed, swimDegree * -0.2F, false, 0F, 0F, limbSwing, limbSwingAmount);

                // Front arms — PRIMARY paddles (1.4x = 70% thrust)
                this.flap(left_arm, swimSpeed, swimDegree * 0.4F, true, 0F, 0.15F, limbSwing, limbSwingAmount);
                this.flap(right_arm, swimSpeed, swimDegree * 0.4F, true, 0F, 0.15F, limbSwing, limbSwingAmount);
                this.walk(left_arm, swimSpeed, swimDegree * 1.4F, false, 3F, 0.3F, limbSwing, limbSwingAmount);
                this.walk(right_arm, swimSpeed, swimDegree * 1.4F, false, 3F, 0.3F, limbSwing, limbSwingAmount);

                // Rear legs — RUDDERS (0.6x = 30% steering)
                this.flap(left_leg, swimSpeed, swimDegree * 0.15F, true, 0F, 0.05F, limbSwing, limbSwingAmount);
                this.flap(right_leg, swimSpeed, swimDegree * 0.15F, true, 0F, 0.05F, limbSwing, limbSwingAmount);
                this.walk(left_leg, swimSpeed, swimDegree * 0.6F, false, 2F, 0.15F, limbSwing, limbSwingAmount);
                this.walk(right_leg, swimSpeed, swimDegree * 0.6F, false, 2F, 0.15F, limbSwing, limbSwingAmount);

                // Rear foot counter-steer — subtle yaw rudder action
                left_foot.rotateAngleY += Mth.sin(limbSwing * swimSpeed + 1.5F) * 0.08F * limbSwingAmount;
                right_foot.rotateAngleY -= Mth.sin(limbSwing * swimSpeed + 1.5F) * 0.08F * limbSwingAmount;
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.35F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.5D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            this.head.setScale(1.0F, 1.0F, 1.0F);
        } else {
            this.head.setScale(1.0F, 1.0F, 1.0F);
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
    }

}

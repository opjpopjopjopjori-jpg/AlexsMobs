package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCockroach extends AdvancedEntityModel<EntityCockroach> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox abdomen;
    public final AdvancedModelBox left_leg_front;
    public final AdvancedModelBox right_leg_front;
    public final AdvancedModelBox left_leg_back;
    public final AdvancedModelBox right_leg_back;
    public final AdvancedModelBox left_leg_mid;
    public final AdvancedModelBox right_leg_mid;
    public final AdvancedModelBox left_wing;
    public final AdvancedModelBox right_wing;
    public final AdvancedModelBox neck;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_antenna;
    public final AdvancedModelBox right_antenna;

    public ModelCockroach() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);


        abdomen = new AdvancedModelBox(this, "abdomen");
        abdomen.setPos(0.0F, -1.6F, -1.0F);
        root.addChild(abdomen);
        abdomen.setTextureOffset(0, 12).addBox(-2.0F, -0.9F, -2.0F, 4.0F, 2.0F, 9.0F, 0.0F, false);

        left_leg_front = new AdvancedModelBox(this, "left_leg_front");
        left_leg_front.setPos(1.5F, 0.6F, -2.0F);
        abdomen.addChild(left_leg_front);
        setRotationAngle(left_leg_front, 0.0F, 0.0F, 0.1309F);
        left_leg_front.setTextureOffset(0, 24).addBox(0.0F, 0.0F, 0.0F, 7.0F, 0.0F, 3.0F, 0.0F, false);

        right_leg_front = new AdvancedModelBox(this, "right_leg_front");
        right_leg_front.setPos(-1.5F, 0.6F, -2.0F);
        abdomen.addChild(right_leg_front);
        setRotationAngle(right_leg_front, 0.0F, 0.0F, -0.1309F);
        right_leg_front.setTextureOffset(0, 24).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 0.0F, 3.0F, 0.0F, true);

        left_leg_back = new AdvancedModelBox(this, "left_leg_back");
        left_leg_back.setPos(1.5F, 0.6F, 3.0F);
        abdomen.addChild(left_leg_back);
        setRotationAngle(left_leg_back, -0.0436F, -0.5236F, 0.1745F);
        left_leg_back.setTextureOffset(18, 12).addBox(0.0F, 0.0F, 0.0F, 7.0F, 0.0F, 5.0F, 0.0F, false);

        right_leg_back = new AdvancedModelBox(this, "right_leg_back");
        right_leg_back.setPos(-1.5F, 0.6F, 3.0F);
        abdomen.addChild(right_leg_back);
        setRotationAngle(right_leg_back, -0.0436F, 0.5236F, -0.1745F);
        right_leg_back.setTextureOffset(18, 12).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 0.0F, 5.0F, 0.0F, true);

        left_leg_mid = new AdvancedModelBox(this, "left_leg_mid");
        left_leg_mid.setPos(1.5F, 0.6F, 0.0F);
        abdomen.addChild(left_leg_mid);
        setRotationAngle(left_leg_mid, -0.0436F, -0.2182F, 0.1309F);
        left_leg_mid.setTextureOffset(23, 20).addBox(0.0F, 0.0F, 0.0F, 7.0F, 0.0F, 4.0F, 0.0F, false);

        right_leg_mid = new AdvancedModelBox(this, "right_leg_mid");
        right_leg_mid.setPos(-1.5F, 0.6F, 0.0F);
        abdomen.addChild(right_leg_mid);
        setRotationAngle(right_leg_mid, -0.0436F, 0.2182F, -0.1309F);
        right_leg_mid.setTextureOffset(23, 20).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 0.0F, 4.0F, 0.0F, true);

        left_wing = new AdvancedModelBox(this, "left_wing");
        left_wing.setPos(0.0F, -1.4F, -2.0F);
        abdomen.addChild(left_wing);
        left_wing.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 10.0F, 0.0F, false);

        right_wing = new AdvancedModelBox(this, "right_wing");
        right_wing.setPos(0.0F, -1.4F, -2.0F);
        abdomen.addChild(right_wing);
        right_wing.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 1.0F, 10.0F, 0.0F, true);

        neck = new AdvancedModelBox(this, "neck");
        neck.setPos(0.0F, 0.0F, -2.0F);
        abdomen.addChild(neck);
        neck.setTextureOffset(21, 25).addBox(-2.5F, -1.6F, -2.0F, 5.0F, 3.0F, 2.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setPos(0.0F, -0.1F, -2.0F);
        neck.addChild(head);
        head.setTextureOffset(0, 28).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        left_antenna = new AdvancedModelBox(this, "left_antenna");
        left_antenna.setPos(0.1F, -1.0F, -2.0F);
        head.addChild(left_antenna);
        setRotationAngle(left_antenna, -0.2182F, -0.2618F, 0.1309F);
        left_antenna.setTextureOffset(17, 0).addBox(0.0F, 0.0F, -8.0F, 5.0F, 0.0F, 8.0F, 0.0F, false);

        right_antenna = new AdvancedModelBox(this, "right_antenna");
        right_antenna.setPos(-0.1F, -1.0F, -2.0F);
        head.addChild(right_antenna);
        setRotationAngle(right_antenna, -0.2182F, 0.2618F, -0.1309F);
        right_antenna.setTextureOffset(17, 0).addBox(-5.0F, 0.0F, -8.0F, 5.0F, 0.0F, 8.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, abdomen, neck, head, left_antenna, right_antenna,
                left_leg_front, right_leg_front, left_leg_mid, right_leg_mid,
                left_leg_back, right_leg_back, left_wing, right_wing);
    }

    @Override
    public void setupAnim(EntityCockroach entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🪳 COCKROACH — DASH-ESCAPE TRIPEDAL SCUTTLER ══════
        // IDENTITY: Alternating tripedal gait at cruise speed, switching to
        // quadrupedal gallop at dash speed. Antennae CONSTANTLY sweeping with
        // large-amplitude independent probing. Wings deploy in two stages:
        // tegmina lift → hindwing beat. Abdomen drags behind with tip-sway.
        // UNIQUE vs LeafcutterAnt (pheromone-line marcher, carries leaf overhead).
        // UNIQUE vs TarantulaHawk (search-pause aerial hunter, drag mechanics).

        float partialTick = Minecraft.getInstance().getFrameTime();

        // ── Core State ──────────────────────────────────────────────
        float danceProgress = entity.prevDanceProgress + (entity.danceProgress - entity.prevDanceProgress) * partialTick;
        boolean isFlying = entity.randomWingFlapTick > 0;

        // ── AAA DASH GAIT: Speed-dependent locomotion ────────────────
        // Cockroaches switch from tripedal (slow) to gallop (fast escape)
        float dashThreshold = 0.55F;
        float dashFactor = Mth.clamp((limbSwingAmount - dashThreshold) / (1.0F - dashThreshold), 0.0F, 1.0F);
        float cruiseAmount = limbSwingAmount * (1.0F - dashFactor * 0.5F);
        float dashAmount = limbSwingAmount * dashFactor;

        // ── Animation Parameters ────────────────────────────────────
        float idleSpeed = 0.22F;
        float idleDegree = 0.35F;
        float walkSpeed = 1.25F;
        float walkDegree = 0.45F;
        float dashSpeed = walkSpeed * 2.6F;
        float dashDegree = walkDegree * 1.8F;

        // ── AAA BREATHING: Insect Abdominal Pulse ───────────────────
        float breathCycle = Mth.cos(ageInTicks * 0.15F);
        abdomen.setScale(1.0F + breathCycle * 0.02F, 1.0F, 1.0F + breathCycle * 0.015F);
        abdomen.rotationPointY += breathCycle * 0.06F;

        // ── AAA TRIPEDAL GAIT: Leg-stride-differentiated ─────────────
        // Leg amplitude increases front→back. Front=short, rear=long power stroke.
        float fLegDeg = walkDegree * 0.65F;
        float mLegDeg = walkDegree * 0.90F;
        float bLegDeg = walkDegree * 1.25F;
        // Cruise walk — alternating tripods
        this.swing(left_leg_front, walkSpeed, fLegDeg, false, -1.0F, 0F, limbSwing, cruiseAmount);
        this.swing(right_leg_front, walkSpeed, fLegDeg, true, 1.0F, 0F, limbSwing, cruiseAmount);
        this.swing(left_leg_mid, walkSpeed, mLegDeg, true, 0F, 0F, limbSwing, cruiseAmount);
        this.swing(right_leg_mid, walkSpeed, mLegDeg, false, 0F, 0F, limbSwing, cruiseAmount);
        this.swing(left_leg_back, walkSpeed, bLegDeg, false, 1.0F, 0F, limbSwing, cruiseAmount);
        this.swing(right_leg_back, walkSpeed, bLegDeg, true, -1.0F, 0F, limbSwing, cruiseAmount);
        // Dash gallop — all legs drive at high amplitude; rear=power, body lowers
        float dFrontDeg = dashDegree * 0.75F;
        float dMidDeg = dashDegree * 0.88F;
        float dBackDeg = dashDegree * 1.12F;
        this.swing(left_leg_front, dashSpeed, dFrontDeg, false, -1.0F, 0F, limbSwing, dashAmount);
        this.swing(right_leg_front, dashSpeed, dFrontDeg, true, 1.0F, 0F, limbSwing, dashAmount);
        this.swing(left_leg_mid, dashSpeed, dMidDeg, true, 0F, 0F, limbSwing, dashAmount);
        this.swing(right_leg_mid, dashSpeed, dMidDeg, false, 0F, 0F, limbSwing, dashAmount);
        this.swing(left_leg_back, dashSpeed, dBackDeg, false, 1.0F, 0F, limbSwing, dashAmount);
        this.swing(right_leg_back, dashSpeed, dBackDeg, true, -1.0F, 0F, limbSwing, dashAmount);

        // Body bob — deeper during dash, body hugs ground
        this.bob(abdomen, walkSpeed, walkDegree * 2.0F, true, limbSwing, cruiseAmount);
        this.bob(abdomen, dashSpeed, dashDegree * 2.5F, true, limbSwing, dashAmount);
        abdomen.rotationPointY -= dashFactor * walkDegree * 3.5F * limbSwingAmount;
        // Lateral body sway — weight transfer between tripods
        abdomen.rotationPointX += Mth.sin(limbSwing * walkSpeed) * walkDegree * 0.3F * cruiseAmount;
        abdomen.rotationPointX += Mth.sin(limbSwing * dashSpeed) * dashDegree * 0.4F * dashAmount;

        // ── AAA ABDOMEN DRAG: Follow-through with tip sway ───────────
        this.swing(abdomen, walkSpeed * 0.5F, walkDegree * 0.18F, false, 0F, 0F, limbSwing, cruiseAmount);
        this.swing(abdomen, dashSpeed * 0.5F, dashDegree * 0.22F, false, 0F, 0F, limbSwing, dashAmount);
        abdomen.rotateAngleX += Mth.sin(limbSwing * walkSpeed + 1.5F) * walkDegree * 0.08F * limbSwingAmount;
        // Abdomen yaw twist — rear end sways side-to-side for steering during walk
        abdomen.rotateAngleY += Mth.sin(limbSwing * walkSpeed * 0.65F) * 0.04F * cruiseAmount;
        // Dash steering — sharper abdominal yaw for aggressive escape turns
        abdomen.rotateAngleY += Mth.sin(limbSwing * dashSpeed * 0.55F) * 0.07F * dashAmount;

        // ── AAA ANTENNA ANIMATION ───────────────────────────────────
        // Left and right have DIFFERENT frequencies for independent probing
        float antSwingSp = idleSpeed;
        float antSwingDg = idleDegree;
        this.swing(left_antenna, antSwingSp, antSwingDg, true, 1.0F, -0.12F, ageInTicks, 1.0F);
        this.swing(right_antenna, antSwingSp * 0.85F, antSwingDg * 0.9F, false, 1.3F, -0.10F, ageInTicks, 1.0F);
        this.walk(left_antenna, antSwingSp * 0.8F, antSwingDg * 0.25F, false, -1.0F, -0.05F, ageInTicks, 1.0F);
        this.walk(right_antenna, antSwingSp * 0.7F, antSwingDg * 0.22F, true, -0.7F, -0.05F, ageInTicks, 1.0F);
        this.flap(left_antenna, antSwingSp * 0.6F, antSwingDg * 0.35F, false, 2.5F, 0.03F, ageInTicks, 1.0F);
        this.flap(right_antenna, antSwingSp * 0.7F, antSwingDg * 0.28F, true, 2.8F, 0.03F, ageInTicks, 1.0F);
        // Antennae more active during dash (sensing escape route)
        float dashAnt = 1.0F + dashFactor * 0.6F;
        this.swing(left_antenna, antSwingSp * 1.4F, antSwingDg * 0.5F * dashAnt, true, 1.0F, -0.04F, ageInTicks, dashAmount);
        this.swing(right_antenna, antSwingSp * 1.2F, antSwingDg * 0.45F * dashAnt, false, 1.5F, -0.04F, ageInTicks, dashAmount);

        // ── AAA HEAD & NECK ─────────────────────────────────────────
        this.walk(head, idleSpeed * 0.5F, idleDegree * 0.4F, false, 0F, 0.08F, ageInTicks, 1.0F);
        this.bob(neck, idleSpeed * 0.7F, idleDegree * 0.3F, false, ageInTicks, 1.0F);
        this.swing(neck, walkSpeed, walkDegree * 0.06F, false, 0F, 0F, limbSwing, cruiseAmount);
        this.swing(neck, dashSpeed, dashDegree * 0.05F, false, 0F, 0F, limbSwing, dashAmount);

        // ── AAA WING ANIMATION ──────────────────────────────────────
        float wingTwitch = Mth.sin(ageInTicks * 0.35F + 2.0F) > 0.85F ? Mth.sin(ageInTicks * 0.35F + 2.0F) * 0.5F : 0F;
        if (!isFlying && danceProgress < 0.1F) {
            left_wing.rotateAngleX += wingTwitch * 0.04F;
            right_wing.rotateAngleX += wingTwitch * 0.04F;
            left_wing.rotateAngleX += breathCycle * 0.02F;
            right_wing.rotateAngleX += breathCycle * 0.02F;
        }
        // Flight: two-stage deploy — tegmina lift then hindwing beat with chaos
        if (isFlying) {
            float flySpeed = 0.5F;
            float flyDegree = 0.5F;
            float wingChaos = Mth.sin(ageInTicks * 0.9F) * Mth.cos(ageInTicks * 1.4F) * 0.08F;
            left_wing.rotateAngleX += 0.45F;
            right_wing.rotateAngleX += 0.45F;
            this.swing(left_wing, flySpeed * 3.3F, flyDegree * 0.6F + wingChaos, true, 0F, -0.2F, ageInTicks, 1.0F);
            this.swing(right_wing, flySpeed * 3.3F, flyDegree * 0.6F - wingChaos, false, 0F, -0.2F, ageInTicks, 1.0F);
            left_wing.rotateAngleZ += Mth.sin(ageInTicks * flySpeed * 3.3F + 0.3F) * 0.04F;
            right_wing.rotateAngleZ -= Mth.sin(ageInTicks * flySpeed * 3.3F + 0.3F) * 0.04F;
            abdomen.rotateAngleX += 0.35F;
            left_leg_front.rotateAngleZ -= 0.2F;
            right_leg_front.rotateAngleZ += 0.2F;
            left_leg_mid.rotateAngleZ -= 0.15F;
            right_leg_mid.rotateAngleZ += 0.15F;
            left_leg_back.rotateAngleZ -= 0.2F;
            right_leg_back.rotateAngleZ += 0.2F;
        }

        // ── AAA DANCE ANIMATION (La Cucaracha) ──────────────────────
        if (danceProgress > 0.01F) {
            progressRotationPrev(abdomen, danceProgress, Maths.rad(-70), 0, 0, 5F);
            progressRotationPrev(left_leg_front, danceProgress, 0, Maths.rad(-10), 0, 5F);
            progressRotationPrev(right_leg_front, danceProgress, 0, Maths.rad(10), 0, 5F);
            progressRotationPrev(left_leg_mid, danceProgress, 0, Maths.rad(-10), 0, 5F);
            progressRotationPrev(right_leg_mid, danceProgress, 0, Maths.rad(10), 0, 5F);
            progressPositionPrev(abdomen, danceProgress, 0, -15, 2, 5F);

            this.walk(left_antenna, 0.5F, 0.5F, false, -1, -0.05F, ageInTicks, 1);
            this.walk(right_antenna, 0.5F, 0.5F, false, -1, -0.05F, ageInTicks, 1);

            if (entity.hasMaracas()) {
                this.swing(abdomen, 0.5F, 0.15F, false, 0, 0F, ageInTicks, 1);
                this.flap(abdomen, 0.5F, 0.15F, false, 1, 0F, ageInTicks, 1);
                this.bob(abdomen, 0.25F, 10F, true, ageInTicks, 1);
                this.swing(right_leg_front, 0.5F, 0.5F, false, 0, -0.05F, ageInTicks, 1);
                this.swing(left_leg_front, 0.5F, 0.5F, false, 0, -0.05F, ageInTicks, 1);
                this.swing(right_leg_mid, 0.5F, 0.5F, false, 2, -0.05F, ageInTicks, 1);
                this.swing(left_leg_mid, 0.5F, 0.5F, false, 2, -0.05F, ageInTicks, 1);
            } else {
                float spinDegree = Mth.wrapDegrees(ageInTicks * 15F);
                abdomen.rotateAngleY = (float) (Math.toRadians(spinDegree) * danceProgress * 0.2F);
                this.bob(abdomen, 0.25F, 10F, true, ageInTicks, 1);
            }
        }

        // ── AAA HEAD TRACKING ───────────────────────────────────────
        this.faceTarget(netHeadYaw, headPitch, 1.0F, head);

        // ── Headless State ──────────────────────────────────────────
        if (entity.isHeadless()) {
            head.showModel = false;
            left_antenna.showModel = false;
            right_antenna.showModel = false;
        } else {
            head.showModel = true;
            left_antenna.showModel = true;
            right_antenna.showModel = true;
        }
        // Baby cockroaches have no wings
        if (entity.isBaby()) {
            left_wing.showModel = false;
            right_wing.showModel = false;
        } else {
            left_wing.showModel = true;
            right_wing.showModel = true;
        }
    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            this.head.setScale(1.5F, 1.5F, 1.5F);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.65F, 0.65F, 0.65F);
            matrixStackIn.translate(0.0D, 0.815D, 0.125D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        } else {
            this.head.setScale(1F, 1F, 1F);
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

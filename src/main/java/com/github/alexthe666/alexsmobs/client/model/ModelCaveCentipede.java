package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCentipedeBody;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ModelCaveCentipede<T extends LivingEntity> extends AdvancedEntityModel<T> {
    private final int type;
    private final AdvancedModelBox root;
    private AdvancedModelBox body;
    private AdvancedModelBox leftLegBodyF, leftLeg2BodyF, rightLegBodyF, rightLegBodyF2;
    private AdvancedModelBox leftLegBodyB, leftLeg2BodyB, rightLegBodyB, rightLegBodyB2;
    private AdvancedModelBox tail;
    private AdvancedModelBox leftLegTailF, leftLeg2TailF, rightLegTailF, rightLeg2TailF;
    private AdvancedModelBox leftLegTailB, leftLegTailB2, rightLegTailB, rightLegTailB2;
    private AdvancedModelBox leftTail, leftTailEnd, rightTail, rightTailEnd;
    private AdvancedModelBox head, head2, fangs;
    private AdvancedModelBox antenna_left, antenna_left_r1, antenna_right, antenna_right_r1;

    public ModelCaveCentipede(int type) {
        texWidth = 128; texHeight = 128; this.type = type;
        root = new AdvancedModelBox(this, "root"); root.setRotationPoint(0.0F, 24.0F, 21.0F);
        switch (type) {
            case 0:
                head = new AdvancedModelBox(this, "head"); head.setRotationPoint(0.0F, -7.875F, -20.625F); root.addChild(head);
                head.setTextureOffset(0, 62).addBox(-7.0F, -3.125F, -5.375F, 14.0F, 7.0F, 13.0F, 0.0F, false);
                head2 = new AdvancedModelBox(this, "head2"); head2.setRotationPoint(0.0F, -2.125F, -6.375F); head.addChild(head2);
                head2.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
                antenna_left = new AdvancedModelBox(this, "al"); antenna_left.setRotationPoint(1.2F, -2.125F, -5.775F); head.addChild(antenna_left);
                setRotationAngle(antenna_left, -0.2618F, 0.48F, -0.2618F);
                antenna_left_r1 = new AdvancedModelBox(this, "alr"); antenna_left_r1.setRotationPoint(0.5F, 0.0F, 0.0F); antenna_left.addChild(antenna_left_r1);
                setRotationAngle(antenna_left_r1, 0.1309F, 0.0F, 0.0873F);
                antenna_left_r1.setTextureOffset(55, 17).addBox(-1.0F, 0.0F, -1.0F, 23.0F, 0.0F, 10.0F, 0.0F, false);
                antenna_right = new AdvancedModelBox(this, "ar"); antenna_right.setRotationPoint(-1.2F, -2.125F, -5.775F); head.addChild(antenna_right);
                setRotationAngle(antenna_right, -0.2618F, -0.48F, 0.2618F);
                antenna_right_r1 = new AdvancedModelBox(this, "arr"); antenna_right_r1.setRotationPoint(-0.5F, 0.0F, 0.0F); antenna_right.addChild(antenna_right_r1);
                setRotationAngle(antenna_right_r1, 0.1309F, 0.0F, -0.0873F);
                antenna_right_r1.setTextureOffset(55, 17).addBox(-22.0F, 0.0F, -1.0F, 23.0F, 0.0F, 10.0F, 0.0F, true);
                fangs = new AdvancedModelBox(this, "fangs"); fangs.setRotationPoint(0.0F, 1.875F, -6.375F); head.addChild(fangs);
                fangs.setTextureOffset(62, 28).addBox(-7.0F, 0.0F, -5.0F, 14.0F, 0.0F, 6.0F, 0.0F, false);
                break;
            case 1:
                body = new AdvancedModelBox(this, "body"); body.setRotationPoint(0.0F, -7.6F, -21.0F); root.addChild(body);
                body.setTextureOffset(0, 0).addBox(-8.0F, -5.4F, -8.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
                leftLegBodyF = new AdvancedModelBox(this, "lbf"); leftLegBodyF.setRotationPoint(7.6F, 3.6F, 5.0F); body.addChild(leftLegBodyF);
                setRotationAngle(leftLegBodyF, 0.0F, 0.0F, -0.5672F);
                leftLegBodyF.setTextureOffset(42, 62).addBox(0.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, false);
                leftLeg2BodyF = new AdvancedModelBox(this, "lb2f"); leftLeg2BodyF.setRotationPoint(9.1F, 0.5F, 0.1F); leftLegBodyF.addChild(leftLeg2BodyF);
                setRotationAngle(leftLeg2BodyF, 0.0F, 0.0F, 1.4835F);
                leftLeg2BodyF.setTextureOffset(0, 53).addBox(-5.0F, -4.0F, -0.1F, 15.0F, 6.0F, 0.0F, 0.0F, false);
                rightLegBodyF = new AdvancedModelBox(this, "rbf"); rightLegBodyF.setRotationPoint(-7.6F, 3.6F, 5.0F); body.addChild(rightLegBodyF);
                setRotationAngle(rightLegBodyF, 0.0F, 0.0F, 0.5672F);
                rightLegBodyF.setTextureOffset(42, 62).addBox(-10.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, true);
                rightLegBodyF2 = new AdvancedModelBox(this, "rb2f"); rightLegBodyF2.setRotationPoint(-9.1F, 0.5F, 0.1F); rightLegBodyF.addChild(rightLegBodyF2);
                setRotationAngle(rightLegBodyF2, 0.0F, 0.0F, -1.4835F);
                rightLegBodyF2.setTextureOffset(0, 53).addBox(-10.0F, -4.0F, -0.1F, 15.0F, 6.0F, 0.0F, 0.0F, true);
                leftLegBodyB = new AdvancedModelBox(this, "lbb"); leftLegBodyB.setRotationPoint(7.6F, 3.6F, -5.0F); body.addChild(leftLegBodyB);
                setRotationAngle(leftLegBodyB, 0.0F, 0.0F, -0.5672F);
                leftLegBodyB.setTextureOffset(42, 62).addBox(0.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, false);
                leftLeg2BodyB = new AdvancedModelBox(this, "lb2b"); leftLeg2BodyB.setRotationPoint(9.1F, 0.5F, 0.1F); leftLegBodyB.addChild(leftLeg2BodyB);
                setRotationAngle(leftLeg2BodyB, 0.0F, 0.0F, 1.4835F);
                leftLeg2BodyB.setTextureOffset(0, 53).addBox(-5.0F, -4.0F, -0.1F, 15.0F, 6.0F, 0.0F, 0.0F, false);
                rightLegBodyB = new AdvancedModelBox(this, "rbb"); rightLegBodyB.setRotationPoint(-7.6F, 3.6F, -5.0F); body.addChild(rightLegBodyB);
                setRotationAngle(rightLegBodyB, 0.0F, 0.0F, 0.5672F);
                rightLegBodyB.setTextureOffset(42, 62).addBox(-10.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, true);
                rightLegBodyB2 = new AdvancedModelBox(this, "rb2b"); rightLegBodyB2.setRotationPoint(-9.1F, 0.5F, 0.1F); rightLegBodyB.addChild(rightLegBodyB2);
                setRotationAngle(rightLegBodyB2, 0.0F, 0.0F, -1.4835F);
                rightLegBodyB2.setTextureOffset(0, 53).addBox(-10.0F, -4.0F, -0.1F, 15.0F, 6.0F, 0.0F, 0.0F, true);
                break;
            case 2:
                tail = new AdvancedModelBox(this, "tail"); tail.setRotationPoint(0.0F, -7.6F, -21.0F); root.addChild(tail);
                tail.setTextureOffset(0, 27).addBox(-7.0F, -4.2F, -8.0F, 14.0F, 9.0F, 16.0F, 0.0F, false);
                leftLegTailF = new AdvancedModelBox(this, "ltf"); leftLegTailF.setRotationPoint(6.6F, 3.6F, -5.0F); tail.addChild(leftLegTailF);
                setRotationAngle(leftLegTailF, 0.2269F, -0.1833F, -0.5585F);
                leftLegTailF.setTextureOffset(42, 62).addBox(0.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, false);
                leftLeg2TailF = new AdvancedModelBox(this, "lt2f"); leftLeg2TailF.setRotationPoint(9.1F, 0.5F, 0.1F); leftLegTailF.addChild(leftLeg2TailF);
                setRotationAngle(leftLeg2TailF, 0.0F, 0.0F, 1.4835F);
                leftLeg2TailF.setTextureOffset(0, 53).addBox(-5.0F, -4.0F, 0.0F, 15.0F, 6.0F, 0.0F, 0.0F, false);
                rightLegTailF = new AdvancedModelBox(this, "rtf"); rightLegTailF.setRotationPoint(-6.6F, 3.6F, -5.0F); tail.addChild(rightLegTailF);
                setRotationAngle(rightLegTailF, 0.2269F, 0.1833F, 0.5585F);
                rightLegTailF.setTextureOffset(42, 62).addBox(-10.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, true);
                rightLeg2TailF = new AdvancedModelBox(this, "rt2f"); rightLeg2TailF.setRotationPoint(-9.1F, 0.5F, 0.1F); rightLegTailF.addChild(rightLeg2TailF);
                setRotationAngle(rightLeg2TailF, 0.0F, 0.0F, -1.4835F);
                rightLeg2TailF.setTextureOffset(0, 53).addBox(-10.0F, -4.0F, 0.0F, 15.0F, 6.0F, 0.0F, 0.0F, true);
                leftLegTailB = new AdvancedModelBox(this, "ltb"); leftLegTailB.setRotationPoint(6.6F, 3.6F, 4.0F); tail.addChild(leftLegTailB);
                setRotationAngle(leftLegTailB, 0.4977F, -0.6749F, -0.7314F);
                leftLegTailB.setTextureOffset(42, 62).addBox(0.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, false);
                leftLegTailB2 = new AdvancedModelBox(this, "ltb2"); leftLegTailB2.setRotationPoint(9.1F, 0.5F, 0.1F); leftLegTailB.addChild(leftLegTailB2);
                setRotationAngle(leftLegTailB2, 0.0F, 0.0F, 1.4835F);
                leftLegTailB2.setTextureOffset(0, 53).addBox(-5.0F, -4.0F, 0.0F, 15.0F, 6.0F, 0.0F, 0.0F, false);
                rightLegTailB = new AdvancedModelBox(this, "rtb"); rightLegTailB.setRotationPoint(-6.6F, 3.6F, 4.0F); tail.addChild(rightLegTailB);
                setRotationAngle(rightLegTailB, 0.4977F, 0.6749F, 0.7314F);
                rightLegTailB.setTextureOffset(42, 62).addBox(-10.0F, -2.0F, -1.0F, 10.0F, 3.0F, 2.0F, 0.0F, true);
                rightLegTailB2 = new AdvancedModelBox(this, "rtb2"); rightLegTailB2.setRotationPoint(-9.1F, 0.5F, 0.1F); rightLegTailB.addChild(rightLegTailB2);
                setRotationAngle(rightLegTailB2, 0.0F, 0.0F, -1.4835F);
                rightLegTailB2.setTextureOffset(0, 53).addBox(-10.0F, -4.0F, 0.0F, 15.0F, 6.0F, 0.0F, 0.0F, true);
                leftTail = new AdvancedModelBox(this, "lt"); leftTail.setRotationPoint(2.5F, -0.1F, 8.0F); tail.addChild(leftTail);
                setRotationAngle(leftTail, 0.3054F, 0.3927F, 0.0F);
                leftTail.setTextureOffset(62, 35).addBox(-0.5F, -1.1F, -1.0F, 2.0F, 3.0F, 12.0F, 0.0F, false);
                leftTailEnd = new AdvancedModelBox(this, "lte"); leftTailEnd.setRotationPoint(0.0F, 0.0F, 11.0F); leftTail.addChild(leftTailEnd);
                setRotationAngle(leftTailEnd, -0.5672F, 0.0F, 0.0F);
                leftTailEnd.setTextureOffset(38, 30).addBox(0.5F, -1.1F, -1.0F, 0.0F, 8.0F, 23.0F, 0.0F, false);
                rightTail = new AdvancedModelBox(this, "rt"); rightTail.setRotationPoint(-2.5F, -0.1F, 8.0F); tail.addChild(rightTail);
                setRotationAngle(rightTail, 0.3054F, -0.3927F, 0.0F);
                rightTail.setTextureOffset(62, 35).addBox(-1.5F, -1.1F, -1.0F, 2.0F, 3.0F, 12.0F, 0.0F, true);
                rightTailEnd = new AdvancedModelBox(this, "rte"); rightTailEnd.setRotationPoint(0.0F, 0.0F, 11.0F); rightTail.addChild(rightTailEnd);
                setRotationAngle(rightTailEnd, -0.5672F, 0.0F, 0.0F);
                rightTailEnd.setTextureOffset(38, 30).addBox(-0.5F, -1.1F, -1.0F, 0.0F, 8.0F, 23.0F, 0.0F, true);
                break;
        }
        this.updateDefaultPose();
    }

    @Override public Iterable<BasicModelPart> parts() { return ImmutableList.of(root); }

    @Override public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐛 CAVE CENTIPEDE — METACHRONAL LEG-WAVE PREDATOR ══════
        // IDENTITY: Legs ripple in true metachronal wave (back→front propagation).
        // Body undulates in horizontal S-curve through tunnels. Antennae probe
        // independently like two separate creatures searching darkness. Fangs
        // articulate for venom injection. Tail pincers grasp.
        // BIOMECHANICS: Each leg pair starts its swing AFTER the pair behind it.
        // This creates a traveling wave from tail→head. Unlike the old code where
        // all legs shared the same frequency, each bodyIndex creates a UNIQUE phase.
        // The phase offset is now proportional to (bodyIndex / totalSegments).
        float wkSp = 1.5F, wkDg = 0.85F;
        if (entity.deathTime > 0) { limbSwing = ageInTicks; limbSwingAmount = 1; }
        float breath = Mth.cos(ageInTicks * 0.10F);

        if (type == 0) { // HEAD
            //══════ ANTENNAE: truly independent probing ══════
            // Left antenna: faster, wider sweep — exploring ahead aggressively
            this.swing(antenna_left, 0.30F, 0.40F, true, 1F, -0.1F, ageInTicks, 1);
            this.flap(antenna_left, 0.22F, 0.15F, false, 3F, 0.05F, ageInTicks, 1);
            this.walk(antenna_left_r1, 0.35F, 0.08F, false, 5F, 0.03F, ageInTicks, 1);
            // Right antenna: slower, tighter probe — different rhythm entirely
            this.swing(antenna_right, 0.22F, 0.30F, false, 0.5F, -0.08F, ageInTicks, 1);
            this.flap(antenna_right, 0.28F, 0.10F, true, 2F, 0.04F, ageInTicks, 1);
            this.walk(antenna_right_r1, 0.25F, 0.06F, true, 4F, 0.02F, ageInTicks, 1);
            // Antennae also drift in different horizontal directions
            antenna_left.rotateAngleY += Mth.sin(ageInTicks * 0.18F) * 0.12F;
            antenna_right.rotateAngleY -= Mth.sin(ageInTicks * 0.22F + 0.8F) * 0.10F;

            //══════ FANGS: venom-injection articulation ══════
            this.fangs.rotationPointZ = -6.2F;
            fangs.rotateAngleX += breath * 0.05F;
            // Subtle fang micro-flex (preparing to bite)
            fangs.rotateAngleZ += Mth.sin(ageInTicks * 0.35F + 2F) * 0.02F;

            //══════ HEAD: S-curve steering + dark-environment scanning ══════
            this.swing(head, wkSp, wkDg * 0.08F, false, 0, 0, limbSwing, limbSwingAmount);
            // Head scans left-right slowly when not moving (searching in darkness)
            head.rotateAngleY += Mth.sin(ageInTicks * 0.12F) * 0.08F * (1 - limbSwingAmount * 0.7F);
            head.rotateAngleX += Mth.sin(ageInTicks * 0.08F + 1.2F) * 0.03F * (1 - limbSwingAmount * 0.7F);
            if (entity.deathTime > 0) { head.rotateAngleX -= 0.3F; antenna_left.rotateAngleX -= 0.4F; antenna_right.rotateAngleX -= 0.4F; }
            head.setScale(1.0F + breath * 0.02F, 1.0F, 1.0F + breath * 0.01F);
            head.rotateAngleY += netHeadYaw * 0.5F * Mth.DEG_TO_RAD;
            head.rotateAngleX += headPitch * 0.3F * Mth.DEG_TO_RAD;
        } else if (type == 1) { // BODY
            if (entity instanceof EntityCentipedeBody e) {
                // METACHRONAL WAVE: each segment has a phase based on its body index.
                // The wave travels from BACK to FRONT — higher-index segments lead,
                // lower-index segments follow. This creates the wave illusion.
                float off = ((e.getBodyIndex() + 1) * (float) Math.PI * 0.5F);
                double wOff = off * Math.PI * 0.5F;
                // Amplitude gradient: rear segments have slightly stronger leg swing
                float ampGrad = 0.7F + e.getBodyIndex() * 0.05F;

                this.swing(leftLegBodyF, wkSp, wkDg * ampGrad, true, off, 0, limbSwing, limbSwingAmount);
                this.flap(leftLeg2BodyF, wkSp, wkDg * 0.5F * ampGrad, true, off, 0.1F, limbSwing, limbSwingAmount);
                this.swing(leftLegBodyB, wkSp, wkDg * ampGrad, true, off + 0.5F, 0, limbSwing, limbSwingAmount);
                this.flap(leftLeg2BodyB, wkSp, wkDg * 0.5F * ampGrad, true, off + 0.5F, 0.1F, limbSwing, limbSwingAmount);
                // Right side slightly out of sync with left — organic asymmetry
                this.swing(rightLegBodyF, wkSp, wkDg * ampGrad * 0.95F, false, off + 0.15F, 0, limbSwing, limbSwingAmount);
                this.flap(rightLegBodyF2, wkSp, wkDg * 0.5F * ampGrad, false, off + 0.15F, 0.1F, limbSwing, limbSwingAmount);
                this.swing(rightLegBodyB, wkSp, wkDg * ampGrad * 0.95F, false, off + 0.65F, 0, limbSwing, limbSwingAmount);
                this.flap(rightLegBodyB2, wkSp, wkDg * 0.5F * ampGrad, false, off + 0.65F, 0.1F, limbSwing, limbSwingAmount);
                // Vertical bob
                this.body.rotationPointY += (float) (Math.sin(limbSwing * wkSp - wOff) * limbSwingAmount * wkDg - limbSwingAmount * wkDg);
                // Horizontal S-curve undulation
                this.body.rotateAngleY += Mth.sin(limbSwing * wkSp * 0.7F - wOff) * wkDg * 0.25F * limbSwingAmount;
                this.body.rotateAngleY += Mth.sin(ageInTicks * 0.08F - off) * 0.015F * (1 - limbSwingAmount * 0.7F);
                this.body.rotationPointY += Mth.sin(ageInTicks * 0.1 - off) * 0.01;
                body.setScale(1.0F + Mth.cos(ageInTicks * 0.10F + off * 0.5F) * 0.015F, 1.0F, 1.0F + Mth.cos(ageInTicks * 0.10F + off * 0.5F) * 0.01F);
            }
        } else { // TAIL
            if (entity instanceof EntityCentipedeBody e) {
                float off = ((e.getBodyIndex() + 1) * (float) Math.PI * 0.5F);
                double wOff = off * Math.PI * 0.5F;
                float ampGrad = 0.8F + e.getBodyIndex() * 0.05F;

                this.swing(leftLegTailF, wkSp, wkDg * ampGrad, true, off, 0, limbSwing, limbSwingAmount);
                this.flap(leftLeg2TailF, wkSp, wkDg * 0.5F * ampGrad, true, off, 0.1F, limbSwing, limbSwingAmount);
                this.swing(leftLegTailB, wkSp, wkDg * ampGrad, true, off + 0.5F, 0, limbSwing, limbSwingAmount);
                this.flap(leftLegTailB2, wkSp, wkDg * 0.5F * ampGrad, true, off + 0.5F, 0.1F, limbSwing, limbSwingAmount);
                this.swing(rightLegTailF, wkSp, wkDg * ampGrad * 0.95F, false, off + 0.15F, 0, limbSwing, limbSwingAmount);
                this.flap(rightLeg2TailF, wkSp, wkDg * 0.5F * ampGrad, false, off + 0.15F, 0.1F, limbSwing, limbSwingAmount);
                this.swing(rightLegTailB, wkSp, wkDg * ampGrad * 0.95F, false, off + 0.65F, 0, limbSwing, limbSwingAmount);
                this.flap(rightLegTailB2, wkSp, wkDg * 0.5F * ampGrad, false, off + 0.65F, 0.1F, limbSwing, limbSwingAmount);
                this.tail.rotationPointY += (float) (Math.sin(limbSwing * wkSp - wOff) * limbSwingAmount * wkDg - limbSwingAmount * wkDg);
                this.tail.rotateAngleY += Mth.sin(limbSwing * wkSp * 0.7F - wOff) * wkDg * 0.3F * limbSwingAmount;
                this.tail.rotateAngleY += Mth.sin(ageInTicks * 0.08F - off) * 0.015F * (1 - limbSwingAmount * 0.7F);
                this.tail.rotationPointY += Mth.sin(ageInTicks * 0.1 - off) * 0.01;
                //══════ TAIL PINCERS: grasping ══════
                float pincerCycle = Mth.sin(ageInTicks * 0.22F + off) * 0.5F + 0.5F;
                this.swing(leftTail, wkSp, wkDg * 0.2F, true, off + 1, 0, limbSwing, limbSwingAmount);
                this.swing(rightTail, wkSp, wkDg * 0.2F, false, off + 1, 0, limbSwing, limbSwingAmount);
                leftTail.rotateAngleY += pincerCycle * 0.15F;
                rightTail.rotateAngleY -= pincerCycle * 0.15F;
                this.walk(leftTail, 0.25F, 0.35F, true, off + 1.5F, -0.5F, ageInTicks, 1);
                this.walk(rightTail, 0.25F, 0.35F, false, off + 1.5F, 0.5F, ageInTicks, 1);
                this.flap(leftTailEnd, 0.12F, 0.08F, true, off + 2, 0.02F, ageInTicks, 1);
                this.flap(rightTailEnd, 0.12F, 0.08F, false, off + 2, 0.02F, ageInTicks, 1);
                tail.setScale(1.0F + Mth.cos(ageInTicks * 0.10F + off * 0.5F) * 0.015F, 1.0F, 1.0F + Mth.cos(ageInTicks * 0.10F + off * 0.5F) * 0.01F);
            }
        }
    }

    @Override public Iterable<AdvancedModelBox> getAllParts() {
        return switch (type) {
            case 0 -> ImmutableList.of(root, head, head2, fangs, antenna_left, antenna_left_r1, antenna_right, antenna_right_r1);
            case 1 -> ImmutableList.of(root, body, leftLegBodyF, leftLeg2BodyF, rightLegBodyF, rightLegBodyF2, leftLegBodyB, leftLeg2BodyB, rightLegBodyB, rightLegBodyB2);
            case 2 -> ImmutableList.of(root, tail, leftLegTailF, leftLeg2TailF, rightLegTailF, rightLeg2TailF, leftLegTailB, leftLegTailB2, rightLegTailB, rightLegTailB2, leftTail, leftTailEnd, rightTail, rightTailEnd);
            default -> ImmutableList.of(root);
        };
    }

    public void setRotationAngle(AdvancedModelBox a, float x, float y, float z) { a.rotateAngleX = x; a.rotateAngleY = y; a.rotateAngleZ = z; }
}

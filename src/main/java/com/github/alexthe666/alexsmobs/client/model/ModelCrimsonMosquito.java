package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCrimsonMosquito extends AdvancedEntityModel<EntityCrimsonMosquito> {
    private final AdvancedModelBox root, body, wingL, wingR, legsL, legL1, legL2, legL3, legsR, legR1, legR2, legR3, tail, head, antennaL, antennaR, mouth;
    private ModelAnimator animator;

    public ModelCrimsonMosquito() {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-9.5F,-0.25F);root.addChild(body);body.setTextureOffset(31,65).addBox(-3,-3.5F,-3.75F,6,6,6,0,false);
        wingL=new AdvancedModelBox(this,"wl");wingL.setPos(2.7F,-3.2F,-0.75F);body.addChild(wingL);wingL.setTextureOffset(37,59).addBox(0,0,-1,18,0,5,0,false);
        wingR=new AdvancedModelBox(this,"wr");wingR.setPos(-2.7F,-3.2F,-0.75F);body.addChild(wingR);wingR.setTextureOffset(37,53).addBox(-18,0,-1,18,0,5,0,false);
        legsL=new AdvancedModelBox(this,"ll");legsL.setPos(3,2.5F,-2.75F);body.addChild(legsL);
        legL1=new AdvancedModelBox(this,"l1");legL1.setPos(0,0,0);legsL.addChild(legL1);setRotationAngle(legL1,0,0.5236F,0);legL1.setTextureOffset(0,51).addBox(0,-8,0,18,15,0,0,false);
        legL2=new AdvancedModelBox(this,"l2");legL2.setPos(0,0,0.4F);legsL.addChild(legL2);legL2.setTextureOffset(37,16).addBox(0,-8,0,18,15,0,0,false);
        legL3=new AdvancedModelBox(this,"l3");legL3.setPos(0,0,0.9F);legsL.addChild(legL3);setRotationAngle(legL3,0,-0.8727F,0);legL3.setTextureOffset(37,0).addBox(0,-8,0,18,15,0,0,false);
        legsR=new AdvancedModelBox(this,"lr");legsR.setPos(-3,2.5F,-2.75F);body.addChild(legsR);
        legR1=new AdvancedModelBox(this,"r1");legR1.setPos(0,0,0);legsR.addChild(legR1);setRotationAngle(legR1,0,-0.5236F,0);legR1.setTextureOffset(37,37).addBox(-18,-8,0,18,15,0,0,false);
        legR2=new AdvancedModelBox(this,"r2");legR2.setPos(0,0,0.4F);legsR.addChild(legR2);legR2.setTextureOffset(0,35).addBox(-18,-8,0,18,15,0,0,false);
        legR3=new AdvancedModelBox(this,"r3");legR3.setPos(0,0,0.9F);legsR.addChild(legR3);setRotationAngle(legR3,0,0.8727F,0);legR3.setTextureOffset(0,19).addBox(-18,-8,0,18,15,0,0,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0,-1.5F,2.25F);body.addChild(tail);tail.setTextureOffset(48,83).addBox(-2,-1.4F,0,4,4,16,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,0.5F,-3.75F);body.addChild(head);head.setTextureOffset(56,65).addBox(-2,-2,-4,4,4,4,0,false);
        antennaL=new AdvancedModelBox(this,"al");antennaL.setPos(1,-0.1F,-4);head.addChild(antennaL);setRotationAngle(antennaL,1.2217F,-0.48F,0.0436F);antennaL.setTextureOffset(5,0).addBox(0,-8,0,0,8,2,0,false);
        antennaR=new AdvancedModelBox(this,"ar");antennaR.setPos(-1,-0.1F,-4);head.addChild(antennaR);setRotationAngle(antennaR,1.2217F,0.48F,-0.0436F);antennaR.setTextureOffset(0,0).addBox(0,-8,0,0,8,2,0,false);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setPos(0,2,-3.5F);head.addChild(mouth);setRotationAngle(mouth,-1.0036F,0,0);mouth.setTextureOffset(23,0).addBox(-0.5F,0,-1,1,8,1,0,false);
        animator=ModelAnimator.create();this.updateDefaultPose();
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,wingL,wingR,legsL,legL1,legL2,legL3,legsR,legR1,legR2,legR3,tail,head,antennaL,antennaR,mouth);}

    @Override
    public void setupAnim(EntityCrimsonMosquito entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🦟 CRIMSON MOSQUITO — ERRATIC ZIGZAG BLOOD HUNTER ══════
        // IDENTITY: Unpredictable zigzag flight approach. Proboscis extends
        // to feed. Legs dangle with independent rhythms. Wings beat with
        // chaotic multi-frequency modulation. Body vibrates from wing whine.
        // UNIQUE vs WarpedMosco (giant 4-wing tripedal), Fly (leg-rubber).

        float partialTick = Minecraft.getInstance().getFrameTime();
        float flyProgress = entityIn.prevFlyProgress + (entityIn.flyProgress - entityIn.prevFlyProgress) * partialTick;
        float shootProgress = entityIn.prevShootProgress + (entityIn.shootProgress - entityIn.prevShootProgress) * partialTick;
        boolean flappingWings = flyProgress > 0 || entityIn.randomWingFlapTick > 0;

        // ── AAA BREATHING ───────────────────────────────────────────
        float breath = Mth.cos(ageInTicks * 0.18F);
        body.setScale(1.0F + breath * 0.02F, 1.0F, 1.0F);
        tail.rotationPointY += breath * 0.04F;

        // ── AAA ERRATIC ZIGZAG BODY ─────────────────────────────────
        body.rotationPointX += Mth.sin(ageInTicks * 1.1F) * 0.7F * flyProgress * 0.2F + Mth.sin(ageInTicks * 2.3F + 2F) * 0.35F * flyProgress * 0.2F;
        body.rotationPointZ += Mth.cos(ageInTicks * 0.8F + 1F) * 0.5F * flyProgress * 0.2F;
        // Body micro-vibration from wing whine
        body.rotationPointX += Mth.sin(ageInTicks * 3.3F) * 0.015F * (flappingWings ? 1F : 0F);

        // ── AAA ANTENNA PROBING ─────────────────────────────────────
        float flySpeed = 0.5F;
        float flyDegree = 0.5F;
        this.walk(antennaR, flySpeed, flyDegree * 0.3F, false, 0, 0.12F, ageInTicks, 1);
        this.walk(antennaL, flySpeed, flyDegree * 0.35F, false, 0, 0.12F, ageInTicks, 1);
        this.flap(antennaL, 0.2F, 0.1F, false, 2F, 0.03F, ageInTicks, 1);
        this.flap(antennaR, 0.22F, 0.09F, true, 2.3F, 0.03F, ageInTicks, 1);

        // ── AAA SHOOTING (preserved) ────────────────────────────────
        progressRotationPrev(head, shootProgress, Maths.rad(-10), 0, 0, 5F);
        progressRotationPrev(mouth, shootProgress, Maths.rad(-20), 0, 0, 5F);

        // ── AAA PROBOSCIS EXTENSION ─────────────────────────────────
        if (entityIn.getBloodLevel() > 0 || shootProgress > 0) {
            mouth.rotationPointZ -= 0.3F * (1F + entityIn.getBloodLevel() * 2F);
            mouth.rotateAngleX -= 0.15F;
        }

        // ── AAA PASSENGER/RIDING (preserved) ────────────────────────
        if (entityIn.isPassenger()) {
            progressRotationPrev(body, 5F, Maths.rad(-90), Maths.rad(180), 0, 5F);
            progressRotationPrev(head, 5F, Maths.rad(40), 0, 0, 5F);
            progressRotationPrev(mouth, 5F, Maths.rad(10), 0, 0, 5F);
            float legRot = 50;
            progressRotationPrev(legR1, 5F, 0, 0, Maths.rad(-legRot), 5F);
            progressRotationPrev(legR2, 5F, 0, 0, Maths.rad(-legRot), 5F);
            progressRotationPrev(legR3, 5F, 0, 0, Maths.rad(-legRot), 5F);
            progressRotationPrev(legL1, 5F, 0, 0, Maths.rad(legRot), 5F);
            progressRotationPrev(legL2, 5F, 0, 0, Maths.rad(legRot), 5F);
            progressRotationPrev(legL3, 5F, 0, 0, Maths.rad(legRot), 5F);
            this.mouth.setScale(1F, 0.85F + Mth.sin(ageInTicks) * 0.15F, 1F);
        } else {
            this.mouth.setScale(1F, 1F, 1F);
        }

        // ── AAA BLOOD BLOAT (preserved) ────────────────────────────
        if (shootProgress > 0) {
            this.mouth.setScale(1F + shootProgress * 0.1F, 1F - shootProgress * 0.1F, 1F + shootProgress * 0.1F);
        }
        float bloatScale = 1F + entityIn.getBloodLevel() * 0.1F;
        this.tail.rotateAngleX -= entityIn.getBloodLevel() * 0.05F;
        this.tail.setScale(bloatScale, bloatScale, bloatScale);

        // ── AAA WINGS WITH CHAOS ────────────────────────────────────
        if (flappingWings) {
            float chaos1 = Mth.sin(ageInTicks * 0.7F) * Mth.cos(ageInTicks * 1.3F) * 0.12F;
            float chaos2 = Mth.sin(ageInTicks * 0.55F + 1.7F) * 0.05F;
            this.flap(wingL, flySpeed * 3.3F, flyDegree + chaos1 + chaos2, true, 0, 0.2F, ageInTicks, 1);
            this.flap(wingR, flySpeed * 3.3F, flyDegree + chaos1 - chaos2, false, 0, 0.2F, ageInTicks, 1);
            wingL.rotateAngleZ += Mth.sin(ageInTicks * flySpeed * 3.3F + 0.5F) * 0.1F;
            wingR.rotateAngleZ -= Mth.sin(ageInTicks * flySpeed * 3.3F + 0.5F) * 0.1F;
        } else {
            this.wingR.rotateAngleX = Maths.rad(30);
            this.wingR.rotateAngleY = Maths.rad(70);
            this.wingL.rotateAngleX = Maths.rad(30);
            this.wingL.rotateAngleY = Maths.rad(-70);
        }

        // ── AAA FLIGHT ──────────────────────────────────────────────
        if (flyProgress > 0) {
            progressPositionPrev(body, flyProgress, 0, -10F, 0F, 5F);
            progressRotationPrev(legL1, flyProgress, 0, Maths.rad(-30), Maths.rad(60), 5F);
            progressRotationPrev(legR1, flyProgress, 0, Maths.rad(30), Maths.rad(-60), 5F);
            progressRotationPrev(legL2, flyProgress, 0, Maths.rad(-20), Maths.rad(60), 5F);
            progressRotationPrev(legR2, flyProgress, 0, Maths.rad(20), Maths.rad(-60), 5F);
            progressRotationPrev(legL3, flyProgress, 0, Maths.rad(-5), Maths.rad(60), 5F);
            progressRotationPrev(legR3, flyProgress, 0, Maths.rad(5), Maths.rad(-60), 5F);
            this.bob(body, flySpeed * 0.5F, flyDegree * 5, false, ageInTicks, 1);

            // AAA LEG DANGLE DIFFERENTIATION — each leg unique amplitude
            this.flap(legL1, flySpeed, flyDegree * 0.45F, true, 1, 0.1F, ageInTicks, 1);
            this.flap(legL2, flySpeed, flyDegree * 0.35F, true, 2, 0.08F, ageInTicks, 1);
            this.flap(legL3, flySpeed, flyDegree * 0.28F, true, 2.5F, 0.06F, ageInTicks, 1);
            this.flap(legR1, flySpeed, flyDegree * 0.5F, false, 1.3F, 0.12F, ageInTicks, 1);
            this.flap(legR2, flySpeed, flyDegree * 0.38F, false, 2.2F, 0.09F, ageInTicks, 1);
            this.flap(legR3, flySpeed, flyDegree * 0.3F, false, 2.8F, 0.07F, ageInTicks, 1);
            this.walk(tail, flySpeed, flyDegree * 0.15F, false, 0, -0.1F, ageInTicks, 1);
        }
    }

    public void setRotationAngle(AdvancedModelBox m, float x, float y, float z) { m.rotateAngleX = x; m.rotateAngleY = y; m.rotateAngleZ = z; }
}

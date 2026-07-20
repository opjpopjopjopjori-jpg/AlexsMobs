package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelElephant extends AdvancedEntityModel<EntityElephant> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox cabin;
    public final AdvancedModelBox left_chest;
    public final AdvancedModelBox right_chest;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox left_arm;
    public final AdvancedModelBox right_arm;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_tusk;
    public final AdvancedModelBox right_tusk;
    public final AdvancedModelBox left_megatusk;
    public final AdvancedModelBox right_megatusk;
    public final AdvancedModelBox left_ear;
    public final AdvancedModelBox right_ear;
    public final AdvancedModelBox trunk1;
    public final AdvancedModelBox trunk2;
    private final ModelAnimator animator;

    public ModelElephant(float f) {
        texWidth = 256; texHeight = 256;
        root = new AdvancedModelBox(this, "root"); root.setRotationPoint(0.0F, 24.0F, 0.0F);
        body = new AdvancedModelBox(this, "body"); body.setRotationPoint(0.0F, -41.0F, 1.0F); root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-14.0F, -15.0F, -24.0F, 28.0F, 29.0F, 47.0F, f, false);
        cabin = new AdvancedModelBox(this, "cabin"); cabin.setRotationPoint(0.0F, -14.0F, -11.5F); body.addChild(cabin);
        cabin.setTextureOffset(0, 165).addBox(-13.0F, -29.0F, -11.5F, 26.0F, 28.0F, 23.0F, f, false);
        cabin.setTextureOffset(109, 176).addBox(-16.0F, -29.1F, -13.5F, 32.0F, 7.0F, 27.0F, f, false);
        left_chest = new AdvancedModelBox(this, "left_chest"); left_chest.setRotationPoint(14.0F, -8.0F, 10.0F); body.addChild(left_chest);
        left_chest.setTextureOffset(57, 125).addBox(0.0F, -2.0F, -10.0F, 9.0F, 13.0F, 19.0F, f, false);
        right_chest = new AdvancedModelBox(this, "right_chest"); right_chest.setRotationPoint(-14.0F, -8.0F, 10.0F); body.addChild(right_chest);
        right_chest.setTextureOffset(57, 125).addBox(-9.0F, -2.0F, -10.0F, 9.0F, 13.0F, 19.0F, f, true);
        tail = new AdvancedModelBox(this, "tail"); tail.setRotationPoint(0.0F, -5.0F, 23.0F); body.addChild(tail);
        setRotationAngle(tail, 0.1745F, 0.0F, 0.0F); tail.setTextureOffset(42, 114).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 20.0F, 0.0F, f, false);
        left_arm = new AdvancedModelBox(this, "left_arm"); left_arm.setRotationPoint(8.1F, 9.5F, -18.2F); body.addChild(left_arm);
        left_arm.setTextureOffset(0, 0).addBox(-5.5F, 4.5F, -5.5F, 11.0F, 27.0F, 11.0F, f, false);
        right_arm = new AdvancedModelBox(this, "right_arm"); right_arm.setRotationPoint(-8.1F, 9.5F, -18.2F); body.addChild(right_arm);
        right_arm.setTextureOffset(0, 0).addBox(-5.5F, 4.5F, -5.5F, 11.0F, 27.0F, 11.0F, f, true);
        left_leg = new AdvancedModelBox(this, "left_leg"); left_leg.setRotationPoint(8.2F, 11.5F, 17.2F); body.addChild(left_leg);
        left_leg.setTextureOffset(71, 77).addBox(-5.5F, 2.5F, -5.5F, 11.0F, 27.0F, 11.0F, f, false);
        right_leg = new AdvancedModelBox(this, "right_leg"); right_leg.setRotationPoint(-8.2F, 11.5F, 17.2F); body.addChild(right_leg);
        right_leg.setTextureOffset(71, 77).addBox(-5.5F, 2.5F, -5.5F, 11.0F, 27.0F, 11.0F, f, true);
        head = new AdvancedModelBox(this, "head"); head.setRotationPoint(0.0F, -2.0F, -25.0F); body.addChild(head);
        head.setTextureOffset(0, 77).addBox(-10.0F, -12.0F, -14.0F, 20.0F, 21.0F, 15.0F, f, false);
        left_tusk = new AdvancedModelBox(this, "left_tusk"); left_tusk.setRotationPoint(6.5F, 8.0F, -11.5F); head.addChild(left_tusk);
        setRotationAngle(left_tusk, -0.4887F, -0.1571F, -0.2967F); left_tusk.setTextureOffset(104, 25).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, f, false);
        right_tusk = new AdvancedModelBox(this, "right_tusk"); right_tusk.setRotationPoint(-6.5F, 8.0F, -11.5F); head.addChild(right_tusk);
        setRotationAngle(right_tusk, -0.4887F, 0.1571F, 0.2967F); right_tusk.setTextureOffset(104, 25).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, f, true);
        left_megatusk = new AdvancedModelBox(this, "left_megatusk"); left_megatusk.setRotationPoint(6.5F, 8.0F, -11.5F); head.addChild(left_megatusk);
        setRotationAngle(left_megatusk, -0.2618F, 0.0524F, -0.2269F); left_megatusk.setTextureOffset(0, 114).addBox(-1.5F, 0.0F, -2.5F, 4.0F, 28.0F, 4.0F, f, false);
        left_megatusk.setTextureOffset(104, 25).addBox(-1.5F, 24.0F, -19.5F, 4.0F, 4.0F, 17.0F, f, false);
        right_megatusk = new AdvancedModelBox(this, "right_megatusk"); right_megatusk.setRotationPoint(-6.5F, 8.0F, -11.5F); head.addChild(right_megatusk);
        setRotationAngle(right_megatusk, -0.2618F, -0.0524F, 0.2269F); right_megatusk.setTextureOffset(0, 114).addBox(-2.5F, 0.0F, -2.5F, 4.0F, 28.0F, 4.0F, f, true);
        right_megatusk.setTextureOffset(104, 25).addBox(-2.5F, 24.0F, -19.5F, 4.0F, 4.0F, 17.0F, f, true);
        left_ear = new AdvancedModelBox(this, "left_ear"); left_ear.setRotationPoint(9.0F, -3.0F, -3.0F); head.addChild(left_ear);
        setRotationAngle(left_ear, 0.0F, -0.6981F, 0.0F); left_ear.setTextureOffset(104, 0).addBox(0.0F, -4.0F, -1.0F, 20.0F, 22.0F, 2.0F, f, false);
        right_ear = new AdvancedModelBox(this, "right_ear"); right_ear.setRotationPoint(-9.0F, -3.0F, -3.0F); head.addChild(right_ear);
        setRotationAngle(right_ear, 0.0F, 0.6981F, 0.0F); right_ear.setTextureOffset(104, 0).addBox(-20.0F, -4.0F, -1.0F, 20.0F, 22.0F, 2.0F, f, true);
        trunk1 = new AdvancedModelBox(this, "trunk1"); trunk1.setRotationPoint(0.0F, 3.0F, -16.0F); head.addChild(trunk1);
        trunk1.setTextureOffset(108, 108).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 24.0F, 8.0F, f, false);
        trunk2 = new AdvancedModelBox(this, "trunk2"); trunk2.setRotationPoint(0.0F, 20.0F, 0.0F); trunk1.addChild(trunk2);
        trunk2.setTextureOffset(17, 114).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, f, false);
        this.updateDefaultPose(); animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose(); animator.update(entity);
        animator.setAnimation(EntityElephant.ANIMATION_TRUMPET_0);
        animator.startKeyframe(5); animator.rotate(head, Maths.rad(-25),0,0); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-65),0,0); animator.rotate(trunk2,Maths.rad(-35),0,0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-35),0,0); animator.rotate(left_ear,0,Maths.rad(45),0); animator.rotate(right_ear,0,Maths.rad(-45),0); animator.rotate(trunk1,Maths.rad(-75),0,0); animator.rotate(trunk2,Maths.rad(-55),0,0); animator.move(trunk2,0,-2,1); animator.endKeyframe();
        animator.setStaticKeyframe(3); animator.resetKeyframe(7);
        animator.setAnimation(EntityElephant.ANIMATION_TRUMPET_1);
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-25),0,Maths.rad(-25)); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-75),Maths.rad(25),0); animator.rotate(trunk2,Maths.rad(-35),Maths.rad(10),0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-25),0,Maths.rad(25)); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-75),Maths.rad(-25),0); animator.rotate(trunk2,Maths.rad(-35),Maths.rad(-10),0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-25),0,Maths.rad(-25)); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-75),Maths.rad(25),0); animator.rotate(trunk2,Maths.rad(-35),Maths.rad(10),0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-25),0,Maths.rad(25)); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-75),Maths.rad(-25),0); animator.rotate(trunk2,Maths.rad(-35),Maths.rad(-10),0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.resetKeyframe(10);
        animator.setAnimation(EntityElephant.ANIMATION_CHARGE_PREPARE);
        animator.startKeyframe(10); animator.rotate(body,Maths.rad(15),0,0); animator.rotate(head,Maths.rad(-30),0,0); animator.rotate(right_ear,0,Maths.rad(-20),0); animator.rotate(left_ear,0,Maths.rad(20),0); animator.rotate(left_arm,Maths.rad(-15),0,Maths.rad(-15)); animator.rotate(right_arm,Maths.rad(-15),0,Maths.rad(15)); animator.rotate(left_leg,Maths.rad(-15),0,0); animator.rotate(right_leg,Maths.rad(-15),0,0); animator.rotate(trunk1,Maths.rad(-15),0,0); animator.rotate(trunk2,Maths.rad(45),0,0); animator.move(right_arm,0,-9,0); animator.move(left_arm,0,-9,0); animator.move(left_leg,0,-1,0); animator.move(right_leg,0,-1,0); animator.move(head,0,2,0); animator.move(trunk2,0,-2,0); animator.move(body,0,6,0); animator.endKeyframe();
        animator.setStaticKeyframe(10); animator.resetKeyframe(5);
        animator.setAnimation(EntityElephant.ANIMATION_STOMP);
        animator.startKeyframe(10); animator.rotate(body,Maths.rad(-35),0,0); animator.rotate(head,Maths.rad(-10),0,0); animator.rotate(left_leg,Maths.rad(35),0,0); animator.rotate(right_leg,Maths.rad(35),0,0); animator.rotate(left_arm,Maths.rad(35),Maths.rad(-15),0); animator.rotate(right_arm,Maths.rad(35),Maths.rad(15),0); animator.rotate(trunk1,Maths.rad(-15),0,0); animator.rotate(trunk2,Maths.rad(45),0,0); animator.rotate(tail,Maths.rad(45),0,0); animator.move(body,0,-6,0); animator.move(trunk2,0,-2,0); animator.move(left_leg,0,-1,0); animator.move(right_leg,0,-1,0); animator.endKeyframe();
        animator.setStaticKeyframe(7); animator.resetKeyframe(3);
        animator.setAnimation(EntityElephant.ANIMATION_FLING);
        animator.startKeyframe(10); animator.rotate(head,Maths.rad(15),0,0); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(10),0,0); animator.rotate(trunk2,Maths.rad(15),0,0); animator.move(head,0,3,0); animator.endKeyframe();
        animator.startKeyframe(5); animator.move(head,0,-2,-1); animator.rotate(head,Maths.rad(-45),0,0); animator.rotate(left_ear,0,Maths.rad(25),0); animator.rotate(right_ear,0,Maths.rad(-25),0); animator.rotate(trunk1,Maths.rad(-55),0,0); animator.rotate(trunk2,Maths.rad(-55),0,0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.setStaticKeyframe(2); animator.resetKeyframe(8);
        animator.setAnimation(EntityElephant.ANIMATION_EAT);
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-10),0,0); animator.rotate(trunk1,Maths.rad(-15),0,0); animator.rotate(trunk2,Maths.rad(-45),0,0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.startKeyframe(8); animator.rotate(head,Maths.rad(10),0,0); animator.rotate(trunk1,Maths.rad(-45),0,0); animator.rotate(trunk2,Maths.rad(120),0,0); animator.move(trunk1,0,-0,-1); animator.move(trunk2,0,-2F,1F); animator.endKeyframe();
        animator.setStaticKeyframe(5); animator.resetKeyframe(9);
        animator.setAnimation(EntityElephant.ANIMATION_BREAKLEAVES);
        animator.startKeyframe(5); animator.rotate(head,Maths.rad(-5),0,0); animator.rotate(left_ear,0,Maths.rad(5),0); animator.rotate(right_ear,0,Maths.rad(-5),0); animator.rotate(trunk1,Maths.rad(-40),0,0); animator.rotate(trunk2,Maths.rad(-60),0,0); animator.move(trunk2,0,-2,0); animator.endKeyframe();
        animator.setStaticKeyframe(5); animator.resetKeyframe(5);
    }

    public void renderToBuffer(PoseStack m, VertexConsumer b, int l, int o, float r, float g, float bl, float a) {
        if(this.young){float f=1.5F,f2=0.75F;head.rotationPointY=-10;head.setScale(f,f,f);tail.setScale(f,f,f);head.setShouldScaleChildren(true);trunk1.setScale(f2,f2,f2);trunk1.setShouldScaleChildren(true);m.pushPose();m.scale(0.35F,0.35F,0.35F);m.translate(0,2.8,0);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();head.setScale(1,1,1);tail.setScale(1,1,1);trunk1.setScale(1,1,1);}
        else{head.rotationPointY=-2.0F;m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }

    @Override public void setupAnim(EntityElephant e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(e,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        float wkSp=0.7F,wkDg=0.45F,idSp=0.08F,idDg=0.15F,pt=Minecraft.getInstance().getFrameTime();
        float sitP=e.prevSitProgress+(e.sitProgress-e.prevSitProgress)*pt;
        float standP=e.prevStandProgress+(e.standProgress-e.prevStandProgress)*pt;
        //══════ 🐘 ELEPHANT — GENTLE GIANT ══════
        // IDENTITY: Massive, slow, deliberate. COLUMNAR legs with lateral-sequence gait.
        // Head stays LEVEL (stabilized). Ears flap for cooling. Trunk is curious —
        // probes ground, touches objects, curls gently. Weight SHIFTS side-to-side
        // (the KEY elephant mechanic — NOT vertical bounce).
        // Dust-bathing is a rare contextual behavior, NOT idle. Idle = trunk exploration.

        // ── BREATHING: slow deep chest (~6 breaths/min), visible flank movement ──
        float breath=Mth.cos(ageInTicks*0.06F);
        body.setScale(1.0F,1.0F+breath*0.025F,1.0F+breath*0.015F);
        trunk1.rotationPointY+=breath*0.3F;trunk2.rotationPointY+=breath*0.4F;

        // ── LATERAL-SEQUENCE GAIT: same-side legs together ──
        // FL+BL → FR+BR (NOT diagonal like horses!)
        this.walk(left_arm,wkSp,wkDg*0.9F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(right_arm,wkSp,wkDg*0.9F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(left_leg,wkSp,wkDg*0.9F,true,0F,-0.05F,limbSwing,limbSwingAmount);
        this.walk(right_leg,wkSp,wkDg*0.9F,false,0F,-0.05F,limbSwing,limbSwingAmount);

        // ── LATERAL WEIGHT SHIFT (THE elephant mechanic!) ──
        // Massive body rocks SIDE-TO-SIDE as weight transfers between leg pairs
        float lateralRock=Mth.sin(limbSwing*wkSp)*wkDg*1.0F*limbSwingAmount;
        body.rotationPointX+=lateralRock;head.rotationPointX+=lateralRock*0.5F;
        body.rotateAngleZ+=Mth.sin(limbSwing*wkSp+1.5F)*wkDg*0.03F*limbSwingAmount;

        // ── MINIMAL VERTICAL BOB: columnar legs barely bounce ──
        float vertBob=Mth.sin(limbSwing*wkSp*2F)*wkDg*0.25F*limbSwingAmount;
        body.rotationPointY+=vertBob;

        // ── HEAD STABILIZATION: stays LEVEL like a gyroscope ──
        head.rotationPointZ+=Mth.sin(limbSwing*wkSp+1.5F)*wkDg*0.2F*limbSwingAmount;
        head.rotateAngleX+=Mth.sin(limbSwing*wkSp+1.5F)*wkDg*0.015F*limbSwingAmount;

        // ── LEG LIFT: columnar legs pick up and place down deliberately ──
        float lfLift=Mth.abs(Mth.sin(limbSwing*wkSp))*wkDg*1.5F*limbSwingAmount;
        float rtLift=Mth.abs(Mth.cos(limbSwing*wkSp))*wkDg*1.5F*limbSwingAmount;
        left_arm.rotationPointY+=lfLift;right_arm.rotationPointY+=rtLift;
        left_leg.rotationPointY+=lfLift*0.8F;right_leg.rotationPointY+=rtLift*0.8F;

        // ── TRUNK: CONSTANT gentle exploration — probes ground, curls, sways ──
        // Elephant trunk is the most dexterous appendage in nature. At idle it
        // explores the immediate environment — touches the ground, curls inward,
        // sways gently seeking scents and objects.
        this.walk(trunk1,idSp*0.5F,idDg*0.8F,false,0F,0.1F,ageInTicks,1);
        this.flap(trunk1,idSp*0.4F,idDg*0.6F,false,2.5F,0,ageInTicks,1);
        this.walk(trunk2,idSp*0.7F,idDg*0.5F,false,1.5F,0.06F,ageInTicks,1);
        this.swing(trunk2,idSp*0.5F,idDg*0.6F,false,3F,0,ageInTicks,1);
        // ── IDLE: trunk gently probes ground, curls, touches objects ──
        if(limbSwingAmount<0.05F){
            // Slow rhythmic trunk sway — exploring air and ground
            float probe=Mth.sin(ageInTicks*0.05F)*0.5F+0.5F;
            trunk1.rotateAngleX-=probe*0.15F; // base drops lower
            trunk2.rotateAngleX-=Mth.sin(ageInTicks*0.07F+1F)*0.2F; // tip curls inward
            trunk2.rotateAngleZ+=Mth.sin(ageInTicks*0.06F+2F)*0.15F; // gentle side probe
            trunk2.rotationPointZ+=Mth.sin(ageInTicks*0.04F)*0.3F; // reaches forward/back
            // Weight shift between legs while standing (subtle sway)
            body.rotationPointX+=Mth.sin(ageInTicks*0.03F)*0.15F;
        }
        // Trunk swings with walking momentum
        this.swing(trunk1,wkSp,wkDg*0.35F,false,3F,0,limbSwing,limbSwingAmount);
        this.swing(trunk2,wkSp,wkDg*0.5F,false,4F,0,limbSwing,limbSwingAmount);

        // ── EAR THERMOREGULATION: constant gentle flap for cooling ──
        this.flap(left_ear,wkSp*0.4F,0.05F,false,1F,0,limbSwing,limbSwingAmount);
        this.flap(right_ear,wkSp*0.4F,0.05F,true,1F,0,limbSwing,limbSwingAmount);
        this.flap(left_ear,idSp*0.3F,idDg*0.35F,false,0F,0,ageInTicks,1);
        this.flap(right_ear,idSp*0.3F,idDg*0.35F,true,0.5F,0,ageInTicks,1);

        // ── TAIL: minimal sway ──
        this.swing(tail,wkSp,wkDg*0.12F,false,3F,0,limbSwing,limbSwingAmount);
        this.flap(tail,idSp*0.3F,idDg*0.6F,false,0F,0,ageInTicks,1);

        // ── HEAD: subtle idle movement ──
        this.walk(head,idSp*0.5F,idDg*0.08F,false,0F,0.03F,ageInTicks,1);

        // ── SIT/STAND STATE TRANSITIONS (preserved) ──
        progressRotationPrev(body,standP,Maths.rad(-60),0,0,5F);
        progressRotationPrev(tail,standP,Maths.rad(60),0,0,5F);
        progressRotationPrev(right_arm,standP,Maths.rad(60),Maths.rad(10),0,5F);
        progressRotationPrev(left_arm,standP,Maths.rad(60),Maths.rad(-10),0,5F);
        progressRotationPrev(right_leg,standP,Maths.rad(60),Maths.rad(-15),0,5F);
        progressRotationPrev(left_leg,standP,Maths.rad(60),Maths.rad(15),0,5F);
        progressPositionPrev(body,standP,0,-9,0,5F);
        progressPositionPrev(right_arm,standP,0,0,2,5F);
        progressPositionPrev(left_arm,standP,0,0,2,5F);
        progressRotationPrev(tail,limbSwingAmount,Maths.rad(20),0,0,1F);
        //═══ AAA TRANSITIONS: Overlapping Action ═══
        // Legs lead, body follows, head trails — creates fluid multi-part response
        float legLead = Math.min(sitP, sitP*1.25F);
        float headTrail = Math.max(0, sitP-1.2F);
        float armTrail = Math.max(0, sitP-0.6F);
        progressRotationPrev(right_arm,legLead,Maths.rad(-90),Maths.rad(10),0,5F);
        progressRotationPrev(left_arm,legLead,Maths.rad(-90),Maths.rad(-10),0,5F);
        progressRotationPrev(right_leg,legLead,Maths.rad(90),Maths.rad(5),0,5F);
        progressRotationPrev(left_leg,legLead,Maths.rad(90),Maths.rad(-5),0,5F);
        progressPositionPrev(body,sitP,0,14,0,5F);
        progressPositionPrev(right_arm,armTrail,0,5,5,5F);
        progressPositionPrev(left_arm,armTrail,0,5,5,5F);
        progressPositionPrev(right_leg,legLead,0,5,-3,5F);
        progressPositionPrev(left_leg,legLead,0,5,-3,5F);
        progressPositionPrev(head,headTrail,0,-3,0,5F);
        progressPositionPrev(trunk1,headTrail,0,-2,0,5F);
        progressPositionPrev(trunk2,headTrail,0,1,0,5F);
        progressRotationPrev(trunk1,headTrail,Maths.rad(-45),0,0,5F);
        progressRotationPrev(trunk2,headTrail,Maths.rad(60),0,0,5F);
        progressRotationPrev(tail,headTrail,Maths.rad(50),0,0,5F);

        this.faceTarget(netHeadYaw,headPitch,2,head);
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,head,trunk1,trunk2,tail,left_ear,right_ear,left_leg,right_leg,left_arm,right_arm,cabin,left_chest,right_chest,left_tusk,right_tusk,left_megatusk,right_megatusk);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

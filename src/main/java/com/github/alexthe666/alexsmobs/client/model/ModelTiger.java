package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelTiger extends AdvancedEntityModel<EntityTiger> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox head;
    private final AdvancedModelBox earleft;
    private final AdvancedModelBox earright;
    private final AdvancedModelBox snout;
    private final AdvancedModelBox legleft;
    private final AdvancedModelBox legright;
    private final AdvancedModelBox armleft;
    private final AdvancedModelBox armright;
    private final ModelAnimator animator;

    public ModelTiger() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setPos(0.0F, -14.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-5.0F, -6.0F, -12.0F, 10.0F, 11.0F, 22.0F, 0.0F, false);

        tail = new AdvancedModelBox(this, "tail");
        tail.setPos(0.0F, -4.0F, 8.6F);
        body.addChild(tail);
        setRotationAngle(tail, 0.0873F, 0.0F, 0.0F);
        tail.setTextureOffset(46, 34).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 9.0F, 3.0F, 0.0F, false);

        tail2 = new AdvancedModelBox(this, "tail2");
        tail2.setPos(0.0F, 7.9F, 0.0F);
        tail.addChild(tail2);
        setRotationAngle(tail2, 0.2182F, 0.0F, 0.0F);
        tail2.setTextureOffset(43, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 9.0F, 3.0F, -0.1F, false);

        head = new AdvancedModelBox(this, "head");
        head.setPos(0.0F, -4.0F, -12.0F);
        body.addChild(head);
        head.setTextureOffset(0, 34).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(9, 15).addBox(4.0F, -1.0F, -5.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(9, 15).addBox(-5.0F, -1.0F, -5.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

        earleft = new AdvancedModelBox(this, "earleft");
        earleft.setPos(3.0F, -4.0F, -2.0F);
        head.addChild(earleft);
        earleft.setTextureOffset(0, 15).addBox(0.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        earright = new AdvancedModelBox(this, "earright");
        earright.setPos(-3.0F, -4.0F, -2.0F);
        head.addChild(earright);
        earright.setTextureOffset(0, 15).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        snout = new AdvancedModelBox(this, "snout");
        snout.setPos(0.0F, -1.0F, -6.0F);
        head.addChild(snout);
        setRotationAngle(snout, 0.1745F, 0.0F, 0.0F);
        snout.setTextureOffset(43, 13).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        legleft = new AdvancedModelBox(this, "legleft");
        legleft.setPos(2.9F, 5.0F, 7.9F);
        body.addChild(legleft);
        legleft.setTextureOffset(0, 48).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 11.0F, 5.0F, 0.0F, false);

        legright = new AdvancedModelBox(this, "legright");
        legright.setPos(-2.9F, 5.0F, 7.9F);
        body.addChild(legright);
        legright.setTextureOffset(0, 48).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 11.0F, 5.0F, 0.0F, true);

        armleft = new AdvancedModelBox(this, "armleft");
        armleft.setPos(3.5F, -1.5F, -9.0F);
        body.addChild(armleft);
        armleft.setTextureOffset(29, 34).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);

        armright = new AdvancedModelBox(this, "armright");
        armright.setPos(-3.5F, -1.5F, -9.0F);
        body.addChild(armright);
        armright.setTextureOffset(29, 34).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, true);
        this.updateDefaultPose();
        animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        animator.update(entity);
        animator.setAnimation(EntityTiger.ANIMATION_PAW_R);
        animator.startKeyframe(5);
        animator.rotate(head, Maths.rad(10F), 0, 0);
        animator.rotate(tail, Maths.rad(10F), 0, 0);
        animator.rotate(armleft, Maths.rad(-20F), 0, 0);
        animator.rotate(armright, Maths.rad(-20F), 0, 0);
        animator.move(body, 0, 0, 3F);
        animator.move(armright, 0, 1, 0);
        animator.move(armleft, 0, 1, 0);
        animator.endKeyframe();
        animator.startKeyframe(3);
        animator.rotate(head, Maths.rad(35F), 0, 0);
        animator.rotate(armleft, Maths.rad(-30F), 0, 0);
        animator.rotate(armright, Maths.rad(-70F), 0, Maths.rad(20F));
        animator.rotate(tail, Maths.rad(50F), 0, 0);
        animator.rotate(tail2, Maths.rad(10F), 0, 0);
        animator.move(body, 0, -3, -3);
        animator.rotate(body, Maths.rad(-30F), 0, 0);
        animator.rotate(legleft, Maths.rad(30F), 0, 0);
        animator.rotate(legright, Maths.rad(30F), 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, Maths.rad(35F), 0, 0);
        animator.rotate(armleft, Maths.rad(10F), 0, Maths.rad(-10F));
        animator.rotate(armright, Maths.rad(-40F), 0, Maths.rad(-20F));
        animator.rotate(tail, Maths.rad(50F), 0, 0);
        animator.rotate(tail2, Maths.rad(10F), 0, 0);
        animator.move(body, 0, -3, -3);
        animator.move(armright, -1, 0, 0);
        animator.rotate(body, Maths.rad(-30F), 0, 0);
        animator.rotate(legleft, Maths.rad(30F), 0, 0);
        animator.rotate(legright, Maths.rad(30F), 0, 0);
        animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityTiger.ANIMATION_PAW_L);
        animator.startKeyframe(5);
        animator.rotate(head, Maths.rad(10F), 0, 0);
        animator.rotate(tail, Maths.rad(10F), 0, 0);
        animator.rotate(armleft, Maths.rad(-20F), 0, 0);
        animator.rotate(armright, Maths.rad(-20F), 0, 0);
        animator.move(body, 0, 0, 3F);
        animator.move(armright, 0, 1, 0);
        animator.move(armleft, 0, 1, 0);
        animator.endKeyframe();
        animator.startKeyframe(3);
        animator.rotate(head, Maths.rad(35F), 0, 0);
        animator.rotate(armleft, Maths.rad(-70F), 0, Maths.rad(-20F));
        animator.rotate(armright, Maths.rad(-30F), 0, 0);
        animator.rotate(tail, Maths.rad(50F), 0, 0);
        animator.rotate(tail2, Maths.rad(10F), 0, 0);
        animator.move(body, 0, -3, -3);
        animator.rotate(body, Maths.rad(-30F), 0, 0);
        animator.rotate(legleft, Maths.rad(30F), 0, 0);
        animator.rotate(legright, Maths.rad(30F), 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, Maths.rad(35F), 0, 0);
        animator.rotate(armleft, Maths.rad(-40F), 0, Maths.rad(10F));
        animator.rotate(armright, Maths.rad(10F), 0, Maths.rad(20F));
        animator.rotate(tail, Maths.rad(50F), 0, 0);
        animator.rotate(tail2, Maths.rad(10F), 0, 0);
        animator.move(body, 0, -3, -3);
        animator.move(armleft, 1, 0, 0);
        animator.rotate(body, Maths.rad(-30F), 0, 0);
        animator.rotate(legleft, Maths.rad(30F), 0, 0);
        animator.rotate(legright, Maths.rad(30F), 0, 0);
        animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityTiger.ANIMATION_TAIL_FLICK);
        animator.startKeyframe(5);
        animator.rotate(tail, Maths.rad(10F), 0, Maths.rad(30F));
        animator.rotate(tail2, Maths.rad(10F), 0, Maths.rad(20F));
        animator.endKeyframe();
        animator.startKeyframe(10);
        animator.rotate(tail, Maths.rad(10F), 0, Maths.rad(-30F));
        animator.rotate(tail2, Maths.rad(10F), 0, Maths.rad(-20F));
        animator.endKeyframe();
        animator.startKeyframe(10);
        animator.rotate(tail, Maths.rad(10F), 0, Maths.rad(30F));
        animator.rotate(tail2, Maths.rad(10F), 0, Maths.rad(20F));
        animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityTiger.ANIMATION_LEAP);
        animator.startKeyframe(5);
        animator.move(body, 0, 1, 3F);
        animator.move(head, 0, 2, 0);
        animator.move(armright, 0, 1, 2);
        animator.move(armleft, 0, 1, 2);
        animator.rotate(head, Maths.rad(-15F), 0, 0);
        animator.rotate(tail, Maths.rad(10F), 0, 0);
        animator.rotate(body, Maths.rad(10F), 0, 0);
        animator.rotate(armleft, Maths.rad(-50F), 0, 0);
        animator.rotate(armright, Maths.rad(-50F), 0, 0);
        animator.rotate(legright, Maths.rad(-20F), 0, 0);
        animator.rotate(legleft, Maths.rad(-20F), 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.rotate(tail, Maths.rad(90F), 0, 0);
        animator.rotate(tail2, Maths.rad(-10F), 0, 0);
        animator.rotate(legleft, Maths.rad(30F), 0, Maths.rad(-10F));
        animator.rotate(legright, Maths.rad(30F), 0, Maths.rad(10F));
        animator.rotate(armright, Maths.rad(-60F), 0, Maths.rad(30F));
        animator.rotate(armleft, Maths.rad(-60F), 0, Maths.rad(-30F));
        animator.move(armright, -1, -1, 1);
        animator.move(armleft, 1, -1, 1);
        animator.move(legright, 0, -4, -1);
        animator.move(legleft, 0, -4, -1);
        animator.move(body, 0, -5, 4);
        animator.endKeyframe();
        animator.setStaticKeyframe(5);
        animator.resetKeyframe(5);


    }

    @Override
    public void setupAnim(EntityTiger entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float wkSp=0.7F, wkDg=0.8F, runSp=1.0F, runDg=0.8F, idSp=0.08F, idDg=0.06F;
        float moveProgress=5F*limbSwingAmount, pt=ageInTicks-entity.tickCount;
        float sitP=entity.prevSitProgress+(entity.sitProgress-entity.prevSitProgress)*pt;
        float holdP=entity.prevHoldProgress+(entity.holdProgress-entity.prevHoldProgress)*pt;
        float sleepP=entity.prevSleepProgress+(entity.sleepProgress-entity.prevSleepProgress)*pt;
        boolean leftSleep=entity.getId()%2==0;

        //══════ 🐅 TIGER — DIRECT-REGISTER STALKER ══════
        // IDENTITY: Solitary apex predator. Low-slung direct-register walk
        // (hind foot lands exactly in forefoot track). Head barely moves — eyes locked.
        // Tail: hangs heavy, TIP alone flicks independently. Ears: constant independent scan.
        // Shoulders & hips: counter-rotate for silent stalking.
        // UNIQUE from Snow Leopard: Tiger stays LOW, tail HANGS, walks HEAVY.
        // Snow Leopard stays HIGH, tail CURLS over back, walks LIGHT on rocks.

        // ── BREATHING: deep slow flank pulse, VISIBLE on sides ──
        float flankPulse=Mth.cos(ageInTicks*0.07F);
        body.setScale(1.0F+flankPulse*0.015F, 1.0F+flankPulse*0.02F, 1.0F+flankPulse*0.01F);
        body.rotationPointY+=flankPulse*0.1F;

        if(entity.isRunning()){
            // ── RUN: explosive gallop, back legs DRIVE, front REACH ──
            float gallopCycle=Mth.sin(limbSwing*runSp*1.2F);
            // Back legs push together (leporine gallop), body fully extends then compresses
            float extend=gallopCycle>0?gallopCycle*runDg*2.5F*limbSwingAmount:0;
            float compress=gallopCycle<0?-gallopCycle*runDg*1.8F*limbSwingAmount:0;
            body.rotationPointY+=extend*0.6F-compress*0.3F;
            body.rotateAngleX+=extend*0.08F-compress*0.05F;
            legleft.rotateAngleX+=extend*0.4F-compress*0.5F;
            legright.rotateAngleX+=extend*0.4F-compress*0.5F;
            armleft.rotateAngleX-=extend*0.3F+compress*0.4F;
            armright.rotateAngleX-=extend*0.3F+compress*0.4F;
            // Tail streams straight back during sprint
            tail.rotateAngleX+=limbSwingAmount*0.6F;
            tail2.rotateAngleX+=limbSwingAmount*0.3F;
            this.bob(body,runSp*2F,runDg*2,false,limbSwing,limbSwingAmount);
            this.walk(armleft,runSp,runDg*0.6F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(armright,runSp,runDg*0.6F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(legright,runSp,runDg*0.8F,false,0.5F,0F,limbSwing,limbSwingAmount);
            this.walk(legleft,runSp,runDg*0.8F,false,0.5F,0F,limbSwing,limbSwingAmount);
        }else{
            // ── DIRECT-REGISTER WALK: hind foot in forefoot track ──
            // Same-side legs move nearly together, creating silent fluid glide
            this.walk(armleft,wkSp,wkDg*0.6F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(armright,wkSp,wkDg*0.6F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(legright,wkSp,wkDg*0.7F,true,0.2F,0F,limbSwing,limbSwingAmount);
            this.walk(legleft,wkSp,wkDg*0.7F,false,0.2F,0F,limbSwing,limbSwingAmount);

            // ── SHOULDER-HIP COUNTER-ROTATION (the KEY felid mechanic) ──
            // Shoulders and pelvis rotate in OPPOSITE directions for stability
            float shoulderRot=Mth.sin(limbSwing*wkSp)*wkDg*0.06F*limbSwingAmount;
            float hipRot=Mth.sin(limbSwing*wkSp+1.5F)*wkDg*0.04F*limbSwingAmount;
            body.rotateAngleZ+=shoulderRot; // shoulders
            // Hips rotate opposite via leg rotation
            legleft.rotateAngleY-=hipRot;legright.rotateAngleY+=hipRot;

            // ── BODY GLIDES LOW: minimal vertical bob, maximal lateral smoothness ──
            body.rotationPointY+=Mth.sin(limbSwing*wkSp*2F)*wkDg*0.15F*limbSwingAmount;
            body.rotationPointX+=Mth.sin(limbSwing*wkSp)*wkDg*0.2F*limbSwingAmount;

            // ── HEAD LOCKS ON TARGET: almost no vertical movement ──
            head.rotationPointY-=Mth.abs(Mth.sin(limbSwing*wkSp))*wkDg*0.08F*limbSwingAmount;
        }

        // ── TAIL: heavy base hangs down, TIP flicks independently (tiger signature) ──
        // Unlike snow leopard (tail curls OVER back)
        if(limbSwingAmount<0.05F){
            // IDLE: tail tip flicks side to side like metronome
            float tailFlick=idDg*0.7F;
            tail2.rotateAngleZ+=Mth.sin(ageInTicks*0.55F+1.2F)*tailFlick;
            // Base barely moves
            tail.rotateAngleZ+=Mth.sin(ageInTicks*0.35F)*tailFlick*0.2F;
        }
        // During walk: tail hangs and sways with momentum
        AdvancedModelBox[] tailChain={tail,tail2};
        this.chainFlap(tailChain,entity.isRunning()?runSp:wkSp,
            (entity.isRunning()?runDg:wkDg)*0.4F,-1,limbSwing,limbSwingAmount);

        // ── EARS: CONSTANT INDEPENDENT SCANNING (360° awareness) ──
        // Left ear and right ear move at DIFFERENT frequencies
        this.flap(earleft,0.25F,0.08F,false,0F,0,ageInTicks,1);
        this.flap(earright,0.30F,0.06F,true,1.5F,0,ageInTicks,1);
        // Ears ROTATE — left ear tracks left sounds, right ear tracks right
        earleft.rotateAngleY+=Mth.sin(ageInTicks*0.4F)*0.3F;
        earright.rotateAngleY+=Mth.sin(ageInTicks*0.35F+1.8F)*0.3F;

        // ── SNOUT SCENTING: subtle nose wiggle as if smelling ──
        if(limbSwingAmount<0.1F){
            snout.rotationPointX+=Mth.sin(ageInTicks*0.45F+0.7F)*0.04F;
            snout.rotationPointY+=Mth.cos(ageInTicks*0.38F)*0.03F;
        }

        // ── SIT/SLEEP/HOLD TRANSITIONS ──
        progressRotationPrev(legleft,sitP,Maths.rad(-90),Maths.rad(-20),0,5F);
        progressRotationPrev(legright,sitP,Maths.rad(-90),Maths.rad(20),0,5F);
        progressRotationPrev(armleft,sitP,Maths.rad(-50),0,0,5F);
        progressRotationPrev(armright,sitP,Maths.rad(-50),0,0,5F);
        float ta=entity.getId()%2==0?1:-1;
        progressRotationPrev(tail,sitP,Maths.rad(20),Maths.rad(ta*-15),Maths.rad(ta*15),5F);
        progressRotationPrev(tail2,sitP,Maths.rad(20),Maths.rad(ta*-30),Maths.rad(ta*30),5F);
        progressPositionPrev(body,sitP,0,5F,0,5F);
        progressPositionPrev(tail,sitP,0,2F,0,5F);
        progressPositionPrev(armright,sitP,0,-1F,4,5F);
        progressPositionPrev(armleft,sitP,0,-1F,4,5F);
        progressPositionPrev(legright,sitP,0,2.8F,-0.5F,5F);
        progressPositionPrev(legleft,sitP,0,2.8F,-0.5F,5F);
        if(leftSleep){
            progressRotationPrev(body,sleepP,0,0,Maths.rad(-90),5F);
            progressRotationPrev(head,sleepP,0,0,Maths.rad(73),5F);
            progressRotationPrev(tail,sleepP,0,0,Maths.rad(20),5F);
            progressRotationPrev(tail2,sleepP,0,0,Maths.rad(-20),5F);
            progressPositionPrev(body,sleepP,0,9,0,5F);
            progressPositionPrev(head,sleepP,0,1,0,5F);
        }else{
            progressRotationPrev(body,sleepP,0,0,Maths.rad(90),5F);
            progressRotationPrev(head,sleepP,0,0,Maths.rad(-73),5F);
            progressRotationPrev(tail,sleepP,0,0,Maths.rad(-20),5F);
            progressRotationPrev(tail2,sleepP,0,0,Maths.rad(20),5F);
            progressPositionPrev(body,sleepP,0,9,0,5F);
            progressPositionPrev(head,sleepP,0,1,0,5F);
        }
        progressRotationPrev(body,holdP,Maths.rad(20),0,0,5F);
        progressRotationPrev(tail,holdP,Maths.rad(10),0,0,5F);
        progressRotationPrev(legleft,holdP,Maths.rad(-20),0,0,5F);
        progressRotationPrev(legright,holdP,Maths.rad(-20),0,0,5F);
        progressRotationPrev(armright,holdP,Maths.rad(-60),Maths.rad(-5),0,5F);
        progressRotationPrev(armleft,holdP,Maths.rad(-60),Maths.rad(5),0,5F);
        progressPositionPrev(body,holdP,0,3,0,5F);
        progressPositionPrev(head,holdP,0,-1,1,5F);
        this.flap(head,0.85F,0.3F,false,0F,0F,ageInTicks,holdP*0.2F);
        this.flap(tail,0.85F,0.3F,false,0F,0F,ageInTicks,holdP*0.2F);
        this.flap(tail2,0.85F,0.3F,false,0F,0F,ageInTicks,holdP*0.2F);
        this.flap(earleft,0.85F,0.3F,false,-1,0F,ageInTicks,holdP*0.2F);
        this.flap(earright,0.85F,0.3F,false,-1,0F,ageInTicks,holdP*0.2F);

        progressRotationPrev(tail,moveProgress,Maths.rad(40),0,0,5F);
        progressPositionPrev(head,Math.min(moveProgress*2F,5F),0,2,0,5F);
        progressPositionPrev(tail,moveProgress,0,1,0,5F);
        progressPositionPrev(body,moveProgress,0,1,0,5F);
        progressPositionPrev(armleft,moveProgress,0,-1,0,5F);progressPositionPrev(armright,moveProgress,0,-1,0,5F);
        progressPositionPrev(legleft,moveProgress,0,-1,0,5F);progressPositionPrev(legright,moveProgress,0,-1,0,5F);

        if(sleepP==0){this.faceTarget(netHeadYaw,headPitch,1.2F,head);}
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, head, tail, tail2, snout, earleft, earright, legleft, legright, armleft, armright);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.5F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.5D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            head.setScale(1, 1, 1);
        } else {
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}
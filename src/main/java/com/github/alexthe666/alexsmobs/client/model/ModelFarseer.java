package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFarseer;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ModelFarseer extends AdvancedEntityModel<EntityFarseer> {
    public final AdvancedModelBox eye;
    private final AdvancedModelBox root;
    private final AdvancedModelBox bodyCube1, head, leftUpperMask, rightUpperMask, leftLowerMask, rightLowerMask, bodyCube2;
    private final AdvancedModelBox leftArm, leftElbow, leftHand, leftUpperRFinger, leftLowerRFinger, leftLowerLFinger, leftUpperLFinger;
    private final AdvancedModelBox leftArm2, leftElbow2, leftHand2, leftUpperRFinger2, leftLowerRFinger2, leftLowerLFinger2, leftUpperLFinger2;
    private final AdvancedModelBox rightArm, rightElbow, rightHand, rightUpperRFinger, rightLowerRFinger2, rightLowerLFinger, rightUpperLFinger;
    private final AdvancedModelBox rightArm2, rightElbow2, rightHand2, rightUpperRFinger2, rightLowerRFinger3, rightLowerLFinger2, rightUpperLFinger2;
    private final ModelAnimator animator;

    public ModelFarseer(float scale) {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0,24,0);
        bodyCube1=new AdvancedModelBox(this,"bc1");bodyCube1.setRotationPoint(2,-8,0);root.addChild(bodyCube1);
        bodyCube1.setTextureOffset(0,56).addBox(-5,-2,-3,10,4,5,scale,false);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0,-15,0);root.addChild(head);
        head.setTextureOffset(0,0).addBox(-10.5F,-5,-6,21,9,13,scale,false);
        leftUpperMask=new AdvancedModelBox(this,"lum");leftUpperMask.setRotationPoint(0,-5,1);head.addChild(leftUpperMask);
        leftUpperMask.setTextureOffset(0,23).addBox(0,-0.5F,-7.9F,11,7,8,scale,false);
        rightUpperMask=new AdvancedModelBox(this,"rum");rightUpperMask.setRotationPoint(0,-5,1);head.addChild(rightUpperMask);
        rightUpperMask.setTextureOffset(0,23).addBox(-11,-0.5F,-7.9F,11,7,8,scale,true);
        leftLowerMask=new AdvancedModelBox(this,"llm");leftLowerMask.setRotationPoint(0,3,1);head.addChild(leftLowerMask);
        leftLowerMask.setTextureOffset(31,31).addBox(0,-2.5F,-7.9F,11,4,8,scale,false);
        rightLowerMask=new AdvancedModelBox(this,"rlm");rightLowerMask.setRotationPoint(0,3,1);head.addChild(rightLowerMask);
        rightLowerMask.setTextureOffset(31,31).addBox(-11,-2.5F,-7.9F,11,4,8,scale,true);
        eye=new AdvancedModelBox(this,"eye");eye.setRotationPoint(0,4,-1);head.addChild(eye);
        eye.setTextureOffset(56,0).addBox(-4.5F,-8,-6,9,4,2,scale,false);
        bodyCube2=new AdvancedModelBox(this,"bc2");bodyCube2.setRotationPoint(1,-2,1);root.addChild(bodyCube2);
        bodyCube2.setTextureOffset(33,44).addBox(-6,-3,-3,10,5,6,scale,false);
        // Arms init abbreviated for compactness — all parts identical to original
        leftArm=new AdvancedModelBox(this,"la");leftArm.setRotationPoint(9,-16.5F,9);root.addChild(leftArm);setRotationAngle(leftArm,0,0,-0.7854F);leftArm.setTextureOffset(31,23).addBox(-1,-1.5F,-2,16,3,4,scale,false);
        leftElbow=new AdvancedModelBox(this,"le");leftElbow.setRotationPoint(15,0,0);leftArm.addChild(leftElbow);leftElbow.setTextureOffset(0,39).addBox(-1,-1,-13,2,2,14,scale,false);
        leftHand=new AdvancedModelBox(this,"lh");leftHand.setRotationPoint(0,0,-12);leftElbow.addChild(leftHand);
        leftUpperRFinger=new AdvancedModelBox(this,"lurf");leftUpperRFinger.setRotationPoint(-1.4F,-1.4F,0);leftHand.addChild(leftUpperRFinger);setRotationAngle(leftUpperRFinger,0,0,-0.7854F);leftUpperRFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftLowerRFinger=new AdvancedModelBox(this,"llrf");leftLowerRFinger.setRotationPoint(-1.4F,1.4F,0);leftHand.addChild(leftLowerRFinger);setRotationAngle(leftLowerRFinger,0,0,-2.3562F);leftLowerRFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftLowerLFinger=new AdvancedModelBox(this,"lllf");leftLowerLFinger.setRotationPoint(1.4F,1.4F,0);leftHand.addChild(leftLowerLFinger);setRotationAngle(leftLowerLFinger,0,0,2.3562F);leftLowerLFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftUpperLFinger=new AdvancedModelBox(this,"lulf");leftUpperLFinger.setRotationPoint(1.4F,-1.4F,0);leftHand.addChild(leftUpperLFinger);setRotationAngle(leftUpperLFinger,0,0,0.7854F);leftUpperLFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftArm2=new AdvancedModelBox(this,"la2");leftArm2.setRotationPoint(6,-13.5F,9);root.addChild(leftArm2);setRotationAngle(leftArm2,0,0,0.6545F);leftArm2.setTextureOffset(31,23).addBox(-1,-1.5F,-2,16,3,4,scale,false);
        leftElbow2=new AdvancedModelBox(this,"le2");leftElbow2.setRotationPoint(15,0,0);leftArm2.addChild(leftElbow2);leftElbow2.setTextureOffset(0,39).addBox(-1,-1,-13,2,2,14,scale,false);
        leftHand2=new AdvancedModelBox(this,"lh2");leftHand2.setRotationPoint(0,0,-12);leftElbow2.addChild(leftHand2);
        leftUpperRFinger2=new AdvancedModelBox(this,"lurf2");leftUpperRFinger2.setRotationPoint(-1.4F,-1.4F,0);leftHand2.addChild(leftUpperRFinger2);setRotationAngle(leftUpperRFinger2,0,0,-0.7854F);leftUpperRFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftLowerRFinger2=new AdvancedModelBox(this,"llrf2");leftLowerRFinger2.setRotationPoint(-1.4F,1.4F,0);leftHand2.addChild(leftLowerRFinger2);setRotationAngle(leftLowerRFinger2,0,0,-2.3562F);leftLowerRFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftLowerLFinger2=new AdvancedModelBox(this,"lllf2");leftLowerLFinger2.setRotationPoint(1.4F,1.4F,0);leftHand2.addChild(leftLowerLFinger2);setRotationAngle(leftLowerLFinger2,0,0,2.3562F);leftLowerLFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        leftUpperLFinger2=new AdvancedModelBox(this,"lulf2");leftUpperLFinger2.setRotationPoint(1.4F,-1.4F,0);leftHand2.addChild(leftUpperLFinger2);setRotationAngle(leftUpperLFinger2,0,0,0.7854F);leftUpperLFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,false);
        rightArm=new AdvancedModelBox(this,"ra");rightArm.setRotationPoint(-9,-16.5F,9);root.addChild(rightArm);setRotationAngle(rightArm,0,0,0.7854F);rightArm.setTextureOffset(31,23).addBox(-15,-1.5F,-2,16,3,4,scale,true);
        rightElbow=new AdvancedModelBox(this,"re");rightElbow.setRotationPoint(-15,0,0);rightArm.addChild(rightElbow);rightElbow.setTextureOffset(0,39).addBox(-1,-1,-13,2,2,14,scale,true);
        rightHand=new AdvancedModelBox(this,"rh");rightHand.setRotationPoint(0,0,-12);rightElbow.addChild(rightHand);
        rightUpperRFinger=new AdvancedModelBox(this,"rurf");rightUpperRFinger.setRotationPoint(1.4F,-1.4F,0);rightHand.addChild(rightUpperRFinger);setRotationAngle(rightUpperRFinger,0,0,0.7854F);rightUpperRFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightLowerRFinger2=new AdvancedModelBox(this,"rlrf2");rightLowerRFinger2.setRotationPoint(1.4F,1.4F,0);rightHand.addChild(rightLowerRFinger2);setRotationAngle(rightLowerRFinger2,0,0,2.3562F);rightLowerRFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightLowerLFinger=new AdvancedModelBox(this,"rllf");rightLowerLFinger.setRotationPoint(-1.4F,1.4F,0);rightHand.addChild(rightLowerLFinger);setRotationAngle(rightLowerLFinger,0,0,-2.3562F);rightLowerLFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightUpperLFinger=new AdvancedModelBox(this,"rulf");rightUpperLFinger.setRotationPoint(-1.4F,-1.4F,0);rightHand.addChild(rightUpperLFinger);setRotationAngle(rightUpperLFinger,0,0,-0.7854F);rightUpperLFinger.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightArm2=new AdvancedModelBox(this,"ra2");rightArm2.setRotationPoint(-6,-13.5F,9);root.addChild(rightArm2);setRotationAngle(rightArm2,0,0,-0.6545F);rightArm2.setTextureOffset(31,23).addBox(-15,-1.5F,-2,16,3,4,scale,true);
        rightElbow2=new AdvancedModelBox(this,"re2");rightElbow2.setRotationPoint(-15,0,0);rightArm2.addChild(rightElbow2);rightElbow2.setTextureOffset(0,39).addBox(-1,-1,-13,2,2,14,scale,true);
        rightHand2=new AdvancedModelBox(this,"rh2");rightHand2.setRotationPoint(0,0,-12);rightElbow2.addChild(rightHand2);
        rightUpperRFinger2=new AdvancedModelBox(this,"rurf2");rightUpperRFinger2.setRotationPoint(1.4F,-1.4F,0);rightHand2.addChild(rightUpperRFinger2);setRotationAngle(rightUpperRFinger2,0,0,0.7854F);rightUpperRFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightLowerRFinger3=new AdvancedModelBox(this,"rlrf3");rightLowerRFinger3.setRotationPoint(1.4F,1.4F,0);rightHand2.addChild(rightLowerRFinger3);setRotationAngle(rightLowerRFinger3,0,0,2.3562F);rightLowerRFinger3.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightLowerLFinger2=new AdvancedModelBox(this,"rllf2");rightLowerLFinger2.setRotationPoint(-1.4F,1.4F,0);rightHand2.addChild(rightLowerLFinger2);setRotationAngle(rightLowerLFinger2,0,0,-2.3562F);rightLowerLFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        rightUpperLFinger2=new AdvancedModelBox(this,"rulf2");rightUpperLFinger2.setRotationPoint(-1.4F,-1.4F,0);rightHand2.addChild(rightUpperLFinger2);setRotationAngle(rightUpperLFinger2,0,0,-0.7854F);rightUpperLFinger2.setTextureOffset(0,0).addBox(-1,-6,-3,2,7,4,scale,true);
        this.updateDefaultPose();animator=ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity,float f,float f1,float f2,float f3,float f4){
        animator.update(entity);
        animator.setAnimation(EntityFarseer.ANIMATION_EMERGE);
        animator.startKeyframe(0);animator.move(root,0,0,20);
        animator.rotate(leftArm,0,Maths.rad(80),0);animator.rotate(leftElbow,Maths.rad(-20),Maths.rad(-30),0);animator.rotate(leftHand,Maths.rad(-35),Maths.rad(40),0);animator.move(leftArm,-1,-5,-6);
        animator.rotate(rightArm,0,Maths.rad(-80),0);animator.rotate(rightElbow,Maths.rad(-40),Maths.rad(70),0);animator.rotate(rightHand,Maths.rad(5),Maths.rad(-60),0);animator.move(rightArm,1,-5,-6);
        animator.rotate(leftArm2,0,Maths.rad(60),0);animator.rotate(leftElbow2,Maths.rad(40),Maths.rad(-30),0);animator.rotate(leftHand2,Maths.rad(-15),Maths.rad(40),0);animator.move(leftArm2,2,6,-6);
        animator.rotate(rightArm2,0,Maths.rad(-80),0);animator.rotate(rightElbow2,Maths.rad(20),Maths.rad(50),0);animator.rotate(rightHand2,Maths.rad(25),Maths.rad(-60),0);animator.move(rightArm2,-2,5,-4);animator.endKeyframe();
        animator.setStaticKeyframe(10);
        animator.startKeyframe(10);animator.move(root,0,0,15);
        animator.rotate(leftArm,0,Maths.rad(70),0);animator.rotate(leftElbow,Maths.rad(-20),Maths.rad(-30),0);animator.rotate(leftHand,Maths.rad(-5),Maths.rad(50),0);animator.move(leftArm,1,-5,-1);
        animator.rotate(rightArm,0,Maths.rad(-80),0);animator.rotate(rightElbow,Maths.rad(-40),Maths.rad(60),0);animator.rotate(rightHand,Maths.rad(5),Maths.rad(-70),0);animator.move(rightArm,-1,-5,-1);
        animator.rotate(leftArm2,0,Maths.rad(60),0);animator.rotate(leftElbow2,Maths.rad(30),Maths.rad(-30),0);animator.rotate(leftHand2,Maths.rad(-5),Maths.rad(40),0);animator.move(leftArm2,3,6,-1);
        animator.rotate(rightArm2,0,Maths.rad(-70),0);animator.rotate(rightElbow2,Maths.rad(20),Maths.rad(50),0);animator.rotate(rightHand2,Maths.rad(25),Maths.rad(-60),0);animator.move(rightArm2,-3,5,1);animator.endKeyframe();
        animator.startKeyframe(10);animator.move(root,0,0,10);
        animator.rotate(leftArm,0,Maths.rad(60),0);animator.rotate(leftElbow,Maths.rad(-20),Maths.rad(-30),0);animator.rotate(leftHand,Maths.rad(-15),Maths.rad(50),0);animator.move(leftArm,2,-5,4);
        animator.rotate(rightArm,0,Maths.rad(-72),0);animator.rotate(rightElbow,Maths.rad(-40),Maths.rad(60),0);animator.rotate(rightHand,Maths.rad(-5),Maths.rad(-75),0);animator.move(rightArm,-1,-3,4);
        animator.rotate(leftArm2,0,Maths.rad(55),0);animator.rotate(leftElbow2,Maths.rad(30),Maths.rad(-30),0);animator.rotate(leftHand2,Maths.rad(-15),Maths.rad(50),0);animator.move(leftArm2,5,6,4);
        animator.rotate(rightArm2,0,Maths.rad(-60),0);animator.rotate(rightElbow2,Maths.rad(20),Maths.rad(50),0);animator.rotate(rightHand2,Maths.rad(5),Maths.rad(-60),0);animator.move(rightArm2,-5,5,6);animator.endKeyframe();
        animator.startKeyframe(10);animator.move(root,0,0,5);
        animator.rotate(leftArm,0,Maths.rad(60),0);animator.rotate(leftElbow,Maths.rad(-20),Maths.rad(-40),0);animator.rotate(leftHand,Maths.rad(-15),Maths.rad(85),0);animator.move(leftArm,3,-5,9);
        animator.rotate(rightArm,0,Maths.rad(-72),0);animator.rotate(rightElbow,Maths.rad(-40),Maths.rad(60),0);animator.rotate(rightHand,Maths.rad(15),Maths.rad(-85),0);animator.move(rightArm,-5,-4,9);
        animator.rotate(leftArm2,0,Maths.rad(45),0);animator.rotate(leftElbow2,Maths.rad(30),Maths.rad(-40),0);animator.rotate(leftHand2,Maths.rad(-5),Maths.rad(70),0);animator.move(leftArm2,8,4,7);
        animator.rotate(rightArm2,0,Maths.rad(-50),0);animator.rotate(rightElbow2,Maths.rad(20),Maths.rad(50),0);animator.rotate(rightHand2,Maths.rad(25),Maths.rad(-70),0);animator.move(rightArm2,-5,5,11);animator.endKeyframe();
        animator.resetKeyframe(10);
    }

    @Override public void setupAnim(EntityFarseer e,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();animate(e,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        //══════ 👁 FARSEER — ALL-SEEING FLOATING EYE ══════
        // IDENTITY: Disembodied eye. Floats wobbly through air.
        // Pupil dilates. Eyelid slowly blinks. Sees everything.
        float pt=ageInTicks-e.tickCount,idSp=0.15F,invPD=1F-e.getFacingCameraAmount(pt),idDg=invPD;
        float angry=e.prevAngryProgress+(e.angryProgress-e.prevAngryProgress)*pt;
        float s1=(e.prevStrikeProgress[0]+(e.strikeProgress[0]-e.prevStrikeProgress[0])*pt)*invPD;
        float s2=(e.prevStrikeProgress[1]+(e.strikeProgress[1]-e.prevStrikeProgress[1])*pt)*invPD;
        float s3=(e.prevStrikeProgress[2]+(e.strikeProgress[2]-e.prevStrikeProgress[2])*pt)*invPD;
        float s4=(e.prevStrikeProgress[3]+(e.strikeProgress[3]-e.prevStrikeProgress[3])*pt)*invPD;
        float c1=Math.max(s1,(e.prevClaspProgress[0]+(e.claspProgress[0]-e.prevClaspProgress[0])*pt)*invPD);
        float c2=Math.max(s2,(e.prevClaspProgress[1]+(e.claspProgress[1]-e.prevClaspProgress[1])*pt)*invPD);
        float c3=Math.max(s3,(e.prevClaspProgress[2]+(e.claspProgress[2]-e.prevClaspProgress[2])*pt)*invPD);
        float c4=Math.max(s4,(e.prevClaspProgress[3]+(e.claspProgress[3]-e.prevClaspProgress[3])*pt)*invPD);
        float armYaw=Maths.rad(e.getLatencyVar(5,3,pt)-e.getLatencyVar(0,3,pt));
        Vec3 toa=e.getLatencyOffsetVec(4,pt).scale(-4),boa=e.getLatencyOffsetVec(8,pt).scale(-5);
        Vec3 b1o=e.getLatencyOffsetVec(8,pt).scale(-3),b2o=e.getLatencyOffsetVec(12,pt).scale(-5);
        Vec3 angS=e.angryShakeVec.scale(angry*0.1F);

        // AAA ELDRITCH BREATHING
        float breath=Mth.cos(ageInTicks*0.08F);
        eye.setScale(1.0F+breath*0.02F,1.0F+breath*0.03F,1.0F+breath*0.02F);
        head.setScale(1.0F+breath*0.015F,1.0F,1.0F);
        bodyCube1.rotationPointY+=breath*0.12F;
        bodyCube2.rotationPointY+=breath*0.08F;

        progressRotationPrev(rightUpperMask,angry,Maths.rad(-35),Maths.rad(13),0,5F);
        progressRotationPrev(leftUpperMask,angry,Maths.rad(-35),Maths.rad(-13),0,5F);
        progressRotationPrev(rightLowerMask,angry,Maths.rad(35),Maths.rad(13),0,5F);
        progressRotationPrev(leftLowerMask,angry,Maths.rad(35),Maths.rad(-13),0,5F);
        progressPositionPrev(bodyCube1,angry,0,0,4,5F);
        progressPositionPrev(bodyCube2,angry,0,0,2,5F);
        // Finger clasping
        progressRotationPrev(leftUpperRFinger,c1,Maths.rad(45),0,0,5F);progressRotationPrev(leftLowerRFinger,c1,Maths.rad(45),0,0,5F);progressRotationPrev(leftLowerLFinger,c1,Maths.rad(45),0,0,5F);progressRotationPrev(leftUpperLFinger,c1,Maths.rad(45),0,0,5F);
        progressRotationPrev(rightUpperRFinger,c2,Maths.rad(45),0,0,5F);progressRotationPrev(rightLowerRFinger2,c2,Maths.rad(45),0,0,5F);progressRotationPrev(rightLowerLFinger,c2,Maths.rad(45),0,0,5F);progressRotationPrev(rightUpperLFinger,c2,Maths.rad(45),0,0,5F);
        progressRotationPrev(leftUpperRFinger2,c3,Maths.rad(45),0,0,5F);progressRotationPrev(leftLowerRFinger2,c3,Maths.rad(45),0,0,5F);progressRotationPrev(leftLowerLFinger2,c3,Maths.rad(45),0,0,5F);progressRotationPrev(leftUpperLFinger2,c3,Maths.rad(45),0,0,5F);
        progressRotationPrev(rightUpperRFinger2,c4,Maths.rad(45),0,0,5F);progressRotationPrev(rightLowerRFinger3,c4,Maths.rad(45),0,0,5F);progressRotationPrev(rightLowerLFinger2,c4,Maths.rad(45),0,0,5F);progressRotationPrev(rightUpperLFinger2,c4,Maths.rad(45),0,0,5F);
        // Strike positions
        progressPositionPrev(leftArm,s1,4,-4,-9,5F);progressRotationPrev(leftArm,s1,0,Maths.rad(90),0,5F);progressRotationPrev(leftElbow,s1,0,Maths.rad(-70),0,5F);progressRotationPrev(leftHand,s1,0,Maths.rad(-10),0,5F);
        progressPositionPrev(rightArm,s2,-4,-4,-9,5F);progressRotationPrev(rightArm,s2,0,Maths.rad(-90),0,5F);progressRotationPrev(rightElbow,s2,0,Maths.rad(70),0,5F);progressRotationPrev(rightHand,s2,0,Maths.rad(10),0,5F);
        progressPositionPrev(leftArm2,s3,6,4,-9,5F);progressRotationPrev(leftArm2,s3,0,Maths.rad(90),0,5F);progressRotationPrev(leftElbow2,s3,0,Maths.rad(-70),0,5F);progressRotationPrev(leftHand2,s3,0,Maths.rad(-10),0,5F);
        progressPositionPrev(rightArm2,s4,-6,4,-9,5F);progressRotationPrev(rightArm2,s4,0,Maths.rad(-90),0,5F);progressRotationPrev(rightElbow2,s4,0,Maths.rad(70),0,5F);progressRotationPrev(rightHand2,s4,0,Maths.rad(10),0,5F);
        // Arm float idle
        leftArm.rotationPointX+=(toa.x+Mth.sin(ageInTicks*idSp+1.3F))*idDg;leftArm.rotationPointY+=(toa.y+Mth.sin(ageInTicks*idSp+1.6F))*idDg;leftArm.rotationPointZ+=(toa.z+Mth.cos(ageInTicks*idSp+1.9F))*idDg;leftArm.rotateAngleY+=armYaw;
        leftArm2.rotationPointX+=(boa.x+Mth.sin(ageInTicks*idSp+2.3F))*idDg;leftArm2.rotationPointY+=(boa.y+Mth.sin(ageInTicks*idSp+2.6F))*idDg;leftArm2.rotationPointZ+=(boa.z+Mth.cos(ageInTicks*idSp+2.9F))*idDg;leftArm2.rotateAngleY+=armYaw;
        rightArm.rotationPointX+=(toa.x+Mth.sin(ageInTicks*idSp+3.3F))*idDg;rightArm.rotationPointY+=(toa.y+Mth.sin(ageInTicks*idSp+3.6F))*idDg;rightArm.rotationPointZ+=(toa.z+Mth.cos(ageInTicks*idSp+3.9F))*idDg;rightArm.rotateAngleY+=armYaw;
        rightArm2.rotationPointX+=(boa.x+Mth.sin(ageInTicks*idSp+4.3F))*idDg;rightArm2.rotationPointY+=(boa.y+Mth.sin(ageInTicks*idSp+4.6F))*idDg;rightArm2.rotationPointZ+=(boa.z+Mth.cos(ageInTicks*idSp+4.9F))*idDg;rightArm2.rotateAngleY+=armYaw;
        bodyCube1.rotationPointX+=(b1o.x+Mth.sin(ageInTicks*idSp+7.3F))*idDg;bodyCube1.rotationPointY+=(b1o.y+Mth.sin(ageInTicks*idSp+7.6F))*idDg;bodyCube1.rotationPointZ+=(b1o.z+Mth.cos(ageInTicks*idSp+7.9F))*idDg;
        bodyCube2.rotationPointX+=(b2o.x+Mth.sin(ageInTicks*idSp+5.3F))*idDg;bodyCube2.rotationPointY+=(b2o.y+Mth.sin(ageInTicks*idSp+5.6F))*idDg;bodyCube2.rotationPointZ+=(b2o.z+Mth.cos(ageInTicks*idSp+5.9F))*idDg;
        head.rotationPointX+=angS.x;head.rotationPointY+=angS.y;head.rotationPointZ+=angS.z;
        this.bob(root,idSp,idDg,false,ageInTicks,1);
        this.swing(leftArm,idSp,idDg*0.2F,true,1,0,ageInTicks,1);this.swing(rightArm,idSp,idDg*0.2F,false,2,0,ageInTicks,1);
        this.swing(leftArm2,idSp,idDg*0.2F,true,3,0,ageInTicks,1);this.swing(rightArm2,idSp,idDg*0.2F,false,4,0,ageInTicks,1);
        this.walk(leftUpperMask,idSp*8,0.05F,true,1,0.2F,ageInTicks,angry*0.2F);this.walk(rightUpperMask,idSp*8,0.05F,true,2,0.2F,ageInTicks,angry*0.2F);
        this.walk(rightLowerMask,idSp*8,0.05F,false,3,0.2F,ageInTicks,angry*0.2F);this.walk(leftLowerMask,idSp*8,0.05F,false,4,0.2F,ageInTicks,angry*0.2F);
        head.rotateAngleY+=netHeadYaw*invPD*Mth.DEG_TO_RAD;
        head.rotateAngleX+=headPitch*invPD*Mth.DEG_TO_RAD;
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,bodyCube1,head,leftUpperMask,rightUpperMask,leftLowerMask,rightLowerMask,eye,bodyCube2,leftArm,leftElbow,leftHand,leftUpperRFinger,leftLowerRFinger,leftLowerLFinger,leftUpperLFinger,leftArm2,leftElbow2,leftHand2,leftUpperRFinger2,leftLowerRFinger2,leftLowerLFinger2,leftUpperLFinger2,rightArm,rightElbow,rightHand,rightUpperRFinger,rightLowerRFinger2,rightLowerLFinger,rightUpperLFinger,rightArm2,rightElbow2,rightHand2,rightUpperRFinger2,rightLowerRFinger3,rightLowerLFinger2,rightUpperLFinger2);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

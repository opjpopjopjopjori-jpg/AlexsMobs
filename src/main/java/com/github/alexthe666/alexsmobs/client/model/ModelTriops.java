package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelTriops extends AdvancedEntityModel<EntityTriops> {
    private final AdvancedModelBox root,body,leftAntenna,rightAntenna,leftLegs,rightLegs,tail1,tail2,leftTailFlipper,rightTailFlipper;

    public ModelTriops(){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this);root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this);body.setRotationPoint(0.0F,-2.0F,-2.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-3.5F,-1.1F,-3.5F,7.0F,3.0F,7.0F,0.0F,false);
        leftAntenna=new AdvancedModelBox(this);leftAntenna.setRotationPoint(3.5F,2.0F,-2.0F);body.addChild(leftAntenna);leftAntenna.setTextureOffset(15,21).addBox(-1.0F,-1.0F,0.0F,4.0F,1.0F,3.0F,0.0F,false);
        rightAntenna=new AdvancedModelBox(this);rightAntenna.setRotationPoint(-3.5F,2.0F,-2.0F);body.addChild(rightAntenna);rightAntenna.setTextureOffset(15,21).addBox(-3.0F,-1.0F,0.0F,4.0F,1.0F,3.0F,0.0F,true);
        leftLegs=new AdvancedModelBox(this);leftLegs.setRotationPoint(0.0F,1.9F,0.0F);body.addChild(leftLegs);setRotateAngle(leftLegs,0.0F,0.0F,0.0873F);leftLegs.setTextureOffset(22,0).addBox(0.0F,0.0F,-2.0F,3.0F,0.0F,4.0F,0.0F,false);
        rightLegs=new AdvancedModelBox(this);rightLegs.setRotationPoint(0.0F,1.9F,0.0F);body.addChild(rightLegs);setRotateAngle(rightLegs,0.0F,0.0F,-0.0873F);rightLegs.setTextureOffset(22,0).addBox(-3.0F,0.0F,-2.0F,3.0F,0.0F,4.0F,0.0F,true);
        tail1=new AdvancedModelBox(this);tail1.setRotationPoint(0.0F,1.0F,2.3F);body.addChild(tail1);tail1.setTextureOffset(0,18).addBox(-1.5F,-1.0F,-0.8F,3.0F,2.0F,4.0F,0.0F,false);
        tail1.setTextureOffset(22,11).addBox(1.5F,1.0F,-0.8F,2.0F,0.0F,4.0F,0.0F,false);tail1.setTextureOffset(22,11).addBox(-3.5F,1.0F,-0.8F,2.0F,0.0F,4.0F,0.0F,true);
        tail2=new AdvancedModelBox(this);tail2.setRotationPoint(0.0F,0.2F,3.2F);tail1.addChild(tail2);tail2.setTextureOffset(11,14).addBox(-1.5F,-1.2F,0.0F,3.0F,2.0F,4.0F,0.0F,false);
        leftTailFlipper=new AdvancedModelBox(this);leftTailFlipper.setRotationPoint(0.7F,-1.0F,4.8F);tail2.addChild(leftTailFlipper);setRotateAngle(leftTailFlipper,0.2618F,0.2618F,0.0F);leftTailFlipper.setTextureOffset(0,11).addBox(0.0F,0.0F,-1.0F,1.0F,0.0F,6.0F,0.0F,false);
        rightTailFlipper=new AdvancedModelBox(this);rightTailFlipper.setRotationPoint(-0.7F,-1.0F,4.8F);tail2.addChild(rightTailFlipper);setRotateAngle(rightTailFlipper,0.2618F,-0.2618F,0.0F);rightTailFlipper.setTextureOffset(0,11).addBox(-1.0F,0.0F,-1.0F,1.0F,0.0F,6.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,leftAntenna,rightAntenna,leftLegs,rightLegs,tail1,tail2,leftTailFlipper,rightTailFlipper);}

    @Override
    public void setupAnim(EntityTriops entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🦐 TRIOPS — UPSIDE-DOWN LIVING FOSSIL ══════
        // IDENTITY: 300-million-year-old tadpole shrimp. Swims UPSIDE-DOWN
        // via continuous leg ripple. Tail flippers steer. Primitive body.
        // BIOMECHANICS: Triops swim inverted — body flipped ~180° so legs
        // face upward, waving continuously to filter-feed and propel.
        float idleSpeed=0.5F,idleDegree=0.2F,swimSpeed=0.65F,swimDegree=0.25F;
        float partialTick=ageInTicks-entity.tickCount;
        float landProgress=entity.prevOnLandProgress+(entity.onLandProgress-entity.prevOnLandProgress)*partialTick;
        float swimAmount=1F-landProgress*0.2F;
        float swimRot=swimAmount*(entity.prevSwimRot+(entity.swimRot-entity.prevSwimRot)*partialTick);
        float yaw=entity.yBodyRotO+(entity.yBodyRot-entity.yBodyRotO)*partialTick;
        float tail1Rot=Mth.wrapDegrees(entity.prevTail1Yaw+(entity.tail1Yaw-entity.prevTail1Yaw)*partialTick-yaw)*0.35F;
        float tail2Rot=Mth.wrapDegrees(entity.prevTail2Yaw+(entity.tail2Yaw-entity.prevTail2Yaw)*partialTick-yaw)*0.35F;

        progressRotationPrev(body,landProgress,0,0,Maths.rad(-180),5F);
        progressPositionPrev(body,limbSwingAmount,0,-3,0,1F);

        // UPSIDE-DOWN SWIMMING: invert body pitch when in water
        float invertSwim=swimAmount*0.7F;
        body.rotateAngleX+=Mth.PI*invertSwim;
        body.rotateAngleZ+=Maths.rad(swimRot);
        this.body.rotateAngleX+=headPitch*Mth.DEG_TO_RAD*(1F-invertSwim);

        // ANTENNAE: independent sensing
        this.swing(rightAntenna,idleSpeed,idleDegree,false,1F,-0.2F,ageInTicks,1);
        this.swing(leftAntenna,idleSpeed*0.8F,idleDegree*1.2F,true,1.5F,-0.15F,ageInTicks,1);

        // TAIL FLIPPERS: steering
        this.walk(leftTailFlipper,idleSpeed,idleDegree*0.7F,false,3F,0.1F,ageInTicks,1);
        this.walk(rightTailFlipper,idleSpeed,idleDegree*0.7F,true,3.5F,0.08F,ageInTicks,1);
        tail1.rotateAngleY+=Maths.rad(tail1Rot);
        tail2.rotateAngleY+=Maths.rad(tail2Rot);

        // LEG RIPPLE: continuous wave (faster during active swim)
        float legSpeed=idleSpeed*3*(1.0F+swimAmount*0.5F);
        this.flap(leftLegs,legSpeed,idleDegree,true,1F,-0.2F,ageInTicks,1);
        this.flap(rightLegs,legSpeed*1.1F,idleDegree*0.9F,false,0.5F,-0.15F,ageInTicks,1);

        // BODY WAVE: subcarangiform
        this.walk(body,swimSpeed,swimDegree*0.6F,false,2.5F,0F,limbSwing,limbSwingAmount);
        this.walk(tail1,swimSpeed,swimDegree*1.0F,false,1.5F,0F,limbSwing,limbSwingAmount);
        this.walk(tail2,swimSpeed,swimDegree*1.8F,false,0.5F,0F,limbSwing,limbSwingAmount);
        this.walk(leftTailFlipper,swimSpeed,swimDegree,false,0F,-0.1F,limbSwing,limbSwingAmount);
        this.walk(rightTailFlipper,swimSpeed,swimDegree,false,0F,-0.1F,limbSwing,limbSwingAmount);

        // LAND: struggle
        this.walk(tail1,idleSpeed,idleDegree,false,0,-0.1F,ageInTicks,landProgress*0.2F);
        this.walk(tail2,idleSpeed,idleDegree*1.5F,false,0,-0.3F,ageInTicks,landProgress*0.2F);

        // LIVING FOSSIL BREATHING
        float breath=Mth.cos(ageInTicks*0.12F);
        body.setScale(1.0F+breath*0.02F,1.0F+breath*0.015F,1.0F);
        body.rotationPointY+=breath*0.04F;
        this.flap(leftAntenna,0.3F,0.04F,false,2,0.02F,ageInTicks,1);
        this.flap(rightAntenna,0.3F,0.04F,true,2.3F,0.02F,ageInTicks,1);
    }

    public void setRotateAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCosmaw extends AdvancedEntityModel<EntityCosmaw> {
    public final AdvancedModelBox root,body,leftArm,rightArm,leftFin,rightFin;
    public final AdvancedModelBox mouthArm1,mouthArm2,mouth,topJaw,lowerJaw;
    public final AdvancedModelBox eyesBase,leftEye,rightEye,tail,leftLeg,rightLeg,tailFin;

    public ModelCosmaw(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-10.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-6.5F,-7.0F,-20.0F,13.0F,14.0F,32.0F,0.0F,false);
        leftArm=new AdvancedModelBox(this,"leftArm");leftArm.setRotationPoint(5.0F,7.0F,-12.2F);body.addChild(leftArm);leftArm.setTextureOffset(17,47).addBox(-1.0F,0.0F,-0.8F,2.0F,4.0F,3.0F,0.0F,false);leftArm.setTextureOffset(0,13).addBox(0.0F,2.0F,1.2F,0.0F,4.0F,3.0F,0.0F,false);
        rightArm=new AdvancedModelBox(this,"rightArm");rightArm.setRotationPoint(-5.0F,7.0F,-12.2F);body.addChild(rightArm);rightArm.setTextureOffset(17,47).addBox(-1.0F,0.0F,-0.8F,2.0F,4.0F,3.0F,0.0F,true);rightArm.setTextureOffset(0,13).addBox(0.0F,2.0F,1.2F,0.0F,4.0F,3.0F,0.0F,true);
        leftFin=new AdvancedModelBox(this,"leftFin");leftFin.setRotationPoint(4.5F,-6.0F,0.0F);body.addChild(leftFin);leftFin.setTextureOffset(33,47).addBox(0.0F,0.0F,-5.0F,9.0F,0.0F,27.0F,0.0F,false);
        rightFin=new AdvancedModelBox(this,"rightFin");rightFin.setRotationPoint(-4.5F,-6.0F,0.0F);body.addChild(rightFin);rightFin.setTextureOffset(33,47).addBox(-9.0F,0.0F,-5.0F,9.0F,0.0F,27.0F,0.0F,true);
        mouthArm1=new AdvancedModelBox(this,"mouthArm1");mouthArm1.setRotationPoint(0.0F,-3.0F,-20.0F);body.addChild(mouthArm1);setRotationAngle(mouthArm1,1.0908F,0.0F,0.0F);mouthArm1.setTextureOffset(65,75).addBox(-2.0F,-1.0F,-20.0F,4.0F,4.0F,22.0F,0.0F,false);
        mouthArm2=new AdvancedModelBox(this,"mouthArm2");mouthArm2.setRotationPoint(0.0F,3.0F,-20.0F);mouthArm1.addChild(mouthArm2);setRotationAngle(mouthArm2,-1.0472F,0.0F,0.0F);mouthArm2.setTextureOffset(79,32).addBox(-2.0F,-4.0F,-17.0F,4.0F,4.0F,17.0F,-0.1F,false);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setRotationPoint(0.0F,-1.4F,-16.7F);mouthArm2.addChild(mouth);
        topJaw=new AdvancedModelBox(this,"topJaw");topJaw.setRotationPoint(0.0F,0.0F,-1.0F);mouth.addChild(topJaw);topJaw.setTextureOffset(0,13).addBox(-2.5F,-3.0F,-7.0F,5.0F,3.0F,8.0F,0.0F,false);
        lowerJaw=new AdvancedModelBox(this,"lowerJaw");lowerJaw.setRotationPoint(0.0F,-1.0F,0.3F);mouth.addChild(lowerJaw);lowerJaw.setTextureOffset(0,0).addBox(-3.0F,0.0F,-9.0F,6.0F,3.0F,9.0F,0.0F,false);
        eyesBase=new AdvancedModelBox(this,"eyesBase");eyesBase.setRotationPoint(0.0F,-7.0F,-11.0F);body.addChild(eyesBase);eyesBase.setTextureOffset(3,69).addBox(-11.0F,-1.0F,-1.0F,23.0F,2.0F,2.0F,0.0F,false);
        leftEye=new AdvancedModelBox(this,"leftEye");leftEye.setRotationPoint(13.0F,0.0F,0.0F);eyesBase.addChild(leftEye);leftEye.setTextureOffset(0,47).addBox(-1.0F,-3.0F,-3.0F,2.0F,6.0F,6.0F,0.0F,false);
        rightEye=new AdvancedModelBox(this,"rightEye");rightEye.setRotationPoint(-12.0F,0.0F,1.0F);eyesBase.addChild(rightEye);rightEye.setTextureOffset(0,47).addBox(-1.0F,-3.0F,-4.0F,2.0F,6.0F,6.0F,0.0F,true);
        tail=new AdvancedModelBox(this,"tail");tail.setRotationPoint(0.0F,-1.8F,11.6F);body.addChild(tail);tail.setTextureOffset(59,0).addBox(-4.5F,-5.0F,0.0F,9.0F,11.0F,20.0F,0.0F,false);
        leftLeg=new AdvancedModelBox(this,"leftLeg");leftLeg.setRotationPoint(3.0F,5.8F,3.2F);tail.addChild(leftLeg);leftLeg.setTextureOffset(19,13).addBox(-1.0F,0.0F,-0.8F,2.0F,4.0F,3.0F,0.0F,false);leftLeg.setTextureOffset(0,0).addBox(0.0F,2.0F,1.2F,0.0F,4.0F,3.0F,0.0F,false);
        rightLeg=new AdvancedModelBox(this,"rightLeg");rightLeg.setRotationPoint(-3.0F,5.8F,3.2F);tail.addChild(rightLeg);rightLeg.setTextureOffset(19,13).addBox(-1.0F,0.0F,-0.8F,2.0F,4.0F,3.0F,0.0F,true);rightLeg.setTextureOffset(0,0).addBox(0.0F,2.0F,1.2F,0.0F,4.0F,3.0F,0.0F,true);
        tailFin=new AdvancedModelBox(this,"tailFin");tailFin.setRotationPoint(0.0F,1.0F,7.0F);tail.addChild(tailFin);tailFin.setTextureOffset(0,47).addBox(0.0F,-10.0F,-3.0F,0.0F,19.0F,32.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,tail,tailFin,body,leftLeg,rightLeg,eyesBase,leftEye,rightEye,leftArm,leftFin,rightArm,rightFin,mouth,mouthArm1,mouthArm2,lowerJaw,topJaw);}

    @Override
    public void setupAnim(EntityCosmaw entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🌌 COSMAW — COSMIC RIFT JAWS + IRREGULAR VOID DRIFT ══════
        // IDENTITY: Living void portal. Jaws open into cosmic rift —
        // upper and lower jaws slowly gape at idle (breathing dimensional energy).
        // Body uses DUAL undulation (chainSwing + chainFlap) for non-repeating
        // alien motion. Fins ripple with comet-trail shimmer. Eyes scan.
        // BIOMECHANICS: Void creatures don't follow sinusoidal physics.
        // Adding a second undulation dimension at incommensurate frequency
        // creates true non-repeating cosmic drift.

        float walkSpeed=0.7F,walkDegree=0.4F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float clutchProgress=entity.prevClutchProgress+(entity.clutchProgress-entity.prevClutchProgress)*partialTick;
        float biteProgress=entity.prevBiteProgress+(entity.biteProgress-entity.prevBiteProgress)*partialTick;
        float openProgress=Math.max(Math.max(entity.prevOpenProgress+(entity.openProgress-entity.prevOpenProgress)*partialTick,clutchProgress),biteProgress);
        float cosmawPitch=(float)(Math.toRadians(entity.getClampedCosmawPitch(partialTick))*(5F-clutchProgress)*0.2F);
        float cosmawPitchPos=(float)((entity.getClampedCosmawPitch(partialTick)/90F)*(5F-clutchProgress)*0.2F);

        //══════ BREATHING: dimensional respiration ══════
        float breath=Mth.cos(ageInTicks*0.07F);
        body.setScale(1.0F+breath*0.015F,1.0F+breath*0.025F,1.0F+breath*0.015F);
        body.rotationPointY+=breath*0.1F;

        //══════ COSMIC RIFT JAWS: always slightly moving at idle ══════
        // Jaws slowly open/close in asymmetric rhythm — breathing void energy
        float jawGape=Mth.sin(ageInTicks*0.05F+1.5F)*0.5F+0.5F;
        topJaw.rotateAngleX-=jawGape*0.04F;
        lowerJaw.rotateAngleX+=jawGape*0.05F;
        // Mouth arms also drift slightly
        mouthArm1.rotateAngleZ+=Mth.sin(ageInTicks*0.06F+2F)*0.03F;
        mouthArm2.rotateAngleZ-=Mth.sin(ageInTicks*0.07F)*0.03F;
        lowerJaw.rotationPointY+=breath*0.04F;

        //══════ PITCH HANDLING (preserved) ══════
        body.rotateAngleX+=cosmawPitch;
        eyesBase.rotateAngleX-=cosmawPitch;
        mouthArm1.rotateAngleX-=cosmawPitch*0.2F;
        mouthArm2.rotateAngleX-=cosmawPitch*1.7F;
        lowerJaw.rotateAngleX-=cosmawPitch*0.3F;
        topJaw.rotateAngleX-=cosmawPitch*0.3F;
        if(cosmawPitchPos>0){mouthArm2.rotationPointY-=Math.min(cosmawPitchPos*6,3F);}
        else{mouthArm2.rotationPointZ-=cosmawPitchPos*3;mouthArm2.rotationPointY+=cosmawPitchPos;}

        //══════ IRREGULAR VOID DRIFT: dual-chain undulation ══════
        AdvancedModelBox[] voidChain={body,tail,tailFin};
        this.chainSwing(voidChain,walkSpeed,walkDegree*0.5F,-2,limbSwing,limbSwingAmount);
        // Second dimension at incommensurate frequency → non-repeating alien motion
        this.chainFlap(voidChain,walkSpeed*0.65F,walkDegree*0.3F,-1.8F,limbSwing,limbSwingAmount);
        // Slow portal spin
        body.rotateAngleY+=Mth.sin(ageInTicks*0.04F)*0.06F;

        //══════ FINS: independent comet-trail ripple ══════
        this.flap(leftFin,walkSpeed,walkDegree*0.7F,false,-2F,0.05F,limbSwing,limbSwingAmount);
        this.flap(rightFin,walkSpeed,walkDegree*0.7F,true,-2F,0.05F,limbSwing,limbSwingAmount);
        this.swing(leftFin,walkSpeed,walkDegree*0.35F,true,-1F,0.12F,limbSwing,limbSwingAmount);
        this.swing(rightFin,walkSpeed,walkDegree*0.25F,false,-1.3F,0.08F,limbSwing,limbSwingAmount);
        // Idle fin shimmer
        this.swing(leftFin,0.12F,0.08F,false,-2F,0.05F,ageInTicks,1);
        this.swing(rightFin,0.15F,0.06F,true,-1.7F,0.04F,ageInTicks,1);

        //══════ APPENDAGES: gentle cosmic drift ══════
        this.walk(leftArm,walkSpeed,walkDegree*0.3F,false,-2F,-0.05F,limbSwing,limbSwingAmount);
        this.walk(rightArm,walkSpeed,walkDegree*0.3F,false,-2F,-0.05F,limbSwing,limbSwingAmount);
        this.walk(leftLeg,walkSpeed,walkDegree*0.3F,false,-3F,-0.05F,limbSwing,limbSwingAmount);
        this.walk(rightLeg,walkSpeed,walkDegree*0.3F,false,-3F,-0.05F,limbSwing,limbSwingAmount);
        this.bob(body,walkSpeed,walkDegree*4F,false,limbSwing,limbSwingAmount);

        //══════ BUOYANCY ══════
        float buoyancy=Mth.sin(ageInTicks*0.06F+1.7F)*0.3F;
        body.rotationPointY+=buoyancy*(1-limbSwingAmount*0.5F);

        //══════ STATE TRANSITIONS (preserved) ══════
        progressRotationPrev(topJaw,openProgress,Maths.rad(-30),0,0,5F);
        progressRotationPrev(lowerJaw,openProgress,Maths.rad(30),0,0,5F);
        progressRotationPrev(body,clutchProgress,Maths.rad(-30),0,0,5F);
        progressRotationPrev(eyesBase,clutchProgress,Maths.rad(30),0,0,5F);
        progressRotationPrev(mouthArm1,clutchProgress,Maths.rad(-5),0,0,5F);
        progressRotationPrev(mouthArm2,clutchProgress,Maths.rad(120),0,0,5F);
        progressPositionPrev(mouthArm2,clutchProgress,0,-2,3,5F);
        progressPositionPrev(body,clutchProgress,0,-10,33,5F);
        progressPositionPrev(body,biteProgress,0,0,20,5F);
        progressRotationPrev(mouthArm1,biteProgress,Maths.rad(-35),0,0,5F);
        progressRotationPrev(mouthArm2,biteProgress,Maths.rad(50),0,0,5F);
        progressRotationPrev(leftArm,biteProgress,Maths.rad(10),0,Maths.rad(-30),5F);
        progressRotationPrev(rightArm,biteProgress,Maths.rad(10),0,Maths.rad(30),5F);

        float eyeYaw=(Mth.clamp(netHeadYaw,-40,40)/57.295776F);
        this.eyesBase.rotateAngleY+=eyeYaw*0.35F;
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
    @Override public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.5F;eyesBase.setScale(f,f,f);eyesBase.setShouldScaleChildren(true);m.pushPose();m.scale(0.5F,0.5F,0.5F);m.translate(0.0D,1.5D,0D);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();eyesBase.setScale(1,1,1);}
        else{m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }
}

package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityRhinoceros;
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

public class ModelRhinoceros extends AdvancedEntityModel<EntityRhinoceros> {
    private final AdvancedModelBox root,body,leftLeg,rightLeg,chest,head,horns,leftEar,rightEar,leftArm,rightArm;
    private final ModelAnimator animator;

    public ModelRhinoceros(){texWidth=128;texHeight=128;root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-19.0F,4.0F);root.addChild(body);body.setTextureOffset(0,44).addBox(-9.0F,-10.0F,-6.0F,18.0F,20.0F,21.0F,0.0F,false);
        leftLeg=new AdvancedModelBox(this,"leftLeg");leftLeg.setRotationPoint(6.0F,9.0F,12.0F);body.addChild(leftLeg);leftLeg.setTextureOffset(70,77).addBox(-4.0F,-1.0F,-4.0F,8.0F,11.0F,9.0F,0.0F,false);
        rightLeg=new AdvancedModelBox(this,"rightLeg");rightLeg.setRotationPoint(-6.0F,9.0F,12.0F);body.addChild(rightLeg);rightLeg.setTextureOffset(70,77).addBox(-4.0F,-1.0F,-4.0F,8.0F,11.0F,9.0F,0.0F,true);
        chest=new AdvancedModelBox(this,"chest");chest.setRotationPoint(0.0F,-4.0F,-10.0F);body.addChild(chest);chest.setTextureOffset(0,0).addBox(-11.0F,-10.0F,-14.0F,22.0F,23.0F,20.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0.0F,3.0F,-14.0F);chest.addChild(head);setRotationAngle(head,0.3927F,0.0F,0.0F);head.setTextureOffset(76,35).addBox(-6.0F,-6.0F,-8.0F,12.0F,14.0F,9.0F,0.0F,false);head.setTextureOffset(65,0).addBox(-4.0F,0.0F,-18.0F,8.0F,8.0F,10.0F,0.0F,false);
        horns=new AdvancedModelBox(this,"horns");horns.setRotationPoint(0.0F,0,0.0F);head.addChild(horns);horns.setTextureOffset(0,0).addBox(-2.0F,-12.0F,-18.0F,4.0F,12.0F,5.0F,0.0F,false);horns.setTextureOffset(0,44).addBox(-2.0F,-4.0F,-13.0F,4.0F,4.0F,4.0F,0.0F,false);
        leftEar=new AdvancedModelBox(this,"leftEar");leftEar.setRotationPoint(6.0F,-5.0F,-4.0F);head.addChild(leftEar);setRotationAngle(leftEar,-0.2443F,-0.2443F,0.7679F);leftEar.setTextureOffset(0,53).addBox(-1.0F,-5.0F,0.0F,3.0F,6.0F,1.0F,0.0F,false);
        rightEar=new AdvancedModelBox(this,"rightEar");rightEar.setRotationPoint(-6.0F,-5.0F,-4.0F);head.addChild(rightEar);setRotationAngle(rightEar,-0.2443F,0.2443F,-0.7679F);rightEar.setTextureOffset(0,53).addBox(-2.0F,-5.0F,0.0F,3.0F,6.0F,1.0F,0.0F,true);
        leftArm=new AdvancedModelBox(this,"leftArm");leftArm.setRotationPoint(7.3F,11.0F,-8.0F);chest.addChild(leftArm);leftArm.setTextureOffset(79,59).addBox(-4.0F,2.0F,-4.0F,7.0F,10.0F,7.0F,0.0F,false);
        rightArm=new AdvancedModelBox(this,"rightArm");rightArm.setRotationPoint(-7.3F,11.0F,-8.0F);chest.addChild(rightArm);rightArm.setTextureOffset(79,59).addBox(-3.0F,2.0F,-4.0F,7.0F,10.0F,7.0F,0.0F,true);
        this.updateDefaultPose();animator=ModelAnimator.create();}

    public void animate(IAnimatedEntity e,float f,float f1,float f2,float f3,float f4){this.resetToDefaultPose();animator.update(e);
        animator.setAnimation(EntityRhinoceros.ANIMATION_FLICK_EARS);
        animator.startKeyframe(2);animator.rotate(head,0,0,Maths.rad(10));animator.rotate(rightEar,0,Maths.rad(25),Maths.rad(40));animator.rotate(leftEar,0,Maths.rad(-25),Maths.rad(-40));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(head,0,0,Maths.rad(-10));animator.rotate(rightEar,0,0,0);animator.rotate(leftEar,0,0,0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(head,0,0,Maths.rad(10));animator.rotate(rightEar,0,Maths.rad(5),Maths.rad(-40));animator.rotate(leftEar,0,Maths.rad(-5),Maths.rad(40));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(head,0,0,Maths.rad(-10));animator.rotate(rightEar,0,Maths.rad(25),Maths.rad(40));animator.rotate(leftEar,0,Maths.rad(-25),Maths.rad(-40));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(head,0,0,Maths.rad(10));animator.rotate(rightEar,0,0,0);animator.rotate(leftEar,0,0,0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(head,0,0,0);animator.rotate(rightEar,0,Maths.rad(5),Maths.rad(-40));animator.rotate(leftEar,0,Maths.rad(-5),Maths.rad(40));animator.endKeyframe();
        animator.resetKeyframe(7);
        animator.setAnimation(EntityRhinoceros.ANIMATION_EAT_GRASS);
        animator.startKeyframe(5);eatPose();animator.endKeyframe();animator.startKeyframe(5);eatPose();animator.move(head,0,1,1);animator.rotate(head,Maths.rad(10),0,0);animator.endKeyframe();
        animator.startKeyframe(5);eatPose();animator.endKeyframe();animator.startKeyframe(5);eatPose();animator.move(head,0,1,1);animator.rotate(head,Maths.rad(10),0,0);animator.endKeyframe();
        animator.startKeyframe(5);eatPose();animator.endKeyframe();animator.startKeyframe(5);eatPose();animator.move(head,0,1,1);animator.rotate(head,Maths.rad(10),0,0);animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityRhinoceros.ANIMATION_FLING);
        animator.startKeyframe(5);animator.move(body,0,1,-2);animator.move(leftArm,0,-1,0);animator.move(rightArm,0,-1,0);animator.move(rightLeg,0,1,0);animator.move(leftLeg,0,1,0);animator.rotate(body,Maths.rad(10),0,0);animator.rotate(leftLeg,Maths.rad(-10),0,0);animator.rotate(rightLeg,Maths.rad(-10),0,0);animator.rotate(head,Maths.rad(20),0,0);animator.rotate(rightArm,Maths.rad(-50),0,Maths.rad(5));animator.rotate(leftArm,Maths.rad(-50),0,Maths.rad(-5));animator.endKeyframe();
        animator.startKeyframe(3);animator.rotate(head,Maths.rad(-60),0,0);animator.endKeyframe();animator.setStaticKeyframe(2);animator.resetKeyframe(5);
        animator.setAnimation(EntityRhinoceros.ANIMATION_SLASH);
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(rightLeg,Maths.rad(-30),0,0);animator.rotate(leftLeg,Maths.rad(-30),0,0);animator.rotate(rightArm,Maths.rad(-30),0,0);animator.rotate(leftArm,Maths.rad(-30),0,0);animator.move(head,0,0,-4);animator.rotate(head,Maths.rad(40),Maths.rad(45),Maths.rad(70));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,-2);animator.move(head,0,0,-2);animator.rotate(head,Maths.rad(-20),Maths.rad(-45),Maths.rad(-50));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(head,0,0,-4);animator.rotate(head,Maths.rad(40),Maths.rad(-45),Maths.rad(-70));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,-2);animator.move(head,0,0,-2);animator.rotate(head,Maths.rad(-20),Maths.rad(45),Maths.rad(50));animator.endKeyframe();
        animator.setStaticKeyframe(5);animator.resetKeyframe(5);}
    private void eatPose(){animator.rotate(body,Maths.rad(10),0,0);animator.rotate(rightLeg,Maths.rad(-10),0,0);animator.rotate(leftLeg,Maths.rad(-10),0,0);animator.rotate(rightArm,Maths.rad(-10),0,0);animator.rotate(leftArm,Maths.rad(-10),0,0);animator.rotate(head,Maths.rad(30),0,0);animator.move(head,0,-4F,-2);animator.move(rightLeg,0,1.8F,-2);animator.move(leftLeg,0,1.8F,-2);animator.move(rightArm,0,-3,0);animator.move(leftArm,0,-3,0);}

    @Override
    public void setupAnim(EntityRhinoceros entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.animate(entity,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        float walkSpeed=0.55F,walkDegree=0.55F,idleSpeed=0.07F,idleDegree=0.1F;

        //══════ 🦏 RHINOCEROS — ARMORED TANK ══════
        // IDENTITY: Living tank. COLUMNAR legs with minimal flex. Nearly ZERO
        // vertical bob — weight shifts LATERALLY like a battleship. Horn scrapes
        // ground at idle (dust-wallowing). Barrel chest, armor-plate skin.
        // UNIQUE vs Bison (hump roll), Gazelle (stott), Moose (high-step).

        // ── BREATHING: deep massive, armor plates expand ──
        float breath=Mth.cos(ageInTicks*0.05F);
        body.setScale(1.0F+breath*0.02F,1.0F+breath*0.03F,1.0F+breath*0.015F);
        body.rotationPointY+=breath*0.3F;chest.rotationPointY+=breath*0.2F;

        // ── HORN SCRAPE: rhino rubs horn on ground at idle ──
        if(limbSwingAmount<0.05F){
            head.rotateAngleX-=Mth.sin(ageInTicks*0.04F+2F)*0.06F; // dips horn
            head.rotationPointZ+=Mth.sin(ageInTicks*0.04F)*0.15F; // scrape motion
            horns.rotateAngleZ+=Mth.sin(ageInTicks*0.05F)*0.04F; // horn wiggles
        }

        // ── COLUMNAR LEGS: barely flex, minimal lift ──
        this.walk(leftArm,walkSpeed,walkDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(rightArm,walkSpeed,walkDegree*1.2F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(leftLeg,walkSpeed,walkDegree*1.2F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(rightLeg,walkSpeed,walkDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
        // Legs barely leave ground (columnar, tank-like)
        float lfPush=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.8F*limbSwingAmount;
        float rtPush=Mth.cos(limbSwing*walkSpeed)*walkDegree*0.8F*limbSwingAmount;
        leftArm.rotationPointY+=Mth.abs(lfPush)*1.2F;rightArm.rotationPointY+=Mth.abs(rtPush)*1.2F;
        leftLeg.rotationPointY+=Mth.abs(lfPush)*1.0F;rightLeg.rotationPointY+=Mth.abs(rtPush)*1.0F;

        // ── LATERAL TANK SWAY: massive body rocks side-to-side ──
        body.rotationPointX+=Mth.sin(limbSwing*walkSpeed*0.6F)*walkDegree*0.6F*limbSwingAmount;
        body.rotateAngleZ+=Mth.sin(limbSwing*walkSpeed*0.6F+1.5F)*walkDegree*0.03F*limbSwingAmount;
        chest.rotationPointX+=Mth.sin(limbSwing*walkSpeed*0.6F)*walkDegree*0.35F*limbSwingAmount;

        // ── HORN WEIGHT: head sways with heavy horn ──
        head.rotationPointZ+=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.4F*limbSwingAmount;

        // ── BOB: minimal vertical (tank doesn't bounce) ──
        this.bob(body,walkSpeed,walkDegree*1.2F,true,limbSwing,limbSwingAmount);

        // ── EARS: slow gentle flick ──
        this.flap(leftEar,0.12F,0.08F,true,1F,0,ageInTicks,1);
        this.flap(rightEar,0.12F,0.08F,false,1.3F,0,ageInTicks,1);
        this.walk(head,idleSpeed,idleDegree*0.5F,false,0F,0.05F,ageInTicks,1);
        this.head.rotateAngleY+=netHeadYaw*0.8F*Mth.DEG_TO_RAD;
        this.head.rotateAngleX+=headPitch*Mth.DEG_TO_RAD;
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.35F,ft=1.3F;head.setScale(f,f,f);head.setShouldScaleChildren(true);horns.showModel=false;leftArm.setScale(1,ft,1);rightArm.setScale(1,ft,1);leftLeg.setScale(1,ft,1);rightLeg.setScale(1,ft,1);ms.pushPose();ms.scale(0.5F,0.5F,0.5F);ms.translate(0.0D,1.3D,0D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();head.setScale(1,1,1);leftArm.setScale(1,1,1);rightArm.setScale(1,1,1);leftLeg.setScale(1,1,1);rightLeg.setScale(1,1,1);horns.showModel=true;}
        else{ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,head,chest,leftArm,leftEar,leftLeg,rightArm,rightEar,rightLeg,horns);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
}

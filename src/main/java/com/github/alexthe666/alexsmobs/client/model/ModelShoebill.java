package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityShoebill;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelShoebill extends AdvancedEntityModel<EntityShoebill> {
    public final AdvancedModelBox root,body,tail,leftWing,leftWingFeathers,rightWing,rightWingFeathers,headPivot,head,beak,jaw,jaw_r1,backHair,hair_r1,leftLeg,leftFoot,rightLeg,rightFoot;
    public ModelAnimator animator;

    public ModelShoebill(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-17.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-6.0F,-8.0F,-9.0F,12.0F,14.0F,18.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-4.0F,8.0F);body.addChild(tail);tail.setTextureOffset(45,0).addBox(-4.0F,-1.0F,0.0F,8.0F,3.0F,9.0F,0.0F,false);
        leftWing=new AdvancedModelBox(this,"leftWing");leftWing.setPos(6.0F,-5.0F,-6.0F);body.addChild(leftWing);leftWing.setTextureOffset(0,33).addBox(0.0F,-2.0F,-2.0F,2.0F,13.0F,16.0F,0.0F,false);
        leftWingFeathers=new AdvancedModelBox(this,"leftWingFeathers");leftWingFeathers.setPos(2.0F,11.0F,14.0F);leftWing.addChild(leftWingFeathers);leftWingFeathers.setTextureOffset(37,33).addBox(-2.0F,-9.0F,-15.0F,2.0F,14.0F,16.0F,0.0F,false);
        rightWing=new AdvancedModelBox(this,"rightWing");rightWing.setPos(-6.0F,-5.0F,-6.0F);body.addChild(rightWing);rightWing.setTextureOffset(0,33).addBox(-2.0F,-2.0F,-2.0F,2.0F,13.0F,16.0F,0.0F,true);
        rightWingFeathers=new AdvancedModelBox(this,"rightWingFeathers");rightWingFeathers.setPos(-2.0F,11.0F,14.0F);rightWing.addChild(rightWingFeathers);rightWingFeathers.setTextureOffset(37,33).addBox(0.0F,-9.0F,-15.0F,2.0F,14.0F,16.0F,0.0F,true);
        headPivot=new AdvancedModelBox(this,"headPivot");headPivot.setPos(0.0F,-6.0F,-10.0F);body.addChild(headPivot);
        head=new AdvancedModelBox(this,"head");headPivot.addChild(head);head.setTextureOffset(74,34).addBox(-4.0F,-5.0F,-10.0F,8.0F,8.0F,12.0F,0.0F,false);
        beak=new AdvancedModelBox(this,"beak");beak.setPos(0.0F,0.0F,-10.0F);head.addChild(beak);beak.setTextureOffset(43,0).addBox(-3.0F,-2.0F,-8.0F,6.0F,3.0F,9.0F,0.0F,false);
        jaw=new AdvancedModelBox(this,"jaw");jaw.setPos(0.0F,1.0F,-18.0F);beak.addChild(jaw);jaw.setTextureOffset(80,55).addBox(-3.0F,0.0F,0.0F,6.0F,2.0F,7.0F,0.0F,false);
        jaw_r1=new AdvancedModelBox(this,"jaw_r1");jaw_r1.setPos(0.0F,2.0F,0.0F);jaw.addChild(jaw_r1);jaw_r1.setTextureOffset(47,13).addBox(-2.5F,0.0F,0.0F,5.0F,1.0F,6.0F,0.0F,false);
        backHair=new AdvancedModelBox(this,"backHair");backHair.setPos(0.0F,-5.0F,1.0F);head.addChild(backHair);
        hair_r1=new AdvancedModelBox(this,"hair_r1");hair_r1.setPos(0.0F,0.0F,0.0F);backHair.addChild(hair_r1);setRotationAngle(hair_r1,-0.2618F,0.0F,0.0F);hair_r1.setTextureOffset(32,63).addBox(-3.0F,-3.0F,0.0F,6.0F,7.0F,0.0F,0.0F,false);
        leftLeg=new AdvancedModelBox(this,"leftLeg");leftLeg.setPos(3.0F,5.0F,3.0F);body.addChild(leftLeg);leftLeg.setTextureOffset(72,13).addBox(-1.5F,1.0F,-2.0F,3.0F,14.0F,3.0F,0.0F,false);
        leftFoot=new AdvancedModelBox(this,"leftFoot");leftFoot.setPos(0.0F,15.0F,0.0F);leftLeg.addChild(leftFoot);leftFoot.setTextureOffset(62,0).addBox(-3.0F,0.0F,-6.0F,5.0F,2.0F,6.0F,0.0F,false);
        rightLeg=new AdvancedModelBox(this,"rightLeg");rightLeg.setPos(-3.0F,5.0F,3.0F);body.addChild(rightLeg);rightLeg.setTextureOffset(72,13).addBox(-1.5F,1.0F,-2.0F,3.0F,14.0F,3.0F,0.0F,true);
        rightFoot=new AdvancedModelBox(this,"rightFoot");rightFoot.setPos(0.0F,15.0F,0.0F);rightLeg.addChild(rightFoot);rightFoot.setTextureOffset(62,0).addBox(-2.0F,0.0F,-6.0F,5.0F,2.0F,6.0F,0.0F,true);
        this.updateDefaultPose();animator=ModelAnimator.create();}

    public void animate(IAnimatedEntity e,float f,float f1,float f2,float f3,float f4){this.resetToDefaultPose();animator.update(e);
        animator.setAnimation(EntityShoebill.ANIMATION_BITE);animator.startKeyframe(2);animator.rotate(head,Maths.rad(-30),0,0);animator.rotate(jaw,Maths.rad(30),0,0);animator.endKeyframe();animator.startKeyframe(2);animator.rotate(head,Maths.rad(5),0,0);animator.rotate(jaw,Maths.rad(5),0,0);animator.endKeyframe();animator.resetKeyframe(3);}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,leftWing,leftWingFeathers,rightWing,rightWingFeathers,headPivot,head,beak,jaw,jaw_r1,backHair,hair_r1,leftLeg,leftFoot,rightLeg,rightFoot);}

    @Override
    public void setupAnim(EntityShoebill entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.animate(entity,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        float walkSpeed=0.6F,walkDegree=0.35F,idleSpeed=0.04F,idleDegree=0.15F,flapSpeed=0.4F,flapDegree=0.2F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float flyProgress=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*partialTick;
        float scaledLimbSwing=Math.min(1.0F,limbSwingAmount*1.6F);
        float runProgress=Math.max(5F*scaledLimbSwing-flyProgress,0);

        //══════ 🦩 SHOEBILL — STATUE-STILL FISH STRIKER ══════
        // IDENTITY: FREEZES motionless for MINUTES. Then EXPLOSIVE strike —
        // head plunges down like a spear to catch fish. Bill clatters
        // (mandible rattles). Slow deliberate wade through marsh.
        // UNIQUE vs Potoo (branch-camouflage), Toucan (fruit-tosser).

        // ── BREATHING: nearly imperceptible (ambush predator) ──
        float breath=Mth.cos(ageInTicks*0.04F);body.rotationPointY+=breath*0.1F;

        // ── STATUE-STILL IDLE: barely moves ──
        this.bob(head,idleSpeed,idleDegree*0.5F,false,ageInTicks,1);

        // ── BILL CLATTER: jaw rattles ──
        jaw.rotateAngleX+=Mth.sin(ageInTicks*0.12F)*0.01F*(1-limbSwingAmount);

        // ── DELIBERATE SLOW WADE ──
        progressRotationPrev(body,runProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(rightLeg,runProgress,Maths.rad(-25),0,0,5F);
        progressRotationPrev(leftLeg,runProgress,Maths.rad(-25),0,0,5F);
        progressRotationPrev(head,runProgress,Maths.rad(-30),0,0,5F);

        progressRotationPrev(body,flyProgress,Maths.rad(35),0,0,5F);
        progressRotationPrev(rightLeg,flyProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(leftLeg,flyProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(rightFoot,flyProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(leftFoot,flyProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(rightWing,flyProgress,Maths.rad(90),0,Maths.rad(-80),5F);
        progressRotationPrev(leftWing,flyProgress,Maths.rad(90),0,Maths.rad(80),5F);
        progressRotationPrev(head,flyProgress,Maths.rad(-20),0,0,5F);
        progressRotationPrev(tail,flyProgress,Maths.rad(10),0,0,5F);
        progressPositionPrev(tail,flyProgress,0,0,1,5F);

        if(flyProgress>0){
            this.flap(leftWing,flapSpeed,flapDegree*4,true,0F,0F,ageInTicks,1);
            this.flap(rightWing,flapSpeed,flapDegree*4,false,0F,0F,ageInTicks,1);
            this.bob(body,flapSpeed*0.5F,flapDegree*2,true,ageInTicks,1);
        }else{
            this.walk(rightLeg,walkSpeed,walkDegree*1.8F,false,0F,0.2F,limbSwing,limbSwingAmount);
            this.walk(leftLeg,walkSpeed,walkDegree*1.8F,true,0F,0.2F,limbSwing,limbSwingAmount);
            this.walk(head,walkSpeed,walkDegree*0.2F,false,2F,-0.01F,limbSwing,limbSwingAmount);
            this.flap(tail,walkSpeed,walkDegree*0.3F,false,1F,0F,limbSwing,limbSwingAmount);
            this.bob(body,walkSpeed,walkDegree*0.5F,true,limbSwing,limbSwingAmount);
        }

        this.faceTarget(netHeadYaw,headPitch,2,headPivot,head);
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

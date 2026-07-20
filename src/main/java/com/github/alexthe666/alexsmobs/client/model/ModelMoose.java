package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityMoose;
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

public class ModelMoose extends AdvancedEntityModel<EntityMoose> {
    private final AdvancedModelBox root,body,left_arm,right_arm,left_leg,right_leg,upper_body,neck,head,left_ear,right_ear,beard;
    private final ModelAnimator animator;

    public ModelMoose() {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-28.0F,0.0F);root.addChild(body);
        body.setTextureOffset(37,80).addBox(-6.0F,-8.0F,-4.0F,12.0F,15.0F,20.0F,0.0F,false);
        left_arm=new AdvancedModelBox(this,"left_arm");left_arm.setPos(4.7F,6.0F,-13.0F);body.addChild(left_arm);
        left_arm.setTextureOffset(19,58).addBox(-2.0F,1.0F,-2.0F,4.0F,21.0F,4.0F,0.0F,false);
        right_arm=new AdvancedModelBox(this,"right_arm");right_arm.setPos(-4.7F,6.0F,-13.0F);body.addChild(right_arm);
        right_arm.setTextureOffset(19,58).addBox(-2.0F,1.0F,-2.0F,4.0F,21.0F,4.0F,0.0F,true);
        left_leg=new AdvancedModelBox(this,"left_leg");left_leg.setPos(3.7F,6.0F,14.0F);body.addChild(left_leg);
        left_leg.setTextureOffset(0,58).addBox(-2.0F,1.0F,-3.0F,4.0F,21.0F,5.0F,0.0F,false);
        right_leg=new AdvancedModelBox(this,"right_leg");right_leg.setPos(-3.7F,6.0F,14.0F);body.addChild(right_leg);
        right_leg.setTextureOffset(0,58).addBox(-2.0F,1.0F,-3.0F,4.0F,21.0F,5.0F,0.0F,true);
        upper_body=new AdvancedModelBox(this,"upper_body");upper_body.setPos(0.0F,-1.0F,-4.0F);body.addChild(upper_body);
        upper_body.setTextureOffset(52,45).addBox(-7.0F,-10.0F,-13.0F,14.0F,18.0F,13.0F,0.0F,false);
        neck=new AdvancedModelBox(this,"neck");neck.setPos(0.0F,-6.0F,-14.0F);upper_body.addChild(neck);
        neck.setTextureOffset(45,0).addBox(-4.0F,-3.0F,-6.0F,8.0F,9.0F,7.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,0.0F,-7.0F);neck.addChild(head);
        head.setTextureOffset(51,18).addBox(-3.0F,-3.0F,-15.0F,6.0F,7.0F,16.0F,0.0F,false);
        head.setTextureOffset(0,34).addBox(3.0F,-12.0F,-7.0F,18.0F,9.0F,14.0F,0.0F,false);
        head.setTextureOffset(0,34).addBox(-21.0F,-12.0F,-7.0F,18.0F,9.0F,14.0F,0.0F,true);
        left_ear=new AdvancedModelBox(this,"left_ear");left_ear.setPos(1.3F,-3.0F,0.5F);head.addChild(left_ear);
        setRotationAngle(left_ear,-0.3054F,-0.2618F,0.3927F);left_ear.setTextureOffset(11,0).addBox(-0.3F,-4.0F,-0.5F,2.0F,4.0F,1.0F,0.0F,false);
        right_ear=new AdvancedModelBox(this,"right_ear");right_ear.setPos(-1.3F,-3.0F,0.5F);head.addChild(right_ear);
        setRotationAngle(right_ear,-0.3054F,0.2618F,-0.3927F);right_ear.setTextureOffset(11,0).addBox(-1.7F,-4.0F,-0.5F,2.0F,4.0F,1.0F,0.0F,true);
        beard=new AdvancedModelBox(this,"beard");beard.setPos(0.0F,4.0F,0.0F);head.addChild(beard);
        beard.setTextureOffset(0,0).addBox(0.0F,0.0F,-4.0F,0.0F,6.0F,5.0F,0.0F,false);
        animator=ModelAnimator.create();this.updateDefaultPose();
    }

    public void animate(IAnimatedEntity entity,float f,float f1,float f2,float f3,float f4){
        this.resetToDefaultPose();animator.update(entity);
        animator.setAnimation(EntityMoose.ANIMATION_EAT_GRASS);
        animator.startKeyframe(5);animator.rotate(neck,Maths.rad(50),0,0);animator.rotate(head,Maths.rad(4),0,0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck,Maths.rad(70),0,0);animator.rotate(head,Maths.rad(10),0,0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck,Maths.rad(50),0,0);animator.rotate(head,Maths.rad(0),0,0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck,Maths.rad(70),0,0);animator.rotate(head,Maths.rad(10),0,0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck,Maths.rad(50),0,0);animator.rotate(head,Maths.rad(0),0,0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck,Maths.rad(70),0,0);animator.rotate(head,Maths.rad(10),0,0);eatPose();animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityMoose.ANIMATION_ATTACK);
        animator.startKeyframe(8);eatPose();animator.rotate(neck,Maths.rad(50),0,0);animator.rotate(head,Maths.rad(10),0,0);animator.endKeyframe();
        animator.startKeyframe(3);animator.rotate(neck,Maths.rad(-34),0,0);animator.rotate(head,Maths.rad(-20),0,0);animator.endKeyframe();
        animator.resetKeyframe(4);
    }
    private void eatPose(){animator.rotate(body,Maths.rad(10),0,0);animator.move(body,0,2,0);animator.rotate(left_leg,Maths.rad(-10),0,0);animator.rotate(right_leg,Maths.rad(-10),0,0);animator.rotate(left_arm,Maths.rad(-10),0,Maths.rad(-10));animator.rotate(right_arm,Maths.rad(-10),0,Maths.rad(10));animator.move(left_arm,0.1F,-3,0F);animator.move(right_arm,-0.1F,-3,0F);animator.move(left_leg,0,-0.2F,0);animator.move(right_leg,0,-0.2F,0);animator.move(neck,0,1,0);}

    @Override
    public void setupAnim(EntityMoose entityIn,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        animate(entityIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        float walkSpeed=0.6F,walkDegree=0.65F,idleSpeed=0.09F,idleDegree=0.12F,runProgress=5F*limbSwingAmount;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float jostleProgress=entityIn.prevJostleProgress+(entityIn.jostleProgress-entityIn.prevJostleProgress)*partialTick;
        float jostleAngle=entityIn.prevJostleAngle+(entityIn.getJostleAngle()-entityIn.prevJostleAngle)*partialTick;

        //══════ 🫎 MOOSE — HIGH-STEPPING MARSH WADER ══════
        // IDENTITY: Tallest ungulate after giraffe. STILT-LIKE legs lift HIGH
        // each step — wades through deep snow and marsh. MASSIVE antlers act
        // like pendulum, swinging with each deliberate step.
        // UNIQUE vs Gazelle (stott), Bison (heavy grazer), Rhino (tank).

        // ── BREATHING: deep slow, visible in barrel chest ──
        float breath=Mth.cos(ageInTicks*0.06F);
        upper_body.setScale(1.0F,1.0F+breath*0.025F,1.0F);body.rotationPointY+=breath*0.25F;upper_body.rotationPointY+=breath*0.15F;

        // ── HIGH-STEPPING: moose lifts knees WAY up to wade ──
        // This is THE moose signature — exaggerated knee lift each step
        float lfKnee=Mth.abs(Mth.sin(limbSwing*walkSpeed))*walkDegree*3.5F*limbSwingAmount;
        float rtKnee=Mth.abs(Mth.cos(limbSwing*walkSpeed))*walkDegree*3.5F*limbSwingAmount;
        left_arm.rotationPointY+=lfKnee;right_arm.rotationPointY+=rtKnee;
        left_leg.rotationPointY+=lfKnee*0.7F;right_leg.rotationPointY+=rtKnee*0.7F;
        // Legs also swing forward with extra reach (stilt stride)
        left_arm.rotationPointZ+=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.5F*limbSwingAmount;
        right_arm.rotationPointZ+=Mth.cos(limbSwing*walkSpeed)*walkDegree*0.5F*limbSwingAmount;

        // ── ANTLER PENDULUM: massive weight above head sways ──
        // 40kg of antler creates significant inertia
        head.rotateAngleZ+=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.07F*limbSwingAmount;
        head.rotationPointZ+=Mth.sin(limbSwing*walkSpeed+0.3F)*walkDegree*0.6F*limbSwingAmount;
        upper_body.rotateAngleZ+=Mth.sin(limbSwing*walkSpeed+0.7F)*walkDegree*0.03F*limbSwingAmount;

        // ── BODY SWAY: lateral rock of a tall animal ──
        body.rotationPointX+=Mth.sin(limbSwing*walkSpeed*0.6F)*walkDegree*0.5F*limbSwingAmount;

        // ── WALK LEGS ──
        this.walk(body,walkSpeed,walkDegree*0.05F,true,0F,0F,limbSwing,limbSwingAmount);
        this.bob(body,walkSpeed,walkDegree*1.2F,true,limbSwing,limbSwingAmount);
        this.walk(neck,walkSpeed,walkDegree*0.25F,true,1F,0F,limbSwing,limbSwingAmount);
        this.walk(head,walkSpeed,-walkDegree*0.25F,true,1F,0F,limbSwing,limbSwingAmount);
        this.walk(right_arm,walkSpeed,walkDegree*1.1F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(left_arm,walkSpeed,walkDegree*1.1F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(right_leg,walkSpeed,walkDegree*1.1F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(left_leg,walkSpeed,walkDegree*1.1F,true,0F,0F,limbSwing,limbSwingAmount);

        // ── BEARD & EARS: lazy gentle sway ──
        this.flap(beard,idleSpeed,idleDegree*3,false,0F,0F,ageInTicks,1);
        this.flap(left_ear,idleSpeed,idleDegree,false,1F,-0.2F,ageInTicks,1);
        this.flap(right_ear,idleSpeed,idleDegree,true,1F,0.2F,ageInTicks,1);
        this.walk(neck,idleSpeed,idleDegree,false,0F,0F,ageInTicks,1);
        this.walk(head,idleSpeed,-idleDegree,false,0.5F,0F,ageInTicks,1);

        progressRotationPrev(neck,jostleProgress,Maths.rad(7),0,0,5F);
        progressRotationPrev(head,jostleProgress,Maths.rad(80),0,0,5F);
        progressPositionPrev(neck,jostleProgress,0,0,1,5F);progressPositionPrev(head,jostleProgress,0,0,-1,5F);
        if(jostleProgress>0){float ya=jostleAngle*Mth.DEG_TO_RAD*0.5F*jostleProgress*0.2F;neck.rotateAngleY+=ya;head.rotateAngleY+=ya;}
        else{this.faceTarget(netHeadYaw,headPitch,2,neck,head);}
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.35F,ft=1.45F;head.setScale(f,f,f);head.setShouldScaleChildren(true);right_arm.setScale(1,ft,1);left_arm.setScale(1,ft,1);right_leg.setScale(1,ft,1);left_leg.setScale(1,ft,1);ms.pushPose();ms.scale(0.35F,0.35F,0.35F);ms.translate(0.0D,2.25D,0.125D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();head.setScale(1,1,1);right_arm.setScale(1,1,1);left_arm.setScale(1,1,1);right_leg.setScale(1,1,1);left_leg.setScale(1,1,1);}
        else{ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,left_ear,right_ear,head,neck,body,upper_body,beard,left_leg,right_leg,left_arm,right_arm);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

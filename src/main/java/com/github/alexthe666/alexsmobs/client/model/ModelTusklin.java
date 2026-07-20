package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTusklin;
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

public class ModelTusklin extends AdvancedEntityModel<EntityTusklin> {
    private final AdvancedModelBox root,body,leg_left,leg_right,torso,arm_left,arm_right,head,tusk_left,tusk_right,ear_left,earLeft_r1,ear_right,earLeft_r2;
    private ModelAnimator animator;

    public ModelTusklin(){texWidth=128;texHeight=128;root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-18.0F,1.0F);root.addChild(body);body.setTextureOffset(52,56).addBox(-8.0F,-7.0F,0.0F,16.0F,15.0F,15.0F,0.0F,false);
        leg_left=new AdvancedModelBox(this,"leg_left");leg_left.setRotationPoint(6.0F,8.0F,13.0F);body.addChild(leg_left);leg_left.setTextureOffset(58,0).addBox(-3.0F,-1.0F,-3.0F,6.0F,11.0F,7.0F,0.0F,false);
        leg_right=new AdvancedModelBox(this,"leg_right");leg_right.setRotationPoint(-6.0F,8.0F,13.0F);body.addChild(leg_right);leg_right.setTextureOffset(58,0).addBox(-3.0F,-1.0F,-3.0F,6.0F,11.0F,7.0F,0.0F,true);
        torso=new AdvancedModelBox(this,"torso");torso.setRotationPoint(0.0F,0.0F,-1.0F);body.addChild(torso);torso.setTextureOffset(0,0).addBox(-9.0F,-11.0F,-20.0F,18.0F,20.0F,21.0F,0.0F,false);torso.setTextureOffset(0,0).addBox(0.0F,-16.0F,-20.0F,0.0F,5.0F,10.0F,0.0F,false);
        arm_left=new AdvancedModelBox(this,"arm_left");arm_left.setRotationPoint(5.5F,10.0F,-14.0F);torso.addChild(arm_left);arm_left.setTextureOffset(0,71).addBox(-3.0F,-1.0F,-3.0F,6.0F,9.0F,6.0F,0.0F,false);
        arm_right=new AdvancedModelBox(this,"arm_right");arm_right.setRotationPoint(-5.5F,10.0F,-14.0F);torso.addChild(arm_right);arm_right.setTextureOffset(0,71).addBox(-3.0F,-1.0F,-3.0F,6.0F,9.0F,6.0F,0.0F,true);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0.0F,-5.0F,-22.0F);torso.addChild(head);setRotationAngle(head,0.5236F,0.0F,0.0F);head.setTextureOffset(0,42).addBox(-7.0F,-3.0F,-17.0F,14.0F,9.0F,19.0F,0.0F,false);head.setTextureOffset(52,50).addBox(0.0F,6.0F,-16.0F,0.0F,3.0F,7.0F,0.0F,false);head.setTextureOffset(0,42).addBox(0.0F,-10.0F,-5.0F,0.0F,7.0F,7.0F,0.0F,false);
        tusk_left=new AdvancedModelBox(this,"tusk_left");tusk_left.setRotationPoint(8.0F,2.0F,-13.5F);head.addChild(tusk_left);tusk_left.setTextureOffset(48,42).addBox(-1.0F,-11.0F,-1.5F,2.0F,11.0F,3.0F,0.0F,false);tusk_left.setTextureOffset(59,42).addBox(-1.0F,-11.0F,1.5F,2.0F,3.0F,4.0F,0.0F,false);
        tusk_right=new AdvancedModelBox(this,"tusk_right");tusk_right.setRotationPoint(-8.0F,2.0F,-13.5F);head.addChild(tusk_right);tusk_right.setTextureOffset(48,42).addBox(-1.0F,-11.0F,-1.5F,2.0F,11.0F,3.0F,0.0F,true);tusk_right.setTextureOffset(59,42).addBox(-1.0F,-11.0F,1.5F,2.0F,3.0F,4.0F,0.0F,true);
        ear_left=new AdvancedModelBox(this,"ear_left");ear_left.setRotationPoint(7.0F,0.0F,-1.0F);head.addChild(ear_left);setRotationAngle(ear_left,0.0F,0.0F,-0.48F);
        earLeft_r1=new AdvancedModelBox(this,"earLeft_r1");earLeft_r1.setRotationPoint(1.0F,0.0F,2.0F);ear_left.addChild(earLeft_r1);setRotationAngle(earLeft_r1,-0.3927F,0.0F,0.0F);earLeft_r1.setTextureOffset(68,46).addBox(-1.0F,-1.0F,-3.0F,1.0F,5.0F,4.0F,0.0F,false);
        ear_right=new AdvancedModelBox(this,"ear_right");ear_right.setRotationPoint(-7.0F,0.0F,-1.0F);head.addChild(ear_right);setRotationAngle(ear_right,0.0F,0.0F,0.48F);
        earLeft_r2=new AdvancedModelBox(this,"earLeft_r2");earLeft_r2.setRotationPoint(-1.0F,0.0F,2.0F);ear_right.addChild(earLeft_r2);setRotationAngle(earLeft_r2,-0.3927F,0.0F,0.0F);earLeft_r2.setTextureOffset(68,46).addBox(0.0F,-1.0F,-3.0F,1.0F,5.0F,4.0F,0.0F,true);
        this.updateDefaultPose();animator=ModelAnimator.create();}

    public void animate(IAnimatedEntity e,float f,float f1,float f2,float f3,float f4){animator.update(e);
        animator.setAnimation(EntityTusklin.ANIMATION_RUT);
        for(int i=0;i<3;i++){animator.startKeyframe(4);animator.move(head,0,4,0);animator.rotate(head,Maths.rad(30),0,0);animator.rotate(ear_left,0,0,Maths.rad(-30));animator.rotate(ear_right,0,0,Maths.rad(30));animator.endKeyframe();animator.startKeyframe(2);animator.move(head,-1,3,0);animator.rotate(head,Maths.rad(40),Maths.rad(i%2==0?30:-30),0);animator.endKeyframe();}
        animator.resetKeyframe(4);
        animator.setAnimation(EntityTusklin.ANIMATION_GORE_L);
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(leg_right,Maths.rad(-30),0,0);animator.rotate(leg_left,Maths.rad(-30),0,0);animator.rotate(arm_right,Maths.rad(-30),0,0);animator.rotate(arm_left,Maths.rad(-30),0,0);animator.move(head,0,2,-2);animator.rotate(head,Maths.rad(40),Maths.rad(-45),Maths.rad(-90));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,-2);animator.rotate(head,Maths.rad(-20),Maths.rad(45),Maths.rad(60));animator.endKeyframe();animator.setStaticKeyframe(5);animator.resetKeyframe(5);
        animator.setAnimation(EntityTusklin.ANIMATION_GORE_R);
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(leg_right,Maths.rad(-30),0,0);animator.rotate(leg_left,Maths.rad(-30),0,0);animator.rotate(arm_right,Maths.rad(-30),0,0);animator.rotate(arm_left,Maths.rad(-30),0,0);animator.move(head,0,2,-2);animator.rotate(head,Maths.rad(40),Maths.rad(45),Maths.rad(90));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,-2);animator.rotate(head,Maths.rad(-20),Maths.rad(-45),Maths.rad(-60));animator.endKeyframe();animator.setStaticKeyframe(5);animator.resetKeyframe(5);
        animator.setAnimation(EntityTusklin.ANIMATION_FLING);
        animator.startKeyframe(5);animator.move(body,0,1,-2);animator.move(arm_left,0,-1,0);animator.move(arm_right,0,-1,0);animator.move(leg_right,0,1,0);animator.move(leg_left,0,1,0);animator.rotate(body,Maths.rad(10),0,0);animator.rotate(leg_left,Maths.rad(-10),0,0);animator.rotate(leg_right,Maths.rad(-10),0,0);animator.rotate(head,Maths.rad(20),0,0);animator.rotate(arm_right,Maths.rad(-50),0,Maths.rad(20));animator.rotate(arm_left,Maths.rad(-50),0,Maths.rad(-20));animator.endKeyframe();
        animator.startKeyframe(3);animator.rotate(head,Maths.rad(-60),0,0);animator.endKeyframe();animator.setStaticKeyframe(2);animator.resetKeyframe(5);
        animator.setAnimation(EntityTusklin.ANIMATION_BUCK);
        animator.startKeyframe(5);animator.move(body,0,-5,3);animator.rotate(body,Maths.rad(-30),0,0);animator.rotate(leg_left,Maths.rad(30),0,0);animator.rotate(leg_right,Maths.rad(30),0,0);animator.rotate(head,Maths.rad(-40),0,0);animator.rotate(arm_right,Maths.rad(30),0,Maths.rad(-10));animator.rotate(arm_left,Maths.rad(30),0,Maths.rad(10));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,-5,-2);animator.rotate(body,Maths.rad(20),0,0);animator.rotate(leg_left,Maths.rad(-20),0,0);animator.rotate(leg_right,Maths.rad(-20),0,0);animator.rotate(head,Maths.rad(-60),0,0);animator.rotate(arm_right,Maths.rad(-20),0,Maths.rad(-10));animator.rotate(arm_left,Maths.rad(-20),0,Maths.rad(10));animator.endKeyframe();animator.resetKeyframe(5);}

    @Override
    public void setupAnim(EntityTusklin entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();animate(entity,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        float walkSpeed=0.75F,walkDegree=0.65F,idleSpeed=0.1F,idleDegree=0.4F;
        if(this.young){this.head.rotationPointY-=4.0F;this.head.rotationPointZ+=2.0F;}

        //══════ 🐗 TUSKLIN — SNOUT-ROOTING TUSKED BOAR ══════
        // IDENTITY: Aggressive rooter. Snout CONSTANTLY to ground digging.
        // Massive tusks make head heavy — shakes head side-to-side. Bristly
        // aggressive energy. Charges with tusks lowered.
        // UNIQUE vs all other ungulates: rooter, NOT grazer.

        // ── BREATHING: bristly boar grunt ──
        float breath=Mth.cos(ageInTicks*0.09F);
        body.setScale(1.0F,1.0F+breath*0.015F,1.0F);body.rotationPointY+=breath*0.15F;torso.rotationPointY+=breath*0.1F;

        // ── SNOUT-ROOTING: boar constantly digs ground with snout ──
        this.walk(head,idleSpeed*0.4F,idleDegree*0.2F,true,1F,-0.01F,ageInTicks,1);
        if(limbSwingAmount<0.05F){
            head.rotateAngleX-=Mth.sin(ageInTicks*0.3F)*0.1F; // snout digs
            head.rotationPointZ+=Mth.sin(ageInTicks*0.25F)*0.15F; // forward dig
            // Tusks scrape ground
            tusk_left.rotateAngleX+=Mth.sin(ageInTicks*0.3F+0.5F)*0.05F;
            tusk_right.rotateAngleX+=Mth.sin(ageInTicks*0.3F+0.5F)*0.05F;
        }

        // ── TUSK-HEAVY HEAD SHAKE: massive tusks create inertia ──
        head.rotateAngleZ+=Mth.sin(limbSwing*walkSpeed+1.2F)*walkDegree*0.06F*limbSwingAmount;
        head.rotationPointX+=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.2F*limbSwingAmount;

        // ── EARS: bristly, quick flick ──
        this.flap(ear_right,idleSpeed*0.7F,idleDegree*0.2F,false,0F,-0.01F,ageInTicks,1);
        this.flap(ear_left,idleSpeed*0.7F,idleDegree*0.2F,true,0F,-0.01F,ageInTicks,1);

        // ── WALK: short powerful legs, aggressive stride ──
        this.walk(leg_left,walkSpeed,walkDegree*1.6F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(leg_right,walkSpeed,walkDegree*1.6F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(arm_left,walkSpeed,walkDegree*1.6F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(arm_right,walkSpeed,walkDegree*1.6F,false,0F,0F,limbSwing,limbSwingAmount);
        this.bob(body,walkSpeed,walkDegree*3.5F,true,limbSwing,limbSwingAmount);
        this.bob(head,walkSpeed*0.6F,walkDegree*2F,true,limbSwing,limbSwingAmount);

        if(entity.getAnimation()==IAnimatedEntity.NO_ANIMATION){this.faceTarget(netHeadYaw,headPitch,1,head);}
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.6F,f1=2.2F,f2=1.4F;ear_left.setScale(f1,f1,f1);ear_right.setScale(f1,f1,f1);head.setScale(f,f,f);arm_left.setScale(1,f2,1);arm_right.setScale(1,f2,1);leg_left.setScale(1,f2,1);leg_right.setScale(1,f2,1);head.setShouldScaleChildren(true);tusk_left.showModel=false;tusk_right.showModel=false;ms.pushPose();ms.scale(0.45F,0.45F,0.45F);ms.translate(0.0D,1.6D,0.125D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();head.setScale(1,1,1);ear_left.setScale(1,1,1);ear_right.setScale(1,1,1);arm_left.setScale(1,1,1);arm_right.setScale(1,1,1);leg_left.setScale(1,1,1);leg_right.setScale(1,1,1);}
        else{tusk_left.showModel=true;tusk_right.showModel=true;ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,leg_left,leg_right,torso,arm_left,arm_right,head,tusk_left,tusk_right,earLeft_r1,ear_left,ear_right,earLeft_r2);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

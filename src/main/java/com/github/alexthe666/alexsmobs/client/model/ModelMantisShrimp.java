package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelMantisShrimp extends AdvancedEntityModel<EntityMantisShrimp> {
    public final AdvancedModelBox root, body, tail, legs_front, legs_back, head, flapper_left, flapper_right, eye_left, eye_right, arm_left, fist_left, arm_right, fist_right, whisker_left, whisker_right;

    public ModelMantisShrimp() {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-14,-5);root.addChild(body);body.setTextureOffset(0,0).addBox(-6,-2,0,12,10,25,0,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0,0,21);body.addChild(tail);setRotationAngle(tail,-0.2182F,0,0);tail.setTextureOffset(50,0).addBox(-7,0,0,14,9,9,0,false);
        legs_front=new AdvancedModelBox(this,"lf");legs_front.setPos(0,5,-7);body.addChild(legs_front);legs_front.setTextureOffset(0,61).addBox(-5,0,0,10,9,8,0,false);
        legs_back=new AdvancedModelBox(this,"lb");legs_back.setPos(0,8,1);body.addChild(legs_back);legs_back.setTextureOffset(0,36).addBox(-5,0,0,10,6,18,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,4,2);body.addChild(head);setRotationAngle(head,0.3491F,0,0);head.setTextureOffset(49,53).addBox(-6,-14,-8,12,14,8,0.1F,false);
        flapper_left=new AdvancedModelBox(this,"fl");flapper_left.setPos(4,-14,-8);head.addChild(flapper_left);setRotationAngle(flapper_left,-0.48F,0.2182F,0);flapper_left.setTextureOffset(50,19).addBox(0,0,0,14,5,0,0,false);
        flapper_right=new AdvancedModelBox(this,"fr");flapper_right.setPos(-4,-14,-8);head.addChild(flapper_right);setRotationAngle(flapper_right,-0.48F,-0.2182F,0);flapper_right.setTextureOffset(50,19).addBox(-14,0,0,14,5,0,0,true);
        eye_left=new AdvancedModelBox(this,"el");eye_left.setPos(3,-14,-4);head.addChild(eye_left);eye_left.setTextureOffset(0,15).addBox(-2,-4,-2,4,4,4,0,false);
        eye_right=new AdvancedModelBox(this,"er");eye_right.setPos(-3,-14,-4);head.addChild(eye_right);eye_right.setTextureOffset(0,15).addBox(-2,-4,-2,4,4,4,0,true);
        arm_left=new AdvancedModelBox(this,"al");arm_left.setPos(4,-1,-8);head.addChild(arm_left);arm_left.setTextureOffset(0,36).addBox(-2.5F,-9,-2,5,10,3,0,false);
        fist_left=new AdvancedModelBox(this,"fl");fist_left.setPos(-1,-7,-1);arm_left.addChild(fist_left);fist_left.setTextureOffset(0,0).addBox(-1,-1,-4,4,10,4,0,false);
        arm_right=new AdvancedModelBox(this,"ar");arm_right.setPos(-4,-1,-8);head.addChild(arm_right);arm_right.setTextureOffset(0,36).addBox(-2.5F,-9,-2,5,10,3,0,true);
        fist_right=new AdvancedModelBox(this,"fr2");fist_right.setPos(1,-7,-1);arm_right.addChild(fist_right);fist_right.setTextureOffset(0,0).addBox(-3,-1,-4,4,10,4,0,true);
        whisker_left=new AdvancedModelBox(this,"wl");whisker_left.setPos(1,-14,-8);head.addChild(whisker_left);setRotationAngle(whisker_left,0,-0.3927F,0);whisker_left.setTextureOffset(39,39).addBox(0,0,-13,8,0,13,0,false);
        whisker_right=new AdvancedModelBox(this,"wr");whisker_right.setPos(-1,-14,-8);head.addChild(whisker_right);setRotationAngle(whisker_right,0,0.3927F,0);whisker_right.setTextureOffset(39,39).addBox(-8,0,-13,8,0,13,0,true);
        this.updateDefaultPose();
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,head,eye_left,eye_right,fist_left,fist_right,arm_left,arm_right,whisker_left,whisker_right,flapper_left,flapper_right,tail,legs_back,legs_front);}

    public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){eye_left.setScale(1.15F,1.15F,1.15F);eye_right.setScale(1.15F,1.15F,1.15F);m.pushPose();m.scale(0.5F,0.5F,0.5F);m.translate(0,1.5,0.125);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
        else{eye_left.setScale(1,1,1);eye_right.setScale(1,1,1);m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }

    @Override
    public void setupAnim(EntityMantisShrimp entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🦐 MANTIS SHRIMP — SPRING-LOADED PUNCHER ══════
        // IDENTITY: Two-stage punch: SLOW cock → INSTANT strike. Eyes
        // scan independently on stalks. Rainbow iridescent body shimmer.
        // Flappers spread during threat. Impact recoil after punch.
        float idleSpeed=0.1f,idleDegree=0.3f,walkSpeed=0.9f,walkDegree=0.6F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float swimProgress=(Math.min(limbSwingAmount,0.25F)*4F)*(entity.prevInWaterProgress+(entity.inWaterProgress-entity.prevInWaterProgress)*partialTick);
        float punchProgress=entity.prevPunchProgress+(entity.punchProgress-entity.prevPunchProgress)*partialTick;
        float leftEyePitch=entity.prevLeftPitch+(entity.getEyePitch(true)-entity.prevLeftPitch)*partialTick;
        float rightEyePitch=entity.prevRightPitch+(entity.getEyePitch(false)-entity.prevRightPitch)*partialTick;
        float leftEyeYaw=entity.prevLeftYaw+(entity.getEyeYaw(true)-entity.prevLeftYaw)*partialTick;
        float rightEyeYaw=entity.prevRightYaw+(entity.getEyeYaw(false)-entity.prevRightYaw)*partialTick;

        // ── AAA EYE TRACKING (preserved) ────────────────────────────
        this.eye_left.rotateAngleX+=leftEyePitch*Mth.DEG_TO_RAD;
        this.eye_left.rotateAngleY+=leftEyeYaw*Mth.DEG_TO_RAD;
        this.eye_right.rotateAngleX+=rightEyePitch*Mth.DEG_TO_RAD;
        this.eye_right.rotateAngleY+=rightEyeYaw*Mth.DEG_TO_RAD;
        // AAA EYE AUTONOMOUS SCANNING when not tracking/punching
        if(punchProgress==0&&swimProgress==0){
            eye_left.rotateAngleY+=Mth.sin(ageInTicks*0.18F)*0.2F;eye_left.rotateAngleX+=Mth.sin(ageInTicks*0.13F+1F)*0.1F;
            eye_right.rotateAngleY+=Mth.sin(ageInTicks*0.22F+1.5F)*0.2F;eye_right.rotateAngleX+=Mth.sin(ageInTicks*0.15F+2.5F)*0.1F;
        }

        // ── AAA TWO-STAGE PUNCH ────────────────────────────────────
        // Stage 1 (progress 0-3): SLOW cocking — clubs retract
        // Stage 2 (progress 3-5): INSTANT strike — clubs fire forward
        float cockPhase=Mth.clamp(punchProgress/3F,0F,1F);
        float strikePhase=Mth.clamp((punchProgress-3F)/2F,0F,1F);
        progressPositionPrev(arm_right,punchProgress,1,-7,0,2F);
        progressPositionPrev(arm_left,punchProgress,-1,-7,0,2F);
        progressPositionPrev(fist_right,punchProgress,0,-2,-2,2F);
        progressPositionPrev(fist_left,punchProgress,0,-2,-2,2F);
        // Cock: arm rotates up slowly, fist curls back
        progressRotationPrev(arm_right,cockPhase*3F,Maths.rad(40),0,Maths.rad(-10),5F);
        progressRotationPrev(arm_left,cockPhase*3F,Maths.rad(40),0,Maths.rad(10),5F);
        progressRotationPrev(fist_right,cockPhase*3F,Maths.rad(-90),0,Maths.rad(15),5F);
        progressRotationPrev(fist_left,cockPhase*3F,Maths.rad(-90),0,Maths.rad(-15),5F);
        // Strike: arm fires forward, fist extends rapidly
        progressRotationPrev(arm_right,strikePhase*2F,Maths.rad(-30),0,0,5F);
        progressRotationPrev(arm_left,strikePhase*2F,Maths.rad(-30),0,0,5F);
        progressRotationPrev(fist_right,strikePhase*2F,Maths.rad(150),0,Maths.rad(-5),5F);
        progressRotationPrev(fist_left,strikePhase*2F,Maths.rad(150),0,Maths.rad(5),5F);
        // Impact recoil: arms bounce back after strike
        if(punchProgress>4F){
            float recoil=Mth.sin((punchProgress-4F)*8F)*0.1F;
            arm_right.rotateAngleX-=recoil;arm_left.rotateAngleX-=recoil;
        }
        // AAA FLAPPER THREAT DISPLAY during punch
        if(punchProgress>0){flapper_left.rotateAngleZ+=0.3F;flapper_right.rotateAngleZ-=0.3F;}

        // ── AAA RAINBOW SHIMMER ────────────────────────────────────
        body.setScale(1+Mth.sin(ageInTicks*0.17F)*0.008F,1+Mth.cos(ageInTicks*0.13F)*0.006F,1+Mth.sin(ageInTicks*0.11F+1.5F)*0.007F);

        this.head.rotateAngleY+=netHeadYaw*0.5F*Mth.DEG_TO_RAD;
        this.head.rotateAngleX+=headPitch*0.8F*Mth.DEG_TO_RAD;

        // ── AAA WHISKER DIFFERENTIATION ────────────────────────────
        this.walk(whisker_left,idleSpeed*1.5F,idleDegree,false,0F,-0.25F,ageInTicks,1);
        this.walk(whisker_right,idleSpeed*1.5F,idleDegree,true,0F,0.25F,ageInTicks,1);
        this.swing(whisker_left,idleSpeed,idleDegree*0.8F,false,1F,0F,ageInTicks,1);
        this.swing(whisker_right,idleSpeed*0.85F,idleDegree*0.7F,false,1.3F,0F,ageInTicks,1);

        // ── Idle ────────────────────────────────────────────────────
        this.swing(flapper_left,idleSpeed,idleDegree*0.75F,false,2F,-0.3F,ageInTicks,1);
        this.swing(flapper_right,idleSpeed,idleDegree*0.75F,true,2F,-0.3F,ageInTicks,1);
        this.swing(arm_left,idleSpeed,idleDegree*0.5F,false,1F,-0.2F,ageInTicks,1);
        this.swing(arm_right,idleSpeed,idleDegree*0.5F,true,1F,-0.2F,ageInTicks,1);

        // ── Walk ────────────────────────────────────────────────────
        this.walk(legs_front,walkSpeed,walkDegree,true,2,0,limbSwing,limbSwingAmount);
        this.bob(legs_front,walkSpeed*0.5F,walkDegree*4F,true,limbSwing,limbSwingAmount);
        this.walk(legs_back,walkSpeed,walkDegree*0.2F,true,2,0,limbSwing,limbSwingAmount);
        this.bob(legs_back,walkSpeed*0.5F,walkDegree*4F,true,limbSwing,limbSwingAmount);

        // ── Swim state (preserved) ──────────────────────────────────
        progressRotationPrev(head,Math.max(0,swimProgress-punchProgress*2.5F),Maths.rad(45),0,0,5F);
        progressRotationPrev(whisker_left,swimProgress,Maths.rad(-45),0,0,5F);
        progressRotationPrev(whisker_right,swimProgress,Maths.rad(-45),0,0,5F);
        progressRotationPrev(arm_left,swimProgress,Maths.rad(20),0,0,5F);
        progressRotationPrev(arm_right,swimProgress,Maths.rad(20),0,0,5F);
        progressPositionPrev(head,swimProgress,0,-6,0,5F);
        progressPositionPrev(arm_left,swimProgress,0,-3,0,5F);
        progressPositionPrev(arm_right,swimProgress,0,-3,0,5F);
        if(swimProgress>0){
            this.bob(body,walkSpeed*0.5F,walkDegree*4F,true,limbSwing,limbSwingAmount);
            this.walk(body,walkSpeed,walkDegree*0.2F,true,3F,0F,limbSwing,limbSwingAmount);
            this.walk(tail,walkSpeed,walkDegree*0.5F,true,3F,-0.2F,limbSwing,limbSwingAmount);
            this.walk(head,walkSpeed,walkDegree*0.1F,true,2F,0F,limbSwing,limbSwingAmount);
        }

        // ── AAA BREATHING ──────────────────────────────────────────
        float breath=Mth.cos(ageInTicks*0.1F);
        body.rotationPointY+=breath*0.06F;

        // ── AAA FLAPPER RIPPLE ─────────────────────────────────────
        this.flap(flapper_left,idleSpeed*1.5F,idleDegree*0.3F,false,1,0.05F,ageInTicks,swimProgress>0?swimProgress:0.15F);
        this.flap(flapper_right,idleSpeed*1.5F,idleDegree*0.3F,true,1,0.05F,ageInTicks,swimProgress>0?swimProgress:0.15F);
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

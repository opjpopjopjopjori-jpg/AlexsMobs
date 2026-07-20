package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelPlatypus extends AdvancedEntityModel<EntityPlatypus> {
    private final AdvancedModelBox root, body, head, beak, fedora, arm_left, arm_right, leg_left, leg_right, tail;

    public ModelPlatypus() {
        texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-2.6F,-0.1F);root.addChild(body);body.setTextureOffset(0,2).addBox(-3.5F,-3.4F,-5.9F,7,6,11,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,-0.4F,-5.9F);body.addChild(head);head.setTextureOffset(5,51).addBox(-3,-2.5F,-4,6,5,4,0,false);
        beak=new AdvancedModelBox(this,"beak");beak.setPos(0,2,-5);head.addChild(beak);beak.setTextureOffset(28,0).addBox(-2,-2,-3,4,2,4,0,false);
        fedora=new AdvancedModelBox(this,"fedora");fedora.setPos(0,-2.6F,-1.9F);head.addChild(fedora);fedora.setTextureOffset(23,20).addBox(-4,0,-4,8,0,8,0,false);fedora.setTextureOffset(29,30).addBox(-2,-2,-2,4,2,4,0,false);
        arm_left=new AdvancedModelBox(this,"al");arm_left.setPos(3.5F,2.1F,-4.9F);body.addChild(arm_left);arm_left.setTextureOffset(7,39).addBox(0,-0.5F,0,3,1,3,0,false);
        arm_right=new AdvancedModelBox(this,"ar");arm_right.setPos(-3.5F,2.1F,-4.9F);body.addChild(arm_right);arm_right.setTextureOffset(7,39).addBox(-3,-0.5F,0,3,1,3,0,true);
        leg_left=new AdvancedModelBox(this,"ll");leg_left.setPos(3.5F,2.1F,2.6F);body.addChild(leg_left);leg_left.setTextureOffset(27,43).addBox(0,-0.5F,-0.5F,3,1,3,0,false);
        leg_right=new AdvancedModelBox(this,"lr");leg_right.setPos(-3.5F,2.1F,2.6F);body.addChild(leg_right);leg_right.setTextureOffset(27,43).addBox(-3,-0.5F,-0.5F,3,1,3,0,true);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0,-0.4F,5.1F);body.addChild(tail);tail.setTextureOffset(0,24).addBox(-3,-1,0,6,3,8,0,false);
        this.updateDefaultPose();
    }

    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,head,beak,fedora,arm_left,arm_right,leg_left,leg_right);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}

    @Override
    public void setupAnim(EntityPlatypus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🦆 PLATYPUS — BILL-SWEEPING ELECTRO-FORAGER ══════
        // IDENTITY: Bill sweeps side-to-side like metal detector for
        // electroreception. Front webbed feet primary paddles (70%),
        // rear clawed feet rudders (30%). Tail stores fat, jiggles.
        // UNIQUE: Only creature with independent bill-sweep bone.

        float walkSpeed=1F,walkDegree=1.3F,idleSpeed=0.3F,idleDegree=0.2F,swimSpeed=1.3F,swimDegree=1.3F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float digProgress=entity.prevDigProgress+(entity.digProgress-entity.prevDigProgress)*partialTick;
        float swimProgress=entity.prevInWaterProgress+(entity.inWaterProgress-entity.prevInWaterProgress)*partialTick;
        boolean sensing=entity.isSensing()||entity.isSensingVisual();

        // ── AAA BREATHING (was COMPLETELY MISSING) ──────────────────
        float breath=Mth.cos(ageInTicks*0.12F);
        body.setScale(1+breath*0.015F,1+breath*0.02F,1+breath*0.015F);
        body.rotationPointY+=breath*0.05F;
        head.rotationPointY+=breath*0.02F;

        // ── Swim/dig transitions (preserved) ────────────────────────
        progressPositionPrev(body,swimProgress,0,-3.5F,0,5f);
        progressRotationPrev(arm_left,swimProgress,Maths.rad(-5),0,Maths.rad(75),5f);
        progressRotationPrev(arm_right,swimProgress,Maths.rad(-5),0,Maths.rad(-75),5f);
        progressRotationPrev(leg_left,swimProgress,Maths.rad(-5),0,Maths.rad(75),5f);
        progressRotationPrev(leg_right,swimProgress,Maths.rad(-5),0,Maths.rad(-75),5f);
        progressRotationPrev(tail,swimProgress,Maths.rad(-10),0,0,5f);
        progressPositionPrev(body,digProgress,0,-1.5F,0,5f);
        progressPositionPrev(arm_right,digProgress,1,-1,-0.5F,5f);
        progressPositionPrev(arm_left,digProgress,-1,-1,-0.5F,5f);
        progressRotationPrev(body,digProgress,Maths.rad(35),0,0,5f);
        progressRotationPrev(tail,digProgress,Maths.rad(10),0,0,5f);
        progressRotationPrev(head,digProgress,Maths.rad(-20),0,0,5f);
        progressRotationPrev(arm_left,digProgress,Maths.rad(-30),Maths.rad(10),Maths.rad(-65),5f);
        progressRotationPrev(arm_right,digProgress,Maths.rad(-30),Maths.rad(-10),Maths.rad(65),5f);

        // ── AAA BILL-SWEEP: Electroreception ────────────────────────
        if(sensing){
            beak.rotateAngleY=Mth.sin(ageInTicks*0.2F)*0.35F;
            head.rotateAngleY+=netHeadYaw*0.3F*Mth.DEG_TO_RAD;
        }

        // ── AAA TAIL FAT JIGGLE ────────────────────────────────────
        tail.rotateAngleX+=Mth.sin(ageInTicks*0.08F)*0.04F;
        tail.rotateAngleZ+=Mth.sin(limbSwing*walkSpeed+2F)*0.05F*limbSwingAmount;
        tail.rotationPointY+=breath*0.02F;

        // ── AAA FEDORA BOB ─────────────────────────────────────────
        fedora.rotationPointY+=Mth.sin(ageInTicks*0.1F)*0.03F;

        // ── Dig state (preserved) ──────────────────────────────────
        if(digProgress>0F){
            this.swing(body,0.8F,idleDegree*1.2F,false,3F,0F,ageInTicks,1);
            this.swing(head,0.8F,idleDegree*0.7F,false,3F,0F,ageInTicks,1);
            this.swing(arm_right,0.8F,idleDegree*2.6F,false,1F,-0.25F,ageInTicks,1);
            this.swing(arm_left,0.8F,idleDegree*2.6F,true,1F,-0.25F,ageInTicks,1);
        }else if(!sensing){
            this.faceTarget(netHeadYaw,headPitch,1.2F,head);
        }

        // ── Swim vs Walk ───────────────────────────────────────────
        if(swimProgress>0F){
            // AAA ASYMMETRIC SWIM: Front arms 70% power, rear 30%
            this.bob(body,idleSpeed,idleDegree*2F,false,ageInTicks,1);
            this.walk(tail,swimSpeed,swimDegree*0.1F,false,3F,0.25F,limbSwing,limbSwingAmount);
            this.swing(tail,swimSpeed,swimDegree*0.5F,false,2F,0F,limbSwing,limbSwingAmount);
            this.swing(body,swimSpeed,swimDegree*0.3F,false,3F,0F,limbSwing,limbSwingAmount);
            this.swing(head,swimSpeed,swimDegree*0.5F,true,3F,0F,limbSwing,limbSwingAmount);
            this.walk(body,swimSpeed,swimDegree*0.2F,false,0F,0F,limbSwing,limbSwingAmount);
            // Front webbed feet — PRIMARY paddles (1.3x)
            this.flap(arm_right,swimSpeed,swimDegree*1.3F,false,1F,0.85F,limbSwing,limbSwingAmount);
            this.flap(arm_left,swimSpeed,swimDegree*1.3F,true,1F,0.85F,limbSwing,limbSwingAmount);
            // Rear clawed feet — RUDDERS (0.6x)
            this.flap(leg_right,swimSpeed,swimDegree*0.6F,false,3F,0.5F,limbSwing,limbSwingAmount);
            this.flap(leg_left,swimSpeed,swimDegree*0.6F,true,3F,0.5F,limbSwing,limbSwingAmount);
            // Rear foot counter-steer
            leg_left.rotateAngleY+=Mth.sin(limbSwing*swimSpeed+1.5F)*0.08F*limbSwingAmount;
            leg_right.rotateAngleY-=Mth.sin(limbSwing*swimSpeed+1.5F)*0.08F*limbSwingAmount;
        }else{
            // AAA DIAGONAL WALK: Front/rear differentiated stride
            this.swing(tail,idleSpeed*0.5F,idleDegree,false,0F,0F,ageInTicks,1);
            this.bob(body,walkSpeed*1.75F,walkDegree*1F,false,limbSwing,limbSwingAmount);
            this.swing(body,walkSpeed,walkDegree*0.3F,false,3F,0F,limbSwing,limbSwingAmount);
            this.swing(head,walkSpeed,walkDegree*0.2F,true,3F,0F,limbSwing,limbSwingAmount);
            this.walk(tail,walkSpeed,walkDegree*0.3F,false,3F,0.1F,limbSwing,limbSwingAmount);
            // Front arms — shorter stride (0.8x)
            this.swing(arm_left,walkSpeed,walkDegree*0.8F,true,1F,0.15F,limbSwing,limbSwingAmount);
            this.flap(arm_left,walkSpeed,walkDegree*0.6F,true,0F,0.25F,limbSwing,limbSwingAmount);
            this.swing(arm_right,walkSpeed,walkDegree*0.8F,true,1F,-0.15F,limbSwing,limbSwingAmount);
            this.flap(arm_right,walkSpeed,walkDegree*0.6F,true,0F,-0.25F,limbSwing,limbSwingAmount);
            // Rear legs — longer stride (1.2x)
            this.swing(leg_left,walkSpeed,walkDegree*1.2F,false,1F,-0.15F,limbSwing,limbSwingAmount);
            this.flap(leg_left,walkSpeed,walkDegree*0.8F,false,0F,-0.15F,limbSwing,limbSwingAmount);
            this.swing(leg_right,walkSpeed,walkDegree*1.2F,false,1F,0.15F,limbSwing,limbSwingAmount);
            this.flap(leg_right,walkSpeed,walkDegree*0.8F,false,0F,0.15F,limbSwing,limbSwingAmount);
        }
    }

    @Override public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.65F;head.setScale(f,f,f);head.setShouldScaleChildren(true);m.pushPose();m.scale(0.5F,0.5F,0.5F);m.translate(0,1.5,0);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();head.setScale(1,1,1);}
        else{m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

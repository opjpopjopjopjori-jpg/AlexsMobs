package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelSoulVulture extends AdvancedEntityModel<EntitySoulVulture> {
    private final AdvancedModelBox root,body,leftWing,rightWing,heart,leftLeg,leftFoot,rightLeg,rightFoot,neck,head;

    public ModelSoulVulture(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-5.0F,4.0F);root.addChild(body);
        setRotationAngle(body,-0.3054F,0.0F,0.0F);
        body.setTextureOffset(0,15).addBox(-3.0F,-6.0F,-9.0F,6.0F,6.0F,10.0F,0.0F,false);
        body.setTextureOffset(26,25).addBox(-3.5F,-6.5F,-9.2F,7.0F,6.0F,7.0F,0.0F,false);
        leftWing=new AdvancedModelBox(this,"leftWing");leftWing.setRotationPoint(3.0F,-2.0F,-7.0F);body.addChild(leftWing);
        setRotationAngle(leftWing,1.1345F,-1.3265F,0.3491F);
        leftWing.setTextureOffset(36,15).addBox(-1.0F,-1.0F,-1.0F,7.0F,2.0F,2.0F,0.0F,false);
        leftWing.setTextureOffset(0,0).addBox(-1.0F,0.0F,0.0F,24.0F,0.0F,14.0F,0.0F,false);
        rightWing=new AdvancedModelBox(this,"rightWing");rightWing.setRotationPoint(-3.0F,-2.0F,-7.0F);body.addChild(rightWing);
        setRotationAngle(rightWing,1.1345F,1.3265F,-0.3491F);
        rightWing.setTextureOffset(36,15).addBox(-6.0F,-1.0F,-1.0F,7.0F,2.0F,2.0F,0.0F,true);
        rightWing.setTextureOffset(0,0).addBox(-23.0F,0.0F,0.0F,24.0F,0.0F,14.0F,0.0F,true);
        heart=new AdvancedModelBox(this,"heart");heart.setRotationPoint(0.0F,0.0F,-6.0F);body.addChild(heart);
        heart.setTextureOffset(0,15).addBox(-1.0F,-2.0F,-1.0F,2.0F,2.0F,2.0F,0.0F,false);
        leftLeg=new AdvancedModelBox(this,"leftLeg");leftLeg.setRotationPoint(3.0F,0.0F,0.0F);body.addChild(leftLeg);
        setRotationAngle(leftLeg,0.7418F,0.0F,0.0F);
        leftLeg.setTextureOffset(0,6).addBox(-1.0F,-1.0F,-4.0F,1.0F,4.0F,4.0F,0.0F,false);
        leftFoot=new AdvancedModelBox(this,"leftFoot");leftFoot.setRotationPoint(0.0F,3.0F,-4.0F);leftLeg.addChild(leftFoot);
        setRotationAngle(leftFoot,-0.4363F,0.0F,0.0F);
        leftFoot.setTextureOffset(0,0).addBox(-2.0F,0.0F,-2.0F,3.0F,2.0F,3.0F,0.0F,false);
        rightLeg=new AdvancedModelBox(this,"rightLeg");rightLeg.setRotationPoint(-3.0F,0.0F,0.0F);body.addChild(rightLeg);
        setRotationAngle(rightLeg,0.7418F,0.0F,0.0F);
        rightLeg.setTextureOffset(0,6).addBox(0.0F,-1.0F,-4.0F,1.0F,4.0F,4.0F,0.0F,true);
        rightFoot=new AdvancedModelBox(this,"rightFoot");rightFoot.setRotationPoint(0.0F,3.0F,-4.0F);rightLeg.addChild(rightFoot);
        setRotationAngle(rightFoot,-0.4363F,0.0F,0.0F);
        rightFoot.setTextureOffset(0,0).addBox(-1.0F,0.0F,-2.0F,3.0F,2.0F,3.0F,0.0F,true);
        neck=new AdvancedModelBox(this,"neck");neck.setRotationPoint(0.0F,-2.5F,-10.0F);body.addChild(neck);
        setRotationAngle(neck,0.48F,0.0F,0.0F);
        neck.setTextureOffset(17,39).addBox(-1.5F,-1.5F,-7.0F,3.0F,3.0F,8.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0.0F,-2.5F,-6.0F);neck.addChild(head);
        setRotationAngle(head,-0.1745F,0.0F,0.0F);
        head.setTextureOffset(0,32).addBox(-2.5F,-3.0F,-5.0F,5.0F,4.0F,7.0F,0.0F,false);
        head.setTextureOffset(23,15).addBox(-1.5F,-2.0F,-11.0F,3.0F,3.0F,6.0F,0.0F,false);
        head.setTextureOffset(32,39).addBox(-1.5F,1.0F,-11.0F,3.0F,1.0F,2.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,neck,heart,head,rightFoot,leftFoot,rightWing,leftWing,leftLeg,rightLeg);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}

    @Override
    public void setupAnim(EntitySoulVulture entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🦅 SOUL VULTURE — THERMAL-CIRCLING SPECTRAL SCAVENGER ══════
        // IDENTITY: Undead vulture. Circles in thermals (slow banking body).
        // Head scans for carrion below. Spectral wing-tip flutter.
        // Heart pulses with collected souls.
        float idleSpeed=0.1F,idleDegree=0.1F,flapSpeed=0.4F,flapDegree=0.2F,walkSpeed=0.7F,walkDegree=0.4F;
        float partialTick=ageInTicks-entity.tickCount;
        float flyProgress=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*partialTick;
        float tackleProgress=entity.prevTackleProgress+(entity.tackleProgress-entity.prevTackleProgress)*partialTick;

        progressRotationPrev(body,flyProgress,Maths.rad(15),0,0,5F);
        progressRotationPrev(neck,flyProgress,Maths.rad(-35),0,0,5F);
        progressRotationPrev(head,flyProgress,Maths.rad(25),0,0,5F);
        progressRotationPrev(leftLeg,flyProgress,Maths.rad(55),0,0,5F);
        progressRotationPrev(rightLeg,flyProgress,Maths.rad(55),0,0,5F);
        progressRotationPrev(rightWing,flyProgress,Maths.rad(-70),Maths.rad(-90),0,5F);
        progressRotationPrev(leftWing,flyProgress,Maths.rad(-70),Maths.rad(90),0,5F);
        progressPositionPrev(rightWing,flyProgress,0F,-2,-1,5f);
        progressPositionPrev(leftWing,flyProgress,0F,-2,-1,5f);
        progressPositionPrev(body,flyProgress,0F,2F,0,5f);
        progressPositionPrev(head,flyProgress,0F,3F,-2F,5f);
        progressRotationPrev(body,tackleProgress,-Maths.rad(55),0,0,5F);
        progressRotationPrev(neck,tackleProgress,Maths.rad(-35),0,0,5F);
        progressRotationPrev(head,tackleProgress,Maths.rad(90),0,0,5F);
        progressPositionPrev(leftLeg,tackleProgress,0F,3,-2,5f);
        progressPositionPrev(rightLeg,tackleProgress,0F,3,-2,5f);

        if(flyProgress>0){
            this.walk(rightLeg,walkSpeed,walkDegree*0.4F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(leftLeg,walkSpeed,walkDegree*0.4F,true,0F,0F,limbSwing,limbSwingAmount);
            this.bob(body,flapSpeed*0.5F,flapDegree*8,true,ageInTicks,1);
            this.flap(rightWing,flapSpeed,flapDegree*5,true,0F,0F,ageInTicks,1);
            this.flap(leftWing,flapSpeed,flapDegree*5,false,0F,0F,ageInTicks,1);
            // THERMAL CIRCLING: slow body banking
            body.rotateAngleZ+=Mth.sin(ageInTicks*0.06F)*0.15F;
            // Wing-tip spectral flutter
            leftWing.rotateAngleZ+=Mth.sin(ageInTicks*0.4F)*0.06F;
            rightWing.rotateAngleZ-=Mth.sin(ageInTicks*0.4F+1F)*0.06F;
        }else{
            this.walk(body,walkSpeed,walkDegree*0.5F,false,2F,0F,limbSwing,limbSwingAmount);
            this.walk(neck,walkSpeed,walkDegree*0.4F,true,1F,0F,limbSwing,limbSwingAmount);
            this.swing(rightWing,walkSpeed,walkDegree*0.4F,true,1F,0.2F,limbSwing,limbSwingAmount);
            this.swing(leftWing,walkSpeed,walkDegree*0.4F,false,1F,0.2F,limbSwing,limbSwingAmount);
            this.walk(rightLeg,walkSpeed,walkDegree*1.85F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(leftLeg,walkSpeed,walkDegree*1.85F,true,0F,0F,limbSwing,limbSwingAmount);
        }
        this.walk(heart,idleSpeed,idleDegree,false,2F,0F,ageInTicks,1);
        this.bob(heart,idleSpeed,idleDegree*4F,false,ageInTicks,1);
        this.walk(neck,idleSpeed,idleDegree,false,0F,0F,ageInTicks,1);
        // CARRION-SCANNING HEAD TILT (vulture signature)
        head.rotateAngleZ+=Mth.sin(ageInTicks*0.22F)*0.12F;
        head.rotateAngleX+=Mth.sin(ageInTicks*0.15F+1F)*0.06F;
        // Neck micro-rotation (searching carrion)
        neck.rotateAngleZ+=Mth.sin(ageInTicks*0.13F)*0.04F;

        this.faceTarget(netHeadYaw,headPitch,2,neck,head);
        // SPECTRAL BREATHING + PHASING: combined scale for ghostly pulse
        float breath=Mth.cos(ageInTicks*0.10F);
        float phase=Mth.sin(ageInTicks*0.05F+0.8F);
        body.setScale(1.0F+breath*0.015F+phase*0.025F, 1.0F+breath*0.02F+phase*0.015F, 1.0F+phase*0.025F);
        body.rotationPointY+=breath*0.06F+phase*0.03F;
        leftWing.rotateAngleZ+=phase*0.04F;
        rightWing.rotateAngleZ-=phase*0.04F;
        float bloatScale=1F+Math.min(1F,entity.getSoulLevel()*0.5F);
        this.heart.setScale(bloatScale,bloatScale,bloatScale);
    }

    @Override public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){root.render(m,b,l,o);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySkunk;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelSkunk extends AdvancedEntityModel<EntitySkunk> {
    private final AdvancedModelBox root,body,leftLeg,rightLeg,leftArm,rightArm,tail,head;

    public ModelSkunk(){texWidth=64;texHeight=64;root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-3.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-3.5F,-4.0F,-4.5F,7.0F,6.0F,9.0F,0.0F,false);
        leftLeg=new AdvancedModelBox(this,"leftLeg");leftLeg.setRotationPoint(4.0F,2.0F,4.0F);body.addChild(leftLeg);setRotationAngle(leftLeg,0.0F,-0.7418F,0.0F);leftLeg.setTextureOffset(0,33).addBox(-1.0F,-1.0F,-3.0F,2.0F,2.0F,4.0F,0.0F,false);
        rightLeg=new AdvancedModelBox(this,"rightLeg");rightLeg.setRotationPoint(-4.0F,2.0F,4.0F);body.addChild(rightLeg);setRotationAngle(rightLeg,0.0F,0.7418F,0.0F);rightLeg.setTextureOffset(0,33).addBox(-1.0F,-1.0F,-3.0F,2.0F,2.0F,4.0F,0.0F,true);
        leftArm=new AdvancedModelBox(this,"leftArm");leftArm.setRotationPoint(3.5F,2.0F,-3.0F);body.addChild(leftArm);setRotationAngle(leftArm,0.0F,-0.5672F,0.0F);leftArm.setTextureOffset(32,31).addBox(-1.0F,-1.0F,-3.0F,2.0F,2.0F,4.0F,0.0F,false);
        rightArm=new AdvancedModelBox(this,"rightArm");rightArm.setRotationPoint(-3.5F,2.0F,-3.0F);body.addChild(rightArm);setRotationAngle(rightArm,0.0F,0.5672F,0.0F);rightArm.setTextureOffset(32,31).addBox(-1.0F,-1.0F,-3.0F,2.0F,2.0F,4.0F,0.0F,true);
        tail=new AdvancedModelBox(this,"tail");tail.setRotationPoint(0.0F,-1.0F,4.5F);body.addChild(tail);tail.setTextureOffset(0,16).addBox(-3.0F,-10.0F,0.0F,6.0F,12.0F,4.0F,0.0F,false);tail.setTextureOffset(21,16).addBox(-3.0F,-10.0F,4.0F,6.0F,7.0F,5.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0.0F,0.0F,-5.5F);body.addChild(head);head.setTextureOffset(24,0).addBox(-3.0F,-2.0F,-3.0F,6.0F,4.0F,4.0F,0.0F,false);head.setTextureOffset(21,29).addBox(-2.0F,0.0F,-6.0F,4.0F,2.0F,3.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,leftArm,rightArm,leftLeg,rightLeg,tail,head);}

    @Override
    public void setupAnim(EntitySkunk entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float idleSpeed=0.1F,idleDegree=0.15f,walkSpeed=1.1F,walkDegree=0.45f;
        float partialTicks=ageInTicks-entity.tickCount;
        float sprayProgress=entity.prevSprayProgress+(entity.sprayProgress-entity.prevSprayProgress)*partialTicks;
        float legsStill=Math.max(sprayProgress*0.2F,limbSwingAmount);

        //══════ 🦨 SKUNK — WARNING-STOMP SPRAY DEFENDER ══════
        // IDENTITY: Deliberate waddle. Raises tail as WARNING before spraying.
        // Stomps front feet when threatened. Black-and-white bold posture.
        // UNIQUE vs Raccoon (hand-washer), TasmanianDevil (bite-shaker).

        // ── BREATHING: small mustelid ──
        float breath=Mth.cos(ageInTicks*0.1F);body.rotationPointY+=breath*0.04F;

        // ── TAIL: RAISED warning posture (skunk's defining defense) ──
        if(sprayProgress>0){tail.rotateAngleZ+=Mth.sin(ageInTicks*0.6F)*sprayProgress*0.1F;}
        // Idle: tail sways gently — but when spraying, it's UP and tense
        this.walk(tail,idleSpeed,idleDegree,false,1F,0F,ageInTicks,1);
        this.flap(tail,walkSpeed,walkSpeed*0.2F,true,-1,0,limbSwing,limbSwingAmount);

        // ── STOMP WARNING: front feet stamp when threatened ──
        progressRotationPrev(leftArm,sprayProgress,Maths.rad(80F),0,0,5F);
        progressRotationPrev(rightArm,sprayProgress,Maths.rad(80F),0,0,5F);
        progressRotationPrev(leftLeg,sprayProgress,Maths.rad(100F),0,0,5F);
        progressRotationPrev(rightLeg,sprayProgress,Maths.rad(100F),0,0,5F);
        progressRotationPrev(tail,sprayProgress,Maths.rad(30F),0,0,5F);
        progressPositionPrev(body,sprayProgress,0,-2.4F,0,5F);
        progressPositionPrev(tail,sprayProgress,0,-2F,-1F,5F);
        this.walk(body,0.5F,0.2F,true,4F,0F,ageInTicks,sprayProgress*0.2F);
        this.flap(tail,0.5F,0.5F,false,2.5F,0F,ageInTicks,sprayProgress*0.2F);

        // ── SPRAWL WALK: skunks waddle with legs splayed ──
        progressRotationPrev(leftArm,Math.min(legsStill,0.5F),0,Maths.rad(30F),0,0.5F);
        progressRotationPrev(rightArm,Math.min(legsStill,0.5F),0,Maths.rad(-30F),0,0.5F);
        progressRotationPrev(leftLeg,Math.min(legsStill,0.5F),0,Maths.rad(40F),0,0.5F);
        progressRotationPrev(rightLeg,Math.min(legsStill,0.5F),0,Maths.rad(-40F),0,0.5F);
        progressPositionPrev(head,Math.min(legsStill,0.5F),0,-1F,0,0.5F);

        this.swing(body,walkSpeed,walkDegree*0.5F,false,3F,0F,limbSwing,limbSwingAmount);
        this.swing(head,walkSpeed,walkDegree*0.5F,true,2F,0F,limbSwing,limbSwingAmount);
        this.walk(leftArm,walkSpeed,walkDegree*1.2F,true,-2.5F,-0.2F,limbSwing,limbSwingAmount);
        this.walk(rightArm,walkSpeed,walkDegree*1.2F,false,-2.5F,0.2F,limbSwing,limbSwingAmount);
        this.walk(rightLeg,walkSpeed,walkDegree*1.2F,true,-2.5F,-0.2F,limbSwing,limbSwingAmount);
        this.walk(leftLeg,walkSpeed,walkDegree*1.2F,false,-2.5F,0.2F,limbSwing,limbSwingAmount);
        this.flap(body,walkSpeed,walkSpeed*0.3F,false,-1,0,limbSwing,limbSwingAmount);

        float ls=(float)(Math.sin((limbSwing*walkSpeed)-2.5F)*limbSwingAmount*walkDegree-(limbSwingAmount*walkDegree));
        float rs=(float)(Math.sin(-(limbSwing*walkSpeed)+2.5F)*limbSwingAmount*walkDegree-(limbSwingAmount*walkDegree));
        rightArm.rotationPointY+=3*ls;leftArm.rotationPointY+=3*rs;
        leftLeg.rotationPointY+=3*ls;rightLeg.rotationPointY+=3*rs;
        rightArm.rotationPointZ+=1F*ls;leftArm.rotationPointZ+=1F*rs;
        leftLeg.rotationPointZ+=1F*ls;rightLeg.rotationPointZ+=1F*rs;

        this.faceTarget(netHeadYaw,headPitch,1.2F,head);
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){head.setScale(1.5F,1.5F,1.5F);ms.pushPose();ms.scale(0.65F,0.65F,0.65F);ms.translate(0.0D,0.815D,0.125D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
        else{head.setScale(1F,1F,1F);ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

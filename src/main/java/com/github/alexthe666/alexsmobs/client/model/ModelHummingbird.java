package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityHummingbird;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelHummingbird extends AdvancedEntityModel<EntityHummingbird> {
    private final AdvancedModelBox root,body,head,wingL,wingL_r1,wingR,wingR_r1,tail,legL,legR;

    public ModelHummingbird(){texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-2.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-1.5F,-3.0F,-4.0F,3.0F,3.0F,6.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,-2.0F,-4.0F);body.addChild(head);head.setTextureOffset(13,0).addBox(-1.0F,-2.0F,-2.0F,2.0F,2.0F,3.0F,0.0F,false);head.setTextureOffset(0,10).addBox(-0.5F,0.0F,-5.0F,1.0F,1.0F,3.0F,0.0F,false);
        wingL=new AdvancedModelBox(this,"wingL");wingL.setPos(1.5F,-2.0F,-3.0F);body.addChild(wingL);wingL.setTextureOffset(0,15).addBox(0.0F,-0.5F,-2.0F,1.0F,8.0F,4.0F,0.0F,false);
        wingL_r1=new AdvancedModelBox(this,"wingL_r1");wingL_r1.setPos(0.5F,7.5F,0.0F);wingL.addChild(wingL_r1);setRotateAngle(wingL_r1,-0.2182F,0.0F,0.0F);wingL_r1.setTextureOffset(11,12).addBox(-0.5F,0.0F,-1.5F,1.0F,4.0F,2.0F,0.0F,false);
        wingR=new AdvancedModelBox(this,"wingR");wingR.setPos(-1.5F,-2.0F,-3.0F);body.addChild(wingR);wingR.setTextureOffset(0,15).addBox(-1.0F,-0.5F,-2.0F,1.0F,8.0F,4.0F,0.0F,true);
        wingR_r1=new AdvancedModelBox(this,"wingR_r1");wingR_r1.setPos(-0.5F,7.5F,0.0F);wingR.addChild(wingR_r1);setRotateAngle(wingR_r1,-0.2182F,0.0F,0.0F);wingR_r1.setTextureOffset(11,12).addBox(-0.5F,0.0F,-1.5F,1.0F,4.0F,2.0F,0.0F,true);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-1.0F,2.0F);body.addChild(tail);tail.setTextureOffset(0,10).addBox(-2.0F,0.0F,0.0F,4.0F,1.0F,3.0F,0.0F,false);
        legL=new AdvancedModelBox(this,"legL");legL.setPos(1.0F,0.0F,0.0F);body.addChild(legL);legL.setTextureOffset(0,0).addBox(-0.5F,0.0F,-0.5F,1.0F,2.0F,1.0F,0.0F,false);
        legR=new AdvancedModelBox(this,"legR");legR.setPos(-1.0F,0.0F,0.0F);body.addChild(legR);legR.setTextureOffset(0,0).addBox(-0.5F,0.0F,-0.5F,1.0F,2.0F,1.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,head,wingL,wingL_r1,wingR,wingR_r1,tail,legL,legR);}

    @Override
    public void setupAnim(EntityHummingbird entityIn,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float flySpeed=1.2F,flyDegree=0.8F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float flyProgress=entityIn.prevFlyProgress+(entityIn.flyProgress-entityIn.prevFlyProgress)*partialTick;
        float zoomProgress=entityIn.prevMovingProgress+(entityIn.movingProgress-entityIn.prevMovingProgress)*partialTick;
        float sipProgress=entityIn.prevSipProgress+(entityIn.sipProgress-entityIn.prevSipProgress)*partialTick;

        //══════ 💎 HUMMINGBIRD — TRUE HOVERING JEWEL ══════
        // IDENTITY: ONLY bird that can truly hover and fly backwards.
        // Figure-8 wing pattern at ~40Hz illusion. Needle beak sips nectar.
        // Tail fans for air-braking. Body stays PERFECTLY still while hovering.
        // UNIQUE vs Sunbird (fire-hoverer), BlueJay (bossy hopper).

        // ── BREATHING: fastest metabolism on earth ──
        float breath=Mth.cos(ageInTicks*0.3F);body.rotationPointY+=breath*0.03F;

        progressRotationPrev(body,flyProgress,Maths.rad(30),0,0,5F);
        progressRotationPrev(head,flyProgress,Maths.rad(-30),0,0,5F);
        progressRotationPrev(wingL,flyProgress,Maths.rad(15),Maths.rad(55),Maths.rad(-100),5F);
        progressRotationPrev(wingR,flyProgress,Maths.rad(15),Maths.rad(-55),Maths.rad(100),5F);
        progressRotationPrev(tail,flyProgress,Maths.rad(-45),0,0,5F);
        progressRotationPrev(legL,flyProgress,Maths.rad(-45),0,0,5F);
        progressRotationPrev(legR,flyProgress,Maths.rad(-45),0,0,5F);
        progressPositionPrev(wingL,flyProgress,0,1,0,5F);progressPositionPrev(wingR,flyProgress,0,1,0,5F);
        progressPositionPrev(legL,flyProgress,0,-0.4F,0,5F);progressPositionPrev(legR,flyProgress,0,-0.4F,0,5F);
        progressPositionPrev(head,flyProgress,0,-0.5F,-1F,5F);

        if(flyProgress>0){
            progressRotationPrev(body,zoomProgress,Maths.rad(25),0,0,5F);
            progressRotationPrev(head,zoomProgress,Maths.rad(-25),0,0,5F);
            // ── FIGURE-8 WINGS: hummingbird's defining motion ──
            // Wings beat in horizontal figure-8 (not just up/down)
            this.flap(wingL,flySpeed,flyDegree,false,0F,0F,ageInTicks,1);
            this.flap(wingR,flySpeed,flyDegree,true,0F,0F,ageInTicks,1);
            // Wingtips trace figure-8: forward on downstroke, back on upstroke
            this.swing(wingL,flySpeed*2F,flyDegree*0.3F,true,0.5F,0,ageInTicks,1);
            this.swing(wingR,flySpeed*2F,flyDegree*0.3F,false,0.5F,0,ageInTicks,1);
            // Body stays PERFECTLY still during hover (gyroscopic stabilization)
            this.bob(body,flySpeed*0.1F,flyDegree*0.3F,false,ageInTicks,1);
            // Tail fans for braking/steering
            this.flap(tail,flySpeed*0.5F,0.08F,false,0F,0,ageInTicks,1);
            // Feet tuck
            legL.rotationPointZ-=0.3F;legR.rotationPointZ-=0.3F;
        }else{
            // ── PERCHED: tiny, still, occasional head dart ──
            head.rotateAngleX+=Mth.sin(ageInTicks*0.6F)*0.05F;
            head.rotateAngleZ+=Mth.sin(ageInTicks*0.5F+1F)*0.06F;
        }

        // ── SIPPING: needle beak probes flower ──
        progressRotationPrev(body,sipProgress,Maths.rad(15),0,0,5F);
        progressRotationPrev(head,sipProgress,Maths.rad(-20),0,0,5F);
        progressPositionPrev(head,sipProgress,0,0,-0.5F,5F);
        if(sipProgress>0){head.rotationPointX+=Mth.sin(ageInTicks*0.7F)*0.03F;}

        if(flyProgress<=0){this.faceTarget(netHeadYaw,headPitch,1,head);}
    }

    public void setRotateAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

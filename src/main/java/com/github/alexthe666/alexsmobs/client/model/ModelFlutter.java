package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFlutter;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelFlutter extends AdvancedEntityModel<EntityFlutter> {
    private final AdvancedModelBox root,body,eyes,petals,front_petal,left_petal,right_petal,back_petal,left_arm,left_leg,right_leg,right_arm;

    public ModelFlutter(){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0,-3.9F,0);root.addChild(body);body.setTextureOffset(0,13).addBox(-3.5F,-3,-3.5F,7,5,7,0,false);body.setTextureOffset(0,0).addBox(-3.5F,-3,-3.5F,7,5,7,-0.2F,false);
        eyes=new AdvancedModelBox(this,"eyes");eyes.setRotationPoint(0,-1,-3);body.addChild(eyes);eyes.setTextureOffset(23,30).addBox(-1.5F,-0.5F,0,3,1,0,0,false);
        petals=new AdvancedModelBox(this,"petals");petals.setRotationPoint(0,-3,0);body.addChild(petals);
        front_petal=new AdvancedModelBox(this,"fp");front_petal.setRotationPoint(0,0,-1.5F);petals.addChild(front_petal);setRotationAngle(front_petal,1.1781F,0,0);front_petal.setTextureOffset(0,26).addBox(-3.5F,-7,0,7,7,0,0,false);
        left_petal=new AdvancedModelBox(this,"lp");left_petal.setRotationPoint(1.5F,0,0);petals.addChild(left_petal);setRotationAngle(left_petal,1.1781F,-1.5708F,0);left_petal.setTextureOffset(0,26).addBox(-3.5F,-7,0,7,7,0,0,false);
        right_petal=new AdvancedModelBox(this,"rp");right_petal.setRotationPoint(-1.5F,0,0);petals.addChild(right_petal);setRotationAngle(right_petal,1.1781F,1.5708F,0);right_petal.setTextureOffset(0,26).addBox(-3.5F,-7,0,7,7,0,0,true);
        back_petal=new AdvancedModelBox(this,"bp");back_petal.setRotationPoint(0,0,1.5F);petals.addChild(back_petal);setRotationAngle(back_petal,1.1781F,3.1416F,0);back_petal.setTextureOffset(0,26).addBox(-3.5F,-7,0,7,7,0,0,false);
        left_arm=new AdvancedModelBox(this,"la");left_arm.setRotationPoint(3,1.9F,-3);body.addChild(left_arm);setRotationAngle(left_arm,0,-0.7418F,0);left_arm.setTextureOffset(0,0).addBox(-0.5F,0,-2,1,2,2,0,false);
        left_leg=new AdvancedModelBox(this,"ll");left_leg.setRotationPoint(3,1.9F,3);body.addChild(left_leg);setRotationAngle(left_leg,-3.1416F,-0.7418F,3.1416F);left_leg.setTextureOffset(0,0).addBox(-0.5F,0,-2,1,2,2,0,false);
        right_leg=new AdvancedModelBox(this,"rl");right_leg.setRotationPoint(-3,1.9F,3);body.addChild(right_leg);setRotationAngle(right_leg,-3.1416F,0.7418F,-3.1416F);right_leg.setTextureOffset(0,0).addBox(-0.5F,0,-2,1,2,2,0,true);
        right_arm=new AdvancedModelBox(this,"ra");right_arm.setRotationPoint(-3,1.9F,-3);body.addChild(right_arm);setRotationAngle(right_arm,0,0.7418F,0);right_arm.setTextureOffset(0,0).addBox(-0.5F,0,-2,1,2,2,0,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,eyes,petals,front_petal,left_petal,back_petal,right_petal,left_arm,right_arm,left_leg,right_leg);}

    @Override public void setupAnim(EntityFlutter e,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🦋 FLUTTER — ETHEREAL PETAL SPIRIT ══════
        // IDENTITY: Ghost butterfly with 4 petal-wings. Each petal ripples
        // independently like silk in wind. Floats weightlessly — no heavy
        // insect buzzing. Eyes track the player. Tiny legs for perching.
        // UNIQUE vs other flyers: petal wings, not insect wings. Silent float.
        float idSp=0.25F,idDg=0.12F,wkSp=1.6F,wkDg=1.2F,pt=ageInTicks-e.tickCount;
        float shoot=e.prevShootProgress+(e.shootProgress-e.prevShootProgress)*pt;
        float fly=e.prevFlyProgress+(e.flyProgress-e.prevFlyProgress)*pt;
        float sit=e.prevSitProgress+(e.sitProgress-e.prevSitProgress)*pt;
        float gnd=(5F-fly)*0.2F,tent=(5F-limbSwingAmount*5F)*fly*0.2F;
        float invTent=(e.prevTentacleProgress+(e.tentacleProgress-e.prevTentacleProgress)*pt)*fly*0.2F;
        float flutterPitch=Maths.rad(Mth.rotLerp(pt,e.prevFlutterPitch,e.getFlutterPitch()));
        Entity look=Minecraft.getInstance().getCameraEntity();

        //══════ BREATHING: ethereal pulse ══════
        float breath=Mth.cos(ageInTicks*0.12F);
        body.setScale(1.0F+breath*0.02F,1.0F+breath*0.015F,1.0F+breath*0.01F);
        body.rotationPointY+=breath*0.04F;

        //══════ PETAL WINGS: 4 unique motion axes ══════
        // Front petal: fast vertical flap (leading edge)
        this.flap(front_petal,0.35F,idDg*1.3F,false,0F,0.08F,ageInTicks,1);
        // Back petal: slow horizontal swing (trailing edge)
        this.swing(back_petal,0.18F,idDg*1.2F,true,1.5F,0.06F,ageInTicks,1);
        // Right petal: diagonal flap (Z-rotation)
        right_petal.rotateAngleZ+=Mth.sin(ageInTicks*0.28F)*0.07F;
        // Left petal: diagonal flap opposite phase
        left_petal.rotateAngleZ-=Mth.sin(ageInTicks*0.33F+0.7F)*0.07F;
        // Secondary all-axis drift — offset phases so petals never sync
        this.flap(front_petal,0.15F,idDg*0.3F,true,0F,0,ageInTicks,1);
        this.swing(back_petal,0.22F,idDg*0.4F,false,1F,0,ageInTicks,1);
        this.flap(right_petal,0.12F,idDg*0.2F,false,2F,0,ageInTicks,1);
        this.swing(left_petal,0.16F,idDg*0.25F,true,2.5F,0,ageInTicks,1);
        // Front/back phase coordination: front leads, back follows
        back_petal.rotateAngleX+=Mth.sin(ageInTicks*0.35F+0.6F)*0.04F;
        // Left/right cross-axis flutter prevents mirroring
        right_petal.rotateAngleX-=Mth.sin(ageInTicks*0.28F+0.3F)*0.03F;
        left_petal.rotateAngleX+=Mth.sin(ageInTicks*0.33F+1.0F)*0.03F;

        //══════ ETHEREAL FLOAT: weightless drift ══════
        this.bob(body,fly*0.3F,0.06F*fly,true,ageInTicks,fly*0.2F);
        // Slow gentle body tilt while floating
        body.rotateAngleZ+=Mth.sin(ageInTicks*0.1F+1F)*0.03F*fly*0.2F;

        //══════ EYE TRACKING + HEAD SHAKE ══════
        if(e.isShakingHead()){eyes.rotationPointX+=Mth.sin(ageInTicks);body.rotateAngleY+=Mth.sin(ageInTicks)*0.1F;eyes.rotationPointY=-0.5F;}
        else if(look!=null){
            Vec3 v=look.getEyePosition(0),v1=e.getEyePosition(0);
            double d0=v.y-v1.y;
            eyes.rotationPointY=(float)Mth.clamp(-d0-0.5F,-2,0);
            Vec3 v2=e.getViewVector(0);v2=new Vec3(v2.x,0,v2.z);
            Vec3 v3=(new Vec3(v1.x-v.x,0,v1.z-v.z)).normalize().yRot((Mth.PI/2F));
            double d1=v2.dot(v3);
            eyes.rotationPointX+=Mth.sqrt((float)Math.abs(d1))*1.5F*(float)Math.signum(d1);
        }else{eyes.rotationPointY=-1;}

        //══════ GROUND: delicate tip-toe walk ══════
        this.walk(right_arm,wkSp,wkDg*1.2F,true,0,0.25F,limbSwing,limbSwingAmount*gnd);
        this.walk(left_arm,wkSp,wkDg*1.2F,false,0,-0.25F,limbSwing,limbSwingAmount*gnd);
        this.walk(right_leg,wkSp,wkDg*1.2F,false,0,-0.25F,limbSwing,limbSwingAmount*gnd);
        this.walk(left_leg,wkSp,wkDg*1.2F,true,0,0.25F,limbSwing,limbSwingAmount*gnd);
        this.bob(body,wkSp*1.5F,wkDg*0.6F,false,limbSwing,limbSwingAmount*gnd);

        //══════ STATE TRANSITIONS ══════
        progressRotationPrev(front_petal,Math.max(shoot,invTent),Maths.rad(-45),0,0,5F);
        progressRotationPrev(back_petal,Math.max(shoot,invTent),Maths.rad(-45),0,0,5F);
        progressRotationPrev(right_petal,Math.max(shoot,invTent),0,0,Maths.rad(45),5F);
        progressRotationPrev(left_petal,Math.max(shoot,invTent),0,0,Maths.rad(-45),5F);
        progressRotationPrev(front_petal,fly,Maths.rad(15),0,0,5F);
        progressRotationPrev(back_petal,fly,Maths.rad(15),0,0,5F);
        progressRotationPrev(right_petal,fly,0,0,Maths.rad(-15),5F);
        progressRotationPrev(left_petal,fly,0,0,Maths.rad(15),5F);
        progressPositionPrev(body,tent,0,-3,0,5F);
        progressRotationPrev(right_leg,tent,Maths.rad(105),0,0,5F);
        progressRotationPrev(left_leg,tent,Maths.rad(105),0,0,5F);
        progressRotationPrev(right_arm,tent,Maths.rad(105),0,0,5F);
        progressRotationPrev(left_arm,tent,Maths.rad(105),0,0,5F);
        progressPositionPrev(body,sit,0,2,0,5F);
        progressRotationPrev(right_leg,sit,Maths.rad(-45),0,0,5F);
        progressRotationPrev(left_leg,sit,Maths.rad(-45),0,0,5F);
        progressRotationPrev(right_arm,sit,Maths.rad(-45),0,0,5F);
        progressRotationPrev(left_arm,sit,Maths.rad(-45),0,0,5F);

        this.root.rotateAngleX-=flutterPitch*fly*0.2F;
        this.body.rotateAngleY+=Math.toRadians(Mth.wrapDegrees(shoot*360*0.2F));
        float ps=1+invTent*0.05F;
        front_petal.setScale(1,ps,1);back_petal.setScale(1,ps,1);left_petal.setScale(1,ps,1);right_petal.setScale(1,ps,1);
        if(e.isBaby()){root.rotationPointY+=1.5F;body.setScale(0.5F,0.5F,0.5F);body.setShouldScaleChildren(true);}
        else{body.setScale(1,1,1);}
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

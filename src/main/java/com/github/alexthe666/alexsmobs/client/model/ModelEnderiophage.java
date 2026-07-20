package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelEnderiophage extends AdvancedEntityModel<EntityEnderiophage> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body, mouth, sheath, collar, capsid, eye;
    private final AdvancedModelBox tailmid_left, tailmid_right;
    private final AdvancedModelBox tailback_left, tailback_right;
    private final AdvancedModelBox tailfront_left, tailfront_right;
    private final AdvancedModelBox tailmid_leftPivot, tailmid_rightPivot;
    private final AdvancedModelBox tailback_leftPivot, tailback_rightPivot;
    private final AdvancedModelBox tailfront_leftPivot, tailfront_rightPivot;

    public ModelEnderiophage(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-11,0);root.addChild(body);
        body.setTextureOffset(0,30).addBox(-4,-2,-4,8,3,8,0,false);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setPos(0,1,0);body.addChild(mouth);
        mouth.setTextureOffset(0,0).addBox(-1,-5,-1,2,7,2,0,false);
        sheath=new AdvancedModelBox(this,"sheath");sheath.setPos(0,-2,0);body.addChild(sheath);
        sheath.setTextureOffset(50,43).addBox(-2,-14,-2,4,14,4,0,false);
        collar=new AdvancedModelBox(this,"collar");collar.setPos(0,-14,0);sheath.addChild(collar);
        collar.setTextureOffset(0,55).addBox(-3,-1,-3,6,1,6,0,false);
        capsid=new AdvancedModelBox(this,"capsid");capsid.setPos(0,-1,0);collar.addChild(capsid);
        capsid.setTextureOffset(0,0).addBox(-7,-15,-7,14,15,14,0,false);
        eye=new AdvancedModelBox(this,"eye");eye.setPos(0,-8,0);capsid.addChild(eye);
        eye.setTextureOffset(43,0).addBox(-3,-3,-3,6,6,6,0,false);
        tailmid_leftPivot=new AdvancedModelBox(this,"tmlP");tailmid_leftPivot.setPos(4,-1,0);body.addChild(tailmid_leftPivot);
        tailmid_left=new AdvancedModelBox(this,"tml");tailmid_leftPivot.addChild(tailmid_left);
        tailmid_left.setTextureOffset(25,43).addBox(0,0,0,12,12,0,0,false);
        tailmid_rightPivot=new AdvancedModelBox(this,"tmrP");tailmid_rightPivot.setPos(-4,-1,0);body.addChild(tailmid_rightPivot);
        tailmid_right=new AdvancedModelBox(this,"tmr");tailmid_rightPivot.addChild(tailmid_right);
        tailmid_right.setTextureOffset(25,43).addBox(-12,0,0,12,12,0,0,true);
        tailback_leftPivot=new AdvancedModelBox(this,"tblP");tailback_leftPivot.setPos(4,-1,4);body.addChild(tailback_leftPivot);
        setRotationAngle(tailback_leftPivot,0,-0.7854F,0);
        tailback_left=new AdvancedModelBox(this,"tbl");tailback_leftPivot.addChild(tailback_left);
        tailback_left.setTextureOffset(33,30).addBox(0,0,0,12,12,0,0,false);
        tailback_rightPivot=new AdvancedModelBox(this,"tbrP");tailback_rightPivot.setPos(-4,-1,4);body.addChild(tailback_rightPivot);
        setRotationAngle(tailback_rightPivot,0,0.7854F,0);
        tailback_right=new AdvancedModelBox(this,"tbr");tailback_rightPivot.addChild(tailback_right);
        tailback_right.setTextureOffset(33,30).addBox(-12,0,0,12,12,0,0,true);
        tailfront_leftPivot=new AdvancedModelBox(this,"tflP");tailfront_leftPivot.setPos(4,-1,-4);body.addChild(tailfront_leftPivot);
        setRotationAngle(tailfront_leftPivot,0,0.6981F,0);
        tailfront_left=new AdvancedModelBox(this,"tfl");tailfront_leftPivot.addChild(tailfront_left);
        tailfront_left.setTextureOffset(0,42).addBox(0,0,0,12,12,0,0,false);
        tailfront_rightPivot=new AdvancedModelBox(this,"tfrP");tailfront_rightPivot.setPos(-4,-1,-4);body.addChild(tailfront_rightPivot);
        setRotationAngle(tailfront_rightPivot,0,-0.6981F,0);
        tailfront_right=new AdvancedModelBox(this,"tfr");tailfront_rightPivot.addChild(tailfront_right);
        tailfront_right.setTextureOffset(0,42).addBox(-12,0,0,12,12,0,0,true);
        this.updateDefaultPose();}

    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,tailback_left,tailback_right,tailfront_left,tailfront_right,tailmid_left,tailmid_right,tailback_leftPivot,tailback_rightPivot,tailfront_leftPivot,tailfront_rightPivot,tailmid_leftPivot,tailmid_rightPivot,body,capsid,eye,mouth,sheath,collar);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}

    @Override public void setupAnim(EntityEnderiophage e,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🦠 ENDERIOPHAGE — ASYMMETRIC PARASITIC DRIFTER ══════
        // IDENTITY: Ender-parasite. Each of 6 tail fibers moves AUTONOMOUSLY
        // with unique rhythm — like independent sensory organs searching for
        // a host surface to attach. Body drifts with ERRATIC twitching.
        // Sheath contracts to inject ender-DNA. Capsid pulses with parasitic energy.
        // BIOMECHANICS: Real bacteriophages have ASYNCHRONOUS tail fiber movement.
        // Each fiber contracts independently. The current code replaces the old
        // perfectly-synchronized identical flapping with 6 independent rhythms.

        Entity look=Minecraft.getInstance().getCameraEntity();
        float pt=ageInTicks-e.tickCount;
        float wkSp=2F,wkDg=0.6F;
        float fly=e.prevFlyProgress+(e.flyProgress-e.prevFlyProgress)*pt;
        float phagePitch=Maths.rad(Mth.rotLerp(pt,e.prevPhagePitch,e.getPhagePitch()));
        float totalYaw=Maths.rad(Mth.rotLerp(pt,e.yBodyRotO,e.yBodyRot));
        float tentP=(5F-limbSwingAmount*10F)*fly*0.2F;

        //══════ CAPSID PULSING BREATH ══════
        float breath=Mth.cos(ageInTicks*0.14F);
        capsid.setScale(1.0F+breath*0.03F,1.0F+breath*0.02F,1.0F+breath*0.03F);
        body.rotationPointY+=breath*0.08F;
        // Sheath pulse: contracts/expands for injection
        sheath.setScale(1.0F,1.0F+breath*0.04F,1.0F);
        // Collar micro-pulse (base plate)
        collar.rotateAngleY+=Mth.sin(ageInTicks*0.35F)*0.04F;
        // Mouth probe: searches for host surface
        mouth.rotateAngleX+=Mth.sin(ageInTicks*0.4F+2F)*0.08F;
        mouth.rotationPointY+=breath*0.03F;

        //══════ ERRATIC BODY TWITCH: parasitic micro-spasm ══════
        // Not smooth sinusoidal — uses threshold-triggered rapid twitches
        float twitch=Mth.sin(ageInTicks*3.5F);
        float twitchX=twitch>0.85F?twitch*0.06F:0;
        float twitchZ=Mth.sin(ageInTicks*2.8F+1.2F)>0.9F?0.04F:0;
        body.rotationPointX+=twitchX;
        body.rotationPointZ+=twitchZ;
        // Slow irregular drift rotation
        body.rotateAngleY+=Mth.sin(ageInTicks*0.3F)*0.08F;
        body.rotateAngleZ+=Mth.sin(ageInTicks*0.22F+1.5F)*0.04F;

        //══════ EYE: floats within capsid — tracks player ══════
        this.bob(eye,0.30F,0.10F*8,false,ageInTicks,1);

        //══════ 6 TAIL FIBERS: truly independent rhythms ══════
        // Each fiber has UNIQUE frequency + amplitude + axis.
        // Left/right pairs also differ — organic asymmetry.
        // Front fibers: fastest, tightest (searching ahead)
        this.flap(tailfront_left,0.28F,0.12F,true,0F,0.5F,ageInTicks,1);
        this.walk(tailfront_left,0.22F,0.08F,false,-2F,0.2F,ageInTicks,1);
        this.flap(tailfront_right,0.22F,0.09F,false,0.5F,0.4F,ageInTicks,1);
        this.walk(tailfront_right,0.25F,0.06F,true,-1.8F,0.15F,ageInTicks,1);
        // Mid fibers: medium speed, wider (lateral sensors)
        this.flap(tailmid_left,0.18F,0.15F,true,1F,0.6F,ageInTicks,1);
        this.walk(tailmid_left,0.16F,0.1F,false,-3F,0.25F,ageInTicks,1);
        this.flap(tailmid_right,0.20F,0.11F,false,1.5F,0.5F,ageInTicks,1);
        this.walk(tailmid_right,0.19F,0.07F,true,-2.5F,0.2F,ageInTicks,1);
        // Back fibers: slowest, widest (trailing anchors)
        this.flap(tailback_left,0.13F,0.18F,true,2F,0.7F,ageInTicks,1);
        this.walk(tailback_left,0.12F,0.12F,false,-4F,0.3F,ageInTicks,1);
        this.flap(tailback_right,0.16F,0.14F,false,2.5F,0.55F,ageInTicks,1);
        this.walk(tailback_right,0.15F,0.09F,true,-3.5F,0.28F,ageInTicks,1);

        this.bob(body,0.25F,0.12F*8,false,ageInTicks,1);
        this.body.rotationPointY+=8F;

        //══════ FLYING STATE (preserved) ══════
        if(fly!=5){limbSwingAmount*=1-(fly*0.2F);
            this.walk(sheath,wkSp,wkDg*0.2F,true,1,0.05F,limbSwing,limbSwingAmount);
            this.swing(tailfront_right,wkSp,wkDg*-1.2F,false,0,-0.3F,limbSwing,limbSwingAmount);
            this.swing(tailfront_left,wkSp,wkDg*-1.2F,false,0,0.3F,limbSwing,limbSwingAmount);
            this.flap(tailfront_right,wkSp,wkDg*-1.6F,false,0,-0.2F,limbSwing,limbSwingAmount);
            this.flap(tailfront_left,wkSp,wkDg*-1.6F,false,0,0.2F,limbSwing,limbSwingAmount);
            this.walk(tailfront_right,wkSp,wkDg*-1.6F,true,0,0.3F,limbSwing,limbSwingAmount);
            this.walk(tailfront_left,wkSp,wkDg*-1.6F,false,0,-0.3F,limbSwing,limbSwingAmount);
            this.swing(tailmid_right,wkSp,wkDg*-1.2F,false,-2.5F,0.2F,limbSwing,limbSwingAmount);
            this.swing(tailmid_left,wkSp,wkDg*-1.2F,false,-2.5F,-0.2F,limbSwing,limbSwingAmount);
            this.flap(tailmid_right,wkSp,wkDg*-1.6F,false,-2.5F,0.5F,limbSwing,limbSwingAmount);
            this.flap(tailmid_left,wkSp,wkDg*-1.6F,false,-2.5F,-0.5F,limbSwing,limbSwingAmount);
            this.walk(tailmid_right,wkSp,wkDg*-1.6F,true,-2.5F,0.3F,limbSwing,limbSwingAmount);
            this.walk(tailmid_left,wkSp,wkDg*-1.6F,false,-2.5F,-0.3F,limbSwing,limbSwingAmount);
            this.swing(tailback_right,wkSp,wkDg*-1.2F,false,-5,-0.2F,limbSwing,limbSwingAmount);
            this.swing(tailback_left,wkSp,wkDg*-1.2F,false,-5,0.2F,limbSwing,limbSwingAmount);
            this.flap(tailback_right,wkSp,wkDg*-1.6F,false,-5,0.5F,limbSwing,limbSwingAmount);
            this.flap(tailback_left,wkSp,wkDg*-1.6F,false,-5,-0.5F,limbSwing,limbSwingAmount);
            this.walk(tailback_right,wkSp,wkDg*-1.6F,true,-5,0.3F,limbSwing,limbSwingAmount);
            this.walk(tailback_left,wkSp,wkDg*-1.6F,false,-5,-0.3F,limbSwing,limbSwingAmount);
            this.bob(body,wkSp*1.5F,wkDg*6,false,limbSwing,limbSwingAmount);
            progressRotationPrev(body,limbSwingAmount,Maths.rad(-15),0,0,1F);
            progressRotationPrev(sheath,limbSwingAmount,Maths.rad(-15),0,0,1F);
            progressRotationPrev(tailfront_left,limbSwingAmount,Maths.rad(15),0,0,1F);
            progressRotationPrev(tailfront_right,limbSwingAmount,Maths.rad(15),0,0,1F);
        }

        //══════ PASSENGER STATE (preserved) ══════
        if(e.isMissingEye()){eye.showModel=false;}else{eye.showModel=true;}
        if(e.isPassenger()){body.rotateAngleX+=Mth.HALF_PI;body.rotateAngleY+=Mth.HALF_PI*e.passengerIndex;sheath.setScale(1F,0.85F+Mth.sin(ageInTicks)*0.15F,1F);collar.rotationPointY-=Mth.sin(ageInTicks)*0.15F*12F;capsid.setScale(0.85F+Mth.sin(ageInTicks+2)*0.15F,1F+Mth.sin(ageInTicks)*0.15F,0.85F+Mth.sin(ageInTicks+2)*0.15F);mouth.rotationPointY+=(Mth.sin(ageInTicks)+1)*2F;tentP=-2F;}
        else{sheath.setScale(1,1,1);capsid.setScale(1,1,1);body.rotateAngleX-=phagePitch*fly*0.2F;}

        //══════ TENTACLE TRANSITION (preserved) ══════
        progressPositionPrev(body,tentP,0,-6,0,5F);
        progressRotationPrev(tailfront_left,tentP,0,0,Maths.rad(-45),5F);
        progressRotationPrev(tailmid_left,tentP,0,0,Maths.rad(-45),5F);
        progressRotationPrev(tailback_left,tentP,0,0,Maths.rad(-45),5F);
        progressRotationPrev(tailfront_right,tentP,0,0,Maths.rad(45),5F);
        progressRotationPrev(tailmid_right,tentP,0,0,Maths.rad(45),5F);
        progressRotationPrev(tailback_right,tentP,0,0,Maths.rad(45),5F);

        //══════ EYE TRACKING (preserved) ══════
        if(look!=null){Vec3 v=look.getEyePosition(pt),v1=e.getEyePosition(pt),v2=v.subtract(v1);float f=Mth.sqrt((float)(v2.x*v2.x+v2.z*v2.z))-totalYaw;eye.rotateAngleY+=-(float)(Mth.atan2(v2.x,v2.z))-totalYaw;eye.rotateAngleX+=-Mth.clamp(v2.y*0.5F,Mth.PI*-0.5F,Mth.PI*0.5F)+phagePitch*fly*0.2F;}
    }

    @Override public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){root.render(m,b,l,o);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

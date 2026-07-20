package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityPotoo;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelPotoo extends AdvancedEntityModel<EntityPotoo> {
    public final AdvancedModelBox root,body,tail,left_wing,right_wing,left_eye,right_eye,left_pupil,right_pupil,head,left_foot,right_foot;

    public ModelPotoo(){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-5.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-2.0F,-5.0F,-3.0F,4.0F,10.0F,7.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,3.0F,4.0F);body.addChild(tail);tail.setTextureOffset(16,0).addBox(-1.5F,0.0F,0.0F,3.0F,2.0F,8.0F,0.0F,false);
        left_wing=new AdvancedModelBox(this,"left_wing");left_wing.setPos(2.0F,-3.0F,0.0F);body.addChild(left_wing);left_wing.setTextureOffset(31,0).addBox(0.0F,0.0F,-1.0F,1.0F,6.0F,8.0F,0.0F,false);
        right_wing=new AdvancedModelBox(this,"right_wing");right_wing.setPos(-2.0F,-3.0F,0.0F);body.addChild(right_wing);right_wing.setTextureOffset(31,0).addBox(-1.0F,0.0F,-1.0F,1.0F,6.0F,8.0F,0.0F,true);
        left_eye=new AdvancedModelBox(this,"left_eye");left_eye.setPos(1.0F,-3.5F,-3.0F);body.addChild(left_eye);left_eye.setTextureOffset(0,18).addBox(-1.0F,-1.0F,-0.5F,2.0F,3.0F,1.0F,0.0F,false);
        left_pupil=new AdvancedModelBox(this,"left_pupil");left_pupil.setPos(0.0F,0.5F,-0.51F);left_eye.addChild(left_pupil);left_pupil.setTextureOffset(7,18).addBox(-0.5F,-1.0F,-0.01F,1.0F,2.0F,1.0F,0.0F,false);
        right_eye=new AdvancedModelBox(this,"right_eye");right_eye.setPos(-1.0F,-3.5F,-3.0F);body.addChild(right_eye);right_eye.setTextureOffset(0,18).addBox(-1.0F,-1.0F,-0.5F,2.0F,3.0F,1.0F,0.0F,true);
        right_pupil=new AdvancedModelBox(this,"right_pupil");right_pupil.setPos(0.0F,0.5F,-0.51F);right_eye.addChild(right_pupil);right_pupil.setTextureOffset(7,18).addBox(-0.5F,-1.0F,-0.01F,1.0F,2.0F,1.0F,0.0F,true);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,-3.5F,-3.0F);body.addChild(head);head.setTextureOffset(22,15).addBox(-1.5F,-1.5F,-3.0F,3.0F,3.0F,4.0F,0.0F,false);head.setTextureOffset(0,23).addBox(-2.5F,-1.0F,-9.0F,5.0F,2.0F,6.0F,0.0F,false);
        left_foot=new AdvancedModelBox(this,"left_foot");left_foot.setPos(1.0F,5.0F,2.0F);body.addChild(left_foot);left_foot.setTextureOffset(0,0).addBox(-1.0F,0.0F,-2.0F,2.0F,0.0F,3.0F,0.0F,false);
        right_foot=new AdvancedModelBox(this,"right_foot");right_foot.setPos(-1.0F,5.0F,2.0F);body.addChild(right_foot);right_foot.setTextureOffset(0,0).addBox(-1.0F,0.0F,-2.0F,2.0F,0.0F,3.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,left_wing,right_wing,left_eye,left_pupil,right_eye,right_pupil,head,left_foot,right_foot);}

    @Override
    public void setupAnim(EntityPotoo entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float partialTick=Minecraft.getInstance().getFrameTime();
        float flyProgress=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*partialTick;
        float perchProgress=entity.prevPerchProgress+(entity.perchProgress-entity.prevPerchProgress)*partialTick;
        float mouthProgress=entity.prevMouthProgress+(entity.mouthProgress-entity.prevMouthProgress)*partialTick;
        float flapSpeed=0.8F,flapDegree=0.2F,walkSpeed=1.6f,walkDegree=0.8f;
        float eyeScale=Mth.clamp((15F-entity.getEyeScale(10,partialTick))/15F,0F,1F);

        //══════ 🦉 POTOO — LIVING BRANCH (MASTER OF CAMOUFLAGE) ══════
        // IDENTITY: FREEZES perfectly still — becomes invisible against bark.
        // Giant yellow eyes bulge. Wide frog-mouth yawns open.
        // Sleeps with eyes closed to slits. Perches VERTICALLY like broken branch.
        // UNIQUE vs Shoebill (statue→strike), Toucan (fruit-tosser).

        // ── BREATHING: nearly imperceptible (camouflage) ──
        float breath=Mth.cos(ageInTicks*0.05F);body.rotationPointY+=breath*0.03F;

        // ── EYES: giant, bulging, scale with alertness ──
        this.left_pupil.setScale(0.5F,1.0F+eyeScale*2.1F,1.0F+eyeScale*2.1F);
        this.right_pupil.setScale(0.5F,1.0F+eyeScale*2.1F,1.0F+eyeScale*2.1F);
        this.left_pupil.rotationPointX+=0.5F;this.right_pupil.rotationPointX-=0.5F;

        // ── SLEEP: eyes close — potoo vanishes ──
        if(entity.isSleeping()){
            right_eye.showModel=false;right_pupil.showModel=false;
            left_eye.showModel=false;left_pupil.showModel=false;
        }else{
            right_eye.showModel=true;right_pupil.showModel=true;
            left_eye.showModel=true;left_pupil.showModel=true;
        }

        // ── FROG-MOUTH GAPE: potoo's massive yawn ──
        head.setScale(1.0F+mouthProgress*0.3F,1.0F,1.0F);

        // ── STATUE-STILL PERCH: barely moves ──
        progressRotationPrev(body,perchProgress,Maths.rad(-15),0,0,5F);
        progressRotationPrev(body,flyProgress,Maths.rad(20),0,0,5F);
        progressRotationPrev(head,flyProgress,Maths.rad(-15),0,0,5F);

        if(flyProgress>0){
            this.flap(left_wing,flapSpeed,flapDegree*5,true,0F,0F,ageInTicks,1);
            this.flap(right_wing,flapSpeed,flapDegree*5,false,0F,0F,ageInTicks,1);
            this.bob(body,flapSpeed*0.5F,flapDegree*10,true,ageInTicks,1);
        }else if(perchProgress<=0){
            this.bob(body,walkSpeed*1F,walkDegree*1.3F,true,limbSwing,limbSwingAmount);
            this.walk(left_foot,walkSpeed,walkDegree*1.85F,false,0F,0.2F,limbSwing,limbSwingAmount);
            this.walk(right_foot,walkSpeed,walkDegree*1.85F,true,0F,0.2F,limbSwing,limbSwingAmount);
        }

        // ── HEAD: slow, owl-like rotation ──
        if(!entity.isSleeping()){head.rotateAngleY+=netHeadYaw*0.25F*Mth.DEG_TO_RAD;}
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){ms.pushPose();ms.scale(0.5F,0.5F,0.5F);ms.translate(0.0D,1.5D,0D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
        else{ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
}

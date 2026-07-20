package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelToucan extends AdvancedEntityModel<EntityToucan> {
    public final AdvancedModelBox root,body,tail,left_wing,left_wingtip,right_wing,right_wingtip,head,beak,left_leg,right_leg;

    public ModelToucan(){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-4.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-2.0F,-4.0F,-4.0F,4.0F,5.0F,8.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-2.0F,4.0F);body.addChild(tail);tail.setTextureOffset(24,0).addBox(-1.5F,0.0F,0.0F,3.0F,2.0F,6.0F,0.0F,false);
        left_wing=new AdvancedModelBox(this,"left_wing");left_wing.setPos(2.0F,-3.0F,-2.0F);body.addChild(left_wing);left_wing.setTextureOffset(17,14).addBox(0.0F,-1.0F,-1.0F,1.0F,4.0F,8.0F,0.0F,false);
        left_wingtip=new AdvancedModelBox(this,"left_wingtip");left_wingtip.setPos(1.0F,3.0F,7.0F);left_wing.addChild(left_wingtip);left_wingtip.setTextureOffset(0,14).addBox(-1.0F,-2.0F,-0.5F,1.0F,4.0F,5.0F,0.0F,false);
        right_wing=new AdvancedModelBox(this,"right_wing");right_wing.setPos(-2.0F,-3.0F,-2.0F);body.addChild(right_wing);right_wing.setTextureOffset(17,14).addBox(-1.0F,-1.0F,-1.0F,1.0F,4.0F,8.0F,0.0F,true);
        right_wingtip=new AdvancedModelBox(this,"right_wingtip");right_wingtip.setPos(-1.0F,3.0F,7.0F);right_wing.addChild(right_wingtip);right_wingtip.setTextureOffset(0,14).addBox(0.0F,-2.0F,-0.5F,1.0F,4.0F,5.0F,0.0F,true);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,-1.0F,-4.0F);body.addChild(head);head.setTextureOffset(36,15).addBox(-2.0F,-4.0F,-3.0F,4.0F,5.0F,5.0F,0.0F,false);
        beak=new AdvancedModelBox(this,"beak");beak.setPos(0.0F,-2.0F,-3.0F);head.addChild(beak);beak.setTextureOffset(0,24).addBox(-1.5F,-1.0F,-5.0F,3.0F,2.0F,6.0F,0.0F,false);beak.setTextureOffset(13,26).addBox(-1.0F,1.0F,-5.0F,2.0F,1.0F,5.0F,0.0F,false);
        left_leg=new AdvancedModelBox(this,"left_leg");left_leg.setPos(1.0F,1.0F,1.0F);body.addChild(left_leg);left_leg.setTextureOffset(25,27).addBox(-1.0F,0.0F,-2.0F,2.0F,3.0F,3.0F,0.0F,false);
        right_leg=new AdvancedModelBox(this,"right_leg");right_leg.setPos(-1.0F,1.0F,1.0F);body.addChild(right_leg);right_leg.setTextureOffset(25,27).addBox(-1.0F,0.0F,-2.0F,2.0F,3.0F,3.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,left_wing,left_wingtip,right_wing,right_wingtip,head,beak,left_leg,right_leg);}

    @Override
    public void setupAnim(EntityToucan entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float flapSpeed=1.0F,flapDegree=0.2F,walkSpeed=1.2F,walkDegree=0.78F,idleSpeed=0.1F,idleDegree=0.1F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float flyProgress=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*partialTick;
        float runProgress=Math.max(0,(limbSwingAmount*5F)-flyProgress);
        float biteProgress=entity.prevPeckProgress+(entity.peckProgress-entity.prevPeckProgress)*partialTick;

        //══════ 🌈 TOUCAN — BIG-BILLED FRUIT-TOSSER ══════
        // IDENTITY: Massive colorful bill — light but looks heavy.
        // HOPS between branches (both feet together). TOSSES fruit up
        // and catches it (head tilts back). Bill bobs with each hop.
        // UNIQUE vs BlueJay (bossy hopper), Shoebill (statue→strike).

        // ── BREATHING: tropical bird ──
        float breath=Mth.cos(ageInTicks*0.13F);body.rotationPointY+=breath*0.06F;

        // ── BIG BILL BOBBING: bill looks heavy, creates head inertia ──
        float billBob=Mth.sin(limbSwing*walkSpeed)*walkDegree*0.4F*limbSwingAmount;
        head.rotationPointZ+=billBob;beak.rotationPointY+=billBob*0.5F;

        // ── FRUIT-TOSSING IDLE: head tilts back then forward ──
        if(limbSwingAmount<0.05F&&flyProgress<0.1F){
            head.rotateAngleX+=Mth.sin(ageInTicks*0.2F)*0.15F; // toss fruit
            beak.rotateAngleX+=Mth.sin(ageInTicks*0.25F+0.5F)*0.1F;
        }

        progressRotationPrev(head,biteProgress,Maths.rad(90),0,0,5F);
        progressRotationPrev(body,biteProgress,Maths.rad(-15),0,0,5F);
        progressRotationPrev(left_leg,biteProgress,Maths.rad(15),0,0,5F);
        progressRotationPrev(right_leg,biteProgress,Maths.rad(15),0,0,5F);
        progressPositionPrev(head,biteProgress,0,1,-2,5F);

        progressRotationPrev(head,runProgress,Maths.rad(-20),0,0,5F);
        progressRotationPrev(body,runProgress,Maths.rad(15),0,0,5F);
        progressRotationPrev(left_leg,runProgress,Maths.rad(-15),0,0,5F);
        progressRotationPrev(right_leg,runProgress,Maths.rad(-15),0,0,5F);

        progressRotationPrev(body,flyProgress,Maths.rad(20),0,0,5F);
        progressRotationPrev(head,flyProgress,Maths.rad(-15),0,0,5F);
        progressRotationPrev(left_leg,flyProgress,Maths.rad(55),0,0,5F);
        progressRotationPrev(right_leg,flyProgress,Maths.rad(55),0,0,5F);
        progressRotationPrev(right_wing,flyProgress,Maths.rad(-90),0,Maths.rad(90),5F);
        progressRotationPrev(left_wing,flyProgress,Maths.rad(-90),0,Maths.rad(-90),5F);
        progressPositionPrev(right_wing,flyProgress,0F,2F,1F,5f);
        progressPositionPrev(left_wing,flyProgress,0F,2F,1F,5f);
        progressPositionPrev(head,flyProgress,0,1F,-1F,5f);
        progressPositionPrev(body,flyProgress,0,1F,0F,5f);

        if(flyProgress>0){
            this.flap(left_wing,flapSpeed,flapDegree*5,true,0F,0F,ageInTicks,1);
            this.flap(right_wing,flapSpeed,flapDegree*5,false,0F,0F,ageInTicks,1);
            this.bob(body,flapSpeed*0.5F,flapDegree*5,true,ageInTicks,1);
            this.walk(head,flapSpeed,flapDegree*0.4F,true,2F,-0.1F,ageInTicks,1);
            this.walk(tail,flapSpeed,flapDegree*0.6F,true,3F,0.1F,ageInTicks,1);
        }else{
            // ── HOP: both feet together (toucan signature) ──
            this.bob(body,walkSpeed*1F,walkDegree*1.0F,true,limbSwing,limbSwingAmount);
            body.rotationPointY+=Mth.abs(Mth.sin(limbSwing*walkSpeed*1.5F))*walkDegree*2F*limbSwingAmount;
            this.walk(head,walkSpeed,walkDegree*0.3F,false,1F,0F,limbSwing,limbSwingAmount);
            this.flap(tail,walkSpeed,walkDegree*0.3F,false,1F,0F,limbSwing,limbSwingAmount);
        }

        this.walk(tail,idleSpeed,idleDegree*0.5F,false,1F,0F,ageInTicks,1);
        this.walk(head,idleSpeed,idleDegree*0.5F,false,0F,0.05F,ageInTicks,1);

        this.faceTarget(netHeadYaw,headPitch,1,head);
    }

    public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){ms.pushPose();ms.scale(0.5F,0.5F,0.5F);ms.translate(0.0D,1.5D,0D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
        else{ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
}

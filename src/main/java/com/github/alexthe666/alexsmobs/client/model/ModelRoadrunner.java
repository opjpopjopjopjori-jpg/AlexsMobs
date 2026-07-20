package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelRoadrunner extends AdvancedEntityModel<EntityRoadrunner> {
    private final AdvancedModelBox root,body,neck,head,beak,tail,left_wing,right_wing,left_spin,right_spin,left_leg,left_knee,left_foot,right_leg,right_knee,right_foot;

    public ModelRoadrunner(){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-9.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-3.0F,-4.0F,-5.0F,6.0F,6.0F,13.0F,0.0F,false);
        neck=new AdvancedModelBox(this,"neck");neck.setPos(0.0F,-2.0F,-5.0F);body.addChild(neck);neck.setTextureOffset(26,0).addBox(-1.5F,-5.0F,-1.5F,3.0F,7.0F,3.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,-4.0F,0.0F);neck.addChild(head);head.setTextureOffset(0,20).addBox(-1.5F,-2.0F,-3.0F,3.0F,3.0F,4.0F,0.0F,false);
        beak=new AdvancedModelBox(this,"beak");beak.setPos(0.0F,0.0F,-3.0F);head.addChild(beak);beak.setTextureOffset(0,28).addBox(-1.0F,-1.0F,-4.0F,2.0F,2.0F,4.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-2.0F,8.0F);body.addChild(tail);tail.setTextureOffset(39,0).addBox(-2.0F,-1.0F,0.0F,4.0F,3.0F,10.0F,0.0F,false);
        left_wing=new AdvancedModelBox(this,"left_wing");left_wing.setPos(3.0F,-3.0F,-2.0F);body.addChild(left_wing);left_wing.setTextureOffset(0,0).addBox(0.0F,-1.0F,-1.0F,1.0F,3.0F,6.0F,0.0F,false);
        right_wing=new AdvancedModelBox(this,"right_wing");right_wing.setPos(-3.0F,-3.0F,-2.0F);body.addChild(right_wing);right_wing.setTextureOffset(0,0).addBox(-1.0F,-1.0F,-1.0F,1.0F,3.0F,6.0F,0.0F,true);
        left_spin=new AdvancedModelBox(this,"left_spin");left_spin.setPos(0.0F,0.0F,0.0F);left_wing.addChild(left_spin);left_spin.setTextureOffset(15,20).addBox(0.0F,-0.5F,1.0F,1.0F,3.0F,5.0F,0.0F,false);
        right_spin=new AdvancedModelBox(this,"right_spin");right_spin.setPos(0.0F,0.0F,0.0F);right_wing.addChild(right_spin);right_spin.setTextureOffset(15,20).addBox(-1.0F,-0.5F,1.0F,1.0F,3.0F,5.0F,0.0F,true);
        left_leg=new AdvancedModelBox(this,"left_leg");left_leg.setPos(2.0F,2.0F,5.0F);body.addChild(left_leg);left_leg.setTextureOffset(26,11).addBox(-1.0F,0.0F,-1.0F,2.0F,10.0F,2.0F,0.0F,false);
        left_knee=new AdvancedModelBox(this,"left_knee");left_knee.setPos(0.0F,10.0F,0.0F);left_leg.addChild(left_knee);left_knee.setTextureOffset(0,0).addBox(-1.0F,0.0F,-1.0F,2.0F,6.0F,2.0F,0.0F,false);
        left_foot=new AdvancedModelBox(this,"left_foot");left_foot.setPos(0.0F,6.0F,0.0F);left_knee.addChild(left_foot);left_foot.setTextureOffset(35,14).addBox(-1.5F,0.0F,-4.0F,3.0F,1.0F,4.0F,0.0F,false);
        right_leg=new AdvancedModelBox(this,"right_leg");right_leg.setPos(-2.0F,2.0F,5.0F);body.addChild(right_leg);right_leg.setTextureOffset(26,11).addBox(-1.0F,0.0F,-1.0F,2.0F,10.0F,2.0F,0.0F,true);
        right_knee=new AdvancedModelBox(this,"right_knee");right_knee.setPos(0.0F,10.0F,0.0F);right_leg.addChild(right_knee);right_knee.setTextureOffset(0,0).addBox(-1.0F,0.0F,-1.0F,2.0F,6.0F,2.0F,0.0F,true);
        right_foot=new AdvancedModelBox(this,"right_foot");right_foot.setPos(0.0F,6.0F,0.0F);right_knee.addChild(right_foot);right_foot.setTextureOffset(35,14).addBox(-1.5F,0.0F,-4.0F,3.0F,1.0F,4.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,neck,head,beak,tail,left_wing,right_wing,left_spin,right_spin,left_leg,left_knee,left_foot,right_leg,right_knee,right_foot);}

    @Override
    public void setupAnim(EntityRoadrunner entityIn,float limbSwing,float limbSwingAmount,float ageInTicks,float netneckYaw,float neckPitch){
        this.resetToDefaultPose();
        float walkSpeed=0.9F,walkDegree=0.4F,idleSpeed=0.1F,idleDegree=0.4F;
        float runProgress=5F*limbSwingAmount;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float biteProgress=entityIn.prevAttackProgress+(entityIn.attackProgress-entityIn.prevAttackProgress)*partialTick;

        //══════ 🏃 ROADRUNNER — MEEP-MEEP GROUND SPRINTER ══════
        // IDENTITY: Ground-running cuckoo. Runs FAST on long legs.
        // Tail BOBS up/down like pump handle while running. Wings spread
        // for balance during tight turns. Head DARTS to catch lizards.
        // UNIQUE vs Emu (flightless runner), Shoebill (statue wader).

        // ── BREATHING: fast aerobic runner ──
        float breath=Mth.cos(ageInTicks*0.14F);body.rotationPointY+=breath*0.06F;

        // ── TAIL: pumps up/down while running (roadrunner signature) ──
        progressRotationPrev(tail,runProgress,Maths.rad(-10),0,0,5F);
        this.swing(tail,idleSpeed,idleDegree,false,0F,0F,ageInTicks,1);
        tail.rotateAngleX+=Mth.sin(limbSwing*walkSpeed*1.5F)*walkDegree*1.5F*limbSwingAmount;

        // ── HEAD: darts like striking snake ──
        progressRotationPrev(neck,biteProgress,Maths.rad(55),0,0,5F);
        progressRotationPrev(neck,runProgress,Maths.rad(25),0,0,5F);
        this.walk(neck,idleSpeed,idleDegree*0.2F,false,0F,-0.1F,ageInTicks,1);
        if(limbSwingAmount<0.05F){head.rotateAngleX+=Mth.sin(ageInTicks*0.5F)*0.08F;head.rotateAngleZ+=Mth.sin(ageInTicks*0.45F+1F)*0.06F;}

        // ── WINGS: spread for balance (short flights only) ──
        progressRotationPrev(right_wing,runProgress,Maths.rad(-10),Maths.rad(-30),Maths.rad(40),5F);
        progressRotationPrev(left_wing,runProgress,Maths.rad(-10),Maths.rad(30),Maths.rad(-40),5F);
        this.flap(left_wing,walkSpeed,walkDegree,true,2F,0.1F,limbSwing,limbSwingAmount);

        // ── FAST LEGS: long stride ground running ──
        progressRotationPrev(body,runProgress,Maths.rad(-5),0,0,5F);
        progressRotationPrev(right_leg,runProgress,Maths.rad(-15),0,0,5F);
        progressRotationPrev(left_leg,runProgress,Maths.rad(-15),0,0,5F);
        this.walk(right_leg,walkSpeed,walkDegree*1.2F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(left_leg,walkSpeed,walkDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
        this.walk(right_knee,walkSpeed,walkDegree*0.5F,false,0F,0F,limbSwing,limbSwingAmount);
        this.walk(left_knee,walkSpeed,walkDegree*0.5F,true,0F,0F,limbSwing,limbSwingAmount);
        // Body bobs with each stride
        body.rotationPointY+=Mth.abs(Mth.sin(limbSwing*walkSpeed))*walkDegree*2F*limbSwingAmount;

        this.faceTarget(netneckYaw,neckPitch,1.3F,neck,head);
    }
}

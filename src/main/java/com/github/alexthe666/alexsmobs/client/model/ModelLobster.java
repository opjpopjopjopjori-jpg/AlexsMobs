package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityLobster;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelLobster extends AdvancedEntityModel<EntityLobster> {
    private final AdvancedModelBox root, body, antenna_left, antenna_right, arm_left, hand_left, arm_right, hand_right, tail, tail2, legs_left, legs_right;

    public ModelLobster() {
        texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-1,0);root.addChild(body);body.setTextureOffset(0,11).addBox(-2,-1.4F,-7,4,2,7,0,false);
        antenna_left=new AdvancedModelBox(this,"al");antenna_left.setPos(1.4F,-0.5F,-7);body.addChild(antenna_left);setRotationAngle(antenna_left,-0.3054F,-0.4363F,0);antenna_left.setTextureOffset(18,18).addBox(0,-0.9F,-5,0,1,5,0,false);
        antenna_right=new AdvancedModelBox(this,"ar");antenna_right.setPos(-1.4F,-0.5F,-7);body.addChild(antenna_right);setRotationAngle(antenna_right,-0.3054F,0.4363F,0);antenna_right.setTextureOffset(18,20).addBox(0,-0.9F,-5,0,1,5,0,true);
        arm_left=new AdvancedModelBox(this,"al2");arm_left.setPos(1.7F,-0.4F,-5);body.addChild(arm_left);setRotationAngle(arm_left,0,0.6981F,0.3491F);arm_left.setTextureOffset(15,5).addBox(0,0,-0.5F,4,0,1,0,false);
        hand_left=new AdvancedModelBox(this,"hl");hand_left.setPos(4,0.4F,0.4F);arm_left.addChild(hand_left);setRotationAngle(hand_left,0,0.6981F,0);hand_left.setTextureOffset(0,21).addBox(0,-1,-1.9F,3,1,2,0,false);
        arm_right=new AdvancedModelBox(this,"ar2");arm_right.setPos(-1.7F,-0.4F,-5);body.addChild(arm_right);setRotationAngle(arm_right,0,-0.6981F,-0.3491F);arm_right.setTextureOffset(15,6).addBox(-4,0,-0.5F,4,0,1,0,true);
        hand_right=new AdvancedModelBox(this,"hr");hand_right.setPos(-4,0.4F,0.4F);arm_right.addChild(hand_right);setRotationAngle(hand_right,0,-0.6981F,0);hand_right.setTextureOffset(0,25).addBox(-3,-1,-1.9F,3,1,2,0,true);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0,-0.4F,0);body.addChild(tail);tail.setTextureOffset(0,0).addBox(-1.5F,-1,0,3,2,8,0,false);
        tail2=new AdvancedModelBox(this,"t2");tail2.setPos(0,0,6);tail.addChild(tail2);tail2.setTextureOffset(15,0).addBox(-4,0,0,8,0,4,0,false);
        legs_left=new AdvancedModelBox(this,"ll");legs_left.setPos(2,0.1F,-1.45F);body.addChild(legs_left);setRotationAngle(legs_left,0,0,0.3054F);legs_left.setTextureOffset(16,11).addBox(0,0,-3.55F,3,0,5,0,false);
        legs_right=new AdvancedModelBox(this,"lr");legs_right.setPos(-2,0.1F,-1.45F);body.addChild(legs_right);setRotationAngle(legs_right,0,0,-0.3054F);legs_right.setTextureOffset(25,11).addBox(-3,0,-3.55F,3,0,5,0,true);
        this.updateDefaultPose();
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,antenna_left,antenna_right,arm_left,arm_right,hand_left,hand_right,tail,tail2,legs_left,legs_right);}

    @Override
    public void setupAnim(EntityLobster entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🦞 LOBSTER — SIDEWAYS SCUTTLER WITH SNAP-ESCAPE ══════
        // IDENTITY: Primarily sideways scuttle. Claws wind up then snap.
        // Tail-fan escape burst at high speed. Antennae dip to taste substrate.
        float idleSpeed=0.1f,idleDegree=0.3f,walkSpeed=0.75F,walkDegree=0.65F;
        float partialTick=Minecraft.getInstance().getFrameTime();
        float attackProgress=entityIn.prevAttackProgress+(entityIn.attackProgress-entityIn.prevAttackProgress)*partialTick;

        // ── AAA CLAW SNAP: Wind-up then strike ─────────────────────
        // Phase 1 (0-2.5): claws OPEN — wind-up
        // Phase 2 (2.5-5): claws SNAP shut — strike
        float windUp=Mth.clamp(attackProgress*0.4F,0F,1F);
        float snap=Mth.clamp((attackProgress-2.5F)*0.4F,0F,1F);
        progressRotationPrev(arm_left,attackProgress,0,Maths.rad(25)*windUp-Maths.rad(20)*snap,Maths.rad(-15)*snap,5F);
        progressRotationPrev(arm_right,attackProgress,0,Maths.rad(-25)*windUp+Maths.rad(20)*snap,Maths.rad(15)*snap,5F);
        progressRotationPrev(hand_left,attackProgress,0,Maths.rad(-40)*windUp+Maths.rad(20)*snap,0,5F);
        progressRotationPrev(hand_right,attackProgress,0,Maths.rad(40)*windUp-Maths.rad(20)*snap,0,5F);

        // ── AAA SIDEWAYS SCUTTLE BIAS ──────────────────────────────
        body.rotationPointX+=Mth.sin(limbSwing*walkSpeed)*0.5F*limbSwingAmount;
        body.rotateAngleY+=Mth.sin(limbSwing*walkSpeed+1F)*0.08F*limbSwingAmount;

        // ── AAA TAIL-FAN ESCAPE BURST ──────────────────────────────
        float escapeBurst=Mth.clamp((limbSwingAmount-0.6F)*2.5F,0F,1F);
        tail.rotateAngleX-=0.5F*escapeBurst;
        tail2.rotateAngleX-=0.35F*escapeBurst;

        // ── AAA ANTENNA SUBSTRATE DIP ──────────────────────────────
        this.walk(antenna_left,idleSpeed*1.5F,idleDegree,false,0F,0F,ageInTicks,1);
        this.walk(antenna_right,idleSpeed*1.5F,idleDegree,true,0F,0F,ageInTicks,1);
        this.flap(antenna_left,idleSpeed*1.5F,idleDegree*0.22F,false,2,0.06F,ageInTicks,1);
        this.flap(antenna_right,idleSpeed*1.5F,idleDegree*0.2F,true,2.3F,0.06F,ageInTicks,1);
        antenna_left.rotateAngleX-=Mth.abs(Mth.sin(ageInTicks*0.08F))*0.2F;
        antenna_right.rotateAngleX-=Mth.abs(Mth.sin(ageInTicks*0.08F+1F))*0.2F;

        // ── Tail idle ──────────────────────────────────────────────
        this.walk(tail,idleSpeed,idleDegree*0.2F,false,1F,-0.1F,ageInTicks,1);
        this.walk(tail2,idleSpeed,idleDegree*0.15F,false,1F,0.1F,ageInTicks,1);

        // ── Walk ───────────────────────────────────────────────────
        this.walk(legs_left,walkSpeed,walkDegree*0.8F,false,0F,0F,limbSwing,limbSwingAmount);
        this.swing(legs_left,walkSpeed,walkDegree,false,1F,0.1F,limbSwing,limbSwingAmount);
        this.walk(legs_right,walkSpeed,walkDegree*0.8F,false,0F,0F,limbSwing,limbSwingAmount);
        this.swing(legs_right,walkSpeed,walkDegree,true,1F,0.1F,limbSwing,limbSwingAmount);
        this.bob(body,walkSpeed*0.5F,walkDegree*4F,true,limbSwing,limbSwingAmount);
        this.swing(arm_left,walkSpeed,walkDegree*1,true,2,0,limbSwing,limbSwingAmount);
        this.swing(arm_right,walkSpeed,walkDegree*1,true,2,0,limbSwing,limbSwingAmount);

        // ── AAA SCUTTLE ROCK ──────────────────────────────────────
        float scuttleRock=Mth.sin(limbSwing*walkSpeed*0.5F)*walkDegree*0.3F*limbSwingAmount;
        body.rotationPointX+=scuttleRock;
        body.rotateAngleZ+=scuttleRock*0.2F;

        // ── AAA TAIL CURL WAVE ────────────────────────────────────
        AdvancedModelBox[] tailChain={tail,tail2};
        this.chainSwing(tailChain,walkSpeed,walkDegree*0.5F,-1.5F,limbSwing,limbSwingAmount);

        // ── AAA BREATHING ─────────────────────────────────────────
        float breath=Mth.cos(ageInTicks*0.11F);
        body.rotationPointY+=breath*0.04F;
        body.setScale(1.0F+breath*0.015F,1.0F,1.0F);
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

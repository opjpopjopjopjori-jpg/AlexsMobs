package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySkelewag;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelSkelewag extends AdvancedEntityModel<EntitySkelewag> {
    private final AdvancedModelBox root, body, head, flag, left_fin, right_fin, tail, tail_fin;
    private ModelAnimator animator;

    public ModelSkelewag() {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0,-9,-3);root.addChild(body);
        body.setTextureOffset(17,11).addBox(-1,-1,-16,2,2,20,0,false);body.setTextureOffset(50,66).addBox(-0.5F,-16,-15,1,15,2,0,false);
        body.setTextureOffset(23,34).addBox(-0.5F,-12,-11,1,11,2,0,false);body.setTextureOffset(19,6).addBox(-0.5F,-9,-7,1,8,2,0,false);
        body.setTextureOffset(26,6).addBox(-0.5F,-7,-3,1,6,2,0,false);body.setTextureOffset(0,0).addBox(0,-4,1,0,3,2,0,false);body.setTextureOffset(45,34).addBox(-2,1,-12,4,6,10,0,false);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(-0.5F,0,-17);body.addChild(head);
        head.setTextureOffset(23,54).addBox(-2,-1,-7,5,7,8,0,false);head.setTextureOffset(50,51).addBox(-0.5F,-1,-19,2,2,12,0,false);head.setTextureOffset(42,17).addBox(0,-1,-31,1,1,12,0,false);
        flag=new AdvancedModelBox(this,"flag");flag.setRotationPoint(0.5F,-10,-15);body.addChild(flag);setRotationAngle(flag,0,0.1309F,0);flag.setTextureOffset(0,0).addBox(0,-5,0,0,12,18,0,false);
        left_fin=new AdvancedModelBox(this,"lf");left_fin.setRotationPoint(2,7,-10);body.addChild(left_fin);setRotationAngle(left_fin,0,0,0.7854F);left_fin.setTextureOffset(19,0).addBox(0,0,-2,10,1,4,0,false);
        right_fin=new AdvancedModelBox(this,"rf");right_fin.setRotationPoint(-2,7,-10);body.addChild(right_fin);setRotationAngle(right_fin,0,0,-0.7854F);right_fin.setTextureOffset(19,0).addBox(-10,0,-2,10,1,4,0,true);
        tail=new AdvancedModelBox(this,"tail");tail.setRotationPoint(0,0,5);body.addChild(tail);
        tail.setTextureOffset(23,34).addBox(-1,-1,-1,2,2,17,0,false);tail.setTextureOffset(9,0).addBox(0,-3,0,0,2,2,0,false);
        tail.setTextureOffset(57,17).addBox(0,-2,4,0,1,10,0,false);tail.setTextureOffset(42,0).addBox(-2,1,2,4,5,11,0,false);tail.setTextureOffset(0,0).addBox(0,4,5,0,6,8,0,false);
        tail_fin=new AdvancedModelBox(this,"tf");tail_fin.setRotationPoint(0,0,15);tail.addChild(tail_fin);tail_fin.setTextureOffset(0,31).addBox(0,-12,0,0,25,11,0,false);
        this.updateDefaultPose();animator=ModelAnimator.create();
    }

    public void animate(IAnimatedEntity e,float f,float f1,float f2,float f3,float f4){this.resetToDefaultPose();
        animator.update(e);animator.setAnimation(EntitySkelewag.ANIMATION_STAB);
        animator.startKeyframe(3);animator.move(body,0,0,10);animator.move(head,1,0,-1);animator.rotate(body,Maths.rad(-10),0,0);animator.rotate(head,Maths.rad(10),Maths.rad(-5),0);animator.endKeyframe();
        animator.setStaticKeyframe(2);animator.startKeyframe(2);animator.move(body,0,0,-10);animator.rotate(body,0,0,Maths.rad(-10));animator.endKeyframe();animator.resetKeyframe(3);
        animator.setAnimation(EntitySkelewag.ANIMATION_SLASH);
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(body,0,Maths.rad(-40),0);animator.rotate(tail,0,Maths.rad(20),0);animator.rotate(head,Maths.rad(10),Maths.rad(-10),Maths.rad(-10));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(body,0,Maths.rad(40),0);animator.rotate(tail,0,Maths.rad(-20),0);animator.rotate(head,Maths.rad(-10),Maths.rad(10),Maths.rad(10));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(body,0,Maths.rad(-40),0);animator.rotate(tail,0,Maths.rad(20),0);animator.rotate(head,Maths.rad(-10),Maths.rad(-10),Maths.rad(-10));animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,5);animator.rotate(body,0,Maths.rad(40),0);animator.rotate(tail,0,Maths.rad(-20),0);animator.rotate(head,Maths.rad(10),Maths.rad(10),Maths.rad(10));animator.endKeyframe();animator.resetKeyframe(5);
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,tail_fin,head,left_fin,right_fin,flag);}

    @Override
    public void setupAnim(EntitySkelewag entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //══════ 💀 SKELEWAG — DEATH-RATTLE BONE SERPENT ══════
        // IDENTITY: Body micro-vibrates (vertebrae rattle). Jaw clacks
        // periodically at idle. Fin asymmetry + visible breathing.
        // Flag ripples in water current. Unnatural undead motion.
        float idleSpeed=0.2F,idleDegree=0.3F,swimSpeed=0.55F,swimDegree=0.5F;
        float partialTick=net.minecraft.client.Minecraft.getInstance().getFrameTime();
        float landProgress=entity.prevOnLandProgress+(entity.onLandProgress-entity.prevOnLandProgress)*partialTick;
        float fallApartProgress=entity.deathTime>0?(entity.deathTime+partialTick)/20F:0;

        // ── State transitions (preserved) ──────────────────────────
        progressRotationPrev(body,landProgress,0,0,Maths.rad(-90),5F);
        progressPositionPrev(body,landProgress,0,3,6,5F);
        progressPositionPrev(tail,fallApartProgress,0,0,4,1F);progressPositionPrev(tail_fin,fallApartProgress,0,0,4,1F);
        progressPositionPrev(right_fin,fallApartProgress,0,1,2,1F);progressPositionPrev(left_fin,fallApartProgress,0,1,2,1F);
        progressPositionPrev(head,fallApartProgress,0,0,-1,1F);
        progressRotationPrev(right_fin,fallApartProgress,0,Maths.rad(25),0,1F);progressRotationPrev(left_fin,fallApartProgress,0,Maths.rad(-25),0,1F);

        // ── AAA BONE RATTLE: Body micro-vibration ──────────────────
        body.rotationPointX+=Mth.sin(ageInTicks*4.5F)*0.03F+Mth.sin(ageInTicks*7.3F)*0.02F;

        // ── AAA JAW CLACK: Periodic head twitch ────────────────────
        if(Mth.sin(ageInTicks*1.1F)>0.85F)head.rotateAngleZ+=0.04F;

        // ── AAA VISIBLE BREATHING ──────────────────────────────────
        float breath=Mth.cos(ageInTicks*0.11F);
        body.rotationPointY+=breath*0.05F;body.setScale(1+breath*0.015F,1,1+breath*0.015F);

        // ── Idle ───────────────────────────────────────────────────
        AdvancedModelBox[] tailBoxes={body,tail,tail_fin};
        this.chainSwing(tailBoxes,idleSpeed,idleDegree*0.1F,3,ageInTicks,1);
        this.bob(body,idleSpeed,idleDegree,false,ageInTicks,1);
        this.bob(left_fin,idleSpeed,idleDegree,false,ageInTicks,1);
        this.bob(right_fin,idleSpeed,idleDegree,false,ageInTicks,1);
        this.swing(flag,idleSpeed,idleDegree*0.2F,false,3,0.05F,ageInTicks,1);
        // AAA FLAG RIPPLE: Dual-frequency for non-repeating pattern
        this.flap(flag,0.25F,0.08F,false,0,0.03F,ageInTicks,1);
        this.flap(flag,0.15F,0.06F,true,2,0.02F,ageInTicks,1);

        // ── Swim ───────────────────────────────────────────────────
        this.chainSwing(tailBoxes,swimSpeed,swimDegree,-2,limbSwing,limbSwingAmount);
        this.swing(head,swimSpeed,swimDegree,true,-0.5F,0,limbSwing,limbSwingAmount);
        // AAA FIN ASYMMETRY: Slightly different freqs
        this.flap(left_fin,swimSpeed*0.15F,swimDegree*0.08F,true,-1,0.02F,limbSwing,limbSwingAmount);
        this.flap(right_fin,swimSpeed*0.17F,swimDegree*0.06F,false,-1.3F,0.02F,limbSwing,limbSwingAmount);
        this.bob(left_fin,swimSpeed,-1.5F*swimDegree,false,limbSwing,limbSwingAmount);
        this.bob(right_fin,swimSpeed,-1.5F*swimDegree,false,limbSwing,limbSwingAmount);
        this.swing(flag,swimSpeed,swimDegree*0.6F,false,2,0.3F,limbSwing,limbSwingAmount);

        // ── Head pitch ─────────────────────────────────────────────
        this.body.rotateAngleX+=headPitch*Mth.DEG_TO_RAD;
        this.head.rotateAngleX-=headPitch*0.5F*Mth.DEG_TO_RAD;
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

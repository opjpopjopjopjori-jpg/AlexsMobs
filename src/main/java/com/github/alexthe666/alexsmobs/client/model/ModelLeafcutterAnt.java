package com.github.alexthe666.alexsmobs.client.model;// Made with Blockbench 3.8.3

import com.github.alexthe666.alexsmobs.entity.EntityAnteater;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelLeafcutterAnt extends AdvancedEntityModel<EntityLeafcutterAnt> {
    private final AdvancedModelBox root, body, legfront_left, legfront_right, legmid_left, legmid_right, legback_left, legback_right, abdomen, head, leaf, leaf_r1, antenna_left, antenna_right, fangs;
    private ModelAnimator animator;

    public ModelLeafcutterAnt() {
        texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-4,0.125F);root.addChild(body);
        body.setTextureOffset(14,5).addBox(-1,-0.4F,-2.125F,2,2,5,0,false);
        legfront_left=new AdvancedModelBox(this,"lfl");legfront_left.setPos(1,1,-1.125F);body.addChild(legfront_left);setRotationAngle(legfront_left,0,0.2618F,0);legfront_left.setTextureOffset(0,19).addBox(0,-1,0,4,4,0,0,false);
        legfront_right=new AdvancedModelBox(this,"lfr");legfront_right.setPos(-1,1,-1.125F);body.addChild(legfront_right);setRotationAngle(legfront_right,0,-0.2618F,0);legfront_right.setTextureOffset(0,19).addBox(-4,-1,0,4,4,0,0,true);
        legmid_left=new AdvancedModelBox(this,"lml");legmid_left.setPos(1,1,0.875F);body.addChild(legmid_left);legmid_left.setTextureOffset(0,19).addBox(0,-1,0,4,4,0,0,false);
        legmid_right=new AdvancedModelBox(this,"lmr");legmid_right.setPos(-1,1,0.875F);body.addChild(legmid_right);legmid_right.setTextureOffset(0,19).addBox(-4,-1,0,4,4,0,0,true);
        legback_left=new AdvancedModelBox(this,"lbl");legback_left.setPos(1,1,2.875F);body.addChild(legback_left);setRotationAngle(legback_left,0,-0.3491F,0);legback_left.setTextureOffset(0,19).addBox(0,-1,0,4,4,0,0,false);
        legback_right=new AdvancedModelBox(this,"lbr");legback_right.setPos(-1,1,2.875F);body.addChild(legback_right);setRotationAngle(legback_right,0,0.3491F,0);legback_right.setTextureOffset(0,19).addBox(-4,-1,0,4,4,0,0,true);
        abdomen=new AdvancedModelBox(this,"abd");abdomen.setPos(0,0,2.875F);body.addChild(abdomen);abdomen.setTextureOffset(0,0).addBox(-2,-3,0,4,4,5,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,-0.5F,-2.125F);body.addChild(head);head.setTextureOffset(0,10).addBox(-2,-2,-4,4,3,5,0,false);
        leaf=new AdvancedModelBox(this,"leaf");leaf.setPos(0,1,-5);head.addChild(leaf);setRotationAngle(leaf,0.3491F,0,0);
        leaf_r1=new AdvancedModelBox(this,"lr1");leaf_r1.setPos(0,0,2);leaf.addChild(leaf_r1);setRotationAngle(leaf_r1,0.5672F,0,0);leaf_r1.setTextureOffset(6,5).addBox(0,-14,-6,0,14,13,0,false);
        antenna_left=new AdvancedModelBox(this,"al");antenna_left.setPos(0,-2,-4);head.addChild(antenna_left);setRotationAngle(antenna_left,-0.3927F,-0.2618F,0.1745F);antenna_left.setTextureOffset(12,13).addBox(0,0,-6,5,0,6,0,false);
        antenna_right=new AdvancedModelBox(this,"ar");antenna_right.setPos(0,-2,-4);head.addChild(antenna_right);setRotationAngle(antenna_right,-0.3927F,0.2618F,-0.1745F);antenna_right.setTextureOffset(12,13).addBox(-5,0,-6,5,0,6,0,true);
        fangs=new AdvancedModelBox(this,"fangs");fangs.setPos(0,1,-5);head.addChild(fangs);fangs.setTextureOffset(14,0).addBox(-1,-1,-1,2,1,2,0,false);
        this.updateDefaultPose();animator=ModelAnimator.create();
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(legback_left,leaf,leaf_r1,legback_right,root,body,legfront_left,legfront_right,legmid_left,legmid_right,abdomen,head,antenna_left,antenna_right,fangs);}

    public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){float f=1.5F;head.setScale(f,f,f);head.setShouldScaleChildren(true);m.pushPose();m.scale(0.5F,0.5F,0.5F);m.translate(0,1.5,0.125);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();head.setScale(1,1,1);}
        else{m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }

    public void animate(IAnimatedEntity e,float f,float f1,float f2,float f3,float f4){this.resetToDefaultPose();
        animator.update(e);animator.setAnimation(EntityLeafcutterAnt.ANIMATION_BITE);
        animator.startKeyframe(5);animator.move(body,0,0,-5);animator.rotate(head,Maths.rad(-25),0,0);animator.rotate(abdomen,Maths.rad(25),0,0);animator.rotate(antenna_left,Maths.rad(-25),Maths.rad(-25),0);animator.rotate(antenna_right,Maths.rad(-25),Maths.rad(25),0);animator.endKeyframe();
        animator.startKeyframe(5);animator.move(body,0,0,2);animator.rotate(head,Maths.rad(25),0,0);animator.endKeyframe();animator.resetKeyframe(3);
    }

    @Override
    public void setupAnim(EntityLeafcutterAnt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //══════ 🐜 LEAFCUTTER ANT — SWAYING-LEAF MARCHER ══════
        // IDENTITY: Tripedal march with leg-stride differentiation.
        // Carried leaf sways with inertia. Antennae tap ground for
        // pheromone trails. Mandibles micro-articulate at idle.
        // UNIQUE vs Cockroach: straight-line marcher, carries leaf.
        // UNIQUE vs TarantulaHawk: ground worker, not aerial hunter.

        float idleSpeed = 0.25F, idleDegree = 0.25F, walkSpeed = 1F, walkDegree = 1F;

        // ── AAA LEG-STRIDE DIFFERENTIATION ─────────────────────────
        float fLegDg = walkDegree * 0.7F, mLegDg = walkDegree * 0.9F, bLegDg = walkDegree * 1.15F;
        float offsetleft = 2.7F;
        // Tripod A: R-back, R-front
        this.swing(legback_right, walkSpeed, bLegDg * 1.2F, false, 0, 0.2F, limbSwing, limbSwingAmount);
        this.flap(legback_right, walkSpeed, bLegDg * 0.8F, false, -1.5F, 0.4F, limbSwing, limbSwingAmount);
        this.swing(legfront_right, walkSpeed, fLegDg, false, 0, -0.3F, limbSwing, limbSwingAmount);
        this.flap(legfront_right, walkSpeed, fLegDg * 0.8F, false, -1.5F, 0.4F, limbSwing, limbSwingAmount);
        this.swing(legmid_left, walkSpeed, mLegDg, false, 0, 0F, limbSwing, limbSwingAmount);
        this.flap(legmid_left, walkSpeed, mLegDg * 0.8F, false, -1.5F, -0.4F, limbSwing, limbSwingAmount);
        // Tripod B: L-back, L-front (offset)
        this.swing(legback_left, walkSpeed, -bLegDg * 1.2F, false, offsetleft, -0.2F, limbSwing, limbSwingAmount);
        this.flap(legback_left, walkSpeed, bLegDg * 0.8F, false, offsetleft - 1.5F, -0.4F, limbSwing, limbSwingAmount);
        this.swing(legfront_left, walkSpeed, -fLegDg, false, offsetleft, 0.3F, limbSwing, limbSwingAmount);
        this.flap(legfront_left, walkSpeed, fLegDg * 0.8F, false, offsetleft + 1.5F, -0.4F, limbSwing, limbSwingAmount);
        this.swing(legmid_right, walkSpeed, -mLegDg, false, offsetleft, 0, limbSwing, limbSwingAmount);
        this.flap(legmid_right, walkSpeed, mLegDg * 0.8F, false, offsetleft - 1.5F, 0.4F, limbSwing, limbSwingAmount);

        // ── AAA FIXED BODY BOB — removed *2F jitter ────────────────
        this.bob(body, walkSpeed, walkDegree * 0.6F, false, limbSwing, limbSwingAmount);

        // ── AAA ANTENNA GROUND-TAP ─────────────────────────────────
        this.swing(antenna_left, idleSpeed, idleDegree * 1.15F, true, 1, 0.12F, ageInTicks, 1);
        this.swing(antenna_right, idleSpeed, idleDegree * 1.1F, false, 1.3F, 0.1F, ageInTicks, 1);
        this.walk(antenna_left, idleSpeed, idleDegree * 0.3F, false, -1, -0.06F, ageInTicks, 1);
        this.walk(antenna_right, idleSpeed, idleDegree * 0.28F, false, -1, -0.06F, ageInTicks, 1);
        this.flap(antenna_left, idleSpeed, idleDegree * 0.25F, false, 3, 0.06F, ageInTicks, 1);
        this.flap(antenna_right, idleSpeed, idleDegree * 0.22F, true, 3.3F, 0.06F, ageInTicks, 1);
        // Downward bias during walk for ground-tapping
        antenna_left.rotateAngleX -= 0.12F * limbSwingAmount;
        antenna_right.rotateAngleX -= 0.12F * limbSwingAmount;

        // ── AAA LEAF INERTIA SWAY ──────────────────────────────────
        leaf.rotateAngleY += Mth.sin(limbSwing * walkSpeed + 1.5F) * walkDegree * 0.18F * limbSwingAmount;
        leaf_r1.rotateAngleY += Mth.sin(limbSwing * walkSpeed + 2.0F) * walkDegree * 0.24F * limbSwingAmount;

        // ── AAA MANDIBLE IDLE ──────────────────────────────────────
        this.walk(fangs, 0.15F, 0.05F, true, 0, 0.01F, ageInTicks, 1);

        // ── Body rock + abdomen drag (preserved, enhanced) ─────────
        float tripodRock = Mth.sin(limbSwing * walkSpeed * 0.7F) * walkDegree * 0.2F * limbSwingAmount;
        body.rotationPointX += tripodRock;
        abdomen.rotationPointX += tripodRock * 0.6F;
        body.rotateAngleZ += tripodRock * 0.15F;
        this.swing(abdomen, walkSpeed, walkDegree * 0.35F, false, 2.5F, 0, limbSwing, limbSwingAmount);

        // ── Breathing ──────────────────────────────────────────────
        float breath = Mth.cos(ageInTicks * 0.2F);
        body.rotationPointY += breath * 0.04F;
        abdomen.setScale(1.0F + breath * 0.02F, 1.0F, 1.0F + breath * 0.01F);

        this.faceTarget(netHeadYaw, headPitch, 1.2F, head);
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
    public void animateAnteater(EntityAnteater anteater,float pt){this.resetToDefaultPose();float t=anteater.tickCount+pt;this.swing(root,0.5F,0.8F,false,0,0,t,1);}
}

package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityGazelle;
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

public class ModelGazelle extends AdvancedEntityModel<EntityGazelle> {
    private final AdvancedModelBox body,neck,head,earL,earR,snout,hornL,hornR,tail,frontlegR,frontlegL,backlegL,backlegR;
    private ModelAnimator animator;

    public ModelGazelle() {
        texWidth = 64;texHeight = 64;
        body = new AdvancedModelBox(this, "body");body.setPos(0.0F, 20.8F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-4.0F, -16.8F, -9.0F, 8.0F, 8.0F, 18.0F, 0.0F, false);
        neck = new AdvancedModelBox(this, "neck");neck.setPos(0.0F, -14.8F, -8.0F);body.addChild(neck);
        setRotationAngle(neck, 0.2618F, 0.0F, 0.0F);neck.setTextureOffset(0, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        head = new AdvancedModelBox(this, "head");head.setPos(0.0F, -7.0F, 0.0F);neck.addChild(head);
        setRotationAngle(head, -0.2618F, 0.0F, 0.0F);head.setTextureOffset(0, 27).addBox(-2.5F, -4.0F, -3.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);
        earL = new AdvancedModelBox(this, "earL");earL.setPos(1.5F, -3.3F, 0.5F);head.addChild(earL);
        setRotationAngle(earL, -0.2618F, -0.5236F, 0.6109F);earL.setTextureOffset(0, 38).addBox(-0.5F, -3.7F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        earR = new AdvancedModelBox(this, "earR");earR.setPos(-1.5F, -3.3F, 0.5F);head.addChild(earR);
        setRotationAngle(earR, -0.2618F, 0.5236F, -0.6109F);earR.setTextureOffset(0, 38).addBox(-1.5F, -3.7F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, true);
        snout = new AdvancedModelBox(this, "snout");snout.setPos(0.0F, -0.5F, -2.9F);head.addChild(snout);
        snout.setTextureOffset(34, 27).addBox(-1.5F, -1.5F, -3.1F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        hornL = new AdvancedModelBox(this, "hornL");hornL.setPos(1.3F, -3.4F, -1.9F);head.addChild(hornL);
        setRotationAngle(hornL, -0.2618F, 0.0F, 0.2618F);hornL.setTextureOffset(35, 0).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        hornR = new AdvancedModelBox(this, "hornR");hornR.setPos(-1.3F, -3.4F, -1.9F);head.addChild(hornR);
        setRotationAngle(hornR, -0.2618F, 0.0F, -0.2618F);hornR.setTextureOffset(35, 0).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, true);
        tail = new AdvancedModelBox(this, "tail");tail.setPos(0.0F, -13.8F, 9.0F);body.addChild(tail);
        setRotationAngle(tail, 0.3491F, 0.0F, 0.0F);tail.setTextureOffset(35, 12).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, false);
        frontlegR = new AdvancedModelBox(this, "frontlegR");frontlegR.setPos(2.5F, -6.8F, -6.5F);body.addChild(frontlegR);
        frontlegR.setTextureOffset(34, 34).addBox(-6.5F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, true);
        frontlegL = new AdvancedModelBox(this, "frontlegL");frontlegL.setPos(2.5F, -6.8F, -6.5F);body.addChild(frontlegL);
        frontlegL.setTextureOffset(34, 34).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, false);
        backlegL = new AdvancedModelBox(this, "backlegL");backlegL.setPos(2.5F, -7.8F, 7.5F);body.addChild(backlegL);
        backlegL.setTextureOffset(21, 27).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, false);
        backlegR = new AdvancedModelBox(this, "backlegR");backlegR.setPos(-2.5F, -7.8F, 7.5F);body.addChild(backlegR);
        backlegR.setTextureOffset(21, 27).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, true);
        this.updateDefaultPose();animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        animator.update(entity);
        animator.setAnimation(EntityGazelle.ANIMATION_FLICK_TAIL);
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, Maths.rad(50));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, 0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, Maths.rad(-50));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, Maths.rad(50));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, 0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(tail, 0, 0, Maths.rad(-50));animator.endKeyframe();
        animator.resetKeyframe(2);
        animator.setAnimation(EntityGazelle.ANIMATION_FLICK_EARS);
        animator.startKeyframe(2);animator.rotate(neck, Maths.rad(25), Maths.rad(20), 0);animator.rotate(head, Maths.rad(5), 0, Maths.rad(10));animator.rotate(body, 0, Maths.rad(5), 0);animator.rotate(earR, 0, Maths.rad(25), Maths.rad(40));animator.rotate(earL, 0, Maths.rad(-25), Maths.rad(-40));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(neck, Maths.rad(25), Maths.rad(-20), 0);animator.rotate(head, Maths.rad(5), 0, Maths.rad(-10));animator.rotate(body, 0, Maths.rad(-5), 0);animator.rotate(earR, 0, 0, 0);animator.rotate(earL, 0, 0, 0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(neck, Maths.rad(25), Maths.rad(20), 0);animator.rotate(head, Maths.rad(5), 0, Maths.rad(10));animator.rotate(body, 0, Maths.rad(5), 0);animator.rotate(earR, 0, Maths.rad(5), Maths.rad(-40));animator.rotate(earL, 0, Maths.rad(-5), Maths.rad(40));animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(neck, Maths.rad(25), Maths.rad(-20), 0);animator.rotate(head, Maths.rad(5), 0, Maths.rad(-10));animator.rotate(earR, 0, Maths.rad(25), Maths.rad(40));animator.rotate(earL, 0, Maths.rad(-25), Maths.rad(-40));animator.rotate(body, 0, Maths.rad(-5), 0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(neck, Maths.rad(25), Maths.rad(20), 0);animator.rotate(head, Maths.rad(5), 0, Maths.rad(10));animator.rotate(body, 0, Maths.rad(5), 0);animator.rotate(earR, 0, 0, 0);animator.rotate(earL, 0, 0, 0);animator.endKeyframe();
        animator.startKeyframe(2);animator.rotate(neck, 0, 0, 0);animator.rotate(head, 0, 0, 0);animator.rotate(body, 0, Maths.rad(-5), 0);animator.rotate(earR, 0, Maths.rad(5), Maths.rad(-40));animator.rotate(earL, 0, Maths.rad(-5), Maths.rad(40));animator.endKeyframe();
        animator.resetKeyframe(7);
        animator.setAnimation(EntityGazelle.ANIMATION_EAT_GRASS);
        animator.startKeyframe(5);animator.rotate(neck, Maths.rad(100), 0, 0);animator.rotate(head, Maths.rad(-40), 0, 0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck, Maths.rad(120), 0, 0);animator.rotate(head, Maths.rad(-50), 0, 0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck, Maths.rad(100), 0, 0);animator.rotate(head, Maths.rad(-40), 0, 0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck, Maths.rad(120), 0, 0);animator.rotate(head, Maths.rad(-50), 0, 0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck, Maths.rad(100), 0, 0);animator.rotate(head, Maths.rad(-40), 0, 0);eatPose();animator.endKeyframe();
        animator.startKeyframe(4);animator.rotate(neck, Maths.rad(120), 0, 0);animator.rotate(head, Maths.rad(-50), 0, 0);eatPose();animator.endKeyframe();
        animator.resetKeyframe(5);
    }
    private void eatPose(){
        animator.rotate(body, Maths.rad(10), 0, 0);animator.move(body, 0, 2, 0);
        animator.rotate(backlegL, Maths.rad(-10), 0, 0);animator.rotate(backlegR, Maths.rad(-10), 0, 0);
        animator.rotate(frontlegL, Maths.rad(-10), 0, 0);animator.rotate(frontlegR, Maths.rad(-10), 0, 0);
        animator.move(frontlegL, 0.1F, -3, 0F);animator.move(frontlegR, -0.1F, -3, 0F);
        animator.move(backlegL, 0, -1, 0);animator.move(backlegR, 0, -1, 0);animator.move(neck, 0, 1, 0);
    }

    @Override
    public void setupAnim(EntityGazelle entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean running=entityIn.isRunning();
        float runSpeed=0.7F,runDegree=0.7F,walkSpeed=0.7F,walkDegree=0.4F;

        //══════ 🦌 GAZELLE — NERVOUS STOTTING SPRINTER ══════
        // IDENTITY: Ultralight, stiff-spine sprinter. STOTTS (springs straight up)
        // when alarmed. Ears CONSTANTLY flick independently scanning for danger.
        // Tail flicks nervously. Gallop has SUSPENSION phase (all 4 off ground).
        // UNIQUE vs Bison (heavy grazer), Moose (high-step), Rhino (tank), Tusklin (rooter).

        // ── BREATHING: rapid oxygen-hungry sprinter ──
        float breath=Mth.cos(ageInTicks*0.13F);
        body.setScale(1.0F,1.0F+breath*0.015F,1.0F);body.rotationPointY+=breath*0.07F;snout.rotationPointY+=breath*0.03F;

        // ── EARS: CONSTANT independent scanning for danger ──
        // Gazelles survive by detecting predators early — ears NEVER stop
        this.flap(earL,0.3F,0.1F,false,1F,0,ageInTicks,1);
        this.flap(earR,0.28F,0.1F,true,1.3F,0,ageInTicks,1);
        earL.rotateAngleY+=Mth.sin(ageInTicks*0.45F)*0.3F; // left ear rotates independently
        earR.rotateAngleY+=Mth.sin(ageInTicks*0.5F+1.5F)*0.3F; // right ear different phase

        // ── NECK: subtle scanning ──
        this.walk(neck,0.05F,0.1F,false,0F,0F,ageInTicks,1);

        if(running){
            // ── STOTTING GALLOP: spring up, all 4 legs leave ground ──
            // This is THE gazelle signature move — bouncing straight up
            this.walk(frontlegR,runSpeed,runDegree*1.3F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(frontlegL,runSpeed,runDegree*1.3F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(backlegR,runSpeed,runDegree*1.3F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(backlegL,runSpeed,runDegree*1.3F,false,0F,0F,limbSwing,limbSwingAmount);
            // STOTTING BOUNCE: the entire gazelle springs vertically
            body.rotationPointY+=Mth.sin(limbSwing*runSpeed*2F)*runDegree*5F*limbSwingAmount;
            // HEAD PERFECTLY LEVEL during stott (vestibular stabilization)
            head.rotateAngleX-=Mth.sin(limbSwing*runSpeed*2F)*runDegree*0.15F*limbSwingAmount;
            head.rotationPointY-=Mth.abs(Mth.sin(limbSwing*runSpeed))*0.25F*limbSwingAmount;
            this.flap(tail,runSpeed*0.5F,0.2F,false,1F,0,limbSwing,limbSwingAmount);
        }else{
            // ── DIAGONAL WALK: light, precise hoof placement ──
            this.walk(frontlegR,walkSpeed,walkDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
            this.walk(frontlegL,walkSpeed,walkDegree*1.2F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(backlegR,walkSpeed,walkDegree*1.2F,false,0F,0F,limbSwing,limbSwingAmount);
            this.walk(backlegL,walkSpeed,walkDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
            // Delicate bounce (not heavy like bison)
            body.rotationPointY+=Mth.abs(Mth.sin(limbSwing*walkSpeed))*walkDegree*0.5F*limbSwingAmount;
        }

        // ── TAIL: nervous flicking (fly swatter + alarm signal) ──
        this.flap(tail,0.3F,0.15F,false,0.5F,0,ageInTicks,1-limbSwingAmount*0.5F);

        this.faceTarget(netHeadYaw, headPitch, 2, neck, head);
    }

    public void renderToBuffer(PoseStack ms, VertexConsumer b, int l, int o, float r, float g, float bl, float a) {
        if(this.young){float f=1.75F;head.setScale(f,f,f);hornL.setScale(0.4F,0.4F,0.4F);hornR.setScale(0.4F,0.4F,0.4F);head.setShouldScaleChildren(true);ms.pushPose();ms.scale(0.5F,0.5F,0.5F);ms.translate(0.0D,1.5D,0.125D);parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();head.setScale(1,1,1);hornL.setScale(1,1,1);hornR.setScale(1,1,1);}
        else{ms.pushPose();parts().forEach(p->p.render(ms,b,l,o,r,g,bl,a));ms.popPose();}
    }
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(body);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(body,neck,head,earL,earR,backlegL,backlegR,frontlegL,frontlegR,snout,hornL,hornR,tail);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

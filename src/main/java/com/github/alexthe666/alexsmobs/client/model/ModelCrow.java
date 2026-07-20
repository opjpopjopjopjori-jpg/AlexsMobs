package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCrow extends AdvancedEntityModel<EntityCrow> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox leg_right;
    public final AdvancedModelBox wing_left;
    public final AdvancedModelBox wing_right;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox head;
    public final AdvancedModelBox beak;

    public ModelCrow() {
        texWidth = 32;
        texHeight = 32;
        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);
        body = new AdvancedModelBox(this, "body");
        body.setPos(0.0F, -2.1F, 0.0F);
        root.addChild(body);
        setRotationAngle(body, 1.0036F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
        leg_left = new AdvancedModelBox(this, "leg_left");
        leg_left.setPos(0.9F, 0.0F, 0.0F);
        body.addChild(leg_left);
        setRotationAngle(leg_left, 0.5672F, 0.0F, 0.0F);
        leg_left.setTextureOffset(0, 17).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        leg_right = new AdvancedModelBox(this, "leg_right");
        leg_right.setPos(-0.9F, 0.0F, 0.0F);
        body.addChild(leg_right);
        setRotationAngle(leg_right, 0.5672F, 0.0F, 0.0F);
        leg_right.setTextureOffset(0, 17).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, true);
        wing_left = new AdvancedModelBox(this, "wing_left");
        wing_left.setPos(1.5F, -4.9F, 1.7F);
        body.addChild(wing_left);
        setRotationAngle(wing_left, 0.0436F, 0.0F, 0.0F);
        wing_left.setTextureOffset(13, 13).addBox(-0.5F, 0.0F, -1.7F, 1.0F, 6.0F, 3.0F, 0.0F, false);
        wing_right = new AdvancedModelBox(this, "wing_right");
        wing_right.setPos(-1.5F, -4.9F, 1.7F);
        body.addChild(wing_right);
        setRotationAngle(wing_right, 0.0436F, 0.0F, 0.0F);
        wing_right.setTextureOffset(13, 13).addBox(-0.5F, 0.0F, -1.7F, 1.0F, 6.0F, 3.0F, 0.0F, true);
        tail = new AdvancedModelBox(this, "tail");
        tail.setPos(0.0F, -0.1F, 3.0F);
        body.addChild(tail);
        setRotationAngle(tail, -0.1309F, 0.0F, 0.0F);
        tail.setTextureOffset(13, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 2.0F, -0.1F, false);
        head = new AdvancedModelBox(this, "head");
        head.setPos(0.0F, -4.8F, 1.7F);
        body.addChild(head);
        setRotationAngle(head, -0.7418F, 0.0F, 0.0F);
        head.setTextureOffset(0, 9).addBox(-1.5F, -2.8F, -1.5F, 3.0F, 4.0F, 3.0F, -0.2F, false);
        beak = new AdvancedModelBox(this, "beak");
        beak.setPos(0.0F, -1.4F, -1.9F);
        head.addChild(beak);
        beak.setTextureOffset(13, 7).addBox(-0.5F, -1.0F, -1.8F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, head, beak, leg_left, leg_right, tail, body, wing_left, wing_right);
    }

    @Override
    public void setupAnim(EntityCrow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.resetToDefaultPose();
        float flSp=0.8F,flDg=0.2F,wkSp=1.2F,wkDg=0.78F,idSp=0.1F,idDg=0.1F;
        float pt=Minecraft.getInstance().getFrameTime();
        float fly=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*pt;
        float sit=entity.prevSitProgress+(entity.sitProgress-entity.prevSitProgress)*pt;
        float runP=Math.max(0,(limbSwingAmount*5F)-fly);
        float bite=entity.prevAttackProgress+(entity.attackProgress-entity.prevAttackProgress)*pt;

        //══════ 🐦‍⬛ CROW — INTELLIGENT SCAVENGER ══════
        // IDENTITY: Crow WALKS with alternating legs at normal speed,
        // HOPS only when in a hurry. Head CONSTANTLY tilts side-to-side
        // (monocular depth perception). Quick nervous movements. Pecks ground.
        // UNIQUE from Seagull: crow is jerky & investigative (head tilt).
        // Seagull is relaxed & waddling (puffed chest, one-leg stand).

        // ── BREATHING: fast alert bird ──
        float breath=Mth.cos(ageInTicks*0.18F);
        body.setScale(1.0F,1.0F+breath*0.02F,1.0F);
        body.rotationPointY+=breath*0.04F;
        beak.rotateAngleX+=Mth.cos(ageInTicks*0.18F+0.15F)*0.02F;

        // ── HEAD TILT: crow's signature intelligence gesture ──
        // Monocular vision — tilts head to gauge depth of objects on ground
        head.rotateAngleZ+=Mth.sin(ageInTicks*0.28F+1.5F)*0.28F*(1-limbSwingAmount*0.4F);
        head.rotateAngleX+=Mth.sin(ageInTicks*0.22F)*0.06F*(1-limbSwingAmount*0.5F);
        if(limbSwingAmount<0.05F){head.rotationPointX+=Mth.sin(ageInTicks*0.3F)*0.12F;}
        // Beak opens slightly while calling/breathing
        beak.rotationPointY+=breath*0.02F;

        // ── BITE ──
        progressRotationPrev(head,bite,Maths.rad(60),0,0,5F);
        progressRotationPrev(body,bite,Maths.rad(25),0,0,5F);
        progressRotationPrev(leg_left,bite,Maths.rad(-25),0,0,5F);
        progressRotationPrev(leg_right,bite,Maths.rad(-25),0,0,5F);

        // ── FLIGHT TRANSITIONS ──
        progressRotationPrev(body,fly,Maths.rad(20),0,0,5F);
        progressRotationPrev(head,fly,Maths.rad(-15),0,0,5F);
        progressRotationPrev(leg_left,fly,Maths.rad(55),0,0,5F);
        progressRotationPrev(leg_right,fly,Maths.rad(55),0,0,5F);
        progressRotationPrev(wing_right,fly,Maths.rad(-90),Maths.rad(90),0,5F);
        progressRotationPrev(wing_left,fly,Maths.rad(-90),Maths.rad(-90),0,5F);
        progressPositionPrev(wing_right,fly,0F,2F,1F,5f);
        progressPositionPrev(wing_left,fly,0F,2F,1F,5f);

        // ── RUNNING TRANSITION ──
        progressRotationPrev(body,runP,Maths.rad(15),0,0,5F);
        progressRotationPrev(head,runP,Maths.rad(-20),0,0,5F);
        progressRotationPrev(leg_left,runP,Maths.rad(-15),0,0,5F);
        progressRotationPrev(leg_right,runP,Maths.rad(-15),0,0,5F);

        if(fly>0){
            // ── FLIGHT: powerful downward wing thrust ──
            float powerFlap=Mth.abs(Mth.sin(ageInTicks*flSp*2F));
            this.swing(wing_right,flSp,flDg*5,true,0F,0F,ageInTicks,1);
            this.swing(wing_left,flSp,flDg*5,false,0F,0F,ageInTicks,1);
            this.bob(body,flSp*0.5F,flDg*4,true,ageInTicks,1);
            this.walk(head,flSp,flDg*0.2F,true,2F,-0.1F,ageInTicks,1);
            this.flap(tail,flSp*0.5F,0.08F,false,1F,0F,ageInTicks,1);
            wing_right.rotateAngleX-=powerFlap*0.15F;wing_left.rotateAngleX-=powerFlap*0.15F;
        }else if(runP>0.3F){
            // ── FAST RUN → HOP: both legs together, springy ──
            float hop=Mth.sin(limbSwing*wkSp*1.5F);
            float hopUp=hop>0?hop*wkDg*3F*limbSwingAmount:0;
            body.rotationPointY+=hopUp;
            leg_left.rotationPointY+=hopUp*0.8F;leg_right.rotationPointY+=hopUp*0.8F;
            this.bob(body,wkSp*1F,wkDg*1.3F,true,limbSwing,limbSwingAmount);
            this.walk(head,wkSp,wkDg*0.4F,false,2F,-0.01F,limbSwing,limbSwingAmount);
            this.flap(tail,wkSp,wkDg*0.2F,false,1F,0F,limbSwing,limbSwingAmount);
        }else{
            // ── NORMAL WALK: alternating legs (crows DO walk, not just hop) ──
            this.bob(body,wkSp*1F,wkDg*1.3F,true,limbSwing,limbSwingAmount);
            this.walk(leg_right,wkSp,wkDg*1.85F,false,0F,0.2F,limbSwing,limbSwingAmount);
            this.walk(leg_left,wkSp,wkDg*1.85F,true,0F,0.2F,limbSwing,limbSwingAmount);
            this.walk(head,wkSp,wkDg*0.4F,false,2F,-0.01F,limbSwing,limbSwingAmount);
            this.flap(tail,wkSp,wkDg*0.2F,false,1F,0F,limbSwing,limbSwingAmount);
        }

        // ── IDLE: quick head/tail movements (foraging behavior) ──
        this.walk(head,idSp*0.7F,idDg,false,-1F,0.05F,ageInTicks,1);
        this.walk(tail,idSp*0.7F,idDg,false,1F,0.05F,ageInTicks,1);

        // ── SITTING ──
        progressRotationPrev(body,sit,Maths.rad(-25),0,0,5F);
        progressRotationPrev(leg_left,sit,Maths.rad(25),0,0,5F);
        progressRotationPrev(leg_right,sit,Maths.rad(25),0,0,5F);
        progressRotationPrev(head,sit,Maths.rad(25),0,0,5F);

        head.rotateAngleY+=netHeadYaw/57.295776F;
        head.rotateAngleZ+=headPitch/57.295776F;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        if (this.young) {
            float f = 1.45F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.5D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            this.head.setScale(0.9F, 0.9F, 0.9F);
        } else {
            this.head.setScale(0.9F, 0.9F, 0.9F);
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}

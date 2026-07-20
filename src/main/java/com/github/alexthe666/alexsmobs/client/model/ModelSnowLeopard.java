package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySnowLeopard;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelSnowLeopard extends AdvancedEntityModel<EntitySnowLeopard> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tail3;
    private final AdvancedModelBox head;
    private final AdvancedModelBox bubble;
    private final AdvancedModelBox whiskersLeft;
    private final AdvancedModelBox whiskersRight;
    private final AdvancedModelBox armLeft;
    private final AdvancedModelBox armRight;
    private final AdvancedModelBox legLeft;
    private final AdvancedModelBox legRight;
    public ModelAnimator animator;

    public ModelSnowLeopard() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this);
        body.setRotationPoint(0.0F, -11.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 9.0F, 18.0F, 0.0F, false);

        tail1 = new AdvancedModelBox(this);
        tail1.setRotationPoint(0.0F, -1.0F, 9.0F);
        body.addChild(tail1);
        setRotationAngle(tail1, -0.7418F, 0.0F, 0.0F);
        tail1.setTextureOffset(0, 28).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 3.0F, 13.0F, 0.0F, false);

        tail2 = new AdvancedModelBox(this);
        tail2.setRotationPoint(0.0F, -0.2F, 11.0F);
        tail1.addChild(tail2);
        setRotationAngle(tail2, -1.0472F, 0.0F, 0.0F);
        tail2.setTextureOffset(0, 28).addBox(-1.5F, -8.0F, -2.0F, 3.0F, 9.0F, 3.0F, 0.1F, false);

        tail3 = new AdvancedModelBox(this);
        tail3.setRotationPoint(0.0F, -7.3F, -1.3F);
        tail2.addChild(tail3);
        setRotationAngle(tail3, -0.9599F, 0.0F, 0.0F);
        tail3.setTextureOffset(20, 28).addBox(-1.5F, -2.0F, -8.0F, 3.0F, 3.0F, 9.0F, 0.2F, false);

        head = new AdvancedModelBox(this);
        head.setRotationPoint(0.0F, -1.0F, -9.0F);
        body.addChild(head);
        head.setTextureOffset(35, 0).addBox(-3.5F, -4.0F, -4.0F, 7.0F, 5.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(35, 11).addBox(-2.5F, -2.0F, -6.0F, 5.0F, 3.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(36, 28).addBox(1.5F, -6.0F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(36, 28).addBox(-3.5F, -6.0F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, true);

        bubble = new AdvancedModelBox(this);
        bubble.setRotationPoint(0.0F, -2.0F, -6.0F);
        head.addChild(bubble);
        bubble.setTextureOffset(7, 13).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        whiskersLeft = new AdvancedModelBox(this);
        whiskersLeft.setRotationPoint(2.5F, -0.5F, -5.0F);
        head.addChild(whiskersLeft);
        setRotationAngle(whiskersLeft, 0.0F, -0.5236F, 0.0F);
        whiskersLeft.setTextureOffset(11, 0).addBox(0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);

        whiskersRight = new AdvancedModelBox(this);
        whiskersRight.setRotationPoint(-2.5F, -0.5F, -5.0F);
        head.addChild(whiskersRight);
        setRotationAngle(whiskersRight, 0.0F, 0.5236F, 0.0F);
        whiskersRight.setTextureOffset(11, 0).addBox(-3.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, true);

        armLeft = new AdvancedModelBox(this);
        armLeft.setRotationPoint(2.9F, 4.0F, -6.0F);
        body.addChild(armLeft);
        armLeft.setTextureOffset(0, 0).addBox(-1.4F, -2.0F, -2.0F, 3.0F, 9.0F, 4.0F, 0.0F, false);

        armRight = new AdvancedModelBox(this);
        armRight.setRotationPoint(-2.9F, 4.0F, -6.0F);
        body.addChild(armRight);
        armRight.setTextureOffset(0, 0).addBox(-1.6F, -2.0F, -2.0F, 3.0F, 9.0F, 4.0F, 0.0F, true);

        legLeft = new AdvancedModelBox(this);
        legLeft.setRotationPoint(2.9F, 4.0F, 8.0F);
        body.addChild(legLeft);
        legLeft.setTextureOffset(29, 41).addBox(-1.4F, -1.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);

        legRight = new AdvancedModelBox(this);
        legRight.setRotationPoint(-2.9F, 4.0F, 8.0F);
        body.addChild(legRight);
        legRight.setTextureOffset(29, 41).addBox(-1.6F, -1.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, true);
        this.updateDefaultPose();
        animator = new ModelAnimator();
    }


    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, head, bubble, whiskersLeft, whiskersRight, armLeft, armRight, legLeft, legRight, tail1, tail2, tail3);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        animator.update(entity);
        animator.update(entity);
        animator.setAnimation(EntitySnowLeopard.ANIMATION_ATTACK_R);
        animator.startKeyframe(3);
        animator.rotate(body, 0, Maths.rad(-10F), 0);
        animator.rotate(head, 0, Maths.rad(-10F), Maths.rad(-10F));
        animator.rotate(armRight, Maths.rad(25F), Maths.rad(-20F), 0);
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.rotate(head, 0, 0, Maths.rad(0));
        animator.rotate(armRight, Maths.rad(-90F), Maths.rad(-30F), 0);
        animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntitySnowLeopard.ANIMATION_ATTACK_L);
        animator.startKeyframe(3);
        animator.rotate(body, 0, Maths.rad(10F), 0);
        animator.rotate(head, 0, Maths.rad(10F), Maths.rad(10F));
        animator.rotate(armLeft, Maths.rad(25F), Maths.rad(20F), 0);
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.rotate(head, 0, 0, Maths.rad(0));
        animator.rotate(armLeft, Maths.rad(-90F), Maths.rad(30F), 0);
        animator.endKeyframe();
        animator.resetKeyframe(5);
    }

    @Override
    public void setupAnim(EntitySnowLeopard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float wkSp=0.7F, wkDg=0.5F, idSp=0.1F, idDg=0.1F, runP=5F*limbSwingAmount;
        float pt=Minecraft.getInstance().getFrameTime();
        float stalkP=entity.prevSneakProgress+(entity.sneakProgress-entity.prevSneakProgress)*pt;
        float tackleP=entity.prevTackleProgress+(entity.tackleProgress-entity.prevTackleProgress)*pt;
        float sitP=entity.prevSitProgress+(entity.sitProgress-entity.prevSitProgress)*pt;
        float sleepP=entity.prevSleepProgress+(entity.sleepProgress-entity.prevSleepProgress)*pt;
        float sitSleepP=Math.max(sitP,sleepP);

        //══════ 🐆 SNOW LEOPARD — HIGH-ALTITUDE BOUNCING HUNTER ══════
        // IDENTITY: Mountain ghost. TAIL CURLS OVER BACK (signature!).
        // HIGH posture on rocky terrain. BOUNCY, light steps — never heavy.
        // Pounces between rocks. Whiskers twitch sensing snow vibrations.
        // Sleeps with bubble. MOST DISTINCT from Tiger in every dimension.
        // Tiger = LOW heavy glide. Snow Leopard = HIGH bouncy spring.

        // ── BREATHING: thin air at altitude, slightly faster ──
        float thinAir=Mth.cos(ageInTicks*0.1F);
        body.rotationPointY+=thinAir*0.06F; body.setScale(1.0F,1.0F+thinAir*0.008F,1.0F);

        // ── HIGH BOUNCY WALK: light on feet, almost hopping between rocks ──
        this.walk(armRight,wkSp,wkDg*1F,true,0F,0F,limbSwing,limbSwingAmount);
        this.bob(armRight,wkSp,wkDg*0.8F,false,limbSwing,limbSwingAmount);
        this.walk(armLeft,wkSp,wkDg*1F,false,0F,0F,limbSwing,limbSwingAmount);
        this.bob(armLeft,wkSp,wkDg*0.8F,false,limbSwing,limbSwingAmount);
        this.walk(legRight,wkSp,wkDg*1F,false,0F,0F,limbSwing,limbSwingAmount);
        this.bob(legRight,wkSp,wkDg*0.8F,false,limbSwing,limbSwingAmount);
        this.walk(legLeft,wkSp,wkDg*1F,true,0F,0F,limbSwing,limbSwingAmount);
        this.bob(legLeft,wkSp,wkDg*0.8F,false,limbSwing,limbSwingAmount);

        // ── SPRINGY BOUNCE: snow leopard ricochets lightly ──
        float spring=Mth.sin(limbSwing*wkSp*1.3F)*wkDg*1.8F*limbSwingAmount;
        body.rotationPointY+=spring;head.rotationPointY+=spring*0.3F;

        // ── TAIL CURLS OVER BACK: THE signature snow leopard move! ──
        // Unlike tiger (tail hangs down), snow leopard tail arches up and curves forward
        // 3-segment tail creates elegant S-curve cascading over the spine
        AdvancedModelBox[] tailCurve={tail1,tail2,tail3};
        if(limbSwingAmount<0.05F){
            // IDLE: tail curls forward over body — resting over-the-shoulder pose
            tail1.rotateAngleX-=0.8F; // base curves up
            tail2.rotateAngleX-=0.9F; // middle arcs forward  
            tail3.rotateAngleX-=0.6F; // tip hangs down slightly
            // Gentle rhythmic sway of the curled tail
            float tailSway=Mth.sin(ageInTicks*0.4F+1F)*0.08F;
            tail2.rotateAngleZ+=tailSway;tail3.rotateAngleZ+=tailSway*1.3F;
        }
        // During walk: tail streams behind as counterbalance, then flicks up at end
        this.chainSwing(tailCurve,wkSp,wkDg*0.4F,-2.5F,limbSwing,limbSwingAmount);
        // Tail tip has independent flutter even while base swings
        this.flap(tail3,idSp*1.2F,idDg*1.5F,false,2F,0,ageInTicks,1-limbSwingAmount*0.6F);

        // ── WHISKERS: LONG sensitive probing (snow vibration detection) ──
        // Unlike tiger's snout (smell), snow leopard uses whiskers actively
        this.flap(whiskersLeft,0.2F,0.08F,false,0F,0,ageInTicks,1);
        this.flap(whiskersRight,0.2F,0.08F,true,1.5F,0,ageInTicks,1);
        whiskersLeft.rotateAngleY+=Mth.sin(ageInTicks*0.5F)*0.2F;
        whiskersRight.rotateAngleY+=Mth.sin(ageInTicks*0.5F+1F)*0.2F;

        // ── STALK: ultra-low crouch, belly nearly touches ground ──
        if(stalkP>0){body.rotationPointY-=stalkP*0.25F;body.rotationPointZ+=stalkP*0.3F;}
        progressRotationPrev(body,stalkP,Maths.rad(10),0,0,5F);
        progressRotationPrev(legLeft,stalkP,Maths.rad(-10),0,0,5F);
        progressRotationPrev(legRight,stalkP,Maths.rad(-10),0,0,5F);
        progressRotationPrev(armLeft,stalkP,Maths.rad(-15),0,0,5F);
        progressRotationPrev(armRight,stalkP,Maths.rad(-15),0,0,5F);
        progressRotationPrev(head,stalkP,Maths.rad(5),0,0,5F);
        progressPositionPrev(body,stalkP,0,-0.5F,4,5F);
        progressPositionPrev(legLeft,stalkP,0,1.6F,-2,5F);
        progressPositionPrev(legRight,stalkP,0,1.6F,-2,5F);

        // ── HEAD SCANNING: constantly surveys mountain terrain ──
        this.walk(head,idSp*0.3F,idDg,false,0F,0F,ageInTicks,1);
        this.walk(head,idSp*0.3F,-idDg,false,0.5F,0F,ageInTicks,1);
        if(sleepP<=0)this.faceTarget(netHeadYaw,headPitch,1,head);

        // ── TRANSITIONS ──
        progressRotationPrev(tail1,runP,Maths.rad(40),0,0,5F);
        progressRotationPrev(tail2,runP,Maths.rad(-20),0,0,5F);
        progressRotationPrev(tail3,runP,Maths.rad(-20),0,0,5F);
        progressRotationPrev(body,tackleP,Maths.rad(-45),0,0,3F);
        progressRotationPrev(head,tackleP,Maths.rad(45),0,0,3F);
        progressRotationPrev(tail1,tackleP,Maths.rad(60),0,0,3F);
        progressRotationPrev(armRight,tackleP,Maths.rad(-25),0,Maths.rad(45),3F);
        progressRotationPrev(armLeft,tackleP,Maths.rad(-25),0,Maths.rad(-45),3F);
        progressRotationPrev(legLeft,tackleP,Maths.rad(-15),0,Maths.rad(-25),3F);
        progressRotationPrev(legRight,tackleP,Maths.rad(-15),0,Maths.rad(25),3F);
        progressPositionPrev(body,tackleP,0,-5F,0,3F);
        progressPositionPrev(head,tackleP,0,2,0,3F);

        float ta=entity.getId()%2==0?1:-1;
        progressRotationPrev(legLeft,sitSleepP,Maths.rad(-90),Maths.rad(-20),0,5F);
        progressRotationPrev(legRight,sitSleepP,Maths.rad(-90),Maths.rad(20),0,5F);
        progressRotationPrev(armLeft,sitSleepP,Maths.rad(-90),0,0,5F);
        progressRotationPrev(armRight,sitSleepP,Maths.rad(-90),0,0,5F);
        progressPositionPrev(body,sitSleepP,0,3,0,5F);
        progressPositionPrev(armRight,sitSleepP,0,2F,0,5F);
        progressPositionPrev(armLeft,sitSleepP,0,2F,0,5F);
        progressPositionPrev(legRight,sitSleepP,0,2.8F,-0.5F,5F);
        progressPositionPrev(legLeft,sitSleepP,0,2.8F,-0.5F,5F);
        progressRotationPrev(tail1,sitP,Maths.rad(20),Maths.rad(ta*30),0,5F);
        progressRotationPrev(tail2,sitP,Maths.rad(-5),Maths.rad(ta*50),0,5F);
        progressRotationPrev(tail3,sitP,Maths.rad(10),Maths.rad(ta*20),Maths.rad(ta*20),5F);
        progressRotationPrev(tail1,sleepP,Maths.rad(20),Maths.rad(ta*-60),0,5F);
        progressRotationPrev(tail2,sleepP,Maths.rad(10),Maths.rad(ta*-70),Maths.rad(ta*-50),5F);
        progressRotationPrev(tail3,sleepP,Maths.rad(-30),Maths.rad(ta*-50),Maths.rad(ta*-30),5F);
        progressRotationPrev(body,sleepP,Maths.rad(10),0,0,5F);
        progressPositionPrev(head,sleepP,0,5,-1,5F);
        if(sleepP>=5F){float f=(float)(sleepP*Math.max(Math.sin(ageInTicks*0.05F),0F)*0.2F);bubble.showModel=true;bubble.setScale(f,f,f);}
        else{bubble.showModel=false;}
    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.45F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.5D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            head.setScale(1, 1, 1);
        } else {
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }

    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}
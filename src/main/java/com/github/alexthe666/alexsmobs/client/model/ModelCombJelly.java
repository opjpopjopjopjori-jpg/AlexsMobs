package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCombJelly;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCombJelly extends AdvancedEntityModel<EntityCombJelly> {
    private final AdvancedModelBox root,body,inner_body;

    public ModelCombJelly(float f){texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setRotationPoint(0.0F,-13.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-5.0F,-2.0F,-5.0F,10.0F,15.0F,10.0F,f,false);
        inner_body=new AdvancedModelBox(this,"inner_body");inner_body.setRotationPoint(0.0F,-3.0F,0.0F);body.addChild(inner_body);inner_body.setTextureOffset(40,6).addBox(-3.0F,-1.0F,-3.0F,6.0F,13.0F,6.0F,f,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,inner_body);}

    @Override
    public void setupAnim(EntityCombJelly entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🫧 COMB JELLY — BI-HARMONIC CILIA PULSATOR ══════
        // IDENTITY: Moves via rows of cilia (comb plates) that pulse in
        // sequential waves, creating rainbow diffraction. No tentacles —
        // pure gelatinous body. Transparent. Floats weightlessly.
        // UNIQUE: Only creature with true bi-harmonic pulsation.
        // BIOMECHANICS: Comb jellies are the largest animals that move
        // using cilia. Body expands/contracts in asymmetric rhythm with
        // two harmonic frequencies creating organic, never-repeating motion.

        float partialTick=Minecraft.getInstance().getFrameTime();
        float birdPitch=entity.prevjellyPitch+(entity.getJellyPitch()-entity.prevjellyPitch)*partialTick;
        float landProgress=entity.prevOnLandProgress+(entity.onLandProgress-entity.prevOnLandProgress)*partialTick;

        //══════ BI-HARMONIC PULSATION ══════
        // Two independent frequencies create non-repeating organic rhythm.
        // This simulates the sequential firing of cilia comb rows.
        float girateSpeed=0.1F*ageInTicks*(1F-landProgress*0.2F);
        float pulse1=Mth.sin(girateSpeed)*0.1F;           // primary cilia wave
        float pulse2=Mth.sin(girateSpeed*2.3F+0.7F)*0.04F; // harmonic overtone
        float widthScale=0.95F+pulse1+pulse2;
        float heightScale=0.95F+Mth.cos(girateSpeed)*0.1F+Mth.cos(girateSpeed*2.1F+1.2F)*0.03F;

        //══════ OUTER BODY: primary pulsation ══════
        float squishedScale=widthScale-0.1F*landProgress;
        float heightSquished=heightScale-0.15F*landProgress;
        this.body.setScale(squishedScale,heightSquished,widthScale);

        //══════ INNER BODY: COUNTER-PHASE pulsation ══════
        // The inner body pulses OPPOSITE to outer — when outer contracts,
        // inner expands. This creates depth illusion of transparent body.
        float innerWidth=0.95F-pulse1*0.7F-pulse2*0.5F;
        float innerHeight=0.95F-Mth.cos(girateSpeed)*0.07F;
        this.inner_body.setScale(squishedScale*1.02F+(innerWidth-0.95F),heightSquished*1.02F+(innerHeight-0.95F),widthScale*1.02F+(innerWidth-0.95F));

        //══════ BODY ORIENTATION ══════
        this.body.rotateAngleX=birdPitch*Mth.DEG_TO_RAD*(1F-landProgress*0.2F);
        this.body.rotateAngleZ=landProgress*0.2F*Mth.HALF_PI;
        this.body.rotationPointY+=landProgress*1.85F;
        this.body.rotationPointY+=Mth.abs(birdPitch*0.07F);
        this.body.rotationPointX+=landProgress;

        //══════ SLOW ROTATION: comb jellies gently rotate while drifting ══════
        this.body.rotateAngleY+=Mth.sin(ageInTicks*0.04F+1.5F)*0.08F*(1-landProgress);

        //══════ BUOYANCY BOBBING ══════
        float buoyancyBob=Mth.sin(ageInTicks*0.08F+0.5F)*0.6F;
        this.body.rotationPointY+=buoyancyBob*(1F-landProgress*0.2F)*0.8F;

        //══════ LAND STRUGGLE ══════
        if(landProgress>0.01F){
            float landPulse=Mth.sin(ageInTicks*0.15F)*0.03F*landProgress;
            body.setScale(body.getScaleX()+landPulse,body.getScaleY()+landPulse*0.5F,body.getScaleZ()+landPulse);
        }

        this.bob(body,0.1F,1F,false,ageInTicks,1F-landProgress*0.2F);
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

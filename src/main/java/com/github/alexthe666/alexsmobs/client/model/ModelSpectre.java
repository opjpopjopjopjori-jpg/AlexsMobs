package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelSpectre extends AdvancedEntityModel<EntitySpectre> {
    private final AdvancedModelBox root,body,spine,tail,wing_left,wing_right,wing_left_p,wing_right_p;

    public ModelSpectre(){texWidth=256;texHeight=256;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-0.5F,0.0F);root.addChild(body);setRotationAngle(body,0.0F,-0.7854F,0.0F);body.setTextureOffset(43,0).addBox(-12.0F,-5.5F,-12.0F,24.0F,6.0F,24.0F,0.0F,false);
        spine=new AdvancedModelBox(this,"spine");spine.setPos(0.0F,-5.5F,0.0F);body.addChild(spine);setRotationAngle(spine,0.0F,0.7854F,0.0F);spine.setTextureOffset(0,0).addBox(0.0F,-3.0F,-14.0F,0.0F,8.0F,42.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,1.0F,28.0F);spine.addChild(tail);tail.setTextureOffset(76,31).addBox(0.0F,-6.0F,0.0F,0.0F,11.0F,27.0F,0.0F,false);
        wing_left_p=new AdvancedModelBox(this,"wing_left_p");wing_left_p.setPos(12.0F,-2.5F,-12.0F);body.addChild(wing_left_p);
        wing_left=new AdvancedModelBox(this,"wing_left");wing_left_p.addChild(wing_left);wing_left.setTextureOffset(76,76).addBox(0.0F,-1.5F,0.0F,26.0F,3.0F,23.0F,0.0F,false);
        wing_right_p=new AdvancedModelBox(this,"wing_right_p");wing_right_p.setPos(-12.0F,-2.5F,-12.0F);body.addChild(wing_right_p);setRotationAngle(wing_right_p,0.0F,-1.5708F,0.0F);
        wing_right=new AdvancedModelBox(this,"wing_right");wing_right_p.addChild(wing_right);wing_right.setTextureOffset(0,51).addBox(-26.0F,-1.5F,0.0F,26.0F,3.0F,23.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,wing_right_p,wing_left_p,wing_left,wing_right,spine,tail);}

    @Override
    public void setupAnim(EntitySpectre entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 👻 SPECTRE — GHOSTLY PHASING APPARITION ══════
        // IDENTITY: Translucent ghost that phases through walls. Body is
        // a flat disc with spine and wispy tail. Wings are ethereal rays
        // that slowly pulse. Entire being subtly phases (scale oscillation).
        // UNIQUE vs SoulVulture (aggressive), Flutter (butterfly).
        // BIOMECHANICS: Ghosts don't obey physics — no weight, no inertia.
        // Motion is slow, hypnotic, phase-shifted. Like underwater but in air.

        float partialTick=Minecraft.getInstance().getFrameTime();
        float birdPitch=entity.prevBirdPitch+(entity.birdPitch-entity.prevBirdPitch)*partialTick;

        //══════ PHASING: translucent scale oscillation ══════
        // Ghost phases in/out of visibility through scale pulsation
        float phase=Mth.sin(ageInTicks*0.06F);
        body.setScale(1.0F+phase*0.04F,1.0F+phase*0.03F,1.0F+phase*0.04F);

        //══════ SPINE: slow structural undulation ══════
        // Spine is the anchor — slow, heavy, low frequency
        float spineWave1=Mth.sin(ageInTicks*0.07F)*0.1F;
        float spineWave2=Mth.sin(ageInTicks*0.05F+1F)*0.06F;
        spine.rotateAngleZ+=spineWave1+spineWave2;
        spine.rotateAngleX+=Mth.sin(ageInTicks*0.06F+0.5F)*0.04F;

        //══════ WISPY TAIL: fast independent lash ══════
        // Tail is NOT just delayed spine — it has its OWN faster rhythm
        float tailWave1=Mth.sin(ageInTicks*0.25F)*0.18F;
        float tailWave2=Mth.sin(ageInTicks*0.18F+1.5F)*0.12F;
        float tailWave3=Mth.sin(ageInTicks*0.32F+3F)*0.08F;
        tail.rotateAngleZ+=tailWave1+tailWave2+tailWave3;
        tail.rotateAngleX+=Mth.sin(ageInTicks*0.15F)*0.1F;
        tail.rotateAngleY+=Mth.sin(ageInTicks*0.21F+2F)*0.07F;

        //══════ ETHEREAL WINGS: slow hypnotic pulse ══════
        // Ghost wings are not for flight — they're spectral rays that
        // pulse slowly with an otherworldly rhythm
        this.flap(wing_left,0.08F,0.12F,true,2F,0,ageInTicks,1);
        this.flap(wing_right,0.08F,0.12F,false,2.5F,0,ageInTicks,1);
        // Wings also slowly rotate (banking)
        wing_left.rotateAngleZ+=Mth.sin(ageInTicks*0.05F)*0.08F;
        wing_right.rotateAngleZ-=Mth.sin(ageInTicks*0.05F+1F)*0.08F;

        //══════ HYPNOTIC FLOAT: slow circular drift ══════
        // Ghosts don't walk — they float in slow, hypnotic patterns
        body.rotateAngleX+=Mth.sin(ageInTicks*0.04F)*0.06F;
        body.rotateAngleY+=Mth.sin(ageInTicks*0.05F+1F)*0.1F;
        root.rotationPointY+=Mth.sin(ageInTicks*0.07F+0.5F)*0.3F;
        root.rotateAngleX+=birdPitch*Mth.DEG_TO_RAD;

        //══════ BREATHING: spectral pulse ══════
        float breath=Mth.cos(ageInTicks*0.07F);
        body.rotationPointY+=breath*0.1F;
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

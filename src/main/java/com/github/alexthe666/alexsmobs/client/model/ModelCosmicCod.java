package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCosmicCod;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelCosmicCod extends AdvancedEntityModel<EntityCosmicCod> {
    private final AdvancedModelBox root,body,tail,tail_tip,left_fin,right_fin,mouth;

    public ModelCosmicCod(){texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-3.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-2.0F,-3.0F,-5.0F,4.0F,5.0F,8.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-1.0F,3.0F);body.addChild(tail);tail.setTextureOffset(0,14).addBox(-1.0F,-1.0F,0.0F,2.0F,3.0F,3.0F,0.0F,false);
        tail_tip=new AdvancedModelBox(this,"tail_tip");tail_tip.setPos(0.0F,0.0F,3.0F);tail.addChild(tail_tip);tail_tip.setTextureOffset(17,0).addBox(-0.5F,-0.5F,0.0F,1.0F,2.0F,3.0F,0.0F,false);
        left_fin=new AdvancedModelBox(this,"left_fin");left_fin.setPos(2.0F,0.5F,-2.0F);body.addChild(left_fin);left_fin.setTextureOffset(0,21).addBox(0.0F,-0.5F,-1.0F,3.0F,0.0F,3.0F,0.0F,false);
        right_fin=new AdvancedModelBox(this,"right_fin");right_fin.setPos(-2.0F,0.5F,-2.0F);body.addChild(right_fin);right_fin.setTextureOffset(0,21).addBox(-3.0F,-0.5F,-1.0F,3.0F,0.0F,3.0F,0.0F,true);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setPos(0.0F,1.0F,-5.0F);body.addChild(mouth);mouth.setTextureOffset(0,0).addBox(-1.0F,-0.5F,-1.0F,2.0F,1.0F,1.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,tail_tip,left_fin,right_fin,mouth);}

    @Override
    public void setupAnim(EntityCosmicCod entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float swimSpeed=0.3F,swimDegree=0.2F;

        //══════ 🌌 COSMIC COD — BIOLUMINESCENT DEEP DRIFTER ══════
        // IDENTITY: Glowing deep-sea fish. Drifts SLOWLY in the abyss.
        // Bioluminescent spots pulse gently. Mouth opens to vacuum prey.
        // Tail moves in slow, hypnotic wave. Ethereal, alien.
        // UNIQUE vs Pupfish (nervous darter), FlyingFish (surface glider).

        // ── BREATHING: slow deep-sea metabolism ──
        float breath=Mth.cos(ageInTicks*0.05F);body.rotationPointY+=breath*0.08F;

        // ── SLOW HYPNOTIC DRIFT ──
        this.bob(body,swimSpeed*0.5F,swimDegree*4F,false,limbSwing,limbSwingAmount);
        body.rotateAngleX+=Mth.sin(ageInTicks*0.04F)*0.03F; // slow pitch

        // ── TAIL: slow carangiform wave ──
        AdvancedModelBox[] tc={tail,tail_tip};
        this.chainSwing(tc,swimSpeed,swimDegree*1.5F,-2,limbSwing,limbSwingAmount);

        // ── FINS: gentle wave, almost hovering ──
        this.flap(left_fin,0.15F,0.1F,true,1F,0,ageInTicks,1);
        this.flap(right_fin,0.15F,0.1F,false,1F,0,ageInTicks,1);

        // ── MOUTH: slow vacuum ──
        mouth.rotateAngleX+=Mth.sin(ageInTicks*0.06F)*0.03F;

        // ── BIOLUMINESCENT PULSE: subtle scale shimmer ──
        body.setScale(1.0F+breath*0.015F,1.0F,1.0F+breath*0.01F);

        this.faceTarget(netHeadYaw,headPitch,1,body);
    }
}

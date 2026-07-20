package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelDevilsHolePupfish extends AdvancedEntityModel<EntityDevilsHolePupfish> {
    private final AdvancedModelBox root,body,tail,left_fin,right_fin,dorsal_fin,bottom_fin;

    public ModelDevilsHolePupfish(){texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-2.0F,0.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-1.5F,-2.0F,-4.0F,3.0F,3.0F,6.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,-0.5F,2.0F);body.addChild(tail);tail.setTextureOffset(0,10).addBox(-1.0F,-1.0F,0.0F,2.0F,2.0F,3.0F,0.0F,false);
        left_fin=new AdvancedModelBox(this,"left_fin");left_fin.setPos(1.5F,0.5F,-1.0F);body.addChild(left_fin);left_fin.setTextureOffset(13,0).addBox(0.0F,-0.5F,-1.0F,0.0F,2.0F,2.0F,0.0F,false);
        right_fin=new AdvancedModelBox(this,"right_fin");right_fin.setPos(-1.5F,0.5F,-1.0F);body.addChild(right_fin);right_fin.setTextureOffset(13,0).addBox(0.0F,-0.5F,-1.0F,0.0F,2.0F,2.0F,0.0F,true);
        dorsal_fin=new AdvancedModelBox(this,"dorsal_fin");dorsal_fin.setPos(0.0F,-2.0F,0.0F);body.addChild(dorsal_fin);dorsal_fin.setTextureOffset(0,16).addBox(0.0F,-1.0F,-1.0F,0.0F,2.0F,3.0F,0.0F,false);
        bottom_fin=new AdvancedModelBox(this,"bottom_fin");bottom_fin.setPos(0.0F,1.0F,0.0F);body.addChild(bottom_fin);bottom_fin.setTextureOffset(7,16).addBox(0.0F,0.0F,-1.0F,0.0F,1.0F,2.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,tail,left_fin,right_fin,dorsal_fin,bottom_fin);}

    @Override
    public void setupAnim(EntityDevilsHolePupfish entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float swimSpeed=0.5F,swimDegree=0.3F;

        //══════ 🐟 DEVILS HOLE PUPFISH — NERVOUS DESERT SURVIVOR ══════
        // IDENTITY: Rarest fish on Earth. Tiny, nervous, quick-darting.
        // Lives in ONE desert pool. Constant quick, jerky movements.
        // Fins flutter rapidly. Body darts side-to-side.
        // UNIQUE vs CosmicCod (slow drifter), Catfish (bottom-feeder).

        // ── BREATHING: rapid tiny gill flutter ──
        float breath=Mth.cos(ageInTicks*0.25F);body.rotationPointY+=breath*0.02F;

        // ── NERVOUS DARTING: quick jerky swimming ──
        this.bob(body,swimSpeed,swimDegree*3F,false,limbSwing,limbSwingAmount);
        body.rotationPointX+=Mth.sin(limbSwing*swimSpeed*1.5F)*swimDegree*1.2F*limbSwingAmount;
        // Quick direction changes
        body.rotateAngleY+=Mth.sin(ageInTicks*0.5F)*0.1F*(1-limbSwingAmount*0.5F);

        // ── TAIL: rapid subcarangiform wave ──
        this.swing(tail,swimSpeed,swimDegree*2.5F,false,0.5F,0,limbSwing,limbSwingAmount);

        // ── FINS: constant rapid flutter ──
        this.flap(left_fin,0.35F,0.08F,false,1F,0,ageInTicks,1);
        this.flap(right_fin,0.35F,0.08F,true,1.3F,0,ageInTicks,1);
        this.flap(dorsal_fin,0.3F,0.05F,false,0F,0,ageInTicks,1);
        this.flap(bottom_fin,0.3F,0.04F,true,0.5F,0,ageInTicks,1);

        this.faceTarget(netHeadYaw,headPitch,1,body);
    }
}

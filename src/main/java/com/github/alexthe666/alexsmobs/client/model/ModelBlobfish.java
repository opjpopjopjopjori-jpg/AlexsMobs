package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityBlobfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelBlobfish extends AdvancedEntityModel<EntityBlobfish> {
    private final AdvancedModelBox root,body,nose,tail,tail_fin,fin_left,fin_right;

    public ModelBlobfish(){texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-2.5F,1.0F);root.addChild(body);body.setTextureOffset(0,0).addBox(-3.0F,-2.5F,-8.0F,6.0F,5.0F,8.0F,0.0F,false);
        nose=new AdvancedModelBox(this,"nose");nose.setPos(0.0F,-0.5F,-8.0F);body.addChild(nose);nose.setTextureOffset(0,19).addBox(-2.0F,-1.0F,-1.0F,4.0F,2.0F,1.0F,0.0F,false);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.0F,0.25F,0.0F);body.addChild(tail);tail.setTextureOffset(11,14).addBox(-2.0F,-2.25F,0.0F,4.0F,4.0F,4.0F,0.0F,false);
        tail_fin=new AdvancedModelBox(this,"tail_fin");tail_fin.setPos(0.0F,-1.45F,0.0F);tail.addChild(tail_fin);tail_fin.setTextureOffset(0,14).addBox(0.0F,-2.0F,-1.0F,0.0F,6.0F,10.0F,0.0F,false);
        fin_left=new AdvancedModelBox(this,"fin_left");fin_left.setPos(3.0F,1.0F,-4.0F);body.addChild(fin_left);setRotationAngle(fin_left,0.0F,-0.6109F,0.0F);fin_left.setTextureOffset(0,0).addBox(0.0F,-1.5F,0.0F,3.0F,3.0F,0.0F,0.0F,false);
        fin_right=new AdvancedModelBox(this,"fin_right");fin_right.setPos(-3.0F,1.0F,-4.0F);body.addChild(fin_right);setRotationAngle(fin_right,0.0F,0.6109F,0.0F);fin_right.setTextureOffset(0,0).addBox(-3.0F,-1.5F,0.0F,3.0F,3.0F,0.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,fin_left,fin_right,tail,tail_fin,nose);}

    @Override
    public void setupAnim(EntityBlobfish entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🐡 BLOBFISH — GELATINOUS DEEP-SEA DRIFTER ══════
        // IDENTITY: Nearly structureless gelatinous body. No swim bladder —
        // floats by being slightly less dense than water. Famous "sad face"
        // (nose droops from decompression). Barely moves — passive drifter.
        // Tiny fins make micro-adjustments. Gelatinous wobble with any current.
        // UNIQUE: Most passive creature — moves less than any other.
        // BIOMECHANICS: Blobfish have ALMOST NO muscle. They don't "swim" —
        // they drift. Fins are tiny stabilizers, not propulsors.

        this.body.rotateAngleX=headPitch*Mth.DEG_TO_RAD;

        //══════ GELATINOUS WOBBLE: body deforms in all axes ══════
        // Unlike fish with rigid skeletons, blobfish are gelatinous —
        // their body wobbles and deforms continuously with water currents.
        // Three independent wobble frequencies create organic irregular motion.
        float w1=Mth.sin(ageInTicks*0.07F);   // slow primary drift
        float w2=Mth.sin(ageInTicks*0.11F+1F); // medium wobble
        float w3=Mth.sin(ageInTicks*0.05F+2F); // slowest buoyancy cycle

        // Gelatinous deformation: body squishes in Y, expands in X/Z, and vice versa
        body.setScale(1.0F+w1*0.04F+w2*0.02F, 1.0F-w1*0.04F+w3*0.03F, 1.0F+w1*0.03F-w2*0.02F);
        body.rotationPointY+=w1*0.3F+w3*0.15F;
        // Subtle body roll — blobfish slowly rotate with currents
        body.rotateAngleZ+=w3*0.04F;

        //══════ SAD FACE: nose droops (THE blobfish signature) ══════
        // At surface pressure, the blobfish's gelatinous body decompresses
        // and the nose droops pathetically downward. This is the iconic look.
        nose.rotateAngleX+=0.15F+w1*0.04F; // permanent slight droop + wobble
        nose.rotationPointY-=w1*0.02F; // nose bobs

        //══════ TAIL: barely moves — gentle passive sway ══════
        // Blobfish don't use their tail for propulsion. It just drifts.
        tail.rotateAngleY+=w2*0.06F; // gentle lateral drift
        tail_fin.rotateAngleY+=w1*0.08F; // fin streams behind

        //══════ FINS: micro-adjustments, not swimming strokes ══════
        // These are stabilizers, not paddles. Tiny movements only.
        fin_left.rotateAngleZ+=w1*0.05F;
        fin_right.rotateAngleZ-=w1*0.05F;
        fin_left.rotateAngleX+=w2*0.03F;
        fin_right.rotateAngleX-=w2*0.03F;
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

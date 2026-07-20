package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelFly extends AdvancedEntityModel<EntityFly> {
    private final AdvancedModelBox root, body, legs, left_wing, right_wing, mouth;

    public ModelFly() {
        texWidth=32;texHeight=32;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-3,0);root.addChild(body);body.setTextureOffset(0,0).addBox(-2,-2,-3,4,4,6,0,false);
        legs=new AdvancedModelBox(this,"legs");legs.setPos(0,2,-2);body.addChild(legs);legs.setTextureOffset(0,11).addBox(-1.5F,0,0,3,1,5,0,false);
        left_wing=new AdvancedModelBox(this,"lw");left_wing.setPos(1,-2,-1);body.addChild(left_wing);left_wing.setTextureOffset(12,11).addBox(0,0,-1,4,0,3,0,false);
        right_wing=new AdvancedModelBox(this,"rw");right_wing.setPos(-1,-2,-1);body.addChild(right_wing);right_wing.setTextureOffset(12,11).addBox(-4,0,-1,4,0,3,0,true);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setPos(0,0,-3);body.addChild(mouth);mouth.setTextureOffset(15,16).addBox(0,0,-1,0,4,2,0,false);
        this.updateDefaultPose();
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,left_wing,right_wing,legs,mouth);}
    public void renderToBuffer(PoseStack m,VertexConsumer b,int l,int o,float r,float g,float bl,float a){
        if(this.young){m.pushPose();m.scale(0.65F,0.65F,0.65F);m.translate(0,0.95,0.125);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
        else{m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }

    @Override public void setupAnim(EntityFly e,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🪰 FLY — LEG-RUBBING ERRATIC PEST ══════
        // IDENTITY: Rubs legs together when grounded. Proboscis extends
        // to feed. Erratic chaotic flight. Sudden takeoff/landing.
        float flySp=1.4F,flyDg=0.8F,idSp=1.4F,idDg=0.8F;
        boolean flag=e.onGround()&&e.getDeltaMovement().lengthSqr()<1.0E-7D;

        // ── AAA BREATHING ──────────────────────────────────────────
        float breath=Mth.cos(ageInTicks*0.2F);
        body.setScale(1.0F+breath*0.02F,1.0F,1.0F);

        // ── AAA PROBOSCIS EXTENSION ────────────────────────────────
        this.walk(mouth,idSp*0.2F,idDg*0.1F,false,-1,0.2F,ageInTicks,1);
        this.flap(mouth,idSp*0.2F,idDg*0.05F,false,-2,0,ageInTicks,1);
        mouth.rotationPointZ-=Mth.abs(Mth.sin(ageInTicks*0.35F))*0.5F;

        if(flag){
            // ── AAA GROUNDED: Leg-rubbing + wing twitch ────────────
            this.left_wing.rotateAngleZ=Maths.rad(-35);
            this.right_wing.rotateAngleZ=Maths.rad(35);
            this.swing(legs,flySp*0.6F,flyDg*0.2F,false,1,0,limbSwing,limbSwingAmount);
            // Leg-rubbing: multi-axis oscillation of leg assembly
            legs.rotateAngleX+=Mth.sin(ageInTicks*1.8F)*0.22F;
            legs.rotateAngleZ+=Mth.cos(ageInTicks*1.8F+0.4F)*0.14F;
            float twitch=Mth.sin(ageInTicks*0.4F)>0.88F?Mth.sin(ageInTicks*0.4F)*0.15F:0;
            left_wing.rotateAngleZ+=twitch;right_wing.rotateAngleZ-=twitch;
        }else{
            // ── AAA FLYING: Erratic chaos ──────────────────────────
            float chaos1=Mth.sin(ageInTicks*0.9F)*Mth.cos(ageInTicks*1.5F)*0.18F;
            float chaos2=Mth.sin(ageInTicks*0.65F+1.3F)*0.06F;
            this.flap(left_wing,flySp*1.3F,flyDg+chaos1+chaos2,true,0,0.2F,ageInTicks,1);
            this.flap(right_wing,flySp*1.3F,flyDg+chaos1-chaos2,false,0,0.2F,ageInTicks,1);
            this.walk(legs,flySp*0.2F,flyDg*0.2F,false,1,0.2F,ageInTicks,1);
            left_wing.rotateAngleX+=Mth.sin(ageInTicks*flySp*1.3F+0.3F)*0.03F;
            right_wing.rotateAngleX-=Mth.sin(ageInTicks*flySp*1.3F+0.3F)*0.03F;
            // Zigzag body position
            body.rotationPointX+=Mth.sin(ageInTicks*1.1F)*0.4F+Mth.sin(ageInTicks*2.3F)*0.2F;
        }
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}

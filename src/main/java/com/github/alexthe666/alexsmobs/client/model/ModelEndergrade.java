package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelEndergrade extends AdvancedEntityModel<EntityEndergrade> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox bodymain;
    private final AdvancedModelBox legbackL, legbackR, legmidL, legmidR;
    private final AdvancedModelBox bodyfront;
    private final AdvancedModelBox head, mouth;
    private final AdvancedModelBox legfrontL, legfrontR;
    private final AdvancedModelBox tail;

    public ModelEndergrade() {
        texWidth=64;texHeight=64;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        bodymain=new AdvancedModelBox(this,"bodymain");bodymain.setPos(0,-9,-1);root.addChild(bodymain);
        bodymain.setTextureOffset(0,0).addBox(-4.5F,-3.5F,0,9,9,10,0,false);
        legbackL=new AdvancedModelBox(this,"legbackL");legbackL.setPos(3.5F,3.5F,7);bodymain.addChild(legbackL);
        legbackL.setTextureOffset(11,45).addBox(-1.5F,-1.5F,-2,3,7,4,0,false);
        legbackR=new AdvancedModelBox(this,"legbackR");legbackR.setPos(-3.5F,3.5F,7);bodymain.addChild(legbackR);
        legbackR.setTextureOffset(11,45).addBox(-1.5F,-1.5F,-2,3,7,4,0,true);
        legmidL=new AdvancedModelBox(this,"legmidL");legmidL.setPos(3.5F,3.5F,1);bodymain.addChild(legmidL);
        legmidL.setTextureOffset(39,0).addBox(-1.5F,-1.5F,-2,3,7,4,0,false);
        legmidR=new AdvancedModelBox(this,"legmidR");legmidR.setPos(-3.5F,3.5F,1);bodymain.addChild(legmidR);
        legmidR.setTextureOffset(39,0).addBox(-1.5F,-1.5F,-2,3,7,4,0,true);
        bodyfront=new AdvancedModelBox(this,"bodyfront");bodyfront.setPos(0,0.5F,0);bodymain.addChild(bodyfront);
        bodyfront.setTextureOffset(25,29).addBox(-4,-3.5F,-8,8,8,8,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,-0.5F,-8);bodyfront.addChild(head);
        head.setTextureOffset(35,16).addBox(-3,-2,-4,6,6,4,0,false);
        mouth=new AdvancedModelBox(this,"mouth");mouth.setPos(0,1.5F,-4.5F);head.addChild(mouth);
        mouth.setTextureOffset(26,46).addBox(-1.5F,-1.5F,-2.5F,3,3,3,0,false);
        legfrontL=new AdvancedModelBox(this,"legfrontL");legfrontL.setPos(3.5F,3,-5);bodyfront.addChild(legfrontL);
        legfrontL.setTextureOffset(0,37).addBox(-1.5F,-1.5F,-2,3,7,4,0,false);
        legfrontR=new AdvancedModelBox(this,"legfrontR");legfrontR.setPos(-3.5F,3,-5);bodyfront.addChild(legfrontR);
        legfrontR.setTextureOffset(0,37).addBox(-1.5F,-1.5F,-2,3,7,4,0,true);
        tail=new AdvancedModelBox(this,"tail");tail.setPos(0.5F,-1,9.9F);bodymain.addChild(tail);
        setRotationAngle(tail,-0.1745F,0,0);
        tail.setTextureOffset(0,20).addBox(-4,-1.5F,-2.4F,7,7,9,0,false);
        this.updateDefaultPose();
    }

    public void renderToBuffer(PoseStack m, VertexConsumer b, int l, int o, float r, float g, float bl, float a) {
        if(this.young){float f=1.75F;head.setScale(f,f,f);head.setShouldScaleChildren(true);m.pushPose();m.scale(0.35F,0.35F,0.35F);m.translate(0,2.75,0.125);parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();head.setScale(1,1,1);}
        else{m.pushPose();parts().forEach(p->p.render(m,b,l,o,r,g,bl,a));m.popPose();}
    }

    @Override public void setupAnim(EntityEndergrade e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐛 ENDERGRADE — ENDER LARVA ══════
        // IDENTITY: Alien caterpillar. Legs wave in chainWave pattern.
        // Body segments ripple. End energy pulses through antennae.
        AdvancedModelBox[] bodyParts={bodyfront,bodymain,tail};
        AdvancedModelBox[] legR={legfrontR,legmidR,legbackR};
        AdvancedModelBox[] legL={legfrontL,legmidL,legbackL};
        float wkSp=1.7F,wkDg=0.7F,pt=Minecraft.getInstance().getFrameTime();
        float birdPitch=e.prevTartigradePitch+(e.tartigradePitch-e.prevTartigradePitch)*pt;
        float biteP=e.prevBiteProgress+(e.biteProgress-e.prevBiteProgress)*pt;
        // AAA BREATHING — tardigrade body pulsing
        float breath=Mth.cos(ageInTicks*0.13F);
        bodymain.setScale(1.0F+breath*0.02F,1.0F,1.0F);
        // AAA MOUTH PROBOSCIS
        this.mouth.setScale(1,1,1+biteP*0.4F);
        this.mouth.rotationPointZ=-3-biteP*0.2F;
        // AAA BODY PITCH
        this.bodymain.rotateAngleX+=birdPitch*Mth.DEG_TO_RAD;
        // AAA LEG WAVE WALKING — left/right legs 180 deg out of phase
        this.chainWave(bodyParts,wkSp,wkDg*0.3F,-1,limbSwing,limbSwingAmount);
        this.chainWave(legR,wkSp,wkDg,-1,limbSwing,limbSwingAmount);
        this.chainWave(legL,wkSp,wkDg,-1,limbSwing+0.5F,limbSwingAmount);
        this.chainFlap(legR,wkSp,wkDg*0.8F,3,limbSwing,limbSwingAmount);
        this.chainFlap(legL,wkSp,-wkDg*0.8F,3,limbSwing+0.5F,limbSwingAmount);
        this.swing(tail,wkSp,wkDg*0.5F,false,0,0,limbSwing,limbSwingAmount);
        // AAA IDLE MICRO-MOVEMENT
        float idle=1.0F-limbSwingAmount*0.6F;
        this.swing(tail,0.1F,0.08F,false,0,0,ageInTicks,idle);
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,bodymain,legbackL,legbackR,legfrontL,legfrontR,legmidL,legmidR,bodyfront,head,mouth,tail);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}
